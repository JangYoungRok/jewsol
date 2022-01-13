package com.jewsol.store.cz.dao;

import java.util.List;

import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.bean.CzSizeDTO;

public interface CzDAO {
	List<CzSizeDTO> getCzSizeList();

	List<CzDTO> getCzList(int branchSeq);

	void insertCz(CzDTO czDto);

	void notInUseCz(int czSeq);

	List<CzDTO> getCzListByCzSizeSeq(CzDTO cz);

	CzDTO getCz(int czSeq);

	int getCzSizeSeq(int czSeq);

	List<Integer> getOptionSeqListByCzSeq(int czSeq);

	void updateCz(CzDTO cz);

	List<Integer> getOptionCzSeqListByCzSeq(int czSeq);
}
