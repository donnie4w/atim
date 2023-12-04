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

import lombok.Data;

@Data
public abstract class Conf {
    public String addr;
    public String origin;
    public String httpUrl;

    public Conf(String addr, String auth) {
        this.addr = addr +"/tim";
        this.origin = "https://github.com/donnie4w/tim";
        this.httpUrl = parseHttpUrl();
    }

    public Conf(String addr, String origin, String auth) {
        this.addr = addr +"/tim";
        this.origin = origin;
        this.httpUrl = parseHttpUrl();
    }

    public abstract void onMessage(byte[] msg);

    public abstract void onOpen();

    public abstract void onError(Throwable throwable);

    public abstract void onClose();

    public String parseHttpUrl() {
        try {
            String[] ss = this.addr.split("//");
            String[] s = ss[1].split("/");
            String u1 = "http";
            if (ss[0].startsWith("wss:")) {
                u1 = "https";
            }
            u1 = u1 + "://" + s[0] + "/tim2";
            return u1;
        } catch (Exception e) {
            throw new TimRunTimeException("url parse error" + e.getMessage());
        }
    }
}
