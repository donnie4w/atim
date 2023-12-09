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

import io.github.donnie4w.tim.handler.*;
import io.github.donnie4w.tim.stub.*;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static io.github.donnie4w.tim.handle.Const.*;

public class Timclient implements ITimClient {
    private final static Logger logger = Logger.getLogger("atim");
    private final Tx tx;
    private final String addr;
    protected Conf conf;
    protected int pingCount;
    protected boolean isLogout;
    protected int v;
    private Clihandle handler;
    private MessageHandler messageHandler;
    private PresenceHandler presenceHandler;
    private AckHandler ackHandler;
    private NodesHandler nodesHandler;

    private PullmessageHandler pullmessageHandler;

    private OfflineMsgHandler offlineMsgHandler;

    private OfflinemsgEndHandler offlinemsgEndHandler;

    private StreamHandler streamHandler;

    public Timclient(String ip, int port, boolean tls) throws TimRunTimeException {
        this.addr = formatUrl(ip, port, tls);
        if (this.addr == null) {
            throw new TimRunTimeException("service address error for tim");
        }
        this.tx = new Tx();
        init();
    }

    private void init() {
        this.conf = new Conf(this.addr, "https://github.com/donnie4w/tim") {
            @Override
            public void onMessage(byte[] msg) {
                try {
                    byte t = (byte) (msg[0] & 0x7f);
                    if ((byte) (msg[0] & 0x80) == (byte) 0x80) {
                        ByteBuffer bb = ByteBuffer.allocate(5);
                        bb.put(TIMACK);
                        bb.put(Arrays.copyOfRange(msg, 1, 5));
                        handler.sendws(bb.array());
                        msg = Arrays.copyOfRange(msg, 5, msg.length);
                    } else {
                        msg = Arrays.copyOfRange(msg, 1, msg.length);
                    }
                    pingCount = 0;
                    doMsg(t, msg);
                } catch (Exception e) {
                    logger.warning("failed to parse server data>>>"+e.getMessage());
                }
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onClose() {
                try {
                    logger.info("onClose");
                    close();
                    Thread.sleep(1000 << 2);
                    if (!isLogout) {
                        connect();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onOpen() {
            }
        };
    }

    private void login() throws TimException {
        if (handler != null) {
            this.handler.sendws(this.tx.login());
        }
    }

    private void connect() throws TimException {
        this.isLogout = false;
        this.pingCount = 0;
        this.v++;
        try {
            handler = new Clihandle(this.conf);
            this.login();
            new Thread(new watch(this.v, this)).start();
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            try {
                Thread.sleep(1000 << 2);
            } catch (InterruptedException ex) {
            }
            this.connect();
        }
    }


    private void doMsg(byte t, byte[] msg) throws TimException {
        switch (t) {
            case TIMPING:
                if (this.pingCount > 0) {
                    this.pingCount--;
                }
                break;
            case TIMACK:
                if (this.ackHandler != null) {
                    this.ackHandler.run(Utils.tDecode(msg, new TimAck()));
                }
                break;
            case TIMMESSAGE:
                if (this.messageHandler != null) {
                    this.messageHandler.run(Utils.tDecode(msg, new TimMessage()));
                }
                break;
            case TIMPRESENCE:
                if (this.presenceHandler != null) {
                    this.presenceHandler.run(Utils.tDecode(msg, new TimPresence()));
                }
                break;
            case TIMNODES:
                if (this.nodesHandler != null) {
                    this.nodesHandler.run(Utils.tDecode(msg, new TimNodes()));
                }
                break;
            case TIMPULLMESSAGE:
                if (this.pullmessageHandler != null) {
                    this.pullmessageHandler.run(Utils.tDecode(msg, new TimMessageList()));
                }
                break;
            case TIMOFFLINEMSG:
                if (this.offlineMsgHandler != null) {
                    this.offlineMsgHandler.run(Utils.tDecode(msg, new TimMessageList()));
                }
                break;
            case TIMOFFLINEMSGEND:
                if (this.offlinemsgEndHandler != null) {
                    this.offlinemsgEndHandler.run();
                }
                break;
            case TIMSTREAM:
                if (this.streamHandler != null) {
                    this.streamHandler.run(Utils.tDecode(msg, new TimStream()));
                }
                break;
            default:
                logger.warning("undisposed>>" + t + ",data length:" + msg.length);
        }
    }


    private String formatUrl(String ip, int port, boolean tls) {
        if (ip == null || port > 65535 || port < 0) {
            return null;
        }
        if (tls) {
            return "wss://" + ip + ":" + port;
        } else {
            return "ws://" + ip + ":" + port;
        }
    }

    public void closeAndLogout() throws TimException {
        this.isLogout = true;
        this.close();
    }

    public void close() throws TimException {
        this.v++;
        if (this.handler != null) {
            this.handler.close();
        }
    }

    protected void ping() throws TimException {
        this.handler.sendws(this.tx.ping());
    }

    public void MessageHandler(MessageHandler handler) {
        this.messageHandler = handler;
    }

    public void PresenceHandler(PresenceHandler handler) {
        this.presenceHandler = handler;
    }

    public void AckHandler(AckHandler handler) {
        this.ackHandler = handler;
    }

    public void NodesHandler(NodesHandler handler) {
        this.nodesHandler = handler;
    }

    public void PullmessageHandler(PullmessageHandler handler) {
        this.pullmessageHandler = handler;
    }

    public void OfflineMsgHandler(OfflineMsgHandler handler) {
        this.offlineMsgHandler = handler;
    }

    public void OfflinemsgEndHandler(OfflinemsgEndHandler handler) {
        this.offlinemsgEndHandler = handler;
    }

    public void StreamHandler(StreamHandler handler) {
        this.streamHandler = handler;
    }

    // register with username and password
    // domain can be set "" where is not requied；Different domains cannot communicate with each other
    // 如果不需要使用 domain（域）时，可设置为空字符串，不同域无法相互通讯
    @Override
    public TimAck Register(String username, String pwd, String domain) throws TimException {
        byte[] bs = Clihandle.sendsync(this.conf, this.tx.register(username, pwd, domain));
        return Utils.tDecode(Arrays.copyOfRange(bs, 1, bs.length), new TimAck());
    }

    @Override
    public long Token(String username, String pwd, String domain) throws TimException {
        byte[] bs = Clihandle.sendsync(this.conf, this.tx.token(username, pwd, domain));
        TimAck ta = Utils.tDecode(Arrays.copyOfRange(bs, 1, bs.length), new TimAck());
        if (ta != null && ta.ok) {
            return ta.t;
        } else {
            throw new TimException(ta.error.toString());
        }
    }

    /**
     * resource is the terminal information defined by the developer. For example, phone model: HUAWEI P50 Pro, iPhone 11 Pro
     * if resource is not required, pass ""
     * resource是开发者自定义的终端信息，一般是登录设备信息，如 HUAWEI P50 Pro，若不使用，赋空值即可
     **/
    @Override
    public void Login(String name, String pwd, String domain, String resource, byte termtyp,Map<String,String> extend) throws TimException {
        this.close();
        this.tx.loginByAccount(name, pwd, domain, resource, termtyp,extend);
        this.connect();
    }

    @Override
    public void LoginByToken(long token, String resource, byte termtyp,Map<String,String> extend) throws TimException {
        this.close();
        this.tx.loginByToken(token, resource, termtyp,extend);
        this.connect();
    }

    /**
     * 退出登录
     **/
    @Override
    public void Logout() throws TimException {
        this.closeAndLogout();
    }

    /**
     * modify password
     * 修改密码
     */
    public void Modify(String oldpwd,String newpwd)throws TimException {
        this.handler.sendws(this.tx.modify(oldpwd,newpwd));
    }

    /**
     * send message to a user
     * showType and textType is a value defined by the developer and is sent to the peer terminal as is
     * showType 和 textType 为开发者自定义字段，会原样发送到对方的终端，由开发者自定义解析，
     * If showType or textType  is not required, pass 0
     * showType 或 textType  不使用时，传默认值0
     **/
    @Override
    public void MessageToUser(String toUser, String msg, short showType, short textType, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException {
        this.handler.sendws(this.tx.message2Friend(msg, toUser, showType, textType, extend, extra));
    }

    /**
     * revoke the message 撤回信息
     * mid is message's id
     * mid and to is  required
     **/
    @Override
    public void RevokeMessage(long mid, String touser, String room, String msg, short showType, short textType) throws TimException {
        this.handler.sendws(this.tx.revokeMessage(mid, touser, room, msg, showType, textType));
    }


    /**
     * Burn After Reading  阅后即焚
     * mid is message's id
     * mid and to is  required
     **/
    @Override
    public void BurnMessage(long mid, String touser, String msg, short showType, short textType) throws TimException {
        this.handler.sendws(this.tx.burnMessage(mid, msg, touser, showType, textType));
    }

    /**
     * send message to a room
     **/
    @Override
    public void MessageToRoom(String room, String msg, short showType, short textType, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException {
        this.handler.sendws(this.tx.message2Room(msg, room, showType, textType, extend, extra));
    }

    /**
     * send a message to a room member
     **/
    @Override
    public void MessageByPrivacy(String member, String room, String msg, short showType, short textType, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException {
        this.handler.sendws(this.tx.messageByPrivacy(msg, member, room, showType, textType, extend, extra));
    }

    /**
     * send  stream data to user
     */
    @Override
    public void StreamToUser(String to, byte[] data,short udShow,short udType) throws TimException {
        this.handler.sendws(this.tx.stream(data, to, "",udShow,udType));
    }

    /**
     * send  stream data to room
     */
    @Override
    public void StreamToRoom(String to, byte[] data,short udShow,short udType) throws TimException {
        this.handler.sendws(this.tx.stream(data, "", to,udShow,udType));
    }

    /**
     * send presence to other user
     * 发送状态给其他账号
     */
    @Override
    public void PresenceToUser(String touser, short show, String status, byte subStatus, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException {
        this.handler.sendws(this.tx.presence(touser, show, status, subStatus, extend, extra));
    }

    /**
     * send presence to other user list
     */
    @Override
    public void PresenceToList(String[] toList, short show, String status, byte subStatus, Map<String, String> extend, Map<String, ByteBuffer> extra) throws TimException {
        this.handler.sendws(this.tx.presenceList(show, status, subStatus, extend, extra, toList));
    }

    /**
     * broad the presence and substatus to all the friends
     * 向所有好友广播状态和订阅状态
     */
    @Override
    public void BroadPresence(byte subStatus, short show, String status) throws TimException {
        this.handler.sendws(this.tx.broadPresence(subStatus, show, status));
    }

    /**
     * triggers tim to send user rosters
     * 触发tim服务器发送用户花名册
     */
    @Override
    public void Roster() throws TimException {
        this.handler.sendws(this.tx.roster());
    }

    /**
     * send request to  the account for add friend
     */
    @Override
    public void Addroster(String node, String msg) throws TimException {
        this.handler.sendws(this.tx.addroster(node, msg));
    }

    /**
     * remove a relationship with a specified account
     * 移除与指定账号的关系
     */
    @Override
    public void Rmroster(String node) throws TimException {
        this.handler.sendws(this.tx.rmroster(node));
    }

    /**
     * Block specified account
     * 拉黑指定账号
     */
    @Override
    public void Blockroster(String node) throws TimException {
        this.handler.sendws(this.tx.blockroster(node));
    }

    /**
     * pull message with user
     * 拉取用户聊天消息
     */
    @Override
    public void PullUserMessage(String to, long mid, long limit) throws TimException {
        this.handler.sendws(this.tx.pullmsg(1, to, mid, limit));
    }

    /**
     * pull message of group
     * 拉取群信息
     */
    @Override
    public void PullRoomMessage(String to, long mid, long limit) throws TimException {
        this.handler.sendws(this.tx.pullmsg(2, to, mid, limit));
    }

    /**
     * triggers tim to send the offlien message
     * 触发tim服务器发送离线信息
     */
    @Override
    public void OfflineMsg() throws TimException {
        this.handler.sendws(this.tx.offlinemsg());
    }

    /**
     * triggers tim to send the user's ROOM account
     * 触发tim服务器发送用户的群账号
     */
    @Override
    public void UserRoom() throws TimException {
        this.handler.sendws(this.tx.userroom());
    }

    /**
     * triggers tim to send the ROOM member account
     * 触发tim服务器发送群成员账号
     */
    @Override
    public void RoomUsers(String node) throws TimException {
        this.handler.sendws(this.tx.roomusers(node));
    }

    /**
     * creating a room, provide the room name and room type
     * 创建群，需提供群名称和群类型
     */
    @Override
    public void NewRoom(long gtype, String roomname) throws TimException {
        this.handler.sendws(this.tx.newroom(gtype, roomname));
    }

    /**
     * send a request to join the group
     * 发送一个加入群的请求
     */
    @Override
    public void AddRoom(String node, String msg) throws TimException {
        this.handler.sendws(this.tx.addroom(node, msg));
    }

    /**
     * pull a account into the group
     * 将用户拉入群
     */
    @Override
    public void PullInRoom(String roomNode, String userNode) throws TimException {
        this.handler.sendws(this.tx.pullroom(roomNode, userNode));
    }

    /**
     * reject a account to join into the group
     * 拒绝用户加入群
     */
    @Override
    public void RejectRoom(String roomNode, String userNode, String msg) throws TimException {
        this.handler.sendws(this.tx.nopassroom(roomNode, userNode, msg));
    }

    /**
     * Kick a account out of the group
     * 将用户踢出群
     */
    @Override
    public void KickRoom(String roomNode, String userNode) throws TimException {
        this.handler.sendws(this.tx.kickroom(roomNode, userNode));
    }

    /**
     * leave group
     * 退出群
     */
    @Override
    public void LeaveRoom(String roomNode) throws TimException {
        this.handler.sendws(this.tx.leaveroom(roomNode));
    }

    /**
     * Cancel a group
     * 注销群
     */
    @Override
    public void CancelRoom(String roomNode) throws TimException {
        this.handler.sendws(this.tx.cancelroom(roomNode));
    }

    /**
     * block the group
     * 拉黑群
     */
    @Override
    public void BlockRoom(String roomNode) throws TimException {
        this.handler.sendws(this.tx.blockroom(roomNode));
    }

    /**
     * block the group member or the account join into group
     * 拉黑群成员或其他账号入群
     */
    @Override
    public void BlockRoomMember(String roomNode, String toNode) throws TimException {
        this.handler.sendws(this.tx.blockroomMember(roomNode, toNode));
    }

    /**
     * blocklist of user
     * 用户黑名单
     */
    @Override
    public void BlockRosterList() throws TimException {
        this.handler.sendws(this.tx.blockrosterlist());
    }

    /**
     * blocklist of user group
     * 用户群黑名单
     */
    @Override
    public void BlockRoomList() throws TimException {
        this.handler.sendws(this.tx.blockroomlist());
    }

    /**
     * blocklist of group
     *  群黑名单
     */
    @Override
    public void BlockRoomMemberlist(String roomNode) throws TimException {
        this.handler.sendws(this.tx.blockroommemberlist(roomNode));
    }

    /**
     * creating a Virtual room
     * 创建虚拟房间
     */
    @Override
    public void VirtualroomRegister() throws TimException {
        this.handler.sendws(this.tx.virtualroom(1, null, null));
    }

    /**
     * creating a Virtual room
     * 销毁虚拟房间
     */
    @Override
    public void VirtualroomRemove(String roomNode) throws TimException {
        this.handler.sendws(this.tx.virtualroom(2, roomNode, null));
    }

    /**
     * Add push stream data permissions for virtual rooms to a account
     * 给账户添加向虚拟房间推送流数据的权限
     */
    @Override
    public void VirtualroomAddAuth(String roomNode, String tonode) throws TimException {
        this.handler.sendws(this.tx.virtualroom(3, roomNode, tonode));
    }

    /**
     * delete the push stream data permissions for virtual rooms to a account
     * 删除用户向虚拟房间推送流数据的权限
     */
    @Override
    public void VirtualroomDelAuth(String roomNode, String tonode) throws TimException {
        this.handler.sendws(this.tx.virtualroom(4, roomNode, tonode));
    }

    /**
     * Subscribe the stream data of the virtual room
     * 向虚拟房间订阅流数据
     */
    @Override
    public void VirtualroomSub(String roomNode) throws TimException {
        this.handler.sendws(this.tx.virtualroom(5, roomNode, ""));
    }

    /**
     * cancel subscribe the stream data of the virtual room
     * 取消订阅虚拟房间数据
     */
    @Override
    public void VirtualroomSubCancel(String roomNode) throws TimException {
        this.handler.sendws(this.tx.virtualroom(6, roomNode, ""));
    }

    /**
     * push the stream data to the virtual room
     * body: body is stream data
     * dtype : dtype is a data type defined by the developer and can be set to 0 if it is not required
     * 推送流数据到虚拟房间
     * body ：body是流数据
     * dtype：dtype 是开发者自定义的数据类型，若不需要，可以设置为0
     */
    @Override
    public void PushStream(String virtualroom, byte[] body, byte dtype) throws TimException {
        this.handler.sendws(this.tx.pushstream(virtualroom, body, dtype));
    }

    /**
     * get user information
     * 获取用户资料
     */
    @Override
    public void UserInfo(String... nodes) throws TimException {
        this.handler.sendws(this.tx.nodeinfo(NODEINFO_USERINFO, nodes, null, null));
    }

    /**
     * get group information
     * 获取群资料
     */
    @Override
    public void RoomInfo(String... nodes) throws TimException {
        this.handler.sendws(this.tx.nodeinfo(NODEINFO_ROOMINFO, nodes, null, null));
    }

    /**
     * modify user information
     * 修改用户资料
     */
    @Override
    public void ModifyUserInfo(TimUserBean tub) throws TimException {
        Map<String, TimUserBean> m = new HashMap<>();
        m.put("", tub);
        this.handler.sendws(this.tx.nodeinfo(NODEINFO_MODIFYUSER, null, m, null));
    }

    /**
     * modify group information
     * 修改群资料
     */
    @Override
    public void ModifyRoomInfo(String roomNode, TimRoomBean trb) throws TimException {
        Map<String, TimRoomBean> m = new HashMap<>();
        m.put(roomNode, trb);
        this.handler.sendws(this.tx.nodeinfo(NODEINFO_MODIFYROOM, null, null, m));
    }

}

class watch implements Runnable {
    Timclient timHandler;
    int v;

    public watch(int v, Timclient timHandler) {
        this.v = v;
        this.timHandler = timHandler;
    }

    @Override
    public void run() {
        while (timHandler.v == v) {
            if (timHandler.pingCount++ > 3) {
                break;
            }
            try {
                Thread.sleep(15000);
                timHandler.ping();
            } catch (Exception e) {
                break;
            }
        }
        try {
            timHandler.close();
        } catch (Exception ex) {
        }
    }
}
