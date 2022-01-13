package com.jewsol.store.payment.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.date.DateService;
import com.jewsol.common.util.BasicUtil;
import com.jewsol.common.util.UtilSession;
import com.jewsol.store.customer.bean.CustomerBalanceDTO;
import com.jewsol.store.customer.bean.CustomerDTO;
import com.jewsol.store.customer.service.CustomerService;
import com.jewsol.store.goldPrice.bean.GoldPriceDTO;
import com.jewsol.store.goldPrice.service.GoldPriceService;
import com.jewsol.store.payment.bean.PaymentDTO;
import com.jewsol.store.payment.service.PaymentService;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;

@Controller
public class PaymentController {
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private DateService dateService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private GoldPriceService goldPriceService;
	@Autowired
	private BasicUtil basicUtil;
	
	@RequestMapping("/store/payment/paymentForm.do")
	public ModelAndView paymentForm(HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		String paymentDate = dateService.getToday();
		model.put("paymentDate", paymentDate);
		mav.addObject("paymentListForm","/WEB-INF/view/store/payment/paymentListForm.jsp");
		utilSession.checkExistLogin(session, mav, "/store/payment/paymentForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/payment/inqueryPaymentForm.do")
	public ModelAndView inqueryPaymentForm(HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		String paymentDate = dateService.getToday();
		model.put("paymentDate", paymentDate);
		mav.addObject("paymentListForm","/WEB-INF/view/store/payment/inquery/paymentListForm.jsp");
		utilSession.checkExistLogin(session, mav, "/store/payment/inquery/inqueryPaymentForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/payment/searchCustomer.do")
	public ModelAndView searchCustomer(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		List<PaymentDTO> paymentList = paymentService.getPaymentList(customerSeq);
		CustomerBalanceDTO lastCustomerBalance = customerService.getLastCustomerBalance(customerSeq);
		Double customerBalanceGold = 0.0;
		int customerBalanceCash = 0;
		if(lastCustomerBalance != null){
			customerBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
			customerBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
		}
		
		customerBalanceGold = basicUtil.doubleCut(customerBalanceGold);
		customerBalanceCash = basicUtil.intCut(customerBalanceCash);
		
		model.put("paymentList", paymentList);
		model.put("customerBalanceGold", customerBalanceGold);
		model.put("customerBalanceCash", customerBalanceCash);
		mav.addObject("paymentListForm","/WEB-INF/view/store/payment/paymentListForm.jsp");
		mav.setViewName("/store/payment/searchCustomerResult.jsp");
		return mav;
	}
	
	@RequestMapping("/store/payment/insertPayment.do")
	public ModelAndView insertPayment(ModelMap model,
			@RequestParam("paymentContent") String paymentContent,
			@RequestParam("paymentGold") Double paymentGold,
			@RequestParam("paymentCash") int paymentCash,
			@RequestParam("paymentDC") int paymentDC,
			@RequestParam("customerSeq") int customerSeq,
			@RequestParam("paymentDate") String paymentDate){
		ModelAndView mav = new ModelAndView();
		Double lastBalanceGold = 0.0;
		int lastBalanceCash = 0;
		GoldPriceDTO goldPrice = goldPriceService.getGoldPrice();
		int goldPriceSeq = goldPrice.getGoldPriceSeq();
		Double balanceGold = 0.0;
		int balanceCash = 0;
		
		CustomerBalanceDTO customerBalance = new CustomerBalanceDTO();
		PaymentDTO payment = new PaymentDTO();
		CustomerBalanceDTO lastCustomerBalance = customerService.getLastCustomerBalance(customerSeq);
		if(lastCustomerBalance == null){
			balanceGold = balanceGold - paymentGold;
			balanceCash = balanceCash - paymentCash - paymentDC;
		}else{
			lastBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
			lastBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
			
			balanceGold = lastBalanceGold - paymentGold;
			balanceCash = lastBalanceCash - paymentCash - paymentDC;
		}
		
		balanceGold = basicUtil.doubleCut(balanceGold);
		balanceCash = basicUtil.intCut(balanceCash);
		
		payment.setLastBalanceGold(lastBalanceGold);
		payment.setLastBalanceCash(lastBalanceCash);
		payment.setPaymentGold(paymentGold);
		payment.setPaymentCash(paymentCash);
		payment.setBalanceGold(balanceGold);
		payment.setBalanceCash(balanceCash);
		payment.setCustomerSeq(customerSeq);
		payment.setGoldPriceSeq(goldPriceSeq);
		payment.setPaymentDate(paymentDate);
		payment.setPaymentContent(paymentContent);
		payment.setPaymentDC(paymentDC);
		int paymentSeq = paymentService.insertPayment(payment);
		
		customerBalance.setCustomerSeq(customerSeq);
		customerBalance.setSaleSheetSeq(0);
		customerBalance.setPaymentSeq(paymentSeq);
		customerBalance.setCustomerBalanceGold(balanceGold);
		customerBalance.setCustomerBalanceCash(balanceCash);
		customerService.insertCustomerBalance(customerBalance);
		
		mav.setViewName("redirect:/store/payment/afterInsertPayment.do?customerSeq="+customerSeq);
		return mav;
	}
	
	@RequestMapping("/store/payment/afterInsertPayment.do")
	public ModelAndView afterInsertPayment(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		List<PaymentDTO> paymentList = paymentService.getPaymentList(customerSeq);
		CustomerBalanceDTO lastCustomerBalance = customerService.getLastCustomerBalance(customerSeq);
		
		Double customerBalanceGold = 0.0;
		int customerBalanceCash = 0;
		customerBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
		customerBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
		
		model.put("paymentList", paymentList);
		model.put("customerBalanceGold", customerBalanceGold);
		model.put("customerBalanceCash", customerBalanceCash);
		mav.addObject("paymentListForm","/WEB-INF/view/store/payment/paymentListForm.jsp");
		mav.setViewName("/store/payment/afterInsertPayment.jsp");
		return mav;
	}
	
	@RequestMapping("/store/payment/printPayment.do")
	public ModelAndView printPayment(ModelMap model,
			@RequestParam("paymentSeq") int paymentSeq){
		ModelAndView mav = new ModelAndView();
		PaymentDTO payment = paymentService.getPayment(paymentSeq);
		CustomerDTO customer = customerService.getCustomer(payment.getCustomerSeq());
		model.put("customer", customer);
		model.put("payment", payment);
		mav.setViewName("/store/payment/printPayment.jsp");
		return mav;
	}
	
	@RequestMapping("/store/payment/getLastSaleDateBeforePaymentTest.do")
	public void getLastSaleDateBeforePaymentTest(ModelMap model,
			@RequestParam("customerSeq") int customerSeq){
		SaleSheetDTO saleSheetDto = paymentService.getLastSaleDateBeforePayment(customerSeq);
		System.out.println(saleSheetDto.getSaleSheetDate() + saleSheetDto.getSaleGold() + saleSheetDto.getSaleCash());
	}
}
