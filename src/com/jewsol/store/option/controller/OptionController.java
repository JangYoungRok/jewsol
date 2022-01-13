package com.jewsol.store.option.controller;

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
import com.jewsol.factory.product.bean.TypeDTO;
import com.jewsol.factory.product.service.ProductService;
import com.jewsol.store.option.bean.OptionAddDTO;
import com.jewsol.store.option.bean.OptionCzDTO;
import com.jewsol.store.option.bean.OptionDTO;
import com.jewsol.store.option.bean.OptionPartDTO;
import com.jewsol.store.option.bean.OptionStoneDTO;
import com.jewsol.store.option.service.OptionService;
import com.jewsol.store.storeProduct.service.StoreProductService;

@Controller
public class OptionController {
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private ProductService productService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private StoreProductService storeProductService;
	@Autowired
	private CzService czService;
	@Autowired
	private StoneService stoneService;
	@Autowired
	private PartService partService;
	@Autowired
	private AddService addService;
	
	@RequestMapping("/store/option/adminOptionForm.do")
	public ModelAndView adminOptionForm( ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		List<CategoryDTO> categoryList = productService.getFactoryCategoryList();
		List<TypeDTO> typeList = productService.getTypeList();
		
		model.put("typeList",typeList);
		model.put("categoryList",categoryList);
		utilSession.checkExistLogin(session, mav, "/store/option/admin/adminOptionForm.jsp");
		
		return mav;
		
	}
	
	@RequestMapping("/store/optoin/admin/updateOption.do")
	public ModelAndView updateOption( ModelMap model, HttpServletRequest request,
			@RequestParam("optionSeq") int optionSeq,
			@RequestParam("optionName") String optionName,
			@RequestParam("czSeqArr") String czSeqArr,
			@RequestParam("czQtyArr") String czQtyArr,
			@RequestParam("czSizeArr") String czSizeArr,
			@RequestParam("czNameArr") String czNameArr,
			@RequestParam("stoneSeqArr") String stoneSeqArr,
			@RequestParam("stoneQtyArr") String stoneQtyArr,
			@RequestParam("stoneNameArr") String stoneNameArr,
			@RequestParam("stoneSizeArr") String stoneSizeArr,
			@RequestParam("partSeqArr") String partSeqArr,
			@RequestParam("partNameArr") String partNameArr,
			@RequestParam("addSeqArr") String addSeqArr,
			@RequestParam("addNameArr") String addNameArr
			){
		ModelAndView mav = new ModelAndView();
		
		OptionDTO updateOptionDto = new OptionDTO();
		updateOptionDto.setOptionSeq(optionSeq);
		updateOptionDto.setOptionName(optionName);
		optionService.updateOptionNameByOptionSeq(updateOptionDto);
		optionService.deleteOptionDetails(optionSeq);
		optionService.insertOptionCz(optionSeq, czSeqArr, czNameArr, czQtyArr, czSizeArr);
		optionService.insertOptionStone(optionSeq, stoneSeqArr, stoneQtyArr, stoneNameArr, stoneSizeArr);
		optionService.insertOptionPart(optionSeq, partSeqArr, partNameArr);
		optionService.insertOptionAdd(optionSeq, addSeqArr, addNameArr);
		optionService.initOptionLabor(optionSeq);
		optionService.initOptionPrice(optionSeq);
		
		mav.setViewName("redirect:/store/option/admin/afterUpdateOption.do?optionSeq="+optionSeq);
		return mav;
		
	}
	
