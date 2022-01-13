package com.jewsol.store.orderOption.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.add.bean.AddDTO;
import com.jewsol.store.add.service.AddService;
import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.service.CzService;
import com.jewsol.store.part.bean.PartDTO;
import com.jewsol.store.part.service.PartService;
import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.service.StoneService;
import com.jewsol.store.option.bean.OptionCzDTO;
import com.jewsol.store.option.bean.OptionStoneDTO;
import com.jewsol.store.orderOption.bean.OrderOptionAddDTO;
import com.jewsol.store.orderOption.bean.OrderOptionCzDTO;
import com.jewsol.store.orderOption.bean.OrderOptionDTO;
import com.jewsol.store.orderOption.bean.OrderOptionPartDTO;
import com.jewsol.store.orderOption.bean.OrderOptionStoneDTO;
import com.jewsol.store.orderOption.dao.OrderOptionDAO;

@Service
public class OrderOptionService {
	@Autowired
	OrderOptionDAO orderOptionDao;
	@Autowired
	UtilSession utilSession;
	@Autowired
	CzService czService;
	@Autowired
	StoneService stoneService;
	@Autowired
	PartService partService;
	@Autowired
	AddService addService;

	public int insertOrderOption(OrderOptionDTO insertOrderOption) {
		return orderOptionDao.insertOrderOption(insertOrderOption);
	}
	
	public void insertOrderOptionCz(int orderOptionSeq, String czSeqArr,
			String czNameArr, String czQtyArr, String czSizeArr) {
		String[] czSeqArray = czSeqArr.split(",");
		String[] czNameArray = czNameArr.split(",");
		String[] czQtyArray = czQtyArr.split(",");
		String[] czSizeArray = czSizeArr.split(",");
		int czSeq = 0;
		String orderOptionCzName = "";
		String orderOptionCzSize = "";
		int orderOptionCzQty = 0;
		int orderOptionCzPrice = 0;
		int orderOptionCzLabor = 0;
		OrderOptionCzDTO orderOptionCzDto = new OrderOptionCzDTO();
		CzDTO czDto = new CzDTO();
		if(utilSession.checkArrayLength(czSeqArray)){
			for(int i = 0; i < czSeqArray.length; i++){
				czSeq = Integer.parseInt(czSeqArray[i]);
				orderOptionCzQty = Integer.parseInt(czQtyArray[i]);
				orderOptionCzName = czNameArray[i];
				orderOptionCzSize = czSizeArray[i];
				czDto = czService.getCz(czSeq);
				orderOptionCzPrice = czDto.getCzPrice() * orderOptionCzQty;
				orderOptionCzLabor = czDto.getCzLabor() * orderOptionCzQty;
				
				orderOptionCzDto.setOrderOptionSeq(orderOptionSeq);
				orderOptionCzDto.setCzSeq(czSeq);
				orderOptionCzDto.setOrderOptionCzName(orderOptionCzName);
				orderOptionCzDto.setOrderOptionCzSize(orderOptionCzSize);
				orderOptionCzDto.setOrderOptionCzQty(orderOptionCzQty);
				orderOptionCzDto.setOrderOptionCzPrice(orderOptionCzPrice);
				orderOptionCzDto.setOrderOptionCzLabor(orderOptionCzLabor);
				
				orderOptionDao.insertOrderOptionCz(orderOptionCzDto);
			}
		}
	}

