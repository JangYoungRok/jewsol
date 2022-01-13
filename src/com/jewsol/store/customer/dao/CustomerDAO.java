package com.jewsol.store.customer.dao;

import java.util.List;

import com.jewsol.store.customer.bean.CustomerBalanceDTO;
import com.jewsol.store.customer.bean.CustomerDTO;
import com.jewsol.store.customer.bean.CustomerGodoInfoDTO;
import com.jewsol.store.customer.bean.CustomerTypeDTO;

public interface CustomerDAO {

	List<CustomerDTO> getCustomerList(int branchSeq);

	int checkOverLapCustomerName(CustomerDTO customer);

	void insertCustomer(CustomerDTO customerDto);

	void notInUseCustomer(int customerSeq);

	List<CustomerDTO> getCustomerKeyWord(String keyword, int branchSeq);

	CustomerDTO getCustomer(int customerSeq);

	CustomerBalanceDTO getLastCustomerBalance(int customerSeq);

	void insertCustomerBalance(CustomerBalanceDTO customerBalance);

	int getSaleTypeSeq(int customerSeq);

	int countCustomerGodoInfo(CustomerGodoInfoDTO customerGodoInfoDto);

	List<CustomerTypeDTO> getCustomerTypeList(int branchSeq);

	List<CustomerDTO> getCustomerListByCustomerType(CustomerTypeDTO customerType);

}
