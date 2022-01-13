window.onload = initAdminAddForm;

function initAdminAddForm(){
	document.getElementById("insertBtn").onclick = insertAddAjax;
	document.getElementById("notInUseBtn").onclick = notInUseAddAjax;
	
	//basic공용 함수
	document.getElementById("unselectBtn").onclick = unselectElem;
	initListClickEvent();
	initListMouseenter();

}

function insertAddAjax(){
	var validateResult = validateAdd();
	
	if(validateResult){
		var params = getInsertAddParameter();
		
		sendRequest("/jewsol/store/add/insertAddAjax.do", params, getAddListAjaxCallBack, "POST");
	}
	
}


function getInsertAddParameter(){
	var params = {};
	
	params.addName = $("#addName").val();
	params.addLabor = $("#addLabor").val();
	params.addCost = $("#addCost").val();
	params.addPrice = $("#addPrice").val();
	
	return params;
}

function getAddListAjaxCallBack(){
	if(request.readyState == 4){
		if(request.status == 200){
			var list = request.responseText;
			refreshList(list);
			setSystemMessage("작업을 완료 하고 추가공정 리스트를 갱신 하였습니다.");
		}
	}
}

function notInUseAddAjax(){
	setSystemMessage("추가공정 이용 중지를 요청 합니다.");
	//버튼 비활성화
	disableTriggerButtons();
	//parameter 값 가져오기
	var params = {};
	
	params.addSeq = $(".selected").attr("id");
	//ajax post 보내기
	//callback refreshStoneList
	sendRequest("/jewsol/store/add/notInUseAddAjax.do", params, getAddListAjaxCallBack, "POST");
	
}


function validateAdd(){
	//system 메세지 초기화
	emptySystemMessage();

	if(!validateStringValueNull($("#addName"))){
		setSystemMessage("추가 공정 이름에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#addCost"))){
		setSystemMessage("추가 공정 매입 가격에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#addPrice"))){
		setSystemMessage("추가 공정 판매 가격에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#addLabor"))){
		setSystemMessage("추가 공정 공임에 값을 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#addCost"))){
		setSystemMessage("추가 공정 매입 가격에 숫자를 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#addPrice"))){
		setSystemMessage("추가 공정 판매 가격에 숫자를 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#addLabor"))){
		setSystemMessage("추가 공정 공임에 숫자를 입력하세요.");
		return false;
	}else{
		emptySystemMessage();
		return true;
	};
	
}