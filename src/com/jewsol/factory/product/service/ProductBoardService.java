package com.jewsol.factory.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.factory.product.bean.CategoryDTO;
import com.jewsol.factory.product.bean.ProductDTO;
import com.jewsol.factory.product.bean.SearchProductDTO;
import com.jewsol.factory.product.dao.ProductDAO;

@Service
public class ProductBoardService {
	@Autowired
	private ProductDAO productDao;
	@Autowired
	private ProductService productService;
	
	//pageBlock 아래 페이징 개수 설정
	private int pageBlock = 10;
	//pagePer 한페이지에 표시하는 개수
	private int pageSize = 16;
	private StringBuffer pagingHTML;
	
	public int getEndNum(int selectedPage){
		return selectedPage * pageSize;
	}
	
	public int getStartNum(int endNum){
		return endNum - pageSize + 1;
	}
	
	public void initProductBoard(ModelMap model) {
		
		int selectedPage = 1;
		int categorySeq = 0;
		String productCode = "";
		
		List<CategoryDTO> categoryList = productDao.getCategoryListTypeT();
		model.put("categoryList", categoryList);
		
		searchProductBoard(model, categorySeq, productCode, selectedPage);
		
	}
	
	public void searchProductBoard(ModelMap model, int categorySeq,
			String productCode,int selectedPage) {
		
		SearchProductDTO searchProductDto = new SearchProductDTO();
		int endNum = getEndNum(selectedPage);
		int startNum = getStartNum(endNum);
		
		searchProductDto.setCategorySeq(categorySeq);
		searchProductDto.setEndNum(endNum);
		searchProductDto.setProductCode(productCode);
		searchProductDto.setStartNum(startNum);
		
		List<ProductDTO> productList = productDao.searchProductBoard(searchProductDto);
		int totalRow = productDao.getTotalRow(searchProductDto);
		
		String category = "";
		if(categorySeq == 0){
			category = "전체";
		}else{
			category = productDao.getCategory(categorySeq);
		}
		 
		model.put("retrievedCategory", category);
		model.put("retrievedProductCode", productCode);
		model.put("productList", productList);
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

	public void productDetail(ModelMap model, int productSeq) {
		productService.getProductModel(model, productSeq);
		productService.getProductOptionModel(model, productSeq);
	}

	public int getCategorySeq(String category) {
		int categorySeq = 0;
		
		if(!(category.equals("전체"))){
			categorySeq = productDao.getCategorySeq(category);
		}
		
		return categorySeq;
	}
}
