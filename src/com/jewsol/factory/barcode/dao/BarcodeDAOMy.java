package com.jewsol.factory.barcode.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BarcodeDAOMy implements BarcodeDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

}
