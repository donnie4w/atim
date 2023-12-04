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

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class HttpClient {
    public static Logger logger = Logger.getLogger("HttpClient");


    public static CloseableHttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).build();
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(SSLUtil.newSSLConnectionSocketFactory())
                .setDefaultRequestConfig(config)
                .build();
        return httpClient;
    }

    public static byte[] post(String uri, byte[] bs, String origin) throws TimException {
        HttpPost httpPost = new HttpPost(uri);
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new ByteArrayEntity(bs));
            httpPost.setHeaders(new Header[]{new BasicHeader("Origin", origin)});
            response = getHttpClient().execute(httpPost);
            return EntityUtils.toByteArray(response.getEntity());
        } catch (Exception e) {
            throw new TimException(e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
    }
}