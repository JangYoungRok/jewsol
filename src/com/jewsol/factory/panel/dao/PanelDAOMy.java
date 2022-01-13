package com.jewsol.factory.panel.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.factory.panel.bean.ViewOrderForPanelDTO;
import com.jewsol.factory.panel.bean.PanelDTO;
import com.jewsol.factory.panel.bean.ViewOrderForStickerDTO;

@Repository
public class PanelDAOMy implements PanelDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertPanel(PanelDTO panel) {
		sqlSession.insert("factory.panel.insertPanel", panel);
		return panel.getPanelSeq();
	}

	@Override
	public List<PanelDTO> getPanelList(String date) {
		return sqlSession.selectList("factory.panel.getPanelList", date);
	}

	@Override
	public List<ViewOrderForPanelDTO> getOrderForPanelList(int panelSeq) {
		return sqlSession.selectList("factory.panel.getOrderForPanelList", panelSeq);
	}

	@Override
	public List<ViewOrderForStickerDTO> getOrderForStickerList(String orderDate) {
		return sqlSession.selectList("factory.panel.getOrderForStickerList", orderDate);
	}

	@Override
	public int getPanelSeq(PanelDTO panelDto) {
		return sqlSession.selectOne("factory.panel.getPanelSeq", panelDto);
	}

	@Override
	public int getPanelLength(String orderDate) {
		return sqlSession.selectOne("factory.panel.getPanelLength", orderDate);
	}

	@Override
	public String getOrderClose(String orderDate) {
		return sqlSession.selectOne("factory.panel.getOrderClose", orderDate);
	}

}
