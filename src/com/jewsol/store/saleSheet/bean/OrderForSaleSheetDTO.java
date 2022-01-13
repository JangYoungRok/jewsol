package com.jewsol.store.saleSheet.bean;

public class OrderForSaleSheetDTO {
	private int orderSeq;
	private int customerSeq;
	private int saleSheetSeq;
	private String orderK;
	private String storeProductName;
	private String orderOptionName;
	private String orderEtc;
	private int totalCzQty;
	private Double orderSaleWeight;
	private int orderSalePrice;
	
	public String getOrderEtc() {
		return orderEtc;
	}
	public void setOrderEtc(String orderEtc) {
		this.orderEtc = orderEtc;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public int getCustomerSeq() {
		return customerSeq;
	}
	public void setCustomerSeq(int customerSeq) {
		this.customerSeq = customerSeq;
	}
	public int getSaleSheetSeq() {
		return saleSheetSeq;
	}
	public void setSaleSheetSeq(int saleSheetSeq) {
		this.saleSheetSeq = saleSheetSeq;
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
	public String getOrderOptionName() {
		return orderOptionName;
	}
	public void setOrderOptionName(String orderOptionName) {
		this.orderOptionName = orderOptionName;
	}
	public int getTotalCzQty() {
		return totalCzQty;
	}
	public void setTotalCzQty(int totalCzQty) {
		this.totalCzQty = totalCzQty;
	}
	public Double getOrderSaleWeight() {
		return orderSaleWeight;
	}
	public void setOrderSaleWeight(Double orderSaleWeight) {
		this.orderSaleWeight = orderSaleWeight;
	}
	public int getOrderSalePrice() {
		return orderSalePrice;
	}
	public void setOrderSalePrice(int orderSalePrice) {
		this.orderSalePrice = orderSalePrice;
	}
	
	
}
