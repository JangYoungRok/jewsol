package com.jewsol.store.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.common.util.BasicUtil;
import com.jewsol.core.branch.bean.BranchBalanceDTO;
import com.jewsol.core.branch.service.BranchService;
import com.jewsol.factory.release.dao.ReleaseDAO;
import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.service.ReleaseSheetService;
import com.jewsol.store.order.dao.OrderDAO;
import com.jewsol.store.order.service.OrderService;
import com.jewsol.store.stock.bean.StockDTO;
import com.jewsol.store.stock.dao.StockDAO;

@Service
public class StockService {
	@Autowired
	private ReleaseSheetService releaseSheetService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private StockDAO stockDao;
	@Autowired
	private ReleaseDAO releaseDao;
	@Autowired
	private OrderDAO orderDao;
	@Autowired BasicUtil basicUtil;

	public void toStock(String orderSeqArr, String unCheckedOrderSeqArr, String toStockDate, int branchSeq, int releaseSheetSeq) {
		String[] orderSeqArray = orderSeqArr.split(",");
		String[] unCheckedArray = unCheckedOrderSeqArr.split(",");
		int totalLabor = releaseSheetService.getTotalLabor(orderSeqArray);
		Double total14KWeight = releaseSheetService.getTotalKWeight("14", orderSeqArray);
		Double total18KWeight = releaseSheetService.getTotalKWeight("18", orderSeqArray);
		Double total10KWeight = releaseSheetService.getTotalKWeight("10", orderSeqArray);
		Double totalGoldWeight = basicUtil.doubleCut(releaseSheetService.getTotalGoldWeight(orderSeqArray) * 1.07);
		int totalQty = orderSeqArray.length;
		ReleaseSheetDTO releaseSheet = new ReleaseSheetDTO();
		releaseSheet.setReleaseSheetSeq(releaseSheetSeq);
		releaseSheet.setTotalGoldWeight(totalGoldWeight);
		releaseSheet.setTotal10KWeight(total10KWeight);
		releaseSheet.setTotal14KWeight(total14KWeight);
		releaseSheet.setTotal18KWeight(total18KWeight);
		releaseSheet.setTotalLabor(totalLabor);
		releaseSheet.setTotalQty(totalQty);
		stockDao.updateToStockReleaseSheet(releaseSheet);
		
		int branchReleaseSeq = branchService.insertBranchRelease(releaseSheet);
		BranchBalanceDTO lastBranchBalance = branchService.getLastBranchBalance(branchSeq);
		BranchBalanceDTO branchBalance = new BranchBalanceDTO();
		Double balanceGold = 0.0;
		int balanceLabor = 0;
		
		if(lastBranchBalance == null){
			balanceGold = totalGoldWeight;
			balanceLabor = totalLabor;
		}else{
			balanceGold = lastBranchBalance.getBalanceGold() + totalGoldWeight;
			balanceLabor = lastBranchBalance.getBalanceLabor() + totalLabor;
		}
		
		branchBalance.setBranchSeq(branchSeq);
		branchBalance.setBranchReleaseSeq(branchReleaseSeq);
		branchBalance.setBalanceGold(balanceGold);
		branchBalance.setBalanceLabor(balanceLabor);
		branchService.insertBranchBalance(branchBalance);
		updateOrderState6(orderSeqArray);
		insertStock(orderSeqArray, toStockDate);
		updateUnCheckedOrder(unCheckedArray);
	}

	private void updateUnCheckedOrder(String[] unCheckedArray) {
		int releaseSheetSeq = 0;
		for(String value : unCheckedArray){
			if(value != ""){
				int orderSeq = Integer.parseInt(value);
				releaseDao.updateOrderState4(orderSeq);
				orderDao.updateReleaseSheetSeqInOrder(orderSeq, releaseSheetSeq);
			}
		}
	}

	public void insertStock(String[] orderSeqArray, String toStockDate) {
		for(String value : orderSeqArray){
			StockDTO stock = new StockDTO();
			int orderSeq = Integer.parseInt(value);
			stock.setOrderSeq(orderSeq);
			stock.setToStockDate(toStockDate);
			stockDao.insertStock(stock);
		}
	}

	private void updateOrderState6(String[] orderSeqArray) {
		for(String value : orderSeqArray){
			int orderSeq = Integer.parseInt(value);
			orderService.updateOrderState6(orderSeq);
		}
	}

	public void deleteStock(String[] orderSeqArray) {
		for(String orderSeqStr : orderSeqArray){
			int orderSeq = Integer.parseInt(orderSeqStr);
			stockDao.deleteStock(orderSeq);
		}
		
	}
}
