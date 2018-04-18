package com.opensmart.entity;

public class ApartmentManage {

	private String apartmentId;
	private String apartmentCode;
	private String apartmentType;
	private String apartmentName;
	private String provinceAddress;
	private String cityAddress;
	private String districtAddress;
	private String detailAddress;
	private String installTime;
	private String carrierOperator;
	private String userApartmentId;
	private int floorNum;
	private int floorStart;
	private int roomsNum;
	public ApartmentManage() {
		super();
	}
	public ApartmentManage(String apartmentId, String apartmentCode,
			String apartmentType, String apartmentName, String provinceAddress,
			String cityAddress, String districtAddress, String detailAddress,
			String installTime, String carrierOperator, String userApartmentId,
			int floorNum, int floorStart, int roomsNum) {
		super();
		this.apartmentId = apartmentId;
		this.apartmentCode = apartmentCode;
		this.apartmentType = apartmentType;
		this.apartmentName = apartmentName;
		this.provinceAddress = provinceAddress;
		this.cityAddress = cityAddress;
		this.districtAddress = districtAddress;
		this.detailAddress = detailAddress;
		this.installTime = installTime;
		this.carrierOperator = carrierOperator;
		this.userApartmentId = userApartmentId;
		this.floorNum = floorNum;
		this.floorStart = floorStart;
		this.roomsNum = roomsNum;
	}
	public String getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
	}
	public String getApartmentCode() {
		return apartmentCode;
	}
	public void setApartmentCode(String apartmentCode) {
		this.apartmentCode = apartmentCode;
	}
	public String getApartmentType() {
		return apartmentType;
	}
	public void setApartmentType(String apartmentType) {
		this.apartmentType = apartmentType;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getProvinceAddress() {
		return provinceAddress;
	}
	public void setProvinceAddress(String provinceAddress) {
		this.provinceAddress = provinceAddress;
	}
	public String getCityAddress() {
		return cityAddress;
	}
	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}
	public String getDistrictAddress() {
		return districtAddress;
	}
	public void setDistrictAddress(String districtAddress) {
		this.districtAddress = districtAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getInstallTime() {
		return installTime;
	}
	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}
	public String getCarrierOperator() {
		return carrierOperator;
	}
	public void setCarrierOperator(String carrierOperator) {
		this.carrierOperator = carrierOperator;
	}
	public String getUserApartmentId() {
		return userApartmentId;
	}
	public void setUserApartmentId(String userApartmentId) {
		this.userApartmentId = userApartmentId;
	}
	public int getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}
	public int getFloorStart() {
		return floorStart;
	}
	public void setFloorStart(int floorStart) {
		this.floorStart = floorStart;
	}
	public int getRoomsNum() {
		return roomsNum;
	}
	public void setRoomsNum(int roomsNum) {
		this.roomsNum = roomsNum;
	}
	@Override
	public String toString() {
		return "ApartmentManage [apartmentId=" + apartmentId
				+ ", apartmentCode=" + apartmentCode + ", apartmentType="
				+ apartmentType + ", apartmentName=" + apartmentName
				+ ", provinceAddress=" + provinceAddress + ", cityAddress="
				+ cityAddress + ", districtAddress=" + districtAddress
				+ ", detailAddress=" + detailAddress + ", installTime="
				+ installTime + ", carrierOperator=" + carrierOperator
				+ ", userApartmentId=" + userApartmentId + ", floorNum="
				+ floorNum + ", floorStart=" + floorStart + ", roomsNum="
				+ roomsNum + "]";
	}
}
