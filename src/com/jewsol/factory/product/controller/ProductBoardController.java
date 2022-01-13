package com.jewsol.factory.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.util.UtilSession;
import com.jewsol.factory.product.service.ProductBoardService;

@Controller
public class ProductBoardController {
	@Autowired
	private UtilSession utilSession;
	
	@Autowired
	private ProductBoardService productBoardService;
	
	@RequestMapping("/factory/product/board/productBoard.do")
	public ModelAndView productBoard( ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
	
		productBoardService.initProductBoard(model);
		utilSession.checkExistLogin(session, mav, "/factory/product/board/productBoard.jsp");
		
		return mav;
		
	}
	
	@RequestMapping("/factory/product/board/searchProduct.do")
	public ModelAndView searchProduct( ModelMap model, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		int selectedPage = 1;	 
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));	
		
		String productCode = request.getParameter("productCode");
	
		productBoardService.searchProductBoard(model, categorySeq, productCode, selectedPage);
		
		mav.setViewName("/factory/product/board/ajaxCallBack/searchProductResult.jsp");

		return mav;
		
	}
	
	@RequestMapping("/factory/product/board/getProductPage.do")
	public ModelAndView getProductPage( ModelMap model, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		int selectedPage = Integer.parseInt(request.getParameter("selectedPage"));	 
		int categorySeq = productBoardService.getCategorySeq(request.getParameter("category"));
		String productCode = request.getParameter("productCode");
	
		productBoardService.searchProductBoard(model, categorySeq, productCode, selectedPage);
		
		mav.setViewName("/factory/product/board/ajaxCallBack/searchProductResult.jsp");

		return mav;
		
	}
	
	@RequestMapping("factory/product/board/productDetail.do")
	public ModelAndView productDetail( ModelMap model, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		int productSeq = Integer.parseInt(request.getParameter("productSeq"));
		
		productBoardService.productDetail(model, productSeq);
		model.put("productSeq", productSeq);
		mav.setViewName("/factory/product/board/productDetail.jsp");
		return mav;
		
	}

}
