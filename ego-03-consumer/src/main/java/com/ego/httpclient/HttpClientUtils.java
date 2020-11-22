package com.ego.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuweiwei
 * @since 2020-11-22
 */
public class HttpClientUtils {
    public static void main(String[] args) throws IOException {
        // 设置参数，NameValuePair参数实体类，所有参数的内容全部放入到这个类中，一个参数是一个对象
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name", "林一明"));
        list.add(new BasicNameValuePair("age", "28"));
        list.add(new BasicNameValuePair("phone", "15820774006"));

        // 负责发起请求及解析响应
        CloseableHttpClient client = HttpClients.createDefault();
        // 设置访问的URL地址
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/test");
        // 把参数设置到HttpPost中
        HttpEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
        post.setEntity(entity);
        // 执行发送
        CloseableHttpResponse response = client.execute(post);
        // 获取响应体
        response.getEntity();
        // 工具类把响应实体中内容转换为字符串
        String body = EntityUtils.toString(response.getEntity());
        System.out.println(body);
    }
}
