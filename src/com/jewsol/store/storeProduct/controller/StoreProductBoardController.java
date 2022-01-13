package com.jewsol.store.storeProduct.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.factory.product.service.ProductBoardService;
import com.jewsol.store.option.bean.OptionAddDTO;
import com.jewsol.store.option.bean.OptionCzDTO;
import com.jewsol.store.option.bean.OptionDTO;
import com.jewsol.store.option.bean.OptionPartDTO;
import com.jewsol.store.option.bean.OptionStoneDTO;
import com.jewsol.store.option.service.OptionService;
import com.jewsol.store.storeProduct.bean.StoreProductDTO;
import com.jewsol.store.storeProduct.service.StoreProductBoardService;
import com.jewsol.store.storeProduct.service.StoreProductService;
import com.jewsol.store.supplier.service.SupplierService;

@Controller
public class StoreProductBoardController {
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private StoreProductBoardService storeProductBoardService;
	@Autowired
	private ProductBoardService productBoardService;
	@Autowired
	private StoreProductService storeProductService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private SupplierService supplierService;
	
	
	@RequestMapping("/store/storeProduct/board/storeProductBoard.do")
	public ModelAndView storeProductBoard( ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		storeProductBoardService.initStoreProductBoard(model ,branchSeq);
		utilSession.checkExistLogin(session, mav, "/store/storeProduct/board/storeProductBoard.jsp");
		
		return mav;
		
	}
	
	
	@RequestMapping("/store/storeProduct/board/searchStoreProduct.do")
	public ModelAndView searchStoreProduct( ModelMap model, HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		int selectedPage = 1;	 
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));	
		
		String productCode = request.getParameter("productCode");
		int branchSeq = utilSession.getBranchSeq(session);
		storeProductBoardService.searchStoreProductBoard(model, categorySeq, productCode, selectedPage, branchSeq);
		
		mav.setViewName("/store/storeProduct/board/ajaxCallBack/searchProductResult.jsp");

		return mav;
		
	}
	
	@RequestMapping("/store/storeProduct/board/getProductPage.do")
	public ModelAndView getProductPage( ModelMap model, HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		int selectedPage = Integer.parseInt(request.getParameter("selectedPage"));	 
		int categorySeq = productBoardService.getCategorySeq(request.getParameter("category"));	
		String productCode = request.getParameter("productCode");
		int branchSeq = utilSession.getBranchSeq(session);
		storeProductBoardService.searchStoreProductBoard(model, categorySeq, productCode, selectedPage, branchSeq);
		
		mav.setViewName("/store/storeProduct/board/ajaxCallBack/searchProductResult.jsp");

		return mav;
		
	}
	
	@RequestMapping("/store/storeProduct/board/storeProductDetail.do")
	public ModelAndView storeProductDetail( ModelMap model, HttpServletRequest request,
			@RequestParam("storeProductSeq") int storeProductSeq,
			@RequestParam(value = "optionNumber",required = false, defaultValue = "1") int optionNumber){
		ModelAndView mav = new ModelAndView();
		
		int optionSeq = optionService.getOptionSeq(storeProductSeq, optionNumber);
		
		StoreProductDTO storeProduct = storeProductService.getSearchStoreProduct(storeProductSeq);
		String supplierName = supplierService.getSupplierName(storeProduct.getSupplierSeq());
		OptionDTO option = optionService.getOption(storeProductSeq, optionNumber);
		
		List<OptionDTO> optionDtoList = optionService.getOptionList(storeProductSeq);
		List<OptionCzDTO> optionCzList = optionService.getOptionCzList(optionSeq);
		List<OptionStoneDTO> optionStoneList = optionService.getOptionStoneList(optionSeq);
		List<OptionPartDTO> optionPartList = optionService.getOptionPartList(optionSeq);
		List<OptionAddDTO> optionAddList = optionService.getOptionAddList(optionSeq);
		
		int optionCzPrice = optionService.getOptionCzPrice(optionCzList);
		int optionStonePrice = optionService.getOptionStonePrice(optionStoneList);
		int optionPartPrice = optionService.getOptionPartPrice(optionPartList);
		int optionAddPrice = optionService.getOptionAddPRice(optionAddList);
		
		model.put("storeProductDto",storeProduct);
		model.put("supplierName",supplierName);
		model.put("optionDto",option);
		model.put("optionNumber",optionNumber);
		model.put("optionDtoList",optionDtoList);
		model.put("optionCzList",optionCzList);
		model.put("optionStoneList",optionStoneList);
		model.put("optionPartList",optionPartList);
		model.put("optionAddList",optionAddList);
		model.put("optionCzPrice",optionCzPrice);
		model.put("optionStonePrice",optionStonePrice);
		model.put("optionPartPrice",optionPartPrice);
		model.put("optionAddPrice",optionAddPrice);
		
		mav.addObject("storeProduct", "/WEB-INF/view/store/storeProduct/board/detail/storeProduct.jsp");
		mav.addObject("optionList", "/WEB-INF/view/store/storeProduct/board/detail/optionList.jsp");
		mav.addObject("optionDetail", "/WEB-INF/view/store/storeProduct/board/detail/optionDetail.jsp");
		mav.setViewName("/store/storeProduct/board/detail/storePoductDetail.jsp");
		
		return mav;
		
	}
}
