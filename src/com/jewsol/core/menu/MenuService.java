package com.jewsol.core.menu;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MenuService {
	public void getMenu(HttpSession session, ModelAndView mav){
		//branchType 값 세션에서 얻어오기
		//authority 값 세션에서 얻어오기
		String authority = session.getAttribute("sAuthority").toString();
		String branchType = session.getAttribute("sBranchType").toString();
		
		//각 값에 맞는 메뉴 jsp 파일 Object에 추가
		if(branchType.equals("1")){
			if(authority.equals("9")){
				mav.addObject("menu", "/WEB-INF/view/core/menu/menu19.jsp");
			}else if(authority.equals("7")){
				mav.addObject("menu", "/WEB-INF/view/core/menu/menu17.jsp");
			}
		}else if(branchType.equals("2")){
			if(authority.equals("9")){
				mav.addObject("menu", "/WEB-INF/view/core/menu/menu29.jsp");			
			}else if(authority.equals("1")){
				mav.addObject("menu", "/WEB-INF/view/core/menu/menu21.jsp");
			}else if(authority.equals("7")){
				mav.addObject("menu", "/WEB-INF/view/core/menu/menu27.jsp");
			}
		}
		
	}

}
