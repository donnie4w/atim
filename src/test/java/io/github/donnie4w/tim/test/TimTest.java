/*
 * Copyright (c) 2024 donnie4w <donnie4w@gmail.com>. All rights reserved.
 * Original source: https://github.com/donnie4w/atim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.donnie4w.tim.test;

import io.github.donnie4w.tim.handle.TimException;
import io.github.donnie4w.tim.handle.Timclient;
import io.github.donnie4w.tim.stub.TimAck;
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
    public void timTest() throws Exception {
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
    public void register(){
        Timclient tc = new TimClientWithHandle().NewTimClientWithHandle("192.168.2.11", 5081, false);
        try {
           TimAck ta = tc.Register("tim10", "123", "tlnte.top");
            System.out.println(ta.ok);
            System.out.println(ta.n);
        } catch (TimException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void login(){
        Timclient tc = new TimClientWithHandle().NewTimClientWithHandle("192.168.2.11", 5081, false);
        try {
            tc.Login("tim10", "123", "tlnte.top","android",(byte)1,null);
            Thread.sleep(10000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
//        tc.VirtualroomAddAuth("NGhMpCbk2wQ", "100001");
//        tc.VirtualroomDelAuth("NGhMpCbk2wQ", "100001");
        tc.VirtualroomSub("NGhMpCbk2wQ");
//        tc.VirtualroomSubBinary("NGhMpCbk2wQ");
        tc.VirtualroomSubCancel("NGhMpCbk2wQ");
        tc.PushStream("NGhMpCbk2wQ", new byte[]{1, 2, 3, 4}, (byte) 0);
        Thread.sleep(1000000);
    }

    @Test
    public void VirtualroomSubBinary() throws Exception {
        Timclient tc = new TimClientWithHandle().NewTimClientWithHandle("192.168.2.11", 5080, false);
        tc.Login("tim3", "123", "tlnet.top", "tlnet.top", (byte) 0, null);
        tc.VirtualroomSubBinary("NGhMpCbk2wQ");
        Thread.sleep(1000000);
    }
}
