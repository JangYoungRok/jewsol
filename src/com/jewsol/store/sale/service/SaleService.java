package com.jewsol.store.sale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.common.util.BasicUtil;
import com.jewsol.store.goldPrice.bean.GoldPriceDTO;
import com.jewsol.store.goldPrice.service.GoldPriceService;
import com.jewsol.store.order.service.OrderService;
import com.jewsol.store.sale.bean.OrderForSaleDTO;
import com.jewsol.store.sale.bean.SaleDTO;
import com.jewsol.store.sale.bean.SaleTypeDTO;
import com.jewsol.store.sale.dao.SaleDAO;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;
import com.jewsol.store.saleSheet.service.SaleSheetService;
import com.jewsol.store.stock.service.StockService;

@Service
public class SaleService {
	@Autowired
	private SaleDAO saleDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private GoldPriceService goldPriceService;
	@Autowired
	private SaleSheetService saleSheetService;
	@Autowired
	private BasicUtil util;
	@Autowired
	private StockService stockService;
	
	private Double lossRate = 1.1;
	private Double k14Rate = 0.585;
	private Double k18Rate = 0.75;
	private Double k10Rate = 0.416;
	
	public List<SaleTypeDTO> getSaleTypeList() {
		return saleDao.getSaleTypeList();
	}

	public List<OrderForSaleDTO> getOrderListForSale(int customerSeq) {
		return saleDao.getOrderListForSale(customerSeq);
	}

	public Double getTotalKGoldWeight(String orderKSample, String[] orderSeqArray,
			String[] orderSaleWeightArray) {
		String orderK = null;
		Double totalKGoldWeight = 0.0;
		Double goldWeight = 0.0;
		int orderSeq = 0;
		for(int i = 0; i < orderSeqArray.length; i++){
			orderSeq = Integer.parseInt(orderSeqArray[i]);
			orderK = orderService.getOrderK(orderSeq);
			if(orderK.equals(orderKSample)){
				goldWeight = Double.parseDouble(orderSaleWeightArray[i]);
				totalKGoldWeight = totalKGoldWeight + goldWeight;
			}
		}
		
		return totalKGoldWeight;
	}

	public Double getTotalGoldWeight(Double total14Weight,
			Double total18Weight, Double total10Weight) {
		Double totalGoldWeight = ((total14Weight * k14Rate) + (total18Weight * k18Rate) + (total10Weight * k10Rate)) * lossRate;
		return totalGoldWeight;
	}

	public int getTotalSalePrice(String[] orderSalePriceArray) {
		int totalSalePrice = 0;
		for(String value : orderSalePriceArray){
			int salePrice = Integer.parseInt(value);
			totalSalePrice = totalSalePrice + salePrice;
		}
		return totalSalePrice;
	}

	public void insertSale(int customerSeq, String saleDate, int saleTypeSeq,
			String orderSeqArr, String orderSaleWeightArr,
			String orderSalePriceArr) {
		
		GoldPriceDTO goldPrice = goldPriceService.getGoldPrice();
		int goldPriceSeq = goldPrice.getGoldPriceSeq();
		String[] orderSeqArray = orderSeqArr.split(",");
		String[] orderSaleWeightArray = orderSaleWeightArr.split(",");
		String[] orderSalePriceArray = orderSalePriceArr.split(",");
		Double total14Weight = getTotalKGoldWeight("14", orderSeqArray, orderSaleWeightArray);
		Double total18Weight = getTotalKGoldWeight("18", orderSeqArray, orderSaleWeightArray);
		Double total10Weight = getTotalKGoldWeight("10", orderSeqArray, orderSaleWeightArray);
		Double totalGoldWeight = getTotalGoldWeight(total14Weight, total18Weight, total10Weight);
		
		total14Weight = util.doubleCut(total14Weight);
		total18Weight = util.doubleCut(total18Weight);
		total10Weight = util.doubleCut(total10Weight);
		totalGoldWeight = util.doubleCut(totalGoldWeight);
		
		int totalSalePrice = getTotalSalePrice(orderSalePriceArray);
		totalSalePrice = util.intCut(totalSalePrice);
		int lastSaleSheetNumber = saleSheetService.getLastSaleSheetNumber(saleDate);
		lastSaleSheetNumber = lastSaleSheetNumber + 1;
		
		SaleSheetDTO saleSheet = new SaleSheetDTO();
		saleSheet.setCustomerSeq(customerSeq);
		saleSheet.setGoldPriceSeq(goldPriceSeq);
		saleSheet.setSaleTypeSeq(saleTypeSeq);
		saleSheet.setSaleSheetDate(saleDate);
		saleSheet.setSaleSheetNumber(lastSaleSheetNumber);
		saleSheet.setTotal14Weight(total14Weight);
		saleSheet.setTotal18Weight(total18Weight);
		saleSheet.setTotal10Weight(total10Weight);
		saleSheet.setTotalGoldWeight(totalGoldWeight);
		saleSheet.setTotalSalePrice(totalSalePrice);
		
		int saleSheetSeq = saleSheetService.insertSaleSheet(saleSheet, goldPrice);
		int orderState = 7;
		insertSale(orderSeqArray, saleSheetSeq, orderSalePriceArray, orderSaleWeightArray);
		
		orderService.updateOrderSaleWeight(orderSeqArray, orderSaleWeightArray);
		orderService.updateOrderPrice(orderSeqArray, orderSalePriceArray);
		orderService.updateOrderState(orderSeqArray, orderState);
		
		stockService.deleteStock(orderSeqArray);
		
	}

	private void insertSale(String[] orderSeqArray, int saleSheetSeq, String[] orderSalePriceArray, String[] orderSaleWeightArray) {
		SaleDTO sale = new SaleDTO();
		for(int i = 0; i < orderSeqArray.length; i++){
			int orderSeq = Integer.parseInt(orderSeqArray[i]);
			int orderPrice = Integer.parseInt(orderSalePriceArray[i]);
			Double orderWeight = Double.parseDouble(orderSaleWeightArray[i]);
			
			sale.setOrderSeq(orderSeq);
			sale.setSaleSheetSeq(saleSheetSeq);
			sale.setSalePrice(orderPrice);
			sale.setSaleWeight(orderWeight);
			saleDao.insertSale(sale);
		}
	}

	public void deleteSaleSheet(int saleSheetSeq) {
		saleDao.deleteSaleSheet(saleSheetSeq);
	}
	
	public List<Integer> getOrderSeqList(int saleSheetSeq) {
		return saleDao.getOrderSeqList(saleSheetSeq);
	}

	public void insertSale(int newSaleSheetSeq, List<Integer> orderSeqList) {
		SaleDTO sale = new SaleDTO();
		int salePrice = 0;
		Double saleWeight = 0.0;
		for(int orderSeq : orderSeqList){
			salePrice = orderService.getOrderSalePrice(orderSeq);
			saleWeight = orderService.getOrderSaleWeight(orderSeq);
			sale.setOrderSeq(orderSeq);
			sale.setSaleSheetSeq(newSaleSheetSeq);
			sale.setSalePrice(salePrice);
			sale.setSaleWeight(saleWeight);
			saleDao.insertSale(sale);
		}
	}
}
