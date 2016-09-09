/**
 * Project Name:timClient
 * File Name:ClientImpl.java
 * Package Name:com.tim.client
 * Date:2016年5月15日下午9:37:26
 * Copyright (c) 2016, donnie4w@gmail.com All Rights Reserved.
 *
 */

package com.tim.client;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.tim.config.Config;
import com.tim.exception.TimException;
import com.tim.listener.AckListener;
import com.tim.listener.MessageListener;
import com.tim.listener.PresenceListener;
import com.tim.log.Log;

/**
 * ClassName:ClientImpl <br/>
 * Date: 2016年5月15日 下午9:37:26 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.7
 * @desc
 */
public class ClientImpl implements Client {
	private static final Log logger = Log.getLogger();
	private static ClientImpl ci;
	// private ClientImpl ci;
	AckListener al;
	MessageListener ml;
	PresenceListener pl;

	ClientSub cs;
	Flow flow;
	Config config;

	boolean valid = false;
	volatile int pingNum = 0;

	boolean isProcessError = false;

	// public ClientImpl() {
	// }

	@SuppressWarnings("static-access")
	private ClientImpl(Config config) throws TimException {
		this.config = config;
		cs = cs.newClient(this);
	}

	public synchronized static ClientImpl newClient(Config config) throws TimException {
		// public synchronized ClientImpl newClient(Config config) throws TimException {
		if (ci != null) {
			ci.close();
		}
		ci = new ClientImpl(config);
		ci.flow = Flow.CONNECT;
		if (config.isReconnectionAllowed()) {
			new Thread(new HeartBeat(ci)).start();
		}
		return ci;
	}

	static class HeartBeat implements Runnable {
		private static final Log logger = Log.getLogger();
		ClientImpl ci;
		private AtomicInteger ai = new AtomicInteger(0);

		public HeartBeat(ClientImpl ci) {
			this.ci = ci;
		}

		@Override
		public void run() {
			int sleeptime = ci.getConifg().getHeartbeat();
			while (ci.flow != Flow.DISCONNECT && ci.flow != Flow.AUTHNOPASS) {
				try {
					for (int k = 0; k < sleeptime; k++) {
						Thread.sleep(1000);
						if (ci.isProcessError) {
							throw new Exception("process error");
						}
					}
					if (ci.pingNum >= 2) {
						throw new Exception("ping number over limit :" + ci.pingNum);
					}
					if (ci.flow == Flow.AUTH) {
						ci.cs.connect.getC2sClient().timPing(String.valueOf(System.nanoTime()));
						ci.addPing();
						logger.info("ping timserver");
					}
				} catch (Exception e) {
					logger.info("ping timserver error " + e.getMessage());
					ci.disconnect();
					ci.setValid(false);
					ci.isProcessError = false;
					sleep();
					try {
						if (ci.flow == Flow.AUTH) {
							logger.info("tim try reconnect");
							ClientSub cs = ClientSub.newClient(ci.config, ci);
							ci.setClientSub(cs);
							ci.addAckListener(ci.al);
							ci.addMessageListener(ci.ml);
							ci.addPresenceListener(ci.pl);
							ci.login(ci.config.getLoginName(), ci.config.getPassword());
							ci.initPing();
							initAi();
						}
					} catch (Exception e1) {
						logger.info("try reConnect error:", e1);
					}
				}
			}
		}

		void sleep() {
			try {
				int sleepTime = getAi();
				if (sleepTime <= 5) {
					sleepTime = 1;
				} else if (sleepTime > 5 && sleepTime <= 30) {
					sleepTime = 60;
				} else if (sleepTime > 30 && sleepTime <= 100) {
					sleepTime = 180;
				} else if (sleepTime > 100) {
					sleepTime = 600;
				} else if (sleepTime > 500) {
					sleepTime = 1800;
				}
				Thread.sleep(sleepTime * 1000);
			} catch (Exception e) {
				logger.severe(e);
			}
		}

		int getAi() {
			if (ai.get() > 1000) {
				ai.set(0);
			}
			return ai.getAndIncrement();
		}

		void initAi() {
			ai.set(0);
		}

	}

	@Override
	public void login(String name, String pwd) throws TimException {
		cs.login(name, pwd);
	}

	@Override
	public void sendMessage(String toName, String msg, TidEnum tidType) throws TimException {
		cs.sendMessage(toName, msg, tidType);
	}

	@Override
	public void close() {
		flow = Flow.DISCONNECT;
		cs.close();
	}

	public void disconnect() {
		cs.close();
	}

	@Override
	public void addMessageListener(MessageListener messageListener) {
		ml = messageListener;
		cs.addMessageListener(messageListener);
	}

	@Override
	public void addPresenceListener(PresenceListener presenceListener) {
		pl = presenceListener;
		cs.addPresenceListener(presenceListener);
	}

	@Override
	public void addAckListener(AckListener ackListener) {
		al = ackListener;
		cs.addAckListener(ackListener);
	}

	@Override
	public boolean isConnect() {
		return cs.isConnect();
	}

	@Override
	public boolean isLogin() {
		return cs.isLogin();
	}

	@Override
	public void setFlow(Flow flow) {
		this.flow = flow;
		cs.setFlow(flow);
	}

	@Override
	public Config getConifg() {
		return this.config;
	}

	public void setClientSub(ClientSub cs) {
		this.cs = cs;
	}

	@Override
	public Flow getFlow() {
		return this.flow;
	}

	@Override
	public boolean isValid() {
		if (!valid) {
			return valid;
		}
		try {
			if (this.flow == Flow.AUTH) {
				this.cs.connect.getC2sClient().timPing(String.valueOf(System.nanoTime()));
				addPing();
				logger.info("ping timserver valid");
			}
		} catch (Exception e) {
			logger.info("valid error", e);
			return false;
		}
		return valid;
	}

	@Override
	public void loadMessage(List<String> toNameList, String fromstamp, String toStamp, int limitCount) throws TimException {
		cs.loadMessage(toNameList, fromstamp, toStamp, limitCount);
	}

	@Override
	public void delMessage(String toName, String mid) throws TimException {
		cs.delMessage(toName, mid);
	}

	@Override
	public void delAllMessage(String toName) throws TimException {
		cs.delAllMessage(toName);
	}

	@Override
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public void addPing() {
		this.pingNum++;
		logger.info("ping+1===>" + this.pingNum);
	}

	@Override
	public void initPing() {
		logger.info("initping");
		this.pingNum = 0;
	}

	@Override
	public void processError(boolean error) {

		// TODO Auto-generated method stub

	}

}
