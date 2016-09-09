/**
 * Project Name:timClient
 * File Name:flow.java
 * Package Name:com.tim.client
 * Date:2016年4月30日上午11:36:56
 * Copyright (c) 2016, donnie4w@gmail.com All Rights Reserved.
 *
 */

package com.tim.client;

/**
 * ClassName: Flow.java <br/>
 * date: 2016年5月15日 下午6:13:10 <br/>
 *
 * @author dong
 * @version
 * @since JDK 1.7
 * @classDesc 类描述:
 */
public enum Flow {
	/** 连接 */
	CONNECT,
	/** 业务接口调用，登陆完成 */
	AUTH,
	/** 验证未通过 */
	AUTHNOPASS,
	/** 连接关闭 */
	DISCONNECT
}
