package com.jewsol.orangebox.godoOrder.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewsol.factory.barcode.bean.BarcodeDTO;
import com.jewsol.factory.barcode.service.BarcodeService;
import com.jewsol.orangebox.godoOrder.bean.GodoOrderDTO;
import com.jewsol.orangebox.godoOrder.bean.GodoOrderListDTO;
import com.jewsol.orangebox.godoOrder.bean.GodoOrderNumberDTO;
import com.jewsol.orangebox.godoOrder.dao.GodoOrderDAO;
import com.jewsol.orangebox.godoOrderHistory.service.GodoOrderHistoryService;
import com.jewsol.store.customer.bean.CustomerGodoInfoDTO;
import com.jewsol.store.customer.service.CustomerService;

@Service
public class GodoOrderService {
	
	@Autowired
	private GodoOrderDAO godoOrderDao;
	@Autowired
	private GodoOrderHistoryService godoOrderHistoryService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BarcodeService barcodeService;
	
	public void insertParams(String params) throws ParseException {
		JSONObject jsonObject = null;
		JSONParser jsonParser = new JSONParser();
		
		String keyValueStr = "";
		JSONArray orderTimeArray = null;
		JSONArray orderNumberArray = null;
		JSONArray customerNameArray = null;
		JSONArray customerIDArray = null;
		JSONArray customerPhoneNumberArray = null;
		JSONArray customerAdressArray = null;
		JSONArray productNameArray = null;
		JSONArray productCodeArray = null;
		JSONArray productOptionArray = null;
		JSONArray orderQtyArray  = null;
		
		String godoOrderHistory = "";
		int godoOrderSeq = 0;
		
		jsonObject= (JSONObject)jsonParser.parse(params);
		
		orderTimeArray = (JSONArray)jsonParser.parse(jsonObject.get("orderTimeArray").toString());
		orderNumberArray = (JSONArray)jsonParser.parse(jsonObject.get("orderNumberArray").toString());
		customerNameArray = (JSONArray)jsonParser.parse(jsonObject.get("customerNameArray").toString());
		customerIDArray = (JSONArray)jsonParser.parse(jsonObject.get("customerIDArray").toString());
		customerPhoneNumberArray = (JSONArray)jsonParser.parse(jsonObject.get("customerPhoneNumberArray").toString());
		customerAdressArray = (JSONArray)jsonParser.parse(jsonObject.get("customerAdressArray").toString());
		productNameArray = (JSONArray)jsonParser.parse(jsonObject.get("productNameArray").toString());
		productCodeArray = (JSONArray)jsonParser.parse(jsonObject.get("productCodeArray").toString());
		productOptionArray = (JSONArray)jsonParser.parse(jsonObject.get("productOptionArray").toString());
		orderQtyArray = (JSONArray)jsonParser.parse(jsonObject.get("orderQtyArray").toString());
		
		keyValueStr = (String)jsonObject.get("keyValue");
		
		GodoOrderDTO godoOrderDto = new GodoOrderDTO();
		godoOrderDto.setOrderTimeArray(orderTimeArray.toString());
		godoOrderDto.setOrderNumberArray(orderNumberArray.toString());
		godoOrderDto.setCustomerAdressArray(customerAdressArray.toString());
		godoOrderDto.setCustomerIDArray(customerIDArray.toString());
		godoOrderDto.setCustomerNameArray(customerNameArray.toString());
		godoOrderDto.setCustomerPhoneNumberArray(customerPhoneNumberArray.toString());
		godoOrderDto.setProductCodeArray(productCodeArray.toString());
		godoOrderDto.setProductNameArray(productNameArray.toString());
		godoOrderDto.setProductOptionArray(productOptionArray.toString());
		godoOrderDto.setOrderQtyArray(orderQtyArray.toString());
		godoOrderDto.setKeyValueStr(keyValueStr);
		
		godoOrderSeq = godoOrderDao.insertGodoOrder(godoOrderDto);
		insertGodoOrderList(godoOrderSeq);
		
		godoOrderHistory="params -> godoOrder 변환 입력 완료";
		godoOrderHistoryService.inserGodoOrderHistory(godoOrderSeq, godoOrderHistory);
		
	}

