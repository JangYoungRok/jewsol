package com.jewsol.core.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.core.member.bean.MemberDTO;
import com.jewsol.core.member.dao.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDao;

	public List<MemberDTO> getMemberList(int branchSeq) {
		return memberDao.getMemberList(branchSeq);
	}

}
