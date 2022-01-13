package com.jewsol.core.plate.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.core.plate.bean.PlateDTO;

@Repository
public class PlateDAOMy implements PlateDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<PlateDTO> getPlateList() {
		return sqlSession.selectList("core.plate.getPlateList");
	}

	@Override
	public int getPlatePrice(int plateSeq) {
		return sqlSession.selectOne("core.plate.getPlatePrice", plateSeq);
	}

}
