package com.opensmart.service.lock.gj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensmart.dao.UserDao;
import com.opensmart.entity.UserFunction;
import com.opensmart.utils.DESEncrypt;
import com.opensmart.utils.HttpUtils;

@Service("lockGJService")
public class LockGJServiceImpl implements LockGJService {

	@Autowired
	UserDao userDao;
	
	/**
	 * 果加管理员登陆
	 */
	@Override
	public String getGJLockUserLogin(String userPhoneNum) {
		UserFunction userFunction = userDao.getUserFunction(userPhoneNum,1);
		if(userFunction==null)return null;
		String url="http://ops.huohetech.com:80/login";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("account", userFunction.getUser_account());
		jsonParams.put("password", userFunction.getPassword());
		JSONObject json=HttpUtils.doPost(url, header, jsonParams);
		if("成功".equals(json.get("rlt_msg"))){
			String returnData=json.getJSONObject("data").getString("access_token");
			return returnData;
		}
		return null;
	}
	
	@Override
	public List<JSONObject> listGJLockCodeMessage(String access_token) {
		String url="http://ops.huohetech.com:80/lock/list";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		JSONObject jsonParams=JSON.parseObject("{}");
		JSONObject jsonNodeListData=HttpUtils.doPost(url, header, jsonParams);
		JSONObject objData=jsonNodeListData.getJSONObject("data");
		JSONArray arrRows=objData.getJSONArray("rows");
		List<JSONObject> returnList=new ArrayList<JSONObject>();
		for(int i=0;i<arrRows.size();i++){
			JSONObject jsonLockMsgData=getLockMessage(access_token,arrRows.getJSONObject(i).getString("lock_no")).getJSONObject("data");
			String lock_kind=jsonLockMsgData.getString("lock_kind");
			switch(lock_kind){
			case "0":jsonLockMsgData.put("lock_kind", "一代协议 433门锁");break;
			case "1":jsonLockMsgData.put("lock_kind", "蓝牙门锁");break;
			case "3":jsonLockMsgData.put("lock_kind", "二代协议 433门锁");break;
		}
			
			long installTime=jsonLockMsgData.getLong("install_time");
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
			jsonLockMsgData.put("install_time", sdf.format(installTime));
			returnList.add(jsonLockMsgData);
			String node_comu_status=jsonLockMsgData.getString("node_comu_status");
			switch(node_comu_status){
				case "00":jsonLockMsgData.put("node_comu_status", "正常通信");break;
				case "01":jsonLockMsgData.put("node_comu_status", "异常通信");break;
			}
			
			
		}
		return returnList;
	}
	

	/**
	 * 查看门锁列表
	 */
	@Override
	public JSONObject getLockDataList(String access_token) {
		String url="http://ops.huohetech.com:80/lock/list";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("page_size", 10);
		JSONObject returnData=HttpUtils.doPost(url, header, jsonParams);
		return returnData;
	}

	/**
	 * 查询门锁详情
	 * lock_no：门锁编码
	 */
	@Override
	public JSONObject getLockMessage(String access_token ,String lock_no) {
		String url="http://ops.huohetech.com:80/lock/view";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("lock_no", lock_no);
		JSONObject returnData=HttpUtils.doPost(url, header, jsonParams);
		return returnData;
	}

	/**
	 * 查询门锁密码信息
	 * lock_no：门锁编码
	 */
	@Override
	public List<JSONObject> getLockPasswordData(String access_token ,String lock_no) {
		String url="http://ops.huohetech.com:80/pwd/list";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("lock_no", lock_no);
		JSONObject returnPasswordObject=HttpUtils.doPost(url, header, jsonParams);
		JSONArray returnPasswordArray=returnPasswordObject.getJSONArray("data");
		List<JSONObject> returnList=new ArrayList<JSONObject>();
		
		for(int i=0;i<returnPasswordArray.size();i++){
			JSONObject returnObject=returnPasswordArray.getJSONObject(i);
			String pwd_text=DESEncrypt.getInstance().decrypt(returnObject.getString("pwd_text"));
			returnObject.put("pwd_text", pwd_text);
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			
			returnObject.put("valid_time_start", sdf.format(returnObject.getLong("valid_time_start")));
			returnObject.put("valid_time_end", sdf.format(returnObject.getLong("valid_time_end")));
			
			int pwd_no=returnObject.getInteger("pwd_no");
			if(pwd_no>1&&pwd_no<29){
				returnObject.put("pwd_no", "自定义密码");
			}else if(pwd_no>31&&pwd_no<38){
				returnObject.put("pwd_no", "一次性密码");
			}else{
				returnObject.put("pwd_no", "离线失效密码");
			}
			int status=Integer.parseInt(returnObject.getString("status"));
			switch(status){
				case 01:returnObject.put("status", "启用中");break;
				case 03:returnObject.put("status", "删除中");break;
				case 11:returnObject.put("status", "已启用");break;
				case 13:returnObject.put("status", "已删除");break;
				case 21:returnObject.put("status", "启动失败");break;
				case 23:returnObject.put("status", "删除失败");break;
			}
			returnList.add(returnObject);
		}
		return returnList;
	}

