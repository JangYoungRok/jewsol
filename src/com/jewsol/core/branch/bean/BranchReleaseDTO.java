package com.jewsol.core.branch.bean;

public class BranchReleaseDTO {
	private int branchReleaseSeq;
	private int releaseSheetSeq;
	private Double releaseGold;
	private int releaseLabor;
	private String logtime;
	public int getBranchReleaseSeq() {
		return branchReleaseSeq;
	}
	public void setBranchReleaseSeq(int branchReleaseSeq) {
		this.branchReleaseSeq = branchReleaseSeq;
	}
	public int getReleaseSheetSeq() {
		return releaseSheetSeq;
	}
	public void setReleaseSheetSeq(int releaseSheetSeq) {
		this.releaseSheetSeq = releaseSheetSeq;
	}
	public Double getReleaseGold() {
		return releaseGold;
	}
	public void setReleaseGold(Double releaseGold) {
		this.releaseGold = releaseGold;
	}
	public int getReleaseLabor() {
		return releaseLabor;
	}
	public void setReleaseLabor(int releaseLabor) {
		this.releaseLabor = releaseLabor;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
	
}
