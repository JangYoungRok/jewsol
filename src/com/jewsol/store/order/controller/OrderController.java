package com.jewsol.store.order.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jewsol.common.date.DateService;
import com.jewsol.common.util.UtilSession;
import com.jewsol.store.add.bean.AddDTO;
import com.jewsol.store.add.service.AddService;
import com.jewsol.core.color.bean.ColorDTO;
import com.jewsol.core.color.service.ColorService;
import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.bean.CzSizeDTO;
import com.jewsol.store.cz.service.CzService;
import com.jewsol.core.k.bean.KDTO;
import com.jewsol.core.k.service.KService;
import com.jewsol.core.member.bean.MemberDTO;
import com.jewsol.core.member.service.MemberService;
import com.jewsol.store.part.bean.PartAttributeDTO;
import com.jewsol.store.part.bean.PartDTO;
import com.jewsol.store.part.service.PartService;
import com.jewsol.core.plate.bean.PlateDTO;
import com.jewsol.core.plate.service.PlateService;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;
import com.jewsol.store.saleSheet.service.SaleSheetService;
import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.bean.StoneSizeDTO;
import com.jewsol.store.stone.service.StoneService;
import com.jewsol.factory.product.service.ProductService;
import com.jewsol.store.customer.bean.CustomerDTO;
import com.jewsol.store.customer.service.CustomerService;
import com.jewsol.store.option.bean.OptionCzDTO;
import com.jewsol.store.option.bean.OptionDTO;
import com.jewsol.store.option.bean.OptionStoneDTO;
import com.jewsol.store.option.service.OptionService;
import com.jewsol.store.order.bean.InqueryOrderDTO;
import com.jewsol.store.order.bean.OrderDTO;
import com.jewsol.store.order.bean.OrderDetailDTO;
import com.jewsol.store.order.bean.ViewOrderDTO;
import com.jewsol.store.order.service.OrderService;
import com.jewsol.store.orderOption.bean.OrderOptionAddDTO;
import com.jewsol.store.orderOption.bean.OrderOptionCzDTO;
import com.jewsol.store.orderOption.bean.OrderOptionDTO;
import com.jewsol.store.orderOption.bean.OrderOptionPartDTO;
import com.jewsol.store.orderOption.bean.OrderOptionStoneDTO;
import com.jewsol.store.orderOption.service.OrderOptionService;
import com.jewsol.store.originalSheet.service.OriginalSheetService;
import com.jewsol.store.storeProduct.bean.StoreProductDTO;
import com.jewsol.store.storeProduct.service.StoreProductService;

@Controller
public class OrderController {
	
	@Autowired
	private UtilSession utilSession;
	@Autowired
	private DateService dateService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private KService kService;
	@Autowired
	private PlateService plateService;
	@Autowired
	private OriginalSheetService originalSheetService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderOptionService orderOptionService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private StoreProductService storeProductService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private CzService czService;
	@Autowired
	private StoneService stoneService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PartService partService;
	@Autowired
	private AddService addService;
	@Autowired
	private SaleSheetService saleSheetService;
	
	@RequestMapping("/store/order/insert/insertOrderForm.do")
	public ModelAndView insertOrderForm(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		int branchSeq  = utilSession.getBranchSeq(session);
		List<MemberDTO> memberDtoList = memberService.getMemberList(branchSeq);
		List<ColorDTO> mainColorList = colorService.getColorList("M");
		List<ColorDTO> subColorList = colorService.getColorList("S");
		List<KDTO> kList = kService.getKList();
		List<PlateDTO> plateList = plateService.getPlateList();
		
		model.put("orderDate", dateService.getToday());
		model.put("memberDtoList", memberDtoList);
		model.put("mainColorList", mainColorList);
		model.put("subColorList", subColorList);
		model.put("kList", kList);
		model.put("plateList", plateList);
		utilSession.checkExistLogin(session, mav, "/store/order/insert/insertOrderForm.jsp");
		return mav;
	}

