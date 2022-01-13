package com.jewsol.core.member.dao;

import java.util.List;

import com.jewsol.core.member.bean.MemberDTO;

public interface MemberDAO {

	List<MemberDTO> getMemberList(int branchSeq);

}
