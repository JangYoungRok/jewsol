package com.jewsol.store.option.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.store.add.bean.AddDTO;
import com.jewsol.store.add.service.AddService;
import com.jewsol.store.cz.bean.CzDTO;
import com.jewsol.store.cz.service.CzService;
import com.jewsol.store.part.bean.PartDTO;
import com.jewsol.store.part.service.PartService;
import com.jewsol.store.stone.bean.StoneDTO;
import com.jewsol.store.stone.service.StoneService;
import com.jewsol.store.option.bean.OptionAddDTO;
import com.jewsol.store.option.bean.OptionCzDTO;
import com.jewsol.store.option.bean.OptionDTO;
import com.jewsol.store.option.bean.OptionPartDTO;
import com.jewsol.store.option.bean.OptionStoneDTO;
import com.jewsol.store.option.dao.OptionDAO;
import com.jewsol.store.storeProduct.service.StoreProductService;

@Service
public class OptionService {
	@Autowired
	private OptionDAO optionDao;
	@Autowired
	private CzService czService;
	@Autowired
	private StoneService stoneService;
	@Autowired
	private PartService partService;
	@Autowired
	private AddService addService;
	@Autowired
	private StoreProductService storeProductService;
	
	public int insertOption(int storeProductSeq, String optionName,
			int optionNumber) {
		OptionDTO optionDto = new OptionDTO();
		optionDto.setStoreProductSeq(storeProductSeq);
		optionDto.setOptionName(optionName);
		optionDto.setOptionNumber(optionNumber);
		
		return optionDao.insertOption(optionDto);
	}
	
	public boolean checkArrayLength(String[] stringArr){
		boolean result =false;
		
		if(stringArr[0] != ""){
			result = true;
		}
		return result;
	}

	public void insertOptionCz(int optionSeq, String czSeqArr,
			String czNameArr, String czQtyArr, String czSizeArr) {
		String[] czSeqArray = czSeqArr.split(",");
		String[] czNameArray = czNameArr.split(",");
		String[] czQtyArray = czQtyArr.split(",");
		String[] czSizeArray = czSizeArr.split(",");
		int czSeq = 0;
		String czName = "";
		String czSize = "";
		int optionCzQty = 0;
		int optionCzPrice = 0;
		int optionCzLabor = 0;
		OptionCzDTO optionCzDto = new OptionCzDTO();
		CzDTO czDto = new CzDTO();
		if(checkArrayLength(czSeqArray)){
			for(int i = 0; i < czSeqArray.length; i++){
				czSeq = Integer.parseInt(czSeqArray[i]);
				optionCzQty = Integer.parseInt(czQtyArray[i]);
				czName = czNameArray[i];
				czSize = czSizeArray[i];
				czDto = czService.getCz(czSeq);
				optionCzPrice = czDto.getCzPrice() * optionCzQty;
				optionCzLabor = czDto.getCzLabor() * optionCzQty;
				
				optionCzDto.setOptionSeq(optionSeq);
				optionCzDto.setCzSeq(czSeq);
				optionCzDto.setCzName(czName);
				optionCzDto.setCzSize(czSize);
				optionCzDto.setOptionCzQty(optionCzQty);
				optionCzDto.setOptionCzPrice(optionCzPrice);
				optionCzDto.setOptionCzLabor(optionCzLabor);
				
				optionDao.insertOptionCz(optionCzDto);
			}
		}
	}

	public void insertOptionStone(int optionSeq, String stoneSeqArr,
			String stoneQtyArr, String stoneNameArr, String stoneSizeArr) {
		String[] stoneSeqArray = stoneSeqArr.split(",");
		String[] stoneNameArray = stoneNameArr.split(",");
		String[] stoneQtyArray = stoneQtyArr.split(",");
		String[] stoneSizeArray = stoneSizeArr.split(",");
		int stoneSeq = 0;
		String stoneName = "";
		String stoneSize = "";
		int optionStoneQty = 0;
		int optionStonePrice = 0;
		int optionStoneLabor = 0;
		OptionStoneDTO optionStoneDto = new OptionStoneDTO();
		StoneDTO stoneDto = new StoneDTO();
		if(checkArrayLength(stoneSeqArray)){
			for(int i = 0; i < stoneSeqArray.length; i++){
				stoneSeq = Integer.parseInt(stoneSeqArray[i]);
				optionStoneQty = Integer.parseInt(stoneQtyArray[i]);
				stoneName = stoneNameArray[i];
				stoneSize = stoneSizeArray[i];
				stoneDto = stoneService.getStone(stoneSeq);
				optionStonePrice = stoneDto.getStonePrice() * optionStoneQty;
				optionStoneLabor = stoneDto.getStoneLabor() * optionStoneQty;
				
				optionStoneDto.setOptionSeq(optionSeq);
				optionStoneDto.setStoneSeq(stoneSeq);
				optionStoneDto.setStoneName(stoneName);
				optionStoneDto.setStoneSize(stoneSize);
				optionStoneDto.setOptionStoneQty(optionStoneQty);
				optionStoneDto.setOptionStonePrice(optionStonePrice);
				optionStoneDto.setOptionStoneLabor(optionStoneLabor);
				
				optionDao.insertOptionStone(optionStoneDto);
			}
		}
	}

