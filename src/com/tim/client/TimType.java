/**
 * Project Name:timClient
 * File Name:TimType.java
 * Package Name:com.tim.client
 * Date:2016年5月23日上午12:51:57
 * Copyright (c) 2016, donnie4w@gmail.com All Rights Reserved.
 *
 */

package com.tim.client;

/**
 * ClassName:TimType <br/>
 * Date: 2016年5月23日 上午12:51:57 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.7
 * @desc
 */
public enum TimType {

	LOGIN("login"), PING("ping"), MESSAGE("message"), PRESENCE("presence"), OTHER("");

	String type;

	TimType(String type) {
		this.type = type;
	}

	public static TimType getTimType(String type) {
		if (type == null) {
			return OTHER;
		}
		switch (type) {
		case "login":
			return LOGIN;
		case "ping":
			return PING;
		case "message":
			return MESSAGE;
		case "presence":
			return PRESENCE;
		default:
			return OTHER;
		}
	}
}
