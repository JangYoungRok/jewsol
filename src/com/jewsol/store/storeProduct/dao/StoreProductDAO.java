package com.jewsol.store.storeProduct.dao;

import java.util.List;

import com.jewsol.factory.product.bean.SearchProductDTO;
import com.jewsol.store.storeProduct.bean.StoreProductDTO;


public interface StoreProductDAO {

	int getStoreProductSeq(StoreProductDTO storeProductDto);

	int insertStoreProduct(StoreProductDTO storeProductDto);

	int checkOverLapStoreProductName(StoreProductDTO storeProductDto);

	StoreProductDTO getSearchStoreProduct(int storeProductSeq);

	String getStoreProductName(int storeProductSeq);

	void notInUseStoreProduct(int storeProductSeq);

	String getStoreProductInUse(int storeProductSeq);

	void updateStoreProduct(StoreProductDTO storeProductDto);

	StoreProductDTO getStoreProduct(int storeProductSeq);

	List<StoreProductDTO> searchStoreProductBoard(
			SearchProductDTO searchProductDto);

	int getTotalRow(SearchProductDTO searchProductDto);

	int getTypeSeq(int storeProductSeq);

	List<StoreProductDTO> getStoreProductKeyWord(String keyword, int branchSeq);

	int getCountStoreProductName(StoreProductDTO storeProduct);

	List<StoreProductDTO> getStoreProductList(int productSeq);

	void updateStoreProductInFactory(StoreProductDTO updateStoreProduct);

	String getStoreProductImage(int storeProductSeq);

}
