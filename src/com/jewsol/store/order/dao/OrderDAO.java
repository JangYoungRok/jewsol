package com.jewsol.store.order.dao;

import java.util.List;

import com.jewsol.store.order.bean.InqueryOrderDTO;
import com.jewsol.store.order.bean.OrderDTO;
import com.jewsol.store.order.bean.OrderDetailDTO;
import com.jewsol.store.order.bean.OrderPriceDTO;
import com.jewsol.store.order.bean.OrderWeightDTO;
import com.jewsol.store.order.bean.ViewOrderDTO;

public interface OrderDAO {

	int insertOrder(OrderDTO insertOrderDTO);

	void insertOrderDetail(OrderDetailDTO insertOrderDetailDto);

	void insertOrderPrice(int orderSeq);

	void insertOrderWeight(int orderSeq);

	void updateOrderCzWeight(int orderSeq);

	List<ViewOrderDTO> getTempOrderList(int originalSheetSeq);

	OrderDTO getOrder(int orderSeq);

	OrderDetailDTO getOrderDetail(int orderSeq);

	void updateOrder(OrderDTO updateOrder);

	void updateOrderDetail(OrderDetailDTO updateOtderDetail);

	void deleteOrderWeight(int orderSeq);

	void deleteOrderPrice(int orderSeq);

	void deleteOrderDetail(int orderSeq);

	void deleteOrder(int orderSeq);

	void registerOrder(int originalSheetSeq);

	List<InqueryOrderDTO> getInqueryOrder(String productCode, String branchName);

	void updateOrderState5(int orderSeq);
	
	void updateReleaseSheetSeqInOrder(int orderSeq, int releaseSheetSeq);

	void updateOrderState6(int orderSeq);

	String getOrderK(int orderSeq);

	void updateOrderSaleWeight(OrderWeightDTO orderWeight);

	void updateOrderSalePrice(OrderPriceDTO orderSPrice);

	void updateOrderState(OrderDetailDTO orderDetail);

	void updateOrderReleasePrice(int orderSeq, int orderReleasePrice);

	Double getOrderWeight(int orderSeq);

	int getOrderSalePrice(int orderSeq);

	Double getOrderSaleWeight(int orderSeq);

	List<InqueryOrderDTO> getInqueryOrderByCustomer(InqueryOrderDTO inqueryOrder);

}
