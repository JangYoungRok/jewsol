package com.jewsol.store.part.dao;

import java.util.List;

import com.jewsol.store.part.bean.PartAttributeDTO;
import com.jewsol.store.part.bean.PartDTO;

public interface PartDAO {

	List<PartAttributeDTO> getPartAttributeList(int typeMainSeq);

	int getTypeMainSeq(int typeSeq);

	List<PartDTO> getPartList(int branchSeq);

	void insertPart(PartDTO partDto);

	void notInUsePart(int partSeq);

	List<PartDTO> getPartListByPartAttributeSeq(
			PartDTO part);

	PartDTO getPart(int partSeq);

	int getPartAttributeSeq(int partSeq);

}
