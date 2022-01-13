package com.jewsol.store.saleSheet.bean;

public class SaleSheetDTO {
	private int saleSheetSeq;
	private int customerSeq;
	private int goldPriceSeq;
	private int saleTypeSeq;
	private String saleSheetDate;
	private int saleSheetNumber;
	private Double total14Weight;
	private Double total18Weight;
	private Double total10Weight;
	private Double totalGoldWeight;
	private int totalSalePrice;
	private int saleCash;
	private Double saleGold;
	private Double lastBalanceGold;
	private int lastBalanceCash;
	private Double balanceGold;
	private int balanceCash;
	private String logtime;
	
	public int getSaleCash() {
		return saleCash;
	}
	public void setSaleCash(int saleCash) {
		this.saleCash = saleCash;
	}
	public Double getSaleGold() {
		return saleGold;
	}
	public void setSaleGold(Double saleGold) {
		this.saleGold = saleGold;
	}
	public int getBalanceCash() {
		return balanceCash;
	}
	public void setBalanceCash(int balanceCash) {
		this.balanceCash = balanceCash;
	}
	public Double getLastBalanceGold() {
		return lastBalanceGold;
	}
	public void setLastBalanceGold(Double lastBalanceGold) {
		this.lastBalanceGold = lastBalanceGold;
	}
	public int getLastBalanceCash() {
		return lastBalanceCash;
	}
	public void setLastBalanceCash(int lastBalanceCash) {
		this.lastBalanceCash = lastBalanceCash;
	}
	public Double getBalanceGold() {
		return balanceGold;
	}
	public void setBalanceGold(Double balanceGold) {
		this.balanceGold = balanceGold;
	}
	public int getSaleSheetNumber() {
		return saleSheetNumber;
	}
	public void setSaleSheetNumber(int saleSheetNumber) {
		this.saleSheetNumber = saleSheetNumber;
	}
	public int getSaleSheetSeq() {
		return saleSheetSeq;
	}
	public void setSaleSheetSeq(int saleSheetSeq) {
		this.saleSheetSeq = saleSheetSeq;
	}
	public int getCustomerSeq() {
		return customerSeq;
	}
	public void setCustomerSeq(int customerSeq) {
		this.customerSeq = customerSeq;
	}
	public int getGoldPriceSeq() {
		return goldPriceSeq;
	}
	public void setGoldPriceSeq(int goldPriceSeq) {
		this.goldPriceSeq = goldPriceSeq;
	}
	public int getSaleTypeSeq() {
		return saleTypeSeq;
	}
	public void setSaleTypeSeq(int saleTypeSeq) {
		this.saleTypeSeq = saleTypeSeq;
	}
	public String getSaleSheetDate() {
		return saleSheetDate;
	}
	public void setSaleSheetDate(String saleSheetDate) {
		this.saleSheetDate = saleSheetDate;
	}
	public Double getTotal14Weight() {
		return total14Weight;
	}
	public void setTotal14Weight(Double total14Weight) {
		this.total14Weight = total14Weight;
	}
	public Double getTotal18Weight() {
		return total18Weight;
	}
	public void setTotal18Weight(Double total18Weight) {
		this.total18Weight = total18Weight;
	}
	public Double getTotal10Weight() {
		return total10Weight;
	}
	public void setTotal10Weight(Double total10Weight) {
		this.total10Weight = total10Weight;
	}
	public Double getTotalGoldWeight() {
		return totalGoldWeight;
	}
	public void setTotalGoldWeight(Double totalGoldWeight) {
		this.totalGoldWeight = totalGoldWeight;
	}
	public int getTotalSalePrice() {
		return totalSalePrice;
	}
	public void setTotalSalePrice(int totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
}
