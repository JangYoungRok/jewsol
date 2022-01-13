package com.jewsol.core.color.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.core.color.bean.ColorDTO;
import com.jewsol.core.color.dao.ColorDAO;

@Service
public class ColorService {
	@Autowired
	private ColorDAO colorDao;

	public List<ColorDTO> getColorList(String colorType) {
		return colorDao.getColorList(colorType);
	}

}
