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

public class TimRunTimeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TimRunTimeException() {
        super();
    }

    public TimRunTimeException(String message) {
        super(message);
    }

    public TimRunTimeException(Throwable cause) {
        super(cause);
    }

    public TimRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
