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
import io.github.donnie4w.tim.handler.PullmessageHandler;
import io.github.donnie4w.tim.log.Log;
import io.github.donnie4w.tim.stub.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.github.donnie4w.tim.handle.Const.*;

public class TimClientWithHandle {

    final static byte SUBSTATUS_REQ = 1;
    final static byte SUBSTATUS_ACK = 2;

    String myAccount = "";

    public Timclient NewTimClientWithHandle(String ip, int port, boolean tls) {

        Timclient tc = new Timclient(ip, port, tls);
        tc.MessageHandler((TimMessage tm) -> {
            if (tm.msType == 1) {
                Log.debug("this is system message >>", "body>>>", tm.getDataString());
            } else if (tm.msType == 2) {
                Log.debug("this is user to user message");
            } else if (tm.msType == 3) {
                Log.debug("this is room to user message");
            }
            switch (tm.odType) {
                case 1: //常规消息
                    Log.debug("chat message >> from>>", tm.fromTid.node, " ,to>>", tm.toTid, ",body>>>", tm.getDataString());
                    break;
                case 2: //撤回消息
                    Log.debug("RevokeMessage>>>", tm.mid);
                    break;
                case 3: //阅后即焚
                    Log.debug("BurnMessage>>>", tm.mid);
                    break;
                case 4: //业务消息
                    Log.debug("business message>>>", tm);
                    break;
                case 5: //流数据
                    Log.debug("stream message>>>", tm);
                    break;
                default: //开发者自定义的消息
                    Log.debug("other message>>>", tm);
            }
        });
        tc.PullmessageHandler((TimMessageList tm) -> {
            Log.debug("pull msg >>>", tm);
        });
        tc.OfflineMsgHandler((TimMessageList tml) -> {
            if (tml != null) {
                for (TimMessage tm : tml.getMessageList()) {
                    Log.debug("offline msg>>>", tm);
                }
            }
        });

        //Indicates that the offline message is pushed
        //离线消息已经推送完毕
        tc.OfflinemsgEndHandler(() -> {
            tc.Roster();                                     //Pull the roster 拉取花名册
            tc.UserRoom();                                   // pull the account of group 拉取群账号
            tc.BlockRoomList();                               //blocklist of user group 用户群黑名单
            tc.BlockRosterList();                             //blocklist of user 用户黑名单
            tc.BroadPresence(SUBSTATUS_REQ, (short) 0, "I am busy😄"); //when finish offline message recive,subcript and broad the presence
        });

        tc.PullmessageHandler((tml)->{
            if (tml != null) {
                for (TimMessage tm : tml.getMessageList()) {
                    Log.debug("pull msg>>>", tm);
                }
            }
        });

        //记录状态订阅者
        Map<String, Byte> submap = new ConcurrentHashMap<>();
        //状态消息
        tc.PresenceHandler((TimPresence tp) -> {
            Log.debug(tp.fromTid.node, " presence");
            if (tp.subStatus > 0) {
                if (tp.subStatus == SUBSTATUS_REQ) {
                    tc.PresenceToUser(tp.fromTid.node, (short) 0, "", SUBSTATUS_ACK, null, null);
                }
                if ((tp.subStatus == SUBSTATUS_REQ) || (tp.subStatus == SUBSTATUS_ACK)) {
                    if (submap.containsKey(tp.fromTid.node)) {
                        submap.put(tp.fromTid.node, (byte) 0);
                    }
                }
            }
            if (tp.offline && tp.fromTid.node.equals(myAccount)) {
                tc.BroadPresence(SUBSTATUS_REQ, (short) 0, "I am busy😄");
            }
            Log.debug("presence>>>", tp);
        });

        //现场流数据（即直播数据，实时语音视频等流数据）
        tc.StreamHandler((TimStream ts) -> {
            Log.debug("steamData>>>>", ts);
        });


        //ack message from server 服务反馈的信息
        tc.AckHandler((TimAck ta) -> {
            Log.debug("ack>>>", ta);
            switch (ta.timType) {
                case TIMMESSAGE:
                    if (!ta.ok) { //not ok 表示信息发送失败(注意，信息发送成功是没有ack的，服务器会推送发送用户的信息回来，信息会带上时间与id)
                        Log.debug("send message failed>>", ta.error.code, ":", ta.error.info);
                    }
                    break;
                case TIMPRESENCE:
                    if (!ta.ok) { //not ok，表示状态信息发送失败(注意，状态信息发送成功是没有ack的)
                        Log.debug("send presence failed>>", ta.error.code, ":", ta.error.info);
                    }
                    break;
                case TIMLOGOUT: //强制下线
                    Log.debug("force to logout >>>", myAccount);
                    tc.Logout();//收到强制下线的指令后，主动退出登录
                    break;
                case TIMAUTH:
                    if (ta.ok) { //登录成功
                        myAccount = ta.n;
                        Log.debug("login successful,my node is :", myAccount);
                        tc.OfflineMsg(); //when login successful, get the offline message 登录成功后，拉取离线信息
                    } else {
                        Log.debug("login failed:", ta.error.code, ":", ta.error.info);
                        tc.Logout();
                    }
                    break;
                //虚拟房间操作回馈信息
                case TIMVROOM:
                    if (ta.ok) {
                        switch ((int) ta.t) {
                            case VRITURLROOM_REGISTER: //注册虚拟房间成功
                                Log.debug("register vriturl room ok>>", ta.t, " >>", ta.n);
                                break;
                            case VRITURLROOM_REMOVE: //删除虚拟房间成功
                                Log.debug("remove vriturl room ok>>", ta.t, " >>", ta.n);
                                break;
                            case VRITURLROOM_ADDAUTH: //加权成功
                                Log.debug("add auth to vriturl room ok>>", ta.t, " >>", ta.n);
                                break;
                            case VRITURLROOM_RMAUTH: //去权成功
                                Log.debug("cancel auth vriturl room process >>", ta.t, " >>", ta.n);
                                break;
                            case VRITURLROOM_SUB: //订阅成功
                                Log.debug("sub vriturl room process >>", ta.t, " >>", ta.n);
                                break;
                            case VRITURLROOM_SUBCANCEL: //取消订阅成功
                                Log.debug("sub cancel vriturl room process >>", ta.t, " >>", ta.n);
                                break;
                            default:
                                Log.debug("vriturl room process ok>>", ta.t, " >>", ta.n);
                        }
                    } else {
                        Log.debug("vriturl room process failed:", ta.error.code, ":", ta.error.info);
                    }
                    /*************************************************************/
                case TIMBUSINESS: //业务操作回馈
                    if (ta.ok) {
                        Log.debug("business process ok:", ta.n);
                        int t = (int) ta.t;
                        switch (t) {
                            case BUSINESS_REMOVEROSTER: //删除好友成功
                                Log.debug("romove friend successful:", ta.n);
                                break;
                            case BUSINESS_BLOCKROSTER: //拉黑好友成功
                                Log.debug("block  successful:", ta.n);
                                break;
                            case BUSINESS_NEWROOM: //新建群组成功
                                Log.debug("new group successful:", ta.n);
                                break;
                            case BUSINESS_ADDROOM: //
                                Log.debug("join group successful:", ta.n);
                                break;
                            case BUSINESS_PASSROOM: //申请加入群成功
                                Log.debug("new group successful:", ta.n);
                                break;
                            case BUSINESS_NOPASSROOM: //申请加入群不成功
                                Log.debug("reject  group successful:", ta.n);
                                break;
                            case BUSINESS_PULLROOM: //拉人入群
                                Log.debug("new group successful:", ta.n);
                                break;
                            case BUSINESS_KICKROOM: //踢人出群
                                Log.debug("kick out of group successful:", ta.n);
                                break;
                            case BUSINESS_BLOCKROOM:
                                Log.debug("block the group successful:", ta.n);
                                break;
                            case BUSINESS_LEAVEROOM: //退群
                                Log.debug("leave group successful:", ta.n);
                                break;
                            case BUSINESS_CANCELROOM: //注销群
                                Log.debug("cancel group successful:", ta.n);
                                break;
                            default:
                                Log.debug("business successful >>", ta);
                        }
                    } else {
                        Log.debug("business process failed:", ta.error.code, ":", ta.error.info);
                    }
            }
        });

        //批量账号信息返回
        tc.NodesHandler((TimNodes tn) -> {
            switch (tn.ntype) {
                case NODEINFO_ROSTER: //花名册返回
                    Log.debug(" roster ack >>>", tn);
                    if (tn.nodelist != null) {
                        tc.UserInfo(tn.nodelist.toArray(new String[tn.nodelist.size()]));//获取用户详细资料
                    }
                    break;
                case NODEINFO_ROOM: //用户的群账号返回
                    Log.debug(" groups ack >>>", tn);
                    if (tn.nodelist != null) {
                        tc.RoomInfo(tn.nodelist.toArray(new String[tn.nodelist.size()]));//获取群详细资料
                    }
                    break;
                case NODEINFO_ROOMMEMBER: //群成员账号返回
                    Log.debug(" group member ack >>>", tn);
                    break;
                case NODEINFO_USERINFO: //用户信息返回
                    Log.debug(" userinfo ack >>>");
                    for (Map.Entry<String, TimUserBean> me : tn.usermap.entrySet()) {
                        String k = me.getKey();
                        TimUserBean v = me.getValue();
                        Log.debug(k, ">>", v.getName(), " ", v.getNickName(), " ", v.getBrithday(), " ", v.getGender(), " ", v.getCover(), " ", v.getArea());
                    }
                    break;
                case NODEINFO_ROOMINFO: //群信息返回
                    Log.debug(" groupinfo ack >>>");
                    for (Map.Entry<String, TimRoomBean> me : tn.roommap.entrySet()) {
                        String k = me.getKey();
                        TimRoomBean v = me.getValue();
                        Log.debug(k, ">>", v.getTopic(), " ", v.getFounder(), " ", v.getManagers(), " ", v.getCreatetime(), " ", v.getLabel());
                    }
                    break;
                case NODEINFO_BLOCKROSTERLIST: //用户黑名单
                    Log.debug(" block roster list ack >>>", tn);
                    break;
                case NODEINFO_BLOCKROOMLIST: //用户拉黑群的群账号
                    Log.debug(" block room list ack >>>", tn);
                    break;
                case NODEINFO_BLOCKROOMMEMBERLIST: //群拉黑账号名单
                    Log.debug(" block room member list ack >>>", tn);
                    break;
            }
        });
        return tc;
    }
}
