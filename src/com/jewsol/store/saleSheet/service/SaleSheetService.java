package com.jewsol.store.saleSheet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.common.date.DateService;
import com.jewsol.common.util.BasicUtil;
import com.jewsol.store.customer.bean.CustomerBalanceDTO;
import com.jewsol.store.customer.bean.CustomerDTO;
import com.jewsol.store.customer.bean.CustomerTypeDTO;
import com.jewsol.store.customer.service.CustomerService;
import com.jewsol.store.goldPrice.bean.GoldPriceDTO;
import com.jewsol.store.goldPrice.service.GoldPriceService;
import com.jewsol.store.order.service.OrderService;
import com.jewsol.store.sale.service.SaleService;
import com.jewsol.store.saleSheet.bean.OrderForSaleSheetDTO;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;
import com.jewsol.store.saleSheet.dao.SaleSheetDAO;
import com.jewsol.store.stock.service.StockService;

@Service
public class SaleSheetService {
	@Autowired
	private SaleSheetDAO saleSheetDao;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BasicUtil util;
	@Autowired
	private GoldPriceService goldPriceService;
	@Autowired
	private DateService dateService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private StockService stockService;
	
	private Double taxPremium = 1.02;
	private Double taxRate = 1.1;
	
	private int withOutTax = 1;
	private int sumGoldSalePrice = 2;
	private int taxSalePrice = 3;
	private int sumTaxGoldTaxSalePrice = 4;

	public List<SaleSheetDTO> getSaleSheetList(int customerSeq) {
		return saleSheetDao.getSaleSheetList(customerSeq);
	}

	public SaleSheetDTO getSaleSheet(int saleSheetSeq) {
		return saleSheetDao.getSaleSheet(saleSheetSeq);
	}

	public List<OrderForSaleSheetDTO> getOrderListForSaleSheet(
			int saleSheetSeq) {
		return saleSheetDao.getOrderListForSaleSheet(saleSheetSeq);
	}

	public int getLastSaleSheetNumber(String saleDate) {
		return saleSheetDao.getLastSaleSheetNumber(saleDate);
	}

	public int insertSaleSheet(SaleSheetDTO saleSheet, GoldPriceDTO goldPrice) {
		int saleSheetSeq = 0;
		int saleType = saleSheet.getSaleTypeSeq();
		int customerSeq = saleSheet.getCustomerSeq();
		int saleCash = 0;
		Double saleGold = 0.0;
		int lastBalanceCash = 0;
		Double lastBalanceGold = 0.0;
		int balanceCash = 0;
		Double balanceGold = 0.0;
		
		if(saleType == withOutTax){
			saleCash = saleSheet.getTotalSalePrice();
			saleGold = saleSheet.getTotalGoldWeight();
		}else if(saleType == sumGoldSalePrice){
			saleCash = saleSheet.getTotalSalePrice() + (int)(saleSheet.getTotalGoldWeight()/3.75 * goldPrice.getGoldPrice());
		}else if(saleType == taxSalePrice){
			saleCash = (int)(saleSheet.getTotalSalePrice() * taxRate);
			saleGold = saleSheet.getTotalGoldWeight();
		}else if(saleType == sumTaxGoldTaxSalePrice){
			saleCash = (int)(saleSheet.getTotalSalePrice() * taxRate) + (int)(saleSheet.getTotalGoldWeight()/3.75 * goldPrice.getGoldTaxPrice() * taxPremium * taxRate);
		}
		
		saleCash = util.intCut(saleCash);
		saleGold = util.doubleCut(saleGold);
		saleSheet.setSaleCash(saleCash);
		saleSheet.setSaleGold(saleGold);
		saleSheetSeq = saleSheetDao.insertSaleSheet(saleSheet);
		
		CustomerBalanceDTO lastCustomerBalance = customerService.getLastCustomerBalance(customerSeq);
		CustomerBalanceDTO customerBalance = new CustomerBalanceDTO();
		if(lastCustomerBalance == null){
			balanceCash = saleCash;
			balanceGold = saleGold;
		}else{
			lastBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
			lastBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
			
			balanceCash = lastBalanceCash + saleCash;
			balanceGold = lastBalanceGold + saleGold;
		}
		
		customerBalance.setCustomerBalanceCash(balanceCash);
		customerBalance.setCustomerBalanceGold(balanceGold);
		customerBalance.setCustomerSeq(customerSeq);
		customerBalance.setPaymentSeq(0);
		customerBalance.setSaleSheetSeq(saleSheetSeq);
		customerService.insertCustomerBalance(customerBalance);
		
		saleSheet.setSaleSheetSeq(saleSheetSeq);
		saleSheet.setBalanceCash(balanceCash);
		saleSheet.setBalanceGold(balanceGold);
		saleSheet.setLastBalanceCash(lastBalanceCash);
		saleSheet.setLastBalanceGold(lastBalanceGold);
		saleSheetDao.updateBalance(saleSheet);
		
		return saleSheetSeq;
	}

