package com.jewsol.store.saleSheet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.date.DateService;
import com.jewsol.common.util.UtilSession;
import com.jewsol.store.customer.bean.CustomerBalanceDTO;
import com.jewsol.store.customer.bean.CustomerDTO;
import com.jewsol.store.customer.service.CustomerService;
import com.jewsol.store.goldPrice.bean.GoldPriceDTO;
import com.jewsol.store.goldPrice.service.GoldPriceService;
import com.jewsol.store.saleSheet.bean.OrderForSaleSheetDTO;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;
import com.jewsol.store.saleSheet.service.SaleSheetService;

@Controller
public class SaleSheetController {
	@Autowired
	private SaleSheetService saleSheetService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DateService dateService;
	@Autowired
	private GoldPriceService goldPriceService;
	@Autowired
	private UtilSession utilSession;
	
	@RequestMapping("/store/saleSheet/printSaleSheet.do")
	public ModelAndView printSaleSheet(ModelMap model,
			@RequestParam("saleSheetSeq") int saleSheetSeq){
		ModelAndView mav = new ModelAndView();
		SaleSheetDTO saleSheet = saleSheetService.getSaleSheet(saleSheetSeq);
		List<OrderForSaleSheetDTO> orderList = saleSheetService.getOrderListForSaleSheet(saleSheetSeq);
		CustomerDTO customer = customerService.getCustomer(saleSheet.getCustomerSeq());
		model.put("customer", customer);
		model.put("saleSheet", saleSheet);
		model.put("orderList", orderList);
		mav.setViewName("/store/saleSheet/printSaleSheet.jsp");
		return mav;
	}
	
	@RequestMapping("/store/saleSheet/insertSaleSheetForm.do")
	public ModelAndView insertSaleSheetForm(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String saleSheetDate = dateService.getToday();
		model.put("saleSheetDate", saleSheetDate);
		utilSession.checkExistLogin(session, mav, "/store/saleSheet/insertSaleSheetForm.jsp");
		return mav;
	}
	
	
	@RequestMapping("/store/saleSheet/insertSaleSheet.do")
	public ModelAndView insertSaleSheet(ModelMap model,
			@RequestParam("saleGold") Double saleGold,
			@RequestParam("saleCash") int saleCash,
			@RequestParam("customerSeq") int customerSeq,
			@RequestParam("saleSheetDate") String saleSheetDate){
		ModelAndView mav = new ModelAndView();
		int lastSaleSheetNumber = saleSheetService.getLastSaleSheetNumber(saleSheetDate);
		lastSaleSheetNumber = lastSaleSheetNumber + 1;
		int saleTypeSeq = 1;
		GoldPriceDTO goldPrice = goldPriceService.getGoldPrice();
		int goldPriceSeq = goldPrice.getGoldPriceSeq();
		
		if(saleGold > 0.0 || saleCash > 0){
			SaleSheetDTO saleSheet = new SaleSheetDTO();
			saleSheet.setCustomerSeq(customerSeq);
			saleSheet.setGoldPriceSeq(goldPriceSeq);
			saleSheet.setSaleTypeSeq(saleTypeSeq);
			saleSheet.setSaleSheetDate(saleSheetDate);
			saleSheet.setSaleSheetNumber(lastSaleSheetNumber);
			saleSheet.setTotal14Weight(0.0);
			saleSheet.setTotal18Weight(0.0);
			saleSheet.setTotal10Weight(0.0);
			saleSheet.setTotalGoldWeight(saleGold);
			saleSheet.setTotalSalePrice(saleCash);
			saleSheetService.insertSaleSheet(saleSheet, goldPrice);
		}
		
		mav.setViewName("redirect:/store/saleSheet/afterInsertSaleSheet.do?customerSeq="+customerSeq);
		return mav;
	}
	
	@RequestMapping("/store/saleSheet/afterInsertSaleSheet.do")
	public ModelAndView afterInsertSaleSheet(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		CustomerBalanceDTO lastCustomerBalance = customerService.getLastCustomerBalance(customerSeq);
		
		Double lastBalanceGold = 0.0;
		int lastBalanceCash = 0;
		lastBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
		lastBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
		
		model.put("lastBalanceGold", lastBalanceGold);
		model.put("lastBalanceCash", lastBalanceCash);
		mav.setViewName("/store/saleSheet/afterInsertSaleSheet.jsp");
		return mav;
	}
	
	@RequestMapping("/store/saleSheet/searchCustomer.do")
	public ModelAndView searchCustomer(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		CustomerBalanceDTO lastCustomerBalance = customerService.getLastCustomerBalance(customerSeq);
		
		Double lastBalanceGold = 0.0;
		int lastBalanceCash = 0;
		if(lastCustomerBalance != null){
			lastBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
			lastBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
		}
		model.put("lastBalanceGold", lastBalanceGold);
		model.put("lastBalanceCash", lastBalanceCash);
		mav.setViewName("/store/saleSheet/searchCustomerResult.jsp");
		return mav;
	}
	
	@RequestMapping("/store/saleSheet/cancleSaleSheetForm.do")
	public ModelAndView cancleSaleSheetFrom(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String successView = "/store/saleSheet/cancle/cancleSaleSheetForm.jsp";
		utilSession.checkExistLogin(session, mav, successView);
		return mav;
	}
	
	@RequestMapping("/store/saleSheet/getSaleSheetListByCustomer.do")
	public ModelAndView getSaleSheetListByCustomer(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		List<SaleSheetDTO> saleSheetList = saleSheetService.getSaleSheetList(customerSeq);
		model.put("saleSheetList", saleSheetList);
		mav.setViewName("/store/saleSheet/cancle/saleSheetListByCustomerResult.jsp");
		return mav;
	}
	
	@RequestMapping("/store/saleSheet/getSaleSheetContent.do")
	public ModelAndView getSaleSheetContent(ModelMap model,
			@RequestParam("saleSheetSeq") int saleSheetSeq){
		ModelAndView mav = new ModelAndView();
		SaleSheetDTO saleSheet = saleSheetService.getSaleSheet(saleSheetSeq);
		List<OrderForSaleSheetDTO> orderList = saleSheetService.getOrderListForSaleSheet(saleSheetSeq);
		CustomerDTO customer = customerService.getCustomer(saleSheet.getCustomerSeq());
		model.put("customer", customer);
		model.put("saleSheet", saleSheet);
		model.put("orderList", orderList);
		mav.setViewName("/store/saleSheet/cancle/saleSheetContent.jsp");
		return mav;
	}
	
	@RequestMapping("/store/saleSheet/cancleSaleSheet.do")
	public ModelAndView cancleSaleSheet(
			@RequestParam("saleSheetSeq") int saleSheetSeq,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		saleSheetService.cancleSaleSheet(saleSheetSeq, customerSeq);
		mav.setViewName("redirect:/store/saleSheet/afterCancleSaleSheet.do?customerSeq="+customerSeq);
		return mav;
	}
	
	@RequestMapping("/store/saleSheet/afterCancleSaleSheet.do")
	public ModelAndView afterCancleSaleSheet(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		List<SaleSheetDTO> saleSheetList = saleSheetService.getSaleSheetList(customerSeq);
		model.put("saleSheetList", saleSheetList);
		mav.setViewName("/store/saleSheet/cancle/afterCancleSaleSheet.jsp");
		return mav;
	}
	
}
