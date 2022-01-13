package com.jewsol.factory.factoryOrder.controller;

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
import com.jewsol.factory.factoryOrder.bean.InqueryFactoryOrderDTO;
import com.jewsol.factory.factoryOrder.service.FactoryOrderService;
import com.jewsol.factory.product.service.ProductService;

@Controller
public class FacrotyOrderController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private DateService dateService;
	@Autowired
	private FactoryOrderService factoryOrderService;
	
	@RequestMapping("/factory/factoryOrder/closeOrderForm.do")
	public ModelAndView closeOrderForm(HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		String today = dateService.getToday();
		String closeOrder = "F";
		int orderDateState = factoryOrderService.checkOrderDate(today);
		if(orderDateState == 0){
			factoryOrderService.insertOrderDate(today);
		}else if(orderDateState == 1){
			closeOrder = factoryOrderService.getOrderClose(today);
		}
		model.put("orderDate", today);
		model.put("closeOrder", closeOrder);
		utilSession.checkExistLogin(session, mav, "/factory/factoryOrder/closeOrderForm.jsp");
		return mav;
		
	}
	
	@RequestMapping("/factory/factoryOrder/checkCloseOrder.do")
	public ModelAndView checkCloseOrder( ModelMap model,
			@RequestParam("orderDate") String orderDate){
		ModelAndView mav = new ModelAndView();
		String closeOrder = factoryOrderService.getOrderClose(orderDate);
		closeOrder = (closeOrder == null)? "T": closeOrder;
		model.put("closeOrder", closeOrder);
		mav.setViewName("/factory/factoryOrder/checkCloseOrder.jsp");
		return mav;
		
	}
	
	@RequestMapping("/factory/factoryOrder/closeOrder.do")
	public ModelAndView closeOrder( ModelMap model,
			@RequestParam("orderDate") String orderDate){
		ModelAndView mav = new ModelAndView();
		factoryOrderService.closeOrder(orderDate);
		String closeOrder = factoryOrderService.getOrderClose(orderDate);
		model.put("closeOrder", closeOrder);
		mav.setViewName("/factory/factoryOrder/closeOrder.jsp");
		return mav;
		
	}
	@RequestMapping("/factory/factoryOrder/inqueryFactoryOrderForm.do")
	public ModelAndView inqueryFacrotyOrderForm(HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		utilSession.checkExistLogin(session, mav, "/factory/factoryOrder/inqueryFactoryOrderForm.jsp");
		return mav;
		
	}
	
	@RequestMapping("/factory/factoryOrder/getInqueryFactoryOrder.do")
	public ModelAndView getFactoryInqueryOrder(ModelMap model,
			@RequestParam("productCode") String productCode){
		ModelAndView mav = new ModelAndView();
		List<InqueryFactoryOrderDTO> inqueryOrderList = factoryOrderService.getFactoryInqueryOrder(productCode);
		model.put("inqueryOrderList", inqueryOrderList);
		mav.setViewName("/factory/factoryOrder/inqueryFactoryOrderList.jsp");
		return mav;
		
	}
}
