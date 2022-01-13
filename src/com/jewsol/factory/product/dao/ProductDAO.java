package com.jewsol.factory.product.dao;

import java.util.List;

import com.jewsol.factory.product.bean.CategoryDTO;
import com.jewsol.factory.product.bean.ProductCzSizeDTO;
import com.jewsol.factory.product.bean.ProductDTO;
import com.jewsol.factory.product.bean.ProductPartAttributeDTO;
import com.jewsol.factory.product.bean.ProductStoneSizeDTO;
import com.jewsol.factory.product.bean.SearchProductDTO;
import com.jewsol.factory.product.bean.TypeDTO;
import com.jewsol.factory.product.bean.TypeMainDTO;

public interface ProductDAO {

	List<CategoryDTO> getCategoryList();

	List<TypeDTO> getTypeList();

	int checkOverLapProductName(ProductDTO productDto);

	String getCategory(int categorySeq);

	String getType(int typeSeq);

	int productInsert(ProductDTO productDto);

	void insertProductCzSize(ProductCzSizeDTO productCzSizeDto);

	void insertProductStoneSize(ProductStoneSizeDTO productStoneSizeDto);

	void insertProductPartAttribute(ProductPartAttributeDTO partAttributeDto);

	List<TypeMainDTO> getTypeMainList();

	List<ProductDTO> getProductList(int startNum, int endNum);

	List<ProductDTO> searchProductBoard(SearchProductDTO searchProductDto);

	ProductDTO getProduct(int productSeq);

	List<ProductCzSizeDTO> getProductCzSizeList(int productSeq);

	List<ProductStoneSizeDTO> getProductStoneSizeList(int productSeq);

	List<ProductPartAttributeDTO> getProductPartAttributeList(int productSeq);

	int getCategorySeq(String category);

	int getTotalRow(SearchProductDTO searchProductDto);

	int getProductSeq(ProductDTO productDto);

	int getTypeMainSeq(int typeSeq);

	void notInUseProduct(int productSeq);

	void updateProduct(ProductDTO updateProductDto);

	void deleteProductCzSize(int productSeq);

	void deleteProductStoneSize(int productSeq);

	void deleteProductPartAttribute(int productSeq);

	List<CategoryDTO> getSupplierCategoryList();

	int getPtoductSeqInUse(ProductDTO productDto);

	List<CategoryDTO> getFactoryCategoryList();

	int getTypeSeq(int storeProductSeq);

	List<CategoryDTO> getCategoryListTypeT();

	void updateProductDetail(ProductDTO updateProductDto);

}
