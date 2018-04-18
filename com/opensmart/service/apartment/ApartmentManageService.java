package com.opensmart.service.apartment;

import java.util.List;

import com.opensmart.entity.ApartmentManage;

public interface ApartmentManageService {

	List<ApartmentManage> getAllApartmentManageData(String userId);

	List<ApartmentManage> listApartmentManageDataByLimit(ApartmentManage apartmentManage);
	
	String addApartmentManageData(ApartmentManage apartmentManage);

	ApartmentManage getApartmentManageDataByApartmentCode(String apartmentCode);
	
	boolean deleteApartmentManageData(String apartmentCode);
	
	boolean updateApartmentManageData(ApartmentManage apartmentManage);
	
	int addApartmentRoomData(String apartmentId, String roomList);

	List<String> getApartmentRoomDataByApartmentCode(String apartmentCode);

	boolean updateApartmentRoomData(String apartmentCode, String roomList);

	

	

	

	
	
	

}
