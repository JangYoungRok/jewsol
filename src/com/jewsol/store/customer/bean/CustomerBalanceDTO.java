package com.jewsol.store.customer.bean;

public class CustomerBalanceDTO {
	private int customerBalanceSeq;
	private int customerSeq;
	private int saleSheetSeq;
	private int paymentSeq;
	private int customerBalanceCash;
	private Double customerBalanceGold;
	private String logtime;
	
	
	public int getPaymentSeq() {
		return paymentSeq;
	}
	public void setPaymentSeq(int paymentSeq) {
		this.paymentSeq = paymentSeq;
	}
	public int getCustomerBalanceSeq() {
		return customerBalanceSeq;
	}
	public void setCustomerBalanceSeq(int customerBalanceSeq) {
		this.customerBalanceSeq = customerBalanceSeq;
	}
	public int getCustomerSeq() {
		return customerSeq;
	}
	public void setCustomerSeq(int customerSeq) {
		this.customerSeq = customerSeq;
	}
	public int getSaleSheetSeq() {
		return saleSheetSeq;
	}
	public void setSaleSheetSeq(int saleSheetSeq) {
		this.saleSheetSeq = saleSheetSeq;
	}
	public int getCustomerBalanceCash() {
		return customerBalanceCash;
	}
	public void setCustomerBalanceCash(int customerBalanceCash) {
		this.customerBalanceCash = customerBalanceCash;
	}
	public Double getCustomerBalanceGold() {
		return customerBalanceGold;
	}
	public void setCustomerBalanceGold(Double customerBalanceGold) {
		this.customerBalanceGold = customerBalanceGold;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
	
}
