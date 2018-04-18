package com.opensmart.service.meter.fd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.opensmart.utils.HttpUtils;

@Service("powerBeeAmmeterService")
public class PowerBeeAmmeterServiceImpl implements PowerBeeAmmeterService {

	//用户登陆
	@Override
	public JSONObject getAmmeterUserLogin(String userid, String pass) {
		String url="http://smartammeter.zg118.com:8001/user/login/"+userid+"/"+pass;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		JSONObject jsonObject=HttpUtils.doget(url, header);
		return jsonObject;
	}

	//刷新令牌
	@Override
	public JSONObject refreshAmmeterToken(String token, String uuid) {
		String url="http://smartammeter.zg118.com:8001/user/token/refresh";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doget(url, header);
		return jsonObject;
	}

	//获取电表数据(devid=uuid)
	@Override
	public JSONObject getAmmeterData(String token, String uuid, String devid) {
		String url="http://smartammeter.zg118.com:8001/device/ammeter/"+devid;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doget(url, header);
		return jsonObject;
	}
	
	//绑定节点
	@Override
	public JSONObject getAmmeterBindCode(String token, String uuid, String nid,
			String cid, String custcode, String pid, String vid) {
		String url="http://smartammeter.zg118.com:8001/node/bind/"+nid+"/"+cid+"/"+custcode+"/"+pid+"/"+vid;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
	}

	//解绑节点
	@Override
	public JSONObject getAmmeterUnbindCode(String token, String uuid, String pid) {
		String url="http://smartammeter.zg118.com:8001/node/unbound/"+pid;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
	}
	
	//改变付款方式(value:0后付费，1预付费)
	@Override
	public JSONObject changeAmmeterPaymode(String token, String uuid,String devid, String value) {
		String url="http://smartammeter.zg118.com:8001/device/ammeter/paymode/"+devid+"/"+value;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
	}

	//设备断电
	@Override
	public JSONObject getAmmeterSwitchoff(String token, String uuid,String devid) {
		String url="http://smartammeter.zg118.com:8001/device/switchoff/"+devid;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
		
	}

	//设备通电
	@Override
	public JSONObject getAmmeterSwitchon(String token, String uuid, String devid) {
		String url="http://smartammeter.zg118.com:8001/device/switchon/"+devid;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
	}

	//设置电表上报间隔时间，单位为分钟
	@Override
	public JSONObject changeAmmeterReportInterval(String token, String uuid,String devid, String value) {
		String url="http://smartammeter.zg118.com:8001/device/ammeter/collection/"+devid+"/"+value;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
	}

	//获取电表日期段每天的用电量
	@Override
	public JSONObject getAmmeterReportData(String token, String uuid, String devid, String bdate, String edate) {
		String url="http://smartammeter.zg118.com:8001/device/ammeter/report/"+devid+"/"+bdate+"/"+edate;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doget(url, header);
		return jsonObject;
	}

	//查看账户余额
	@Override
	public JSONObject getAmmeterAccountBalance(String token, String uuid) {
		String url="http://smartammeter.zg118.com:8001/account/overage";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doget(url, header);
		return jsonObject;
	}

	//获取当前用户的提现记录
	@Override
	public JSONObject getAmmeterAccountRecord(String token, String uuid) {
		String url="http://smartammeter.zg118.com:8001/account";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doget(url, header);
		return jsonObject;
	}

	//申请提现(paytype:0银行卡，1微信，2支付宝。accountid：账号。accountname：姓名)
	@Override
	public JSONObject getAmmeterApplyAccount(String token, String uuid,
			String paytype, String accountid, String accountname,String accountbank) {
		String url="http://smartammeter.zg118.com:8001/account";
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonParams=new JSONObject();
		jsonParams.put("paytype", paytype);
		jsonParams.put("accountid", accountid);
		jsonParams.put("accountname", accountname);
		jsonParams.put("accountbank", accountbank);
		JSONObject jsonObject=HttpUtils.doPost(url, header, jsonParams);
		return jsonObject;
	}

	//确认提现
	@Override
	public JSONObject getAmmeterConfirmAccount(String token, String uuid, String aid) {
		String url="http://smartammeter.zg118.com:8001/account/confirm/"+aid;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
	}

	//用户充值记录(bdate:YYYY-MM-DD)
	@Override
	public JSONObject getAmmeterUserRechargeRecord(String token, String uuid, String bdate, String edate) {
		String url="http://smartammeter.zg118.com:8001/account/recharge/"+bdate+"/"+edate;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doget(url, header);
		return jsonObject;
	}

	//设备充值记录
	@Override
	public JSONObject getAmmeterDevidRechargeRecord(String token, String uuid,
			String devid, String bdate, String edate) {
		String url="http://smartammeter.zg118.com:8001/account/recharge/"+devid+"/"+bdate+"/"+edate;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doget(url, header);
		return jsonObject;
	}

	//充值电量
	@Override
	public JSONObject getAmmeterElectricRecharge(String token, String uuid, String devid, String value) {
		String url="http://smartammeter.zg118.com:8001/account/threshold/"+devid+"/"+value;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
	}

	//充值金额
	@Override
	public JSONObject getAmmeterCashRecharge(String token, String uuid, String devid, String value) {
		String url="http://smartammeter.zg118.com:8001/account/recharge/"+devid+"/"+value;
		Map<String,String> header=new HashMap<String,String>();
		header.put("version", "0116010101");
		header.put("token", token);
		header.put("uid", uuid);
		JSONObject jsonObject=HttpUtils.doput(url, header);
		return jsonObject;
	}

}
