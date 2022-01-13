package com.jewsol.factory.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.file.FileService;
import com.jewsol.common.util.UtilSession;
import com.jewsol.store.part.bean.PartAttributeDTO;
import com.jewsol.factory.product.bean.ProductDTO;
import com.jewsol.factory.product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private ProductService productService;
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/factory/product/insertProductForm.do")
	public ModelAndView insertProductForm(HttpServletRequest request, HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		//입력후 productName Parameter가 있을 경우
		if(request.getParameter("productName") != null){
			
			model.put("productName", request.getParameter("productName"));
		}
		int typeMainSeq = 1; 
		//공통 처리 부분
		productService.initInsertProductFrom(model, typeMainSeq);
		utilSession.checkExistLogin(session, mav, "/factory/product/insertProductForm.jsp");
		return mav;
		
	}
	
	@RequestMapping("/factory/product/checkOverLapProductName.do")
	public ModelAndView checkOverLapProductName(HttpServletRequest request, ModelMap model){
		ModelAndView mav = new ModelAndView();
		ProductDTO productDto = new ProductDTO();
		
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));
		String productCode =  request.getParameter("productCode");
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		
		productDto.setCategorySeq(categorySeq);
		productDto.setProductCode(productCode);
		productDto.setTypeSeq(typeSeq);
		
		int overLapResult = productService.checkOverLapProductName(productDto);
		
		model.put("overLapResult", overLapResult);
		
		mav.setViewName("/factory/product/checkOverLapProductNameResult.jsp");
		
		return mav;
		
	}
	
	@RequestMapping("/factory/product/insertProduct.do")
	public ModelAndView insertProduct(MultipartHttpServletRequest request, @RequestParam MultipartFile productImage) throws IOException{
		ModelAndView mav = new ModelAndView();
		//parameter
		int categorySeq = Integer.parseInt(request.getParameter("category"));
		int typeSeq = Integer.parseInt(request.getParameter("type"));
		String productCode = request.getParameter("productCode");
		Double productAveWeight = 0.0;
		if(!request.getParameter("productAveWeight").equals("")){
			productAveWeight = Double.parseDouble(request.getParameter("productAveWeight"));	
		}

		//file upload
		String filePath = fileService.getFilePath();
		String productImageFileName = fileService.uploadProductImage(productImage, filePath);
		//procutName 추출
		String productName = productService.getProductName(categorySeq, productCode, typeSeq);
		
		//parameter 담기
		ProductDTO productDto = new ProductDTO();
		productDto.setCategorySeq(categorySeq);
		productDto.setProductAveWeight(productAveWeight);
		productDto.setProductCode(productCode);
		productDto.setProductImage(productImageFileName);
		productDto.setProductName(productName);
		productDto.setTypeSeq(typeSeq);
		
		productService.insertProduct(productDto, request);
		mav.setViewName("redirect:/factory/product/insertProductForm.do?productName="+productName);
		
		return mav;
		
	}
	
	@RequestMapping("/factory/product/getPartAttributeList.do")
	public ModelAndView getPartAttributeList(HttpServletRequest request, ModelMap model){
		ModelAndView mav = new ModelAndView();
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		List<PartAttributeDTO> partAttributeList = productService.getPartAttributeList(typeSeq);
		
		model.put("partAttributeList", partAttributeList);
		
		mav.setViewName("/factory/product/ajax/partAttributeList.jsp");
		
		return mav;
	
	}
	
	@RequestMapping("/factory/product/update/updateProductForm.do")
	public ModelAndView updateProductForm(HttpServletRequest request,HttpSession session, ModelMap model){
		ModelAndView mav = new ModelAndView();
		
		productService.initUpdateProductFrom(model);
		utilSession.checkExistLogin(session, mav, "/factory/product/update/updateProductForm.jsp");
		
		return mav;
	
	}
	
	@RequestMapping("/factory/product/update/searchProduct.do")
	public ModelAndView searchProduct(HttpServletRequest request, ModelMap model){
		ModelAndView mav = new ModelAndView(); 
		
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		String productCode = request.getParameter("productCode");
		
		productService.getUpdateProduct(model, categorySeq, typeSeq, productCode);
		mav.setViewName("/factory/product/update/ajaxCallBack/getUpdateProduct.jsp");
		
		return mav;
	}
	
	@RequestMapping("/factory/product/update/getProductOption.do")
	public ModelAndView getProductOption(HttpServletRequest request, ModelMap model){
		ModelAndView mav = new ModelAndView(); 
		
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		String productCode = request.getParameter("productCode");
		
		productService.getProductOption(model, categorySeq, typeSeq, productCode);
		mav.setViewName("/common/productOption.jsp");
		
		return mav;
	}
	
	@RequestMapping("/factory/product/update/getUpdateProductOption.do")
	public ModelAndView getUpdateProductOption(HttpServletRequest request, ModelMap model){
		ModelAndView mav = new ModelAndView(); 
		
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		int typeMainSeq = productService.getTypeMainSeq(typeSeq);
		String productCode = request.getParameter("productCode");
		productService.initInsertProductFrom(model, typeMainSeq);
		productService.getProductOption(model, categorySeq, typeSeq, productCode);
		mav.setViewName("/factory/product/update/ajaxCallBack/updateProductOption.jsp");
		
		return mav;
	}
	
	@RequestMapping("/factory/product/update/notInUseProduct.do")
	public ModelAndView notInUseProduct(HttpServletRequest request, ModelMap model){
		ModelAndView mav = new ModelAndView(); 
		
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		String productCode = request.getParameter("productCode");
		
		productService.notInUseProduct(categorySeq, productCode, typeSeq);

		String productName = productService.getProductName(categorySeq, productCode, typeSeq);
		model.put("productName",productName);
		
		
		mav.setViewName("/common/productName.jsp");
		
		return mav;
	}
	
	@RequestMapping("/factory/product/update/updateProductDetail.do")
	public ModelAndView updateProductDetail(MultipartHttpServletRequest request, ModelMap model, 
			@RequestParam MultipartFile productImage,
			@RequestParam("productSeq") int productSeq){
		
		ModelAndView mav = new ModelAndView();
		Double productAveWeight = 0.0;
		if(request.getParameter("productAveWeight") != null || request.getParameter("productAveWeight").length() == 0){
			productAveWeight = Double.parseDouble(request.getParameter("productAveWeight"));	
		}
		String filePath = fileService.getFilePath();
		String productImageFileName = fileService.uploadProductImage(productImage,filePath);
		
		ProductDTO updateProductDto = new ProductDTO();
		updateProductDto.setProductSeq(productSeq);
		updateProductDto.setProductAveWeight(productAveWeight);
		updateProductDto.setProductImage(productImageFileName);
		
		productService.updateProductDetail(updateProductDto);
		mav.setViewName("redirect:/factory/product/board/productDetail.do?productSeq="+productSeq);
		
		return mav;
	}
	
	@RequestMapping("/factory/product/update/updateProduct.do")
	public ModelAndView updateProduct(MultipartHttpServletRequest request, ModelMap model/*, @RequestParam MultipartFile productImage*/) throws IOException{
		ModelAndView mav = new ModelAndView(); 
		//parameter
		int categorySeq = Integer.parseInt(request.getParameter("category"));
		int typeSeq = Integer.parseInt(request.getParameter("type"));
		String productCode = request.getParameter("productCode");
		Double productAveWeight = 0.0;
		if(request.getParameter("productAveWeight") != null){
			productAveWeight = Double.parseDouble(request.getParameter("productAveWeight"));	
		}
		
		//file upload
		//String filePath = fileService.getFilePath();
		//String productImageFileName = fileService.uploadProductImage(productImage,filePath);
		//procutName 추출
		String productName = productService.getProductName(categorySeq, productCode, typeSeq);
		int productSeq = productService.getPtoductSeq(categorySeq, typeSeq, productCode);
		String getProductOption = request.getParameter("getProductOption");
		
		//parameter 담기
		ProductDTO updateProductDto = new ProductDTO();
		updateProductDto.setProductSeq(productSeq);
		updateProductDto.setCategorySeq(categorySeq);
		updateProductDto.setProductAveWeight(productAveWeight);
		updateProductDto.setProductCode(productCode);
		//updateProductDto.setProductImage(productImageFileName);
		updateProductDto.setProductName(productName);
		updateProductDto.setTypeSeq(typeSeq);
		
		productService.updateProduct(updateProductDto);
		
		if(getProductOption.equals("O")){
			productService.updateProductOption(productSeq, request);
		}

		mav.setViewName("redirect:/factory/product/update/afterUpdateProductForm.do?categorySeq="+categorySeq+"&typeSeq="+typeSeq+"&productCode="+productCode);
		
		return mav;
	}
	
	@RequestMapping("/factory/product/update/afterUpdateProductForm.do")
	public ModelAndView afterUpdateProductForm(HttpServletRequest request,HttpSession session , ModelMap model){
		ModelAndView mav = new ModelAndView(); 
		
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		String productCode = request.getParameter("productCode");
		
		int productSeq = productService.getPtoductSeq(categorySeq, typeSeq, productCode);
		
		productService.getProductModel(model, productSeq);
		productService.getProductOptionModel(model, productSeq);
		productService.initUpdateProductFrom(model);
		utilSession.checkExistLogin(session, mav, "/factory/product/update/afterUpdateProductForm.jsp");
		
		return mav;
	}
}
