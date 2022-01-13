package com.jewsol.core.k.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.core.k.bean.KDTO;
import com.jewsol.core.k.dao.KDAO;

@Service
public class KService {
	@Autowired
	private KDAO kDao;

	public List<KDTO> getKList() {
		return kDao.getKList();
	}

}