	public void insertOrderOptionStone(int orderOptionSeq, String stoneSeqArr,
			String stoneQtyArr, String stoneNameArr, String stoneSizeArr) {
		String[] stoneSeqArray = stoneSeqArr.split(",");
		String[] stoneNameArray = stoneNameArr.split(",");
		String[] stoneQtyArray = stoneQtyArr.split(",");
		String[] stoneSizeArray = stoneSizeArr.split(",");
		int stoneSeq = 0;
		String orderOptionStoneName = "";
		String orderOptionStoneSize = "";
		int orderOptionStoneQty = 0;
		int orderOptionStonePrice = 0;
		int orderOptionStoneLabor = 0;
		OrderOptionStoneDTO orderOptionStoneDto = new OrderOptionStoneDTO();
		StoneDTO stoneDto = new StoneDTO();
		if(utilSession.checkArrayLength(stoneSeqArray)){
			for(int i = 0; i < stoneSeqArray.length; i++){
				stoneSeq = Integer.parseInt(stoneSeqArray[i]);
				orderOptionStoneQty = Integer.parseInt(stoneQtyArray[i]);
				orderOptionStoneName = stoneNameArray[i];
				orderOptionStoneSize = stoneSizeArray[i];
				stoneDto = stoneService.getStone(stoneSeq);
				orderOptionStonePrice = stoneDto.getStonePrice() * orderOptionStoneQty;
				orderOptionStoneLabor = stoneDto.getStoneLabor() * orderOptionStoneQty;
				
				orderOptionStoneDto.setOrderOptionSeq(orderOptionSeq);
				orderOptionStoneDto.setStoneSeq(stoneSeq);
				orderOptionStoneDto.setOrderOptionStoneName(orderOptionStoneName);
				orderOptionStoneDto.setOrderOptionStoneSize(orderOptionStoneSize);
				orderOptionStoneDto.setOrderOptionStoneQty(orderOptionStoneQty);
				orderOptionStoneDto.setOrderOptionStonePrice(orderOptionStonePrice);
				orderOptionStoneDto.setOrderOptionStoneLabor(orderOptionStoneLabor);
				
				orderOptionDao.insertOrderOptionStone(orderOptionStoneDto);
			}
		}
	}

	public void insertOrderOptionPart(int orderOptionSeq, String partSeqArr,
			String partNameArr) {
		String[] partSeqArray = partSeqArr.split(",");
		String[] partNameArray = partNameArr.split(",");
		int partSeq = 0;
		String orderOptionPartName = "";
		int orderOptionPartPrice = 0;
		int orderOptionPartLabor = 0;
		OrderOptionPartDTO orderOptionPartDto = new OrderOptionPartDTO();
		PartDTO partDto = new PartDTO();
		
		if(utilSession.checkArrayLength(partSeqArray)){
			for(int i = 0; i < partSeqArray.length; i++){
				partSeq = Integer.parseInt(partSeqArray[i]);
				orderOptionPartName = partNameArray[i];
				partDto = partService.getPart(partSeq);
				orderOptionPartPrice = partDto.getPartPrice();
				orderOptionPartLabor = partDto.getPartLabor();
				
				orderOptionPartDto.setOrderOptionSeq(orderOptionSeq);
				orderOptionPartDto.setPartSeq(partSeq);
				orderOptionPartDto.setOrderOptionPartName(orderOptionPartName);
				orderOptionPartDto.setOrderOptionPartPrice(orderOptionPartPrice);
				orderOptionPartDto.setOrderOptionPartLabor(orderOptionPartLabor);
				
				orderOptionDao.insertOrderOptionPart(orderOptionPartDto);
			}
		}
	}

	public void insertOrderOptionAdd(int orderOptionSeq, String addSeqArr,
			String addNameArr) {
		String[] addSeqArray = addSeqArr.split(",");
		String[] addNameArray = addNameArr.split(",");
		int addSeq = 0;
		String orderOptionAddName = "";
		int orderOptionAddPrice = 0;
		int orderOptionAddLabor = 0;
		OrderOptionAddDTO orderOptionAddDto = new OrderOptionAddDTO();
		AddDTO addDto = new AddDTO();
		
		if(utilSession.checkArrayLength(addSeqArray)){
			for(int i = 0; i < addSeqArray.length; i++){
				addSeq = Integer.parseInt(addSeqArray[i]);
				orderOptionAddName = addNameArray[i];
				addDto = addService.getAdd(addSeq);
				orderOptionAddPrice = addDto.getAddPrice();
				orderOptionAddLabor = addDto.getAddLabor();
				
				orderOptionAddDto.setOrderOptionSeq(orderOptionSeq);
				orderOptionAddDto.setAddSeq(addSeq);
				orderOptionAddDto.setOrderOptionAddName(orderOptionAddName);
				orderOptionAddDto.setOrderOptionAddPrice(orderOptionAddPrice);
				orderOptionAddDto.setOrderOptionAddLabor(orderOptionAddLabor);
				
				orderOptionDao.insertOrderOptionAdd(orderOptionAddDto);
			}
		}
	}
	
	public void initOrderOptionLabor(int orderOptionSeq) {
		orderOptionDao.initOrderOptionLabor(orderOptionSeq);
		
	}

	public void initOrderOptionPrice(int orderOptionSeq) {
		orderOptionDao.initOrderOptionPrice(orderOptionSeq);
		
	}

