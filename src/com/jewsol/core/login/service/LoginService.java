package com.jewsol.core.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.core.login.bean.LoginInfoDTO;
import com.jewsol.core.login.dao.LoginDAO;
import com.jewsol.core.menu.MenuService;

@Service
public class LoginService {
	
	@Autowired
	private LoginDAO loginDao;
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private MenuService menuService;

	public int checkLoginInfo(String loginId, String loginPw) {
		int loginState = 0;
		int resultIdCount = 0;
		String resultPw = "";
		
		//DB에서 로그인 아이디가 있는 지 확인
		resultIdCount = loginDao.checkLoginId(loginId);
		
		//id가 있는 경우
		//pw받아 와서 비교
		//같으면 loginState = 3
		//틀리면 loginState = 2
		//id가 없는 경우 loginState = 1
		if(resultIdCount == 1){
			resultPw = loginDao.getLoginPw(loginId);
			if(loginPw.equals(resultPw)){
				loginState = 3;
			}else{
				loginState = 2;
			}
		}else if(resultIdCount == 0){
			loginState = 1;
		}
	
		return loginState;
	}

	public String getLoginSystemMessage(int loginState) {
		String systemMessage = "";
		if(loginState == 1){
			systemMessage = "아이디가 존재 하지 않습니다.";
		}else if(loginState == 2){
			systemMessage = "비밀번호가 일치 하지 않습니다.";
		}
	
		return systemMessage;
	}

	public void login(String loginId, int loginState, String systemMessage, ModelAndView mav,
			ModelMap model ,HttpSession session) {
		
		LoginInfoDTO loginInfoDto = new LoginInfoDTO();
		
		if(loginState == 1){
			model.put("systemMessage", systemMessage);
			mav.setViewName("/core/login/loginForm.jsp");
		}else if(loginState == 2){
			model.put("systemMessage", systemMessage);
			model.put("loginId", loginId);
			mav.setViewName("/core/login/loginForm.jsp");
	
		}else if(loginState == 3){
			
			//memberName 가져오기
			//branchName 가져오기
			//branchType 가져오기
			//authority 가져오기
			loginInfoDto = loginDao.getLoginInfo(loginId);
			//session값 저장
			session.setAttribute("sLoginId", loginId);
			session.setAttribute("sMemberName", loginInfoDto.getMemberName());
			session.setAttribute("sBranchType", loginInfoDto.getBranchType());
			session.setAttribute("sBranchName", loginInfoDto.getBranchName());
			session.setAttribute("sAuthority", loginInfoDto.getAuthority());
			session.setAttribute("sBranchSeq", loginInfoDto.getBranchSeq());
			
			getLoginInfo(session, mav);
			menuService.getMenu(session, mav);
			//loginState 성공시 main.jsp로 이동
			mav.setViewName("/core/main/main.jsp");
			
		}
		
		
	}

	public void getLoginInfo(HttpSession session, ModelAndView mav) {
		mav.addObject("loginInfo", "/WEB-INF/view/core/login/loginInfo.jsp");
	}

	public int getMemberSeq(String loginId) {
		return loginDao.getMemberSeq(loginId);
	}
	
}
