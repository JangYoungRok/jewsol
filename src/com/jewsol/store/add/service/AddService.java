package com.jewsol.store.add.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.store.add.bean.AddDTO;
import com.jewsol.store.add.dao.AddDAO;


@Service
public class AddService {
	@Autowired
	private AddDAO addDao;

	public void initAdminAddFrom(ModelMap model, int branchSeq) {
		List<AddDTO> addList = addDao.getAddList(branchSeq);
		
		model.put("addList", addList);
		
	}

	public void insertAdd(HttpServletRequest request, int branchSeq) {
		AddDTO addDto = new AddDTO();
		//parameter 처리
		addDto.setBranchSeq(branchSeq);
		addDto.setAddName(request.getParameter("addName"));
		addDto.setAddLabor(Integer.parseInt(request.getParameter("addLabor")));
		addDto.setAddCost(Integer.parseInt(request.getParameter("addCost")));
		addDto.setAddPrice(Integer.parseInt(request.getParameter("addPrice")));
		
		addDao.insertAdd(addDto);
		
	}

	public List<AddDTO> getAddList(int branchSeq) {
		
		return addDao.getAddList(branchSeq);
	}

	public void notInUseAdd(int addSeq) {
		
	}

	public AddDTO getAdd(int addSeq) {
		return addDao.getAdd(addSeq);
	}

}
