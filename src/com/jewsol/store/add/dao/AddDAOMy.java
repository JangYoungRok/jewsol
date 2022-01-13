package com.jewsol.store.add.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.add.bean.AddDTO;


@Repository
public class AddDAOMy implements AddDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<AddDTO> getAddList(int branchSeq) {
		
		return sqlSession.selectList("store.add.getAddList", branchSeq);
	}

	@Override
	public void insertAdd(AddDTO addDto) {
		sqlSession.insert("store.add.insertAdd", addDto);
		
	}

	@Override
	public void notInUseAdd(int addSeq) {
		sqlSession.update("store.add.notInUseAdd", addSeq);
		
	}

	@Override
	public AddDTO getAdd(int addSeq) {
		return sqlSession.selectOne("store.add.getAdd", addSeq);
	}

}