	@RequestMapping("/store/order/insert/insertOrder.do")
	public ModelAndView insertOption(ModelMap model, HttpSession session,
			@RequestParam("orderDate") String orderDate,
			@RequestParam("optionNumber") int optionNumber,
			@RequestParam("originalSheetNumber") int originalSheetNumber,
			@RequestParam("orderMemberSeq") int orderMemberSeq,
			@RequestParam("customerSeq") int customerSeq,
			@RequestParam("storeProductSeq") int storeProductSeq,
			@RequestParam("kSeq") int kSeq,
			@RequestParam("orderK") String orderK,
			@RequestParam("orderSubColor") String orderSubColor,
			@RequestParam("orderMainColor") String orderMainColor,
			@RequestParam("orderSize") String orderSize,
			@RequestParam("orderEtc") String orderEtc,
			@RequestParam("orderHurry") String orderHurry,
			@RequestParam("orderHalf") String orderHalf,
			@RequestParam("plateSeq") int plateSeq,
			@RequestParam("orderOptionName") String orderOptionName,
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
		int branchSeq = utilSession.getBranchSeq(session);
		int originalSheetSeq = originalSheetService.getOriginalSheetSeq(orderDate, originalSheetNumber, branchSeq);
		
		OrderDTO insertOrderDTO = new OrderDTO();
		insertOrderDTO.setCustomerSeq(customerSeq);
		insertOrderDTO.setOrderMemberSeq(orderMemberSeq);
		insertOrderDTO.setOriginalSheetSeq(originalSheetSeq);
		insertOrderDTO.setStoreProductSeq(storeProductSeq);
		
		int orderSeq = orderService.insertOrder(insertOrderDTO);
		
		OrderDetailDTO insertOrderDetailDto = new OrderDetailDTO();
		insertOrderDetailDto.setkSeq(kSeq);
		insertOrderDetailDto.setOrderEtc(orderEtc);
		insertOrderDetailDto.setOrderHurry(orderHurry);
		insertOrderDetailDto.setOrderHalf(orderHalf);
		insertOrderDetailDto.setOrderK(orderK);
		insertOrderDetailDto.setOrderMainColor(orderMainColor);
		insertOrderDetailDto.setOrderSeq(orderSeq);
		insertOrderDetailDto.setOrderSize(orderSize);
		insertOrderDetailDto.setOrderSubColor(orderSubColor);
		insertOrderDetailDto.setPlateSeq(plateSeq);
		
		orderService.insertOrderDetail(insertOrderDetailDto);
		orderService.insertOrderPrice(orderSeq);
		orderService.insertOrderWeight(orderSeq);
		
		OrderOptionDTO insertOrderOption = new OrderOptionDTO();
		insertOrderOption.setOrderSeq(orderSeq);
		insertOrderOption.setOrderOptionName(orderOptionName);
		int orderOptionSeq = orderOptionService.insertOrderOption(insertOrderOption);
		
		if(optionNumber < 9){
			int optionSeq = optionService.getOptionSeq(storeProductSeq, optionNumber);
			List<OptionCzDTO> optionCzList = optionService.getOptionCzList(optionSeq);
			List<OptionStoneDTO> optionStoneList = optionService. getOptionStoneList(optionSeq);
			orderOptionService.insertOrderOptionCz(orderOptionSeq, optionCzList);
			orderOptionService.insertOrderOptionStone(orderOptionSeq, optionStoneList);
			
		}else{
			orderOptionService.insertOrderOptionCz(orderOptionSeq, czSeqArr, czNameArr, czQtyArr, czSizeArr);
			orderOptionService.insertOrderOptionStone(orderOptionSeq, stoneSeqArr, stoneQtyArr, stoneNameArr, stoneSizeArr);
		}
		orderOptionService.insertOrderOptionPart(orderOptionSeq, partSeqArr, partNameArr);
		orderOptionService.insertOrderOptionAdd(orderOptionSeq, addSeqArr, addNameArr);
		
		orderOptionService.initOrderOptionLabor(orderOptionSeq);
		orderOptionService.initOrderOptionPrice(orderOptionSeq);
		
		orderService.updateOrderReleasePrice(orderOptionSeq, insertOrderDetailDto);
		
		orderService.updateOrderCzWeight(orderSeq);
		mav.setViewName("redirect:/store/order/insert/afterInsertOrder.do");
		return mav;
	}
	
