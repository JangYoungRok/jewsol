package com.jewsol.store.order.bean;

public class OrderPriceDTO {
	private int orderSeq;
	private int orderReleasePrice;
	private int orderSalePrice;
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
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
	
}
