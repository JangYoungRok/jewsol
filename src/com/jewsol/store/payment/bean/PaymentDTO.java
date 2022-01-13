package com.jewsol.store.payment.bean;

public class PaymentDTO {
	private int paymentSeq;
	private int customerSeq;
	private int goldPriceSeq;
	private String paymentDate;
	private Double lastBalanceGold;
	private int lastBalanceCash;
	private Double paymentGold;
	private int paymentCash;
	private String paymentContent;
	private int paymentDC;
	private Double balanceGold;
	private int balanceCash;
	private String logtime;
	
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getPaymentSeq() {
		return paymentSeq;
	}
	public void setPaymentSeq(int paymentSeq) {
		this.paymentSeq = paymentSeq;
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
	public Double getPaymentGold() {
		return paymentGold;
	}
	public void setPaymentGold(Double paymentGold) {
		this.paymentGold = paymentGold;
	}
	public int getPaymentCash() {
		return paymentCash;
	}
	public void setPaymentCash(int paymentCash) {
		this.paymentCash = paymentCash;
	}
	public String getPaymentContent() {
		return paymentContent;
	}
	public void setPaymentContent(String paymentContent) {
		this.paymentContent = paymentContent;
	}
	public int getPaymentDC() {
		return paymentDC;
	}
	public void setPaymentDC(int paymentDC) {
		this.paymentDC = paymentDC;
	}
	public Double getBalanceGold() {
		return balanceGold;
	}
	public void setBalanceGold(Double balanceGold) {
		this.balanceGold = balanceGold;
	}
	public int getBalanceCash() {
		return balanceCash;
	}
	public void setBalanceCash(int balanceCash) {
		this.balanceCash = balanceCash;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
}
