package com.jewsol.core.branch.dao;

import java.util.List;

import com.jewsol.core.branch.bean.BranchBalanceDTO;
import com.jewsol.core.branch.bean.BranchDTO;
import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;

public interface BranchDAO {

	List<BranchDTO> getBranchList();

	int getBranchSeq(String branchName);

	int insertBranchRelease(ReleaseSheetDTO releaseSheet);

	BranchBalanceDTO getLastBranchBalance(int branchSeq);

	void insertBranchBalance(BranchBalanceDTO branchBalance);

}
