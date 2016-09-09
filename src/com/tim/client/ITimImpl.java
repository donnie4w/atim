package com.tim.client;

import org.apache.thrift.TException;

import com.tim.packet.*;

/**
 * ClassName:ITimImpl <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.6
 * @see
 */
public class ITimImpl extends IfaceImpl {
	// private static final Log logger = Log.getLogger();
	IConnect connect;
	Client client;

	public ITimImpl(IConnect connect, Client client) {
		this.connect = connect;
		this.client = client;
	}

	@Override
	public void timStream(TimParam param) throws TException {

	}

	@Override
	public void timStarttls() throws TException {
	}

	@Override
	public void timLogin(Tid tid, String pwd) throws TException {
	}

	@Override
	public void timAck(TimAckBean ab) throws TException {
		String ackType = ab.getAckType();
		String ackStatus = ab.getAckStatus();
		client.initPing();
		if (ackType != null) {
			switch (ackType) {
			case "login":
				if ("200".equals(ackStatus)) {
					client.setFlow(Flow.AUTH);
					client.setValid(true);
				}
				break;
			}
			try {
				connect.getAckListener().processAck(ab);
			} catch (Exception e) {
				new TException(e);
			}
		}
	}

	@Override
	public void timPresence(TimPBean pbean) throws TException {
		connect.getC2sClient().timAck(new TimAckBean().setAckType("presence").setAckStatus("200").setId(pbean.getThreadId()));
		try {
			connect.getPresenceListener().processPresence(pbean);
		} catch (Exception e) {
			new TException(e);
		}
	}

	@Override
	public void timMessage(TimMBean mbean) throws TException {
		connect.getC2sClient().timAck(new TimAckBean().setAckType("message").setAckStatus("200").setId(mbean.getThreadId()));
		try {
			connect.getMessageListener().processMessage(mbean);
		} catch (Exception e) {
			new TException(e);
		}
	}

	@Override
	public void timPing(String threadId) throws TException {
		if (client.getFlow() == Flow.AUTH) {
			connect.getC2sClient().timAck(new TimAckBean().setAckType("ping").setId(threadId));
		}
	}

	@Override
	public void timError(TimError e) throws TException {
	}

	@Override
	public void timLogout() throws TException {
		client.close();
	}

	@Override
	public void timRegist(Tid tid, String auth) throws TException {
	}

	@Override
	public void timMessageIq(TimMessageIq timMsgIq, String iqType) throws TException {
	}

	@Override
	public void timMessageResult(TimMBean mbean) throws TException {
		connect.getC2sClient().timAck(new TimAckBean().setAckType("message").setId(mbean.getThreadId()));
		connect.getMessageListener().loadMessage(mbean);
	}

	@Override
	public void timMessageList(TimMBeanList mbeanList) throws TException {
		connect.getC2sClient().timAck(new TimAckBean().setAckType("message").setAckStatus("200").setId(mbeanList.getThreadId()));
		try {
			connect.getMessageListener().processMessage(mbeanList.getTimMBeanList());
		} catch (Exception e) {
			new TException(e);
		}
	}

}
