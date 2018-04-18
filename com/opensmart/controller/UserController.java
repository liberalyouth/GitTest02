package com.opensmart.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensmart.entity.JsonResult;
import com.opensmart.entity.User;
import com.opensmart.service.user.UserService;

@Controller
@RequestMapping("/userController")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/getIdentifyCode.do")
	@ResponseBody
	public JsonResult<String> getIdentifyCode(String telNum){
		//String returnStr=userService.getIdentifyCode(telNum);
		System.out.println("123456");
		return new JsonResult<String>("123456");
	}
	
	@RequestMapping("/userRegist.do")
	@ResponseBody
	public JsonResult<String> userRegist(String campanyName,String apartName,String city,String cause,String telNumber,String password){
		Map<String,String> map=new HashMap<String,String>();
		map.put("campanyName", campanyName);
		map.put("apartName", apartName);
		map.put("city", city);
		map.put("cause", cause);
		map.put("telNumber", telNumber);
		map.put("password", password);
		String retrunStr=userService.userRegist(map);
		return new JsonResult<String>(retrunStr);
	}
	
	@RequestMapping("/userLogin.do")
	@ResponseBody
	public JsonResult<User> userLogin(String userName){
		User user=userService.userLogin(userName);
		System.out.println(userName);
		return new JsonResult<User>(user);
	}
	
	
	
	
}
