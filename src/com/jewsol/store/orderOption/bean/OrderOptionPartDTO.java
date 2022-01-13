package com.jewsol.store.orderOption.bean;

public class OrderOptionPartDTO {
	private int orderOptionPartSeq;
	private int partSeq;
	private int orderOptionSeq;
	private String orderOptionPartName;
	private int orderOptionPartPrice;
	private int orderOptionPartLabor;

	public String getOrderOptionPartName() {
		return orderOptionPartName;
	}
	public void setOrderOptionPartName(String orderOptionPartName) {
		this.orderOptionPartName = orderOptionPartName;
	}
	public int getOrderOptionPartSeq() {
		return orderOptionPartSeq;
	}
	public void setOrderOptionPartSeq(int orderOptionPartSeq) {
		this.orderOptionPartSeq = orderOptionPartSeq;
	}
	public int getPartSeq() {
		return partSeq;
	}
	public void setPartSeq(int partSeq) {
		this.partSeq = partSeq;
	}
	public int getOrderOptionSeq() {
		return orderOptionSeq;
	}
	public void setOrderOptionSeq(int orderOptionSeq) {
		this.orderOptionSeq = orderOptionSeq;
	}
	public int getOrderOptionPartPrice() {
		return orderOptionPartPrice;
	}
	public void setOrderOptionPartPrice(int orderOptionPartPrice) {
		this.orderOptionPartPrice = orderOptionPartPrice;
	}
	public int getOrderOptionPartLabor() {
		return orderOptionPartLabor;
	}
	public void setOrderOptionPartLabor(int orderOptionPartLabor) {
		this.orderOptionPartLabor = orderOptionPartLabor;
	}
	
	
}
