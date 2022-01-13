package com.jewsol.store.add.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.add.bean.AddDTO;
import com.jewsol.store.add.service.AddService;


@Controller
public class AddController {
	
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private AddService addService;
	
	@RequestMapping("/store/add/adminAddForm.do")
	public ModelAndView adminAddForm(HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		//공통 처리 부분
		//공통 처리 부분
		addService.initAdminAddFrom(model, branchSeq);
		utilSession.checkExistLogin(session, mav, "/store/add/adminAddForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/add/insertAddAjax.do")
	public ModelAndView insertAddAjax(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		addService.insertAdd(request, branchSeq);
		mav.setViewName("redirect:/store/add/getAddList.do");
		
		return mav;
	}

	@RequestMapping("/store/add/getAddList.do")
	public ModelAndView getPartList(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		List<AddDTO> addList = addService.getAddList(branchSeq);
		
		model.put("addList", addList);
		mav.setViewName("/store/add/ajax/addList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/add/notInUseAddAjax.do")
	public ModelAndView notInUseAddAjax(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		int addSeq = Integer.parseInt(request.getParameter("addSeq"));
		addService.notInUseAdd(addSeq);
		mav.setViewName("redirect:/store/add/getAddList.do");
		return mav;
	}
}
