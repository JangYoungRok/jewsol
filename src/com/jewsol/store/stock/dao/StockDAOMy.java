package com.jewsol.store.stock.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;
import com.jewsol.store.stock.bean.StockDTO;

@Repository
public class StockDAOMy implements StockDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertStock(StockDTO stock) {
		sqlSession.insert("store.stock.insertStock", stock);
		
	}

	@Override
	public void updateToStockReleaseSheet(ReleaseSheetDTO releaseSheet) {
		sqlSession.update("store.stock.updateToStockReleaseSheet", releaseSheet);
	}

	@Override
	public void deleteStock(int orderSeq) {
		sqlSession.delete("store.stock.deleteStock", orderSeq);
	}

}
