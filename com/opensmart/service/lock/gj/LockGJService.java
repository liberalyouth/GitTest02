package com.opensmart.service.lock.gj;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface LockGJService {

	String getGJLockUserLogin(String userPhoneNum);
	
	List<JSONObject> listGJLockCodeMessage(String access_token);
	
	JSONObject getLockDataList(String access_token);
	
	JSONObject getLockMessage(String access_token ,String lock_no);
	
	List<JSONObject> getLockPasswordData(String access_token ,String lock_no);
	
	JSONObject addOneTimeLockPassword(String access_token ,String lock_no ,String pwd_user_mobile);

	List<JSONObject> getLockOpenMessage(String access_token ,String lock_no);
	
	JSONObject addCoutomLockPassword(String access_token ,String lock_no ,String startTime ,String endTime ,String userPhone,String password);
	
	JSONObject updateCoutomLockPassword(String access_token ,String lock_no ,String pwd_no);
	
	JSONObject deleteLockPassword(String access_token ,String lock_no ,String pwd_no);
	
	JSONObject remoteUnlock(String access_token ,String lock_no ,String pwd_user_mobile);

	
	
}
