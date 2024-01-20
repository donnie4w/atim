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
