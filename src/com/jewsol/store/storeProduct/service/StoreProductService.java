package com.jewsol.store.storeProduct.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.add.bean.AddDTO;
import com.jewsol.store.add.service.AddService;
import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.bean.CzSizeDTO;
import com.jewsol.store.cz.service.CzService;
import com.jewsol.store.part.bean.PartAttributeDTO;
import com.jewsol.store.part.bean.PartDTO;
import com.jewsol.store.part.service.PartService;
import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.bean.StoneSizeDTO;
import com.jewsol.store.stone.service.StoneService;
import com.jewsol.factory.product.bean.CategoryDTO;
import com.jewsol.factory.product.bean.ProductCzSizeDTO;
import com.jewsol.factory.product.bean.ProductDTO;
import com.jewsol.factory.product.bean.ProductPartAttributeDTO;
import com.jewsol.factory.product.bean.ProductStoneSizeDTO;
import com.jewsol.factory.product.bean.TypeDTO;
import com.jewsol.factory.product.dao.ProductDAO;
import com.jewsol.factory.product.service.ProductService;
import com.jewsol.store.storeProduct.bean.StoreProductDTO;
import com.jewsol.store.storeProduct.dao.StoreProductDAO;
import com.jewsol.store.supplier.service.SupplierService;

@Service
public class StoreProductService {
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private ProductService productService;
	@Autowired
	private StoreProductDAO storeProductDao;
	@Autowired
	private CzService czService;
	@Autowired
	private StoneService stoneService;
	@Autowired
	private PartService partService;
	@Autowired
	private AddService addService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private UtilSession utilSession;
	
	public void initRegisterStoreProductForm(ModelMap model) {
		List<CategoryDTO> categoryList = productDao.getCategoryList();
		//typeList
		List<TypeDTO> typeList = productDao.getTypeList();
		
		model.put("categoryList", categoryList);
		model.put("typeList", typeList);
		
	}

	public void checkStoreProduct(ModelMap model, int categorySeq,
			int typeSeq, String productCode, int branchSeq) {
		
		int productSeq = productService.getPtoductSeqInUse(categorySeq, typeSeq,productCode);
		int storeProductSeq = getStoreProductSeq(categorySeq, typeSeq,productCode, branchSeq);
		int searchStoreProductState = 0;
		String systemMessage = "";
		
		if(productSeq == 0){
			searchStoreProductState = 2;
			systemMessage = "공장에 입력 하지 않은 제품 입니다.";
			
		}else{
			if(storeProductSeq == 0){
				searchStoreProductState = 1;
				systemMessage = "검색이 완료 되었습니다.";
				List<CzSizeDTO> czSizeList = czService.getCzSizeList();
				List<StoneSizeDTO> stoneSizeList = stoneService.getStoneSizeList();
				int typeMainSeq = productService.getTypeMainSeq(typeSeq);
				List<PartAttributeDTO> partAttributeList = partService.getPartAttributeList(typeMainSeq);
				List<AddDTO> addList = addService.getAddList(branchSeq);
				
				productService.getProductModel(model, productSeq);
				List<ProductCzSizeDTO> productCzSizeList = productDao.getProductCzSizeList(productSeq);
				List<ProductStoneSizeDTO> productStoneSizeList = productDao.getProductStoneSizeList(productSeq);
				List<ProductPartAttributeDTO> productPartAttributeList = productDao.getProductPartAttributeList(productSeq);
				
				List<List<CzDTO>> czListResponse = getCzListResponse(productCzSizeList, branchSeq);
				List<List<StoneDTO>> stoneListResponse = getStoneListResponse(productStoneSizeList, branchSeq);
				List<List<PartDTO>> partListResponse = getPartListResponse(productPartAttributeList, branchSeq);
				
				model.put("czSizeList", czSizeList);
				model.put("stoneSizeList", stoneSizeList);
				model.put("partAttributeList", partAttributeList);
				model.put("addList", addList);
				
				model.put("productCzSizeList",productCzSizeList);
				model.put("productStoneSizeList",productStoneSizeList);
				model.put("productPartAttributeList",productPartAttributeList);
				
				model.put("czListResponse",czListResponse);
				model.put("stoneListResponse",stoneListResponse);
				model.put("partListResponse",partListResponse);
				
			}else{
				searchStoreProductState = 2;
				systemMessage = "이미 등록 된 제품 입니다.";
			}
		}
		
		model.put("searchStoreProductState",searchStoreProductState);
		model.put("systemMessage",systemMessage);
	}
	
