package com.jewsol.store.customer.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.customer.bean.CustomerBalanceDTO;
import com.jewsol.store.customer.bean.CustomerDTO;
import com.jewsol.store.customer.bean.CustomerGodoInfoDTO;
import com.jewsol.store.customer.bean.CustomerKeywordDTO;
import com.jewsol.store.customer.bean.CustomerTypeDTO;


@Repository
public class CustomerDAOMy implements CustomerDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<CustomerDTO> getCustomerList(int branchSeq) {
		
		return sqlSession.selectList("store.customer.getCustomerList", branchSeq);
	}

	@Override
	public int checkOverLapCustomerName(CustomerDTO customer) {
		
		return sqlSession.selectOne("store.customer.checkOverLapCustomerName", customer);
	}

	@Override
	public void insertCustomer(CustomerDTO customerDto) {
		sqlSession.insert("store.customer.insertCustomer", customerDto);
		
	}

	@Override
	public void notInUseCustomer(int customerSeq) {
		sqlSession.update("store.customer.notInUseCustomer", customerSeq);
		
	}

	@Override
	public List<CustomerDTO> getCustomerKeyWord(String keyword, int branchSeq) {
		CustomerKeywordDTO customerKeyword = new CustomerKeywordDTO();
		customerKeyword.setKeyword(keyword);
		customerKeyword.setBranchSeq(branchSeq);
		return sqlSession.selectList("store.customer.getCustomerKeyWord", customerKeyword);
	}

	@Override
	public CustomerDTO getCustomer(int customerSeq) {
		return sqlSession.selectOne("store.customer.getCustomer", customerSeq);
	}

	@Override
	public CustomerBalanceDTO getLastCustomerBalance(int customerSeq) {
		return sqlSession.selectOne("store.customer.getLastCustomerBalance", customerSeq);
	}

	@Override
	public void insertCustomerBalance(CustomerBalanceDTO customerBalance) {
		sqlSession.insert("store.customer.insertCustomerBalance", customerBalance);
		
	}

	@Override
	public int getSaleTypeSeq(int customerSeq) {
		return sqlSession.selectOne("store.customer.getSaleTypeSeq", customerSeq);
	}

	@Override
	public int countCustomerGodoInfo(CustomerGodoInfoDTO customerGodoInfoDto) {
		
		return sqlSession.selectOne("store.customer.countCustomerGodoInfo", customerGodoInfoDto);
	}

	@Override
	public List<CustomerTypeDTO> getCustomerTypeList(int branchSeq) {
		
		return sqlSession.selectList("store.customer.getCustomerTypeList", branchSeq);
	}

	@Override
	public List<CustomerDTO> getCustomerListByCustomerType(
			CustomerTypeDTO customerType) {
		
		return sqlSession.selectList("store.customer.getCustomerListByCustomerType", customerType);
	}

}
