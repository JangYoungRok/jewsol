package com.jewsol.orangebox.godoOrder.dao;

import java.util.List;

import com.jewsol.orangebox.godoOrder.bean.GodoOrderDTO;
import com.jewsol.orangebox.godoOrder.bean.GodoOrderListDTO;

public interface GodoOrderDAO {

	int insertGodoOrder(GodoOrderDTO godoOrderDto);

	GodoOrderDTO getGodoOrder(int godoOrderSeq);

	void insertGodoOrderList(GodoOrderListDTO godoOrderListDto);

	List<GodoOrderListDTO> getGodoOrderList(int godoOrderSeq);

}
