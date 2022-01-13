package com.jewsol.factory.product.bean;

public class ProductStoneSizeDTO {
	private int productSeq;
	private int stoneSizeSeq;
	private int stoneQty;
	private int productStoneSizeSeq;
	private String stoneSize;

	public final String getStoneSize() {
		return stoneSize;
	}
	public final void setStoneSize(String stoneSize) {
		this.stoneSize = stoneSize;
	}
	public int getProductStoneSizeSeq() {
		return productStoneSizeSeq;
	}
	public void setProductStoneSizeSeq(int productStoneSizeSeq) {
		this.productStoneSizeSeq = productStoneSizeSeq;
	}
	public int getProductSeq() {
		return productSeq;
	}
	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}
	public int getStoneSizeSeq() {
		return stoneSizeSeq;
	}
	public void setStoneSizeSeq(int stoneSizeSeq) {
		this.stoneSizeSeq = stoneSizeSeq;
	}
	public int getStoneQty() {
		return stoneQty;
	}
	public void setStoneQty(int stoneQty) {
		this.stoneQty = stoneQty;
	}
	
	
	

}
