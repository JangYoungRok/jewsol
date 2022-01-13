package com.jewsol.core.member.bean;

public class MemberDTO {
	private int memberSeq;
	private int branchSeq;
	private String memberName;
	public final int getMemberSeq() {
		return memberSeq;
	}
	public final void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	public final int getBranchSeq() {
		return branchSeq;
	}
	public final void setBranchSeq(int branchSeq) {
		this.branchSeq = branchSeq;
	}
	public final String getMemberName() {
		return memberName;
	}
	public final void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
}
