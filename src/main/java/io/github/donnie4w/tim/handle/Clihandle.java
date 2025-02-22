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

import jakarta.websocket.*;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Clihandle extends Endpoint implements MessageHandler.Whole<byte[]> {
    private final Session session;
    private final Conf conf;
    private final AtomicLong al = new AtomicLong(0);

    public Clihandle(Conf conf) throws TimException {
        try {
            this.conf = conf;
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().configurator(new ClientEndpointConfig.Configurator() {
                public void beforeRequest(Map<String, List<String>> headers) {
                    headers.put("Origin", Collections.singletonList(conf.origin));
                }
            }).build();
            URI path = URI.create(conf.addr);
            if ("wss".equalsIgnoreCase(path.getScheme())) {
                cec.getUserProperties().put("org.apache.tomcat.websocket.SSL_CONTEXT", SSLUtil.newSSLContext());
            }
            session = container.connectToServer(this, cec, path);
            session.addMessageHandler(this);
        } catch (Exception e) {
            throw new TimException(e);
        }
    }

    public void close() throws TimException {
        try {
            session.close();
        } catch (IOException e) {
            throw new TimException(e);
        }
    }

    @Override
    public void onMessage(byte[] message) {
        conf.onMessage(message);
    }

    @Override
    public void onError(Session session, Throwable throwable) {
        conf.onError(throwable);
    }

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        conf.onOpen();
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        conf.onClose();
    }

    synchronized void sendws(byte[] bs) throws TimException {
        try {
            session.getBasicRemote().sendObject(bs);
        } catch (Exception e) {
            throw new TimException(e);
        }
    }

    synchronized static byte[] sendsync(Conf conf, byte[] bs) throws TimException {
        return HttpClient.post(conf.httpUrl, bs, conf.origin);
    }
}
