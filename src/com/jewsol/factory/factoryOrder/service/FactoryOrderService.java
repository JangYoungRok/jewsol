package com.jewsol.factory.factoryOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.jewsol.factory.factoryOrder.bean.InqueryFactoryOrderDTO;
import com.jewsol.factory.factoryOrder.dao.FactoryOrderDAO;
import com.jewsol.factory.panel.bean.PanelDTO;
import com.jewsol.factory.panel.service.PanelService;

@Service
public class FactoryOrderService {
	@Autowired
	private FactoryOrderDAO factoryOrderDao;
	@Autowired
	private PanelService panelService;
	private int panelNumber = 0;
	private int orderNumber = 0;
	private int panelSize = 15;
	
	public int checkOrderDate(String today) {
		return factoryOrderDao.checkOrderDate(today);
	}

	public void insertOrderDate(String today) {
		factoryOrderDao.insertOrderDate(today);
		
	}

	public String getOrderClose(String today) {
		return factoryOrderDao.getOrderClose(today);
	}

	public void closeOrder(String orderDate) {
		List<Integer> orderSeq18RingList = factoryOrderDao.getOrderSeqRingList("18", orderDate);
		List<Integer> orderSeq14RingList = factoryOrderDao.getOrderSeqRingList("14", orderDate);
		
		List<Integer> orderSeq18PendentList = factoryOrderDao.getOrderSeqPendentList("18", orderDate);
		List<Integer> orderSeq14PendentList = factoryOrderDao.getOrderSeqPendentList("14", orderDate);
		
		List<Integer> orderSeq18NecklaceList = factoryOrderDao.getOrderSeqNecklaceList("18", orderDate);
		List<Integer> orderSeq14NecklaceList = factoryOrderDao.getOrderSeqNecklaceList("14", orderDate);
		
		List<Integer> orderSeq18HurryList = factoryOrderDao.getOrderSeqHurryList("18", orderDate);
		List<Integer> orderSeq14HurryList = factoryOrderDao.getOrderSeqHurryList("14", orderDate);
		
		List<Integer> orderSeq10SvList = factoryOrderDao.getOrderSeq10SvList(orderDate);
		List<Integer> orderSeqAnoList = factoryOrderDao.getOrderSeqAnoList(orderDate);
		
		panelCategorize(orderSeq14RingList, "R14", orderDate);
		panelCategorize(orderSeq14PendentList, "P14", orderDate);
		panelCategorize(orderSeq14NecklaceList, "N14", orderDate);
		panelCategorize(orderSeq14HurryList, "H14", orderDate);
		
		panelCategorize(orderSeq18RingList, "R18", orderDate);
		panelCategorize(orderSeq18PendentList, "P18", orderDate);
		panelCategorize(orderSeq18NecklaceList, "N18", orderDate);
		panelCategorize(orderSeq18HurryList, "H18", orderDate);
		
		panelCategorize(orderSeq10SvList, "10SV", orderDate);
		panelCategorize(orderSeqAnoList, "ANO", orderDate);
		
		factoryOrderDao.closeOrderDate(orderDate);
		initFactoryVar();
	}

	private void initFactoryVar() {
		this.panelNumber = 0;
		this.orderNumber = 0;
		this.panelSize = 15;
	}

	private void panelCategorize(List<Integer> orderSeqList, String panelGroup, String orderDate) {
		int listSize = orderSeqList.size();
		int totalPanel = (listSize + panelSize - 1) / panelSize;
		int panelSeq = 0;
		int orderSeq = 0;
		if(listSize > 0){
			for(int i = 0; i < totalPanel; i++){
				panelNumber = panelNumber + 1;
				PanelDTO panel = new PanelDTO();
				panel.setPanelDate(orderDate);
				panel.setPanelGroup(panelGroup);
				panel.setPanelNumber(panelNumber);
				panelSeq = panelService.insertPanel(panel);
				for(int j = 0; j < panelSize; j++){
					int index = i * panelSize + j;
					if(listSize ==  index){
						break;
					}
					orderSeq = orderSeqList.get(index);
					orderNumber = orderNumber + 1;
					factoryOrderDao.updateOrderPanelSeq(orderSeq, panelSeq, orderNumber);
				}
			}
		}
	}

	public List<InqueryFactoryOrderDTO> getFactoryInqueryOrder(String productCode) {
		return factoryOrderDao.getFactoryInqueryOrder(productCode);
	}
	
}
