package com.jewsol.store.orderOption.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.orderOption.bean.OrderOptionAddDTO;
import com.jewsol.store.orderOption.bean.OrderOptionCzDTO;
import com.jewsol.store.orderOption.bean.OrderOptionDTO;
import com.jewsol.store.orderOption.bean.OrderOptionPartDTO;
import com.jewsol.store.orderOption.bean.OrderOptionStoneDTO;

@Repository
public class OrderOptionDAOMy implements OrderOptionDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertOrderOption(OrderOptionDTO insertOrderOption) {
		
		sqlSession.insert("store.orderOption.insertOrderOption", insertOrderOption);
		return insertOrderOption.getOrderOptionSeq();
	}

	@Override
	public void insertOrderOptionCz(OrderOptionCzDTO orderOptionCzDto) {
		sqlSession.insert("store.orderOption.insertOrderOptionCz", orderOptionCzDto);
		
	}

	@Override
	public void insertOrderOptionStone(OrderOptionStoneDTO orderOptionStoneDto) {
		sqlSession.insert("store.orderOption.insertOrderOptionStone", orderOptionStoneDto);
		
	}

	@Override
	public void insertOrderOptionPart(OrderOptionPartDTO orderOptionPartDto) {
		sqlSession.insert("store.orderOption.insertOrderOptionPart", orderOptionPartDto);
		
	}

	@Override
	public void insertOrderOptionAdd(OrderOptionAddDTO orderOptionAddDto) {
		sqlSession.insert("store.orderOption.insertOrderOptionAdd", orderOptionAddDto);
		
	}

	@Override
	public void initOrderOptionLabor(int orderOptionSeq) {
		sqlSession.update("store.orderOption.initOrderOptionLabor", orderOptionSeq);
		
	}

	@Override
	public void initOrderOptionPrice(int orderOptionSeq) {
		sqlSession.update("store.orderOption.initOrderOptionPrice", orderOptionSeq);
		
	}

	@Override
	public int getOrderOptionSeq(int orderSeq) {
		return sqlSession.selectOne("store.orderOption.getOrderOptionSeq", orderSeq);
	}

	@Override
	public String getOrderOptionName(int orderOptionSeq) {
		return sqlSession.selectOne("store.orderOption.getOrderOptionName", orderOptionSeq);
	}

	@Override
	public List<OrderOptionCzDTO> getOrderOptionCzList(int orderOptionSeq) {
		return sqlSession.selectList("store.orderOption.getOrderOptionCzList", orderOptionSeq);
	}

	@Override
	public List<OrderOptionStoneDTO> getOrderOptionStoneList(int orderOptionSeq) {
		return sqlSession.selectList("store.orderOption.getOrderOptionStoneList", orderOptionSeq);
	}

	@Override
	public List<OrderOptionPartDTO> getOrderOptionPartList(int orderOptionSeq) {
		return sqlSession.selectList("store.orderOption.getOrderOptionPartList", orderOptionSeq);
	}

	@Override
	public List<OrderOptionAddDTO> getOrderOptionAddList(int orderOptionSeq) {
		return sqlSession.selectList("store.orderOption.getOrderOptionAddList", orderOptionSeq);
	}

	@Override
	public void deleteOrderOptionCz(int orderOptionSeq) {
		sqlSession.update("store.orderOption.deleteOrderOptionCz", orderOptionSeq);
		
	}

	@Override
	public void deleteOrderOptionPart(int orderOptionSeq) {
		sqlSession.update("store.orderOption.deleteOrderOptionPart", orderOptionSeq);
	}

	@Override
	public void deleteOrderOptionAdd(int orderOptionSeq) {
		sqlSession.update("store.orderOption.deleteOrderOptionAdd", orderOptionSeq);
	}

	@Override
	public void deleteOrderOption(int orderOptionSeq) {
		sqlSession.update("store.orderOption.deleteOrderOption", orderOptionSeq);
	}

	@Override
	public void deleteOrderOptionStone(int orderOptionSeq) {
		sqlSession.update("store.orderOption.deleteOrderOptionStone", orderOptionSeq);
		
	}

	@Override
	public int getStoreProductSeq(int orderOptionSeq) {
		return sqlSession.selectOne("store.orderOption.getStoreProductSeq", orderOptionSeq);
	}

	@Override
	public int getOrderOptionLabor(int orderSeq) {
		return sqlSession.selectOne("store.orderOption.getOrderOptionLabor", orderSeq);
	}

	@Override
	public void updateOrderOption(OrderOptionDTO orderOption) {
		sqlSession.update("store.orderOption.updateOrderOption", orderOption);
	}

	@Override
	public int getOrderOptionPrice(int orderOptionSeq) {
		return sqlSession.selectOne("store.orderOption.getOrderOptionPrice", orderOptionSeq);
	}
}
