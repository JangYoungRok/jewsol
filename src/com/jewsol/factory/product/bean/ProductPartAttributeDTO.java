package com.jewsol.factory.product.bean;

public class ProductPartAttributeDTO {
	private int productSeq;
	private int partAttributeSeq;
	private int productPartAttributeSeq;
	private String partAttribute;
	
	
	public String getPartAttribute() {
		return partAttribute;
	}
	public void setPartAttribute(String partAttribute) {
		this.partAttribute = partAttribute;
	}
	public int getProductSeq() {
		return productSeq;
	}
	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}
	public int getPartAttributeSeq() {
		return partAttributeSeq;
	}
	public void setPartAttributeSeq(int partAttributeSeq) {
		this.partAttributeSeq = partAttributeSeq;
	}
	public int getProductPartAttributeSeq() {
		return productPartAttributeSeq;
	}
	public void setProductPartAttributeSeq(int productPartAttributeSeq) {
		this.productPartAttributeSeq = productPartAttributeSeq;
	}

}
