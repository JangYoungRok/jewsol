package com.jewsol.store.stockLocation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.stockLocation.bean.StockLocationDTO;
import com.jewsol.store.stockLocation.service.StockLocationService;


@Controller
public class StockLocationController {
	
	@Autowired
	private StockLocationService stockLocationService;
	@Autowired
	private UtilSession utilSession;
	
	@RequestMapping("/store/stockLocation/adminStockLocationForm.do")
	public ModelAndView adminStockLocationForm(HttpServletRequest request, HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		//공통 처리 부분
		stockLocationService.initStockLocationFrom(model, branchSeq);
		utilSession.checkExistLogin(session, mav, "/store/stockLocation/adminStockLocationForm.jsp");
		
		return mav;
		
	}
	
	@RequestMapping("/store/stockLocation/insertStockLocationAjax.do")
	public ModelAndView insertStockLocationAjax(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		stockLocationService.insertStockLocation(request, session);
		
		mav.setViewName("redirect:/store/stockLocation/getStockLocationList.do");
		
		return mav;
		
	}

	@RequestMapping("/store/stockLocation/getStockLocationList.do")
	public ModelAndView getStockLocationList(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		List<StockLocationDTO> stockLocationList = stockLocationService.getStockLocationList(branchSeq);
		
		model.put("stockLocationList", stockLocationList);
		
		mav.setViewName("/store/stockLocation/ajax/stockLocationList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/stockLocation/getStockLocationOptionListAjax.do")
	public ModelAndView getStockLocationOptionListAjax(HttpServletRequest request, ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int stockLocationTypeSeq = Integer.parseInt(request.getParameter("stockLocationTypeSeq"));
		int branchSeq = utilSession.getBranchSeq(session);
		List<StockLocationDTO> stockLocationList = stockLocationService.getStockLocationListByStockLocationType(stockLocationTypeSeq, branchSeq);
		
		model.put("stockLocationList", stockLocationList);
		
		mav.setViewName("/store/stockLocation/ajax/stockLocationOptionList.jsp");
		return mav;
	}
	

	
	@RequestMapping("/store/stockLocation/checkOverLapStockLocationNameAjax.do")
	public ModelAndView checkOverLapStockLocationNameAjax(HttpServletRequest request, ModelMap model, HttpSession session ){
		ModelAndView mav = new ModelAndView();
		String stockLocationName = request.getParameter("stockLocationName");
		int branchSeq = utilSession.getBranchSeq(session);
		StockLocationDTO stockLocation = new StockLocationDTO();
		stockLocation.setStockLocationName(stockLocationName);
		stockLocation.setBranchSeq(branchSeq);
		int count = stockLocationService.checkOverLapStockLocationName(stockLocation);
		
		model.put("count",count);
		mav.setViewName("/store/stockLocation/ajax/resultOverLapStockLocationNameAjaxCallBack.jsp");
		return mav;
	}
	
	@RequestMapping("/store/stockLocation/notInUseStockLocationAjax.do")
	public ModelAndView notInUseStockLocationAjax(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		int stockLocationSeq = Integer.parseInt(request.getParameter("stockLocationSeq"));
		
		stockLocationService.notInUseStockLocation(stockLocationSeq);
		mav.setViewName("redirect:/store/stockLocation/getStockLocationList.do");
		
		return mav;
		
	}
	
	
}
