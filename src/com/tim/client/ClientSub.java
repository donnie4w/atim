package com.tim.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import com.tim.config.Config;
import com.tim.exception.TimException;
import com.tim.listener.AckListener;
import com.tim.listener.MessageListener;
import com.tim.listener.PresenceListener;
import com.tim.log.Log;
import com.tim.packet.TimMessageIq;
import com.tim.packet.TimPage;
import com.tim.packet.TimParam;
import com.tim.packet.timConstants;

/**
 * @author wuxiaodong
 * @email donnie4w@gmail.com donnie4w@qq.com
 */

public class ClientSub implements Client {

	private static final Log logger = Log.getLogger();

	ConnectImpl connect;
	Config config;
	Flow flow;
	boolean valid;

	public ClientSub(Config config) {
		this.config = config;
	}

	public static ClientSub newClient(Client client) throws TimException {
		ClientSub ci = new ClientSub(client.getConifg());
		logger.info("tim ip:", client.getConifg().getIp());
		logger.info("tim port:", client.getConifg().getPort());
		try {
			ci.connect = ConnectImpl.newConnect(client);
			ci.flow = Flow.CONNECT;
		} catch (Exception e) {
			logger.severe("newClient error:", e);
			throw new TimException(e);
		}
		return ci;
	}

	public static ClientSub newClient(Config config, Client client) throws TimException {
		ClientSub ci = new ClientSub(config);
		logger.info("tim ip:", config.getIp());
		logger.info("tim port:", config.getPort());
		try {
			ci.connect = ConnectImpl.newConnect(client);
			ci.flow = Flow.CONNECT;
		} catch (Exception e) {
			logger.severe("newClient error:", e);
			throw new TimException(e);
		}
		return ci;
	}

	@Override
	public void login(String name, String pwd) throws TimException {
		try {
			TimParam param = new TimParam();
			param.setVersion((short) timConstants.protocolversion);
			param.setInterflow("1");
			connect.getC2sClient().timStream(param);
			config.setLoginName(name);
			config.setPassword(pwd);
			connect.getC2sClient().timLogin(TimUtils.newTid(name, config, TidEnum.TIDTYPE_NORMA), pwd);
		} catch (TException e) {
			throw new TimException(e);
		}
	}

	@Override
	public void sendMessage(String toName, String msg, TidEnum tidType) throws TimException {
		try {
			connect.getC2sClient().timMessage(TimUtils.newTimMBean(toName, msg, tidType, config));
		} catch (TException e) {
			throw new TimException(e);
		}
	}

	@Override
	public void close() {
		connect.close();
	}

	@Override
	public void addMessageListener(MessageListener messageListener) {
		connect.setMessageListener(messageListener);
	}

	@Override
	public void addPresenceListener(PresenceListener presenceListener) {
		connect.setPresenceListener(presenceListener);
	}

	@Override
	public void addAckListener(AckListener ackListener) {
		connect.setAckListener(ackListener);
	}

	@Override
	public boolean isConnect() {
		return flow == Flow.CONNECT;
	}

	@Override
	public boolean isLogin() {
		return flow == Flow.AUTH;
	}

	@Override
	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	@Override
	public Config getConifg() {
		return this.config;
	}

	@Override
	public Flow getFlow() {
		return flow;
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public void loadMessage(List<String> toNameList, String fromstamp, String toStamp, int limitCount) throws TimException {
		try {
			TimMessageIq timMsgIq = new TimMessageIq();
			timMsgIq.timPage = new TimPage();
			timMsgIq.setTidlist(toNameList);
			timMsgIq.timPage.setFromTimeStamp(fromstamp);
			timMsgIq.timPage.setToTimeStamp(toStamp);
			timMsgIq.timPage.setLimitCount(limitCount);
			connect.getC2sClient().timMessageIq(timMsgIq, "get");
		} catch (TException e) {
			throw new TimException(e);
		}
	}

	@Override
	public void delMessage(String toName, String mid) throws TimException {
		try {
			TimMessageIq timMsgIq = new TimMessageIq();
			List<String> namelist = new ArrayList<>();
			namelist.add(toName);
			timMsgIq.setTidlist(namelist);
			List<String> midlist = new ArrayList<>();
			midlist.add(mid);
			timMsgIq.setMidlist(midlist);
			connect.getC2sClient().timMessageIq(timMsgIq, "del");
		} catch (TException e) {
			throw new TimException(e);
		}
	}

	@Override
	public void delAllMessage(String toName) throws TimException {
		try {
			TimMessageIq timMsgIq = new TimMessageIq();
			List<String> namelist = new ArrayList<>();
			namelist.add(toName);
			timMsgIq.setTidlist(namelist);
			connect.getC2sClient().timMessageIq(timMsgIq, "delAll");
		} catch (TException e) {
			throw new TimException(e);
		}
	}

	@Override
	public void setValid(boolean valid) {
	}

	@Override
	public void addPing() {
		logger.info("clientsub addPing");
	}

	@Override
	public void initPing() {
		logger.info("clientsub initping");
	}

	@Override
	public void processError(boolean error) {
		logger.info("processError :" + error);
	}
}