	public ArrayList<List<CzDTO>> getCzListResponse(List<ProductCzSizeDTO> productCzSizeList, int branchSeq){
		
		ArrayList<List<CzDTO>> czListResponse = new ArrayList<List<CzDTO>>(productCzSizeList.size());
		for(int i = 0; i < productCzSizeList.size(); i++){
			List<CzDTO> list = czService.getCzListByCzSizeSeq(productCzSizeList.get(i).getCzSizeSeq(), branchSeq);
			czListResponse.add(i, list);
		}
		return czListResponse;
	}
	
	public ArrayList<List<StoneDTO>> getStoneListResponse(List<ProductStoneSizeDTO> productStoneSizeList, int branchSeq){
		StoneDTO stone = new StoneDTO();
		stone.setBranchSeq(branchSeq);
		ArrayList<List<StoneDTO>> stoneListResponse = new ArrayList<List<StoneDTO>>(productStoneSizeList.size());
		for(int i = 0; i < productStoneSizeList.size(); i++){
			stone.setStoneSizeSeq(productStoneSizeList.get(i).getStoneSizeSeq());
			List<StoneDTO> list = stoneService.getStoneListByStoneSizeSeq(stone);
			stoneListResponse.add(i, list);
		}
		return stoneListResponse;
	}
	
	public ArrayList<List<PartDTO>> getPartListResponse(List<ProductPartAttributeDTO> productPartAttributeList, int branchSeq){
		ArrayList<List<PartDTO>> partListResponse = new ArrayList<List<PartDTO>>(productPartAttributeList.size());
		PartDTO part = new PartDTO();
		part.setBranchSeq(branchSeq);
		for(int i = 0; i < productPartAttributeList.size(); i++){
			part.setPartAttributeSeq(productPartAttributeList.get(i).getPartAttributeSeq());
			List<PartDTO> list = partService.getPartListByPartAttributeSeq(part);
			partListResponse.add(i, list);
		}
		return partListResponse;
	}

	public int getStoreProductSeq(int categorySeq, int typeSeq,
			String productCode, int branchSeq) {
		StoreProductDTO storeProductDto = new StoreProductDTO();
		storeProductDto.setCategorySeq(categorySeq);
		storeProductDto.setTypeSeq(typeSeq);
		storeProductDto.setStoreProductCode(productCode);
		storeProductDto.setBranchSeq(branchSeq);
		
		return storeProductDao.getStoreProductSeq(storeProductDto);
	}

