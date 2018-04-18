package com.opensmart.dao;

import java.util.List;
import java.util.Map;

import com.opensmart.entity.User;
import com.opensmart.entity.UserFunction;

public interface UserDao {
	
	User getUserDataUserName(String userName);
	
	List<Map<String,Object>> listUser();

	void insertUserDataIntoTable(Map<String, String> map);

	UserFunction getUserFunction(String userName, int function);
}
