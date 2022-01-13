package com.jewsol.store.payment.dao;

import java.util.List;

import com.jewsol.store.payment.bean.PaymentDTO;

public interface PaymentDAO {

	List<PaymentDTO> getPaymentList(int customerSeq);

	int insertPayment(PaymentDTO payment);

	PaymentDTO getPayment(int paymentSeq);

	PaymentDTO getLastPayment(int customerSeq);

}
