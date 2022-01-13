window.onload = initAdminStockLocationForm;

function initAdminStockLocationForm(){
	document.getElementById("insertBtn").onclick = insertStockLocationAjax;
	document.getElementById("notInUseBtn").onclick = notInUseStockLocationAjax;
	
	//basic공용 함수
	document.getElementById("unselectBtn").onclick = unselectElem;
	initListClickEvent();
	initListMouseenter();
	
}

function insertStockLocationAjax(){
	
	var validateResult = validateStockLocation();
	
	if(validateResult){
		checkOverLapStockLocationNameAjax();
	}
}

function checkOverLapStockLocationNameAjax(){
	
	var params = {};
	params.stockLocationName = $("#stockLocationName").val();
	
	sendRequest("/jewsol/store/stockLocation/checkOverLapStockLocationNameAjax.do", params, resultOverLapStockLocationNameAjaxCallBack , "GET");
}

 function resultOverLapStockLocationNameAjaxCallBack(){
	if(request.readyState == 4 && request.status == 200){
		var count = request.responseText;
		if(count > 0){
			setSystemMessage("중복된 제고 위치 이름 입니다.");
			return false;
		}else{
			var params = getInsertStockLocationParameter();
			
			sendRequest("/jewsol/store/stockLocation/insertStockLocationAjax.do", params, getStockLocationListAjaxCallBack, "POST");
		}
	}
}

function getStockLocationListAjaxCallBack(){
	if(request.readyState == 4){
		if(request.status == 200){
			var list = request.responseText;
			refreshList(list);
		}
	}
	
}

function getInsertStockLocationParameter(){
	var params = {};
	
	params.stockLocationName = $("#stockLocationName").val();
	params.stockLocationTypeSeq = $("#stockLocationType").val();
	params.stockLocationType = $("#stockLocationType option:selected").text();
	
	return params;
}

function validateStockLocation(){
	//system 메세지 초기화
	emptySystemMessage();

	if(!validateStringValueNull($("#stockLocationName"))){
		setSystemMessage("제고위치 이름에 값을 입력하세요.");
		return false;
	}else{
		
		return true;
	}
}

function notInUseStockLocationAjax(){
	//버튼 비활성화
	disableTriggerButtons();
	//parameter 값 가져오기
	var params = {};
	
	params.stockLocationSeq = $(".selected").attr("id");
	//ajax post 보내기
	//callback refreshStoneList
	sendRequest("/jewsol/store/stockLocation/notInUseStockLocationAjax.do", params, getStockLocationListAjaxCallBack, "POST");
}
