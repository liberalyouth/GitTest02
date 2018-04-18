package com.opensmart.controller.lock.gj;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.opensmart.entity.JsonResult;
import com.opensmart.service.lock.gj.LockGJService;

@Controller
@RequestMapping("/gjLockController")
public class GJLockController {

	@Resource
	private LockGJService lockGJService;
	
	@RequestMapping("gjLockUserLogin.do")
	@ResponseBody
	public JsonResult<String> getGJLockUserLogin(String userPhoneNum){
		long t1=System.currentTimeMillis();
		String object=lockGJService.getGJLockUserLogin(userPhoneNum);
		long t2=System.currentTimeMillis();
		System.out.println(t2-t1);
		return new JsonResult<String>(object);
	}
	
	@RequestMapping("listGJLockCodeMessage.do")
	@ResponseBody
	public JsonResult<List<JSONObject>> listGJLockCodeMessage(String access_token){
		List<JSONObject> returnData=lockGJService.listGJLockCodeMessage(access_token);
		return new JsonResult<List<JSONObject>>(returnData);
	}
	
	@RequestMapping("getLockPasswordData.do")
	@ResponseBody
	public JsonResult<List<JSONObject>> getLockPasswordData(String access_token,String lock_no){
		List<JSONObject> returnList=lockGJService.getLockPasswordData(access_token, lock_no);
		return new JsonResult<List<JSONObject>>(returnList);
	}
	
	
	
	@RequestMapping("addCoutomLockPassword.do")
	@ResponseBody
	public JsonResult<JSONObject> addCoutomLockPassword(String access_token,String lock_no,String phoneNum,String startTime,String endTime,String password){
		JSONObject object=lockGJService.addCoutomLockPassword(access_token, lock_no, startTime, endTime, phoneNum, password);
		return new JsonResult<JSONObject>(object);
		
	}
	
	@RequestMapping("addOneTimeLockPassword.do")
	@ResponseBody
	public JsonResult<JSONObject> addOneTimeLockPassword(String access_token,String lock_no,String phoneNum){
		JSONObject object=lockGJService.addOneTimeLockPassword(access_token, lock_no, phoneNum);
		return new JsonResult<JSONObject>(object);
	}
	
	@RequestMapping("getRemoteUnlock.do")
	@ResponseBody
	public JsonResult<JSONObject> getRemoteUnlock(String access_token,String lock_no,String phoneNum){
		JSONObject object=lockGJService.remoteUnlock(access_token, lock_no, phoneNum);
		return new JsonResult<JSONObject>(object);
	}
	
	
	@RequestMapping("getLockOpenMessage.do")
	@ResponseBody
	public JsonResult<List<JSONObject>> getLockOpenMessage(String access_token,String lock_no){
		List<JSONObject> returnList=lockGJService.getLockOpenMessage(access_token, lock_no);
		return new JsonResult<List<JSONObject>>(returnList);
	}
	
	
	
}