	public int registerStoreProduct(int categorySeq, int typeSeq,
			String productCode, int storeProductPrice, HttpSession session) {
		
		int branchSeq = utilSession.getBranchSeq(session);
		int supplierSeq = 0;
		int productSeq = productService.getPtoductSeq(categorySeq, typeSeq, productCode);
		String storeProductCode = productCode;
		String storeProductName = productService.getProductName(categorySeq, productCode, typeSeq);
		String storeProductOriginalName = storeProductName;
		int storeProductLabor = (int)(storeProductPrice * 0.7);
		
		ProductDTO productDto = productService.getProduct(productSeq);
		String storeProductImage = productDto.getProductImage();
		Double storeProductAveWeight = productDto.getProductAveWeight();
		
		StoreProductDTO storeProductDto = new StoreProductDTO();
		storeProductDto.setBranchSeq(branchSeq);
		storeProductDto.setSupplierSeq(supplierSeq);
		storeProductDto.setProductSeq(productSeq);
		storeProductDto.setCategorySeq(categorySeq);
		storeProductDto.setTypeSeq(typeSeq);
		storeProductDto.setStoreProductCode(storeProductCode);
		storeProductDto.setStoreProductName(storeProductName);
		storeProductDto.setStoreProductOriginalName(storeProductOriginalName);
		storeProductDto.setStoreProductImage(storeProductImage);
		storeProductDto.setStoreProductLabor(storeProductLabor);
		storeProductDto.setStoreProductAveWeight(storeProductAveWeight);
		storeProductDto.setStoreProductPrice(storeProductPrice);
		
		return storeProductDao.insertStoreProduct(storeProductDto);
		
	}

	public boolean checkArrayLength(String[] stringArr){
		boolean result =false;
		
		if(stringArr[0] != ""){
			result = true;
		}
		
		return result;
	}
	
	public String getSystemMessage(int state, String storeProductName) {
		String systemMessage = "";
		
		if(state == 1){
			systemMessage = "";
		}else if(state == 2){
			systemMessage = storeProductName + " 입력 되었습니다.";
		}
		
		return systemMessage;
	}

	public List<CategoryDTO> getSupplierCategoryList() {
		return productDao.getSupplierCategoryList();
	}

	public int checkOverLapStoreProductName(StoreProductDTO storeProductDto) {
		return storeProductDao.checkOverLapStoreProductName(storeProductDto);
	}

	public int insertStoreProduct(StoreProductDTO storeProductDto) {
		
		return storeProductDao.insertStoreProduct(storeProductDto);
	}

	public int getSearchStoreProductState(int storeProductSeq) {
		
		int searchStoreProductState = 0;
		
		if(storeProductSeq == 0){
			searchStoreProductState = 2;
		}else{
			String inUse = storeProductDao.getStoreProductInUse(storeProductSeq);
			if(inUse.equals("T")){
				searchStoreProductState = 1;
			}else{
				searchStoreProductState = 3;
			}
			
		}
		
		return searchStoreProductState;
	}

	public StoreProductDTO getSearchStoreProduct(int storeProductSeq) {
		
		return storeProductDao.getSearchStoreProduct(storeProductSeq);
	}

	public String getStoreProductName(int storeProductSeq) {
		return storeProductDao.getStoreProductName(storeProductSeq);
	}

	public void notInUseStoreProduct(int storeProductSeq) {
		storeProductDao.notInUseStoreProduct(storeProductSeq);
		
	}

	public void updateStoreProduct(StoreProductDTO storeProductDto) {
		storeProductDao.updateStoreProduct(storeProductDto);
	}

	public StoreProductDTO getStoreProduct(int storeProductSeq) {
		return storeProductDao.getStoreProduct(storeProductSeq);
	}

	public int getTypeSeq(int storeProductSeq) {
		return storeProductDao.getTypeSeq(storeProductSeq);
	}

	public List<StoreProductDTO> getStoreProductKeyWord(String keyword, int branchSeq) {
		return storeProductDao.getStoreProductKeyWord(keyword, branchSeq);
	}

	public int getCountStoreProductName(StoreProductDTO storeProduct) {
		return storeProductDao.getCountStoreProductName(storeProduct);
	}

	public List<StoreProductDTO> getStoreProductList(int productSeq) {
		return storeProductDao.getStoreProductList(productSeq);
	}

	public void updateStoreProductInFactory(StoreProductDTO updateStoreProduct) {
		storeProductDao.updateStoreProductInFactory(updateStoreProduct);
		
	}

	public String getStoreProductImage(int storeProductSeq) {
		
		return storeProductDao.getStoreProductImage(storeProductSeq);
	}

}