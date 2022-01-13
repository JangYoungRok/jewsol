package com.jewsol.store.orderOption.dao;

import java.util.List;

import com.jewsol.store.orderOption.bean.OrderOptionAddDTO;
import com.jewsol.store.orderOption.bean.OrderOptionCzDTO;
import com.jewsol.store.orderOption.bean.OrderOptionDTO;
import com.jewsol.store.orderOption.bean.OrderOptionPartDTO;
import com.jewsol.store.orderOption.bean.OrderOptionStoneDTO;

public interface OrderOptionDAO {

	int insertOrderOption(OrderOptionDTO insertOrderOption);

	void insertOrderOptionCz(OrderOptionCzDTO orderOptionCzDto);

	void insertOrderOptionStone(OrderOptionStoneDTO orderOptionStoneDto);

	void insertOrderOptionPart(OrderOptionPartDTO orderOptionPartDto);

	void insertOrderOptionAdd(OrderOptionAddDTO orderOptionAddDto);

	void initOrderOptionLabor(int orderOptionSeq);

	void initOrderOptionPrice(int orderOptionSeq);

	int getOrderOptionSeq(int orderSeq);

	String getOrderOptionName(int orderOptionSeq);

	List<OrderOptionCzDTO> getOrderOptionCzList(int orderOptionSeq);

	List<OrderOptionStoneDTO> getOrderOptionStoneList(int orderOptionSeq);

	List<OrderOptionPartDTO> getOrderOptionPartList(int orderOptionSeq);

	List<OrderOptionAddDTO> getOrderOptionAddList(int orderOptionSeq);

	void deleteOrderOptionCz(int orderOptionSeq);

	void deleteOrderOptionPart(int orderOptionSeq);

	void deleteOrderOptionAdd(int orderOptionSeq);

	void deleteOrderOption(int orderOptionSeq);

	void deleteOrderOptionStone(int orderOptionSeq);

	int getStoreProductSeq(int orderOptionSeq);

	int getOrderOptionLabor(int orderSeq);

	void updateOrderOption(OrderOptionDTO orderOption);

	int getOrderOptionPrice(int orderOptionSeq);

}
