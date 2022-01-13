package com.jewsol.core.main;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;

@Controller
public class MainController {
	
	@Autowired
	UtilSession utilSession;
	
	@RequestMapping("/index.do")
	public ModelAndView index(HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		utilSession.checkExistLogin(session, mav, "/core/main/main.jsp");
		
		return mav;
		
	}

}
