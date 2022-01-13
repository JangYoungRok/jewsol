package com.jewsol.core.branch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.core.branch.bean.BranchBalanceDTO;
import com.jewsol.core.branch.bean.BranchDTO;
import com.jewsol.core.branch.dao.BranchDAO;
import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;

@Service
public class BranchService {
	@Autowired
	private BranchDAO branchDao;
	
	public List<BranchDTO> getBranchList(){
		return branchDao.getBranchList();
	}

	public int getBranchSeq(String branchName) {
		return branchDao.getBranchSeq(branchName);
	}

	public int insertBranchRelease(ReleaseSheetDTO releaseSheet) {
		return branchDao.insertBranchRelease(releaseSheet);
	}

	public BranchBalanceDTO getLastBranchBalance(int branchSeq) {
		return branchDao.getLastBranchBalance(branchSeq);
	}

	public void insertBranchBalance(BranchBalanceDTO branchBalance) {
		branchDao.insertBranchBalance(branchBalance);
	}
}
