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
 * ClassName:flow <br/>
 * Date: 2016年4月30日 上午11:36:56 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.7
 * @desc
 */
public enum FlowConnect {
	/** 流程开始，connect */
	START,
	/** 业务接口调用，process完成 */
	RUN,
	/** 流程结束 */
	STOP
}
