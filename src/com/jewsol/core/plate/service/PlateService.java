package com.jewsol.core.plate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.core.plate.bean.PlateDTO;
import com.jewsol.core.plate.dao.PlateDAO;

@Service
public class PlateService {
	@Autowired
	private PlateDAO plateDao;

	public List<PlateDTO> getPlateList() {
		
		return plateDao.getPlateList();
	}

	public int getPlatePrice(int plateSeq) {
		return plateDao.getPlatePrice(plateSeq);
	}

}
