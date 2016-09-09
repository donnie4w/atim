package com.tim.listener;

import java.util.List;

import com.tim.packet.TimMBean;

public interface MessageListener {
	void processMessage(TimMBean mbean);

	void processMessage(List<TimMBean> mbeans);

	void loadMessage(TimMBean mbean);
}
