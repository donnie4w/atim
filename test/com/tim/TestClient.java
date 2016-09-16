/**
 * Project Name:timClient
 * File Name:TestClient.java
 * Package Name:com.tim
 * Date:2016年4月25日下午2:04:31
 * Copyright (c) 2016, donnie4w@gmail.com All Rights Reserved.
 *
 */

package com.tim;

import java.util.Date;
import java.util.List;

import com.tim.client.Client;
import com.tim.client.ClientFactory;
import com.tim.client.TPClient;
import com.tim.client.TidEnum;
import com.tim.client.TimType;
import com.tim.config.Config;
import com.tim.listener.AckListener;
import com.tim.listener.MessageListener;
import com.tim.listener.PresenceListener;
import com.tim.log.Log;
import com.tim.log.LogLevel;
import com.tim.packet.Tid;
import com.tim.packet.TimAckBean;
import com.tim.packet.TimMBean;
import com.tim.packet.TimNode;
import com.tim.packet.TimPBean;
import com.tim.packet.TimTime;

/**
 * ClassName:TestClient <br/>
 * Date: 2016年4月25日 下午2:04:31 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.7
 * @desc
 */
public class TestClient implements Runnable {
	private static final Log logger = Log.getLogger();
	String name, pwd, toname;

	public TestClient(String name, String pwd, String toname) {
		this.name = name;
		this.pwd = pwd;
		this.toname = toname;
	}

	@SuppressWarnings("unchecked")
	public void Test(String name, String pwd) throws Exception {
		Config.setLogLevel(LogLevel.INFO);
		Config config = new Config();
		config.setDomain("wuxiaodong");
		config.setHeartbeat(40);
		config.setIp("127.0.0.1");
		config.setPort(3737);
		config.setResource("huawei");
		config.setReconnectionAllowed(true);
		config.setTLS(true); // 用TLS传输
		config.setTsslPort(5757); // 服务器TLS端口
		TPClient<Client> tp = TPClient.newInstance(config);
		Client client = tp.getClient(ClientFactory.getClient(config));
		client.addMessageListener(new MessageListenerImpl());
		client.addAckListener(new AckListenerImpl(client));
		client.addPresenceListener(new PresenceListenerImpl());
		client.login(name, pwd);
		Thread.sleep(10000);
		int i = 0;
		while (true) {
			Thread.sleep(800);
			client.sendMessage(toname, "hello>>>" + i++, TidEnum.TIDTYPE_NORMA);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Test(this.name, this.pwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			new Thread(new TestClient("dong", "1234", "wu")).start();
			// new Thread(new TestClient("wu", "1234", "dong")).start();
			Thread.sleep(10000000);
		} catch (Exception e) {
			logger.severe(e);
		}
	}
}

/**
 * @classDesc 类描述: ack监听实现类
 */
class AckListenerImpl implements AckListener {
	private static final Log logger = Log.getLogger();
	Client client;

	public AckListenerImpl(Object... args) {
		client = (Client) args[0];
	}

	@Override
	public void processAck(TimAckBean ab) {
		logger.info(ab == null ? "null" : ab.toString());
		TimType ackType = TimType.getTimType(ab.getAckType());
		String ackStatus = ab.getAckStatus();
		logger.info("ackType:", ackType);
		logger.info("ackStatus:", ackStatus);
		if (ackType != null) {
			switch (ackType) {
			case LOGIN:
				if ("400".equals(ackStatus)) {
					logger.info("用户名或密码错误");
					client.close();
				}
				break;
			case MESSAGE:
				break;
			case PING:
				break;
			case PRESENCE:
				break;
			default:
			}
		}
	}
}

/**
 * @classDesc 类描述: message监听实现类
 */
class MessageListenerImpl implements MessageListener {
	private static final Log logger = Log.getLogger();

	public MessageListenerImpl(Object... args) {
	}

	@Override
	public void processMessage(TimMBean mbean) {
		String type = mbean.getType(); // chat
		short msgType = mbean.getMsgType(); // 1 文字 2图片 3音频 4视频
		String mid = mbean.getMid(); // 信息id
		String timestamp = mbean.getTimestamp();
		Date date = new Date(Long.valueOf(timestamp)); // 发信时间
		Tid fromtid = mbean.getFromTid(); // 发信者tid
		String name = fromtid.getName(); // 发信登陆名
		TimTime tt = mbean.getOffline();
		if (tt != null) {
			// 说明是离线信息
		}
		List<TimNode> extraList = mbean.getExtraList(); // 附加字段
		logger.info(type);
		logger.info(msgType);
		logger.info(mbean.getBody());
		logger.info(mid);
		logger.info(date);
		logger.info(name);
		logger.info(tt);
		logger.info(extraList);
	}

	@Override
	public void loadMessage(TimMBean mbean) {
		logger.info(mbean.toString());
	}

	@Override
	public void processMessage(List<TimMBean> mbeans) {
		if (mbeans != null && mbeans.size() > 0) {
			for (TimMBean tm : mbeans) {
				processMessage(tm);
			}
		}
	}
}

class PresenceListenerImpl implements PresenceListener {
	private static final Log logger = Log.getLogger();

	@Override
	public void processPresence(TimPBean pbean) {
		if (pbean != null) {
			logger.info("在线状态:", pbean.toString());
		}
	}
}
