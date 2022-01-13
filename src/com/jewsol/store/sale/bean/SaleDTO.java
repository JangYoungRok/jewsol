package com.jewsol.store.sale.bean;

public class SaleDTO {
	private int saleSeq;
	private int orderSeq;
	private int saleSheetSeq;
	private int salePrice;
	private Double saleWeight;
	
	
	
	public Double getSaleWeight() {
		return saleWeight;
	}
	public void setSaleWeight(Double saleWeight) {
		this.saleWeight = saleWeight;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getSaleSeq() {
		return saleSeq;
	}
	public void setSaleSeq(int saleSeq) {
		this.saleSeq = saleSeq;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
	public int getSaleSheetSeq() {
		return saleSheetSeq;
	}
	public void setSaleSheetSeq(int saleSheetSeq) {
		this.saleSheetSeq = saleSheetSeq;
	}
	
}
