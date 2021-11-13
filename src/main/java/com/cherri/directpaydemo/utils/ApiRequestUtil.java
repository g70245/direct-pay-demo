package com.cherri.directpaydemo.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ApiRequestUtil {

    public static String post(String jsonStr, String url, String partnerKey) throws IOException {
        /*MediaType mediaType = MediaType.get("application/json; charset=utf-8");
//        OkHttpClient client = new OkHttpClient();
        X509TrustManager manager = SSLSocketClientUtil.getX509TrustManager();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClientUtil.getSocketFactory(manager), manager)// 忽略校验
                .hostnameVerifier(SSLSocketClientUtil.getHostnameVerifier())//忽略校验
                .connectionPool(new ConnectionPool(10, 1, TimeUnit.MINUTES))
                .build();
//        OkHttpClient client = trustAllSslClient(new OkHttpClient());
        RequestBody body = RequestBody.create(jsonStr, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Api-Key", partnerKey)
                .post(body)
                .build();
        return client.newCall(request).execute().body().string();*/
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }

        /*  RestTemplate */
        RestTemplate restTemplate = restTemplate();
        //RestTemplate restTemplate = new RestTemplate();
        System.out.println(jsonStr);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", partnerKey);

        HttpEntity<String> request = new HttpEntity<>(jsonStr , headers);
        return restTemplate.postForObject(url, request, String.class);
    }

    private static RestTemplate restTemplate() {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = null;
        try {
            //sslContext = SSLContext.getInstance("TLSv1.2");
            //sslContext.init(null, null, null);

            sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        //CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLContext(sslContext).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

//        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

    public static class SSLSocketClientUtil {

        public static SSLSocketFactory getSocketFactory(TrustManager manager) {
            SSLSocketFactory socketFactory = null;
            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{manager}, new SecureRandom());
                socketFactory = sslContext.getSocketFactory();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            return socketFactory;
        }

        public static X509TrustManager getX509TrustManager() {
            return new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
        }

        public static HostnameVerifier getHostnameVerifier() {
            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            };
            return hostnameVerifier;
        }
    }
}

