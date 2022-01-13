package com.jewsol.factory.panel.dao;

import java.util.List;

import com.jewsol.factory.panel.bean.ViewOrderForPanelDTO;
import com.jewsol.factory.panel.bean.PanelDTO;
import com.jewsol.factory.panel.bean.ViewOrderForStickerDTO;

public interface PanelDAO {

	int insertPanel(PanelDTO panel);

	List<PanelDTO> getPanelList(String date);

	List<ViewOrderForPanelDTO> getOrderForPanelList(int panelSeq);

	List<ViewOrderForStickerDTO> getOrderForStickerList(String orderDate);

	int getPanelSeq(PanelDTO panelDto);

	int getPanelLength(String orderDate);

	String getOrderClose(String orderDate);

}
