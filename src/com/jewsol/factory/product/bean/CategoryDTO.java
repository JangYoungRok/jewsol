package com.jewsol.factory.product.bean;

public class CategoryDTO {
	private int categorySeq;
	private String category;
	private String categoryType;
	public final String getCategoryType() {
		return categoryType;
	}
	public final void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public int getCategorySeq() {
		return categorySeq;
	}
	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
