package com.jewsol.store.cz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.bean.CzSizeDTO;

@Repository
public class CzDAOMy implements CzDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CzSizeDTO> getCzSizeList() {
		return sqlSession.selectList("store.cz.getCzSizeList");
	}

	@Override
	public List<CzDTO> getCzList(int branchSeq) {
		return sqlSession.selectList("store.cz.getCzList", branchSeq);
	}

	@Override
	public void insertCz(CzDTO czDto) {
		sqlSession.insert("store.cz.insertCz", czDto);
		
	}

	@Override
	public void notInUseCz(int czSeq) {
		sqlSession.update("store.cz.notInUseCz", czSeq);
		
	}

	@Override
	public List<CzDTO> getCzListByCzSizeSeq(CzDTO cz) {
		return sqlSession.selectList("store.cz.getCzListByCzSizeSeq", cz);
	}

	@Override
	public CzDTO getCz(int czSeq) {
		return sqlSession.selectOne("store.cz.getCz",czSeq);
	}

	@Override
	public int getCzSizeSeq(int czSeq) {
		return sqlSession.selectOne("store.cz.getCzSizeSeq",czSeq);
	}

	@Override
	public List<Integer> getOptionSeqListByCzSeq(int czSeq) {
		return sqlSession.selectList("store.cz.getOptionSeqListByCzSeq", czSeq);
	}

	@Override
	public void updateCz(CzDTO cz) {
		sqlSession.update("store.cz.updateCz", cz);
	}

	@Override
	public List<Integer> getOptionCzSeqListByCzSeq(int czSeq) {
		return sqlSession.selectList("store.cz.getOptionCzSeqListByCzSeq", czSeq);
	}

}
