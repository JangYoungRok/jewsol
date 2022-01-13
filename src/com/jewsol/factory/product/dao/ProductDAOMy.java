package com.jewsol.factory.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.factory.product.bean.CategoryDTO;
import com.jewsol.factory.product.bean.ProductCzSizeDTO;
import com.jewsol.factory.product.bean.ProductDTO;
import com.jewsol.factory.product.bean.ProductPartAttributeDTO;
import com.jewsol.factory.product.bean.ProductStoneSizeDTO;
import com.jewsol.factory.product.bean.SearchProductDTO;
import com.jewsol.factory.product.bean.TypeDTO;
import com.jewsol.factory.product.bean.TypeMainDTO;


@Repository
public class ProductDAOMy implements ProductDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CategoryDTO> getCategoryList() {
		return sqlSession.selectList("factory.product.getCategoryList");
	}

	@Override
	public List<TypeDTO> getTypeList() {
		return sqlSession.selectList("factory.product.getTypeList");
	}

	@Override
	public int checkOverLapProductName(ProductDTO productDto) {
		
		return sqlSession.selectOne("factory.product.checkOverLapProductName", productDto);
	}

	@Override
	public String getCategory(int categorySeq) {
		
		return sqlSession.selectOne("factory.product.getCategory", categorySeq);
	}

	@Override
	public String getType(int typeSeq) {
		return sqlSession.selectOne("factory.product.getType", typeSeq);
	}

	@Override
	public int productInsert(ProductDTO productDto) {
		sqlSession.insert("factory.product.productInsert", productDto);
		return productDto.getProductSeq();
	}

	@Override
	public void insertProductCzSize(ProductCzSizeDTO productCzSizeDto) {
		sqlSession.insert("factory.product.insertProductCzSize", productCzSizeDto);
		
	}

	@Override
	public void insertProductStoneSize(ProductStoneSizeDTO productStoneSizeDto) {
		sqlSession.insert("factory.product.insertProductStoneSize", productStoneSizeDto);
		
	}

	@Override
	public void insertProductPartAttribute(
			ProductPartAttributeDTO partAttributeDto) {
		sqlSession.insert("factory.product.insertProductPartAttribute", partAttributeDto);
		
	}

	@Override
	public List<TypeMainDTO> getTypeMainList() {
		return sqlSession.selectList("factory.product.getTypeMainList");
	}

	@Override
	public List<ProductDTO> getProductList(int startNum, int endNum) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("endNum",endNum);
		map.put("startNum", startNum);
		
		return sqlSession.selectList("factory.product.getProductList",map);
	}

	@Override
	public List<ProductDTO> searchProductBoard(SearchProductDTO searchProductDto) {
		return sqlSession.selectList("factory.product.searchProductBoard",searchProductDto);
	}

	@Override
	public ProductDTO getProduct(int productSeq) {
		
		return sqlSession.selectOne("factory.product.getProduct", productSeq);
	}
	
	@Override
	public List<ProductCzSizeDTO> getProductCzSizeList(int productSeq) {
		
		return sqlSession.selectList("factory.product.getProductCzSizeList", productSeq);
	}
	
	@Override
	public List<ProductStoneSizeDTO> getProductStoneSizeList(int productSeq) {
		
		return sqlSession.selectList("factory.product.getProductStoneSizeList", productSeq);
	}
	
	@Override
	public List<ProductPartAttributeDTO> getProductPartAttributeList(int productSeq) {
		
		return sqlSession.selectList("factory.product.getProductPartAttributeList", productSeq);
	}

	@Override
	public int getCategorySeq(String category) {
		return sqlSession.selectOne("factory.product.getCategorySeq", category);
	}

	@Override
	public int getTotalRow(SearchProductDTO searchProductDto) {
		return sqlSession.selectOne("factory.product.getTotalRow", searchProductDto);
	}

	@Override
	public int getProductSeq(ProductDTO productDto) {
		return sqlSession.selectOne("factory.product.getProductSeq", productDto);
	}

	@Override
	public int getTypeMainSeq(int typeSeq) {
		return sqlSession.selectOne("factory.product.getTypeMainSeq", typeSeq);
	}

	@Override
	public void notInUseProduct(int productSeq) {
		sqlSession.update("factory.product.notInUseProduct", productSeq);
	
	}

	@Override
	public void updateProduct(ProductDTO updateProductDto) {
		sqlSession.update("factory.product.updateProduct", updateProductDto);
	}

	@Override
	public void deleteProductCzSize(int productSeq) {
		sqlSession.delete("factory.product.deleteProductCzSize", productSeq);
		
	}

	@Override
	public void deleteProductStoneSize(int productSeq) {
		sqlSession.delete("factory.product.deleteProductStoneSize", productSeq);
		
	}

	@Override
	public void deleteProductPartAttribute(int productSeq) {
		sqlSession.delete("factory.product.deleteProductPartAttribute", productSeq);
		
	}

	@Override
	public List<CategoryDTO> getSupplierCategoryList() {
		return sqlSession.selectList("factory.product.getSupplierCategoryList");
	}

	@Override
	public int getPtoductSeqInUse(ProductDTO productDto) {
		return sqlSession.selectOne("factory.product.getPtoductSeqInUse", productDto);
	}

	@Override
	public List<CategoryDTO> getFactoryCategoryList() {
		return sqlSession.selectList("factory.product.getFactoryCategoryList");
	}

	@Override
	public int getTypeSeq(int storeProductSeq) {
		return sqlSession.selectOne("factory.product.getTypeSeq", storeProductSeq);
	}

	@Override
	public List<CategoryDTO> getCategoryListTypeT() {
		return sqlSession.selectList("factory.product.getCategoryListTypeT");
	}

	@Override
	public void updateProductDetail(ProductDTO updateProductDto) {
		sqlSession.update("factory.product.updateProductDetail", updateProductDto);
		
	}

	
}
