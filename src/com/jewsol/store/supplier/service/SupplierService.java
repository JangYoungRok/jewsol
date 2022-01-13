package com.jewsol.store.supplier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.store.supplier.bean.SupplierDTO;
import com.jewsol.store.supplier.dao.SupplierDAO;


@Service
public class SupplierService {
	
	@Autowired
	SupplierDAO supplierDao;

	public List<SupplierDTO> getSupplierList() {
		return supplierDao.getSupplierList();
	}

	public String getSupplierName(int storeProductSeq) {
		
		return supplierDao.getSupplierName(storeProductSeq);
	}

}
