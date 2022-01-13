package com.jewsol.store.stone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.service.StoneService;

@Controller
public class StoneController {
	@Autowired
	UtilSession utilSession;
	@Autowired
	StoneService stoneService;
	
	@RequestMapping("/store/stone/adminStoneForm.do")
	public ModelAndView adminStoneForm(HttpServletRequest request, HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		stoneService.initAdminStoneFrom(model, branchSeq);
		utilSession.checkExistLogin(session, mav, "/store/stone/adminStoneForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/stone/insertStoneAjax.do")
	public ModelAndView insertStoneAjax(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		StoneDTO stoneDto = new StoneDTO();
		//parameter 처리
		stoneDto.setBranchSeq(branchSeq);
		stoneDto.setStoneName(request.getParameter("stoneName"));
		stoneDto.setStoneCost(Integer.parseInt(request.getParameter("stoneCost")));
		stoneDto.setStonePrice(Integer.parseInt(request.getParameter("stonePrice")));
		stoneDto.setStoneSizeSeq(Integer.parseInt(request.getParameter("stoneSize")));
		
		stoneService.insertStone(stoneDto);
		mav.setViewName("redirect:/store/stone/refreshStoneList.do");
		return mav;
	}
	
	@RequestMapping("/store/stone/notInUseStoneAjax.do")
	public ModelAndView notInUseStoneAjax(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		int stoneSeq = Integer.parseInt(request.getParameter("stoneSeq"));
		
		stoneService.notInUseStone(stoneSeq);
		mav.setViewName("redirect:/store/stone/refreshStoneList.do");
		return mav;
	}
	@RequestMapping("/store/stone/refreshStoneList.do")
	public ModelAndView refreshStoneList(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		List<StoneDTO> stoneList = stoneService.getStoneList(branchSeq);
		
		model.put("stoneList", stoneList);
		mav.setViewName("/store/stone/ajax/refreshStoneList.jsp");
		return mav;
	}
}
