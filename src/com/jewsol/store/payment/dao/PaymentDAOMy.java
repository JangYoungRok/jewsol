package com.jewsol.store.payment.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jewsol.store.payment.bean.PaymentDTO;

@Repository
public class PaymentDAOMy implements PaymentDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<PaymentDTO> getPaymentList(int customerSeq) {
		return sqlSession.selectList("store.payment.getPaymentList", customerSeq);
	}

	@Override
	public int insertPayment(PaymentDTO payment) {
		return sqlSession.insert("store.payment.insertPayment", payment);
	}

	@Override
	public PaymentDTO getPayment(int paymentSeq) {
		return sqlSession.selectOne("store.payment.getPayment", paymentSeq);
	}

	@Override
	public PaymentDTO getLastPayment(int customerSeq) {
		return sqlSession.selectOne("store.payment.getLastPayment", customerSeq);
	}
	

}
