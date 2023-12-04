/*
 * Copyright (c) , donnie <donnie4w@gmail.com>
 * All rights reserved.
 * https://github.com/donnie4w/tim
 * https://githuc.com/donnie4w/atim
 *
 * Use of this source code is governed by a MIT-style license that can be
 * found in the LICENSE file
 */
package io.github.donnie4w.tim.log;

import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class Log {
    private final static Logger logger = Logger.getLogger("atimlog");
    public static void debug(Object ...v){
        StringBuilder sb = new StringBuilder();
        for (Object o:v){
            sb.append(o);
        }
        logger.info(sb.toString());
    }
}
