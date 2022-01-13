package com.jewsol.store.orderOption.bean;

public class OrderOptionDTO {
	private int orderOptionSeq;
	private int orderSeq;
	private String orderOptionName;
	private int orderOptionLabor;
	private int orderOptionPrice;
	public int getOrderOptionSeq() {
		return orderOptionSeq;
	}
	public void setOrderOptionSeq(int orderOptionSeq) {
		this.orderOptionSeq = orderOptionSeq;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public String getOrderOptionName() {
		return orderOptionName;
	}
	public void setOrderOptionName(String orderOptionName) {
		this.orderOptionName = orderOptionName;
	}
	public int getOrderOptionLabor() {
		return orderOptionLabor;
	}
	public void setOrderOptionLabor(int orderOptionLabor) {
		this.orderOptionLabor = orderOptionLabor;
	}
	public int getOrderOptionPrice() {
		return orderOptionPrice;
	}
	public void setOrderOptionPrice(int orderOptionPrice) {
		this.orderOptionPrice = orderOptionPrice;
	}
}
