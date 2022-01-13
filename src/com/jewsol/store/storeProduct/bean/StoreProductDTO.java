package com.jewsol.store.storeProduct.bean;

public class StoreProductDTO {
	
	private int storeProductSeq;
	private int branchSeq;
	private int supplierSeq;
	private int productSeq;
	private int categorySeq;
	private int typeSeq;
	private String storeProductCode;
	private String storeProductName;
	private String storeProductOriginalName;
	private String storeProductImage;
	private Double storeProductAveWeight;
	private int storeProductPrice;
	private int storeProductLabor;
	private String storeProductInUse;

	public int getStoreProductLabor() {
		return storeProductLabor;
	}
	public void setStoreProductLabor(int storeProductLabor) {
		this.storeProductLabor = storeProductLabor;
	}
	public final String getStoreProductOriginalName() {
		return storeProductOriginalName;
	}
	public final void setStoreProductOriginalName(String storeProductOriginalName) {
		this.storeProductOriginalName = storeProductOriginalName;
	}
	public final int getStoreProductSeq() {
		return storeProductSeq;
	}
	public final void setStoreProductSeq(int storeProductSeq) {
		this.storeProductSeq = storeProductSeq;
	}
	public final int getBranchSeq() {
		return branchSeq;
	}
	public final void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public final int getSupplierSeq() {
		return supplierSeq;
	}
	public final void setSupplierSeq(int supplierSeq) {
		this.supplierSeq = supplierSeq;
	}
	public final int getProductSeq() {
		return productSeq;
	}
	public final void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}
	public final int getCategorySeq() {
		return categorySeq;
	}
	public final void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}
	public final int getTypeSeq() {
		return typeSeq;
	}
	public final void setTypeSeq(int typeSeq) {
		this.typeSeq = typeSeq;
	}
	public final String getStoreProductCode() {
		return storeProductCode;
	}
	public final void setStoreProductCode(String storeProductCode) {
		this.storeProductCode = storeProductCode;
	}
	public final String getStoreProductName() {
		return storeProductName;
	}
	public final void setStoreProductName(String storeProductName) {
		this.storeProductName = storeProductName;
	}
	public final String getStoreProductImage() {
		return storeProductImage;
	}
	public final void setStoreProductImage(String storeProductImage) {
		this.storeProductImage = storeProductImage;
	}
	public final Double getStoreProductAveWeight() {
		return storeProductAveWeight;
	}
	public final void setStoreProductAveWeight(Double storeProductAveWeight) {
		this.storeProductAveWeight = storeProductAveWeight;
	}
	public final int getStoreProductPrice() {
		return storeProductPrice;
	}
	public final void setStoreProductPrice(int storeProductPrice) {
		this.storeProductPrice = storeProductPrice;
	}
	public final String getStoreProductInUse() {
		return storeProductInUse;
	}
	public final void setStoreProductInUse(String storeProductInUse) {
		this.storeProductInUse = storeProductInUse;
	}
}
