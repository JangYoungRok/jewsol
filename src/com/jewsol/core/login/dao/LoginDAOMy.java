package com.jewsol.core.login.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.core.login.bean.LoginInfoDTO;

@Repository
public class LoginDAOMy implements LoginDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int checkLoginId(String loginId) {
		int resultIdcount = 0;
		resultIdcount = sqlSession.selectOne("core.login.checkLoginId", loginId);
		
		return resultIdcount;
	}

	@Override
	public String getLoginPw(String loginId) {
		String resultPw = "";
		resultPw = sqlSession.selectOne("core.login.getLoginPw", loginId);
		
		return resultPw;
	}

	@Override
	public LoginInfoDTO getLoginInfo(String loginId) {
		
		return sqlSession.selectOne("core.login.getLoginInfo", loginId);
	}

	@Override
	public int getBranchSeq(String loginId) {
		return sqlSession.selectOne("core.login.getBranchSeq", loginId);
	}

	@Override
	public int getMemberSeq(String loginId) {
		return sqlSession.selectOne("core.login.getMemberSeq", loginId);
	}

}
