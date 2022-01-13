package com.jewsol.core.branch.bean;

public class BranchBalanceDTO {
	private int branchBalanceSeq;
	private int branchSeq;
	private int branchReleaseSeq;
	private Double balanceGold;
	private int balanceLabor;
	private String logtime;
	public int getBranchBalanceSeq() {
		return branchBalanceSeq;
	}
	public void setBranchBalanceSeq(int branchBalanceSeq) {
		this.branchBalanceSeq = branchBalanceSeq;
	}
	public int getBranchSeq() {
		return branchSeq;
	}
	public void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public int getBranchReleaseSeq() {
		return branchReleaseSeq;
	}
	public void setBranchReleaseSeq(int branchReleaseSeq) {
		this.branchReleaseSeq = branchReleaseSeq;
	}
	public Double getBalanceGold() {
		return balanceGold;
	}
	public void setBalanceGold(Double balanceGold) {
		this.balanceGold = balanceGold;
	}
	public int getBalanceLabor() {
		return balanceLabor;
	}
	public void setBalanceLabor(int balanceLabor) {
		this.balanceLabor = balanceLabor;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
	
}
