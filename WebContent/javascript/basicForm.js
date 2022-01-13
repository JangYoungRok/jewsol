function initListMouseenter(){
var list = $("#listDiv p");
	
	for(var i = 0; i < list.length; i++){
		var elem = list[i];
		$(elem).mouseenter(function(){
			$(this).addClass("hover");
			
		}).mouseleave(function(){
			$(this).removeClass();
			
		});
	}
}

function initListClickEvent(){
	var list = $("#listDiv p");
	
	for(var i = 0; i < list.length; i++){
		var elem = list[i];
		$(elem).bind('click', function(event){
			
			selectElem($(this));
		});	
	}
}

function removeListEvent(){
	var list = $("#listDiv p");
	for(var i = 0; i < list.length; i++){
		var elem = list[i];
		$(elem).unbind();
	}
}

function selectElem(elem){
	//기존 선택된 엘레멘트 
	$(".hover").removeClass();
	$(".selected").removeClass();
	//해당 P select class 적용
	$(elem).addClass("selected");
	
	//클릭 이벤트 없애기
	//hover설정 없애기
	removeListEvent();
	
	//수정 버튼 활성화
	ableTriggerButtons();
	
}

function unselectElem(){
	//버튼 비활성화
	disableTriggerButtons();
	
	//클래스 초기화
	$(".selected").removeClass();
	
	//클릭 이벤트 
	//마우스 hover bg color pointer 설정
	initListClickEvent();
	initListMouseenter();

}

function disableTriggerButtons(){
	var elemList = $("#triggerButtonPanel input");
	if(elemList != null){
		for(var i = 0; i < elemList.length; i++){
			$(elemList[i]).attr("disabled",true);
			
		}
	}
}

function ableTriggerButtons(){
	var elemList = $("#triggerButtonPanel input");
	if(elemList != null){
		for(var i = 0; i < elemList.length; i++){
			$(elemList[i]).attr("disabled",false);
			
		}
	}
	
}

function refreshList(list){
	refreshElem("#listDiv",list);
	initListClickEvent();
	initListMouseenter();
	
}
