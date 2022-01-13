package com.jewsol.core.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.core.member.bean.MemberDTO;

@Repository
public class MemberDAOMy implements MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<MemberDTO> getMemberList(int branchSeq) {
		return sqlSession.selectList("core.member.getMemberList", branchSeq);
	}
}
