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
