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

    synchronized byte[] sendsync(Conf conf, byte[] bs) throws TimException {
        return HttpClient.post(conf.httpUrl, bs, conf.origin);
    }
}
