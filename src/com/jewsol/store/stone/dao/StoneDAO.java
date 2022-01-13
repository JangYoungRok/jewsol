package com.jewsol.store.stone.dao;

import java.util.List;

import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.bean.StoneSizeDTO;

public interface StoneDAO {

	List<StoneSizeDTO> getStoneSizeList();

	List<StoneDTO> getStoneList(int branchSeq);

	void insertStone(StoneDTO stoneDto);

	void notInUseStone(int stoneSeq);

	List<StoneDTO> getStoneListByStoneSizeSeq(StoneDTO stone);

	StoneDTO getStone(int stoneSeq);

	int getStoneSizeSeq(int stoneSeq);

}
