/*
 * Copyright (c) , donnie <donnie4w@gmail.com>
 * All rights reserved.
 * https://github.com/donnie4w/tim
 * https://githuc.com/donnie4w/atim
 *
 * Use of this source code is governed by a MIT-style license that can be
 * found in the LICENSE file
 */
package io.github.donnie4w.tim.handle;

public class Const {
    public static final byte TIMACK = 12; //回复ACK
    public static final byte TIMPING = 13; //ping
    public static final byte TIMREGISTER = 14; //注册
    public static final byte TIMTOKEN = 15; //拉取token
    public static final byte TIMAUTH = 16; //登录验证
    public static final byte TIMOFFLINEMSG = 17; //推送离线消息
    public static final byte TIMOFFLINEMSGEND = 18; //推送离线消息完毕标识
    public static final byte TIMBROADPRESENCE = 19; //广播状态订阅信息给在线好友
    public static final byte TIMLOGOUT = 20; //强制下线
    public static final byte TIMPULLMESSAGE = 21; //拉取聊天信息
    public static final byte TIMVROOM = 22; //虚拟房间操作
    public static final byte TIMBUSINESS = 41; //业务
    public static final byte TIMNODES = 42; //账号信息
    public static final byte TIMMESSAGE = 90; //消息
    public static final byte TIMPRESENCE = 91; //状态
    public static final byte TIMREVOKEMESSAGE = 92; //撤回
    public static final byte TIMBURNMESSAGE = 93; //焚烧信息
    public static final byte TIMSTREAM = 94; //虚拟房间流数据

    //room species 群种类
    public static final long ROOM_PRIVATE = 1; //私有群，入群需验证
    public static final long ROOM_OPEN = 2; //公开群，入群不需验证


    public static final int BUSINESS_ROSTER = 1;  //拉取花名册
    public static final int BUSINESS_USERROOM = 2;  //拉取群账号
    public static final int BUSINESS_ROOMUSERS = 3; //拉取群成员账号
    public static final int BUSINESS_ADDROSTER = 4;  //加好友
    public static final int BUSINESS_FRIEND = 5;  //成为好友
    public static final int BUSINESS_REMOVEROSTER = 6;  //删除好友
    public static final int BUSINESS_BLOCKROSTER = 7; //拉黑账号
    public static final int BUSINESS_NEWROOM = 8;  //建群
    public static final int BUSINESS_ADDROOM = 9;  //加入群
    public static final int BUSINESS_PASSROOM = 10; //通过加群申请
    public static final int BUSINESS_NOPASSROOM = 11; //不通过加群申请
    public static final int BUSINESS_PULLROOM = 12; //拉人入群
    public static final int BUSINESS_KICKROOM = 13; //踢人出群
    public static final int BUSINESS_BLOCKROOM = 14;//拉黑群
    public static final int BUSINESS_BLOCKROOMMEMBER = 15; //拉黑群成员
    public static final int BUSINESS_LEAVEROOM = 16; //退群
    public static final int BUSINESS_CANCELROOM = 17; //注销群
    public static final int BUSINESS_BLOCKROSTERLIST = 18; //拉取账号黑名单
    public static final int BUSINESS_BLOCKROOMLIST = 19; //拉取账号拉黑群名单
    public static final int BUSINESS_BLOCKROOMMEMBERLIST = 20; //管理员拉取群黑名单
    public static final  int BUSINESS_MODIFYAUTH  = 21; //修改密码
    public static final int NODEINFO_ROSTER = 1;//花名册
    public static final int NODEINFO_ROOM = 2; //用户的群
    public static final int NODEINFO_ROOMMEMBER = 3; //群的成员
    public static final int NODEINFO_USERINFO = 4; //用户信息
    public static final int NODEINFO_ROOMINFO = 5; //群信息
    public static final int NODEINFO_MODIFYUSER = 6; //修改用户信息
    public static final int NODEINFO_MODIFYROOM = 7;//修改群信息
    public static final int NODEINFO_BLOCKROSTERLIST = 8; //用户黑名单
    public static final int NODEINFO_BLOCKROOMLIST = 9;//用户拉黑群的群账号
    public static final int NODEINFO_BLOCKROOMMEMBERLIST = 10;//群拉黑账号名单


    public static final int VRITURLROOM_REGISTER = 1; //虚拟房间注册
    public static final int VRITURLROOM_REMOVE = 2;//虚拟房间删除
    public static final int VRITURLROOM_ADDAUTH = 3; //虚拟房间加权
    public static final int VRITURLROOM_RMAUTH = 4; //虚拟房间除权
    public static final int VRITURLROOM_SUB = 5; //虚拟房间订阅
    public static final int VRITURLROOM_SUBCANCEL = 6;//虚拟房间取消订阅
}
