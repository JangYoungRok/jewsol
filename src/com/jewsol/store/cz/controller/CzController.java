package com.jewsol.store.cz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.service.CzService;

@Controller
public class CzController {
	@Autowired
	UtilSession utilSession;
	@Autowired
	CzService czService;

		@RequestMapping("/store/cz/adminCzForm.do")
		public ModelAndView adminCzForm(HttpServletRequest request, HttpSession session, ModelMap model){
			ModelAndView mav = new ModelAndView();
			int branchSeq = utilSession.getBranchSeq(session);
			czService.initAdminCzFrom(model, branchSeq);
			utilSession.checkExistLogin(session, mav, "/store/cz/adminCzForm.jsp");
			
			return mav;
			
		}
		
		@RequestMapping("/store/cz/insertCzAjax.do")
		public ModelAndView insertCzAjax(HttpServletRequest request, HttpSession session){
			ModelAndView mav = new ModelAndView();
			int branchSeq = utilSession.getBranchSeq(session);
			CzDTO czDto = new CzDTO();
			//parameter 처리
			czDto.setBranchSeq(branchSeq);
			czDto.setCzName(request.getParameter("czName"));
			czDto.setCzLabor(Integer.parseInt(request.getParameter("czLabor")));
			czDto.setCzPrice(Integer.parseInt(request.getParameter("czPrice")));
			czDto.setCzSizeSeq(Integer.parseInt(request.getParameter("czSize")));
			
			czService.insertCz(czDto);
			mav.setViewName("redirect:/store/cz/refreshCzList.do");
			
			return mav;
			
		}
		
		@RequestMapping("/store/cz/notInUseCzAjax.do")
		public ModelAndView notInUseCzAjax(HttpServletRequest request){
			ModelAndView mav = new ModelAndView();
			int czSeq = Integer.parseInt(request.getParameter("czSeq"));
			
			czService.notInUseCz(czSeq);
			mav.setViewName("redirect:/store/cz/refreshCzList.do");
			
			return mav;
			
		}
		@RequestMapping("/store/cz/refreshCzList.do")
		public ModelAndView refreshCzList(ModelMap model, HttpSession session){
			ModelAndView mav = new ModelAndView();
			int branchSeq = utilSession.getBranchSeq(session);
			List<CzDTO> czList = czService.getCzList(branchSeq);
			model.put("czList", czList);
			mav.setViewName("/store/cz/refreshCzList.jsp");
			return mav;
		}
		
		@RequestMapping("/store/cz/updateCz.do")
		public ModelAndView updateCz(
				@RequestParam("czSeq") int czSeq,
				@RequestParam("czName") String czName,
				@RequestParam("czLabor") int czLabor,
				@RequestParam("czPrice") int czPrice){
			ModelAndView mav = new ModelAndView();
			CzDTO cz = new CzDTO();
			cz.setCzSeq(czSeq);
			cz.setCzLabor(czLabor);
			cz.setCzName(czName);
			cz.setCzPrice(czPrice);
			czService.updateCz(cz);
			mav.setViewName("redirect:/store/cz/refreshCzList.do");
			return mav;
		}
}
