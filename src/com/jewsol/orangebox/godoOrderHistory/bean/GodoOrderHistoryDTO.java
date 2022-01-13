package com.jewsol.orangebox.godoOrderHistory.bean;

public class GodoOrderHistoryDTO {
	private int godoOrderHistorySeq;
	private int godoOrderSeq;
	private String godoOrderHistory;
	private String logtime;
	
	public int getGodoOrderHistorySeq() {
		return godoOrderHistorySeq;
	}
	public void setGodoOrderHistorySeq(int godoOrderHistorySeq) {
		this.godoOrderHistorySeq = godoOrderHistorySeq;
	}
	public int getGodoOrderSeq() {
		return godoOrderSeq;
	}
	public void setGodoOrderSeq(int godoOrderSeq) {
		this.godoOrderSeq = godoOrderSeq;
	}
	public String getGodoOrderHistory() {
		return godoOrderHistory;
	}
	public void setGodoOrderHistory(String godoOrderHistory) {
		this.godoOrderHistory = godoOrderHistory;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	
	
}
