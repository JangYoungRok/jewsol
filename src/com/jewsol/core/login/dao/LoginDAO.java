package com.jewsol.core.login.dao;

import com.jewsol.core.login.bean.LoginInfoDTO;

public interface LoginDAO {

	int checkLoginId(String loginId);

	String getLoginPw(String loginId);

	LoginInfoDTO getLoginInfo(String loginId);

	int getBranchSeq(String loginId);

	int getMemberSeq(String loginId);

}
