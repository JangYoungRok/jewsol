package com.jewsol.store.customer.bean;

public class CustomerDTO {
	private int customerSeq;
	private int branchSeq;
	private String customerName;
	private String customerSection;
	private String customerArea;
	private int stockLocationSeq;
	private String customerInUse;
	private String stockLocationType;
	private int customerTypeSeq;
	
	public int getCustomerTypeSeq() {
		return customerTypeSeq;
	}
	public void setCustomerTypeSeq(int customerTypeSeq) {
		this.customerTypeSeq = customerTypeSeq;
	}
	public String getStockLocationType() {
		return stockLocationType;
	}
	public void setStockLocationType(String stockLocationType) {
		this.stockLocationType = stockLocationType;
	}
	public int getCustomerSeq() {
		return customerSeq;
	}
	public void setCustomerSeq(int customerSeq) {
		this.customerSeq = customerSeq;
	}
	public int getBranchSeq() {
		return branchSeq;
	}
	public void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSection() {
		return customerSection;
	}
	public void setCustomerSection(String cuctomerSection) {
		this.customerSection = cuctomerSection;
	}
	public String getCustomerArea() {
		return customerArea;
	}
	public void setCustomerArea(String customerArea) {
		this.customerArea = customerArea;
	}
	public int getStockLocationSeq() {
		return stockLocationSeq;
	}
	public void setStockLocationSeq(int stockLocationSeq) {
		this.stockLocationSeq = stockLocationSeq;
	}
	public String getCustomerInUse() {
		return customerInUse;
	}
	public void setCustomerInUse(String customerInUse) {
		this.customerInUse = customerInUse;
	}
	
	
	

}
