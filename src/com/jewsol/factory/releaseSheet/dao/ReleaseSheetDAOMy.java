package com.jewsol.factory.releaseSheet.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;
import com.jewsol.factory.releaseSheet.bean.ViewOrderForReleaseSheetDTO;

@Repository
public class ReleaseSheetDAOMy implements ReleaseSheetDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Override
	public List<ReleaseSheetDTO> getReleaseSheetList(String releaseSheetDate) {
		return sqlSession.selectList("factory.releaseSheet.getReleaseSheetList", releaseSheetDate);
	}
	@Override
	public List<ViewOrderForReleaseSheetDTO> getOrderListForReleaseSheetList(
			ViewOrderForReleaseSheetDTO orderDto) {
		return sqlSession.selectList("factory.releaseSheet.getOrderListForReleaseSheetList", orderDto);
	}
	@Override
	public int getReleaseSheetNumber(ReleaseSheetDTO releaseSheetDto) {
		
		return sqlSession.selectOne("factory.releaseSheet.getReleaseSheetNumber", releaseSheetDto);
	}
	@Override
	public String getReleaseSheetType(int releaseSheetSeq) {
		return sqlSession.selectOne("factory.releaseSheet.getReleaseSheetType", releaseSheetSeq);
	}
	@Override
	public ReleaseSheetDTO getReleaseSheet(int releaseSheetSeq) {
		return sqlSession.selectOne("factory.releaseSheet.getReleaseSheet", releaseSheetSeq);
	}
	@Override
	public int insertReleaseSheet(ReleaseSheetDTO releaseSheet) {
		sqlSession.insert("factory.releaseSheet.insertReleaseSheet", releaseSheet);
		return releaseSheet.getReleaseSheetSeq();
		
	}
	@Override
	public List<ViewOrderForReleaseSheetDTO> getOrderListForPrintReleaseSheetList(
			int releaseSheetSeq) {
		return sqlSession.selectList("factory.releaseSheet.getOrderListForPrintReleaseSheetList", releaseSheetSeq);
	}
	@Override
	public List<ReleaseSheetDTO> getUnStockedReleaseSheetList(int branchSeq) {
		return sqlSession.selectList("factory.releaseSheet.getUnStockedReleaseSheetList", branchSeq);
	}
	@Override
	public List<ViewOrderForReleaseSheetDTO> getOrderList(int releaseSheetSeq) {
		return sqlSession.selectList("factory.releaseSheet.getOrderListByReleaseSheetSeq", releaseSheetSeq);
	}
	@Override
	public void updateReleaseSheet(ReleaseSheetDTO releaseSheet) {
		sqlSession.update("factory.releaseSheet.updateReleaseSheet", releaseSheet);
		
	}

}
