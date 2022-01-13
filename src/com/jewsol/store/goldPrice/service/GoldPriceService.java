package com.jewsol.store.goldPrice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.store.goldPrice.bean.GoldPriceDTO;
import com.jewsol.store.goldPrice.dao.GoldPriceDAO;

@Service
public class GoldPriceService {
	@Autowired
	private GoldPriceDAO goldPriceDao;

	public GoldPriceDTO getGoldPrice() {
		return goldPriceDao.getGoldPrice();
	}

	public void initAdminPartFrom(ModelMap model) {
		GoldPriceDTO goldPrice = getGoldPrice();
		model.put("goldPrice",goldPrice);
		
		
	}

}
