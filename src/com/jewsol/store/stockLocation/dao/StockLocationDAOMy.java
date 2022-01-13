package com.jewsol.store.stockLocation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.stockLocation.bean.StockLocationDTO;
import com.jewsol.store.stockLocation.bean.StockLocationTypeDTO;

@Repository
public class StockLocationDAOMy implements StockLocationDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<StockLocationDTO> getStockLocationList(int branchSeq) {
		
		return sqlSession.selectList("store.stockLocation.getStockLocationList", branchSeq);
	}
	
	@Override
	public List<StockLocationTypeDTO> getStockLocationTypeList() {
		
		return sqlSession.selectList("store.stockLocation.getStockLocationTypeList");
	}

	@Override
	public void insertStockLocation(StockLocationDTO stockLocationDto) {
		sqlSession.insert("store.stockLocation.insertStockLocation", stockLocationDto);
		
	}

	@Override
	public void notInUseStockLocation(int stockLocationSeq) {
		sqlSession.update("store.stockLocation.notInUseStockLocation", stockLocationSeq);
		
	}

	@Override
	public int checkOverLapStockLocationName(StockLocationDTO stockLocation) {
		return sqlSession.selectOne("store.stockLocation.checkOverLapStockLocationName", stockLocation);
	}

	@Override
	public List<StockLocationDTO> getStockLocationListByStockLocationTypeSeq(int stockLocationTypeSeq, int branchSeq) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("stockLocationTypeSeq", stockLocationTypeSeq);
		map.put("branchSeq", branchSeq);
		return sqlSession.selectList("store.stockLocation.getStockLocationListByStockLocationTypeSeq", map);
	}

}