	public void cancleSaleSheet(int saleSheetSeq, int customerSeq) {
		int saleCash = 0;
		Double saleGold = 0.0;
		int lastBalanceCash = 0;
		Double lastBalanceGold = 0.0;
		int balanceCash = 0;
		Double balanceGold = 0.0;
		
		String saleDate = dateService.getToday();
		SaleSheetDTO saleSheet = getSaleSheet(saleSheetSeq);
		CustomerBalanceDTO lastCustomerBalance	= customerService.getLastCustomerBalance(customerSeq);
		GoldPriceDTO goldPrice = goldPriceService.getGoldPrice();
		int goldPriceSeq = goldPrice.getGoldPriceSeq();
		int lastSaleSheetNumber = getLastSaleSheetNumber(saleDate);
		lastSaleSheetNumber = lastSaleSheetNumber + 1;
		Double total14Weight = saleSheet.getTotal14Weight() * -1;
		Double total18Weight = saleSheet.getTotal18Weight()	* -1;
		Double total10Weight = saleSheet.getTotal10Weight() * -1;
		Double totalGoldWeight = saleSheet.getTotalGoldWeight() * -1;
		int totalSalePrice = saleSheet.getTotalSalePrice() * -1;
		saleCash = saleSheet.getSaleCash() * -1;
		saleGold = saleSheet.getSaleGold() * -1;
		
		SaleSheetDTO insertSaleSheet = new SaleSheetDTO();
		insertSaleSheet.setCustomerSeq(customerSeq);
		insertSaleSheet.setGoldPriceSeq(goldPriceSeq);
		insertSaleSheet.setSaleTypeSeq(saleSheet.getSaleTypeSeq());
		insertSaleSheet.setSaleSheetDate(saleDate);
		insertSaleSheet.setSaleSheetNumber(lastSaleSheetNumber);
		insertSaleSheet.setTotal14Weight(total14Weight);
		insertSaleSheet.setTotal18Weight(total18Weight);
		insertSaleSheet.setTotal10Weight(total10Weight);
		insertSaleSheet.setTotalGoldWeight(totalGoldWeight);
		insertSaleSheet.setTotalSalePrice(totalSalePrice);
		insertSaleSheet.setSaleCash(saleCash);
		insertSaleSheet.setSaleGold(saleGold);
		int newSaleSheetSeq = saleSheetDao.insertSaleSheet(insertSaleSheet);
		
		CustomerBalanceDTO customerBalance = new CustomerBalanceDTO();
		if(lastCustomerBalance == null){
			balanceCash = saleCash;
			balanceGold = saleGold;
		}else{
			lastBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
			lastBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
			
			balanceCash = lastBalanceCash + saleCash;
			balanceGold = lastBalanceGold + saleGold;
		}
		
		customerBalance.setCustomerBalanceCash(balanceCash);
		customerBalance.setCustomerBalanceGold(balanceGold);
		customerBalance.setCustomerSeq(customerSeq);
		customerBalance.setPaymentSeq(0);
		customerBalance.setSaleSheetSeq(newSaleSheetSeq);
		customerService.insertCustomerBalance(customerBalance);
		
		saleSheet.setSaleSheetSeq(newSaleSheetSeq);
		saleSheet.setBalanceCash(balanceCash);
		saleSheet.setBalanceGold(balanceGold);
		saleSheet.setLastBalanceCash(lastBalanceCash);
		saleSheet.setLastBalanceGold(lastBalanceGold);
		saleSheetDao.updateBalance(saleSheet);
		
		int orderState = 6;
		
		List<Integer> orderSeqList = saleSheetDao.getOrderSeqList(saleSheetSeq);
		//orderService.initOrderSalePrice(orderSeqList);
		//orderService.initOrderSaleWeight(orderSeqList);
		orderService.updateOrderState(orderSeqList, orderState);
		//saleService.deleteSaleSheet(saleSheetSeq);
		String[] orderSeqArr = new String[orderSeqList.size()];
		
		for(int i = 0; i < orderSeqList.size(); i++){
			orderSeqArr[i] = String.valueOf(orderSeqList.get(i));
		}
		
		stockService.insertStock(orderSeqArr, saleDate);
		saleService.insertSale(newSaleSheetSeq, orderSeqList);
	}

