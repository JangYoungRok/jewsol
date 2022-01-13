package com.jewsol.core.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.core.login.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/core/login/login.do")
	public ModelAndView index(HttpServletRequest request, HttpSession session,  ModelMap model){
		ModelAndView mav = new ModelAndView();
		//변수 초기화
		String loginId = "";
		String loginPw = "";
		String systemMessage = "";
		int loginState = 0;
		
		//parameter 값얻기
		loginId = request.getParameter("loginId");
		loginPw = request.getParameter("loginPw");
		

		loginState = loginService.checkLoginInfo(loginId, loginPw);
		
		systemMessage = loginService.getLoginSystemMessage(loginState);
		
		loginService.login(loginId, loginState, systemMessage, mav, model, session);
		
		return mav;
		
	}
	
	@RequestMapping("/core/login/logout.do")
	public ModelAndView loginOut(HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		session.invalidate();
		mav.setViewName("/core/login/loginForm.jsp");
		
		return mav;
		
	}
}
