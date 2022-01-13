package com.jewsol.store.customer.bean;

public class CustomerTypeDTO {
	private int customerTypeSeq;
	private int branchSeq;
	private String customerType;
	
	public int getCustomerTypeSeq() {
		return customerTypeSeq;
	}
	public void setCustomerTypeSeq(int customerTypeSeq) {
		this.customerTypeSeq = customerTypeSeq;
	}
	public int getBranchSeq() {
		return branchSeq;
	}
	public void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
}
