package com.jewsol.store.option.dao;

import java.util.List;

import com.jewsol.store.option.bean.OptionAddDTO;
import com.jewsol.store.option.bean.OptionCzDTO;
import com.jewsol.store.option.bean.OptionDTO;
import com.jewsol.store.option.bean.OptionPartDTO;
import com.jewsol.store.option.bean.OptionStoneDTO;

public interface OptionDAO {
	int insertOption(OptionDTO optionDto);

	void insertOptionCz(OptionCzDTO optionCzDto);

	void insertOptionStone(OptionStoneDTO optionStoneDto);

	void insertOptionPart(OptionPartDTO optionPartDto);

	void insertOptionAdd(OptionAddDTO optionAddDto);

	void initOptionLabor(int optionSeq);
	
	void initOptionPrice(int optionSeq);

	String getOptionName(int storeProductSeq, int optionNumber);

	void updateOptionName(OptionDTO optionDto);

	OptionDTO getOption(int storeProductSeq, int optionNumber);

	int getOptionSeq(int storeProductSeq, int optionNumber);

	List<OptionCzDTO> getOptionCzList(int optionSeq);

	List<OptionStoneDTO> getOptionStoneList(int optionSeq);

	List<OptionPartDTO> getOptionPartList(int optionSeq);

	List<OptionAddDTO> getOptionAddList(int optionSeq);

	List<OptionDTO> getOptionList(int storeProductSeq);

	int getStoreProductSeq(int optionSeq);

	String getOptionName(int optionSeq);

	void deleteOptionCz(int optionSeq);

	void deleteOptionStone(int optionSeq);

	void deleteOptionPart(int optionSeq);

	void deleteOptionAdd(int optionSeq);

	OptionDTO getOption(int optionSeq);

	void updateOptionNameByOptionSeq(OptionDTO updateOptionDto);

	void deleteOption(int optionSeq);

	void updateOptionCz(OptionCzDTO optionCz);
}
