package com.jewsol.store.saleSheet.dao;

import java.util.List;

import com.jewsol.store.saleSheet.bean.OrderForSaleSheetDTO;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;

public interface SaleSheetDAO {

	List<SaleSheetDTO> getSaleSheetList(int customerSeq);

	SaleSheetDTO getSaleSheet(int saleSheetSeq);

	List<OrderForSaleSheetDTO> getOrderListForSaleSheet(int saleSheetSeq);

	int getLastSaleSheetNumber(String saleDate);

	void updateLastBalance(SaleSheetDTO saleSheet);

	void updateBalance(SaleSheetDTO saleSheet);

	int insertSaleSheet(SaleSheetDTO saleSheet);

	List<Integer> getOrderSeqList(int saleSheetSeq);

	SaleSheetDTO getLastSaleSheet(int customerSeq);

	List<SaleSheetDTO> getSaleSheetListByMonth(int thisYear, int thisMonth, int branchSeq);

	

}
