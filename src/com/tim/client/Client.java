package com.tim.client;

import java.util.List;

import com.tim.config.Config;
import com.tim.exception.TimException;
import com.tim.listener.AckListener;
import com.tim.listener.MessageListener;
import com.tim.listener.PresenceListener;

public interface Client {

	/** 登陆 */
	public void login(String name, String pwd) throws TimException;

	/** 发送信息 */
	public void sendMessage(String toName, String msg, TidEnum tidType) throws TimException;

	/** 关闭 */
	public void close();

	/** 注册消息监听器 */
	public void addMessageListener(MessageListener messageListener);

	/** 注册出席协议监听器 */
	public void addPresenceListener(PresenceListener presenceListener);

	/** 注册回执监听器 */
	public void addAckListener(AckListener ackListener);

	/** 是否已经连接 */
	public boolean isConnect();

	/** 是否已经登陆 */
	public boolean isLogin();

	/** 流程节点 */
	public void setFlow(Flow flow);

	/** 流程节点 */
	public Flow getFlow();

	/** 配置 */
	public Config getConifg();

	/** 是否有效 */
	public boolean isValid();

	/** 加载信息 */
	public void loadMessage(List<String> toNameList, String fromstamp, String toStamp, int limitCount) throws TimException;

	/** 删除信息 */
	public void delMessage(String toName, String mid) throws TimException;

	/** 删除信息 */
	public void delAllMessage(String toName) throws TimException;

	void setValid(boolean valid);

	void addPing();

	void initPing();

	void processError(boolean error);
}
