package com.jewsol.store.cz.bean;

public class CzDTO {
	private int czSeq;
	private int branchSeq;
	private int czSizeSeq;
	private String czName;
	private int czLabor;
	private int czCost;
	private int czPrice;
	private String czInUse;
	private String czSize;
	
	public int getBranchSeq() {
		return branchSeq;
	}
	public void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public String getCzSize() {
		return czSize;
	}
	public void setCzSize(String czSize) {
		this.czSize = czSize;
	}
	public int getCzSeq() {
		return czSeq;
	}
	public void setCzSeq(int czSeq) {
		this.czSeq = czSeq;
	}
	public int getCzSizeSeq() {
		return czSizeSeq;
	}
	public void setCzSizeSeq(int czSizeSeq) {
		this.czSizeSeq = czSizeSeq;
	}
	public String getCzName() {
		return czName;
	}
	public void setCzName(String czName) {
		this.czName = czName;
	}
	public int getCzLabor() {
		return czLabor;
	}
	public void setCzLabor(int czLabor) {
		this.czLabor = czLabor;
	}
	public int getCzCost() {
		return czCost;
	}
	public void setCzCost(int czCost) {
		this.czCost = czCost;
	}
	public int getCzPrice() {
		return czPrice;
	}
	public void setCzPrice(int czPrice) {
		this.czPrice = czPrice;
	}
	public String getCzInUse() {
		return czInUse;
	}
	public void setCzInUse(String czInUse) {
		this.czInUse = czInUse;
	}
}
