package com.jewsol.store.customer.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.customer.bean.CustomerBalanceDTO;
import com.jewsol.store.customer.bean.CustomerDTO;
import com.jewsol.store.customer.bean.CustomerTypeDTO;
import com.jewsol.store.customer.service.CustomerService;
import com.jewsol.store.payment.bean.PaymentDTO;
import com.jewsol.store.payment.service.PaymentService;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;
import com.jewsol.store.saleSheet.service.SaleSheetService;


@Controller
public class CustomerController {
	@Autowired
	private UtilSession utilSession;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SaleSheetService saleSheetService;
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping("/store/customer/adminCustomerForm.do")
	public ModelAndView adminCustomerForm(HttpServletRequest request, HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		//공통 처리 부분
		customerService.initAdminCustomerFrom(model, branchSeq);
		utilSession.checkExistLogin(session, mav, "/store/customer/adminCustomerForm.jsp");
		
		return mav;
		
	}
	
	@RequestMapping("/store/customer/checkOverLapCustomerNameAjax.do")
	public ModelAndView checkOverLapCustomerNameAjax(HttpServletRequest request, ModelMap model, HttpSession session){
	
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		int count = customerService.checkOverLapCustomerName(request, branchSeq);
		
		model.put("count",count);
		
		mav.setViewName("/store/customer/ajax/resultOverLapSuctomerNameAjaxCallBack.jsp");
		
		return mav;
	}
	@RequestMapping("/store/customer/insertCustomerAjax.do")
	public ModelAndView insertCustomerAjax(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		customerService.insertCustomer(request, session);
		
		mav.setViewName("redirect:/store/customer/getCustomerListAjaxCallBack.do");
		
		return mav;
		
	}
	                
