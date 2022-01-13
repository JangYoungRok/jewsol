package com.jewsol.store.customer.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.common.util.UtilSession;
import com.jewsol.store.customer.bean.CustomerBalanceDTO;
import com.jewsol.store.customer.bean.CustomerDTO;
import com.jewsol.store.customer.bean.CustomerGodoInfoDTO;
import com.jewsol.store.customer.bean.CustomerTypeDTO;
import com.jewsol.store.customer.dao.CustomerDAO;
import com.jewsol.store.stockLocation.bean.StockLocationDTO;
import com.jewsol.store.stockLocation.bean.StockLocationTypeDTO;
import com.jewsol.store.stockLocation.dao.StockLocationDAO;


@Service
public class CustomerService {
	@Autowired
	private StockLocationDAO stockLocationDao;
	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private UtilSession utilSession;
	

	public void initAdminCustomerFrom(ModelMap model, int branchSeq) {
		int stockLocationTypeSeq = 1;
		
		List<StockLocationTypeDTO> stockLocationTypeList = stockLocationDao.getStockLocationTypeList();
		List<StockLocationDTO> stockLocationList = stockLocationDao.getStockLocationListByStockLocationTypeSeq(stockLocationTypeSeq, branchSeq);
		List<CustomerDTO> customerList = customerDao.getCustomerList(branchSeq);
		
		model.put("stockLocationTypeList", stockLocationTypeList);
		model.put("stockLocationList", stockLocationList);
		model.put("customerList", customerList);
		
	}
	
	//한글테스트
	public int checkOverLapCustomerName(HttpServletRequest request, int branchSeq) {
	
		String customerName = request.getParameter("customerName");
		String customerArea = request.getParameter("customerArea");
		String customerSection = request.getParameter("customerSection");
		CustomerDTO customer = new CustomerDTO();
		customer.setBranchSeq(branchSeq);
		customer.setCustomerName(customerName);
		customer.setCustomerArea(customerArea);
		customer.setCustomerSection(customerSection);
		
		return customerDao.checkOverLapCustomerName(customer);
	}

	public void insertCustomer(HttpServletRequest request, HttpSession session) {
		CustomerDTO customerDto = new CustomerDTO();
		
		int branchSeq  = utilSession.getBranchSeq(session);
		
		customerDto.setBranchSeq(branchSeq);
		customerDto.setCustomerSection(request.getParameter("customerSection"));
		customerDto.setCustomerArea(request.getParameter("customerArea"));
		customerDto.setCustomerName(request.getParameter("customerName"));
		customerDto.setStockLocationType(request.getParameter("stockLocationType"));
		customerDto.setStockLocationSeq(Integer.parseInt(request.getParameter("stockLocationSeq")));
		
		customerDao.insertCustomer(customerDto);
		
	}

	public List<CustomerDTO> getCustomerList(int branchSeq) {
		
		return customerDao.getCustomerList(branchSeq);
	}

	public void notInUseCustomer(int customerSeq) {
		customerDao.notInUseCustomer(customerSeq);
		
	}

	public List<CustomerDTO> getCustomerKeyWord(String keyword, int branchSeq) {
	
		return customerDao.getCustomerKeyWord(keyword, branchSeq);
	}

	public CustomerDTO getCustomer(int customerSeq) {
		return customerDao.getCustomer(customerSeq);
	}

	public CustomerBalanceDTO getLastCustomerBalance(int customerSeq) {
		return customerDao.getLastCustomerBalance(customerSeq);
	}


	public void insertCustomerBalance(CustomerBalanceDTO customerBalance) {
		customerDao.insertCustomerBalance(customerBalance);
		
	}

	public int getSaleTypeSeq(int customerSeq) {
		return customerDao.getSaleTypeSeq(customerSeq);
	}

	public int countCustomerGodoInfo(CustomerGodoInfoDTO customerGodoInfoDto) {
		
		return customerDao.countCustomerGodoInfo(customerGodoInfoDto);
	}

	public List<CustomerTypeDTO> getCustomerTypeList(int branchSeq) {
		
		return customerDao.getCustomerTypeList(branchSeq);
	}

	public List<CustomerDTO> getCustomerListByCustomerType(CustomerTypeDTO customerType) {
		
		return customerDao.getCustomerListByCustomerType(customerType);
	}
}
