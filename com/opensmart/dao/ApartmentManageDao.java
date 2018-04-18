package com.opensmart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.opensmart.entity.ApartmentManage;

public interface ApartmentManageDao {

	List<ApartmentManage> listAllApartmentManageData(String userId);

	List<ApartmentManage> listApartmentManageDataByLimit(ApartmentManage apartmentManage);
	
	int insertApartmentManageData(ApartmentManage apartmentManage);

	ApartmentManage getApartmentManageDataByApartmentCode(String apartmentCode);
	
	int deleteApartmentManageData(String apartmentId);
	
	int updateApartmentManageData(ApartmentManage apartmentManage);
	
	int insertApartmentRoomData(@Param("apartmentId")String apartmentId, @Param("roomList")List<String> roomList);

	List<String> listApartmentRoomData(String apartmentCode);

	int deleteApartmentRoomData(String apartmentId);

	

	

	
	
	

}
