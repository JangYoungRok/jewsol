package com.jewsol.factory.panel.controller;

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
import com.jewsol.factory.panel.bean.PanelDTO;
import com.jewsol.factory.panel.bean.ViewOrderForStickerDTO;
import com.jewsol.factory.panel.service.PanelService;

@Controller
public class PanelController {
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private DateService dateService;
	@Autowired
	private PanelService panelService;
	
	
	@RequestMapping("/factory/panel/printPanelForm.do")
	public ModelAndView printPanelForm(HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		String date = dateService.getToday();
		//List<PanelDTO> panelDtoList = panelService.getPanelList(date);
		
		model.put("orderDate", date);
		//model.put("panelDtoList", panelDtoList);
		//mav.addObject("panelList", "/WEB-INF/view/factory/panel/orderForPanelDtoList.jsp");
		utilSession.checkExistLogin(session, mav, "/factory/panel/printPanelForm.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/panel/getPrintPanel.do")
	public ModelAndView getPrintPanel(ModelMap model,
			@RequestParam("orderDate") String orderDate){
		ModelAndView mav = new ModelAndView();
		List<PanelDTO> panelDtoList = panelService.getPanelList(orderDate);
		
		model.put("orderDate", orderDate);
		model.put("panelDtoList", panelDtoList);
		mav.setViewName("/factory/panel/printPanel.jsp");
		return mav;
		
	}
	
	@RequestMapping("/factory/panel/getPrintSticker.do")
	public ModelAndView getPrintSticker(ModelMap model,
			@RequestParam("orderDate") String orderDate){
		ModelAndView mav = new ModelAndView();
		List<ViewOrderForStickerDTO> viewOrderForStickerList = panelService.getOrderForStickerList(orderDate);
		model.put("orderDate", orderDate);
		model.put("orderList", viewOrderForStickerList);
		mav.setViewName("/factory/panel/printSticker.jsp");
		return mav;
		
	}
	
	@RequestMapping("/factory/panel/checkOrderDate.do")
	public ModelAndView checkOrderDate(ModelMap model,
			@RequestParam("orderDate") String orderDate){
		ModelAndView mav = new ModelAndView();
		
		String orderClose = panelService.getOrderClose(orderDate);
		model.put("orderClose", orderClose);
		
		mav.setViewName("/factory/panel/checkOrderDateResult.jsp");
		return mav;
		
	}
	
	
	@RequestMapping("/factory/panel/getOrderForPanelByOrderDate.do")
	public ModelAndView getOrderForPanelByOrderDate(ModelMap model,
			@RequestParam("orderDate") String orderDate){
		ModelAndView mav = new ModelAndView();
		List<PanelDTO> panelDtoList = panelService.getPanelList(orderDate);
		
		model.put("panelDtoList", panelDtoList);
		mav.setViewName("/factory/panel/orderForPanelDtoList.jsp");
		return mav;
		
	}
}
