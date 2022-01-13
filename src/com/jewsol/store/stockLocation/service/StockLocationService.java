package com.jewsol.store.stockLocation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.core.login.dao.LoginDAO;
import com.jewsol.store.stockLocation.bean.StockLocationDTO;
import com.jewsol.store.stockLocation.bean.StockLocationTypeDTO;
import com.jewsol.store.stockLocation.dao.StockLocationDAO;


@Service
public class StockLocationService {
	@Autowired
	private StockLocationDAO stockLocationDao;
	@Autowired
	private LoginDAO loginDao;

	public void initStockLocationFrom(ModelMap model, int branchSeq) {

		List<StockLocationDTO> stockLocationList = stockLocationDao.getStockLocationList(branchSeq);
		List<StockLocationTypeDTO> stockLocationTypeList = stockLocationDao.getStockLocationTypeList();
		
		model.put("stockLocationList", stockLocationList);
		model.put("stockLocationTypeList", stockLocationTypeList);
		
	}

	public void insertStockLocation(HttpServletRequest request, HttpSession session) {
		StockLocationDTO stockLocationDto = new StockLocationDTO();
		//parameter 처리
		String loginId = (String) session.getAttribute("sLoginId");
		int branchSeq  = loginDao.getBranchSeq(loginId);
		
		stockLocationDto.setBranchSeq(branchSeq);
		stockLocationDto.setStockLocationName(request.getParameter("stockLocationName"));
		stockLocationDto.setStockLocationType(request.getParameter("stockLocationType"));
		stockLocationDto.setStockLocationTypeSeq(Integer.parseInt(request.getParameter("stockLocationTypeSeq")));
		
		stockLocationDao.insertStockLocation(stockLocationDto);
		
	}

	public List<StockLocationDTO> getStockLocationList(int branchSeq) {
		
		return stockLocationDao.getStockLocationList(branchSeq);
	}

	public void notInUseStockLocation(int stockLocationSeq) {
		stockLocationDao.notInUseStockLocation(stockLocationSeq);
		
	}

	public int checkOverLapStockLocationName(StockLocationDTO stockLocation) {
		return stockLocationDao.checkOverLapStockLocationName(stockLocation);
	}

	public List<StockLocationDTO> getStockLocationListByStockLocationType(
			int stockLocationTypeSeq, int branchSeq) {
		
		return stockLocationDao.getStockLocationListByStockLocationTypeSeq(stockLocationTypeSeq, branchSeq);
	}

}
