window.onload = initAdminCzForm;

function initAdminCzForm(){
	document.getElementById("insertCzBtn").onclick = insertCzAjax;
	document.getElementById("notInUseCzBtn").onclick = notInUseCz;
	document.getElementById("cancelUpdateCzBtn").onclick = unselectCz;
	$("#updateCzBtn").click(function(){
		var params = {};
		params.czSeq =$(".selected").attr("id");
		params.czName = $("#czName").val();
		params.czLabor = $("#czLabor").val();
		params.czPrice = $("#czPrice").val();
		sendRequest("/jewsol/store/cz/updateCz.do", params, afterUpdateCz, "POST");
	});
	
	initCzListClickEvent();
	initCzListMouseenter();

}
function afterUpdateCz(){
	if(request.readyState == 4 && request.status == 200){
		var czList = request.responseText;
		refreshCzList(czList);
		initCzListClickEvent();
		initCzListMouseenter();
		disableUpdateButtons();
		
		$("#insertCzBtn").attr("disabled", false);
		$("#czSize").attr("disabled", false);
		
		$("#czName").val("");
		$("#czLabor").val(0);
		$("#czPrice").val(0);
	}
}
//init function
function initCzListMouseenter(){
var czList = $("#czListSection p");
	
	for(var i = 0; i < czList.length; i++){
		var elem = czList[i];
		$(elem).mouseenter(function(){
			$(this).addClass("hover");
			
		}).mouseleave(function(){
			$(this).removeClass();
			
		});
	}
}

function initCzListClickEvent(){
	var czList = $("#czListSection p");
	
	for(var i = 0; i < czList.length; i++){
		var elem = czList[i];
		$(elem).bind('click',{index : i}, function(event){
			selectCz(event.data.index);
			$("#insertCzBtn").attr("disabled", true);
			$("#czSize").attr("disabled", true);
			$("#czName").val($("[name='czName']:eq("+event.data.index+")").val());
			$("#czLabor").val($("[name='czLabor']:eq("+event.data.index+")").val());
			$("#czPrice").val($("[name='czPrice']:eq("+event.data.index+")").val());
		});	
	}
}

function validateCz(){
	//system 메세지 초기화
	emptySystemMessage();

	if(!validateStringValueNull($("#czName"))){
		setSystemMessage("조각 이름에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#czLabor"))){
		setSystemMessage("조각 공장 공임에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#czPrice"))){
		setSystemMessage("조각 판매 공임에 값을 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#czPrice"))){
		setSystemMessage("조각 판매 공임에 숫자를 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#czLabor"))){
		setSystemMessage("조각 공장 공임에 숫자를 입력하세요.");
		return false;
	}else{
		emptySystemMessage();
		return true;
	};
	
}

function insertCzAjax(){
	var validateResult = validateCz();
	
	if(validateResult){
		var params = getInsertCzParameter();
		
		sendRequest("/jewsol/store/cz/insertCzAjax.do", params, getRefreshCzListAjaxCallBack, "POST");
	}
	
}

function getRefreshCzListAjaxCallBack(){
	if(request.readyState == 4){
		if(request.status == 200){
			var czList = request.responseText;
			refreshCzList(czList);
			initCzListClickEvent();
			initCzListMouseenter();
			disableUpdateButtons();
		}
	}
	
}

function refreshCzList(czList){
	//기존 리스트 삭제
	emptyElem(czListSection);
	//새로운 리스트 추가
	czListSection.innerHTML = czList;
}

function getInsertCzParameter(){
	var params = {};
	
	params.czSize = $("#czSize").val();
	params.czName = $("#czName").val();
	params.czLabor = $("#czLabor").val();
	params.czPrice = $("#czPrice").val();
	
	return params;
}



function removeCzListEvent(){
	var czList = $("#czListSection p");
	for(var i = 0; i < czList.length; i++){
		var elem = czList[i];
		$(elem).unbind();
	}
}


function selectCz(index){
	var czList = $("#czListSection p");
	var selectedElem = czList[index];
	//기존 선택된 엘레멘트 
	$(".hover").removeClass();
	
	$(".selected").removeClass();
	//해당 P select class 적용
	$(selectedElem).addClass("selected");
	
	//클릭 이벤트 없애기
	//hover설정 없애기
	removeCzListEvent();
	
	//수정 버튼 활성화
	ableUpdateButtons();
	
}

function disableUpdateButtons(){
	$("#notInUseCzBtn").attr("disabled",true);
	$("#cancelUpdateCzBtn").attr("disabled",true);
	$("#updateCzBtn").attr("disabled", true);
}

function ableUpdateButtons(){
	$("#notInUseCzBtn").attr("disabled",false);
	$("#cancelUpdateCzBtn").attr("disabled",false);
	$("#updateCzBtn").attr("disabled", false);
	
}


function notInUseCz(){
	//parameter 값 가져오기
	var params = {};
	
	params.czSeq = $(".selected").attr("id");
	//ajax post 보내기
	//callback refreshCzList
	sendRequest("/jewsol/store/cz/notInUseCzAjax.do", params, getRefreshCzListAjaxCallBack, "POST");
	
}

function unselectCz(){
	//버튼 비활성화
	disableUpdateButtons();
	
	//클래스 초기화
	$(".selected").removeClass();
	
	//클릭 이벤트 
	//마우스 hover bg color pointer 설정
	initCzListClickEvent();
	initCzListMouseenter();
	
	$("#insertCzBtn").attr("disabled", false);
	$("#czSize").attr("disabled", false);
	
	$("#czName").val("");
	$("#czLabor").val(0);
	$("#czPrice").val(0);
	
	

}
