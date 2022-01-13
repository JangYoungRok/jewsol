package com.jewsol.store.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.order.bean.InqueryOrderDTO;
import com.jewsol.store.order.bean.OrderDTO;
import com.jewsol.store.order.bean.OrderDetailDTO;
import com.jewsol.store.order.bean.OrderPriceDTO;
import com.jewsol.store.order.bean.OrderWeightDTO;
import com.jewsol.store.order.bean.ViewOrderDTO;

@Repository
public class OrderDAOMy implements OrderDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertOrder(OrderDTO insertOrderDTO) {
		sqlSession.insert("store.order.insertOrder", insertOrderDTO);
		return insertOrderDTO.getOrderSeq();
	}

	@Override
	public void insertOrderDetail(OrderDetailDTO insertOrderDetailDto) {
		sqlSession.insert("store.order.insertOrderDetail", insertOrderDetailDto);
		
	}

	@Override
	public void insertOrderPrice(int orderSeq) {
		sqlSession.insert("store.order.insertOrderPrice", orderSeq);
		
	}

	@Override
	public void insertOrderWeight(int orderSeq) {
		sqlSession.insert("store.order.insertOrderWeight", orderSeq);
		
	}

	@Override
	public void updateOrderCzWeight(int orderSeq) {
		sqlSession.update("store.order.updateOrderCzWeight", orderSeq);
		
	}

	@Override
	public List<ViewOrderDTO> getTempOrderList(int originalSheetSeq) {
		return sqlSession.selectList("store.order.getTempOrderList", originalSheetSeq);
	}

	@Override
	public OrderDTO getOrder(int orderSeq) {
		return sqlSession.selectOne("store.order.getOrder", orderSeq);
	}

	@Override
	public OrderDetailDTO getOrderDetail(int orderSeq) {
		return sqlSession.selectOne("store.order.getOrderDetail", orderSeq);
	}

	@Override
	public void updateOrder(OrderDTO updateOrder) {
		sqlSession.update("store.order.updateOrder", updateOrder);
	}

	@Override
	public void updateOrderDetail(OrderDetailDTO updateOtderDetail) {
		sqlSession.update("store.order.updateOrderDetail", updateOtderDetail);
	}

	@Override
	public void deleteOrderWeight(int orderSeq) {
		sqlSession.delete("store.order.deleteOrderWeight", orderSeq);
	}

	@Override
	public void deleteOrderPrice(int orderSeq) {
		sqlSession.delete("store.order.deleteOrderPrice", orderSeq);
	}

	@Override
	public void deleteOrderDetail(int orderSeq) {
		sqlSession.delete("store.order.deleteOrderDetail", orderSeq);
	}

	@Override
	public void deleteOrder(int orderSeq) {
		sqlSession.delete("store.order.deleteOrder", orderSeq);
	}

	@Override
	public void registerOrder(int originalSheetSeq) {
		sqlSession.update("store.order.registerOrder", originalSheetSeq);
	}

	@Override
	public List<InqueryOrderDTO> getInqueryOrder(String productCode, String branchName) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("productCode", productCode);
		map.put("branchName", branchName);
		return sqlSession.selectList("store.order.getInqueryOrder", map);
	}

	@Override
	public void updateReleaseSheetSeqInOrder(int orderSeq, int releaseSheetSeq) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("orderSeq", orderSeq);
		map.put("releaseSheetSeq", releaseSheetSeq);
		sqlSession.update("store.order.updateReleaseSheetSeqInOrder", map);
		
	}
	
	@Override
	public void updateOrderState5(int orderSeq) {
		sqlSession.update("store.order.updateOrderState5", orderSeq);
		
	}

	@Override
	public void updateOrderState6(int orderSeq) {
		sqlSession.update("store.order.updateOrderState6", orderSeq);
	}

	@Override
	public String getOrderK(int orderSeq) {
		return sqlSession.selectOne("store.order.getOrderK", orderSeq);
	}

	@Override
	public void updateOrderSaleWeight(OrderWeightDTO orderWeight) {
		sqlSession.update("store.order.updateOrderSaleWeight", orderWeight);
		
	}

	@Override
	public void updateOrderSalePrice(OrderPriceDTO orderPrice) {
		sqlSession.update("store.order.updateOrderSalePrice", orderPrice);
		
	}

	@Override
	public void updateOrderState(OrderDetailDTO orderDetail) {
		sqlSession.update("store.order.updateOrderState", orderDetail);
	}

	@Override
	public void updateOrderReleasePrice(int orderSeq, int orderReleasePrice) {
		OrderPriceDTO orderPrice = new OrderPriceDTO();
		orderPrice.setOrderSeq(orderSeq);
		orderPrice.setOrderReleasePrice(orderReleasePrice);
		sqlSession.update("store.order.updateOrderReleasePrice", orderPrice);
		
	}

	@Override
	public Double getOrderWeight(int orderSeq) {
		return sqlSession.selectOne("store.order.getOrderWeight", orderSeq);
	}

	@Override
	public int getOrderSalePrice(int orderSeq) {
		return sqlSession.selectOne("store.order.getOrderSalePrice", orderSeq);
	}

	@Override
	public Double getOrderSaleWeight(int orderSeq) {
		return sqlSession.selectOne("store.order.getOrderSaleWeight", orderSeq);
	}

	@Override
	public List<InqueryOrderDTO> getInqueryOrderByCustomer(
			InqueryOrderDTO inqueryOrder) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("store.order.getInqueryOrderByCustomer", inqueryOrder);
	}

}
