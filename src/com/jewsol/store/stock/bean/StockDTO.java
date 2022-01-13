package com.jewsol.store.stock.bean;

public class StockDTO {
	private int stockSeq;
	private int stockLocationSeq;
	private int orderSeq;
	private String toStockDate;
	private String logtime;
	
	public String getToStockDate() {
		return toStockDate;
	}
	public void setToStockDate(String toStockDate) {
		this.toStockDate = toStockDate;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public int getStockSeq() {
		return stockSeq;
	}
	public void setStockSeq(int stockSeq) {
		this.stockSeq = stockSeq;
	}
	public int getStockLocationSeq() {
		return stockLocationSeq;
	}
	public void setStockLocationSeq(int stockLocationSeq) {
		this.stockLocationSeq = stockLocationSeq;
	}
	public int getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}
}
