package com.jewsol.store.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.common.util.BasicUtil;
import com.jewsol.core.plate.service.PlateService;
import com.jewsol.store.order.bean.InqueryOrderDTO;
import com.jewsol.store.order.bean.OrderDTO;
import com.jewsol.store.order.bean.OrderDetailDTO;
import com.jewsol.store.order.bean.OrderPriceDTO;
import com.jewsol.store.order.bean.OrderWeightDTO;
import com.jewsol.store.order.bean.ViewOrderDTO;
import com.jewsol.store.order.dao.OrderDAO;
import com.jewsol.store.orderOption.service.OrderOptionService;

@Service
public class OrderService {
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private OrderOptionService orderOptionService;
	@Autowired
	private PlateService plateService;
	@Autowired
	private BasicUtil util;

	public int insertOrder(OrderDTO insertOrderDTO) {
		return orderDao.insertOrder(insertOrderDTO);
	}

	public void insertOrderDetail(OrderDetailDTO insertOrderDetailDto) {
		orderDao.insertOrderDetail(insertOrderDetailDto);
		
	}

	public void insertOrderPrice(int orderSeq) {
		orderDao.insertOrderPrice(orderSeq);
		
	}

	public void insertOrderWeight(int orderSeq) {
		orderDao.insertOrderWeight(orderSeq);
		
	}

	public void updateOrderCzWeight(int orderSeq) {
		orderDao.updateOrderCzWeight(orderSeq);
		
	}

	public List<ViewOrderDTO> getTempOrderList(int originalSheetSeq) {
		return orderDao.getTempOrderList(originalSheetSeq);
	}

	public OrderDTO getOrder(int orderSeq) {
		return orderDao.getOrder(orderSeq);
	}

	public OrderDetailDTO getOrderDetail(int orderSeq) {
		return orderDao.getOrderDetail(orderSeq);
	}

	public void updateOrder(OrderDTO updateOrder) {
		orderDao.updateOrder(updateOrder);
	}

	public void updateOrderDetail(OrderDetailDTO updateOtderDetail) {
		orderDao.updateOrderDetail(updateOtderDetail);
	}

	public void deleteOrderWeight(int orderSeq) {
		orderDao.deleteOrderWeight(orderSeq);
	}

	public void deleteOrderPrice(int orderSeq) {
		orderDao.deleteOrderPrice(orderSeq);
	}

	public void deleteOrderDetail(int orderSeq) {
		orderDao.deleteOrderDetail(orderSeq);
	}

	public void deleteOrder(int orderSeq) {
		orderDao.deleteOrder(orderSeq);
	}

	public void registerOrder(int originalSheetSeq) {
		orderDao.registerOrder(originalSheetSeq);
	}

	public List<InqueryOrderDTO> getInqueryOrder(String productCode, String branchName) {
		return orderDao.getInqueryOrder(productCode, branchName);
	}

	public void updateOrderState5(int orderSeq, int releaseSheetSeq) {
		orderDao.updateOrderState5(orderSeq);
		orderDao.updateReleaseSheetSeqInOrder(orderSeq, releaseSheetSeq);
	}

	public void updateOrderState6(int orderSeq) {
		orderDao.updateOrderState6(orderSeq);
		
	}

	public String getOrderK(int orderSeq) {
		return orderDao.getOrderK(orderSeq);
	}

	public void updateOrderSaleWeight(String[] orderSeqArray,
			String[] orderSaleWeightArray) {
		OrderWeightDTO orderWeight = new OrderWeightDTO();
		for(int i = 0; i < orderSeqArray.length; i++){
			int orderSeq = Integer.parseInt(orderSeqArray[i]);
			Double orderSaleWeight = Double.parseDouble(orderSaleWeightArray[i]);
			
			orderWeight.setOrderSeq(orderSeq);
			orderWeight.setOrderSaleWeight(orderSaleWeight);
			orderDao.updateOrderSaleWeight(orderWeight);
		}
	}