	public void insertOptionPart(int optionSeq, String partSeqArr,
			String partNameArr) {
		String[] partSeqArray = partSeqArr.split(",");
		String[] partNameArray = partNameArr.split(",");
		int partSeq = 0;
		String partName = "";
		int optionPartPrice = 0;
		int optionPartLabor = 0;
		OptionPartDTO optionPartDto = new OptionPartDTO();
		PartDTO partDto = new PartDTO();
		
		if(checkArrayLength(partSeqArray)){
			for(int i = 0; i < partSeqArray.length; i++){
				partSeq = Integer.parseInt(partSeqArray[i]);
				partName = partNameArray[i];
				partDto = partService.getPart(partSeq);
				optionPartPrice = partDto.getPartPrice();
				optionPartLabor = partDto.getPartLabor();
				
				optionPartDto.setOptionSeq(optionSeq);
				optionPartDto.setPartSeq(partSeq);
				optionPartDto.setPartName(partName);
				optionPartDto.setOptionPartPrice(optionPartPrice);
				optionPartDto.setOptionPartLabor(optionPartLabor);
				
				optionDao.insertOptionPart(optionPartDto);
			}
		}
	}

	public void insertOptionAdd(int optionSeq, String addSeqArr,
			String addNameArr) {
		String[] addSeqArray = addSeqArr.split(",");
		String[] addNameArray = addNameArr.split(",");
		int addSeq = 0;
		String addName = "";
		int optionAddPrice = 0;
		int optionAddLabor = 0;
		OptionAddDTO optionAddDto = new OptionAddDTO();
		AddDTO addDto = new AddDTO();
		
		if(checkArrayLength(addSeqArray)){
			for(int i = 0; i < addSeqArray.length; i++){
				addSeq = Integer.parseInt(addSeqArray[i]);
				addName = addNameArray[i];
				addDto = addService.getAdd(addSeq);
				optionAddPrice = addDto.getAddPrice();
				optionAddLabor = addDto.getAddLabor();
				
				optionAddDto.setOptionSeq(optionSeq);
				optionAddDto.setAddSeq(addSeq);
				optionAddDto.setAddName(addName);
				optionAddDto.setOptionAddPrice(optionAddPrice);
				optionAddDto.setOptionAddLabor(optionAddLabor);
				
				optionDao.insertOptionAdd(optionAddDto);
			}
		}
	}

	public void initOptionLabor(int optionSeq) {
		optionDao.initOptionLabor(optionSeq);
	}

	public void initOptionPrice(int optionSeq) {
		optionDao.initOptionPrice(optionSeq);
	}

	public String getOptionName(int storeProductSeq, int optionNumber) {
		return optionDao.getOptionName(storeProductSeq, optionNumber);
	}

	public void updateOptionName(OptionDTO optionDto) {
		optionDao.updateOptionName(optionDto);
	}

	public OptionDTO getOption(int storeProductSeq, int optionNumber) {
		return optionDao.getOption(storeProductSeq, optionNumber);
	}

	public int getOptionSeq(int storeProductSeq, int optionNumber) {
		
		return optionDao.getOptionSeq(storeProductSeq, optionNumber);
	}

	public List<OptionCzDTO> getOptionCzList(int optionSeq) {
		return optionDao.getOptionCzList(optionSeq);
	}
	
	public List<OptionStoneDTO> getOptionStoneList(int optionSeq) {
		return optionDao.getOptionStoneList(optionSeq);
	}
	
	public List<OptionPartDTO> getOptionPartList(int optionSeq) {
		return optionDao.getOptionPartList(optionSeq);
	}
	
	public List<OptionAddDTO> getOptionAddList(int optionSeq) {
		return optionDao.getOptionAddList(optionSeq);
	}

	public List<OptionDTO> getOptionList(int storeProductSeq) {
		return optionDao.getOptionList(storeProductSeq);
	}

	public int getOptionCzPrice(List<OptionCzDTO> optionCzList) {
		int optionCzPrice = 0;
		
		for(OptionCzDTO data : optionCzList){
			optionCzPrice = optionCzPrice + data.getOptionCzPrice();
		}
		return optionCzPrice;
	}

	public int getOptionStonePrice(List<OptionStoneDTO> optionStoneList) {
		int optionStonePrice = 0;
		
		for(OptionStoneDTO data : optionStoneList){
			optionStonePrice = optionStonePrice + data.getOptionStonePrice();
		}
		return optionStonePrice;
	}

