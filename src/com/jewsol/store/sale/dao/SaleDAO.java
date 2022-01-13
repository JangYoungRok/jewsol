package com.jewsol.store.sale.dao;

import java.util.List;

import com.jewsol.store.sale.bean.OrderForSaleDTO;
import com.jewsol.store.sale.bean.SaleDTO;
import com.jewsol.store.sale.bean.SaleTypeDTO;

public interface SaleDAO {

	List<SaleTypeDTO> getSaleTypeList();

	List<OrderForSaleDTO> getOrderListForSale(int customerSeq);

	void insertSale(SaleDTO sale);

	void deleteSaleSheet(int saleSheetSeq);
	
	List<Integer> getOrderSeqList(int saleSheetSeq);

}
