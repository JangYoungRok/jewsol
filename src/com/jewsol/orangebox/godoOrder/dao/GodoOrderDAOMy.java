package com.jewsol.orangebox.godoOrder.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.orangebox.godoOrder.bean.GodoOrderDTO;
import com.jewsol.orangebox.godoOrder.bean.GodoOrderListDTO;

@Repository
public class GodoOrderDAOMy implements GodoOrderDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertGodoOrder(GodoOrderDTO godoOrderDto) {
		sqlSession.insert("orangebox.godoOrder.insertGodoOrder", godoOrderDto);
		return godoOrderDto.getGodoOrderSeq();
	}

	@Override
	public GodoOrderDTO getGodoOrder(int godoOrderSeq) {
		
		return sqlSession.selectOne("orangebox.godoOrder.selectGodoOrder", godoOrderSeq);
	}

	@Override
	public void insertGodoOrderList(GodoOrderListDTO godoOrderListDto) {
		sqlSession.insert("orangebox.godoOrder.insertGodoOrderList", godoOrderListDto);
	}

	@Override
	public List<GodoOrderListDTO> getGodoOrderList(int godoOrderSeq) {
		return sqlSession.selectList("orangebox.godoOrder.getGodoOrderList", godoOrderSeq);
	}

}
