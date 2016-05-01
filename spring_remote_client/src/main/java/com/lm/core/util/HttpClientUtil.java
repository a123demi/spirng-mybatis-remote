package com.lm.core.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientUtil implements Runnable {
    /**
     * 不能在主线程中访问网络 所以这里另新建了一个实现了Runnable接口的Http访问类
     */
    private String name;
    private String password;
    private String path;

    public HttpClientUtil(String name, String password,String path) {
        // 初始化用户名和密码
        this.name = name;
        this.password = password;
        this.path = path;
    }

    @Override
    public void run() {
        // 设置访问的Web站点
        // 设置Http请求参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("password", password);

        String result = sendHttpClientPost(path, params, "utf-8");
        // 把返回的接口输出
        System.out.println(result);
    }

    /**
     * 发送Http请求到Web站点
     * 
     * @param path
     *            Web站点请求地址
     * @param map
     *            Http请求参数
     * @param encode
     *            编码格式
     * @return Web站点响应的字符串
     */
    private String sendHttpClientPost(String path, Map<String, String> map,
            String encode) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                // 解析Map传递的参数，使用一个键值对对象BasicNameValuePair保存。
                list.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }
        HttpPost httpPost = null;
        CloseableHttpClient client = null;
        InputStream inputStream = null;
        
        try {
            // 实现将请求 的参数封装封装到HttpEntity中。
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encode);
            // 使用HttpPost请求方式
            httpPost = new HttpPost(path);
            // 设置请求参数到Form中。
            httpPost.setEntity(entity);
            // 实例化一个默认的Http客户端
            client = HttpClients.createDefault();
//            HttpClient client = new DefaultHttpClient();
            // 执行请求，并获得响应数据
            CloseableHttpResponse  httpResponse = client.execute(httpPost);
            // 判断是否请求成功，为200时表示成功，其他均问有问题。
            System.err.println("status:"+httpResponse.getStatusLine().getStatusCode());
            try{
	            if (httpResponse.getStatusLine().getStatusCode() == 200) {
	                // 通过HttpEntity获得响应流
	                inputStream = httpResponse.getEntity().getContent();
	                return changeInputStream(inputStream, encode);
	            }
            }finally{
            	if( null != httpResponse){
            		httpResponse.close();
            	}
            	
            	if(null != inputStream){
            		inputStream.close();
            	}
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	if(null != client){
        		try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        return "";
    }

    /**
     * 把Web站点返回的响应流转换为字符串格式
     * 
     * @param inputStream
     *            响应流
     * @param encode
     *            编码格式
     * @return 转换后的字符串
     */
    private String changeInputStream(InputStream inputStream, String encode) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String result = "";
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, len);
                }
                result = new String(outputStream.toByteArray(), encode);

            } catch (IOException e) {
                e.printStackTrace();
            }finally{
            	if(outputStream != null){
            		try {
						outputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            }
        }
        return result;
    }

}
