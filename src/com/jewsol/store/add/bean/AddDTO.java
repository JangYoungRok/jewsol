package com.jewsol.store.add.bean;

public class AddDTO {
	private int addSeq;
	private int branchSeq;
	private String addName;
	private int addPrice;
	private int addCost;
	private int addLabor;
	
	public int getBranchSeq() {
		return branchSeq;
	}
	public void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	private String addInUse;
	
	public String getAddInUse() {
		return addInUse;
	}
	public void setAddInUse(String addInUse) {
		this.addInUse = addInUse;
	}
	public int getAddSeq() {
		return addSeq;
	}
	public void setAddSeq(int addSeq) {
		this.addSeq = addSeq;
	}
	public String getAddName() {
		return addName;
	}
	public void setAddName(String addName) {
		this.addName = addName;
	}
	public int getAddPrice() {
		return addPrice;
	}
	public void setAddPrice(int addPrice) {
		this.addPrice = addPrice;
	}
	public int getAddCost() {
		return addCost;
	}
	public void setAddCost(int addCost) {
		this.addCost = addCost;
	}
	public int getAddLabor() {
		return addLabor;
	}
	public void setAddLabor(int addLabor) {
		this.addLabor = addLabor;
	}
	
	

}