	private void insertGodoOrderList(int godoOrderSeq) throws ParseException {
		String barcodeURL = "https://orange-box.co.kr/_BarcodeGenerator/_Img/";
		String barcodeFileType = ".png";
		String barcodeSrc = "";
		String godoOrderHistory = "";
		GodoOrderDTO godoOrder = godoOrderDao.getGodoOrder(godoOrderSeq);
		JSONParser jsonParser = new JSONParser();
		
		JSONArray orderTimeJSONArray = null;
		JSONArray orderNumberJSONArray = null;
		JSONArray customerNameJSONArray = null;
		JSONArray customerIDJSONArray = null;
		JSONArray customerPhoneNumberJSONArray = null;
		JSONArray customerAdressJSONArray = null;
		JSONArray productNameJSONArray = null;
		JSONArray productCodeJSONArray = null;
		JSONArray productOptionJSONArray = null;
		JSONArray orderQtyJSONArray  = null;
		
		orderTimeJSONArray = (JSONArray)jsonParser.parse(godoOrder.getOrderTimeArray());
		orderNumberJSONArray = (JSONArray)jsonParser.parse(godoOrder.getOrderNumberArray());
		customerNameJSONArray = (JSONArray)jsonParser.parse(godoOrder.getCustomerNameArray());
		customerIDJSONArray = (JSONArray)jsonParser.parse(godoOrder.getOrderTimeArray());
		customerPhoneNumberJSONArray = (JSONArray)jsonParser.parse(godoOrder.getCustomerPhoneNumberArray());
		customerAdressJSONArray = (JSONArray)jsonParser.parse(godoOrder.getCustomerAdressArray());
		productNameJSONArray = (JSONArray)jsonParser.parse(godoOrder.getProductNameArray());
		productCodeJSONArray = (JSONArray)jsonParser.parse(godoOrder.getProductCodeArray());
		productOptionJSONArray = (JSONArray)jsonParser.parse(godoOrder.getProductOptionArray());
		orderQtyJSONArray = (JSONArray)jsonParser.parse(godoOrder.getOrderQtyArray());
		
		GodoOrderListDTO godoOrderListDto = new GodoOrderListDTO();
		
		for(int index = 0;index < orderTimeJSONArray.size(); index++){
			godoOrderListDto.setGodoOrderTime(orderTimeJSONArray.get(index).toString());
			godoOrderListDto.setGodoOrderNumber(orderNumberJSONArray.get(index).toString());
			godoOrderListDto.setGodoCustomerName(customerNameJSONArray.get(index).toString());
			godoOrderListDto.setGodoCustomerID(customerIDJSONArray.get(index).toString());
			godoOrderListDto.setGodoCustomerPhone(customerPhoneNumberJSONArray.get(index).toString());
			godoOrderListDto.setGodoCustomerAdress(customerAdressJSONArray.get(index).toString());
			godoOrderListDto.setGodoProductName(productNameJSONArray.get(index).toString());
			godoOrderListDto.setGodoProductCode(productCodeJSONArray.get(index).toString());
			godoOrderListDto.setGodoProductOption(productOptionJSONArray.get(index).toString());
			godoOrderListDto.setGodoOrderQty(orderQtyJSONArray.get(index).toString());
			barcodeSrc = barcodeURL + orderNumberJSONArray.get(index).toString() + barcodeFileType;
			godoOrderListDto.setBarcodeSrc(barcodeSrc);
			godoOrderListDto.setGodoOrderSeq(godoOrderSeq);
	
			godoOrderDao.insertGodoOrderList(godoOrderListDto);
		}
		godoOrderHistory = "godoOrderList 입력 완료.";
		godoOrderHistoryService.inserGodoOrderHistory(godoOrderSeq, godoOrderHistory);
		
		convertOrder(godoOrderSeq);
		
	}

	private void convertOrder(int godoOrderSeq) {
		String customerName = "";
		String customerArea = "";
		String customerSection = "";
		String godoCustomerPhone ="";
		String godoCustomerName = "";
		String godoOrderNumber = "";
		int godoCustomerLength = 0;
		int customerSeq = 0;
		int barcodeSeq = 0;
		
		List<GodoOrderListDTO> godoOrderListArrayList = godoOrderDao.getGodoOrderList(godoOrderSeq);
		for(GodoOrderListDTO data : godoOrderListArrayList){
			CustomerGodoInfoDTO customerGodoInfoDto = new CustomerGodoInfoDTO();
			godoCustomerPhone = data.getGodoCustomerPhone();
			godoCustomerName = data.getGodoCustomerName();
			//godoCustomerLength = godoCustomerPhone.length();
			
			//customerName = data.getGodoCustomerName();
			//customerArea = godoCustomerPhone.substring(godoCustomerLength-9, godoCustomerLength-6) + godoCustomerPhone.substring(godoCustomerLength-4, godoCustomerLength);
			customerGodoInfoDto.setGodoCustomerName(godoCustomerName);
			customerGodoInfoDto.setGodoCustomerPhone(godoCustomerPhone);
			
			int godoCustomerCount = customerService.countCustomerGodoInfo(customerGodoInfoDto);
			if(godoCustomerCount > 0){
				//customerSeq = customerService.getCustomerSeqByGodoInfo(customerGodoInfoDto);
			}else{
				//customerSeq = customerService.insertCustomerByGodoInfo(customerGodoInfoDto);
			}
			godoOrderNumber = data.getGodoOrderNumber();
			GodoOrderNumberDTO godoOrderNumberDto = new GodoOrderNumberDTO();
			godoOrderNumberDto.setCustomerSeq(customerSeq);
			godoOrderNumberDto.setGodoOrderNumber(godoOrderNumber);
			//insertGodoOderNumber(godoOrderNumberDto);
			
			BarcodeDTO barcodeDto = new BarcodeDTO();
			barcodeDto.setBarcode(godoOrderNumber);
			barcodeDto.setBarcodeImage(data.getBarcodeSrc());

			//barcodeSeq = barcodeService.insertBarcode(barcodeDto);
			
		}
		
		
	}

}
