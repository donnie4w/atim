package com.tim.client;

import com.tim.config.Config;
import com.tim.exception.TimException;
import com.tim.log.LogLevel;

/**
 * ClassName:ClientFactory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年4月17日 下午11:00:51 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.6
 * @see
 */
public class ClientFactory {
	public static Client getClient(Config config) throws TimException {
		// return new ClientImpl().newClient(config);
		return ClientImpl.newClient(config);
	}

	public static Client getClient(Client client, Config config) throws TimException {
		if (client != null) {
			client.close();
		}
		return getClient(config);
	}

	public static void main(String[] args) {
		Config.setLogLevel(LogLevel.INFO);
		Config config = new Config();
		config.setDomain("wuxiaodong.name");
		config.setHeartbeat(30);
		config.setIp("127.0.0.1");
		config.setPort(3737);
		config.setReconnectionAllowed(true);
		config.setLoginName("wu");
		config.setPassword("123456");
		try {
			Client client = getClient(config);
			client.sendMessage("dong", "helloWorld~~你好世界!", TidEnum.TIDTYPE_NORMA);
		} catch (TimException e) {
			e.printStackTrace();
		}
	}
}
