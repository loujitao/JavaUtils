package com.steve.httpClient;

import com.alibaba.fastjson.JSONObject;
import com.steve.string.StringUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
	
	public static final String CHARSET_UTF8 = "UTF-8";
	
	public static final String CONTENT_TYPE_JSON_CHARSET_UTF8 = "application/json;charset=utf-8";

	private HttpUtils() {
	}

	/**
	 * 用urlconnect  json post 传递参数
	 */
	public static String urlConnectPost(String url, HashMap<String, String> params){
		
		String returnLine = "";
		try {
			JSONObject obj=new JSONObject();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				obj.put(entry.getKey(),entry.getValue());
			}
			System.out.println("**************开始http通讯**************");
			System.out.println("**************调用的接口地址为**************");
			System.out.println(url);
			System.out.println("**************请求发送的数据为**************");
			System.out.println(obj.toString());
			URL my_url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			byte[] content = obj.toString().getBytes("utf-8");
			out.write(content, 0, content.length);
			out.flush();
			out.close(); // flush and close
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			//StringBuilder builder = new StringBuilder();
			String line = "";
			System.out.println("Contents of post request start");
			while ((line = reader.readLine()) != null) {
				// line = new String(line.getBytes(), "utf-8");
				returnLine += line;
			}
			System.out.println("Contents of post request ends");
			reader.close();
			connection.disconnect();
			System.out.println("**************返回的结果为**************");
			System.out.println(returnLine);
			System.out.println("**************http通讯结束**************");

			return returnLine;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 用urlconnect  json post 传递参数
	 */
	public static String urlPost(String url,String jsonString){
		if (url==null || StringUtil.isEmpty(url)){
			return "";
		}
		if (jsonString==null || StringUtil.isEmpty(jsonString)){
			return "";
		}
		String returnLine = "";
		try {
			System.out.println("**************开始http通讯**************");
			System.out.println("**************调用的接口地址为**************");
			System.out.println(url);
			System.out.println("**************请求发送的数据为**************");
			System.out.println(jsonString);
			URL my_url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			byte[] content = jsonString.getBytes("utf-8");
			out.write(content, 0, content.length);
			out.flush();
			out.close(); // flush and close
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			//StringBuilder builder = new StringBuilder();
			String line = "";
			System.out.println("Contents of post request start");
			while ((line = reader.readLine()) != null) {
				// line = new String(line.getBytes(), "utf-8");
				returnLine += line;
			}
			System.out.println("Contents of post request ends");
			reader.close();
			connection.disconnect();
			System.out.println("**************返回的结果为**************");
			System.out.println(returnLine);
			System.out.println("**************http通讯结束**************");

			return returnLine;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 用urlconnect  json post 传递参数
	 */
	public static String urlGet(String url,String jsonString){
		if (url==null || StringUtil.isEmpty(url)){
			return "";
		}
		if (jsonString==null || StringUtil.isEmpty(jsonString)){
			return "";
		}
		String returnLine = "";
		try {
			System.out.println("**************开始http通讯**************");
			System.out.println("**************调用的接口地址为**************");
			System.out.println(url);
			System.out.println("**************请求发送的数据为**************");
			System.out.println(jsonString);
			URL my_url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			byte[] content = jsonString.getBytes("utf-8");
			out.write(content, 0, content.length);
			out.flush();
			out.close(); // flush and close
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			//StringBuilder builder = new StringBuilder();
			String line = "";
			System.out.println("Contents of post request start");
			while ((line = reader.readLine()) != null) {
				// line = new String(line.getBytes(), "utf-8");
				returnLine += line;
			}
			System.out.println("Contents of post request ends");
			reader.close();
			connection.disconnect();
			System.out.println("**************返回的结果为**************");
			System.out.println(returnLine);
			System.out.println("**************http通讯结束**************");

			return returnLine;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	/***
	 * 只传入url和参数不需要编码
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String JsonPostInvoke(String url, Map<String, Object> params) 
			throws ClientProtocolException, IOException{  
        System.out.println("post方式提交json数据开始......");
        // 接收参数json列表  
        JSONObject jsonParam = new JSONObject();  
        // http客户端
        HttpClient httpClient = buildHttpClient(false);
        // post请求
        HttpPost method = new HttpPost(url);  
        if(null != params){
        	for(String key : params.keySet()){
        		jsonParam.put(key, params.get(key));
        	}
        	// 参数实体
        	StringEntity entity = new StringEntity(jsonParam.toString(), CHARSET_UTF8);//解决中文乱码问题    
        	entity.setContentEncoding(CHARSET_UTF8);    
            entity.setContentType(CONTENT_TYPE_JSON_CHARSET_UTF8);    
            method.setEntity(entity);  
        }
        // 执行响应操作
        HttpResponse result = null;
        String data="";//用于接收返回值
		try {
			result = httpClient.execute(method);
			data= EntityUtils.toString(result.getEntity()); 
		} catch (Exception e) {
			//data="请求错误";
			e.printStackTrace();
		}  finally{
			method.releaseConnection();
		}
        // 请求结束，返回结果  
       
        System.out.println(data);
        System.out.println("post方式提交json数据结束......");
        return data;
	} 
	
	/**
	 * 创建HttpClient
	 * 
	 * @param isMultiThread
	 * @return
	 */
	public static HttpClient buildHttpClient(boolean isMultiThread) {
		CloseableHttpClient client;
		if (isMultiThread)
			client = HttpClientBuilder.create().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
		else
			client = HttpClientBuilder.create().build();
		// 设置代理服务器地址和端口
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		return client;
	}
	
}
