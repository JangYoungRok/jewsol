package com.jewsol.core.color.dao;

import java.util.List;

import com.jewsol.core.color.bean.ColorDTO;

public interface ColorDAO {

	List<ColorDTO> getColorList(String colorType);

}
