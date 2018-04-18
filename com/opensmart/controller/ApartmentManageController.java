package com.opensmart.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensmart.entity.ApartmentManage;
import com.opensmart.entity.JsonResult;
import com.opensmart.service.apartment.ApartmentManageService;

@Controller
@RequestMapping("/apartmentmanageDao")
public class ApartmentManageController {

	@Resource
	ApartmentManageService apartmentManageService;
	
	@RequestMapping("/getAllApartmentManageData.do")
	@ResponseBody
	public JsonResult<List<ApartmentManage>> getAllApartmentManageData(String userId){
		List<ApartmentManage> amData=apartmentManageService.getAllApartmentManageData(userId);
		return new JsonResult<List<ApartmentManage>>(amData);
	}
	
	@RequestMapping("/addApartmentManageData.do")
	@ResponseBody
	public JsonResult<String> addApartmentManageData( ApartmentManage apartmentManage){
		String apartmentId=apartmentManageService.addApartmentManageData(apartmentManage);
		return new JsonResult<String>(apartmentId);
	}
	
	@RequestMapping("/getApartmentManageDataByApartmentCode.do")
	@ResponseBody
	public JsonResult<ApartmentManage> getApartmentManageDataByApartmentCode(String apartmentCode){
		ApartmentManage amData=apartmentManageService.getApartmentManageDataByApartmentCode(apartmentCode);
		return new JsonResult<ApartmentManage>(amData);
	}
	
	@RequestMapping("/deleteApartmentManageData.do")
	@ResponseBody
	public JsonResult<Boolean> deleteApartmentManageData(String apartmentCode){
		boolean resultData=apartmentManageService.deleteApartmentManageData(apartmentCode);
		return new JsonResult<Boolean>(resultData);
	}
	
	@RequestMapping("/updateApartmentManageData.do")
	@ResponseBody
	public JsonResult<Boolean> updateApartmentManageData(ApartmentManage apartmentManage){
		boolean resultData=apartmentManageService.updateApartmentManageData(apartmentManage);
		return new JsonResult<Boolean>(resultData);
	}
	
	@RequestMapping("/listApartmentManageDataByLimit.do")
	@ResponseBody
	public JsonResult<List<ApartmentManage>> listApartmentManageDataByLimit(ApartmentManage apartmentManage){
		List<ApartmentManage> amData=apartmentManageService.listApartmentManageDataByLimit(apartmentManage);
		System.out.println(apartmentManage);
		return new JsonResult<List<ApartmentManage>>(amData);
	}
	
	
	@RequestMapping("/addApartmentRoomData.do")
	@ResponseBody
	public JsonResult<Integer> addApartmentRoomData(String resultData,String roomList){
		int isSuccess=apartmentManageService.addApartmentRoomData(resultData, roomList);
		return new JsonResult<Integer>(isSuccess);
	}
	
	@RequestMapping("/getApartmentRoomDataByApartmentCode.do")
	@ResponseBody
	public JsonResult<List<String>> getApartmentRoomDataByApartmentCode(String apartmentCode){
		List<String> roomList=apartmentManageService.getApartmentRoomDataByApartmentCode(apartmentCode);
		return new JsonResult<List<String>>(roomList);
	}
	
	@RequestMapping("/updateApartmentRoomData.do")
	@ResponseBody
	public JsonResult<Boolean> updateApartmentRoomData(String apartmentCode,String roomList){
		boolean returnData=apartmentManageService.updateApartmentRoomData(apartmentCode,roomList);
		return new JsonResult<Boolean>(returnData);
	}
	
	
	
	
}
