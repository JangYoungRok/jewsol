package com.jewsol.store.supplier.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.supplier.bean.SupplierDTO;

@Repository
public class SupplierDAOMy implements SupplierDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<SupplierDTO> getSupplierList() {
		return sqlSession.selectList("store.supplier.getSupplierList");
	}

	@Override
	public String getSupplierName(int storeProductSeq) {
		return sqlSession.selectOne("store.supplier.getSupplierName", storeProductSeq);
	}

}