	public int getOrderOptionSeq(int orderSeq) {
		return orderOptionDao.getOrderOptionSeq(orderSeq);
	}

	public String getOrderOLptionName(int orderOptionSeq) {
		return orderOptionDao.getOrderOptionName(orderOptionSeq);
	}

	public List<OrderOptionCzDTO> getOrderOptionCzList(int orderOptionSeq) {
		return orderOptionDao.getOrderOptionCzList(orderOptionSeq);
	}

	public List<OrderOptionStoneDTO> getOrderOptionStoneList(int orderOptionSeq) {
		return orderOptionDao.getOrderOptionStoneList(orderOptionSeq);
	}

	public List<OrderOptionPartDTO> getOrderOptionPartList(int orderOptionSeq) {
		return orderOptionDao.getOrderOptionPartList(orderOptionSeq);
	}

	public List<OrderOptionAddDTO> getOrderOptionAddList(int orderOptionSeq) {
		return orderOptionDao.getOrderOptionAddList(orderOptionSeq);
	}

	public List<List<CzDTO>> getOrderCzListResponse(
			List<OrderOptionCzDTO> orderOptionCzList, int branchSeq) {
		ArrayList<List<CzDTO>> orderCzListResponse = new ArrayList<List<CzDTO>>(orderOptionCzList.size());
		for(int i = 0; i < orderOptionCzList.size(); i++){
			int czSizeSeq = czService.getCzSizeSeq(orderOptionCzList.get(i).getCzSeq());
			List<CzDTO> list = czService.getCzListByCzSizeSeq(czSizeSeq, branchSeq);
			orderCzListResponse.add(i, list);
		}
		return orderCzListResponse;
	}

	public List<List<StoneDTO>> getOrderStoneListResponse(
			List<OrderOptionStoneDTO> orderOptionStoneList, int branchSeq) {
		StoneDTO stone = new StoneDTO();
		stone.setBranchSeq(branchSeq);
		ArrayList<List<StoneDTO>> orderStoneListResponse = new ArrayList<List<StoneDTO>>(orderOptionStoneList.size());
		for(int i = 0; i < orderOptionStoneList.size(); i++){
			int stoneSizeSeq = stoneService.getStoneSizeSeq(orderOptionStoneList.get(i).getStoneSeq());
			stone.setStoneSizeSeq(stoneSizeSeq);
			List<StoneDTO> list = stoneService.getStoneListByStoneSizeSeq(stone);
			orderStoneListResponse.add(i, list);
		}
		return orderStoneListResponse;
	}

	public List<List<PartDTO>> getOrderPartListResponse(
			List<OrderOptionPartDTO> orderOptionPartList, int branchSeq) {
		ArrayList<List<PartDTO>> orderPartListResponse = new ArrayList<List<PartDTO>>(orderOptionPartList.size());
		PartDTO part = new PartDTO();
		part.setBranchSeq(branchSeq);
		for(int i = 0; i < orderOptionPartList.size(); i++){
			int partAttributeSeq = partService.getPartAttributeSeq(orderOptionPartList.get(i).getPartSeq());
			part.setPartAttributeSeq(partAttributeSeq);
			List<PartDTO> list = partService.getPartListByPartAttributeSeq(part);
			orderPartListResponse.add(i, list);
		}
		return orderPartListResponse;
	}

	public List<Integer> getOptionCzSizeSeqList(
			List<OrderOptionCzDTO> orderOptionCzList) {
		List<Integer> optionCzSizeSeqList = new ArrayList<Integer>();
		for(OrderOptionCzDTO data : orderOptionCzList){
			optionCzSizeSeqList.add(czService.getCzSizeSeq(data.getCzSeq()));
		}

		return optionCzSizeSeqList;
	}

	public List<Integer> getOptionStoneSizeSeqList(
			List<OrderOptionStoneDTO> orderOptionStoneList) {
		List<Integer> optionStoneSizeSeqList = new ArrayList<Integer>();
		
		for(OrderOptionStoneDTO data : orderOptionStoneList){
			optionStoneSizeSeqList.add(stoneService.getStoneSizeSeq(data.getStoneSeq()));
		}

		return optionStoneSizeSeqList;
	}

