package com.tim.listener;

import com.tim.packet.TimAckBean;

public interface AckListener {
	void processAck(TimAckBean ab);
}
