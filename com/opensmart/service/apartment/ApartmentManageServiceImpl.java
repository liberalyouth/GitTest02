package com.opensmart.service.apartment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensmart.dao.ApartmentManageDao;
import com.opensmart.entity.ApartmentManage;

@Service("apartmentManageService")
public class ApartmentManageServiceImpl implements ApartmentManageService{

	@Autowired
	ApartmentManageDao  apartmentManageDao;
	
	
	@Override
	public List<ApartmentManage> getAllApartmentManageData(String userId) {
		List<ApartmentManage> apartmentData=apartmentManageDao.listAllApartmentManageData(userId);
		return apartmentData;
	}

	@Override
	public List<ApartmentManage> listApartmentManageDataByLimit(ApartmentManage apartmentManage) {
		List<ApartmentManage> apartmentData=apartmentManageDao.listApartmentManageDataByLimit(apartmentManage);
		System.out.println("jzf ： "+apartmentData.toString());
		return apartmentData;
	}
	
	
	@Override
	public String addApartmentManageData(ApartmentManage apartmentManage) {
		long now=System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		apartmentManage.setApartmentId(now+"");
		apartmentManage.setInstallTime(sdf.format(now));
		apartmentManage.setCarrierOperator("开睿科技");
		System.out.println(apartmentManage);
		int isSuccess=apartmentManageDao.insertApartmentManageData(apartmentManage);
		if(isSuccess>0)return (now+"");
		return null;
	}

	@Override
	public ApartmentManage getApartmentManageDataByApartmentCode(String apartmentCode) {
		ApartmentManage apartmentManage=apartmentManageDao.getApartmentManageDataByApartmentCode(apartmentCode);
		return apartmentManage;
	}
	
	@Override
	public boolean deleteApartmentManageData(String apartmentCode) {
		ApartmentManage am=apartmentManageDao.getApartmentManageDataByApartmentCode(apartmentCode);
		int return0=apartmentManageDao.deleteApartmentRoomData(am.getApartmentId());
		int return1=apartmentManageDao.deleteApartmentManageData(am.getApartmentId());
		if(return0>0 && return1>0)return true;
		return false;
	}
	
	@Override
	public boolean updateApartmentManageData(ApartmentManage apartmentManage) {
		int returnData=apartmentManageDao.updateApartmentManageData(apartmentManage);
		if(returnData>0)return true;
		return false;
	}
	
	@Override
	public int addApartmentRoomData(String apartmentId, String roomList) {
		List<String> rooms=new ArrayList<String>();
		JSONObject obj=JSON.parseObject(roomList);
		for(int i=1;i<obj.size()+1;i++){
			rooms.add(obj.getString(i+""));
		}
		int isSuccess=apartmentManageDao.insertApartmentRoomData(apartmentId,rooms);
		return isSuccess;
	}

	@Override
	public List<String> getApartmentRoomDataByApartmentCode(String apartmentCode) {
		List<String> roomList=apartmentManageDao.listApartmentRoomData(apartmentCode);
		return roomList;
	}

	@Override
	public boolean updateApartmentRoomData(String apartmentCode, String roomList) {
		List<String> rooms=new ArrayList<String>();
		JSONObject obj=JSON.parseObject(roomList);
		for(int i=1;i<obj.size()+1;i++){
			rooms.add(obj.getString(i+""));
		}
		ApartmentManage am=apartmentManageDao.getApartmentManageDataByApartmentCode(apartmentCode);
		
		int del=apartmentManageDao.deleteApartmentRoomData(am.getApartmentId());
		int ins=apartmentManageDao.insertApartmentRoomData(am.getApartmentId(), rooms);
		if(del>0 && ins>0)return true;
		return false;
	}


	
}