	public List<Integer> getOptionPartAttributeSeq(
			List<OrderOptionPartDTO> orderOptionPartList) {
		List<Integer> optionPartAttributeSeqList = new ArrayList<Integer>();
		
		for(OrderOptionPartDTO data : orderOptionPartList){
			optionPartAttributeSeqList.add(partService.getPartAttributeSeq(data.getPartSeq()));
		}

		return optionPartAttributeSeqList;
	}

	public void deleteOrderOptionCz(int orderOptionSeq) {
		orderOptionDao.deleteOrderOptionCz(orderOptionSeq);
	}

	public void deleteOrderOptionStone(int orderOptionSeq) {
		orderOptionDao.deleteOrderOptionStone(orderOptionSeq);
	}

	public void deleteOrderOptionPart(int orderOptionSeq) {
		orderOptionDao.deleteOrderOptionPart(orderOptionSeq);
	}

	public void deleteOrderOptionAdd(int orderOptionSeq) {
		orderOptionDao.deleteOrderOptionAdd(orderOptionSeq);
	}

	public void deleteOrderOption(int orderOptionSeq) {
		orderOptionDao.deleteOrderOption(orderOptionSeq);
	}

	public int getStoreProductSeq(int orderOptionSeq) {
		
		return orderOptionDao.getStoreProductSeq(orderOptionSeq);
	}

	public int getOrderOptionLabor(int orderSeq) {
		return orderOptionDao.getOrderOptionLabor(orderSeq);
	}

	public String getOrderOptionName(int orderOptionSeq) {
		return orderOptionDao.getOrderOptionName(orderOptionSeq);
	}

	public void insertOrderOptionCz(int orderOptionSeq,
			List<OptionCzDTO> optionCzList) {
		for(OptionCzDTO optionCz : optionCzList){
			OrderOptionCzDTO orderOptionCzDto = new OrderOptionCzDTO();
			CzDTO czDto = new CzDTO();
			czDto = czService.getCz(optionCz.getCzSeq());
			int orderOptionCzPrice = czDto.getCzPrice() * optionCz.getOptionCzQty();
			int orderOptionCzLabor = czDto.getCzLabor() * optionCz.getOptionCzQty();
			
			orderOptionCzDto.setOrderOptionSeq(orderOptionSeq);
			orderOptionCzDto.setCzSeq(optionCz.getCzSeq());
			orderOptionCzDto.setOrderOptionCzName(optionCz.getCzName());
			orderOptionCzDto.setOrderOptionCzSize(optionCz.getCzSize());
			orderOptionCzDto.setOrderOptionCzQty(optionCz.getOptionCzQty());
			orderOptionCzDto.setOrderOptionCzPrice(orderOptionCzPrice);
			orderOptionCzDto.setOrderOptionCzLabor(orderOptionCzLabor);
			
			orderOptionDao.insertOrderOptionCz(orderOptionCzDto);	
			}
	}

	public void insertOrderOptionStone(int orderOptionSeq,
			List<OptionStoneDTO> optionStoneList) {
		for(OptionStoneDTO optionStone : optionStoneList){
			OrderOptionStoneDTO orderOptionStone = new OrderOptionStoneDTO();
			StoneDTO stoneDto = new StoneDTO();
			stoneDto = stoneService.getStone(optionStone.getStoneSeq());
			int orderOptionStonePrice = stoneDto.getStonePrice() * optionStone.getOptionStoneQty();
			int orderOptionStoneLabor = stoneDto.getStoneLabor() * optionStone.getOptionStoneQty();
			
			orderOptionStone.setOrderOptionSeq(orderOptionSeq);
			orderOptionStone.setStoneSeq(optionStone.getStoneSeq());
			orderOptionStone.setOrderOptionStoneName(optionStone.getStoneName());
			orderOptionStone.setOrderOptionStoneSize(optionStone.getStoneSize());
			orderOptionStone.setOrderOptionStoneQty(optionStone.getOptionStoneQty());
			orderOptionStone.setOrderOptionStonePrice(orderOptionStonePrice);
			orderOptionStone.setOrderOptionStoneLabor(orderOptionStoneLabor);
			
			orderOptionDao.insertOrderOptionStone(orderOptionStone);
		}
		
	}

	public void updateOrderOption(OrderOptionDTO orderOption) {
		orderOptionDao.updateOrderOption(orderOption);
		
	}

	public int getOrderOptionPrice(int orderOptionSeq) {
		return orderOptionDao.getOrderOptionPrice(orderOptionSeq);
	}

}