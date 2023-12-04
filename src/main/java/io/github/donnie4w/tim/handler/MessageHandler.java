/*
 * Copyright (c) , donnie <donnie4w@gmail.com>
 * All rights reserved.
 * https://github.com/donnie4w/tim
 * https://githuc.com/donnie4w/atim
 *
 * Use of this source code is governed by a MIT-style license that can be
 * found in the LICENSE file
 */
package io.github.donnie4w.tim.handler;

import io.github.donnie4w.tim.handle.TimException;
import io.github.donnie4w.tim.stub.TimMessage;

public interface MessageHandler {
    public void run(TimMessage tm) throws TimException;
}
