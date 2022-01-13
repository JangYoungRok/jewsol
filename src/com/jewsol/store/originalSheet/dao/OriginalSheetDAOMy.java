package com.jewsol.store.originalSheet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.originalSheet.bean.OriginalSheetDTO;

@Repository
public class OriginalSheetDAOMy implements OriginalSheetDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public OriginalSheetDTO getOriginalSheetResult(int branchSeq,
			int originalSheetNumber) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		map.put("branchSeq", branchSeq);
		map.put("originalSheetNumber", originalSheetNumber);
		
		return sqlSession.selectOne("store.originalSheet.getOriginalSheetResult",map);
	}

	@Override
	public void insertOriginalSheet(OriginalSheetDTO originalSheetDto) {
		sqlSession.insert("store.originalSheet.insertOriginalSheet", originalSheetDto);
		 
	}

	@Override
	public int getOriginalSheetSeq(String orderDate, int originalSheetNumber, int branchSeq) {
		OriginalSheetDTO originalSheetDto = new OriginalSheetDTO();
		originalSheetDto.setOriginalSheetDate(orderDate);
		originalSheetDto.setOriginalSheetNumber(originalSheetNumber);
		originalSheetDto.setBranchSeq(branchSeq);
		return sqlSession.selectOne("store.originalSheet.getOriginalSheetSeq",originalSheetDto);
	}

	@Override
	public void closeOriginalSheet(int originalSheetSeq) {
		sqlSession.update("store.originalSheet.closeOriginalSheet", originalSheetSeq);
		
	}

	@Override
	public List<OriginalSheetDTO> getOriginalSheetList(OriginalSheetDTO originalSheetDto) {
		return sqlSession.selectList("store.originalSheet.getOriginalSheetList", originalSheetDto);
	}

	@Override
	public OriginalSheetDTO getOriginalSheet(int originalSheetSeq) {
		return sqlSession.selectOne("store.originalSheet.getOriginalSheet", originalSheetSeq);
	}

}
