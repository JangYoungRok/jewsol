package com.jewsol.store.storeProduct.controller;

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
import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.service.CzService;
import com.jewsol.core.login.service.LoginService;
import com.jewsol.store.part.bean.PartDTO;
import com.jewsol.store.part.service.PartService;
import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.service.StoneService;
import com.jewsol.factory.product.service.ProductService;
import com.jewsol.store.option.bean.OptionDTO;
import com.jewsol.store.option.service.OptionService;
import com.jewsol.store.storeProduct.bean.StoreProductDTO;
import com.jewsol.store.storeProduct.service.StoreProductService;
import com.jewsol.store.supplier.service.SupplierService;

@Controller
public class StoreProductController {
	
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private StoreProductService storeProductService;
	@Autowired
	private CzService czService;
	@Autowired
	private StoneService stoneService;
	@Autowired
	private PartService partService;
	@Autowired
	private ProductService productService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/store/storeProduct/registerStoreProductForm.do")
	public ModelAndView registerStoreProductForm( ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
	
		storeProductService.initRegisterStoreProductForm(model);
		utilSession.checkExistLogin(session, mav, "/store/storeProduct/register/registerStoreProductForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/checkStoreProduct.do")
	public ModelAndView checkStoreProduct( ModelMap model, HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
	
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		int branchSeq = utilSession.getBranchSeq(session);
		String productCode = request.getParameter("productCode");
		
		storeProductService.checkStoreProduct(model, categorySeq, typeSeq, productCode, branchSeq);
		mav.setViewName("/store/storeProduct/register/ajaxCallBack/afterSearchStoreProduct.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/getCzList.do")
	public ModelAndView getCzList( ModelMap model, HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		int index = Integer.parseInt(request.getParameter("index"));
		int czSizeSeq = Integer.parseInt(request.getParameter("czSizeSeq"));
		
		List<CzDTO> czList = czService.getCzListByCzSizeSeq(czSizeSeq, branchSeq);
		model.put("index",index);
		model.put("czList",czList);
		
		mav.setViewName("/store/storeProduct/register/ajaxCallBack/czList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/getStoneList.do")
	public ModelAndView getStoneList( ModelMap model, HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		int index = Integer.parseInt(request.getParameter("index"));
		int stoneSizeSeq = Integer.parseInt(request.getParameter("stoneSizeSeq"));
		StoneDTO stone = new StoneDTO();
		stone.setBranchSeq(branchSeq);
		stone.setStoneSizeSeq(stoneSizeSeq);
		List<StoneDTO> stoneList = stoneService.getStoneListByStoneSizeSeq(stone);
		
		model.put("index",index);
		model.put("stoneList",stoneList);
		
		mav.setViewName("/store/storeProduct/register/ajaxCallBack/stoneList.jsp");
		
		return mav;
		
	}
	
	@RequestMapping("/store/storeProduct/getPartList.do")
	public ModelAndView getPartList( ModelMap model, HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		int index = Integer.parseInt(request.getParameter("index"));
		int partAttributeSeq = Integer.parseInt(request.getParameter("partAttributeSeq"));
		PartDTO part = new PartDTO();
		part.setBranchSeq(branchSeq);
		part.setPartAttributeSeq(partAttributeSeq);
		List<PartDTO> partList = partService.getPartListByPartAttributeSeq(part);
		
		model.put("index",index);
		model.put("partList",partList);
		
		mav.setViewName("/store/storeProduct/register/ajaxCallBack/partList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/registerStoreProduct.do")
	public ModelAndView registerStoreProduct(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		
		int categorySeq = Integer.parseInt(request.getParameter("categorySeq"));
		int typeSeq = Integer.parseInt(request.getParameter("typeSeq"));
		int storeProductPrice = Integer.parseInt(request.getParameter("storeProductPrice"));
		String productCode = request.getParameter("productCode");
		String productName = productService.getProductName(categorySeq, productCode, typeSeq);
		
		int storeProductSeq = storeProductService.registerStoreProduct(categorySeq,typeSeq, productCode, storeProductPrice, session);
		String optionName = request.getParameter("optionName");
		int optionNumber = 1;
		int optionSeq = optionService.insertOption(storeProductSeq, optionName, optionNumber);
		
		String czSeqArr = request.getParameter("czSeqArr");
		String czQtyArr = request.getParameter("czQtyArr");
		String czNameArr = request.getParameter("czNameArr");
		String czSizeArr = request.getParameter("czSizeArr");
		optionService.insertOptionCz(optionSeq, czSeqArr, czNameArr, czQtyArr, czSizeArr);
		
		String stoneSeqArr = request.getParameter("stoneSeqArr");
		String stoneQtyArr = request.getParameter("stoneQtyArr");
		String stoneNameArr = request.getParameter("stoneNameArr");
		String stoneSizeArr = request.getParameter("stoneSizeArr");
		optionService.insertOptionStone(optionSeq, stoneSeqArr, stoneQtyArr, stoneNameArr, stoneSizeArr);
		
		String partSeqArr = request.getParameter("partSeqArr");
		String partNameArr = request.getParameter("partNameArr");
		optionService.insertOptionPart(optionSeq, partSeqArr, partNameArr);
		
		String addSeqArr = request.getParameter("addSeqArr");
		String addNameArr = request.getParameter("addNameArr");
		optionService.insertOptionAdd(optionSeq, addSeqArr, addNameArr);
		
		optionService.initOptionLabor(optionSeq);
		optionService.initOptionPrice(optionSeq);
		
		
		mav.setViewName("redirect:/store/storeProduct/afterRegisterStoreProduct.do?productName="+productName);
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/afterRegisterStoreProduct.do")
	public ModelAndView afterRegisterStoreProduct( ModelMap model, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String productName = request.getParameter("productName");
		
		model.put("productName",productName);
		mav.setViewName("/common/productName.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/insertStoreProductForm.do")
	public ModelAndView insertStoreProductForm(ModelMap model, HttpSession session,
			@RequestParam(value = "state",required = false, defaultValue = "1") int state,
			@RequestParam(value = "storeProductName",required = false, defaultValue = "") String storeProductName){
		ModelAndView mav = new ModelAndView();
		
		model.put("categoryList",storeProductService.getSupplierCategoryList());
		model.put("typeList",productService.getTypeList());
		model.put("supplierList",supplierService.getSupplierList());
		model.put("systemMessage",storeProductService.getSystemMessage(state, storeProductName));
		
		utilSession.checkExistLogin(session, mav, "/store/storeProduct/insert/insertStoreProductForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/checkOverLapStoreProductName.do")
	public ModelAndView checkOverLapStoreProductName(ModelMap model, HttpSession session,
			@RequestParam("categorySeq") int categorySeq,
			@RequestParam("productCode") String productCode,
			@RequestParam("typeSeq") int typeSeq){
		ModelAndView mav = new ModelAndView();
		StoreProductDTO storeProductDto = new StoreProductDTO();
		
		storeProductDto.setCategorySeq(categorySeq);
		storeProductDto.setStoreProductCode(productCode);
		storeProductDto.setTypeSeq(typeSeq);
		storeProductDto.setBranchSeq(utilSession.getBranchSeq(session));
		
		model.put("overLapResult", storeProductService.checkOverLapStoreProductName(storeProductDto));
		
		mav.setViewName("/factory/product/checkOverLapProductNameResult.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/insertStoreProduct.do")
	public ModelAndView insertStoreProduct(MultipartHttpServletRequest request, HttpSession session,
			@RequestParam("supplier") int supplierSeq,
			@RequestParam("category") int categorySeq,
			@RequestParam("productCode") String storeProductCode,
			@RequestParam("type") int typeSeq,
			@RequestParam("productImage") MultipartFile storeProductImageFile,
			@RequestParam(value = "storeProductOriginalName", required = false, defaultValue = "") String storeProductOriginalName,
			@RequestParam(value = "productAveWeight", required = false, defaultValue = "0.0") Double storeProductAveWeight,
			@RequestParam(value = "optionName", required = false, defaultValue = "") String optionName,
			@RequestParam(value = "storeProductPrice", required = false, defaultValue = "0") int storeProductPrice
			){
		ModelAndView mav = new ModelAndView();
		
		String storeProductName = productService.getProductName(categorySeq, storeProductCode, typeSeq);
		String filePath = fileService.getFilePath();
		String storeProductImage = fileService.uploadProductImage(storeProductImageFile, filePath);
		
		int branchSeq = utilSession.getBranchSeq(session);
		int productSeq = 0;
		int optionNumber = 1;
		Double storeProductLabor = storeProductPrice * 0.7;
		
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
		storeProductDto.setStoreProductAveWeight(storeProductAveWeight);
		storeProductDto.setStoreProductPrice(storeProductPrice);
		storeProductDto.setStoreProductLabor(Integer.parseInt(String.valueOf(Math.round(storeProductLabor))));
		
		int storeProductSeq = storeProductService.insertStoreProduct(storeProductDto);
		optionService.insertOption(storeProductSeq, optionName, optionNumber);
		
		mav.setViewName("redirect:/store/storeProduct/insertStoreProductForm.do?state=2&storeProductName="+storeProductName);
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/adminStoreProductForm.do")
	public ModelAndView adminStoreProductForm(ModelMap model, HttpSession session,
			@RequestParam(value = "state", required = false, defaultValue = "1") int state,
			@RequestParam(value = "storeProductName", required = false, defaultValue = "") String storeProductName,
			@RequestParam(value = "storeProductSeq", required = false, defaultValue = "0") int storeProductSeq){
		ModelAndView mav = new ModelAndView();
		model.put("categoryList",storeProductService.getSupplierCategoryList());
		model.put("typeList",productService.getTypeList());
		model.put("supplierList",supplierService.getSupplierList());
		
		if(state == 1){
			mav.addObject("searchProduct", "/WEB-INF/view/store/storeProduct/admin/searchProduct.jsp");
			mav.addObject("productPanel", "/WEB-INF/view/store/storeProduct/admin/productPanel.jsp");
		}else if(state == 2){
			int optionNumber = 1;
			StoreProductDTO storeProduct = storeProductService.getStoreProduct(storeProductSeq);
			OptionDTO option = optionService.getOption(storeProductSeq, optionNumber);
			String systemMessage = storeProductName + "가 수정 되었습니다.";
			
			model.put("systemMessage", systemMessage);
			model.put("storeProduct", storeProduct);
			model.put("option", option);
			model.put("supplierName", supplierService.getSupplierName(storeProduct.getSupplierSeq()));
			
			mav.addObject("searchProduct", "/WEB-INF/view/store/storeProduct/admin/retrievedSearchProduct.jsp");
			mav.addObject("productPanel", "/WEB-INF/view/store/storeProduct/admin/retrievedProductPanel.jsp");
		}
		
		utilSession.checkExistLogin(session, mav, "/store/storeProduct/admin/adminStoreProductForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/searchStoreProduct.do")
	public ModelAndView searchStoreProduct(ModelMap model, HttpSession session,
			@RequestParam("categorySeq") int categorySeq,
			@RequestParam("productCode") String storeProductCode,
			@RequestParam("typeSeq") int typeSeq){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		StoreProductDTO storeProduct = new StoreProductDTO();
		storeProduct.setCategorySeq(categorySeq);
		storeProduct.setStoreProductCode(storeProductCode);
		storeProduct.setTypeSeq(typeSeq);
		
		int count = storeProductService.getCountStoreProductName(storeProduct);
		int searchStoreProductState = 0;
		if(count > 0){
			searchStoreProductState = 4;
		}else{
			int storeProductSeq = storeProductService.getStoreProductSeq(categorySeq, typeSeq, storeProductCode, branchSeq);
			int optionNumber = 1;
			StoreProductDTO storeProductDto = storeProductService.getSearchStoreProduct(storeProductSeq);
			searchStoreProductState = storeProductService.getSearchStoreProductState(storeProductSeq);
			model.put("storeProduct", storeProductDto);
			if(storeProductSeq != 0){
				model.put("supplierName", supplierService.getSupplierName(storeProductDto.getSupplierSeq()));
			}
			model.put("optionName", optionService.getOptionName(storeProductSeq, optionNumber));
		}
		
		model.put("searchStoreProductState", searchStoreProductState);
		mav.setViewName("/store/storeProduct/admin/ajaxCallBack/retrievedStoreProduct.jsp");
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/notInUseStoreProduct.do")
	public ModelAndView notInUseStoreProduct(ModelMap model, HttpSession session,
			@RequestParam("categorySeq") int categorySeq,
			@RequestParam("productCode") String storeProductCode,
			@RequestParam("typeSeq") int typeSeq){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		int storeProductSeq = storeProductService.getStoreProductSeq(categorySeq, typeSeq, storeProductCode, branchSeq);
		String storeProductName = storeProductService.getStoreProductName(storeProductSeq);
		storeProductService.notInUseStoreProduct(storeProductSeq);
		
		model.put("storeProductName", storeProductName);
		mav.setViewName("/store/storeProduct/admin/ajaxCallBack/afterNotInUseStoreProduct.jsp");
		
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/updateStoreProduct.do")
	public ModelAndView updateStoreProduct(MultipartHttpServletRequest request, HttpSession session,
			@RequestParam("category") int categorySeq,
			@RequestParam("productCode") String storeProductCode,
			@RequestParam("type") int typeSeq,
			@RequestParam("productImage") MultipartFile storeProductImageFile,
			@RequestParam(value = "storeProductOriginalName") String storeProductOriginalName,
			@RequestParam(value = "productAveWeight") Double storeProductAveWeight,
			@RequestParam(value = "optionName") String optionName,
			@RequestParam(value = "storeProductPrice") int storeProductPrice
			){
		ModelAndView mav = new ModelAndView();
		
		String storeProductName = productService.getProductName(categorySeq, storeProductCode, typeSeq);
		String storeProductImage = "";
		int branchSeq = utilSession.getBranchSeq(session);
		int storeProductSeq = storeProductService.getStoreProductSeq(categorySeq, typeSeq, storeProductCode, branchSeq);
		
		String fileName = storeProductImageFile.getOriginalFilename();
		
		if(!fileName.equals("") || fileName.length() != 0){
			String filePath = fileService.getFilePath();
			storeProductImage = fileService.uploadProductImage(storeProductImageFile, filePath);
		}else{
			storeProductImage = storeProductService.getStoreProduct(storeProductSeq).getStoreProductImage();
		}
		
		StoreProductDTO storeProductDto = new StoreProductDTO();
		storeProductDto.setStoreProductSeq(storeProductSeq);
		storeProductDto.setStoreProductOriginalName(storeProductOriginalName);
		storeProductDto.setStoreProductImage(storeProductImage);
		storeProductDto.setStoreProductAveWeight(storeProductAveWeight);
		storeProductDto.setStoreProductPrice(storeProductPrice);
		
		OptionDTO optionDto = new OptionDTO();
		optionDto.setStoreProductSeq(storeProductSeq);
		optionDto.setOptionName(optionName);
		
		storeProductService.updateStoreProduct(storeProductDto);
		optionService.updateOptionName(optionDto);
		
		mav.setViewName("redirect:/store/storeProduct/adminStoreProductForm.do?state=2&storeProductName="+storeProductName+"&storeProductSeq="+storeProductSeq);
		
		return mav;
	}
	
	@RequestMapping("/store/storeProduct/sendStoreProductKeyword.do")
	public ModelAndView sendStoreProductKeyword(ModelMap model, HttpSession session,
			@RequestParam("keyword") String keyword){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		List<StoreProductDTO> storeProductList = storeProductService.getStoreProductKeyWord(keyword, branchSeq);
		
		model.put("storeProductList", storeProductList);
		mav.setViewName("/store/storeProduct/ajax/afterSendStoreProductKeyWord.jsp");
		return mav;
	}
}