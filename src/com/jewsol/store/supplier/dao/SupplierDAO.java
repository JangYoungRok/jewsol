package com.jewsol.store.supplier.dao;

import java.util.List;

import com.jewsol.store.supplier.bean.SupplierDTO;

public interface SupplierDAO {

	List<SupplierDTO> getSupplierList();

	String getSupplierName(int storeProductSeq);

}
