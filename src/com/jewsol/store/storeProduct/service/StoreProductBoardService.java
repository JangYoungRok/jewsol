package com.jewsol.store.storeProduct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.factory.product.bean.CategoryDTO;
import com.jewsol.factory.product.bean.SearchProductDTO;
import com.jewsol.factory.product.dao.ProductDAO;
import com.jewsol.factory.product.service.ProductBoardService;
import com.jewsol.store.storeProduct.bean.StoreProductDTO;
import com.jewsol.store.storeProduct.dao.StoreProductDAO;

@Service
public class StoreProductBoardService {
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private StoreProductDAO storeProductDao;
	@Autowired
	private ProductBoardService productBoardService;
	@Autowired
	private StoreProductService storeProductService;
	
	private int pageBlock = 10;
	private int pageSize = 16;
	private StringBuffer pagingHTML;
	
	private int getEndNum(int selectedPage){
		return selectedPage * pageSize;
	}
	
	private int getStartNum(int endNum){
		return endNum - pageSize + 1;
	}

	public void initStoreProductBoard(ModelMap model, int branchSeq) {
		int selectedPage = 1;
		int categorySeq = 0;
		String productCode = "";
		
		List<CategoryDTO> categoryList = productDao.getCategoryList();
		model.put("categoryList", categoryList);
		
		searchStoreProductBoard(model, categorySeq, productCode, selectedPage, branchSeq);
		
	}

	public void searchStoreProductBoard(ModelMap model, int categorySeq,
			String productCode,int selectedPage, int branchSeq) {
		
		SearchProductDTO searchProductDto = new SearchProductDTO();
		int endNum = getEndNum(selectedPage);
		int startNum = getStartNum(endNum);
		
		searchProductDto.setCategorySeq(categorySeq);
		searchProductDto.setEndNum(endNum);
		searchProductDto.setProductCode(productCode);
		searchProductDto.setStartNum(startNum);
		searchProductDto.setBranchSeq(branchSeq);
		
		List<StoreProductDTO> storeProductList = storeProductDao.searchStoreProductBoard(searchProductDto);
		int totalRow = storeProductDao.getTotalRow(searchProductDto);
		
		String category = "";
		if(categorySeq == 0){
			category = "전체";
		}else{
			category = productDao.getCategory(categorySeq);
		}
		 
		model.put("retrievedCategory", category);
		model.put("retrievedProductCode", productCode);
		model.put("storeProductList", storeProductList);
		model.put("pageNavigation", makePagingHTML(totalRow ,selectedPage));
	}
	
	public String makePagingHTML(int totalRow ,int selectedPage){
		pagingHTML= new StringBuffer();
		
		int totalPage = (totalRow+pageSize-1)/pageSize;//총페이지수
		int startPage=((int)((selectedPage-1)/pageBlock))*pageBlock+1;		
		int endPage=startPage+pageBlock-1;
		
		if(endPage>totalPage){
			endPage=totalPage;
		}		
		
		if(startPage>pageBlock){
			
			pagingHTML.append("<span id='"+(startPage-1)+"'>[이전]</span>");
		}		
		
		for(int i=startPage;i<=endPage;i++){
			if(selectedPage==i){
				pagingHTML.append("<span id='"+i+"' class='selectedPage'>["+i+"]</span>");
			}else{
				pagingHTML.append("<span id='"+i+"'>["+i+"]</span>");
			}
		}
				
		if(endPage<totalPage){
			pagingHTML.append("<span id='"+(startPage+pageBlock)+"'>[다음]</span>");
			
		}
		
		return pagingHTML.toString();
	}


}
