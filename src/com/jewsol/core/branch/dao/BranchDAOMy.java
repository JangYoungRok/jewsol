package com.jewsol.core.branch.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.core.branch.bean.BranchBalanceDTO;
import com.jewsol.core.branch.bean.BranchDTO;
import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;

@Repository
public class BranchDAOMy implements BranchDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BranchDTO> getBranchList() {
		return sqlSession.selectList("core.branch.getBranchList");
	}

	@Override
	public int getBranchSeq(String branchName) {
		return sqlSession.selectOne("core.branch.getBranchSeq", branchName);
	}

	@Override
	public int insertBranchRelease(ReleaseSheetDTO releaseSheet) {
		sqlSession.insert("core.branch.insertBranchRelease", releaseSheet);
		return releaseSheet.getReleaseSheetSeq();
	}

	@Override
	public BranchBalanceDTO getLastBranchBalance(int branchSeq) {
		return sqlSession.selectOne("core.branch.getLastBranchBalance", branchSeq);
	}

	@Override
	public void insertBranchBalance(BranchBalanceDTO branchBalance) {
		sqlSession.insert("core.branch.insertBranchBalance", branchBalance);
	}

}
