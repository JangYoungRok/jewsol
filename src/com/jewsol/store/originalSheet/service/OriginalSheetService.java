package com.jewsol.store.originalSheet.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.common.util.UtilSession;
import com.jewsol.core.login.service.LoginService;
import com.jewsol.store.originalSheet.bean.OriginalSheetDTO;
import com.jewsol.store.originalSheet.dao.OriginalSheetDAO;

@Service
public class OriginalSheetService {
	@Autowired
	private LoginService loginService;
	@Autowired
	private OriginalSheetDAO originalSheetDao;
	@Autowired
	private UtilSession utilSession;

	public int getOriginalSheetState(int originalSheetNumber, HttpSession session) {
		String loginId = utilSession.getLoginId(session);
		int insertMemberSeq = loginService.getMemberSeq(loginId);
		int branchSeq  = utilSession.getBranchSeq(session);
		int originalSheetState = 0;

		OriginalSheetDTO originalSheetResult = getOriginalSheetResult(branchSeq, originalSheetNumber);
		if(originalSheetResult == null){
			OriginalSheetDTO originalSheetDto = new OriginalSheetDTO();
			originalSheetDto.setInsertMemberSeq(insertMemberSeq);
			originalSheetDto.setOriginalSheetNumber(originalSheetNumber);
			originalSheetDto.setBranchSeq(branchSeq);
			
			originalSheetDao.insertOriginalSheet(originalSheetDto);
			
			originalSheetState = 1;
			
		}else{
			if(insertMemberSeq == originalSheetResult.getInsertMemberSeq()){
				if(originalSheetResult.getOriginalSheetClose().equals("F")){
					originalSheetState = 3;
				}else{
					originalSheetState = 4;
				}
			}else{
				originalSheetState = 2;
			}
		}

		return originalSheetState;
	}

	private OriginalSheetDTO getOriginalSheetResult(int branchSeq,
			int originalSheetNumber) {
		
		return originalSheetDao.getOriginalSheetResult(branchSeq, originalSheetNumber);
	}

	public int getOriginalSheetSeq(String orderDate, int originalSheetNumber, int branchSeq) {
		
		return originalSheetDao.getOriginalSheetSeq(orderDate, originalSheetNumber, branchSeq);
	}

	public void closeOriginalSheet(int originalSheetSeq) {
		originalSheetDao.closeOriginalSheet(originalSheetSeq);
	}

	public List<OriginalSheetDTO> getOriginalSheetList(String today, int branchSeq) {
		OriginalSheetDTO originalSheetDto = new OriginalSheetDTO();
		originalSheetDto.setBranchSeq(branchSeq);
		originalSheetDto.setOriginalSheetDate(today);
		return originalSheetDao.getOriginalSheetList(originalSheetDto);
	}

	public OriginalSheetDTO getOriginalSheet(int originalSheetSeq) {
		return originalSheetDao.getOriginalSheet(originalSheetSeq);
	}

}
