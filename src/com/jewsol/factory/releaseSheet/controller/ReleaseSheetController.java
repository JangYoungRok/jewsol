package com.jewsol.factory.releaseSheet.controller;

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
import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.bean.ViewOrderForReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.service.ReleaseSheetService;

@Controller
public class ReleaseSheetController {
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private DateService dateService;
	@Autowired
	private ReleaseSheetService releaseSheetService;
	@Autowired
	private BranchService branchService;
	
	@RequestMapping("/factory/releaseSheet/printReleaseSheetForm.do")
	public ModelAndView printReleaseSheetFrom(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int orderState = 4;
		String branchName = "DN";
		String releaseSheetDate = dateService.getToday();
		List<ReleaseSheetDTO> releaseSheetList = releaseSheetService.getReleaseSheetList(releaseSheetDate);
		List<ViewOrderForReleaseSheetDTO> orderList = releaseSheetService.getOrderListForReleaseSheetList(branchName, orderState);
		List<BranchDTO> branchList = branchService.getBranchList();
		int releaseSheetNumber = releaseSheetService.getReleaseSheetNumber(branchName, releaseSheetDate);
		model.put("branchList", branchList);
		model.put("releaseSheetList", releaseSheetList);
		model.put("orderList", orderList);
		model.put("releaseSheetDate", releaseSheetDate);
		model.put("releaseSheetNumber", releaseSheetNumber);
		mav.addObject("branchListForm", "/WEB-INF/view/core/branch/branchList.jsp");
		mav.addObject("orderListForm", "/WEB-INF/view/factory/releaseSheet/orderListForReleaseSheetForm.jsp");
		mav.addObject("releaseSheetListForm", "/WEB-INF/view/factory/releaseSheet/releaseSheetListForm.jsp");
		utilSession.checkExistLogin(session, mav, "/factory/releaseSheet/printReleaseSheetForm.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/releaseSheet/printReleaseSheet.do")
	public ModelAndView printReleaseSheet(ModelMap model,
			@RequestParam("releaseSheetSeq") int releaseSheetSeq){
		ModelAndView mav = new ModelAndView();
		String url = "";
		ReleaseSheetDTO releaseSheet = releaseSheetService.getReleaseSheet(releaseSheetSeq);
		List<ViewOrderForReleaseSheetDTO> orderList = null;
		if(releaseSheet.getReleaseSheetType() == 1){
			url = "/factory/releaseSheet/print/printReleaseSheetTypeOrder.jsp";
			orderList = releaseSheetService.getOrderListForPrintReleaseSheetList(releaseSheetSeq);
			model.put("orderList", orderList);
		}else{
			url = "/factory/releaseSheet/print/printReleaseSheetTypeBeside.jsp";
		}
		model.put("releaseSheet", releaseSheet);
		mav.setViewName(url);
		return mav;
	}
	
	@RequestMapping("/factory/releaseSheet/getOrderListByBranch.do")
	public ModelAndView getOrderListByBranch(ModelMap model,
			@RequestParam("branchName") String branchName){
		ModelAndView mav = new ModelAndView();
		int orderState = 4;
		List<ViewOrderForReleaseSheetDTO> orderList = releaseSheetService.getOrderListForReleaseSheetList(branchName, orderState);
		model.put("orderList", orderList);
		mav.setViewName("/factory/releaseSheet/orderListForReleaseSheetForm.jsp");
		return mav;
	}
	
	@RequestMapping("/factory/releaseSheet/insertReleaseSheet.do")
	public ModelAndView insertReleaseSheet(
			@RequestParam("orderSeqArr") String orderSeqArr,
			@RequestParam("branchName") String branchName,
			@RequestParam("releaseDate") String releaseDate){
		ModelAndView mav = new ModelAndView();
		releaseSheetService.insertReleaseSheet(orderSeqArr, branchName, releaseDate);
		
		mav.setViewName("redirect:/factory/releaseSheet/afterInsertReleaseSheet.do?branchName="+branchName+"&releaseDate="+releaseDate);
		return mav;
	}
	
	@RequestMapping("/factory/releaseSheet/afterInsertReleaseSheet.do")
	public ModelAndView afterInsertReleaseSheet(ModelMap model,
			@RequestParam("branchName") String branchName,
			@RequestParam("releaseDate") String releaseSheetDate){
		ModelAndView mav = new ModelAndView();
		int orderState = 4;
		int releaseSheetNumber = releaseSheetService.getReleaseSheetNumber(branchName, releaseSheetDate);
		List<ReleaseSheetDTO> releaseSheetList = releaseSheetService.getReleaseSheetList(releaseSheetDate);
		List<ViewOrderForReleaseSheetDTO> orderList  = releaseSheetService.getOrderListForReleaseSheetList(branchName, orderState);
		model.put("releaseSheetNumber", releaseSheetNumber);
		model.put("releaseSheetList", releaseSheetList);
		model.put("orderList", orderList);
		
		mav.addObject("releaseSheetListForm", "/WEB-INF/view/factory/releaseSheet/releaseSheetListForm.jsp");
		mav.addObject("orderListForm", "/WEB-INF/view/factory/releaseSheet/orderListForReleaseSheetForm.jsp");
		mav.setViewName("/factory/releaseSheet/afterInsertReleaseSheet.jsp");
		return mav;	
	}
}
