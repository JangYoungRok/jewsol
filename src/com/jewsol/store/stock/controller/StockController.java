package com.jewsol.store.stock.controller;

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
import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.bean.ViewOrderForReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.service.ReleaseSheetService;
import com.jewsol.store.stock.service.StockService;

@Controller
public class StockController {
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private DateService dateService;
	@Autowired
	private ReleaseSheetService releaseSheetService;
	@Autowired
	private StockService stockService;
	
	@RequestMapping("/store/stock/toStockForm.do")
	public ModelAndView toStockForm(HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		String toStockDate = dateService.getToday();
		int branchSeq = utilSession.getBranchSeq(session);
		List<ReleaseSheetDTO> releaseSheetList = releaseSheetService.getUnStockedReleaseSheetList(branchSeq);
		mav.addObject("releaseSheetListForm", "/WEB-INF/view/store/stock/releaseSheetListForm.jsp");
		model.put("releaseSheetList", releaseSheetList);
		model.put("toStockDate", toStockDate);
		utilSession.checkExistLogin(session, mav, "/store/stock/toStockForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/stock/getOrderListByReleaseSheetSeq")
	public ModelAndView getOrderListByReleaseSheetSeq(ModelMap model,
			@RequestParam("releaseSheetSeq") int releaseSheetSeq){
		ModelAndView mav = new ModelAndView();
		List<ViewOrderForReleaseSheetDTO> orderList = releaseSheetService.getOrderList(releaseSheetSeq);
		model.put("orderList", orderList);
		mav.setViewName("/factory/releaseSheet/orderListForReleaseSheetForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/stock/toStock.do")
	public ModelAndView toStock(ModelMap model, HttpSession session,
			@RequestParam("orderSeqArr") String orderSeqArr,
			@RequestParam("toStockDate") String toStockDate,
			@RequestParam("unCheckedOrderSeqArr") String unCheckedOrderSeqArr,
			@RequestParam("releaseSheetSeq") int releaseSheetSeq){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		stockService.toStock(orderSeqArr, unCheckedOrderSeqArr, toStockDate, branchSeq, releaseSheetSeq);
		
		mav.setViewName("redirect:/store/stock/afterToStock.do?branchSeq="+branchSeq);
		return mav;
	}
	
	@RequestMapping("/store/stock/afterToStock")
	public ModelAndView afterToStock(ModelMap model,
			@RequestParam("branchSeq") int branchSeq){
		ModelAndView mav = new ModelAndView();
		List<ReleaseSheetDTO> releaseSheetList = releaseSheetService.getUnStockedReleaseSheetList(branchSeq);
		model.put("releaseSheetList", releaseSheetList);
		mav.setViewName("/store/stock/releaseSheetListForm.jsp");
		return mav;
	}

}
