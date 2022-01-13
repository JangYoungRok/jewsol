package com.jewsol.store.option.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.option.bean.OptionAddDTO;
import com.jewsol.store.option.bean.OptionCzDTO;
import com.jewsol.store.option.bean.OptionDTO;
import com.jewsol.store.option.bean.OptionPartDTO;
import com.jewsol.store.option.bean.OptionStoneDTO;

@Repository
public class OptionDAOMy implements OptionDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertOption(OptionDTO optionDto) {
		sqlSession.insert("store.option.insertOption", optionDto);
		return optionDto.getOptionSeq();
	}

	@Override
	public void insertOptionCz(OptionCzDTO optionCzDto) {
		sqlSession.insert("store.option.insertOptionCz", optionCzDto);
	}

	@Override
	public void insertOptionStone(OptionStoneDTO optionStoneDto) {
		sqlSession.insert("store.option.insertOptionStone", optionStoneDto);
		
	}

	@Override
	public void insertOptionPart(OptionPartDTO optionPartDto) {
		sqlSession.insert("store.option.insertOptionPart", optionPartDto);
		
	}

	@Override
	public void insertOptionAdd(OptionAddDTO optionAddDto) {
		sqlSession.insert("store.option.insertOptionAdd", optionAddDto);
		
	}

	@Override
	public void initOptionLabor(int optionSeq) {
		sqlSession.update("store.option.initOptionLabor", optionSeq);
		
	}

	@Override
	public void initOptionPrice(int optionSeq) {
		sqlSession.update("store.option.initOptionPrice", optionSeq);
		
	}

	@Override
	public String getOptionName(int storeProductSeq, int optionNumber) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("storeProductSeq", storeProductSeq);
		map.put("optionNumber", optionNumber);
		
		return sqlSession.selectOne("store.option.getOptionName", map);
	}

	@Override
	public void updateOptionName(OptionDTO optionDto) {
		sqlSession.update("store.option.updateOptionName", optionDto);
		
	}

	@Override
	public OptionDTO getOption(int storeProductSeq, int optionNumber) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("storeProductSeq", storeProductSeq);
		map.put("optionNumber", optionNumber);
		
		return sqlSession.selectOne("store.option.getOption", map);
	}

	@Override
	public int getOptionSeq(int storeProductSeq, int optionNumber) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("storeProductSeq",storeProductSeq);
		map.put("optionNumber", optionNumber);
		
		return sqlSession.selectOne("store.option.getOptionSeq", map);
	}

	@Override
	public List<OptionCzDTO> getOptionCzList(int optionSeq) {
		
		return sqlSession.selectList("store.option.getOptionCzList", optionSeq);
	}
	
	@Override
	public List<OptionStoneDTO> getOptionStoneList(int optionSeq) {
		
		return sqlSession.selectList("store.option.getOptionStoneList", optionSeq);
	}
	
	@Override
	public List<OptionPartDTO> getOptionPartList(int optionSeq) {
		
		return sqlSession.selectList("store.option.getOptionPartList", optionSeq);
	}
	
	@Override
	public List<OptionAddDTO> getOptionAddList(int optionSeq) {
		
		return sqlSession.selectList("store.option.getOptionAddList", optionSeq);
	}

	@Override
	public List<OptionDTO> getOptionList(int storeProductSeq) {
		return sqlSession.selectList("store.option.getOptionList", storeProductSeq);
	}

	@Override
	public int getStoreProductSeq(int optionSeq) {
		return sqlSession.selectOne("store.option.getStoreProductSeq", optionSeq);
	}

	@Override
	public String getOptionName(int optionSeq) {
		return sqlSession.selectOne("store.option.getOptionNameByOptionSeq", optionSeq);
	}

	@Override
	public void deleteOptionCz(int optionSeq) {
		sqlSession.delete("store.option.deleteOptionCz", optionSeq);
		
	}

	@Override
	public void deleteOptionStone(int optionSeq) {
		sqlSession.delete("store.option.deleteOptionStone", optionSeq);
		
	}

	@Override
	public void deleteOptionPart(int optionSeq) {
		sqlSession.delete("store.option.deleteOptionPart", optionSeq);
		
	}

	@Override
	public void deleteOptionAdd(int optionSeq) {
		sqlSession.delete("store.option.deleteOptionAdd", optionSeq);
		
	}

	@Override
	public OptionDTO getOption(int optionSeq) {
		return sqlSession.selectOne("store.option.getOptionByOptionSeq", optionSeq);
	}

	@Override
	public void updateOptionNameByOptionSeq(OptionDTO updateOptionDto) {
		sqlSession.update("store.option.updateOptionNameByOptionSeq", updateOptionDto);
		
	}

	@Override
	public void deleteOption(int optionSeq) {
		sqlSession.delete("store.option.deleteOption", optionSeq);
		
	}

	@Override
	public void updateOptionCz(OptionCzDTO optionCz) {
		sqlSession.update("store.option.updateOptionCz", optionCz);
		
	}

}
