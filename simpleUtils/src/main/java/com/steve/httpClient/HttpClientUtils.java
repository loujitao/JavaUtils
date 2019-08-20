package com.steve.httpClient;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * desc:发送HTTP请求通用类
 * @version V1.0
 */
public class HttpClientUtils {

	private static final Logger log = Logger.getLogger(HttpClientUtils.class);

	// 超时时间
	private static int TIMEOUT = 60 * 1000;
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//conn.setConnectTimeout(1000);
			//conn.setReadTimeout(1000);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} 
		return result;
	}
	public static void testNotify(){
		String param = "amount=1&com_amt=0&com_amt_type=-99&mer_date=20151117&mer_id=9389&order_id=T000000000012121&ret_code=0000&ret_msg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&trade_no=1511171017208284&trade_state=4&transfer_date=20151117&transfer_settle_date=20151117&version=4.0&sign=PwqV2Zh2OD5%2Bn0bf2KFHYVSvel9fnLHReb4bNKZnnLSacJRNhK5T9%2FecYBaEFhqmFKu352RJeAxE71jfVqYgtkJQYrSWdqdAvm35ZmiMwr59MYrAJ47HdI8eIAvDJTHFl46wCC43c1f1iCWYiL9MnFSpQvmkZU6wP7GP39LbH1M%3D&sign_type=RSA";
		String param2 = "amount=10&com_amt=0&com_amt_type=-99&mer_date=20151207&mer_id=9389&order_id=T000000000001049&ret_code=0000&ret_msg=%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F&trade_no=1512071114230405&trade_state=4&transfer_date=20151207&transfer_settle_date=20151207&version=4.0&sign=k2xrqYgh4uGXea%2FzJ%2BX%2Faex3gt80vJA0Viz%2Bpm%2F9%2FmpdQ%2F%2FXwpp%2FvgcEGO5vcuCrpKqTPIRWf%2FZExa5JboKUYCPg1aMSSQC1khkIQ9IMla0QYzgdloDKDJYJL0Xwnfw%2BSFSeVlGj406BZvW1lneUJ3GtQYrddunUqn6VZXWAwwk%3D&sign_type=RSA";
//		String param = "amount=2&mer_date=20150911&mer_id=9389&order_id=T000000000000508&ret_code=0000123&ret_msg=交易成功&trade_no=1509111434136992&trade_state=3&transfer_date=20150911&transfer_settle_date=20150911&version=4.0&sign=GaFgkYbKmag%2BqGVjWf95sCwn3SyFOWBDX1XgUd8X93W%2F7tPfmmakkfwNAAow%2FpRll5CMH72qQQj8rG2YC3u3ebqDzxv%2FmxFun8dWEOnFqcr%2FQ9zWt%2BdpoxO9crlZPACKE2Wnjwj%2FAck70FiVSiFmIhhuWqES2JuvD6%2BBLHqDq94%3D&sign_type=RSA&ret_msg=%e8%be%93%e5%85%a5%e7%9a%84%e5%8d%a1%e5%8f%b7%e6%97%a0%e6%95%88%ef%bc%8c%e8%af%b7%e7%a1%ae%e8%ae%a4%e5%90%8e%e8%be%93%e5%85%a5%5b1000014%5d";
//		String url = "http://10.10.74.43:8003/finace_plat/pm/merTestUpayNotice.pm";
//		String url = "http://10.10.38.196:8004/finace_plat/pm/merTestUpayNotice.pm";
		String url = "http://127.0.0.1:8080/finance_plat/pm/merTestUpayNotice.pm";
		String url2 = "http://127.0.0.1:8080/finance_plat/pm/merTestUpayNotice.pm";
//		String url2 = "http://10.10.38.196:8006/finace_plat/pm/upayNotice.pm";
//		String url2 = "http://10.10.74.43:8003/finace_plat/pm/upayNotice.pm";
//		String ret = HttpClientUtils.sendPost(url, param);
		String ret = HttpClientUtils.sendPost(url2, param2);
		System.out.println(ret);
		
	}
	
	//JSONObject  jasonObject = JSONObject.fromObject(result); 
//	Map<String,String> respMap = new HashMap<String, String>();
	//respMap = toHashMap(jasonObject);
	private static HashMap<String, String> toHashMap(Object object)  
	   {  
	       HashMap<String, String> data = new HashMap<String, String>();  
	       // 将json字符串转换成jsonObject  
	       JSONObject jsonObject = JSONObject.parseObject(object.toString());
	        Set<String> it = jsonObject.keySet();
	       // 遍历jsonObject数据，添加到Map对象  
		   for (String key: it) {
			   String value = (String) jsonObject.get(key);
			   data.put(key, value);
		   }
	       return data;
	   }  
}
