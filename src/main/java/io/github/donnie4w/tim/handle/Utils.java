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

import org.apache.thrift.TSerializable;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class Utils {
    static byte[] tEncode(@NotNull TSerializable ts) throws TimException {
        try {
            TMemoryBuffer tmb = new TMemoryBuffer(0);
            ts.write(new TCompactProtocol(tmb));
            return tmb.getArray();
        } catch (Exception e) {
            throw new TimException(e);
        }
    }

    static <T extends TSerializable> T tDecode(@NotNull byte[] bs, T ts) throws TimException {
        try {
            TMemoryBuffer tmb = new TMemoryBuffer(1024);
            tmb.write(bs);
            ts.read(new TCompactProtocol(tmb));
            return ts;
        } catch (Exception e) {
            throw new TimException(e);
        }
    }

    public  static String bytesTostring(@NotNull byte[] bs) {
        return new String(bs, StandardCharsets.ISO_8859_1);
    }

    public static byte[] stringToBytes(@NotNull String s) {
        return s.getBytes(StandardCharsets.ISO_8859_1);
    }

    public static boolean isBlank(String v) {
        return null == v || "".equals(v.trim());
    }

}
