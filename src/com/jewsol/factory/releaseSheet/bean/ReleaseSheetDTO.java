package com.jewsol.factory.releaseSheet.bean;

public class ReleaseSheetDTO {
	private int releaseSheetSeq;
	private int branchSeq;
	private int releaseSheetNumber;
	private String releaseSheetDate;
	private String logtime;
	private int releaseSheetType;
	private int totalLabor;
	private int totalQty;
	private Double totalGoldWeight;
	private String releaseTime;
	private String branchName;
	private Double total14KWeight;
	private Double total18KWeight;
	private Double total10KWeight;
	
	public Double getTotal10KWeight() {
		return total10KWeight;
	}
	public void setTotal10KWeight(Double total10kWeight) {
		total10KWeight = total10kWeight;
	}
	public Double getTotal14KWeight() {
		return total14KWeight;
	}
	public void setTotal14KWeight(Double total14kWeight) {
		total14KWeight = total14kWeight;
	}
	public Double getTotal18KWeight() {
		return total18KWeight;
	}
	public void setTotal18KWeight(Double total18kWeight) {
		total18KWeight = total18kWeight;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public int getReleaseSheetSeq() {
		return releaseSheetSeq;
	}
	public void setReleaseSheetSeq(int releaseSheetSeq) {
		this.releaseSheetSeq = releaseSheetSeq;
	}
	public int getBranchSeq() {
		return branchSeq;
	}
	public void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public int getReleaseSheetNumber() {
		return releaseSheetNumber;
	}
	public void setReleaseSheetNumber(int releaseSheetNumber) {
		this.releaseSheetNumber = releaseSheetNumber;
	}
	public String getReleaseSheetDate() {
		return releaseSheetDate;
	}
	public void setReleaseSheetDate(String releaseSheetDate) {
		this.releaseSheetDate = releaseSheetDate;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public int getReleaseSheetType() {
		return releaseSheetType;
	}
	public void setReleaseSheetType(int releaseSheetType) {
		this.releaseSheetType = releaseSheetType;
	}
	public int getTotalLabor() {
		return totalLabor;
	}
	public void setTotalLabor(int totalLabor) {
		this.totalLabor = totalLabor;
	}
	public Double getTotalGoldWeight() {
		return totalGoldWeight;
	}
	public void setTotalGoldWeight(Double totalGoldWeight) {
		this.totalGoldWeight = totalGoldWeight;
	}
	
}
