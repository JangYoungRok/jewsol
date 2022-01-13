package com.jewsol.store.order.bean;

public class OrderWeightDTO {
	private int orderSeq;
	private float orderWeightTotal;
	private float orderWeightCz;
	private float orderWeightStone;
	private float orderWeight;
	private Double orderSaleWeight;
	
	public Double getOrderSaleWeight() {
		return orderSaleWeight;
	}
	public void setOrderSaleWeight(Double orderSaleWeight) {
		this.orderSaleWeight = orderSaleWeight;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public float getOrderWeightTotal() {
		return orderWeightTotal;
	}
	public void setOrderWeightTotal(float orderWeightTotal) {
		this.orderWeightTotal = orderWeightTotal;
	}
	public float getOrderWeightCz() {
		return orderWeightCz;
	}
	public void setOrderWeightCz(float orderWeightCz) {
		this.orderWeightCz = orderWeightCz;
	}
	public float getOrderWeightStone() {
		return orderWeightStone;
	}
	public void setOrderWeightStone(float orderWeightStone) {
		this.orderWeightStone = orderWeightStone;
	}
	public float getOrderWeight() {
		return orderWeight;
	}
	public void setOrderWeight(float orderWeight) {
		this.orderWeight = orderWeight;
	}
	
	
}
