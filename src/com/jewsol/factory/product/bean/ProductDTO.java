package com.jewsol.factory.product.bean;

public class ProductDTO {
	private int productSeq;
	private int categorySeq;
	private int typeSeq;
	private String productCode;
	private String productName;
	private Double productAveWeight;
	private String productImage;
	private String productInUse;
	
	public String getProductInUse() {
		return productInUse;
	}
	public void setProductInUse(String productInUse) {
		this.productInUse = productInUse;
	}
	public int getProductSeq() {
		return productSeq;
	}
	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}
	public int getCategorySeq() {
		return categorySeq;
	}
	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}
	public int getTypeSeq() {
		return typeSeq;
	}
	public void setTypeSeq(int typeSeq) {
		this.typeSeq = typeSeq;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductAveWeight() {
		return productAveWeight;
	}
	public void setProductAveWeight(Double productAveWeight) {
		this.productAveWeight = productAveWeight;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
	
	

}
