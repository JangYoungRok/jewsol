package com.jewsol.store.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.store.customer.bean.CustomerBalanceDTO;
import com.jewsol.store.customer.service.CustomerService;
import com.jewsol.store.payment.bean.PaymentDTO;
import com.jewsol.store.payment.dao.PaymentDAO;
import com.jewsol.store.saleSheet.bean.SaleSheetDTO;
import com.jewsol.store.saleSheet.service.SaleSheetService;

@Service
public class PaymentService {
	@Autowired
	private PaymentDAO paymentDao;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SaleSheetService saleSheetService;

	public List<PaymentDTO> getPaymentList(int customerSeq) {
		return paymentDao.getPaymentList(customerSeq);
	}

	public int insertPayment(PaymentDTO payment) {
		
		return paymentDao.insertPayment(payment);
	}

	public PaymentDTO getPayment(int paymentSeq) {
		return paymentDao.getPayment(paymentSeq);
	}

	public PaymentDTO getLastPayment(int customerSeq) {
		return paymentDao.getLastPayment(customerSeq);
	}

	public SaleSheetDTO getLastSaleDateBeforePayment(int customerSeq) {
		
		
		SaleSheetDTO lastSaleSheetBeforePayment = new SaleSheetDTO();
		CustomerBalanceDTO lastCustomerBalance = customerService.getLastCustomerBalance(customerSeq);
		List<SaleSheetDTO> saleSheetList = saleSheetService.getSaleSheetList(customerSeq);
		Double sumSaleGold = 0.0;
		int sumSalePrice = 0;
		Double lastCustomerBalanceGold = 0.0;
		int lastCustomerBalanceCash = 0;
		int index = 0;
		int selectedIndex = 0;
		
		if(lastCustomerBalance == null || saleSheetList == null || saleSheetList.size() < 0){
			
		}else{
			lastCustomerBalanceGold = lastCustomerBalance.getCustomerBalanceGold();
			lastCustomerBalanceCash = lastCustomerBalance.getCustomerBalanceCash();
			
			if(lastCustomerBalanceGold > 0 || lastCustomerBalanceCash > 0){
				for(SaleSheetDTO data : saleSheetList){
					
					sumSaleGold = sumSaleGold + data.getTotalGoldWeight();
					sumSalePrice = sumSalePrice + data.getTotalSalePrice();
					sumSaleGold = (double) Math.round(sumSaleGold*100)/100.0;
					
					if((sumSaleGold.equals(lastCustomerBalanceGold) && sumSalePrice == lastCustomerBalanceCash)){
						selectedIndex = index;
						lastSaleSheetBeforePayment = saleSheetList.get(selectedIndex);
						break;
					}else if(sumSaleGold > lastCustomerBalanceGold && sumSalePrice > lastCustomerBalanceCash){
						if(index > 0){
							selectedIndex = index - 1;
							lastSaleSheetBeforePayment = saleSheetList.get(selectedIndex);
							break;
						}else{
							lastSaleSheetBeforePayment = saleSheetList.get(selectedIndex);
							break;
						}
					}
					index++;
				}
			}
		}
		
		return lastSaleSheetBeforePayment;
	}

}
