package com.tim.client;

import java.util.concurrent.CountDownLatch;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.tim.listener.AckListener;
import com.tim.listener.MessageListener;
import com.tim.listener.PresenceListener;
import com.tim.log.Log;
import com.tim.packet.ITim;
import com.tim.client.ITimImpl;
import com.tim.config.Config;
import com.tim.exception.TimException;

/**
 * ClassName: Connect.java <br/>
 * date: 2016年4月17日 上午12:42:06 <br/>
 *
 * @author dong donnie4w@qq.com donnie4w@gmail.com
 * @version
 * @since JDK 1.7
 * @classDesc 类描述:
 */
public class ConnectImpl implements IConnect {
	private static final Log logger = Log.getLogger();

	private TTransport transport;
	private ITim.Client timClient;
	private C2sClient c2sClient = C2sClient.newInstance(this);
	private Config config;
	private FlowConnect flow;
	private MessageListener messageListener;
	private PresenceListener presenceListener;
	private AckListener ackListener;
	private com.tim.client.Client client;

	private ConnectImpl(Config config) {
		this.config = config;
	}

	public static ConnectImpl newConnect(com.tim.client.Client client) throws TimException {
		ConnectImpl c = new ConnectImpl(client.getConifg());
		c.client = client;
		try {
			c.connect();
		} catch (Exception e) {
			throw new TimException(e);
		}
		return c;
	}

	private void connect() throws TimException {
		try {
			transport = new TSocket(config.getIp(), config.getPort(), 0, config.getConnectTimeout());
			TProtocol protocol = new TCompactProtocol(transport);
			transport.open();
			flow = FlowConnect.START;
			CountDownLatch cdl = new CountDownLatch(1);
			new Thread(new TprocessorClient(protocol, this, cdl, client)).start();
			cdl.await();
			timClient = new ITim.Client(protocol);
		} catch (Exception e) {
			logger.severe(e);
			throw new TimException(e);
		}
	}

	public void close() {
		if (transport != null) {
			try {
				flow = FlowConnect.STOP;
				transport.close();
			} catch (Exception e) {
				logger.severe(e.getMessage());
			}
		}
	}

	public static void close(ConnectImpl connect) {
		if (connect != null) {
			try {
				connect.setFlow(FlowConnect.STOP);
				connect.close();
			} catch (Exception e) {
				logger.severe("close connect error:", e);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static class TprocessorClient implements Runnable {
		ConnectImpl connect;
		ITim.Processor processor;
		TProtocol protocol;
		CountDownLatch c4pro;
		com.tim.client.Client timClient;

		public TprocessorClient(TProtocol protocol, ConnectImpl connect, CountDownLatch c4pro, com.tim.client.Client timClient) {
			this.protocol = protocol;
			this.connect = connect;
			this.timClient = timClient;
			this.processor = new ITim.Processor(new ITimImpl(connect, timClient));
			this.c4pro = c4pro;
		}

		@Override
		public void run() {
			boolean isprocess = false;
			while (true) {
				try {
					if (connect.getFlow() != FlowConnect.STOP) {
						if (!isprocess) {
							isprocess = true;
							c4pro.countDown();
						}
						if (connect.getFlow() == FlowConnect.START) {
							connect.setFlow(FlowConnect.RUN);
						}
						processor.process(protocol, protocol);
					} else {
						break;
					}
				} catch (Exception e) {
					logger.severe("TprocessorClient:", e);
					timClient.processError(true);
					connect.close();
					break;
				} finally {
				}
			}
			timClient.setValid(false);
		}
	}

	// static class TMonitor implements Runnable {
	// ITim.Client client;
	// TTransport transport;
	// IConnect connect;
	//
	// public TMonitor(IConnect connect, TTransport transport) {
	// this.connect = connect;
	// this.client = connect.getTimClient();
	// this.transport = transport;
	// }
	//
	// @Override
	// public void run() {
	// int limit = 3;
	// while (true) {
	// try {
	// Thread.sleep(10 * 1000);
	// client.timPing("c2s>>>" + String.valueOf(System.currentTimeMillis()));
	// } catch (Exception e) {
	// if (limit-- < 0) {
	// logger.severe("TMonitor:", e);
	// break;
	// }
	// logger.severe("TMonitor ping fail :", limit);
	// }
	// }
	// }
	// }

	@Override
	public MessageListener getMessageListener() {
		return this.messageListener;
	}

	@Override
	public PresenceListener getPresenceListener() {
		return this.presenceListener;
	}

	@Override
	public AckListener getAckListener() {
		return this.ackListener;
	}

	@Override
	public C2sClient getC2sClient() {
		return this.c2sClient;
	}

	@Override
	public ITim.Client getTimClient() {
		return this.timClient;
	}

	@Override
	public FlowConnect getFlow() {
		return this.flow;
	}

	@Override
	public void setFlow(FlowConnect fw) {
		this.flow = fw;
	}

	@Override
	public void setMessageListener(MessageListener ml) {
		this.messageListener = ml;
	}

	@Override
	public void setPresenceListener(PresenceListener pl) {
		this.presenceListener = pl;
	}

	@Override
	public void setAckListener(AckListener al) {
		this.ackListener = al;
	}
}
