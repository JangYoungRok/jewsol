package com.jewsol.orangebox.godoOrderHistory.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.orangebox.godoOrderHistory.bean.GodoOrderHistoryDTO;

@Repository
public class GodoOrderHistoryDAOMy implements GodoOrderHistoryDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertAfterGodoOrderList(GodoOrderHistoryDTO godoOrderHistoryDto) {
		sqlSession.insert("orangebox.godoOrderHistory.insertAfterGodoOrderList", godoOrderHistoryDto);
		
	}

}
