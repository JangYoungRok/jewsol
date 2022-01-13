package com.jewsol.store.order.bean;

public class ViewOrderDTO {
	private int orderSeq;
	private String orderK;
	private String customerName;
	private String storeProductName;
	private String orderSize;
	private String orderMainColor;
	private String orderSubColor;
	private String orderOptionName;
	private String orderEtc;
	private int originalSheetSeq;
	private String orderOptionPartName;
	private String orderHurry;
	private String orderOptionAddName;
	
	public String getOrderOptionPartName() {
		return orderOptionPartName;
	}
	public void setOrderOptionPartName(String orderOptionPartName) {
		this.orderOptionPartName = orderOptionPartName;
	}
	public String getOrderHurry() {
		return orderHurry;
	}
	public void setOrderHurry(String orderHurry) {
		this.orderHurry = orderHurry;
	}
	public String getOrderOptionAddName() {
		return orderOptionAddName;
	}
	public void setOrderOptionAddName(String orderOptionAddName) {
		this.orderOptionAddName = orderOptionAddName;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public String getOrderK() {
		return orderK;
	}
	public void setOrderK(String orderK) {
		this.orderK = orderK;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public int getOriginalSheetSeq() {
		return originalSheetSeq;
	}
	public void setOriginalSheetSeq(int originalSheetSeq) {
		this.originalSheetSeq = originalSheetSeq;
	}
	
	
}
