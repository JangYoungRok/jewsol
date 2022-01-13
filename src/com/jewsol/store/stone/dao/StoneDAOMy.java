package com.jewsol.store.stone.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.bean.StoneSizeDTO;

@Repository
public class StoneDAOMy implements StoneDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<StoneSizeDTO> getStoneSizeList() {
		return sqlSession.selectList("store.stone.getStoneSizeList");
	}

	@Override
	public List<StoneDTO> getStoneList(int branchSeq) {
		return sqlSession.selectList("store.stone.getStoneList", branchSeq);
	}
	@Override
	public void insertStone(StoneDTO stoneDto) {
		sqlSession.insert("store.stone.insertStone", stoneDto);
	}

	@Override
	public void notInUseStone(int stoneSeq) {
		sqlSession.update("store.stone.notInUseStone", stoneSeq);
		
	}

	@Override
	public List<StoneDTO> getStoneListByStoneSizeSeq(StoneDTO stone) {
		return sqlSession.selectList("store.stone.getStoneListByStoneSizeSeq", stone);
	}

	@Override
	public StoneDTO getStone(int stoneSeq) {
		return sqlSession.selectOne("store.stone.getStone", stoneSeq);
	}

	@Override
	public int getStoneSizeSeq(int stoneSeq) {
		return sqlSession.selectOne("store.stone.getStoneSizeSeq", stoneSeq);
	}
}
