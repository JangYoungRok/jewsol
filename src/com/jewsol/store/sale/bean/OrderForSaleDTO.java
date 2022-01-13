package com.jewsol.store.sale.bean;

public class OrderForSaleDTO {
	private int orderSeq;
	private int customerSeq;
	private String orderK;
	private String storeProductName;
	private String storeProductImage;
	private String orderOptionName;
	private int totalCzQty;
	private Double orderWeight;
	private Double orderSaleWeight;
	private int orderReleasePrice;
	private int orderSalePrice;
	private int orderState;
	private String stockLocationType;
	
	public String getStockLocationType() {
		return stockLocationType;
	}
	public void setStockLocationType(String stockLocationType) {
		this.stockLocationType = stockLocationType;
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
	public String getStoreProductImage() {
		return storeProductImage;
	}
	public void setStoreProductImage(String storeProductImage) {
		this.storeProductImage = storeProductImage;
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
	public Double getOrderWeight() {
		return orderWeight;
	}
	public void setOrderWeight(Double orderWeight) {
		this.orderWeight = orderWeight;
	}
	public Double getOrderSaleWeight() {
		return orderSaleWeight;
	}
	public void setOrderSaleWeight(Double orderSaleWeight) {
		this.orderSaleWeight = orderSaleWeight;
	}
	public int getOrderReleasePrice() {
		return orderReleasePrice;
	}
	public void setOrderReleasePrice(int orderReleasePrice) {
		this.orderReleasePrice = orderReleasePrice;
	}
	public int getOrderSalePrice() {
		return orderSalePrice;
	}
	public void setOrderSalePrice(int orderSalePrice) {
		this.orderSalePrice = orderSalePrice;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	
	
}
