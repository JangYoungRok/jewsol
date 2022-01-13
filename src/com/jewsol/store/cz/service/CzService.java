package com.jewsol.store.cz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.bean.CzSizeDTO;
import com.jewsol.store.cz.dao.CzDAO;
import com.jewsol.store.option.bean.OptionCzDTO;
import com.jewsol.store.option.service.OptionService;

@Service
public class CzService {
	@Autowired
	CzDAO czDao;
	@Autowired
	OptionService optionService;

	public void initAdminCzFrom(ModelMap model, int branchSeq) {
		List<CzDTO> czList = czDao.getCzList(branchSeq);
		List<CzSizeDTO> czSizeList = czDao.getCzSizeList();

		model.put("czList", czList);
		model.put("czSizeList", czSizeList);
	}

	public void insertCz(CzDTO czDto) {
		czDao.insertCz(czDto);
	}

	public List<CzDTO> getCzList(int branchSeq) {
		return czDao.getCzList(branchSeq);
	}

	public void notInUseCz(int czSeq) {
		czDao.notInUseCz(czSeq);
	}

	public List<CzDTO> getCzListByCzSizeSeq(int czSizeSeq, int branchSeq) {
		CzDTO cz = new CzDTO();
		cz.setBranchSeq(branchSeq);
		cz.setCzSizeSeq(czSizeSeq);
		return czDao.getCzListByCzSizeSeq(cz);
	}

	public CzDTO getCz(int czSeq) {
		return czDao.getCz(czSeq);
	}

	public List<CzSizeDTO> getCzSizeList() {
		return czDao.getCzSizeList();
	}

	public int getCzSizeSeq(int czSeq) {
		return czDao.getCzSizeSeq(czSeq);
	}

	public void updateCz(CzDTO cz) {
		czDao.updateCz(cz);
		
		List<Integer> optionCzSeqList = czDao.getOptionCzSeqListByCzSeq(cz.getCzSeq());
		OptionCzDTO optionCz = new OptionCzDTO();
		for(int optionCzSeq : optionCzSeqList){
			optionCz.setOptionCzSeq(optionCzSeq);
			optionCz.setCzName(cz.getCzName());
			optionCz.setOptionCzPrice(cz.getCzPrice());
			optionCz.setOptionCzLabor(cz.getCzLabor());
			optionService.updateOptionCz(optionCz);
		}
		
		List<Integer> optionSeqList = czDao.getOptionSeqListByCzSeq(cz.getCzSeq());
		for(int optionSeq : optionSeqList){
			optionService.initOptionLabor(optionSeq);
			optionService.initOptionPrice(optionSeq);
		}
	}
}
