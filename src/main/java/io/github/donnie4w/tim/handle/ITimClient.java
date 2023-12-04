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

import io.github.donnie4w.tim.stub.*;

import java.nio.ByteBuffer;
import java.util.Map;

public interface ITimClient {

    // register with username and password
    // domain can be set "" where is not requied；Different domains cannot communicate with each other
    // 如果不需要使用 domain（域）时，可设置为空字符串，不同域无法相互通讯
    TimAck Register(String username, String pwd, String domain) throws TimException;

    // get a token for login
    long Token(String username, String pwd, String domain) throws TimException;

    // resource is the terminal information defined by the developer. For example, phone model: HUAWEI P50 Pro, iPhone 11 Pro
    // if resource is not required, pass ""
    // resource是开发者自定义的终端信息，一般是登录设备信息，如 HUAWEI P50 Pro，若不使用，赋空值即可
    void Login(String name, String pwd, String domain, String resource, byte termtyp,Map<String,String> extend) throws TimException;

    void LoginByToken(long token, String resource, byte termtyp,Map<String,String> extend) throws TimException;

    // 退出登录
    void Logout() throws TimException;

    // send message to a user
    // showType and textType is a value defined by the developer and is sent to the peer terminal as is
    // showType 和 textType 为开发者自定义数，会原样发送到对方的终端，由开发者自定义解析，
    // If showType or textType  is not required, pass 0
    // showType 或 textType  不使用时，传默认值0
    void MessageToUser(String toUser, String msg, short showType, short textType, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException;

    // revoke the message 撤回信息
    // mid is message's id
    // mid and to is  required
    void RevokeMessage(long mid, String touser, String roomid, String msg, short showType, short textType) throws TimException;

    // Burn After Reading  阅后即焚
    // mid is message's id
    // mid and to is  required
    void BurnMessage(long mid, String to, String msg, short showType, short textType) throws TimException;

    // send message to a room
    void MessageToRoom(String room, String msg, short showType, short textType, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException;

    // send a message to a room member
    void MessageByPrivacy(String user, String room, String msg, short showType, short textType, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException;

    // send  stream data to user
    void StreamToUser(String to, byte[] msg) throws TimException;

    // send  stream data to room
    void StreamToRoom(String room, byte[] msg) throws TimException;

    // send presence to other user
    // 发送状态给其他账号
    void PresenceToUser(String to, short show, String status, byte subStatus, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException;

    // send presence to other user list
    void PresenceToList(String[] toList, short show, String status, byte subStatus, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException;

    // broad the presence and substatus to all the friends
    // 向所有好友广播状态和订阅状态
    void BroadPresence(byte subStatus, short show, String status) throws TimException;

    // triggers tim to send user rosters
    // 触发tim服务器发送用户花名册
    void Roster() throws TimException;

    // send request to  the account for add friend
    void Addroster(String node, String msg) throws TimException;

    // remove a relationship with a specified account
    // 移除与指定账号的关系
    void Rmroster(String node) throws TimException;

    // Block specified account
    // 拉黑指定账号
    void Blockroster(String node) throws TimException;

    // pull message with user
    // 拉取用户聊天消息
    void PullUserMessage(String to, long mid, long limit) throws TimException;

    // pull message of group
    // 拉取群信息
    void PullRoomMessage(String to, long mid, long limit) throws TimException;

    // triggers tim to send the offlien message
    // 触发tim服务器发送离线信息
    void OfflineMsg() throws TimException;

    // triggers tim to send the user's ROOM account
    // 触发tim服务器发送用户的群账号
    void UserRoom() throws TimException;

    // triggers tim to send the ROOM member account
    // 触发tim服务器发送群成员账号
    void RoomUsers(String node) throws TimException;

    // creating a room, provide the room name and room type
    // 创建群，需提供群名称和群类型
    void NewRoom(long gtype, String roomname) throws TimException;

    // send a request to join the group
    // 发送一个加入群的请求
    void AddRoom(String node, String msg) throws TimException;

    // pull a account into the group
    // 将用户拉入群
    void PullInRoom(String roomNode, String userNode) throws TimException;

    // reject a account to join into the group
    // 拒绝用户加入群
    void RejectRoom(String roomNode, String userNode,String msg) throws TimException;

    // Kick a account out of the group
    // 将用户踢出群
    void KickRoom(String roomNode, String userNode) throws TimException;

    // leave group
    // 退出群
    void LeaveRoom(String roomNode) throws TimException;

    // Cancel a group
    // 注销群
    void CancelRoom(String roomNode) throws TimException;

    // block the group
    // 拉黑群
    void BlockRoom(String roomNode) throws TimException;

    // Cblock the group member or the account join into group
    // 拉黑群成员或其他账号入群
    void BlockRoomMember(String roomNode, String toNode) throws TimException;

    void BlockRosterList() throws TimException;

    void BlockRoomList() throws  TimException;

    void  BlockRoomMemberlist(String node) throws  TimException;

    // creating a Virtual room
    // 创建虚拟房间
    void VirtualroomRegister() throws TimException;

    // creating a Virtual room
    // 销毁虚拟房间
    void VirtualroomRemove(String roomNode) throws TimException;

    // Add push stream data permissions for virtual rooms to a account
    // 给账户添加向虚拟房间推送流数据的权限
    void VirtualroomAddAuth(String roomNode, String tonode) throws TimException;

    // delete the push stream data permissions for virtual rooms to a account
    // 删除用户向虚拟房间推送流数据的权限
    void VirtualroomDelAuth(String roomNode, String tonode) throws TimException;


    // Subscribe the stream data of the virtual room
    // 向虚拟房间订阅流数据
    void  VirtualroomSub(String roomNode) throws TimException;

    // cancel subscribe the stream data of the virtual room
    // 取消订阅虚拟房间数据
    void  VirtualroomSubCancel(String roomNode) throws TimException;

    // push the stream data to the virtual room
    // body: body is stream data
    // dtype : dtype is a data type defined by the developer and can be set to 0 if it is not required
    // 推送流数据到虚拟房间
    // body ：body是流数据
    // dtype：dtype 是开发者自定义的数据类型，若不需要，可以设置为0
    void  PushStream(String virtualroom,byte[] body,byte dtype) throws TimException;

    // get user information
    // 获取用户资料
   void UserInfo(String ...nodes) throws TimException;

    // get group information
    // 获取群资料
    void RoomInfo(String ...nodes) throws TimException;

    // modify user information
    // 修改用户资料
    void  ModifyUserInfo(TimUserBean tub) throws TimException;

    // modify group information
    // 修改群资料
    void ModifyRoomInfo(String roomNode, TimRoomBean trb) throws TimException;

}
