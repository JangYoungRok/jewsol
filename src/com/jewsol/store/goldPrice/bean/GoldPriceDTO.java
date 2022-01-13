package com.jewsol.store.goldPrice.bean;

public class GoldPriceDTO {
	private int goldPriceSeq;
	private String goldPriceDate;
	private int goldPrice;
	private int goldTaxPrice;
	private String logtime;
	public int getGoldPriceSeq() {
		return goldPriceSeq;
	}
	public void setGoldPriceSeq(int goldPriceSeq) {
		this.goldPriceSeq = goldPriceSeq;
	}
	public String getGoldPriceDate() {
		return goldPriceDate;
	}
	public void setGoldPriceDate(String goldPriceDate) {
		this.goldPriceDate = goldPriceDate;
	}
	public int getGoldPrice() {
		return goldPrice;
	}
	public void setGoldPrice(int goldPrice) {
		this.goldPrice = goldPrice;
	}
	public int getGoldTaxPrice() {
		return goldTaxPrice;
	}
	public void setGoldTaxPrice(int goldTaxPrice) {
		this.goldTaxPrice = goldTaxPrice;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
	
}
