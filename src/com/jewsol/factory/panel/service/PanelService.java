package com.jewsol.factory.panel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.factory.panel.bean.ViewOrderForPanelDTO;
import com.jewsol.factory.panel.bean.PanelDTO;
import com.jewsol.factory.panel.bean.ViewOrderForStickerDTO;
import com.jewsol.factory.panel.dao.PanelDAO;

@Service
public class PanelService {
	@Autowired
	private PanelDAO panelDao;

	public int insertPanel(PanelDTO panel) {
		return panelDao.insertPanel(panel);
	}

	public List<PanelDTO> getPanelList(String date) {
		
		return getOrderForPanel(panelDao.getPanelList(date));
	}

	public List<PanelDTO> getOrderForPanel(List<PanelDTO> panelDtoList) {
		for(PanelDTO data : panelDtoList){
			int panelSeq = data.getPanelSeq();
			List<ViewOrderForPanelDTO> orderForPanelList = panelDao.getOrderForPanelList(panelSeq);
			data.setOrderForPanelList(orderForPanelList);
		}
		
		return panelDtoList;
		
	}

	public List<ViewOrderForStickerDTO> getOrderForStickerList(String orderDate) {
		return panelDao.getOrderForStickerList(orderDate);
	}

	public List<ViewOrderForPanelDTO> getOrderListForPanel(String orderDate,
			int panelNumber) {
		PanelDTO panelDto = new PanelDTO();
		panelDto.setPanelDate(orderDate);
		panelDto.setPanelNumber(panelNumber);

		int panelSeq = panelDao.getPanelSeq(panelDto);
		return panelDao.getOrderForPanelList(panelSeq);
	}

	public int getPanelLength(String orderDate) {
		return panelDao.getPanelLength(orderDate);
	}

	public String getOrderClose(String orderDate) {
		
		return panelDao.getOrderClose(orderDate);
	}

}
