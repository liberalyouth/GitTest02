package com.opensmart.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {
     private static final String salt="我的小鱼你醒了";
     public static String crypt(String pwd){
		return DigestUtils.md5Hex(pwd+salt);	 
     }
}
