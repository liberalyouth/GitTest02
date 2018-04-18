package com.opensmart.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public final class HttpUtils {

	private static final CloseableHttpClient httpClient;
	public static final String CHARSET = "UTF-8";

	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}
	
	public static JSONObject doPost(String url, Map<String,String> header, JSONObject jsonParams) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(jsonParams.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);

			Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
			while (iterator.hasNext()){
				Map.Entry<String, String> next = iterator.next();
				httpPost.setHeader(next.getKey(),next.getValue());
			}

			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			String resData = EntityUtils.toString(response.getEntity());
			JSONObject resJson = JSON.parseObject(resData);
			response.close();
			return resJson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject doget(String url,Map<String ,String> header){
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			if(header !=null && !header.isEmpty()){
	            List<NameValuePair> pairs = new ArrayList<NameValuePair>(header.size());  
	            for (String key :header.keySet()){
	                pairs.add(new BasicNameValuePair(key, header.get(key).toString()));  
	            }  
	            url +="?"+EntityUtils.toString(new UrlEncodedFormEntity(pairs), CHARSET);  
	        }
			System.out.println(url);
			
			
			HttpGet httpGet=new HttpGet(url);
			
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			String resData = EntityUtils.toString(httpResponse.getEntity());
			JSONObject resJson = JSON.parseObject(resData);
			httpResponse.close();
			return resJson;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static JSONObject doput(String url,Map<String,String> header){
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			HttpPut httpPut=new HttpPut(url);
			Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
			while (iterator.hasNext()){
				Map.Entry<String, String> next = iterator.next();
				httpPut.setHeader(next.getKey(),next.getValue());
			}
			CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPut.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			String resData = EntityUtils.toString(httpResponse.getEntity());
			JSONObject resJson = JSON.parseObject(resData);
			httpResponse.close();
			return resJson;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static void main(String[] args) {
		long l=System.currentTimeMillis();
		String url="http://ops.huohetech.com:80/pwd/dynamic_pwd";
		Map<String,String> map=new HashMap<String,String>();
		map.put("version", "1.0");
		map.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		map.put("access_token", "5994c12463f3bf9ab278206dc5400a6519538b1d17c57e97eab5b6375fde1239");
		JSONObject obj=JSONObject.parseObject("{}");
		obj.put("lock_no", "11.135.2.137");
		JSONObject o=doPost(url, map, obj);
		System.out.println(System.currentTimeMillis()-l);
		System.out.println(o);
	}
	
	
}