	@RequestMapping("/store/optoin/admin/insertOption.do")
	public ModelAndView insertOption( ModelMap model, HttpServletRequest request,
			@RequestParam("originalOptionSeq") int originalOptionSeq,
			@RequestParam("optionNumber") int optionNumber,
			@RequestParam("optionName") String optionName,
			@RequestParam("czSeqArr") String czSeqArr,
			@RequestParam("czQtyArr") String czQtyArr,
			@RequestParam("czNameArr") String czNameArr,
			@RequestParam("czSizeArr") String czSizeArr,
			@RequestParam("stoneSeqArr") String stoneSeqArr,
			@RequestParam("stoneQtyArr") String stoneQtyArr,
			@RequestParam("stoneNameArr") String stoneNameArr,
			@RequestParam("stoneSizeArr") String stoneSizeArr,
			@RequestParam("partSeqArr") String partSeqArr,
			@RequestParam("partNameArr") String partNameArr,
			@RequestParam("addSeqArr") String addSeqArr,
			@RequestParam("addNameArr") String addNameArr
			){
		ModelAndView mav = new ModelAndView();
		OptionDTO originalOptionDto = optionService.getOption(originalOptionSeq);
		int storeProductSeq = originalOptionDto.getStoreProductSeq();
		int optionSeq = optionService.insertOption(storeProductSeq, optionName, optionNumber);
		optionService.insertOptionCz(optionSeq, czSeqArr, czNameArr, czQtyArr, czSizeArr);
		optionService.insertOptionStone(optionSeq, stoneSeqArr, stoneQtyArr, stoneNameArr, stoneSizeArr);
		optionService.insertOptionPart(optionSeq, partSeqArr, partNameArr);
		optionService.insertOptionAdd(optionSeq, addSeqArr, addNameArr);
		optionService.initOptionLabor(optionSeq);
		optionService.initOptionPrice(optionSeq);
		
		mav.setViewName("redirect:/store/option/admin/afterUpdateOption.do?optionSeq="+optionSeq);
		return mav;
	}
	
	
	@RequestMapping("/store/option/admin/deleteOption.do")
	public ModelAndView deleteOption(ModelMap model, HttpServletRequest request,
			@RequestParam("optionSeq") int optionSeq,
			@RequestParam("optionNumber") int optionNumber,
			@RequestParam("originalOptionSeq") int originalOptionSeq){
		ModelAndView mav = new ModelAndView();
		if(optionNumber > 1){
			optionService.deleteOption(optionSeq);
		}
		optionService.deleteOptionDetails(optionSeq);
		
		mav.setViewName("redirect:/store/option/admin/afterUpdateOption.do?optionSeq="+originalOptionSeq);
		return mav;
	}
	
	@RequestMapping("/store/option/admin/afterUpdateOption.do")
	public ModelAndView afterUpdateOption( ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam("optionSeq") int optionSeq){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		OptionDTO optionDto = optionService.getOption(optionSeq);
		String optionName = optionDto.getOptionName();
		int optionNumber =optionDto.getOptionNumber();
		
		List<OptionDTO> optionDtoList = optionService.getOptionList(optionDto.getStoreProductSeq());
		
		List<CzSizeDTO> czSizeList = czService.getCzSizeList();
		List<StoneSizeDTO> stoneSizeList = stoneService.getStoneSizeList();
		int storeProductSeq = optionService.getStoreProductSeq(optionSeq);
		int typeSeq = storeProductService.getTypeSeq(storeProductSeq);
		int typeMainSeq = productService.getTypeMainSeq(typeSeq);
		List<PartAttributeDTO> partAttributeList = partService.getPartAttributeList(typeMainSeq);
		List<AddDTO> addList = addService.getAddList(branchSeq);
		
		List<OptionCzDTO> optionCzList = optionService.getOptionCzList(optionSeq);
		List<OptionStoneDTO> optionStoneList = optionService.getOptionStoneList(optionSeq);
		List<OptionPartDTO> optionPartList = optionService.getOptionPartList(optionSeq);
		List<OptionAddDTO> optionAddList = optionService.getOptionAddList(optionSeq);
		
		List<Integer> optionCzSizeSeqList = optionService.getOptionCzSizeSeqList(optionCzList);
		List<Integer> optionStoneSizeSeqList = optionService.getOptionStoneSizeSeqList(optionStoneList);
		List<Integer> optionPartAttributeSeqList = optionService.getOptionPartAttributeSeq(optionPartList);
		
		List<List<CzDTO>> czListResponse = optionService.getCzListResponse(optionCzList, branchSeq);
		List<List<StoneDTO>> stoneListResponse = optionService.getStoneListResponse(optionStoneList, branchSeq);
		List<List<PartDTO>> partListResponse = optionService.getPartListResponse(optionPartList, branchSeq);
		
		model.put("optionName", optionName);
		model.put("optionNumber", optionNumber);
		
		model.put("optionDtoList", optionDtoList);
		
		model.put("czSizeList", czSizeList);
		model.put("stoneSizeList", stoneSizeList);
		model.put("partAttributeList", partAttributeList);
		model.put("addList", addList);
		
		model.put("optionCzSizeSeqList", optionCzSizeSeqList);
		model.put("optionStoneSizeSeqList", optionStoneSizeSeqList);
		model.put("optionPartAttributeSeqList", optionPartAttributeSeqList);
		
		model.put("optionCzList", optionCzList);
		model.put("optionStoneList", optionStoneList);
		model.put("optionPartList", optionPartList);
		model.put("optionAddList", optionAddList);
		
		model.put("czListResponse",czListResponse);
		model.put("stoneListResponse",stoneListResponse);
		model.put("partListResponse",partListResponse);
		model.put("optionAddList",optionAddList);
		
		
		mav.addObject("optionList", "/WEB-INF/view/store/option/admin/optionList.jsp");
		mav.addObject("optionDetail", "/WEB-INF/view/store/option/admin/updateOptionDetail.jsp");
		
		mav.setViewName("/store/option/admin/ajaxCallBack/afterUpdateOption.jsp");
		return mav;
	}
		
