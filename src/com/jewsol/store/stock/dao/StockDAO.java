package com.jewsol.store.stock.dao;

import com.jewsol.factory.releaseSheet.bean.ReleaseSheetDTO;
import com.jewsol.store.stock.bean.StockDTO;

public interface StockDAO {

	void insertStock(StockDTO stock);

	void updateToStockReleaseSheet(ReleaseSheetDTO releaseSheet);

	void deleteStock(int orderSeq);

}
