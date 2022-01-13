package com.jewsol.factory.release.dao;

import java.util.List;

import com.jewsol.factory.release.bean.InsertReleaseDTO;
import com.jewsol.factory.release.bean.ViewOrderForReleaseDTO;

public interface ReleaseDAO {

	void checkRelease(int orderSeq);

	List<ViewOrderForReleaseDTO> getOrderListByBranchNameState3(String branchName);

	void updateOrderState4(int orderSeq);

	void updateOrderWeight(InsertReleaseDTO releaseDto);

	List<ViewOrderForReleaseDTO> getOrderListState3();

	void updateOrderState2(int orderSeq);

}
