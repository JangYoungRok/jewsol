package com.jewsol.store.sale.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.sale.bean.OrderForSaleDTO;
import com.jewsol.store.sale.bean.SaleDTO;
import com.jewsol.store.sale.bean.SaleTypeDTO;

@Repository
public class SaleDAOMy implements SaleDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Override
	public List<SaleTypeDTO> getSaleTypeList() {
		return sqlSession.selectList("store.sale.getSaleTypeList");
	}
	@Override
	public List<OrderForSaleDTO> getOrderListForSale(int customerSeq) {
		return sqlSession.selectList("store.sale.getOrderListForSale", customerSeq);
	}
	@Override
	public void insertSale(SaleDTO sale) {
		sqlSession.insert("store.sale.insertSale", sale);
		
	}
	@Override
	public void deleteSaleSheet(int saleSheetSeq) {
		sqlSession.delete("store.sale.deleteSaleSheet", saleSheetSeq);
	}
	@Override
	public List<Integer> getOrderSeqList(int saleSheetSeq) {
		return sqlSession.selectList("store.sale.getOrderSeqList", saleSheetSeq);
	}

}