	@RequestMapping("/store/option/admin/changeOption.do")
	public ModelAndView changeOption( ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam("optionSeq") int optionSeq){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		String optionName = optionService.getOptionName(optionSeq);
		List<CzSizeDTO> czSizeList = czService.getCzSizeList();
		List<StoneSizeDTO> stoneSizeList = stoneService.getStoneSizeList();
		int storeProductSeq = optionService.getStoreProductSeq(optionSeq);
		int typeSeq = storeProductService.getTypeSeq(storeProductSeq);
		int typeMainSeq = productService.getTypeMainSeq(typeSeq);
		List<PartAttributeDTO> partAttributeList = partService.getPartAttributeList(typeMainSeq);
		List<AddDTO> addList = addService.getAddList(branchSeq);
		
		List<OptionCzDTO> optionCzList = optionService.getOptionCzList(optionSeq);
		List<OptionStoneDTO> optionStoneList = optionService.getOptionStoneList(optionSeq);
		List<OptionPartDTO> optionPartList = optionService.getOptionPartList(optionSeq);
		List<OptionAddDTO> optionAddList = optionService.getOptionAddList(optionSeq);
		
		List<Integer> optionCzSizeSeqList = optionService.getOptionCzSizeSeqList(optionCzList);
		List<Integer> optionStoneSizeSeqList = optionService.getOptionStoneSizeSeqList(optionStoneList);
		List<Integer> optionPartAttributeSeqList = optionService.getOptionPartAttributeSeq(optionPartList);
		
		List<List<CzDTO>> czListResponse = optionService.getCzListResponse(optionCzList, branchSeq);
		List<List<StoneDTO>> stoneListResponse = optionService.getStoneListResponse(optionStoneList, branchSeq);
		List<List<PartDTO>> partListResponse = optionService.getPartListResponse(optionPartList, branchSeq);

		model.put("optionName", optionName);
		
		model.put("czSizeList", czSizeList);
		model.put("stoneSizeList", stoneSizeList);
		model.put("partAttributeList", partAttributeList);
		model.put("addList", addList);
		
		model.put("optionCzSizeSeqList", optionCzSizeSeqList);
		model.put("optionStoneSizeSeqList", optionStoneSizeSeqList);
		model.put("optionPartAttributeSeqList", optionPartAttributeSeqList);
		
		model.put("optionCzList", optionCzList);
		model.put("optionStoneList", optionStoneList);
		model.put("optionPartList", optionPartList);
		model.put("optionAddList", optionAddList);
		
		model.put("czListResponse",czListResponse);
		model.put("stoneListResponse",stoneListResponse);
		model.put("partListResponse",partListResponse);
		model.put("optionAddList",optionAddList);
		
		mav.addObject("optionDetail", "/WEB-INF/view/store/option/admin/updateOptionDetail.jsp");
		mav.setViewName("/store/option/admin/ajaxCallBack/afterChangeOption.jsp");
		return mav;
	}
	
