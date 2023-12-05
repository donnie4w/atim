/*
 * Copyright (c) , donnie <donnie4w@gmail.com>
 * All rights reserved.
 * https://github.com/donnie4w/tim
 * https://githuc.com/donnie4w/atim
 *
 * Use of this source code is governed by a MIT-style license that can be
 * found in the LICENSE file
 */

package io.github.donnie4w.tim.test;

import io.github.donnie4w.tim.handle.Timclient;
import io.github.donnie4w.tim.stub.TimRoomBean;
import io.github.donnie4w.tim.stub.TimUserBean;
import org.junit.Test;

public class TimTest {

    final static byte MOBILE = 1;  //移动终端
    final static byte DESKTOP = 2;  //桌面终端

    final static byte SUBSTATUS_REQ = 1;  //订阅状态
    final static byte SUBSTATUS_ACK = 2;  //回复订阅状态
    final static short SHOW_CHAT = 1;   //聊天中
    final static short SHOW_AWAY = 2;   //离线
    final static short SHOW_XA = 3;     //走开
    final static short SHOW_DND = 4;    //勿扰

    final static String STATUS_EMOTION_HAPPY = "I'm happy";
    final static String STATUS_EMOTION_SAD = "I'm down";
    final static String STATUS_EMOTION_SUNSHINE = "☀️☀️☀️";



    @Test
    public void register() {
    }

    @Test
    public void login() throws Exception {
        Timclient tc = new TimClientWithHandle().NewTimClientWithHandle("192.168.2.11", 5120, false);
        tc.Register("jerry", "123", "github.com");
        tc.Login("jerry", "123", "github.com", "ios-20-plus", MOBILE, null);
        tc.MessageToUser("user1", "12345", (short) 0, (short) 0, null, null);
        tc.MessageToRoom("room1", "12345", (short) 0, (short) 0, null, null);
        tc.MessageByPrivacy("user1", "room1","12345", (short) 0, (short) 0, null, null);
        //向账号10001发送状态
        tc.PresenceToUser("10001", SHOW_CHAT, STATUS_EMOTION_SUNSHINE, SUBSTATUS_REQ, null, null);


        tc.PresenceToList(new String[]{"10001"}, SHOW_CHAT, STATUS_EMOTION_SUNSHINE, SUBSTATUS_REQ, null, null);
        //向所有好友广播个人状态并订阅对方状态
        tc.BroadPresence(SUBSTATUS_REQ, SHOW_CHAT, STATUS_EMOTION_SUNSHINE);

        tc.RevokeMessage(1L,"10001", null,  null, (short) 0, (short) 0);
        tc.RevokeMessage(1L,null, "100000001",  null, (short) 0, (short) 0);
        tc.BurnMessage(1L,"10001",   null, (short) 0, (short) 0);

        Thread.sleep(100000);
    }

    @Test
    public void VirtualroomSub() throws Exception {
        Timclient tc = new TimClientWithHandle().NewTimClientWithHandle("192.168.2.11", 5080, false);
        tc.Login("tim3", "123", "tlnet.top", "ios-20-plus", (byte) 0, null);
        tc.VirtualroomSub("NGhMpCbk2wQ");
        Thread.sleep(1000000);
    }

    @Test
    public void Virtualroom() throws Exception {
        Timclient tc = new TimClientWithHandle().NewTimClientWithHandle("192.168.2.11", 5080, false);
        tc.Login("tim3", "123", "tlnet.top", "ios-20-plus", (byte) 0, null);
        tc.VirtualroomRegister();
        tc.VirtualroomRemove("NGhMpCbk2wQ");
        tc.VirtualroomAddAuth("NGhMpCbk2wQ", "100001");
        tc.VirtualroomDelAuth("NGhMpCbk2wQ", "100001");
        tc.VirtualroomSub("NGhMpCbk2wQ");
        tc.VirtualroomSubCancel("NGhMpCbk2wQ");
        tc.PushStream("NGhMpCbk2wQ", new byte[]{1, 2, 3, 4}, (byte) 0);
        Thread.sleep(1000000);
    }
}
