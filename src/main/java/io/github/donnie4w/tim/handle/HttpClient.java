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