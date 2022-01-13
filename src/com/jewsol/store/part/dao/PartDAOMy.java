package com.jewsol.store.part.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.part.bean.PartAttributeDTO;
import com.jewsol.store.part.bean.PartDTO;


@Repository
public class PartDAOMy implements PartDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<PartAttributeDTO> getPartAttributeList(int typeMainSeq) {
		return sqlSession.selectList("store.part.getPartAttributeList", typeMainSeq);
	}

	@Override
	public int getTypeMainSeq(int typeSeq) {
		return sqlSession.selectOne("store.part.getTypeMainSeq", typeSeq);
	}

	@Override
	public List<PartDTO> getPartList(int branchSeq) {
		return sqlSession.selectList("store.part.getPartList", branchSeq);
	}

	@Override
	public void insertPart(PartDTO partDto) {
		sqlSession.insert("store.part.insertPart", partDto);
		
	}

	@Override
	public void notInUsePart(int partSeq) {
		sqlSession.update("store.part.notInUsePart", partSeq);
		
	}

	@Override
	public List<PartDTO> getPartListByPartAttributeSeq(
			PartDTO part) {
		return sqlSession.selectList("store.part.getPartListByPartAttributeSeq", part);
	}

	@Override
	public PartDTO getPart(int partSeq) {
		return sqlSession.selectOne("store.part.getPart", partSeq);
	}

	@Override
	public int getPartAttributeSeq(int partSeq) {
		return sqlSession.selectOne("store.part.getPartAttributeSeq", partSeq);
	}

}
