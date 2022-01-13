package com.jewsol.store.goldPrice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.goldPrice.service.GoldPriceService;

@Controller
public class GoldPriceController {
	@Autowired
	UtilSession utilSession;
	@Autowired
	GoldPriceService goldPriceService;
	
	@RequestMapping("/store/goldPrice/adminGoldPriceForm.do")
	public ModelAndView adminPartForm(HttpServletRequest request, HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		goldPriceService.initAdminPartFrom(model);
		utilSession.checkExistLogin(session, mav, "/store/goldPrice/adminGoldPriceForm.jsp");
		
		return mav;
	}
}
