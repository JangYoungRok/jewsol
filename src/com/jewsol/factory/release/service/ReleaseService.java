package com.jewsol.factory.release.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.factory.release.bean.InsertReleaseDTO;
import com.jewsol.factory.release.bean.ViewOrderForReleaseDTO;
import com.jewsol.factory.release.dao.ReleaseDAO;

@Service
public class ReleaseService {
	@Autowired
	private ReleaseDAO releaseDao;

	public void checkRelease(String[] orderSeqArr) {
		for(String orderSeq : orderSeqArr){
			releaseDao.checkRelease(Integer.parseInt(orderSeq));
		}
	}

	public List<ViewOrderForReleaseDTO> getOrderListByBranchNameState3(
			String branchName) {
		return releaseDao.getOrderListByBranchNameState3(branchName);
	}

	public void insertRelease(String orderSeqArr, String orderWeightArr) {
		String[] orderSeqArray = orderSeqArr.split(",");
		String[] orderWeightArray = orderWeightArr.split(",");
		int orderSeq = 0;
		Double orderWeight = 0.0;
		
		for(int i = 0; i < orderSeqArray.length; i++){
			orderSeq = Integer.parseInt(orderSeqArray[i]);
			orderWeight = Double.parseDouble(orderWeightArray[i]);
			releaseDao.updateOrderState4(orderSeq);
			InsertReleaseDTO releaseDto = new InsertReleaseDTO();
			releaseDto.setOrderSeq(orderSeq);
			releaseDto.setOrderWeight(orderWeight);
			releaseDao.updateOrderWeight(releaseDto);
		}
	}

	public List<ViewOrderForReleaseDTO> getOrderListState3() {
		return releaseDao.getOrderListState3();
	}

	public void cancleRelease(String orderSeqArr) {
		String[] orderSeqArray = orderSeqArr.split(",");
		int orderSeq = 0;
		for(int i = 0; i < orderSeqArray.length; i++){
			orderSeq = Integer.parseInt(orderSeqArray[i]);
			releaseDao.updateOrderState2(orderSeq);
		}
	}

}
