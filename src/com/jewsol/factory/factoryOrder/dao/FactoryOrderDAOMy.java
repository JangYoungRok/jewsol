package com.jewsol.factory.factoryOrder.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.factory.factoryOrder.bean.InqueryFactoryOrderDTO;



@Repository
public class FactoryOrderDAOMy implements FactoryOrderDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int checkOrderDate(String today) {
		return sqlSession.selectOne("factory.factoryOrder.checkOrderDate", today);
	}

	@Override
	public void insertOrderDate(String today) {
		sqlSession.insert("factory.factoryOrder.insertOrder", today);
		
	}

	@Override
	public String getOrderClose(String today) {
		return sqlSession.selectOne("factory.factoryOrder.getOrderClose", today);
	}

	@Override
	public List<Integer> getOrderSeqRingList(String orderK,
			String orderDate) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("orderK",orderK);
		map.put("orderDate",orderDate);
		
		return sqlSession.selectList("factory.factoryOrder.getOrderSeqList", map);
	}

	@Override
	public List<Integer> getOrderSeqPendentList(String orderK, String orderDate) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderK",orderK);
		map.put("orderDate",orderDate);
		
		return sqlSession.selectList("factory.factoryOrder.getOrderSeqPendentList", map);
	}

	@Override
	public List<Integer> getOrderSeqNecklaceList(String orderK, String orderDate) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderK",orderK);
		map.put("orderDate",orderDate);
		
		return sqlSession.selectList("factory.factoryOrder.getOrderSeqNecklaceList", map);
	}

	@Override
	public List<Integer> getOrderSeqHurryList(String orderK, String orderDate) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderK",orderK);
		map.put("orderDate",orderDate);
		
		return sqlSession.selectList("factory.factoryOrder.getOrderSeqHurryList", map);
	}

	@Override
	public void updateOrderPanelSeq(int orderSeq, int panelSeq, int orderNumber) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("orderSeq",orderSeq);
		map.put("panelSeq",panelSeq);
		map.put("orderNumber",orderNumber);
		sqlSession.update("factory.factoryOrder.updateOrderPanelSeq", map);
	}

	@Override
	public List<Integer> getOrderSeq10SvList(String orderDate) {
		return sqlSession.selectList("factory.factoryOrder.getOrderSeq10SvList", orderDate);
	}

	@Override
	public void closeOrderDate(String orderDate) {
		sqlSession.update("factory.factoryOrder.closeOrderDate", orderDate);
		
	}

	@Override
	public List<InqueryFactoryOrderDTO> getFactoryInqueryOrder(String productCode) {
		return sqlSession.selectList("factory.factoryOrder.getFactoryInqueryOrder", productCode);
	}

	@Override
	public List<Integer> getOrderSeqAnoList(String orderDate) {
		return sqlSession.selectList("factory.factoryOrder.getOrderSeqAnoList", orderDate);
	}

}
