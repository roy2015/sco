package com.powere2e.sco.peripheral.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * httpHelper： httpClient支持http/https的接口
 *
 * author: roy2010a@163.com
 *
 */
public class HttpClientHelper {
    private static Logger LOGGER = PeripheralFileUtils.logger;
    //获取token
    private static String HBXT_AUTH_URL = "https://scm.test.laiyifen.com/server/oauth/token?grant_type=client_credentials&client_id=lyfen&client_secret=password";
//    private static String HBXT_AUTH_URL = "http://10.6.4.81:8080/oauth/token?grant_type=client_credentials&client_id=lyfen&client_secret=password";
    //新建
    public static String HBXT_TASTE_CREATE_URL = "https://scm.test.laiyifen.com/server/product/openApi/v1/productTasting?" +
        "access_token=%s&app_id=SCO&intentionCode=%s";
    //修改/删除
    public static String HBXT_TASTE_UPDATE_DELETE_URL = "https://scm.test.laiyifen.com/server/product/openApi/v1/productTasting/%s?" +
            "access_token=%s&app_id=SCO";


    public static HttpClient getCustomHttpclient() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslcontext = createIgnoreVerifySSL();
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
//       CloseableHttpClient client = HttpClients.createDefault();//可以去掉了，上面的同时也支持http
        return client;
    }

    public static String getAccessToken () {
        String body = "";
        try {
            HttpClient httpClient = getCustomHttpclient();
            HttpPost httpPost = new HttpPost(HBXT_AUTH_URL);
            HttpResponse response = httpClient.execute(httpPost);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
        } catch (KeyManagementException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ClientProtocolException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return body;
    }

    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }

    public static void main(String[] args) {
        System.out.println(HttpClientHelper.getAccessToken());
    }


}