	@RequestMapping("/store/customer/getCustomerListAjaxCallBack.do")
	public ModelAndView getCustomerListAjaxCallBack(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		List<CustomerDTO> customerList = customerService.getCustomerList(branchSeq);
		
		model.put("customerList", customerList);
		
		mav.setViewName("/store/customer/ajax/customerList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/customer/notInUseCustomerAjax.do")
	public ModelAndView notInUseCustomerAjax(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		int customerSeq = Integer.parseInt(request.getParameter("customerSeq"));
		
		customerService.notInUseCustomer(customerSeq);
		mav.setViewName("redirect:/store/customer/getCustomerListAjaxCallBack.do");
		
		return mav;
		
	}
	
	@RequestMapping("/store/customer/sendCustomerKeyword.do")
	public ModelAndView sendCustomerKeyword(ModelMap model, HttpSession session,
			@RequestParam("keyword") String keyword){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		List<CustomerDTO> customerList = customerService.getCustomerKeyWord(keyword, branchSeq);
		
		model.put("customerList", customerList);
		mav.setViewName("/store/customer/ajax/afterSendCustomerKeyWord.jsp");
		return mav;
	}
	
	@RequestMapping("/store/customer/inqueryCustomerBalanceForm.do")
	public ModelAndView inqueryCustomerBalanceForm(ModelMap model, HttpSession session) throws ParseException{
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		List<CustomerDTO> customerList = customerService.getCustomerList(branchSeq);
		List<CustomerBalanceDTO> customerBalanceList = new ArrayList<CustomerBalanceDTO>();
		List<SaleSheetDTO> lastSaleSheetList = new ArrayList<SaleSheetDTO>();
		List<PaymentDTO> lastPaymentList = new ArrayList<PaymentDTO>();
		List<SaleSheetDTO> lastSaleSheetBeforePaymentDateList = new ArrayList<SaleSheetDTO>();
		
		int customerSeq = 0;
		
		for(CustomerDTO customer : customerList){
			customerSeq = customer.getCustomerSeq();
			customerBalanceList.add(customerService.getLastCustomerBalance(customerSeq));
			lastSaleSheetList.add(saleSheetService.getLastSaleSheet(customerSeq));
			lastPaymentList.add(paymentService.getLastPayment(customerSeq));
			lastSaleSheetBeforePaymentDateList.add(paymentService.getLastSaleDateBeforePayment(customerSeq));
		}
		
		model.put("customerList", customerList);
		model.put("customerBalanceList", customerBalanceList);
		model.put("lastSaleSheetList", lastSaleSheetList);
		model.put("lastPaymentList", lastPaymentList);
		model.put("lastSaleSheetBeforePaymentDateList", lastSaleSheetBeforePaymentDateList);
		
		mav.addObject("customerBalanceListForm", "/WEB-INF/view/store/customer/customerBalanceListForm.jsp");
		utilSession.checkExistLogin(session, mav, "/store/customer/inqueryCustomerBalanceForm.jsp");
		
		return mav;
	}
	
	@RequestMapping("/store/customer/inqueryCustomerBalanceByCustomerTypeForm.do")
	public ModelAndView inqueryCustomerBalanceByCustomerTypeForm(ModelMap model, HttpSession session) throws ParseException{
		ModelAndView mav = new ModelAndView();
		String successView = "/store/customer/inqueryCustomerBalanceByCustomerTypeForm.jsp";
		int branchSeq = utilSession.getBranchSeq(session);
		
		List<List<CustomerDTO>> customerListByCustomerType = new ArrayList<List<CustomerDTO>>(); 
		List<List<SaleSheetDTO>> lastSaleSheetListByCustomerType = new ArrayList<List<SaleSheetDTO>>();
		List<List<PaymentDTO>> lastPaymentListByCustomerType = new ArrayList<List<PaymentDTO>>();
		List<List<SaleSheetDTO>> lastSaleSheetBeforePaymentDateListByCustomerType = new ArrayList<List<SaleSheetDTO>>();
		List<List<CustomerBalanceDTO>> customerBalanceListByCustomerType = new ArrayList<List<CustomerBalanceDTO>>();
		
		
		List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();
		List<SaleSheetDTO> lastSaleSheetList = new ArrayList<SaleSheetDTO>();
		List<PaymentDTO> lastPaymentList = new ArrayList<PaymentDTO>();
		List<SaleSheetDTO> lastSaleSheetBeforePaymentDateList = new ArrayList<SaleSheetDTO>();
		List<CustomerBalanceDTO> customerBalanceList = new ArrayList<CustomerBalanceDTO>();
		
		int customerSeq = 0;
		
		List<CustomerTypeDTO> customerTypeList = customerService.getCustomerTypeList(branchSeq);
		
		for(CustomerTypeDTO customerType : customerTypeList){
			customerList = customerService.getCustomerListByCustomerType(customerType);
			customerListByCustomerType.add(customerList);
			
		}
		
		for(List<CustomerDTO> customerListByCustomerTypeList : customerListByCustomerType){
			for(CustomerDTO customer : customerListByCustomerTypeList ){
				customerSeq = customer.getCustomerSeq();
				customerBalanceList.add(customerService.getLastCustomerBalance(customerSeq));
				lastSaleSheetList.add(saleSheetService.getLastSaleSheet(customerSeq));
				lastPaymentList.add(paymentService.getLastPayment(customerSeq));
				lastSaleSheetBeforePaymentDateList.add(paymentService.getLastSaleDateBeforePayment(customerSeq));
			}
			customerBalanceListByCustomerType.add(customerBalanceList);
			lastSaleSheetListByCustomerType.add(lastSaleSheetList);
			lastPaymentListByCustomerType.add(lastPaymentList);
			lastSaleSheetBeforePaymentDateListByCustomerType.add(lastSaleSheetBeforePaymentDateList);
			
			lastSaleSheetList = new ArrayList<SaleSheetDTO>();
			lastPaymentList = new ArrayList<PaymentDTO>();
			lastSaleSheetBeforePaymentDateList = new ArrayList<SaleSheetDTO>();
			customerBalanceList = new ArrayList<CustomerBalanceDTO>();
		}
		
		model.put("customerTypeList", customerTypeList);
		model.put("customerListByCustomerType", customerListByCustomerType);
		model.put("lastSaleSheetListByCustomerType", lastSaleSheetListByCustomerType);
		model.put("customerBalanceListByCustomerType", customerBalanceListByCustomerType);
		model.put("lastPaymentListByCustomerType", lastPaymentListByCustomerType);
		model.put("lastSaleSheetBeforePaymentDateListByCustomerType", lastSaleSheetBeforePaymentDateListByCustomerType);
		
		mav.addObject("customerBalanceListByCustomerTypeForm", "/WEB-INF/view/store/customer/customerBalanceListByCustomerTypeForm.jsp");
		utilSession.checkExistLogin(session, mav, successView);
		return mav;
	}
	
	@RequestMapping("/store/customer/inqueryCustomerBalanceMonthByCustomerTypeForm.do")
	public ModelAndView inqueryCustomerBalanceMonthByCustomerTypeForm(ModelMap model, HttpSession session) throws ParseException{
		ModelAndView mav = new ModelAndView();
		String successView = "/store/customer/inqueryCustomerBalanceMonthByCustomerTypeForm.jsp";
		int branchSeq = utilSession.getBranchSeq(session);
		
		List<HashMap<String, SaleSheetDTO>> sumSaleSheetMapByCustomerType = new ArrayList<HashMap<String, SaleSheetDTO>>();
		List<List<String>> sumSaleSheetMapSortedKeyByCustomerType = new ArrayList<List<String>>();
		List<HashMap<String, List<SaleSheetDTO>>> saleSheetListMapByCustomerType = new ArrayList<HashMap<String, List<SaleSheetDTO>>>();
		List<HashMap<String, List<CustomerDTO>>> customerListMapByCustomerType = new ArrayList<HashMap<String, List<CustomerDTO>>>();
		
		List<String> sumSaleSheetMapSortedKey = new ArrayList<String>();
		HashMap<String, SaleSheetDTO> sumSaleSheetMap = new HashMap<String, SaleSheetDTO>();
		HashMap<String, List<SaleSheetDTO>> saleSheetListMap = new HashMap<String, List<SaleSheetDTO>>();
		HashMap<String, List<CustomerDTO>> customerListMap = new HashMap<String, List<CustomerDTO>>();
		List<CustomerTypeDTO> customerTypeList = customerService.getCustomerTypeList(branchSeq);
		
		for(CustomerTypeDTO customerType : customerTypeList){
			sumSaleSheetMap = saleSheetService.getSumSaleSheetMap(customerType);
			sumSaleSheetMapByCustomerType.add(sumSaleSheetMap);
			Set<String> set = sumSaleSheetMap.keySet();
			Iterator<String> iterator = set.iterator();
			
			while(iterator.hasNext()){
				String key = (String)iterator.next();
				sumSaleSheetMapSortedKey.add(key);
				saleSheetListMap = saleSheetService.getSaleSheetListMap(customerType, key);
				
			}
			Collections.sort(sumSaleSheetMapSortedKey);
			sumSaleSheetMapSortedKeyByCustomerType.add(sumSaleSheetMapSortedKey);
			sumSaleSheetMapSortedKey = new ArrayList<String>();
		}
		
		model.put("customerTypeList", customerTypeList);
		model.put("sumSaleSheetMapSortedKeyByCustomerType", sumSaleSheetMapSortedKeyByCustomerType);
		model.put("sumSaleSheetMapByCustomerType", sumSaleSheetMapByCustomerType);
		
		
		mav.addObject("customerBalanceListMonthByCustomerTypeForm", "/WEB-INF/view/store/customer/customerBalanceListMonthByCustomerTypeForm.jsp");
		utilSession.checkExistLogin(session, mav, successView);
		
		return mav;
	}
}
