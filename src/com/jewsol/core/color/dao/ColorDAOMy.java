package com.jewsol.core.color.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.core.color.bean.ColorDTO;

@Repository
public class ColorDAOMy implements ColorDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ColorDTO> getColorList(String colorType) {
		return sqlSession.selectList("core.color.getColorList", colorType);
	}

}
