package com.jewsol.store.sale.controller;

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
import com.jewsol.store.customer.service.CustomerService;
import com.jewsol.store.goldPrice.bean.GoldPriceDTO;
import com.jewsol.store.goldPrice.service.GoldPriceService;
import com.jewsol.store.sale.bean.OrderForSaleDTO;
import com.jewsol.store.sale.bean.SaleTypeDTO;
import com.jewsol.store.sale.service.SaleService;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;
import com.jewsol.store.saleSheet.service.SaleSheetService;

@Controller
public class SaleController {
	@Autowired
	private GoldPriceService goldPriceService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private DateService dateService;
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private SaleSheetService saleSheetService; 
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/store/sale/saleForm.do")
	public ModelAndView saleForm(HttpSession session, ModelMap model ){
		ModelAndView mav = new ModelAndView();
		GoldPriceDTO goldPrice = goldPriceService.getGoldPrice();
		List<SaleTypeDTO> saleTypeList = saleService.getSaleTypeList();
		String saleDate = dateService.getToday();
		
		model.put("goldPrice", goldPrice);
		model.put("saleTypeList", saleTypeList);
		model.put("saleDate", saleDate);
		
		mav.addObject("saleTypeListForm", "/WEB-INF/view/store/sale/saleType/saleTypeListForm.jsp");
		utilSession.checkExistLogin(session, mav, "/store/sale/saleForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/sale/getSaleDataByCustomer.do")
	public ModelAndView getSaleDataByCustomer(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		List<SaleSheetDTO> saleSheetList = saleSheetService.getSaleSheetList(customerSeq);
		List<OrderForSaleDTO> orderList = saleService.getOrderListForSale(customerSeq);
		int saleTypeSeq = customerService.getSaleTypeSeq(customerSeq);
		
		model.put("saleSheetList", saleSheetList);
		model.put("orderList", orderList);
		model.put("saleTypeSeq", saleTypeSeq);
		
		mav.addObject("saleSheetListForm", "/WEB-INF/view/store/sale/saleSheetListForm.jsp");
		mav.addObject("orderListForm", "/WEB-INF/view/store/sale/orderListForm.jsp");
		mav.setViewName("/store/sale/saleDataByCustomerResult.jsp");
		return mav;
	}
	
	@RequestMapping("/store/sale/insertSale.do")
	public ModelAndView insertSale(
			@RequestParam("customerSeq") int customerSeq,
			@RequestParam("saleDate") String saleDate,
			@RequestParam("saleTypeSeq") int saleTypeSeq,
			@RequestParam("orderSeqArr") String orderSeqArr,
			@RequestParam("orderSaleWeightArr") String orderSaleWeightArr,
			@RequestParam("orderSalePriceArr") String orderSalePriceArr){
		ModelAndView mav = new ModelAndView();
		
		saleService.insertSale(customerSeq, saleDate, saleTypeSeq, orderSeqArr, orderSaleWeightArr, orderSalePriceArr);
		mav.setViewName("redirect:/store/sale/afterInsertSale.do?customerSeq="+customerSeq);
		return mav;
	}
	@RequestMapping("/store/sale/afterInsertSale.do")
	public ModelAndView afterInsertSale(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		List<SaleSheetDTO> saleSheetList = saleSheetService.getSaleSheetList(customerSeq);
		List<OrderForSaleDTO> orderList = saleService.getOrderListForSale(customerSeq);
		model.put("saleSheetList", saleSheetList);
		model.put("orderList", orderList);
		
		mav.addObject("saleSheetListForm", "/WEB-INF/view/store/sale/saleSheetListForm.jsp");
		mav.addObject("orderListForm", "/WEB-INF/view/store/sale/orderListForm.jsp");
		mav.setViewName("/store/sale/afterInsertSale.jsp");
		return mav;
	}
}
