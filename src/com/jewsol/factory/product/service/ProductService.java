package com.jewsol.factory.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jewsol.store.cz.bean.CzSizeDTO;
import com.jewsol.store.cz.dao.CzDAO;
import com.jewsol.store.part.bean.PartAttributeDTO;
import com.jewsol.store.part.dao.PartDAO;
import com.jewsol.store.stone.bean.StoneSizeDTO;
import com.jewsol.store.stone.dao.StoneDAO;
import com.jewsol.store.storeProduct.bean.StoreProductDTO;
import com.jewsol.store.storeProduct.service.StoreProductService;
import com.jewsol.factory.product.bean.CategoryDTO;
import com.jewsol.factory.product.bean.ProductCzSizeDTO;
import com.jewsol.factory.product.bean.ProductDTO;
import com.jewsol.factory.product.bean.ProductPartAttributeDTO;
import com.jewsol.factory.product.bean.ProductStoneSizeDTO;
import com.jewsol.factory.product.bean.TypeDTO;
import com.jewsol.factory.product.dao.ProductDAO;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private CzDAO czDao;
	@Autowired
	private StoneDAO stoneDao;
	@Autowired
	private PartDAO partDao;
	@Autowired
	private ProductBoardService productBoradservice;
	@Autowired
	private StoreProductService storeProductService;

	public void initInsertProductFrom(ModelMap model, int typeMainSeq) {
		
		List<CategoryDTO> categoryList = productDao.getCategoryList();
		//typeList
		List<TypeDTO> typeList = productDao.getTypeList();
		//czSizeList
		List<CzSizeDTO> czSizeList = czDao.getCzSizeList();
		List<StoneSizeDTO> stoneSizeList = stoneDao.getStoneSizeList();
		//partAttributeList
		List<PartAttributeDTO> partAttributeList = partDao.getPartAttributeList(typeMainSeq);
		
		//model에 담기
		model.put("categoryList", categoryList);
		model.put("typeList", typeList);
		model.put("czSizeList", czSizeList);
		model.put("stoneSizeList", stoneSizeList);
		model.put("partAttributeList", partAttributeList);
		
	}

	public int checkOverLapProductName(ProductDTO productDto) {
		int overLapResult = productDao.checkOverLapProductName(productDto);
		
		return overLapResult;
	}

	public String getProductName(int categorySeq, String productCode,
			int typeSeq) {
		
		String category = productDao.getCategory(categorySeq);
		String type = productDao.getType(typeSeq);
	
		String productName = category + " " + productCode + " " + type;
	
		return productName;
	}

	public void insertProduct(ProductDTO productDto, MultipartHttpServletRequest request) {
		
		int productSeq = productDao.productInsert(productDto);
		
		insertProductCzSize(productSeq, request);
		insertProductStoneSize(productSeq, request);
		insertProductPartAttribute(productSeq, request);
		
	}

	private void insertProductPartAttribute(int productSeq, MultipartHttpServletRequest request) {
		int pratAttributeSeq = 0;
		
		for(int i = 1; i <= 9; i++){
			String partAttribute ="partAttribute";
			partAttribute = partAttribute + i;
			
			pratAttributeSeq = Integer.parseInt(request.getParameter(partAttribute));
			
			if(pratAttributeSeq > 0){
				pratAttributeSeq = Integer.parseInt(request.getParameter(partAttribute));
				
				ProductPartAttributeDTO partAttributeDto = new ProductPartAttributeDTO();
				
				partAttributeDto.setPartAttributeSeq(pratAttributeSeq);
				partAttributeDto.setProductSeq(productSeq);
				productDao.insertProductPartAttribute(partAttributeDto);
			}
		}
	}

	private void insertProductStoneSize(int productSeq, MultipartHttpServletRequest request) {
		int stoneQtyVal = 0;
		int stoneSizeSeq = 0;
		
		for(int i = 1; i <= 9; i++){
			String stoneQty ="stoneQty";
			String stoneSize = "stoneSize";
			
			stoneQty = stoneQty + i;
			stoneSize = stoneSize + i;
			
			if(!request.getParameter(stoneQty).equals("")){
				stoneQtyVal = Integer.parseInt(request.getParameter(stoneQty));
			}
			stoneSizeSeq = Integer.parseInt(request.getParameter(stoneSize));
			
			if(stoneQtyVal > 0 && stoneSizeSeq > 0){
				
				stoneSizeSeq = Integer.parseInt(request.getParameter(stoneSize));
				stoneQtyVal = Integer.parseInt(request.getParameter(stoneQty));
				
				ProductStoneSizeDTO productStoneSizeDto = new ProductStoneSizeDTO();
				
				productStoneSizeDto.setProductSeq(productSeq);
				productStoneSizeDto.setStoneSizeSeq(stoneSizeSeq);
				productStoneSizeDto.setStoneQty(stoneQtyVal);
				
				productDao.insertProductStoneSize(productStoneSizeDto);
				
			}
		}
		
	}

	public void insertProductCzSize(int productSeq, MultipartHttpServletRequest request) {
		int czQtyVal = 0;
		int czSizeSeq = 0;
		
		for(int i = 1; i <= 9; i++){
			String czQty ="czQty";
			String czSize = "czSize";
			
			czQty = czQty + i;
			czSize = czSize + i;
			
			if(!request.getParameter(czQty).equals("")){
				czQtyVal = Integer.parseInt(request.getParameter(czQty));
			}
			czSizeSeq = Integer.parseInt(request.getParameter(czSize));
			
			if(czQtyVal > 0 && czSizeSeq > 0){
				
				ProductCzSizeDTO productCzSizeDto = new ProductCzSizeDTO();
				
				productCzSizeDto.setProductSeq(productSeq);
				productCzSizeDto.setCzSizeSeq(czSizeSeq);
				productCzSizeDto.setCzQty(czQtyVal);
				
				productDao.insertProductCzSize(productCzSizeDto);
				
			}
		}
	
	}

	public List<PartAttributeDTO> getPartAttributeList(int typeSeq) {
		
		int typeMainSeq = partDao.getTypeMainSeq(typeSeq);
		
		return partDao.getPartAttributeList(typeMainSeq);
	}

	public void initUpdateProductFrom(ModelMap model) {
		List<CategoryDTO> categoryList = productDao.getCategoryList();
		List<TypeDTO> typeList = productDao.getTypeList();
		
		model.put("categoryList", categoryList);
		model.put("typeList", typeList);
		
	}

	public void getUpdateProduct(ModelMap model, int categorySeq, int typeSeq,
			String productCode) {
		
		int productSeq = getPtoductSeq(categorySeq, typeSeq,productCode);
		int searchProductState = 0;
		
		if(productSeq == 0){
			searchProductState = 2;
		}else{
			searchProductState = 1;
			productBoradservice.productDetail(model, productSeq);
		}
		
		model.put("searchProductState", searchProductState);
	}
	
	public void getProductModel(ModelMap model, int productSeq) {
		ProductDTO product = productDao.getProduct(productSeq);
		model.put("product",product);
	}
	
	public int getPtoductSeq( int categorySeq, int typeSeq,
			String productCode){
		ProductDTO productDto = new ProductDTO();
		
		productDto.setCategorySeq(categorySeq);
		productDto.setTypeSeq(typeSeq);
		productDto.setProductCode(productCode);
		
		return productDao.getProductSeq(productDto);
	}

	public void getProductOption(ModelMap model, int categorySeq, int typeSeq,
			String productCode) {
		int productSeq = getPtoductSeq(categorySeq, typeSeq,productCode);
		getProductOptionModel(model, productSeq);
	}
	
	public void getProductOptionModel(ModelMap model, int productSeq){
		List<ProductCzSizeDTO> productCzSizeList = productDao.getProductCzSizeList(productSeq);
		List<ProductStoneSizeDTO> productStoneSizeList = productDao.getProductStoneSizeList(productSeq);
		List<ProductPartAttributeDTO> productPartAttributeLsit = productDao.getProductPartAttributeList(productSeq);
	
		model.put("productCzSizeList",productCzSizeList);
		model.put("productStoneSizeList",productStoneSizeList);
		model.put("productPartAttributeLsit",productPartAttributeLsit);
	}

	public int getTypeMainSeq(int typeSeq) {
		
		return productDao.getTypeMainSeq(typeSeq);
	}

	public void notInUseProduct(int categorySeq, String productCode, int typeSeq) {
		int productSeq = getPtoductSeq(categorySeq, typeSeq, productCode);
		productDao.notInUseProduct(productSeq);
		
	}

	public void updateProduct(ProductDTO updateProductDto) {
		productDao.updateProduct(updateProductDto);
		
	}

	public void updateProductOption(int productSeq, MultipartHttpServletRequest request) {
		productDao.deleteProductCzSize(productSeq);
		productDao.deleteProductStoneSize(productSeq);
		productDao.deleteProductPartAttribute(productSeq);
		
		insertProductCzSize(productSeq, request);
		insertProductStoneSize(productSeq, request);
		insertProductPartAttribute(productSeq, request);
	}

	public ProductDTO getProduct(int productSeq) {
		
		return productDao.getProduct(productSeq);
	}

	public List<TypeDTO> getTypeList() {
		
		return productDao.getTypeList();
	}

	public int getPtoductSeqInUse(int categorySeq, int typeSeq,
			String productCode) {
		ProductDTO productDto = new ProductDTO();
		
		productDto.setCategorySeq(categorySeq);
		productDto.setTypeSeq(typeSeq);
		productDto.setProductCode(productCode);
		
		return productDao.getPtoductSeqInUse(productDto);
	}

	public List<CategoryDTO> getCategoryList() {
		
		return productDao.getCategoryList();
	}

	public List<CategoryDTO> getFactoryCategoryList() {
		return productDao.getFactoryCategoryList();
	}

	public int getTypeSeq(int storeProductSeq) {
		return productDao.getTypeSeq(storeProductSeq);
	}

	public void updateProductDetail(ProductDTO updateProductDto) {
		productDao.updateProductDetail(updateProductDto);
		StoreProductDTO updateStoreProduct = new StoreProductDTO();
		List<StoreProductDTO> storeProductList = storeProductService.getStoreProductList(updateProductDto.getProductSeq());
		for(StoreProductDTO storeProduct : storeProductList){
			updateStoreProduct.setStoreProductSeq(storeProduct.getStoreProductSeq());
			updateStoreProduct.setStoreProductImage(updateProductDto.getProductImage());
			updateStoreProduct.setStoreProductAveWeight(updateProductDto.getProductAveWeight());
			
			storeProductService.updateStoreProductInFactory(updateStoreProduct);
			
			/*String storeProductImage = storeProductService.getStoreProductImage(storeProduct.getStoreProductSeq());
			if(storeProductImage.equals("noPic.jpg")){
				updateStoreProduct.setStoreProductSeq(storeProduct.getStoreProductSeq());
				updateStoreProduct.setStoreProductImage(updateProductDto.getProductImage());
				updateStoreProduct.setStoreProductAveWeight(updateProductDto.getProductAveWeight());
				
				storeProductService.updateStoreProductInFactory(updateStoreProduct);
				
			}*/
		}
		
	}
}