	@RequestMapping("/store/option/admin/searchStoreProduct.do")
	public ModelAndView searchStoreProduct( ModelMap model, HttpServletRequest request,HttpSession session,
			@RequestParam("categorySeq") int categorySeq,
			@RequestParam("productCode") String productCode,
			@RequestParam("typeSeq") int typeSeq){
		ModelAndView mav = new ModelAndView();
		
		
		int branchSeq = utilSession.getBranchSeq(session);
		int storeProductSeq = storeProductService.getStoreProductSeq(categorySeq, typeSeq, productCode, branchSeq);
		int searchOptionState = storeProductService.getSearchStoreProductState(storeProductSeq);
		
		if(searchOptionState == 1){
			String storeProductName = storeProductService.getStoreProductName(storeProductSeq);
			int optionNumber = 1;
			OptionDTO optionDto = optionService.getOption(storeProductSeq, optionNumber);
			int optionSeq = optionDto.getOptionSeq();
			List<OptionDTO> optionDtoList = optionService.getOptionList(storeProductSeq);
			List<CzSizeDTO> czSizeList = czService.getCzSizeList();
			List<StoneSizeDTO> stoneSizeList = stoneService.getStoneSizeList();
			int typeMainSeq = productService.getTypeMainSeq(typeSeq);
			List<PartAttributeDTO> partAttributeList = partService.getPartAttributeList(typeMainSeq);
			List<AddDTO> addList = addService.getAddList(branchSeq);
			
			List<OptionCzDTO> optionCzList = optionService.getOptionCzList(optionSeq);
			List<OptionStoneDTO> optionStoneList = optionService.getOptionStoneList(optionSeq);
			List<OptionPartDTO> optionPartList = optionService.getOptionPartList(optionSeq);
			List<OptionAddDTO> optionAddList = optionService.getOptionAddList(optionSeq);
			
			List<Integer> optionCzSizeSeqList = optionService.getOptionCzSizeSeqList(optionCzList);
			List<Integer> optionStoneSizeSeqList = optionService.getOptionStoneSizeSeqList(optionStoneList);
			List<Integer> optionPartAttributeSeqList = optionService.getOptionPartAttributeSeq(optionPartList);
			
			List<List<CzDTO>> czListResponse = optionService.getCzListResponse(optionCzList, branchSeq);
			List<List<StoneDTO>> stoneListResponse = optionService.getStoneListResponse(optionStoneList, branchSeq);
			List<List<PartDTO>> partListResponse = optionService.getPartListResponse(optionPartList, branchSeq);
				
			model.put("storeProductName", storeProductName);
			model.put("optionName", optionDto.getOptionName());
			
			model.put("optionNumber",optionNumber);
			model.put("optionDtoList", optionDtoList);
			
			model.put("czSizeList", czSizeList);
			model.put("stoneSizeList", stoneSizeList);
			model.put("partAttributeList", partAttributeList);
			model.put("addList", addList);
			
			model.put("optionCzSizeSeqList", optionCzSizeSeqList);
			model.put("optionStoneSizeSeqList", optionStoneSizeSeqList);
			model.put("optionPartAttributeSeqList", optionPartAttributeSeqList);
			
			model.put("optionCzList", optionCzList);
			model.put("optionStoneList", optionStoneList);
			model.put("optionPartList", optionPartList);
			model.put("optionAddList", optionAddList);
			
			model.put("czListResponse",czListResponse);
			model.put("stoneListResponse",stoneListResponse);
			model.put("partListResponse",partListResponse);
			model.put("optionAddList",optionAddList);
			
			mav.addObject("optionList", "/WEB-INF/view/store/option/admin/optionList.jsp");
			mav.addObject("optionDetail", "/WEB-INF/view/store/option/admin/updateOptionDetail.jsp");
		}
		
		model.put("searchOptionState", searchOptionState);
		mav.setViewName("/store/option/admin/ajaxCallBack/afterSearchStoreProduct.jsp");
		return mav;
	}
	
