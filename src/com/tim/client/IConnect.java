package com.tim.client;

import com.tim.listener.AckListener;
import com.tim.listener.MessageListener;
import com.tim.listener.PresenceListener;
import com.tim.packet.ITim;

/**
 * ClassName: IConnect.java <br/>
 * date: 2016年5月15日 下午4:56:14 <br/>
 *
 * @author dong
 * @version
 * @since JDK 1.7
 * @classDesc 类描述:
 */
public interface IConnect {

	public MessageListener getMessageListener();

	public void setMessageListener(MessageListener ml);

	public PresenceListener getPresenceListener();

	public void setPresenceListener(PresenceListener pl);

	public AckListener getAckListener();

	public void setAckListener(AckListener al);

	public C2sClient getC2sClient();

	public ITim.Client getTimClient();

	public FlowConnect getFlow();

	public void setFlow(FlowConnect fw);

	public void close();

}
