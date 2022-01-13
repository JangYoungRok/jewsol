package com.jewsol.store.stockLocation.dao;

import java.util.List;

import com.jewsol.store.stockLocation.bean.StockLocationDTO;
import com.jewsol.store.stockLocation.bean.StockLocationTypeDTO;

public interface StockLocationDAO {

	List<StockLocationDTO> getStockLocationList(int branchSeq);
	public List<StockLocationTypeDTO> getStockLocationTypeList();
	void insertStockLocation(StockLocationDTO stockLocationDto);
	void notInUseStockLocation(int stockLocationSeq);
	int checkOverLapStockLocationName(StockLocationDTO stockLocation);
	List<StockLocationDTO> getStockLocationListByStockLocationTypeSeq(int stockLocationTypeSeq, int branchSeq);

}
