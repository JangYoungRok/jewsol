package com.jewsol.store.originalSheet.bean;

public class OriginalSheetDTO {
	private int originalSheetSeq;
	private int branchSeq;
	private int insertMemberSeq;
	private String originalSheetDate;
	private int originalSheetNumber;
	private String originalSheetClose;
	private int originalSheetQty;
	
	public int getOriginalSheetQty() {
		return originalSheetQty;
	}
	public void setOriginalSheetQty(int originalSheetQty) {
		this.originalSheetQty = originalSheetQty;
	}
	public final int getOriginalSheetSeq() {
		return originalSheetSeq;
	}
	public final void setOriginalSheetSeq(int originalSheetSeq) {
		this.originalSheetSeq = originalSheetSeq;
	}
	public final int getBranchSeq() {
		return branchSeq;
	}
	public final void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public final int getInsertMemberSeq() {
		return insertMemberSeq;
	}
	public final void setInsertMemberSeq(int insertMemberSeq) {
		this.insertMemberSeq = insertMemberSeq;
	}
	public final String getOriginalSheetDate() {
		return originalSheetDate;
	}
	public final void setOriginalSheetDate(String originalSheetDate) {
		this.originalSheetDate = originalSheetDate;
	}

	public final String getOriginalSheetClose() {
		return originalSheetClose;
	}
	public final void setOriginalSheetClose(String originalSheetClose) {
		this.originalSheetClose = originalSheetClose;
	}
	public final int getOriginalSheetNumber() {
		return originalSheetNumber;
	}
	public final void setOriginalSheetNumber(int originalSheetNumber) {
		this.originalSheetNumber = originalSheetNumber;
	}
	
	
	
}
