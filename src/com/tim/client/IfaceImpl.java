/**
 * Project Name:atim
 * File Name:IfaceImpl.java
 * Package Name:com.tim.client
 * Date:2016年6月19日下午1:44:11
 * Copyright (c) 2016, donnie4w@gmail.com All Rights Reserved.
 *
 */

package com.tim.client;

import org.apache.thrift.TException;

import com.tim.packet.ITim;
import com.tim.packet.Tid;
import com.tim.packet.TimAckBean;
import com.tim.packet.TimAuth;
import com.tim.packet.TimError;
import com.tim.packet.TimMBean;
import com.tim.packet.TimMBeanList;
import com.tim.packet.TimMessageIq;
import com.tim.packet.TimPBean;
import com.tim.packet.TimPBeanList;
import com.tim.packet.TimParam;
import com.tim.packet.TimPropertyBean;
import com.tim.packet.TimRemoteUserBean;
import com.tim.packet.TimResponseBean;
import com.tim.packet.TimRoster;
import com.tim.packet.TimUserBean;

/**
 * ClassName:IfaceImpl <br/>
 * Date: 2016年6月19日 下午1:44:11 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.7
 * @desc
 */
public class IfaceImpl implements ITim.Iface {

	@Override
	public void timStream(TimParam param) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timStarttls() throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timLogin(Tid tid, String pwd) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timAck(TimAckBean ab) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timPresence(TimPBean pbean) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timMessage(TimMBean mbean) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timPing(String threadId) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timError(TimError e) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timLogout() throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timRegist(Tid tid, String auth) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timRoser(TimRoster roster) throws TException {

		// TODO Auto-generated method stub

	}

	@Override
	public void timMessageIq(TimMessageIq timMsgIq, String iqType) throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public void timMessageResult(TimMBean mbean) throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public TimRemoteUserBean timRemoteUserAuth(Tid tid, String pwd, TimAuth auth) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimRemoteUserBean timRemoteUserGet(Tid tid, TimAuth auth) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimRemoteUserBean timRemoteUserEdit(Tid tid, TimUserBean ub, TimAuth auth) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimResponseBean timResponsePresence(TimPBean pbean, TimAuth auth) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimResponseBean timResponseMessage(TimMBean mbean, TimAuth auth) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void timMessageList(TimMBeanList mbeanList) throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public void timPresenceList(TimPBeanList pbeanList) throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public void timProperty(TimPropertyBean tpb) throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public TimMBeanList timResponseMessageIq(TimMessageIq timMsgIq, String iqType, TimAuth auth) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimResponseBean timResponsePresenceList(TimPBeanList pbeanList, TimAuth auth) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimResponseBean timResponseMessageList(TimMBeanList mbeanList, TimAuth auth) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
