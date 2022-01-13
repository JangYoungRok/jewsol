package com.jewsol.factory.releaseSheet.dao;

import java.util.List;

import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.bean.ViewOrderForReleaseSheetDTO;

public interface ReleaseSheetDAO {

	List<ReleaseSheetDTO> getReleaseSheetList(String releaseSheetDate);

	List<ViewOrderForReleaseSheetDTO> getOrderListForReleaseSheetList(
			ViewOrderForReleaseSheetDTO orderDto);

	int getReleaseSheetNumber(ReleaseSheetDTO releaseSheetDto);

	String getReleaseSheetType(int releaseSheetSeq);

	ReleaseSheetDTO getReleaseSheet(int releaseSheetSeq);

	int insertReleaseSheet(ReleaseSheetDTO releaseSheet);

	List<ViewOrderForReleaseSheetDTO> getOrderListForPrintReleaseSheetList(
			int releaseSheetSeq);

	List<ReleaseSheetDTO> getUnStockedReleaseSheetList(int branchSeq);

	List<ViewOrderForReleaseSheetDTO> getOrderList(int releaseSheetSeq);

	void updateReleaseSheet(ReleaseSheetDTO releaseSheet);

}