	/**
	 * 获取门锁一次性密码
	 * lock_no：门锁编码
	 */
	@Override
	public JSONObject addOneTimeLockPassword(String access_token ,String lock_no ,String pwd_user_mobile) {
		String url="http://ops.huohetech.com:80/pwd/temp_pwd";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("lock_no", lock_no);
		jsonParams.put("pwd_user_mobile", pwd_user_mobile);
		JSONObject returnData=HttpUtils.doPost(url, header, jsonParams);
		return returnData;
	}


	/**
	 * 查询开锁记录
	 */
	@Override
	public List<JSONObject> getLockOpenMessage(String access_token ,String lock_no) {
		String url="http://ops.huohetech.com:80/lock/open_lock_his";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("lock_no", lock_no);
		JSONObject jsonObject=HttpUtils.doPost(url, header, jsonParams);
		JSONObject data=jsonObject.getJSONObject("data");
		JSONArray jsonRows=data.getJSONArray("rows");
		List<JSONObject> returnList=new ArrayList<JSONObject>();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		for(int i=0;i<jsonRows.size();i++){
			JSONObject object=jsonRows.getJSONObject(i);
			object.put("op_time", sdf.format(object.getLong("op_time")));
			int  op_way=Integer.parseInt(object.getString("op_way"));
			switch(op_way){
				case 0:object.put("op_way", "APP 蓝牙开门");break;
				case 1:object.put("op_way", "自定义密码开门");break;
				case 2:object.put("op_way", "一次性密码开门");break;
				case 3:object.put("op_way", "远程开门");break;
				case 4:object.put("op_way", "时效密码开门");break;
				case 5:object.put("op_way", "门禁卡开门");break;
				case 6:object.put("op_way", "物理钥匙开门");break;
			}
			returnList.add(object);
		}
		return returnList;
	}

	
	/**
	 * 新增自定义密码
	 * lock_no:门锁编码
	 * valid_time_star:密 码 有 效 期（起）
	 * valid_time_end:密 码 有 效 期（止）
	 * pwd_user_mobile:密码使用人手机号
	 */
	@Override
	public JSONObject addCoutomLockPassword(String access_token ,String lock_no ,String startTime ,String endTime ,String userPhone,String password ) {
		String url="http://ops.huohetech.com:80/pwd/add";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			long valid_time_start=sdf.parse(startTime).getTime();
			long valid_time_end=sdf.parse(endTime).getTime();
			
			JSONObject jsonParams=JSON.parseObject("{}");
			jsonParams.put("lock_no", lock_no);
			jsonParams.put("valid_time_start", valid_time_start);
			jsonParams.put("valid_time_end", valid_time_end);
			jsonParams.put("pwd_user_mobile", userPhone);
			if(password!=null){
				String pwd=DESEncrypt.getInstance().encrypt(password);
				jsonParams.put("pwd_text", pwd);
			}
			JSONObject returnData=HttpUtils.doPost(url, header, jsonParams);
			System.out.println(returnData);
			//String pwd_text=returnData.getJSONObject("data").getString("pwd_text");
			/*if(pwd_text==null)return returnData;
			String pwd_text_Dese=DESEncrypt.getInstance().decrypt(pwd_text);
			SendMsgUtil.getMessageData(userPhone, pwd_text_Dese);*/
			return returnData;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 修改自定义密码
	 * pwd_no:密码编号
	 */
	@Override
	public JSONObject updateCoutomLockPassword(String access_token ,String lock_no ,String pwd_no) {
		String url="http://ops.huohetech.com:80/pwd/update";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("lock_no", lock_no);
		jsonParams.put("pwd_no", pwd_no);
		jsonParams.put("pwd_text", "73aaf0050580f895");
		JSONObject returnData=HttpUtils.doPost(url, header, jsonParams);
		return returnData;
	}


	/**
	 * 删除密码
	 * pwd_no:密码编号
	 */
	@Override
	public JSONObject deleteLockPassword(String access_token ,String lock_no ,String pwd_no) {
		String url="http://ops.huohetech.com:80/pwd/delete";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("lock_no", lock_no);
		jsonParams.put("pwd_no", pwd_no);
		JSONObject returnData=HttpUtils.doPost(url, header, jsonParams);
		return returnData;
	}

	/**
	 * 远程开锁
	 */
	@Override
	public JSONObject remoteUnlock(String access_token ,String lock_no ,String pwd_user_mobile) {
		String url="http://ops.huohetech.com:80/lock/remote_open";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "1.0");
		header.put("s_id", "c909fb8a-2866-4e66-a95d-a23c26ba2b5c");
		header.put("access_token", access_token);
		
		JSONObject jsonParams=JSON.parseObject("{}");
		jsonParams.put("lock_no", lock_no);
		jsonParams.put("pwd_user_mobile", pwd_user_mobile);
		JSONObject returnData=HttpUtils.doPost(url, header, jsonParams);
		return returnData;
	}


}
