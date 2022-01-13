package com.jewsol.store.part.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.part.bean.PartAttributeDTO;
import com.jewsol.store.part.bean.PartDTO;
import com.jewsol.store.part.service.PartService;

@Controller
public class PartController {
	@Autowired
	UtilSession utilSession;
	
	@Autowired
	PartService partService;

		@RequestMapping("/store/part/adminPartForm.do")
		public ModelAndView adminPartForm(HttpServletRequest request, HttpSession session, ModelMap model){
			ModelAndView mav = new ModelAndView();
			int branchSeq = utilSession.getBranchSeq(session);
			//공통 처리 부분
			partService.initAdminPartFrom(model, branchSeq);
			utilSession.checkExistLogin(session, mav, "/store/part/adminPartForm.jsp");
			
			return mav;
		}
		
		@RequestMapping("/store/part/getPartAttributeListAjax.do")
		public ModelAndView getPartAttributeListAjax(HttpServletRequest request, ModelMap model){
			ModelAndView mav = new ModelAndView();
			
			int typeMainSeq = Integer.parseInt(request.getParameter("typeMain"));
			List<PartAttributeDTO> partAttributeList = partService.getPartAttribute(typeMainSeq);
			
			model.put("partAttributeList", partAttributeList);
			
			mav.setViewName("/store/part/ajax/partAttributeList.jsp");
			return mav;
		}
		
		@RequestMapping("/store/part/insertPartAjax.do")
		public ModelAndView insertPartAjax(HttpServletRequest request, HttpSession session){
			ModelAndView mav = new ModelAndView();
			int branchSeq = utilSession.getBranchSeq(session);
			partService.insertPart(request, branchSeq);
			
			mav.setViewName("redirect:/store/part/getPartList.do");
			return mav;
		}

		@RequestMapping("/store/part/getPartList.do")
		public ModelAndView getPartList(ModelMap model, HttpSession session){
			ModelAndView mav = new ModelAndView();
			int branchSeq = utilSession.getBranchSeq(session);
			List<PartDTO> partList = partService.getPartList(branchSeq);
			
			model.put("partList", partList);
			mav.setViewName("/store/part/ajax/partList.jsp");
			return mav;
		}
		
		@RequestMapping("/store/part/notInUsePartAjax.do")
		public ModelAndView notInUsePartAjax(HttpServletRequest request){
			ModelAndView mav = new ModelAndView();
			
			int partSeq = Integer.parseInt(request.getParameter("partSeq"));
			
			partService.notInUsePart(partSeq);
			mav.setViewName("redirect:/store/part/getPartList.do");
			
			return mav;
		}
}
