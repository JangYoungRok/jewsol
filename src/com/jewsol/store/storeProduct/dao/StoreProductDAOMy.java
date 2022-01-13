package com.jewsol.store.storeProduct.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.factory.product.bean.SearchProductDTO;
import com.jewsol.store.storeProduct.bean.StoreProductDTO;

@Repository
public class StoreProductDAOMy implements StoreProductDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int getStoreProductSeq(StoreProductDTO storeProductDto) {
		return sqlSession.selectOne("store.storeProduct.getStoreProductSeq", storeProductDto);
	}

	@Override
	public int insertStoreProduct(StoreProductDTO storeProductDto) {
		sqlSession.insert("store.storeProduct.insertStoreProduct", storeProductDto);
		return storeProductDto.getStoreProductSeq();
	}


	@Override
	public int checkOverLapStoreProductName(StoreProductDTO storeProductDto) {
		
		return sqlSession.selectOne("store.storeProduct.checkOverLapStoreProductName", storeProductDto);
	}

	@Override
	public StoreProductDTO getSearchStoreProduct(int storeProductSeq) {
		
		return sqlSession.selectOne("store.storeProduct.getSearchStoreProduct", storeProductSeq);
	}

	@Override
	public String getStoreProductName(int storeProductSeq) {
		return sqlSession.selectOne("store.storeProduct.getStoreProductName", storeProductSeq);
	}

	@Override
	public void notInUseStoreProduct(int storeProductSeq) {
		sqlSession.update("store.storeProduct.notInUseStoreProduct", storeProductSeq);
		
	}

	@Override
	public String getStoreProductInUse(int storeProductSeq) {
		return sqlSession.selectOne("store.storeProduct.getStoreProductInUse", storeProductSeq);
	}

	@Override
	public void updateStoreProduct(StoreProductDTO storeProductDto) {
		sqlSession.update("store.storeProduct.updateStoreProduct", storeProductDto);
		
	}

	@Override
	public StoreProductDTO getStoreProduct(int storeProductSeq) {
		return sqlSession.selectOne("store.storeProduct.getStoreProduct", storeProductSeq);
	}

	@Override
	public List<StoreProductDTO> searchStoreProductBoard(
			SearchProductDTO searchProductDto) {
		return sqlSession.selectList("store.storeProduct.searchStoreProductBoard",searchProductDto);
	}

	@Override
	public int getTotalRow(SearchProductDTO searchProductDto) {
		return sqlSession.selectOne("store.storeProduct.getTotalRow", searchProductDto);
	}

	@Override
	public int getTypeSeq(int storeProductSeq) {
		return sqlSession.selectOne("store.storeProduct.getTypeSeq", storeProductSeq);
	}

	@Override
	public List<StoreProductDTO> getStoreProductKeyWord(String keyword, int branchSeq) {
		StoreProductDTO storeProduct = new StoreProductDTO();
		storeProduct.setStoreProductCode(keyword);
		storeProduct.setBranchSeq(branchSeq);
		return sqlSession.selectList("store.storeProduct.getStoreProductKeyWord", storeProduct);
	}

	@Override
	public int getCountStoreProductName(StoreProductDTO storeProduct) {
		
		return sqlSession.selectOne("store.storeProduct.getCountStoreProductName", storeProduct);
	}

	@Override
	public List<StoreProductDTO> getStoreProductList(int productSeq) {
		return sqlSession.selectList("store.storeProduct.getStoreProductList", productSeq);
	}

	@Override
	public void updateStoreProductInFactory(StoreProductDTO updateStoreProduct) {
		sqlSession.update("store.storeProduct.updateStoreProductInFactory", updateStoreProduct);
		
	}

	@Override
	public String getStoreProductImage(int storeProductSeq) {
		
		return sqlSession.selectOne("store.storeProduct.getStoreProductImage", storeProductSeq);
	}

	

}
