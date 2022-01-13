package com.jewsol.store.option.bean;

public class OptionPartDTO {
	private int optionPartSeq;
	private int optionSeq;
	private int partSeq;
	private String partName;
	private int optionPartPrice;
	private int optionPartLabor;
	public final int getOptionPartSeq() {
		return optionPartSeq;
	}
	public final void setOptionPartSeq(int optionPartSeq) {
		this.optionPartSeq = optionPartSeq;
	}
	public final int getOptionSeq() {
		return optionSeq;
	}
	public final void setOptionSeq(int optionSeq) {
		this.optionSeq = optionSeq;
	}
	public final int getPartSeq() {
		return partSeq;
	}
	public final void setPartSeq(int partSeq) {
		this.partSeq = partSeq;
	}
	public final String getPartName() {
		return partName;
	}
	public final void setPartName(String partName) {
		this.partName = partName;
	}
	public final int getOptionPartPrice() {
		return optionPartPrice;
	}
	public final void setOptionPartPrice(int optionPartPrice) {
		this.optionPartPrice = optionPartPrice;
	}
	public final int getOptionPartLabor() {
		return optionPartLabor;
	}
	public final void setOptionPartLabor(int optionPartLabor) {
		this.optionPartLabor = optionPartLabor;
	}
	
}
