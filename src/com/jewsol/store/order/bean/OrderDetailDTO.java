package com.jewsol.store.order.bean;

public class OrderDetailDTO {
	private int orderSeq;
	private int kSeq;
	private int plateSeq;
	private String orderK;
	private String orderMainColor;
	private String orderSubColor;
	private String orderSize;
	private String orderEtc;
	private String orderHurry;
	private String orderHalf;
	private String orderCheckPrice;
	private int orderState;
	
	public String getOrderHalf() {
		return orderHalf;
	}
	public void setOrderHalf(String orderHalf) {
		this.orderHalf = orderHalf;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public int getkSeq() {
		return kSeq;
	}
	public void setkSeq(int kSeq) {
		this.kSeq = kSeq;
	}
	public int getPlateSeq() {
		return plateSeq;
	}
	public void setPlateSeq(int plateSeq) {
		this.plateSeq = plateSeq;
	}
	public String getOrderK() {
		return orderK;
	}
	public void setOrderK(String orderK) {
		this.orderK = orderK;
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
	public String getOrderSize() {
		return orderSize;
	}
	public void setOrderSize(String orderSize) {
		this.orderSize = orderSize;
	}
	public String getOrderEtc() {
		return orderEtc;
	}
	public void setOrderEtc(String orderEtc) {
		this.orderEtc = orderEtc;
	}
	public String getOrderHurry() {
		return orderHurry;
	}
	public void setOrderHurry(String orderHurry) {
		this.orderHurry = orderHurry;
	}
	public String getOrderCheckPrice() {
		return orderCheckPrice;
	}
	public void setOrderCheckPrice(String orderCheckPrice) {
		this.orderCheckPrice = orderCheckPrice;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	
	
}
