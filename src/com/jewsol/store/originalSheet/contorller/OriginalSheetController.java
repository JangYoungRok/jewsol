package com.jewsol.store.originalSheet.contorller;

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
import com.jewsol.store.order.bean.ViewOrderDTO;
import com.jewsol.store.order.service.OrderService;
import com.jewsol.store.originalSheet.bean.OriginalSheetDTO;
import com.jewsol.store.originalSheet.service.OriginalSheetService;


@Controller
public class OriginalSheetController {
	@Autowired
	private OriginalSheetService originalSheetService;
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private DateService dateService;
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/store/originalSheet/checkOriginalSheetNumber.do")
	public ModelAndView checkOriginalSheetNumber(HttpSession session, ModelMap model,
			@RequestParam("originalSheetNumber") int originalSheetNumber){
		ModelAndView mav = new ModelAndView();
		int originalSheetState = originalSheetService.getOriginalSheetState(originalSheetNumber,session);
		
		model.put("originalSheetState", originalSheetState);
		mav.setViewName("/store/originalSheet/afterOriginalSheetNumber.jsp");
		
		return mav;
	}
	
	@RequestMapping("/store/originalSheet/printOriginalSheetForm.do")
	public ModelAndView printOriginalSheetForm(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String today = dateService.getToday();
		int branchSeq = utilSession.getBranchSeq(session);
		List<OriginalSheetDTO> originalSheetDtoList = originalSheetService.getOriginalSheetList(today, branchSeq);
		
		model.put("originalSheetDate" , today);
		model.put("originalSheetDtoList" , originalSheetDtoList);
		utilSession.checkExistLogin(session, mav, "/store/originalSheet/printOriginalSheetForm.jsp");
		mav.addObject("originalSheetList", "/WEB-INF/view/store/originalSheet/originalSheetList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/originalSheet/getPrintOriginalSheet.do")
	public ModelAndView getPrintOriginalSheet(HttpSession session ,ModelMap model,
			@RequestParam("originalSheetSeq") int originalSheetSeq){
		ModelAndView mav = new ModelAndView();
		OriginalSheetDTO originalSheetDto = originalSheetService.getOriginalSheet(originalSheetSeq);
		List<ViewOrderDTO> viewOrderDtoList = orderService.getTempOrderList(originalSheetSeq);
		String branchName = (String)session.getAttribute("sBranchName");
		
		model.put("branchName" , branchName);
		model.put("originalSheetDto" , originalSheetDto);
		model.put("viewOrderDtoList" , viewOrderDtoList);
		mav.setViewName("/store/originalSheet/printOriginalSheet.jsp");
		return mav;
	}
	
	@RequestMapping("/store/originalSheet/getOriginalSheetContent.do")
	public ModelAndView getOriginalSheetContent(ModelMap model,
			@RequestParam("originalSheetSeq") int originalSheetSeq){
		ModelAndView mav = new ModelAndView();
		List<ViewOrderDTO> viewOrderDtoList = orderService.getTempOrderList(originalSheetSeq);
		
		model.put("viewOrderDtoList" , viewOrderDtoList);
		mav.setViewName("/store/originalSheet/originalSheetContent.jsp");
		return mav;
	}
	
	@RequestMapping("/store/originalSheet/getOriginalSheetList.do")
	public ModelAndView getOriginalSheetList(ModelMap model, HttpSession session,
			@RequestParam("originalSheetDate") String originalSheetDate){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		List<OriginalSheetDTO> originalSheetDtoList = originalSheetService.getOriginalSheetList(originalSheetDate, branchSeq);
		
		model.put("originalSheetDtoList" , originalSheetDtoList);
		mav.setViewName("/store/originalSheet/originalSheetList.jsp");
		return mav;
	}
}
