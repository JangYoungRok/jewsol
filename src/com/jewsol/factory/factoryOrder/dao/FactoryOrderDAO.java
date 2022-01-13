package com.jewsol.factory.factoryOrder.dao;

import java.util.List;

import com.jewsol.factory.factoryOrder.bean.InqueryFactoryOrderDTO;

public interface FactoryOrderDAO {

	int checkOrderDate(String today);

	void insertOrderDate(String today);

	String getOrderClose(String today);

	List<Integer> getOrderSeqRingList(String orderK,
			String orderDate);

	List<Integer> getOrderSeqPendentList(String orderK, String orderDate);
	
	List<Integer> getOrderSeqNecklaceList(String orderK, String orderDate);

	List<Integer> getOrderSeqHurryList(String orderK, String orderDate);

	void updateOrderPanelSeq(int orderSeq, int panelSeq, int orderNumber);

	List<Integer> getOrderSeq10SvList(String orderDate);

	void closeOrderDate(String orderDate);

	List<InqueryFactoryOrderDTO> getFactoryInqueryOrder(String productCode);

	List<Integer> getOrderSeqAnoList(String orderDate);

}
