package com.jewsol.store.add.dao;

import java.util.List;

import com.jewsol.store.add.bean.AddDTO;

public interface AddDAO {

	List<AddDTO> getAddList(int branchSeq);

	void insertAdd(AddDTO addDto);

	void notInUseAdd(int addSeq);

	AddDTO getAdd(int addSeq);
	

}
