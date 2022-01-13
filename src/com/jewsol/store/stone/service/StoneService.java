package com.jewsol.store.stone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.bean.StoneSizeDTO;
import com.jewsol.store.stone.dao.StoneDAO;

@Service
public class StoneService {
	@Autowired
	private StoneDAO stoneDao;

	public void initAdminStoneFrom(ModelMap model, int branchSeq) {
		List<StoneDTO> stoneList = stoneDao.getStoneList(branchSeq);
		List<StoneSizeDTO> stoneSizeList = stoneDao.getStoneSizeList();
		
		model.put("stoneList", stoneList);
		model.put("stoneSizeList", stoneSizeList);
		
	}
	
	public void insertStone(StoneDTO stoneDto) {
		stoneDao.insertStone(stoneDto);
	}

	public List<StoneDTO> getStoneList(int branchSeq) {
		return stoneDao.getStoneList(branchSeq);
	}

	public void notInUseStone(int stoneSeq) {
		stoneDao.notInUseStone(stoneSeq);
		
	}

	public List<StoneDTO> getStoneListByStoneSizeSeq(StoneDTO stone){
		return stoneDao.getStoneListByStoneSizeSeq(stone);
	}

	public List<StoneSizeDTO> getStoneSizeList() {
		return stoneDao.getStoneSizeList();
	}

	public StoneDTO getStone(int stoneSeq) {
		
		return stoneDao.getStone(stoneSeq);
	}

	public int getStoneSizeSeq(int stoneSeq) {
		return stoneDao.getStoneSizeSeq(stoneSeq);
	}


}