	@RequestMapping("/store/option/getOptionForOrder.do")
	public ModelAndView getOptionForOrder( ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam("storeProductSeq") int storeProductSeq,
			@RequestParam("optionNumber") int optionNumber){
		ModelAndView mav = new ModelAndView();
		String optionDetailUrl = "/WEB-INF/view/store/option/order/optionDetail.jsp";
		int branchSeq = utilSession.getBranchSeq(session);
		if(optionNumber == 9){
			optionNumber = 1;
			optionDetailUrl = "/WEB-INF/view/store/option/order/updateOptionDetail.jsp";
		}
		OptionDTO optionDto = optionService.getOption(storeProductSeq, optionNumber);
		int optionSeq = optionDto.getOptionSeq();
		List<OptionDTO> optionDtoList = optionService.getOptionList(storeProductSeq);
		
		List<CzSizeDTO> czSizeList = czService.getCzSizeList();
		List<StoneSizeDTO> stoneSizeList = stoneService.getStoneSizeList();
		int typeSeq = productService.getTypeSeq(storeProductSeq);
		int typeMainSeq = productService.getTypeMainSeq(typeSeq);
		List<PartAttributeDTO> partAttributeList = partService.getPartAttributeList(typeMainSeq);
		List<AddDTO> addList = addService.getAddList(branchSeq);
		
		List<OptionCzDTO> optionCzList = optionService.getOptionCzList(optionSeq);
		List<OptionStoneDTO> optionStoneList = optionService.getOptionStoneList(optionSeq);
		List<OptionPartDTO> optionPartList = optionService.getOptionPartList(optionSeq);
		List<OptionAddDTO> optionAddList = optionService.getOptionAddList(optionSeq);
		
		List<Integer> optionCzSizeSeqList = optionService.getOptionCzSizeSeqList(optionCzList);
		List<Integer> optionStoneSizeSeqList = optionService.getOptionStoneSizeSeqList(optionStoneList);
		List<Integer> optionPartAttributeSeqList = optionService.getOptionPartAttributeSeq(optionPartList);
		
		List<List<CzDTO>> czListResponse = optionService.getCzListResponse(optionCzList, branchSeq);
		List<List<StoneDTO>> stoneListResponse = optionService.getStoneListResponse(optionStoneList, branchSeq);
		List<List<PartDTO>> partListResponse = optionService.getPartListResponse(optionPartList, branchSeq);
		
		model.put("optionDtoList", optionDtoList);
		
		model.put("czSizeList", czSizeList);
		model.put("stoneSizeList", stoneSizeList);
		model.put("partAttributeList", partAttributeList);
		model.put("addList", addList);
		
		model.put("optionCzSizeSeqList", optionCzSizeSeqList);
		model.put("optionStoneSizeSeqList", optionStoneSizeSeqList);
		model.put("optionPartAttributeSeqList", optionPartAttributeSeqList);
		
		model.put("optionCzList", optionCzList);
		model.put("optionStoneList", optionStoneList);
		model.put("optionPartList", optionPartList);
		model.put("optionAddList", optionAddList);
		
		model.put("czListResponse",czListResponse);
		model.put("stoneListResponse",stoneListResponse);
		model.put("partListResponse",partListResponse);
		model.put("optionAddList",optionAddList);
		
		mav.addObject("optionList", "/WEB-INF/view/store/option/order/orderOptionList.jsp");
		mav.addObject("optionDetail", optionDetailUrl);

		mav.setViewName("/store/option/order/afterGetOptionForOrder.jsp");
		return mav;
	}

}
