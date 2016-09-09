/**
 * Project Name:timClient
 * File Name:C2sClient.java
 * Package Name:com.tim.client
 * Date:2016年4月30日上午11:49:34
 * Copyright (c) 2016, donnie4w@gmail.com All Rights Reserved.
 *
 */

package com.tim.client;

import org.apache.thrift.TException;

import com.tim.packet.Tid;
import com.tim.packet.TimAckBean;
import com.tim.packet.TimError;
import com.tim.packet.TimMBean;
import com.tim.packet.TimMessageIq;
import com.tim.packet.TimPBean;
import com.tim.packet.TimParam;

/**
 * ClassName:C2sClient <br/>
 * Date: 2016年4月30日 上午11:49:34 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.7
 * @desc
 */
public class C2sClient extends IfaceImpl {

	private IConnect connect;

	public static C2sClient newInstance(IConnect connect) {
		C2sClient c = new C2sClient();
		c.connect = connect;
		return c;
	}

	@Override
	public void timStream(TimParam param) throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timStream(param);
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timStarttls() throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timStarttls();
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timLogin(Tid tid, String pwd) throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timLogin(tid, pwd);
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timAck(TimAckBean ab) throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timAck(ab);
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timPresence(TimPBean pbean) throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timPresence(pbean);
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timMessage(TimMBean mbean) throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timMessage(mbean);
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timPing(String threadId) throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timPing(threadId);
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timError(TimError e) throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timError(e);
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timLogout() throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timLogout();
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timMessageIq(TimMessageIq timMsgIq, String iqType) throws TException {
		if (connect.getFlow() == FlowConnect.RUN) {
			connect.getTimClient().timMessageIq(timMsgIq, iqType);
		} else {
			throw new TException("FLOW IS NOT RUN:" + connect.getFlow());
		}
	}

	@Override
	public void timMessageResult(TimMBean mbean) throws TException {
	}

}