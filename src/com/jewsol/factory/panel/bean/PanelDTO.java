package com.jewsol.factory.panel.bean;

import java.util.List;

public class PanelDTO {
	private int panelSeq;
	private String panelGroup;
	private int panelNumber;
	private String panelDate;
	private List<ViewOrderForPanelDTO> orderForPanelList;
	
	public int getPanelSeq() {
		return panelSeq;
	}
	public void setPanelSeq(int panelSeq) {
		this.panelSeq = panelSeq;
	}
	public List<ViewOrderForPanelDTO> getOrderForPanelList() {
		return orderForPanelList;
	}
	public void setOrderForPanelList(List<ViewOrderForPanelDTO> orderForPanelList) {
		this.orderForPanelList = orderForPanelList;
	}
	public String getPanelGroup() {
		return panelGroup;
	}
	public void setPanelGroup(String panelGroup) {
		this.panelGroup = panelGroup;
	}
	public int getPanelNumber() {
		return panelNumber;
	}
	public void setPanelNumber(int panelNumber) {
		this.panelNumber = panelNumber;
	}
	public String getPanelDate() {
		return panelDate;
	}
	public void setPanelDate(String panelDate) {
		this.panelDate = panelDate;
	}
	
	
}
