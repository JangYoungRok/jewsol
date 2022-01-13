package com.jewsol.store.stockLocation.bean;

public class StockLocationDTO {
	private int stockLocationSeq;
	private int branchSeq;
	private String stockLocationName;
	private String stockLocationType;
	private int stockLocationTypeSeq;
	private String stockLocationInUse;
	
	public int getStockLocationTypeSeq() {
		return stockLocationTypeSeq;
	}
	public void setStockLocationTypeSeq(int stockLocationTypeSeq) {
		this.stockLocationTypeSeq = stockLocationTypeSeq;
	}
	public String getStockLocationInUse() {
		return stockLocationInUse;
	}
	public void setStockLocationInUse(String stockLocationInUse) {
		this.stockLocationInUse = stockLocationInUse;
	}
	public int getStockLocationSeq() {
		return stockLocationSeq;
	}
	public void setStockLocationSeq(int stockLocationSeq) {
		this.stockLocationSeq = stockLocationSeq;
	}
	public int getBranchSeq() {
		return branchSeq;
	}
	public void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public String getStockLocationName() {
		return stockLocationName;
	}
	public void setStockLocationName(String stockLocationName) {
		this.stockLocationName = stockLocationName;
	}
	public String getStockLocationType() {
		return stockLocationType;
	}
	public void setStockLocationType(String stockLocationType) {
		this.stockLocationType = stockLocationType;
	}
	
	
	

}
