package com.jewsol.core.k.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.core.k.bean.KDTO;

@Repository
public class KDAOMy implements KDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<KDTO> getKList() {
		return sqlSession.selectList("core.k.getKList");
	}

}