	public SaleSheetDTO getLastSaleSheet(int customerSeq) {
		
		return saleSheetDao.getLastSaleSheet(customerSeq);
	}

	public HashMap<String, SaleSheetDTO> getSumSaleSheetMap(
			CustomerTypeDTO customerType) throws ParseException {
			HashMap<String, SaleSheetDTO> sumSaleSheetMap = new HashMap<String, SaleSheetDTO>();
			List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();
			List<SaleSheetDTO> saleSheetList = new ArrayList<SaleSheetDTO>();
			CustomerBalanceDTO lastCustomerBalance = new CustomerBalanceDTO();
			Double sumSaleGold = 0.0;
			int sumSalePrice = 0;
			Double lastCustomerBalanceGold = 0.0;
			int lastCustomerBalanceCash = 0;
			int index = 0;;
			
			customerList = customerService.getCustomerListByCustomerType(customerType);
			
			for(CustomerDTO customer : customerList){
				saleSheetList = getSaleSheetList(customer.getCustomerSeq());
				lastCustomerBalance = customerService.getLastCustomerBalance(customer.getCustomerSeq());
					
				if(lastCustomerBalance == null || saleSheetList == null || saleSheetList.size() < 0){
					
				}else{
					lastCustomerBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
					lastCustomerBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
					
					if(lastCustomerBalanceGold > 0 || lastCustomerBalanceCash > 0){
						for(SaleSheetDTO data : saleSheetList){
							
							sumSaleGold = sumSaleGold + data.getTotalGoldWeight();
							sumSalePrice = sumSalePrice + data.getTotalSalePrice();
							sumSaleGold = (double) Math.round(sumSaleGold*100)/100.0;
							
							if((sumSaleGold.equals(lastCustomerBalanceGold) && sumSalePrice == lastCustomerBalanceCash)){
								
								sumSaleSheetMap = setSumSaleSheetMap(index, saleSheetList, sumSaleSheetMap);
								
								break;
							}else if((sumSaleGold > lastCustomerBalanceGold && sumSalePrice > lastCustomerBalanceCash)){
								
								sumSaleSheetMap = setSumSaleSheetMap((index - 1), saleSheetList, sumSaleSheetMap);
								break;
							}
							++index;
						}
					}
				}
				index = 0;
				sumSaleGold = 0.0;
				sumSalePrice = 0;
				
			}
			
		return sumSaleSheetMap;
	}

	private HashMap<String, SaleSheetDTO> setSumSaleSheetMap(int selectedIndex,
			List<SaleSheetDTO> saleSheetList,
			HashMap<String, SaleSheetDTO> sumSaleSheetMap) throws ParseException {
		String saleSheetDateString = "";
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date saleSheetDate = null;
		SaleSheetDTO sumSaleSheetDtoFromMap = new SaleSheetDTO();
		
		for(int i = 0; i <= selectedIndex; i++){
			saleSheetDate = simpleDateFormat.parse(saleSheetList.get(i).getSaleSheetDate());
			calendar.setTime(saleSheetDate);
			if((calendar.get(Calendar.MONTH) + 1) < 10){
				saleSheetDateString = calendar.get(Calendar.YEAR) + "-0" + (calendar.get(Calendar.MONTH) + 1);
			}else{
				saleSheetDateString = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1);
			}
			
			if(sumSaleSheetMap.get(saleSheetDateString) == null){
				sumSaleSheetMap.put(saleSheetDateString, saleSheetList.get(i));
			}else{
				sumSaleSheetDtoFromMap = sumSaleSheetMap.get(saleSheetDateString);
				sumSaleSheetDtoFromMap.setSaleCash(sumSaleSheetDtoFromMap.getSaleCash() + saleSheetList.get(i).getSaleCash());
				sumSaleSheetDtoFromMap.setSaleGold(sumSaleSheetDtoFromMap.getSaleGold() + saleSheetList.get(i).getSaleGold());
				
				sumSaleSheetMap.put(saleSheetDateString, sumSaleSheetDtoFromMap);
			}
		}
		
		
		return sumSaleSheetMap;
	}

	public HashMap<String, List<SaleSheetDTO>> getSaleSheetListMap(
			CustomerTypeDTO customerType, String key) {
		HashMap<String, List<SaleSheetDTO>> saleSheetListMap = new HashMap<String, List<SaleSheetDTO>>();
		
		
		
		return saleSheetListMap;
	}

	public List<SaleSheetDTO> getSaleSheetListByMonth(int thisYear,
			int thisMonth, int branchSeq) {
			
		return saleSheetDao.getSaleSheetListByMonth(thisYear, thisMonth, branchSeq);
	}
}
