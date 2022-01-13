package com.jewsol.common.util;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.core.login.service.LoginService;
import com.jewsol.core.menu.MenuService;

@Service
public class UtilSession {
	
	@Autowired
	MenuService menuService;
	@Autowired
	LoginService loginService;

	public boolean checkSessionId(HttpSession session){
		
		boolean result = false;
		
		if(session.getAttribute("sLoginId") == null){
			
		}else{
			result = true;
		}
		
		return result;
	}
	
	public void checkExistLogin(HttpSession session ,ModelAndView mav, String successView){
		
		if(checkSessionId(session) == false){
			mav.setViewName("/core/login/loginForm.jsp");
		}else{
			
			menuService.getMenu(session, mav);
			loginService.getLoginInfo(session, mav);
			mav.setViewName(successView);
		}
	
	}
	
	public boolean checkArrayLength(String[] stringArr){
		boolean result =false;
		
		if(stringArr[0] != ""){
			result = true;
		}
		
		return result;
	}
	
	public String getBranchName(HttpSession session){ 
		String branchName = "";
		if(checkSessionId(session) == false){
			nullSession();
		}else{
			branchName = (String)session.getAttribute("sBranchName");
		}
		return branchName;
	}

	public int getBranchSeq(HttpSession session) {
		int branchSeq = 0;
		if(checkSessionId(session) == false){
			nullSession();
		}else{
			branchSeq = (Integer)session.getAttribute("sBranchSeq");
		}
		return branchSeq;
	}
	
	public ModelAndView nullSession(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/core/login/loginForm.jsp");
		return mav;
	}

	public String getLoginId(HttpSession session) {
		String loginId = "";
		if(checkSessionId(session) == false){
			nullSession();
		}else{
			loginId = (String)session.getAttribute("sLoginId");
		}
		return loginId;
	}
}
