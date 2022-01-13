package com.jewsol.orangebox.godoOrderHistory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.orangebox.godoOrderHistory.bean.GodoOrderHistoryDTO;
import com.jewsol.orangebox.godoOrderHistory.dao.GodoOrderHistoryDAO;

@Service
public class GodoOrderHistoryService {
	@Autowired
	private GodoOrderHistoryDAO godoOrderHistorydao;

	public void inserGodoOrderHistory(int godoOrderSeq, String godoOrderHistory) {
		GodoOrderHistoryDTO godoOrderHistoryDto = new GodoOrderHistoryDTO();
		
		godoOrderHistoryDto.setGodoOrderSeq(godoOrderSeq);
		godoOrderHistoryDto.setGodoOrderHistory(godoOrderHistory);
		godoOrderHistorydao.insertAfterGodoOrderList(godoOrderHistoryDto);
		
	}

}
