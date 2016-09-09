package com.tim.listener;

import com.tim.packet.TimPBean;

public interface PresenceListener {
	void processPresence(TimPBean pbean);
}