	@RequestMapping("/store/order/insert/afterInsertOrder.do")
	public ModelAndView afterInsertOrder(){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/store/order/insert/ajaxCallBack/afterInsertOrder.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/getRegisterdOrderList.do")
	public ModelAndView getRegisterdOrderList( ModelMap model, HttpSession session,
			@RequestParam("orderDate") String orderDate,
			@RequestParam("originalSheetNumber") int originalSheetNumber){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		int originalSheetSeq = originalSheetService.getOriginalSheetSeq(orderDate, originalSheetNumber, branchSeq);
		List<ViewOrderDTO> viewOrderList = orderService.getTempOrderList(originalSheetSeq);
		
		model.put("viewOrderList", viewOrderList);
		mav.setViewName("/store/order/insert/ajaxCallBack/refreshTempOrderList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/refreshTempOrderList.do")
	public ModelAndView refreshTempOrderList( ModelMap model, HttpSession session,
			@RequestParam("orderDate") String orderDate,
			@RequestParam("originalSheetNumber") int originalSheetNumber){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		int originalSheetSeq = originalSheetService.getOriginalSheetSeq(orderDate, originalSheetNumber, branchSeq);
		List<ViewOrderDTO> viewOrderList = orderService.getTempOrderList(originalSheetSeq);
		
		model.put("viewOrderList", viewOrderList);
		mav.setViewName("/store/order/insert/ajaxCallBack/refreshTempOrderList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/update/getUpdateOrder.do")
	public ModelAndView getUpdteOrder(ModelMap model, HttpSession session,
			@RequestParam("orderSeq") int orderSeq){
		ModelAndView mav = new ModelAndView();
		OrderDTO order = orderService.getOrder(orderSeq);
		OrderDetailDTO orderDetail = orderService.getOrderDetail(orderSeq);
		int customerSeq = order.getCustomerSeq();
		CustomerDTO customer = customerService.getCustomer(customerSeq);
		int branchSeq = customer.getBranchSeq();
		int storeProductSeq = order.getStoreProductSeq();
		StoreProductDTO storeProduct = storeProductService.getStoreProduct(storeProductSeq);
		
		int orderOptionSeq = orderOptionService.getOrderOptionSeq(orderSeq);
		String orderOptionName = orderOptionService.getOrderOptionName(orderOptionSeq);
		List<OptionDTO> optionDtoList = optionService.getOptionList(storeProductSeq);
		
		List<CzSizeDTO> czSizeList = czService.getCzSizeList();
		List<StoneSizeDTO> stoneSizeList = stoneService.getStoneSizeList();
		int typeSeq = storeProductService.getTypeSeq(storeProductSeq);
		int typeMainSeq = productService.getTypeMainSeq(typeSeq);
		List<PartAttributeDTO> partAttributeList = partService.getPartAttributeList(typeMainSeq);
		List<AddDTO> addList = addService.getAddList(branchSeq);
		
		List<OrderOptionCzDTO> orderOptionCzList = orderOptionService.getOrderOptionCzList(orderOptionSeq);
		List<OrderOptionStoneDTO> orderOptionStoneList = orderOptionService.getOrderOptionStoneList(orderOptionSeq);
		List<OrderOptionPartDTO> orderOptionPartList = orderOptionService.getOrderOptionPartList(orderOptionSeq);
		List<OrderOptionAddDTO> orderOptionAddList = orderOptionService.getOrderOptionAddList(orderOptionSeq);
		
		List<Integer> orderOptionCzSizeSeqList = orderOptionService.getOptionCzSizeSeqList(orderOptionCzList);
		List<Integer> orderOptionStoneSizeSeqList = orderOptionService.getOptionStoneSizeSeqList(orderOptionStoneList);
		List<Integer> orderOptionPartAttributeSeqList = orderOptionService.getOptionPartAttributeSeq(orderOptionPartList);
		
		List<List<CzDTO>> czListResponse = orderOptionService.getOrderCzListResponse(orderOptionCzList, branchSeq);
		List<List<StoneDTO>> stoneListResponse = orderOptionService.getOrderStoneListResponse(orderOptionStoneList, branchSeq);
		List<List<PartDTO>> partListResponse = orderOptionService.getOrderPartListResponse(orderOptionPartList, branchSeq);
		
		model.put("order", order);
		model.put("customer", customer);
		model.put("storeProduct", storeProduct);
		model.put("orderDetail", orderDetail);
		
		model.put("orderOptionSeq", orderOptionSeq);
		model.put("orderOptionName", orderOptionName);
		model.put("optionDtoList", optionDtoList);
		model.put("czSizeList", czSizeList);
		model.put("stoneSizeList", stoneSizeList);
		model.put("partAttributeList", partAttributeList);
		model.put("addList", addList);
		
		model.put("orderOptionCzList", orderOptionCzList);
		model.put("orderOptionStoneList", orderOptionStoneList);
		model.put("orderOptionPartList", orderOptionPartList);
		model.put("orderOptionAddList", orderOptionAddList);
		
		model.put("orderOptionCzSizeSeqList", orderOptionCzSizeSeqList);
		model.put("orderOptionStoneSizeSeqList", orderOptionStoneSizeSeqList);
		model.put("orderOptionPartAttributeSeqList", orderOptionPartAttributeSeqList);
		
		model.put("czListResponse", czListResponse);
		model.put("stoneListResponse", stoneListResponse);
		model.put("partListResponse", partListResponse);
		
		mav.addObject("optionList", "/WEB-INF/view/store/orderOption/ajax/orderOptionList.jsp");
		mav.addObject("optionDetail", "/WEB-INF/view/store/orderOption/ajax/updateOrderOptionDetail.jsp");
		mav.setViewName("/store/order/update/ajax/updateOrderResult.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/update/updateOrder.do")
	public ModelAndView updateOrder(
			@RequestParam("orderSeq") int orderSeq,
			@RequestParam("orderDate") String orderDate,
			@RequestParam("originalSheetNumber") int originalSheetNumber,
			@RequestParam("optionNumber") int optionNumber,
			@RequestParam("orderMemberSeq") int orderMemberSeq,
			@RequestParam("customerSeq") int customerSeq,
			@RequestParam("storeProductSeq") int storeProductSeq,
			@RequestParam("kSeq") int kSeq,
			@RequestParam("orderK") String orderK,
			@RequestParam("orderSubColor") String orderSubColor,
			@RequestParam("orderMainColor") String orderMainColor,
			@RequestParam("orderSize") String orderSize,
			@RequestParam("orderEtc") String orderEtc,
			@RequestParam("orderHurry") String orderHurry,
			@RequestParam("orderHalf") String orderHalf,
			@RequestParam("plateSeq") int plateSeq,
			@RequestParam("orderOptionName") String orderOptionName,
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
			@RequestParam("addNameArr") String addNameArr){
		ModelAndView mav = new ModelAndView();
		int orderOptionSeq = orderOptionService.getOrderOptionSeq(orderSeq);
		
		OrderDTO updateOrder = new OrderDTO();
		updateOrder.setCustomerSeq(customerSeq);
		updateOrder.setOrderMemberSeq(orderMemberSeq);
		updateOrder.setOrderSeq(orderSeq);
		updateOrder.setStoreProductSeq(storeProductSeq);
		
		orderService.updateOrder(updateOrder);
		
		OrderDetailDTO updateOrderDetail = new OrderDetailDTO();
		updateOrderDetail.setkSeq(kSeq);
		updateOrderDetail.setOrderEtc(orderEtc);
		updateOrderDetail.setOrderHurry(orderHurry);
		updateOrderDetail.setOrderHalf(orderHalf);
		updateOrderDetail.setOrderK(orderK);
		updateOrderDetail.setOrderMainColor(orderMainColor);
		updateOrderDetail.setOrderSeq(orderSeq);
		updateOrderDetail.setOrderSize(orderSize);
		updateOrderDetail.setOrderSubColor(orderSubColor);
		updateOrderDetail.setPlateSeq(plateSeq);
		
		orderService.updateOrderDetail(updateOrderDetail);
		
		OrderOptionDTO orderOption = new OrderOptionDTO();
		orderOption.setOrderOptionName(orderOptionName);
		orderOption.setOrderOptionSeq(orderOptionSeq);
		
		orderOptionService.updateOrderOption(orderOption);
		
		orderOptionService.deleteOrderOptionCz(orderOptionSeq);
		orderOptionService.deleteOrderOptionStone(orderOptionSeq);
		orderOptionService.deleteOrderOptionPart(orderOptionSeq);
		orderOptionService.deleteOrderOptionAdd(orderOptionSeq);
		
		orderOptionService.insertOrderOptionCz(orderOptionSeq, czSeqArr, czNameArr, czQtyArr, czSizeArr);
		orderOptionService.insertOrderOptionStone(orderOptionSeq, stoneSeqArr, stoneQtyArr, stoneNameArr, stoneSizeArr);
		orderOptionService.insertOrderOptionPart(orderOptionSeq, partSeqArr, partNameArr);
		orderOptionService.insertOrderOptionAdd(orderOptionSeq, addSeqArr, addNameArr);
		orderOptionService.initOrderOptionLabor(orderOptionSeq);
		orderOptionService.initOrderOptionPrice(orderOptionSeq);
		orderService.updateOrderReleasePrice(orderOptionSeq, updateOrderDetail);
		orderService.updateOrderCzWeight(orderSeq);
		mav.setViewName("/store/order/update/ajax/afterUpdateOrder.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/update/deleteOrder.do")
	public ModelAndView deleteOrder(@RequestParam("orderSeq") int orderSeq){
		ModelAndView mav = new ModelAndView();
		int orderOptionSeq = orderOptionService.getOrderOptionSeq(orderSeq);
		orderOptionService.deleteOrderOptionCz(orderOptionSeq);
		orderOptionService.deleteOrderOptionStone(orderOptionSeq);
		orderOptionService.deleteOrderOptionPart(orderOptionSeq);
		orderOptionService.deleteOrderOptionAdd(orderOptionSeq);
		orderOptionService.deleteOrderOption(orderOptionSeq);
		
		orderService.deleteOrderWeight(orderSeq);
		orderService.deleteOrderPrice(orderSeq);
		orderService.deleteOrderDetail(orderSeq);
		orderService.deleteOrder(orderSeq);
		
		mav.setViewName("/store/order/update/ajax/afterDeleteOrder.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/update/registerOrder.do")
	public ModelAndView registerOrder(
			HttpSession session,
			@RequestParam("orderDate") String orderDate,
			@RequestParam("originalSheetNumber") int originalSheetNumber){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		int originalSheetSeq = originalSheetService.getOriginalSheetSeq(orderDate, originalSheetNumber, branchSeq);
		orderService.registerOrder(originalSheetSeq);
		originalSheetService.closeOriginalSheet(originalSheetSeq);
		mav.setViewName("/store/order/update/ajax/afterRegisterOrder.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/getOrderOption.do")
	public ModelAndView getOrderOption(ModelMap model, HttpSession session,
			@RequestParam("orderOptionSeq") int orderOptionSeq){
		ModelAndView mav = new ModelAndView();
		int storeProductSeq = orderOptionService.getStoreProductSeq(orderOptionSeq);
		int branchSeq = utilSession.getBranchSeq(session);
		List<CzSizeDTO> czSizeList = czService.getCzSizeList();
		List<StoneSizeDTO> stoneSizeList = stoneService.getStoneSizeList();
		int typeSeq = productService.getTypeSeq(storeProductSeq);
		int typeMainSeq = productService.getTypeMainSeq(typeSeq);
		List<PartAttributeDTO> partAttributeList = partService.getPartAttributeList(typeMainSeq);
		List<AddDTO> addList = addService.getAddList(branchSeq);
		
		List<OrderOptionCzDTO> orderOptionCzList = orderOptionService.getOrderOptionCzList(orderOptionSeq);
		List<OrderOptionStoneDTO> orderOptionStoneList = orderOptionService.getOrderOptionStoneList(orderOptionSeq);
		List<OrderOptionPartDTO> orderOptionPartList = orderOptionService.getOrderOptionPartList(orderOptionSeq);
		List<OrderOptionAddDTO> orderOptionAddList = orderOptionService.getOrderOptionAddList(orderOptionSeq);
		
		List<Integer> orderOptionCzSizeSeqList = orderOptionService.getOptionCzSizeSeqList(orderOptionCzList);
		List<Integer> orderOptionStoneSizeSeqList = orderOptionService.getOptionStoneSizeSeqList(orderOptionStoneList);
		List<Integer> orderOptionPartAttributeSeqList = orderOptionService.getOptionPartAttributeSeq(orderOptionPartList);
		
		List<List<CzDTO>> czListResponse = orderOptionService.getOrderCzListResponse(orderOptionCzList, branchSeq);
		List<List<StoneDTO>> stoneListResponse = orderOptionService.getOrderStoneListResponse(orderOptionStoneList, branchSeq);
		List<List<PartDTO>> partListResponse = orderOptionService.getOrderPartListResponse(orderOptionPartList, branchSeq);
		
		
		model.put("czSizeList", czSizeList);
		model.put("stoneSizeList", stoneSizeList);
		model.put("partAttributeList", partAttributeList);
		model.put("addList", addList);
		
		model.put("orderOptionCzSizeSeqList", orderOptionCzSizeSeqList);
		model.put("orderOptionStoneSizeSeqList", orderOptionStoneSizeSeqList);
		model.put("orderOptionPartAttributeSeqList", orderOptionPartAttributeSeqList);
		
		model.put("orderOptionCzList", orderOptionCzList);
		model.put("orderOptionStoneList", orderOptionStoneList);
		model.put("orderOptionPartList", orderOptionPartList);
		model.put("orderOptionAddList", orderOptionAddList);
		
		model.put("czListResponse",czListResponse);
		model.put("stoneListResponse",stoneListResponse);
		model.put("partListResponse",partListResponse);
		model.put("orderOptionAddList",orderOptionAddList);
		
		mav.addObject("optionDetail", "/WEB-INF/view/store/orderOption/ajax/updateOrderOptionDetail.jsp");
		mav.setViewName("/store/option/order/afterGetOptionForOrder.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/inquery/inqueryOrderForm.do")
	public ModelAndView inqueryOrderForm(HttpSession session){
		ModelAndView mav = new ModelAndView();
		utilSession.checkExistLogin(session, mav, "/store/order/inquery/inqueryOrderForm.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/inquery/getInqueryOrder.do")
	public ModelAndView getInqueryOrder(ModelMap model, HttpSession session,
			@RequestParam("productCode") String productCode){
		ModelAndView mav = new ModelAndView();
		String branchName = utilSession.getBranchName(session);
		System.out.println(branchName);
		System.out.println(productCode);
		List<InqueryOrderDTO> inqueryOrderList = orderService.getInqueryOrder(productCode , branchName);
		model.put("inqueryOrderList", inqueryOrderList);
		for(InqueryOrderDTO data : inqueryOrderList){
			System.out.println(data.getOrderSeq());
		}
		mav.setViewName("/store/order/inquery/inqueryOrderList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/inquery/getOrderListByCustomer.do")
	public ModelAndView getOrderListByCustomer(ModelMap model, HttpSession session,
			@RequestParam("customerSeq") int customerSeq){
		ModelAndView mav = new ModelAndView();
		int branchSeq = utilSession.getBranchSeq(session);
		InqueryOrderDTO inqueryOrder = new InqueryOrderDTO();
		inqueryOrder.setBranchSeq(branchSeq);
		inqueryOrder.setCustomerSeq(customerSeq);
		List<InqueryOrderDTO> inqueryOrderList = orderService.getInqueryOrderByCustomer(inqueryOrder);
		
		model.put("inqueryOrderList", inqueryOrderList);
		mav.setViewName("/store/order/inquery/inqueryOrderList.jsp");
		return mav;
	}
	
	@RequestMapping("/store/order/downloadOrderListForm.do")
	public ModelAndView downloadOrderListForm(ModelMap model, HttpSession session){
		ModelAndView mav = new ModelAndView();
		GregorianCalendar cal = new GregorianCalendar();
		int branchSeq = utilSession.getBranchSeq(session);
		int thisYear = cal.get(Calendar.YEAR);
		int thisMonth = cal.get(Calendar.MONTH) + 1;
		String successView = "/store/order/download/downloadOrderListForm.jsp";
		List<SaleSheetDTO> saleSheetList = saleSheetService.getSaleSheetListByMonth(thisYear, thisMonth, branchSeq);
		
		model.put("saleSheetList", saleSheetList);
		model.put("branchSeq", branchSeq);
		model.put("thisYear", thisYear);
		model.put("thisMonth", thisMonth);
		utilSession.checkExistLogin(session, mav, successView);
		
		return mav;
	}
}