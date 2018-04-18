package com.opensmart.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) {
		JSONObject obj=new JSONObject();
		obj.put("a", 1);
		obj.put("b", 2);
		String str=obj.toString();
		System.out.println(str);
		JSONObject o=JSON.parseObject(str);
		System.out.println(o);
		
		/*System.out.println(str);
		String[] s=str.split(",");
		s[0].replace("[", "");
		s[s.length-1].replace("]", "");
		List<String> list=Arrays.asList(s);
		System.out.println(list.toString());
		
		for(int i=0;i<s.length;i++){
			System.out.println(s[i]);
		}*/
	}

}
