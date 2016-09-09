/**
 * Project Name:timClient
 * File Name:TimEnum.java
 * Package Name:com.tim.client
 * Date:2016年4月17日下午7:28:59
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
 */

package com.tim.client;

/**
 * ClassName:TimEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年4月17日 下午7:28:59 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.6
 * @see
 */
public enum TidEnum {
	/** 用户类型 普通用户TID */
	TIDTYPE_NORMA("normal"),
	/** 用户类型 群TID */
	TIDTYPE_GROUP("group");
	
	private String name;

	private TidEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
