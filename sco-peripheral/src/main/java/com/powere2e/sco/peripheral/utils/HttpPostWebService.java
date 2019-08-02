package com.powere2e.sco.peripheral.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

/**
 * HttpPost调用webservice工具类
 * 
 * @author lipengjie
 * @since 2016.4.25
 * @version 1.0
 */
public class HttpPostWebService {
	public static String invoke(String url, String requestXML) throws Exception {
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		HttpEntity re = new StringEntity(requestXML,"UTF-8");
		post.setEntity(re);
		HttpResponse response = client.execute(post);
        StringBuffer result = new StringBuffer();
        try {
    		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
            String line = null;
            while((line = reader.readLine()) != null){
                result.append(line);
            }
        } catch (SocketTimeoutException e) {
        	PeripheralFileUtils.logger.info("连接超时[" + url + "]");
            throw e;
        } catch (java.net.ConnectException e) {
        	PeripheralFileUtils.logger.info("连接失败[" + url + "]");
            throw e;
        } catch (Exception e) {
        	PeripheralFileUtils.logger.info("连接时出现异常[" + url + "]");
            throw e;
        }
        return result.toString();
    }
}
