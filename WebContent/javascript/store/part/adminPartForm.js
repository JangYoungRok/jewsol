window.onload = initAdminPartForm;

function initAdminPartForm(){
	document.getElementById("insertBtn").onclick = insertPartAjax;
	document.getElementById("notInUseBtn").onclick = notInUsePartAjax;
	document.getElementById("typeMain").onchange = getPartAttributeListAjax;
	
	//basic공용 함수
	document.getElementById("unselectBtn").onclick = unselectElem;
	initListClickEvent();
	initListMouseenter();

}

function getPartAttributeListAjax(){
	var params = {};
	var typeMain = $("#typeMain").val();
	
	params.typeMain = typeMain;
	//ajax post 보내기
	//callback refreshStoneList
	sendRequest("/jewsol/store/part/getPartAttributeListAjax.do", params, getPartAttributeListAjaxCallBack, "POST");
	
}

function getPartAttributeListAjaxCallBack(){
	if(request.readyState == 4){
		if(request.status == 200){
			var list = request.responseText;
			refreshElem("#partAttribute", list);
		}
	}
	
}


function insertPartAjax(){
	var validateResult = validatePart();
	
	if(validateResult){
		setSystemMessage("부속 입력을 시작 합니다.");
		var params = getInsertPartParameter();
		
		sendRequest("/jewsol/store/part/insertPartAjax.do", params, getPartListAjaxCallBack, "POST");
	}
	
}

function getInsertPartParameter(){
	var params = {};
	
	params.partAttribute = $("#partAttribute").val();
	params.partName = $("#partName").val();
	params.partLabor = $("#partLabor").val();
	params.partCost = $("#partCost").val();
	params.partPrice = $("#partPrice").val();
	
	return params;
}

function validatePart(){
	//system 메세지 초기화
	emptySystemMessage();

	if(!validateStringValueNull($("#partName"))){
		setSystemMessage("부속 이름에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#partCost"))){
		setSystemMessage("부속 매입 가격에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#partPrice"))){
		setSystemMessage("부속 판매 가격에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#partLabor"))){
		setSystemMessage("부속 공임에 값을 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#partCost"))){
		setSystemMessage("부속 매입 가격에 숫자를 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#partPrice"))){
		setSystemMessage("부속 판매 가격에 숫자를 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#partLabor"))){
		setSystemMessage("부속 공임에 숫자를 입력하세요.");
		return false;
	}else{
		emptySystemMessage();
		return true;
	};
	
}

function getPartListAjaxCallBack(){
	if(request.readyState == 4){
		if(request.status == 200){
			var list = request.responseText;
			refreshList(list);
			setSystemMessage("부속 입력이 완료 되었습니다.");
		}
	}
	
}



function notInUsePartAjax(){
	//버튼 비활성화
	disableTriggerButtons();
	//parameter 값 가져오기
	var params = {};
	
	params.partSeq = $(".selected").attr("id");
	//ajax post 보내기
	//callback refreshStoneList
	sendRequest("/jewsol/store/part/notInUsePartAjax.do", params, getPartListAjaxCallBack, "POST");
	
}