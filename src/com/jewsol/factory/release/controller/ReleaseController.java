package com.jewsol.factory.release.controller;

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
import com.jewsol.core.branch.bean.BranchDTO;
import com.jewsol.core.branch.service.BranchService;
import com.jewsol.factory.panel.bean.ViewOrderForPanelDTO;
import com.jewsol.factory.panel.service.PanelService;
import com.jewsol.factory.release.bean.ViewOrderForReleaseDTO;
import com.jewsol.factory.release.service.ReleaseService;

@Controller
public class ReleaseController {
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private DateService dateService;
	@Autowired
	private PanelService panelService;
	@Autowired
	private ReleaseService releaseService;
	@Autowired
	private BranchService branchService;
	
	
	@RequestMapping("/factory/release/checkReleaseForm.do")
	public ModelAndView checkReleaseForm( ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int panelNumber = 1;
		String orderDate = dateService.getToday();
		//List<ViewOrderForPanelDTO> orderForPanelList = panelService.getOrderListForPanel(orderDate, panelNumber);
		//int panelLength = panelService.getPanelLength(orderDate);
		int panelLength = 0;
		
		model.put("orderDate", orderDate);
		model.put("panelNumber", panelNumber);
		//model.put("orderForPanelList", orderForPanelList);
		model.put("panelLength", panelLength);
		//mav.addObject("orderForPanelDtoList", "/WEB-INF/view/factory/release/orderForReleaseDtoList.jsp");
		utilSession.checkExistLogin(session, mav, "/factory/release/checkReleaseForm.jsp");
		
		return mav;
		
	}
	
	@RequestMapping("/factory/release/getPanelLengthByOrderDate.do")
	public ModelAndView getPanelLengthByOrderDate(ModelMap model,
			@RequestParam("orderDate") String orderDate){
		ModelAndView mav = new ModelAndView();
		int panelLength = panelService.getPanelLength(orderDate);
		model.put("panelLength", panelLength);
		
		mav.setViewName("/factory/release/getPanelLengthByOrderDateResult.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/release/getOrderListForRelease.do")
	public ModelAndView getOrderListForRelease(ModelMap model,
			@RequestParam("orderDate") String orderDate,
			@RequestParam("panelNumber") int panelNumber){
		ModelAndView mav = new ModelAndView();
		List<ViewOrderForPanelDTO> orderForPanelList = panelService.getOrderListForPanel(orderDate, panelNumber);
		//int panelLength = panelService.getPanelLength(orderDate);
		
		//model.put("panelLength", panelLength);
		model.put("orderForPanelList", orderForPanelList);
		mav.addObject("orderForPanelDtoList", "/WEB-INF/view/factory/release/orderForReleaseDtoList.jsp");
		mav.setViewName("/factory/release/orderListForReleaseResult.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/release/checkRelease.do")
	public ModelAndView checkRelease(ModelMap model,
			@RequestParam("orderSeqArr") String orderSeqArr,
			@RequestParam("orderDate") String orderDate,
			@RequestParam("panelNumber") int panelNumber){
		ModelAndView mav = new ModelAndView();
		releaseService.checkRelease(orderSeqArr.split(","));
		
		mav.setViewName("redirect:/factory/release/afterCheckRelease.do?orderDate="+orderDate+"&panelNumber="+panelNumber);
		return mav;
	}
	
	@RequestMapping("/factory/release/afterCheckRelease.do")
	public ModelAndView afterCheckRelease(ModelMap model,
			@RequestParam("orderDate") String orderDate,
			@RequestParam("panelNumber") int panelNumber){
		ModelAndView mav = new ModelAndView();
		List<ViewOrderForPanelDTO> orderForPanelList = panelService.getOrderListForPanel(orderDate, panelNumber);
		model.put("orderForPanelList", orderForPanelList);	
		mav.setViewName("/factory/release/orderForReleaseDtoList.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/release/insertReleaseForm.do")
	public ModelAndView insertReleaseForm(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		List<BranchDTO> branchList = branchService.getBranchList();
		model.put("branchList", branchList);
		mav.addObject("branchListJsp", "/WEB-INF/view/core/branch/branchList.jsp");
		utilSession.checkExistLogin(session, mav, "/factory/release/insert/insertReleaseForm.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/release/getOrderListByBranchNameState3.do")
	public ModelAndView getOrderListByBranchNameState3(ModelMap model,
			@RequestParam("branchName") String branchName){
		ModelAndView mav = new ModelAndView();
		
		List<ViewOrderForReleaseDTO> orderList = releaseService.getOrderListByBranchNameState3(branchName);
		model.put("orderList", orderList);
		mav.setViewName("/factory/release/insert/orderListByBranchNameState3Result.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/release/insertRelease")
	public ModelAndView insertRelease(ModelMap model,
			@RequestParam("orderSeqArr") String orderSeqArr,
			@RequestParam("orderWeightArr") String orderWeightArr,
			@RequestParam("branchName") String branchName){
		ModelAndView mav = new ModelAndView();
		releaseService.insertRelease(orderSeqArr, orderWeightArr);
		mav.setViewName("redirect:/factory/release/afterInsertRelease.do?branchName="+branchName);
		return mav;
	}
	
	@RequestMapping("/factory/release/afterInsertRelease.do")
	public ModelAndView afterInsertRelease(ModelMap model,
			@RequestParam("branchName") String branchName){
		ModelAndView mav = new ModelAndView();
		List<ViewOrderForReleaseDTO> orderList = releaseService.getOrderListByBranchNameState3(branchName);
		model.put("orderList",orderList);
		mav.setViewName("/factory/release/insert/orderListByBranchNameState3Result.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/release/cancleReleaseForm.do")
	public ModelAndView cancleReleaseForm(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String successView = "/factory/release/cancleReleaseForm.jsp";
		List<ViewOrderForReleaseDTO> orderList = releaseService.getOrderListState3();
		mav.addObject("orderListForm", "/WEB-INF/view/factory/release/orderListState3.jsp");
		model.put("orderList", orderList);
		utilSession.checkExistLogin(session, mav, successView);
		return mav;
	}
	
	@RequestMapping("/factory/release/cancleRelease.do")
	public ModelAndView cancleRelease(
			@RequestParam("orderSeqArr") String orderSeqArr){
		ModelAndView mav = new ModelAndView();
		releaseService.cancleRelease(orderSeqArr);
		mav.setViewName("redirect:/factory/release/afterCancleRelease.do");
		return mav;
	}
	
	@RequestMapping("/factory/release/afterCancleRelease.do")
	public ModelAndView afterCancleRelease(ModelMap model){
		ModelAndView mav = new ModelAndView();
		List<ViewOrderForReleaseDTO> orderList = releaseService.getOrderListState3();
		model.put("orderList", orderList);
		mav.setViewName("/factory/release/orderListState3.jsp");
		return mav;
	}
}
