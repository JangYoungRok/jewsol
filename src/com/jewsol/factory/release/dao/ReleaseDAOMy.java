package com.jewsol.factory.release.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.factory.release.bean.InsertReleaseDTO;
import com.jewsol.factory.release.bean.ViewOrderForReleaseDTO;

@Repository
public class ReleaseDAOMy implements ReleaseDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void checkRelease(int orderSeq) {
		sqlSession.update("factory.release.checkRelease", orderSeq);
	}

	@Override
	public List<ViewOrderForReleaseDTO> getOrderListByBranchNameState3(
			String branchName) {
		return sqlSession.selectList("factory.release.getOrderListByBranchNameState3", branchName);
	}

	@Override
	public void updateOrderState4(int orderSeq) {
		sqlSession.update("factory.release.updateOrderState4", orderSeq);
	}

	@Override
	public void updateOrderWeight(InsertReleaseDTO releaseDto) {
		sqlSession.update("factory.release.updateOrderWeight", releaseDto);
	}

	@Override
	public List<ViewOrderForReleaseDTO> getOrderListState3() {
		return sqlSession.selectList("factory.release.getOrderListState3");
	}

	@Override
	public void updateOrderState2(int orderSeq) {
		sqlSession.update("factory.release.updateOrderState2", orderSeq);
		
	}

}
