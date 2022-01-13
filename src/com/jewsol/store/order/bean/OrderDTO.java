package com.jewsol.store.order.bean;

public class OrderDTO {
	private int orderSeq;
	private int originalSheetSeq;
	private int customerSeq;
	private int storeProductSeq;
	private int orderMemberSeq;
	private int barcodeSeq;
	private int panelSeq;
	private int releaseSheetSeq;
	private int orderNumber;
	private String logtimel;
	
	public String getLogtimel() {
		return logtimel;
	}
	public void setLogtimel(String logtimel) {
		this.logtimel = logtimel;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public int getOriginalSheetSeq() {
		return originalSheetSeq;
	}
	public void setOriginalSheetSeq(int originalSheetSeq) {
		this.originalSheetSeq = originalSheetSeq;
	}
	public int getCustomerSeq() {
		return customerSeq;
	}
	public void setCustomerSeq(int customerSeq) {
		this.customerSeq = customerSeq;
	}
	public int getStoreProductSeq() {
		return storeProductSeq;
	}
	public void setStoreProductSeq(int storeProductSeq) {
		this.storeProductSeq = storeProductSeq;
	}
	public int getOrderMemberSeq() {
		return orderMemberSeq;
	}
	public void setOrderMemberSeq(int orderMemberSeq) {
		this.orderMemberSeq = orderMemberSeq;
	}
	public int getBarcodeSeq() {
		return barcodeSeq;
	}
	public void setBarcodeSeq(int barcodeSeq) {
		this.barcodeSeq = barcodeSeq;
	}
	public int getPanelSeq() {
		return panelSeq;
	}
	public void setPanelSeq(int panelSeq) {
		this.panelSeq = panelSeq;
	}
	public int getReleaseSheetSeq() {
		return releaseSheetSeq;
	}
	public void setReleaseSheetSeq(int releaseSheetSeq) {
		this.releaseSheetSeq = releaseSheetSeq;
	}
	
	
}