	public void updateOrderPrice(String[] orderSeqArray,
			String[] orderSalePriceArray) {
		OrderPriceDTO orderPrice = new OrderPriceDTO();
		for(int i = 0; i < orderSeqArray.length; i++){
			int orderSeq = Integer.parseInt(orderSeqArray[i]);
			int orderSalePrice = Integer.parseInt(orderSalePriceArray[i]);
			
			orderPrice.setOrderSeq(orderSeq);
			orderPrice.setOrderSalePrice(orderSalePrice);
			orderDao.updateOrderSalePrice(orderPrice);
		}
		
	}

	public void updateOrderState(String[] orderSeqArray, int orderState) {
		OrderDetailDTO orderDetail = new OrderDetailDTO();
		for(String value : orderSeqArray){
			int orderSeq = Integer.parseInt(value);
			orderDetail.setOrderSeq(orderSeq);
			orderDetail.setOrderState(orderState);
			orderDao.updateOrderState(orderDetail);
		}
	}

	public void updateOrderReleasePrice(int orderSeq, int orderReleasePrice) {
		orderDao.updateOrderReleasePrice(orderSeq, orderReleasePrice);
		
	}

	public void updateOrderReleasePrice(int orderOptionSeq,
			OrderDetailDTO insertOrderDetailDto) {
		int orderReleasePrice = 0;
		int orderOptionPrice = orderOptionService.getOrderOptionPrice(orderOptionSeq);
		int platePrice = plateService.getPlatePrice(insertOrderDetailDto.getPlateSeq());
		if(insertOrderDetailDto.getOrderHalf().equals("T")){
			orderReleasePrice = (int)(orderOptionPrice * 0.5);
		}else{
			orderReleasePrice = orderOptionPrice;
		}
		
		orderReleasePrice = orderReleasePrice + platePrice;
		orderReleasePrice = util.intCut(orderReleasePrice);
		
		updateOrderReleasePrice(insertOrderDetailDto.getOrderSeq(), orderReleasePrice);
		
		
	}

	public void initOrderSalePrice(List<Integer> orderSeqList) {
		int orderSalePrice = 0;
		OrderPriceDTO orderPrice = new OrderPriceDTO();
		for(int orderSeq : orderSeqList){
			orderPrice.setOrderSeq(orderSeq);
			orderPrice.setOrderSalePrice(orderSalePrice);
			orderDao.updateOrderSalePrice(orderPrice);
			
		}
	}
	
	public void initOrderSaleWeight(List<Integer> orderSeqList){
		OrderWeightDTO orderWeight = new OrderWeightDTO();
		Double orderSaleWeight = 0.0;
		for(int orderSeq : orderSeqList){
			orderWeight.setOrderSeq(orderSeq);
			orderWeight.setOrderSaleWeight(orderSaleWeight);
			orderDao.updateOrderSaleWeight(orderWeight);
		}
	}
	
	public void updateOrderState(List<Integer> orderSeqList, int orderState){
		OrderDetailDTO orderDetail = new OrderDetailDTO();
		for(int orderSeq : orderSeqList){
			orderDetail.setOrderSeq(orderSeq);
			orderDetail.setOrderState(orderState);
			orderDao.updateOrderState(orderDetail);
		}
	}

	public Double getOrderWeight(int orderSeq) {
		return orderDao.getOrderWeight(orderSeq);
	}

	public int getOrderSalePrice(int orderSeq) {
		return orderDao.getOrderSalePrice(orderSeq);
	}

	public Double getOrderSaleWeight(int orderSeq) {
		return orderDao.getOrderSaleWeight(orderSeq);
	}

	public List<InqueryOrderDTO> getInqueryOrderByCustomer(
			InqueryOrderDTO inqueryOrder) {
		return orderDao.getInqueryOrderByCustomer(inqueryOrder);
	}

}
