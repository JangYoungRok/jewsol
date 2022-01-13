package com.jewsol.store.order.bean;

public class InqueryOrderDTO {
	private int orderSeq;
	private int customerSeq;
	private String customerSection;
	private String customerArea;
	private String customerName;
	private String orderDate;
	private String orderK;
	private String storeProductName;
	private String orderSize;
	private String orderMainColor;
	private String orderSubColor;
	private String orderOptionName;
	private String orderEtc;
	private int panelNumber;
	private int orderState;
	private String releaseSheetDate;
	private int branchSeq;
	
	public int getCustomerSeq() {
		return customerSeq;
	}
	public void setCustomerSeq(int customerSeq) {
		this.customerSeq = customerSeq;
	}
	public String getCustomerSection() {
		return customerSection;
	}
	public void setCustomerSection(String customerSection) {
		this.customerSection = customerSection;
	}
	public String getCustomerArea() {
		return customerArea;
	}
	public void setCustomerArea(String customerArea) {
		this.customerArea = customerArea;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getBranchSeq() {
		return branchSeq;
	}
	public void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderK() {
		return orderK;
	}
	public void setOrderK(String orderK) {
		this.orderK = orderK;
	}
	public String getStoreProductName() {
		return storeProductName;
	}
	public void setStoreProductName(String storeProductName) {
		this.storeProductName = storeProductName;
	}
	public String getOrderSize() {
		return orderSize;
	}
	public void setOrderSize(String orderSize) {
		this.orderSize = orderSize;
	}
	public String getOrderMainColor() {
		return orderMainColor;
	}
	public void setOrderMainColor(String orderMainColor) {
		this.orderMainColor = orderMainColor;
	}
	public String getOrderSubColor() {
		return orderSubColor;
	}
	public void setOrderSubColor(String orderSubColor) {
		this.orderSubColor = orderSubColor;
	}
	public String getOrderOptionName() {
		return orderOptionName;
	}
	public void setOrderOptionName(String orderOptionName) {
		this.orderOptionName = orderOptionName;
	}
	public String getOrderEtc() {
		return orderEtc;
	}
	public void setOrderEtc(String orderEtc) {
		this.orderEtc = orderEtc;
	}
	public int getPanelNumber() {
		return panelNumber;
	}
	public void setPanelNumber(int panelNumber) {
		this.panelNumber = panelNumber;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public String getReleaseSheetDate() {
		return releaseSheetDate;
	}
	public void setReleaseSheetDate(String releaseSheetDate) {
		this.releaseSheetDate = releaseSheetDate;
	}
}
