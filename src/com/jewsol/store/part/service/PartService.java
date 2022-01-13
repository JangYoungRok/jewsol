package com.jewsol.store.part.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jewsol.store.part.bean.PartAttributeDTO;
import com.jewsol.store.part.bean.PartDTO;
import com.jewsol.store.part.dao.PartDAO;
import com.jewsol.factory.product.bean.TypeMainDTO;
import com.jewsol.factory.product.dao.ProductDAO;


@Service
public class PartService {
	@Autowired
	PartDAO partDao;
	@Autowired
	ProductDAO productDao;
	
	public void initAdminPartFrom(ModelMap model, int branchSeq) {
		int typeMainSeq = 1;
		
		List<PartDTO> partList = partDao.getPartList(branchSeq);
		List<TypeMainDTO> typeMainList = productDao.getTypeMainList();
		List<PartAttributeDTO> partAttributeList = partDao.getPartAttributeList(typeMainSeq);
		model.put("partList", partList);
		model.put("typeMainList", typeMainList);
		model.put("partAttributeList", partAttributeList);
	}

	public List<PartAttributeDTO> getPartAttribute(int typeMainSeq) {
		return partDao.getPartAttributeList(typeMainSeq);
	}

	public void insertPart(HttpServletRequest request, int branchSeq) {
		PartDTO partDto = new PartDTO();
		//parameter 처리
		partDto.setBranchSeq(branchSeq);
		partDto.setPartName(request.getParameter("partName"));
		partDto.setPartAttributeSeq(Integer.parseInt(request.getParameter("partAttribute")));
		partDto.setPartLabor(Integer.parseInt(request.getParameter("partLabor")));
		partDto.setPartCost(Integer.parseInt(request.getParameter("partCost")));
		partDto.setPartPrice(Integer.parseInt(request.getParameter("partPrice")));
		
		partDao.insertPart(partDto);
	}

	public List<PartDTO> getPartList(int branchSeq) {
		return partDao.getPartList(branchSeq);
	}

	public void notInUsePart(int partSeq) {
		partDao.notInUsePart(partSeq);
	}

	public List<PartAttributeDTO> getPartAttributeList(int typeMainSeq) {
		return partDao.getPartAttributeList(typeMainSeq);
	}

	public List<PartDTO> getPartListByPartAttributeSeq(PartDTO part) {
		return partDao.getPartListByPartAttributeSeq(part);
	}

	public PartDTO getPart(int partSeq) {
		return partDao.getPart(partSeq);
	}

	public int getPartAttributeSeq(int partSeq) {
		return partDao.getPartAttributeSeq(partSeq);
	}
}
