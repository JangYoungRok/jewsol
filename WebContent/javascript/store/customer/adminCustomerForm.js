window.onload = initAdminCustomerForm;

function initAdminCustomerForm(){
	document.getElementById("insertBtn").onclick = insertCuctomerAjax;
	document.getElementById("notInUseBtn").onclick = notInUseCuctomerAjax;
	document.getElementById("stockLocationType").onchange = getStockLocationListAjax;
	
	//basic공용 함수
	document.getElementById("unselectBtn").onclick = unselectElem;
	initListClickEvent();
	initListMouseenter();
	
}

function notInUseCuctomerAjax(){
	//버튼 비활성화
	disableTriggerButtons();
	//parameter 값 가져오기
	var params = {};
	
	params.customerSeq = $(".selected").attr("id");
	//ajax post 보내기
	//callback refreshStoneList
	sendRequest("/jewsol/store/customer/notInUseCustomerAjax.do", params, getCustomerListAjaxCallBack, "GET");

	
}

function getStockLocationListAjax(){
	var params = {};
	params.stockLocationTypeSeq = $("#stockLocationType").val();
	
	sendRequest("/jewsol/store/stockLocation/getStockLocationOptionListAjax.do", params, resultStockLocationOptionListAjaxCallBack , "GET");

}

function resultStockLocationOptionListAjaxCallBack(){

	if(request.readyState == 4 && request.status == 200){
		var list = request.responseText;
		refreshElem("#stockLocation",list);
	}
}

function insertCuctomerAjax(){
	
	var validateResult = validateCustomer();
	
	if(validateResult){
		checkOverLapCustomerNameAjax();
	}
}

function checkOverLapCustomerNameAjax(){
	var params = {};
	//params.customerName = $("#customerName").val();
	//params.customerSection = $("#customerSection").val();
	//params.customerArea = $("#customerArea").val();
	
	
	params.customerName = document.getElementById("customerName").value;
	params.customerSection = document.getElementById("customerSection").value;
	params.customerArea = document.getElementById("customerArea").value;
	
	sendRequest("/jewsol/store/customer/checkOverLapCustomerNameAjax.do", params, resultOverLapCustomerNameAjaxCallBack , "GET");

	
}

function resultOverLapCustomerNameAjaxCallBack(){
	if(request.readyState == 4 && request.status == 200){
		var count = request.responseText;
		count = $.trim(count);
		if(count > 0){
			setSystemMessage("중복된 거래처 이름 입니다.");
			return false;
		}else{
			var params = getInsertCustomerParameter();
			
			sendRequest("/jewsol/store/customer/insertCustomerAjax.do", params, getCustomerListAjaxCallBack, "POST");
		}
	}
	
}

function getCustomerListAjaxCallBack(){
	if(request.readyState == 4 && request.status == 200){
		var list = request.responseText;
		refreshList(list);
	}
}

function getInsertCustomerParameter(){
	var params = {};
	
	params.customerName = $("#customerName").val();
	params.customerSection = $("#customerSection").val();
	params.customerArea = $("#customerArea").val();
	params.stockLocationType = $("#stockLocationType option:selected").text();
	params.stockLocationSeq = $("#stockLocation option:selected").val( );
	
	return params;
	
}

function validateCustomer(){
	//system 메세지 초기화
	emptySystemMessage();

	if(!validateStringValueNull($("#customerName"))){
		setSystemMessage("거래처 이름에 값을 입력하세요.");
		return false;
	}else{
		
		return true;
	}
}