	public int getOptionPartPrice(List<OptionPartDTO> optionPartList) {
		int optionPartPrice = 0;
		for(OptionPartDTO data : optionPartList){
			optionPartPrice = optionPartPrice + data.getOptionPartPrice();
		}
		return optionPartPrice;
	}

	public int getOptionAddPRice(List<OptionAddDTO> optionAddList) {
		int optionAddPrice = 0;
		for(OptionAddDTO data : optionAddList){
			optionAddPrice = optionAddPrice + data.getOptionAddPrice();
		}
		return optionAddPrice;
	}

	public ArrayList<List<CzDTO>> getCzListResponse(
			List<OptionCzDTO> optionCzList, int branchSeq) {
		ArrayList<List<CzDTO>> czListResponse = new ArrayList<List<CzDTO>>(optionCzList.size());
		for(int i = 0; i < optionCzList.size(); i++){
			int czSizeSeq = czService.getCzSizeSeq(optionCzList.get(i).getCzSeq());
			List<CzDTO> list = czService.getCzListByCzSizeSeq(czSizeSeq, branchSeq);
			czListResponse.add(i, list);
		}
		return czListResponse;
	}

	public ArrayList<List<StoneDTO>> getStoneListResponse(
			List<OptionStoneDTO> optionStoneList, int branchSeq) {
		StoneDTO stone = new StoneDTO();
		stone.setBranchSeq(branchSeq);
		ArrayList<List<StoneDTO>> stoneListResponse = new ArrayList<List<StoneDTO>>(optionStoneList.size());
		for(int i = 0; i < optionStoneList.size(); i++){
			int stoneSizeSeq = stoneService.getStoneSizeSeq(optionStoneList.get(i).getStoneSeq());
			stone.setStoneSizeSeq(stoneSizeSeq);
			List<StoneDTO> list = stoneService.getStoneListByStoneSizeSeq(stone);
			stoneListResponse.add(i, list);
		}
		return stoneListResponse;
	}

	public ArrayList<List<PartDTO>> getPartListResponse(
			List<OptionPartDTO> optionPartList, int branchSeq) {
		ArrayList<List<PartDTO>> partListResponse = new ArrayList<List<PartDTO>>(optionPartList.size());
		PartDTO part = new PartDTO();
		part.setBranchSeq(branchSeq);
		for(int i = 0; i < optionPartList.size(); i++){
			int partAttributeSeq = partService.getPartAttributeSeq(optionPartList.get(i).getPartSeq());
			part.setPartAttributeSeq(partAttributeSeq);
			List<PartDTO> list = partService.getPartListByPartAttributeSeq(part);
			partListResponse.add(i, list);
		}
		return partListResponse;
	}

	public List<Integer> getOptionCzSizeSeqList(List<OptionCzDTO> optionCzList) {
		List<Integer> optionCzSizeSeqList = new ArrayList<Integer>();
		
		for(OptionCzDTO data : optionCzList){
			optionCzSizeSeqList.add(czService.getCzSizeSeq(data.getCzSeq()));
		}
		return optionCzSizeSeqList;
	}

	public List<Integer> getOptionStoneSizeSeqList(
			List<OptionStoneDTO> optionStoneList) {
		List<Integer> optionStoneSizeSeqList = new ArrayList<Integer>();
		
		for(OptionStoneDTO data : optionStoneList){
			optionStoneSizeSeqList.add(stoneService.getStoneSizeSeq(data.getStoneSeq()));
		}

		return optionStoneSizeSeqList;
	}

	public List<Integer> getOptionPartAttributeSeq(
			List<OptionPartDTO> optionPartList) {
		List<Integer> optionPartAttributeSeqList = new ArrayList<Integer>();
		
		for(OptionPartDTO data : optionPartList){
			optionPartAttributeSeqList.add(partService.getPartAttributeSeq(data.getPartSeq()));
		}
		return optionPartAttributeSeqList;
	}

	public int getStoreProductSeq(int optionSeq) {
		return optionDao.getStoreProductSeq(optionSeq);
	}

	public String getOptionName(int optionSeq) {
		return optionDao.getOptionName(optionSeq);
	}

	public void deleteOptionDetails(int optionSeq) {
		optionDao.deleteOptionCz(optionSeq);
		optionDao.deleteOptionStone(optionSeq);
		optionDao.deleteOptionPart(optionSeq);
		optionDao.deleteOptionAdd(optionSeq);
	}

	public OptionDTO getOption(int optionSeq) {
		return optionDao.getOption(optionSeq);
	}

	public void updateOptionNameByOptionSeq(OptionDTO updateOptionDto) {
		optionDao.updateOptionNameByOptionSeq(updateOptionDto);
	}

	public void deleteOption(int optionSeq) {
		optionDao.deleteOption(optionSeq);
	}

	public void updateOptionCz(OptionCzDTO optionCz) {
		optionDao.updateOptionCz(optionCz);
	}
}