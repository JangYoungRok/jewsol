window.onload = initAdminStoneForm;

function initAdminStoneForm(){
	document.getElementById("insertStoneBtn").onclick = insertStoneAjax;
	document.getElementById("notInUseStoneBtn").onclick = notInUseStone;
	document.getElementById("unselectStoneBtn").onclick = unselectStone;
	
	initStoneListClickEvent();
	initStoneListMouseenter();

}

function initStoneListMouseenter(){
var stoneList = $("#stoneListDiv p");
	
	for(var i = 0; i < stoneList.length; i++){
		var elem = stoneList[i];
		$(elem).mouseenter(function(){
			$(this).addClass("hover");
			
		}).mouseleave(function(){
			$(this).removeClass();
			
		});
	}
}

function initStoneListClickEvent(){
	var stoneList = $("#stoneListDiv p");
	
	for(var i = 0; i < stoneList.length; i++){
		var elem = stoneList[i];
		$(elem).bind('click', function(event){
			
			selectStone($(this));
		});	
	}
}

function validateStone(){
	//system 메세지 초기화
	emptySystemMessage();

	if(!validateStringValueNull($("#stoneName"))){
		setSystemMessage("스톤 이름에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#stoneCost"))){
		setSystemMessage("스톤 매입 가격에 값을 입력하세요.");
		return false;
	}else if(!validateStringValueNull($("#stonePrice"))){
		setSystemMessage("스톤 판매 가격에 값을 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#stoneCost"))){
		setSystemMessage("스톤 매입 가격에 숫자를 입력하세요.");
		return false;
	}else if(!validateIsNumber($("#stonePrice"))){
		setSystemMessage("스톤 판매 가격에 숫자를 입력하세요.");
		return false;
	}else{
		emptySystemMessage();
		return true;
	};
	
}

function insertStoneAjax(){
	var validateResult = validateStone();
	
	if(validateResult){
		var params = getInsertStoneParameter();
		
		sendRequest("/jewsol/store/stone/insertStoneAjax.do", params, getRefreshStoneListAjaxCallBack, "POST");
	}
	
}

function getRefreshStoneListAjaxCallBack(){
	if(request.readyState == 4){
		if(request.status == 200){
			var stoneList = request.responseText;
			refreshStoneList(stoneList);
			initStoneListClickEvent();
			initStoneListMouseenter();
			
			disableUpdateButtons();
		}
	}
	
}

function refreshStoneList(stoneList){
	//기존 리스트 삭제
	emptyElem(stoneListDiv);
	//새로운 리스트 추가
	stoneListDiv.innerHTML = stoneList;
}
function getInsertStoneParameter(){
	var params = {};
	
	params.stoneSize = $("#stoneSize").val();
	params.stoneName = $("#stoneName").val();
	params.stoneCost = $("#stoneCost").val();
	params.stonePrice = $("#stonePrice").val();
	
	return params;
}

function removeStoneListEvent(){
	var stoneList = $("#stoneListDiv p");
	for(var i = 0; i < stoneList.length; i++){
		var elem = stoneList[i];
		$(elem).unbind();
	}
}

function selectStone(elem){
	//기존 선택된 엘레멘트 
	$(".hover").removeClass();
	$(".selected").removeClass();
	//해당 P select class 적용
	$(elem).addClass("selected");
	
	//클릭 이벤트 없애기
	//hover설정 없애기
	removeStoneListEvent();
	
	//수정 버튼 활성화
	ableUpdateButtons();
	
}

function disableUpdateButtons(){
	$("#notInUseStoneBtn").attr("disabled",true);
	$("#unselectStoneBtn").attr("disabled",true);
	
}

function ableUpdateButtons(){
	$("#notInUseStoneBtn").attr("disabled",false);
	$("#unselectStoneBtn").attr("disabled",false);
	
}

function notInUseStone(){
	//버튼 비활성화
	disableUpdateButtons();
	//parameter 값 가져오기
	var params = {};
	
	params.stoneSeq = $(".selected").attr("id");
	//ajax post 보내기
	//callback refreshStoneList
	sendRequest("/jewsol/store/stone/notInUseStoneAjax.do", params, getRefreshStoneListAjaxCallBack, "POST");
	
}

function unselectStone(){
	//버튼 비활성화
	disableUpdateButtons();
	
	//클래스 초기화
	$(".selected").removeClass();
	
	//클릭 이벤트 
	//마우스 hover bg color pointer 설정
	initStoneListClickEvent();
	initStoneListMouseenter();

}

