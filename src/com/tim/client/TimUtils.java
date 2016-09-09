/**
 * Project Name:timClient
 * File Name:TimUtils.java
 * Package Name:com.tim.client
 * Date:2016年4月17日下午7:33:29
 */

package com.tim.client;

import com.tim.config.Config;
import com.tim.packet.Tid;
import com.tim.packet.TimMBean;

/**
 * ClassName:TimUtils <br/>
 * Date: 2016年4月17日 下午7:33:29 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.6
 * @see
 */
public class TimUtils {
	/***
	 * @author dong
	 * @methedDesc 方法描述： 用户tid
	 * @param loginname
	 * @param config
	 * @return
	 */
	public static Tid newTid(String loginname, Config config, TidEnum tidType) {
		Tid tid = new Tid();
		tid.setDomain(config.getDomain());
		tid.setName(loginname);
		tid.setType(tidType.getName());
		tid.setResource(config.getResource());
		return tid;
	}

	public static TimMBean newTimMBean(String toName, String msg, TidEnum tidType, Config config) {
		TimMBean mb = new TimMBean();
		mb.setBody(msg);
		mb.setToTid(newTid(toName, config, tidType));
		mb.setThreadId(threadId());
		mb.setType("chat");
		return mb;
	}

	public static String threadId() {
		return String.valueOf(System.nanoTime());
	}
}
