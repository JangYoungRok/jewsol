package com.jewsol.store.originalSheet.dao;

import java.util.List;

import com.jewsol.store.originalSheet.bean.OriginalSheetDTO;

public interface OriginalSheetDAO {

	OriginalSheetDTO getOriginalSheetResult(int branchSeq,
			int originalSheetNumber);

	void insertOriginalSheet(OriginalSheetDTO originalSheetDto);

	int getOriginalSheetSeq(String orderDate, int originalSheetNumber, int branchSeq);

	void closeOriginalSheet(int originalSheetSeq);

	List<OriginalSheetDTO> getOriginalSheetList(OriginalSheetDTO originalSheetDto);

	OriginalSheetDTO getOriginalSheet(int originalSheetSeq);

}
