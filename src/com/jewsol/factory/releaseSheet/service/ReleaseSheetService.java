package com.jewsol.factory.releaseSheet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.common.util.BasicUtil;
import com.jewsol.core.branch.service.BranchService;
import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.bean.ViewOrderForReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.dao.ReleaseSheetDAO;
import com.jewsol.store.order.service.OrderService;
import com.jewsol.store.orderOption.service.OrderOptionService;

@Service
public class ReleaseSheetService {
	@Autowired
	private ReleaseSheetDAO releaseSheetDao;
	@Autowired
	private BranchService branchService;
	@Autowired
	private OrderOptionService orderOptionService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private BasicUtil basicUtil;
	
	public List<ReleaseSheetDTO> getReleaseSheetList(String releaseSheetDate) {		
		return releaseSheetDao.getReleaseSheetList(releaseSheetDate);
	}

	public List<ViewOrderForReleaseSheetDTO> getOrderListForReleaseSheetList(
			String branchName, int orderState) {
		ViewOrderForReleaseSheetDTO orderDto = new ViewOrderForReleaseSheetDTO();
		orderDto.setBranchName(branchName);
		orderDto.setOrderState(orderState);
		return releaseSheetDao.getOrderListForReleaseSheetList(orderDto);
	}

	public int getReleaseSheetNumber(String branchName, String releaseSheetDate) {
		int releaseSheetNumber = 0;
		int branchSeq = branchService.getBranchSeq(branchName);
		ReleaseSheetDTO releaseSheetDto = new ReleaseSheetDTO();
		releaseSheetDto.setBranchSeq(branchSeq);
		releaseSheetDto.setReleaseSheetDate(releaseSheetDate);
		int value = releaseSheetDao.getReleaseSheetNumber(releaseSheetDto);
		releaseSheetNumber = value + 1;
		
		return releaseSheetNumber;
	}

	public String getReleaseSheetType(int releaseSheetSeq) {
		return releaseSheetDao.getReleaseSheetType(releaseSheetSeq);
	}

	public ReleaseSheetDTO getReleaseSheet(int releaseSheetSeq) {
		return releaseSheetDao.getReleaseSheet(releaseSheetSeq);
	}

	public List<ViewOrderForReleaseSheetDTO> getOrderListForPrintReleaseSheetList(
			int releaseSheetSeq) {
		return releaseSheetDao.getOrderListForPrintReleaseSheetList(releaseSheetSeq);
	}

	public void insertReleaseSheet(String orderSeqArr, String branchName, String releaseDate) {
		String[] orderSeqArray = orderSeqArr.split(",");
		int releaseSheetNumber = getReleaseSheetNumber(branchName, releaseDate);
		int branchSeq = branchService.getBranchSeq(branchName);
		int totalLabor = getTotalLabor(orderSeqArray);
		
		Double total14KWeight = getTotalKWeight("14", orderSeqArray);
		Double total18KWeight = getTotalKWeight("18", orderSeqArray);
		Double total10KWeight = getTotalKWeight("10", orderSeqArray);
		Double totalGoldWeight = basicUtil.doubleCut(((total14KWeight * 0.585) + (total18KWeight * 0.75) + (total10KWeight * 0.416)) * 1.07);
		int totalQty = orderSeqArray.length;
		int releaseSheetType = 1;
		
		ReleaseSheetDTO releaseSheet = new ReleaseSheetDTO();
		releaseSheet.setBranchSeq(branchSeq);
		releaseSheet.setReleaseSheetDate(releaseDate);
		releaseSheet.setReleaseSheetNumber(releaseSheetNumber);
		releaseSheet.setReleaseSheetType(releaseSheetType);
		releaseSheet.setTotalQty(totalQty);
		releaseSheet.setTotalLabor(totalLabor);
		releaseSheet.setTotalGoldWeight(totalGoldWeight);
		releaseSheet.setTotal14KWeight(total14KWeight);
		releaseSheet.setTotal18KWeight(total18KWeight);
		releaseSheet.setTotal10KWeight(total10KWeight);
		
		int releaseSheetSeq = releaseSheetDao.insertReleaseSheet(releaseSheet);
		updateOrderState5(orderSeqArray, releaseSheetSeq);
	}

	public Double getTotalKWeight(String orderKSample, String[] orderSeqArray) {
		String orderK = null;
		Double totalKGoldWeight = 0.0;
		Double orderWeight = 0.0;
		int orderSeq = 0;
		for(String orderSeqStr : orderSeqArray){
			orderSeq = Integer.parseInt(orderSeqStr);
			orderK = orderService.getOrderK(orderSeq);
			if(orderK.equals(orderKSample)){
				orderWeight = orderService.getOrderWeight(orderSeq);
				totalKGoldWeight = totalKGoldWeight + orderWeight;
			}
		}
		return totalKGoldWeight;
	}

	private void updateOrderState5(String[] orderSeqArray, int releaseSheetSeq) {
		for(String value : orderSeqArray){
			int orderSeq = Integer.parseInt(value);
			orderService.updateOrderState5(orderSeq, releaseSheetSeq);
		}
	}

	public int getTotalLabor(String[] orderSeqArray) {
		int totalLabor = 0;
		for(String orderSeqValue : orderSeqArray){
			int orderSeq = Integer.parseInt(orderSeqValue);
			totalLabor = totalLabor + orderOptionService.getOrderOptionLabor(orderSeq); 
		}
		return totalLabor;
	}

	public List<ReleaseSheetDTO> getUnStockedReleaseSheetList(int branchSeq) {
		return releaseSheetDao.getUnStockedReleaseSheetList(branchSeq);
	}

	public List<ViewOrderForReleaseSheetDTO> getOrderList(
			int releaseSheetSeq) {
		return releaseSheetDao.getOrderList(releaseSheetSeq);
	}

	public void updateReleaseSheet(ReleaseSheetDTO releaseSheet) {
		releaseSheetDao.updateReleaseSheet(releaseSheet);
	}

	public Double getTotalGoldWeight(String[] orderSeqArray) {
		Double total14KWeight = getTotalKWeight("14", orderSeqArray);
		Double total18KWeight = getTotalKWeight("18", orderSeqArray);
		Double total10KWeight = getTotalKWeight("10", orderSeqArray);
		Double totalGoldWeight = basicUtil.doubleCut((total14KWeight * 0.585) + (total18KWeight * 0.75) + (total10KWeight * 0.416));
		return totalGoldWeight;
	}

}
