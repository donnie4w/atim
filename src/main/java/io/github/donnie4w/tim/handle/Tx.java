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
package io.github.donnie4w.tim.handle;

import io.github.donnie4w.tim.stub.*;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

import static io.github.donnie4w.tim.handle.Const.*;

public class Tx {
    TimAuth ta;

    public Tx() {
        ta = new TimAuth();
    }

    byte[] ping() {
        ByteBuffer bb = ByteBuffer.allocate(1);
        bb.put(Const.TIMPING);
        return bb.array();
    }

    // register with username password and domain
    // 使用用户名密码域名注册
    byte[] register(String username, String pwd, String domain) {
        this.ta = new TimAuth();
        ta.setName(username);
        ta.setPwd(pwd);
        ta.setDomain(domain);
        try {
            byte[] bs = Utils.tEncode(ta);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(Const.TIMREGISTER);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    // get token by username password and domain
    // 使用用户名密码域名获取token
    byte[] token(String username, String pwd, String domain) {
        this.ta = new TimAuth();
        ta.setName(username);
        ta.setPwd(pwd);
        ta.setDomain(domain);
        try {
            byte[] bs = Utils.tEncode(ta);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(Const.TIMTOKEN);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    // register with username password and domain
    // 使用用户名密码域名注册
    byte[] loginByAccount(String username, String password, String domain, String resource, byte termtyp, Map<String, String> extend) {
        ta.setName(username);
        ta.setPwd(password);
        ta.setDomain(domain);
        ta.setResource(resource);
        ta.setTermtyp(termtyp);
        ta.setExtend(extend);
        try {
            byte[] bs = Utils.tEncode(ta);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(Const.TIMAUTH);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }


    byte[] loginByToken(String username, String token, String domain, String resource, byte termtyp, Map<String, String> extend) {
        ta.setToken(token);
        ta.setResource(resource);
        ta.setTermtyp(termtyp);
        ta.setExtend(extend);
        ta.setName(username);
        ta.setDomain(domain);
        try {
            byte[] bs = Utils.tEncode(ta);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(Const.TIMAUTH);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] login() {
        try {
            byte[] bs = Utils.tEncode(ta);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(Const.TIMAUTH);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] message2Friend(String msg, String to, short udshow, short udtype, Map<String, String> extend, Map<String, ByteBuffer> extra) {
        return _message(TIMMESSAGE, (byte) 2, (byte) 1, msg, to, "", udshow, udtype, 0, extend, extra);
    }


    byte[] _message(byte timtype, byte mstype, byte odType, String msg, String to, String room, short udshow, short udtype, long msgId, Map<String, String> extend, Map<String, ByteBuffer> extra) {
        TimMessage tm = new TimMessage();
        tm.setMsType(mstype);
        tm.setOdType(odType);
        tm.setExtend(extend);
        tm.setExtra(extra);
        if (!Utils.isBlank(room)) {
            tm.setRoomTid(new Tid(room));
            if (Utils.isBlank(to)) {
                tm.setMsType((byte) 3);
            }
        }
        if (!Utils.isBlank(to)) {
            tm.setToTid(new Tid(to));
        }
        if (udshow != 0) {
            tm.setUdshow(udshow);
        }
        if (udtype != 0) {
            tm.setUdtype(udtype);
        }
        if (!Utils.isBlank(msg)) {
            tm.setDataString(msg);
        }
        if (msgId != 0) {
            tm.setMid(msgId);
        }
        try {
            byte[] bs = Utils.tEncode(tm);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(timtype);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] messageByPrivacy(String msg, String to, String room, short udshow, short udtype, Map<String, String> extend, Map<String, ByteBuffer> extra) {
        return this._message(TIMMESSAGE, (byte) 2, (byte) 1, msg, to, room, udshow, udtype, 0, extend, extra);
    }

    byte[] message2Room(String msg, String room, short udshow, short udtype, Map<String, String> extend, Map<String, ByteBuffer> extra) {
        return this._message(TIMMESSAGE, (byte) 3, (byte) 1, msg, "", room, udshow, udtype, 0, extend, extra);
    }

    byte[] revokeMessage(long msgId, String to, String room, String msg, short udshow, short udtype) {
        return this._message(TIMREVOKEMESSAGE, (byte) 2, (byte) 2, msg, to, room, udshow, udtype, msgId, null, null);
    }

    byte[] burnMessage(long msgId, String msg, String to, short udshow, short udtype) {
        return this._message(TIMBURNMESSAGE, (byte) 2, (byte) 3, msg, to, "", udshow, udtype, msgId, null, null);
    }

    byte[] stream(byte[] data, String to, String room, short udShow, short udType) {
        TimMessage tm = new TimMessage();
        if (!Utils.isBlank(room)) {
            tm.setRoomTid(new Tid(room));
            if (Utils.isBlank(to)) {
                tm.setMsType((byte) 3);
            }
        }
        if (!Utils.isBlank(to)) {
            tm.setToTid(new Tid(to));
        }
        if (data != null) {
            tm.setDataBinary(data);
        }
        if (udShow != 0) {
            tm.setUdshow(udShow);
        }
        if (udType != 0) {
            tm.setUdtype(udType);
        }
        try {
            byte[] bs = Utils.tEncode(tm);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMMESSAGE);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] _presence(byte timtype, String to, short show, String status, String[] toList, byte subStatus, Map<String, String> extend, Map<String, ByteBuffer> extra) {
        TimPresence tp = new TimPresence();
        tp.setExtend(extend);
        tp.setExtra(extra);
        if (toList != null) {
            tp.setToList(List.of(toList));
        }
        if (!Utils.isBlank(to)) {
            tp.setToTid(new Tid(to));
        }
        if (show != 0) {
            tp.setShow(show);
        }
        if (!Utils.isBlank(status)) {
            tp.setStatus(status);
        }
        if (subStatus != 0) {
            tp.setSubStatus(subStatus);
        }
        try {
            byte[] bs = Utils.tEncode(tp);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(timtype);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] presence(String to, short show, String status, byte subStatus, Map<String, String> extend, Map<String, ByteBuffer> extra) {
        return this._presence(TIMPRESENCE, to, show, status, null, subStatus, extend, extra);
    }

    byte[] presenceList(short show, String status, byte subStatus, Map<String, String> extend, Map<String, ByteBuffer> extra, String[] toList) {
        return this._presence(TIMPRESENCE, null, show, status, toList, subStatus, extend, extra);
    }

    byte[] broadPresence(byte subStatus, short show, String status) {
        return this._presence(TIMBROADPRESENCE, null, show, status, null, subStatus, null, null);
    }

    byte[] pullmsg(int rtype, String to, long mid, long limit) {
        TimReq tr = new TimReq();
        tr.setRtype(rtype);
        tr.setNode(to);
        tr.setReqInt(mid);
        tr.setReqInt2(limit);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMPULLMESSAGE);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] offlinemsg() {
        ByteBuffer bb = ByteBuffer.allocate(1);
        bb.put(TIMOFFLINEMSG);
        return bb.array();
    }

    byte[] addroster(String node, String msg) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_ADDROSTER);
        tr.setNode(node);
        tr.setReqStr(msg);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] rmroster(String node) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_REMOVEROSTER);
        tr.setNode(node);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] blockroster(String node) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_BLOCKROSTER);
        tr.setNode(node);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] roster() {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_ROSTER);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] userroom() {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_USERROOM);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] roomusers(String node) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_ROOMUSERS);
        tr.setNode(node);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] newroom(long gtype, String roomname) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_NEWROOM);
        tr.setReqInt(gtype);
        tr.setNode(roomname);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] addroom(String gnode, String msg) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_ADDROOM);
        tr.setNode(gnode);
        tr.setReqStr(msg);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] pullroom(String rnode, String unode) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_PULLROOM);
        tr.setNode(rnode);
        tr.setNode2(unode);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] nopassroom(String rnode, String unode, String msg) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_NOPASSROOM);
        tr.setNode(rnode);
        tr.setNode2(unode);
        tr.setReqStr(msg);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] kickroom(String rnode, String unode) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_KICKROOM);
        tr.setNode(rnode);
        tr.setNode2(unode);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] leaveroom(String gnode) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_LEAVEROOM);
        tr.setNode(gnode);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] cancelroom(String gnode) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_CANCELROOM);
        tr.setNode(gnode);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] blockroom(String gnode) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_BLOCKROOM);
        tr.setNode(gnode);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] blockroomMember(String gnode, String tonode) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_BLOCKROOMMEMBER);
        tr.setNode(gnode);
        tr.setNode2(tonode);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] blockrosterlist() {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_BLOCKROSTERLIST);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] blockroomlist() {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_BLOCKROOMLIST);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] blockroommemberlist(String gnode) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_BLOCKROOMMEMBERLIST);
        tr.setNode(gnode);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] modify(String oldpwd, String newpwd) {
        TimReq tr = new TimReq();
        tr.setRtype(BUSINESS_MODIFYAUTH);
        tr.setReqStr(oldpwd);
        tr.setReqStr2(newpwd);
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMBUSINESS);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] virtualroom(int rtype, String vnode, String unode, long i) {
        TimReq tr = new TimReq();
        tr.setRtype(rtype);
        if (!Utils.isBlank(vnode)) {
            tr.setNode(vnode);
        }
        if (!Utils.isBlank(unode)) {
            tr.setNode2(unode);
        }
        if (i != 0) {
            tr.reqInt = i;
        }
        try {
            byte[] bs = Utils.tEncode(tr);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMVROOM);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] pushstream(String node, byte[] body, byte dtype) {
        TimStream ts = new TimStream();
        ts.setVNode(node);
        ts.setBody(body);
        if (dtype != 0) {
            ts.setDtype(dtype);
        }
        try {
            byte[] bs = Utils.tEncode(ts);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMSTREAM);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] nodeinfo(int ntype, String[] nodelist, Map<String, TimUserBean> usermap, Map<String, TimRoomBean> roommap) {
        TimNodes tn = new TimNodes();
        if (nodelist != null) {
            tn.setNodelist(List.of(nodelist));
        }
        if (usermap != null) {
            tn.setUsermap(usermap);
        }
        if (roommap != null) {
            tn.setRoommap(roommap);
        }
        try {
            byte[] bs = Utils.tEncode(tn);
            ByteBuffer bb = ByteBuffer.allocate(1 + bs.length);
            bb.put(TIMNODES);
            bb.put(bs);
            return bb.array();
        } catch (TimException e) {
        }
        return null;
    }

    byte[] bigString(String node, String dataString) {
        if (Utils.isBlank(node) || Utils.isBlank(dataString)) {
            return null;
        }
        byte[] nodeBs = Utils.stringToBytes(node);
        byte[] dataStringBs = Utils.stringToBytes(dataString);
        byte[] seq = Utils.stringToBytes(SEP_STR);
        ByteBuffer bb = ByteBuffer.allocate(1 + nodeBs.length + seq.length + dataStringBs.length);
        bb.put(TIMBIGSTRING);
        bb.put(nodeBs);
        bb.put(seq);
        bb.put(dataStringBs);
        return bb.array();
    }

    byte[] bigBinary(String node, byte[] dataBinary) {
        if (Utils.isBlank(node) || dataBinary == null) {
            return null;
        }
        byte[] nodeBs = Utils.stringToBytes(node);
        ByteBuffer bb = ByteBuffer.allocate(1 + nodeBs.length + 1 + dataBinary.length);
        bb.put(TIMBIGBINARY);
        bb.put(nodeBs);
        bb.put(SEP_BIN);
        bb.put(dataBinary);
        return bb.array();
    }

}
