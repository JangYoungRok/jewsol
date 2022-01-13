package com.jewsol.store.saleSheet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.saleSheet.bean.OrderForSaleSheetDTO;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;

@Repository
public class SaleSheetDAOMy implements SaleSheetDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<SaleSheetDTO> getSaleSheetList(int customerSeq) {
		return sqlSession.selectList("store.saleSheet.getSaleSheetList", customerSeq);
	}

	@Override
	public SaleSheetDTO getSaleSheet(int saleSheetSeq) {
		return sqlSession.selectOne("store.saleSheet.getSaleSheet", saleSheetSeq);
	}

	@Override
	public List<OrderForSaleSheetDTO> getOrderListForSaleSheet(int saleSheetSeq) {
		return sqlSession.selectList("store.saleSheet.getOrderListForSaleSheet", saleSheetSeq);
	}

	@Override
	public int getLastSaleSheetNumber(String saleDate) {
		String saleSheetNumberStr = sqlSession.selectOne("store.saleSheet.getLastSaleSheetNumber", saleDate);
		int saleSheetNumber = 0;
		if(saleSheetNumberStr != null){
			saleSheetNumber = Integer.parseInt(saleSheetNumberStr);
		}
		return saleSheetNumber;
	}

	@Override
	public void updateLastBalance(SaleSheetDTO saleSheet) {
		sqlSession.update("store.saleSheet.updateLastBalance", saleSheet);
		
	}

	@Override
	public void updateBalance(SaleSheetDTO saleSheet) {
		sqlSession.update("store.saleSheet.updateBalance", saleSheet);
		
	}

	@Override
	public int insertSaleSheet(SaleSheetDTO saleSheet) {
		sqlSession.insert("store.saleSheet.insertSaleSheet", saleSheet);
		return saleSheet.getSaleSheetSeq();
	}

	@Override
	public List<Integer> getOrderSeqList(int saleSheetSeq) {
		return sqlSession.selectList("store.saleSheet.getOrderSeqList", saleSheetSeq);
	}

	@Override
	public SaleSheetDTO getLastSaleSheet(int customerSeq) {
		return sqlSession.selectOne("store.saleSheet.getLastSaleSheet", customerSeq);
	}

	@Override
	public List<SaleSheetDTO> getSaleSheetListByMonth(int thisYear,
			int thisMonth, int branchSeq) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("thisYear", thisYear);
		map.put("thisMonth", thisMonth);
		map.put("branchSeq", branchSeq);
		return sqlSession.selectList("store.saleSheet.getSaleSheetListByMonth", map);
	}

	
	
	

}
