package com.opensmart.service.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensmart.dao.UserDao;
import com.opensmart.entity.User;
import com.opensmart.utils.SendMsgUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public String getIdentifyCode(String telNum) {
		String identifyCode=SendMsgUtil.getMessageData(telNum,null);
		return identifyCode;
	}
	
	@Override
	public String userRegist(Map<String, String> map) {
		userDao.insertUserDataIntoTable(map);
		return "ok";
	}
	
	@Override
	public User userLogin(String userName) {
		User user=userDao.getUserDataUserName(userName);
		if(user==null)return null;
		return user;
	}

	
}
