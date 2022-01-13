package com.jewsol.store.goldPrice.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.goldPrice.bean.GoldPriceDTO;

@Repository
public class GoldPriceDAOMy implements GoldPriceDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public GoldPriceDTO getGoldPrice() {
		return sqlSession.selectOne("store.goldPrice.getGoldPrice");
	}

}
