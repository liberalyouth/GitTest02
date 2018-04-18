package com.opensmart.service.meter.fd;

import com.alibaba.fastjson.JSONObject;

public interface PowerBeeAmmeterService {

	public JSONObject getAmmeterUserLogin(String userid,String pass);
	public JSONObject refreshAmmeterToken(String token,String uuid);
	
	public JSONObject getAmmeterData(String token,String uuid,String devid);
	
	
	public JSONObject getAmmeterBindCode(String token,String uuid,String nid,String cid,String castcode,String pid,String vid);
	public JSONObject getAmmeterUnbindCode(String token,String uuid,String pid);
	
	public JSONObject changeAmmeterPaymode(String token,String uuid,String devid,String value);
	public JSONObject getAmmeterSwitchoff(String token,String uuid,String devid);
	public JSONObject getAmmeterSwitchon(String token,String uuid,String devid);
	public JSONObject changeAmmeterReportInterval(String token,String uuid,String devid,String value);
	
	public JSONObject getAmmeterReportData(String token,String uuid,String devid,String bdate,String edate);
	public JSONObject getAmmeterAccountBalance(String token,String uuid);
	public JSONObject getAmmeterAccountRecord(String token,String uuid);
	public JSONObject getAmmeterApplyAccount(String token,String uuid,String paytype,String accountid,String accountname,String accountbank);
	public JSONObject getAmmeterConfirmAccount(String token,String uuid,String aid);
	
	
	public JSONObject getAmmeterUserRechargeRecord(String token,String uuid,String bdate,String edate);
	public JSONObject getAmmeterDevidRechargeRecord(String token,String uuid,String devid,String bdate,String edate);
	
	public JSONObject getAmmeterElectricRecharge(String token,String uuid,String devid,String value);
	public JSONObject getAmmeterCashRecharge(String token,String uuid,String devid,String value);
	
	
}
