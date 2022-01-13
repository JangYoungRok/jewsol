package com.jewsol.core.plate.dao;

import java.util.List;

import com.jewsol.core.plate.bean.PlateDTO;

public interface PlateDAO {

	List<PlateDTO> getPlateList();

	int getPlatePrice(int plateSeq);

}
