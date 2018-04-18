package com.opensmart.service.user;

import java.util.Map;

import com.opensmart.entity.User;

public interface UserService {

	public String getIdentifyCode(String telNum);
	public String userRegist(Map<String, String> map);
	public User userLogin(String userName);
	
	
}
