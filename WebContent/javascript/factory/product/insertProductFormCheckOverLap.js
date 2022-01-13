function checkProductName(){
	emptySystemMessage();
	if(this.id == "type"){
		getPartAttributeList(this.value);
	}
	var result = checkProductCode();
	if(result){
		readyOverLapButton();
	}else{
		disableButtons();
	}
}

function checkOverLapProductName(){
	$("#insertProductBtn").attr("disabled",false);
	
	var params = {};
	
	params.categorySeq = getCategorySeq();
	params.productCode = getProductCode();
	params.typeSeq = getTypeSeq();
	
	sendRequest("/jewsol/factory/product/checkOverLapProductName.do", params, resultOverLapProductName, "GET");
	
}

function resultOverLapProductName(){
	if(request.readyState == 4){
		if(request.status == 200){
			var resultOverLap = request.responseText;
			var systemMessage = "";
			if(resultOverLap == 1){
				disableButtons();	
				systemMessage = "이미 등록된 제품 입니다.";
				readyOverLapButton();
			}else if(resultOverLap == 0){
				readyInsertButton();
				systemMessage = "등록 가능한 제품 입니다.";
			}
			setSystemMessage(systemMessage);
		}
	}
}

function disableButtons(){
	$("#checkOverLapBtn").attr("disabled",true);
	$("#insertProductBtn").attr("disabled",true);
}

function readyOverLapButton(){
	$("#checkOverLapBtn").attr("disabled",false);
	$("#insertProductBtn").attr("disabled",true);
}

function readyInsertButton(){
	$("#checkOverLapBtn").attr("disabled",true);
	$("#insertProductBtn").attr("disabled",false);
}

function getPartAttributeList(typeSeq){
	var params = {};
	
	params.typeSeq = typeSeq;
	
	sendRequest("/jewsol/factory/product/getPartAttributeList.do", params, getPartAttributeListResult, "GET");
	
}

function getPartAttributeListResult(){
	if(request.readyState == 4){
		if(request.status == 200){
			for(var i = 1; i <= 9; i++){
				var select = document.getElementById("partAttribute"+i);
				
				var resultHTML = request.responseText;
				
				select.innerHTML = resultHTML;
			}
		}
	}
}