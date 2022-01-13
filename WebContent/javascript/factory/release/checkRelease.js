$(document).ready(function(){
	$("#orderDate").prop("readonly", true);
	$("#panelNumber").prop("readonly", true);
	$("#panelLength").prop("readonly", true);
	initOrderListEvent();
	checkPanelLength();
	
	
	$("#setYesterdayBtn").click(function(){
		setOrderDate(getYesterday($("#orderDate").val()));
		//getOrderListForRelease(getOrderDate(), 1);
		getPanelLengthByOrderDate(getOrderDate());
		setPanelNumber(1);
	});
	
	$("#setTomorrowBtn").click(function(){
		setOrderDate(getTomorrow($("#orderDate").val()));
		//getOrderListForRelease(getOrderDate(), 1);
		getPanelLengthByOrderDate(getOrderDate());
		setPanelNumber(1);
	});
	
	$("#minusPanelNumberBtn").click(function(){
		emptySystemMessage();
		if(getPanelNumber() == 1){
			setSystemMessage("1보다 적은 번호의 판번호는 가져 올수 없습니다.");
		}else{
			var panelNumber = getPanelNumber() - 1;
			setPanelNumber(panelNumber);
			//getOrderListForRelease(getOrderDate(), panelNumber);
		}
	});
	
	$("#plusPanelNumberBtn").click(function(){
		emptySystemMessage();
		if(getPanelNumber() == getPanelLength()){
			setSystemMessage("마지막 판 번호 입니다.");
		}else{
			var panelNumber = getPanelNumber() + 1;
			setPanelNumber(panelNumber);
			//getOrderListForRelease(getOrderDate(), panelNumber);
		}
	});
	
	
	$("#getPanelListBtn").click(function(){
		getOrderListForRelease(getOrderDate(), getPanelNumber());
	});
	
	$("#checkReleaseBtn").click(function(){
		emptySystemMessage();
		var orderSeqArr = getOrderSeqArr();
		var params = {};
		if(orderSeqArr.length < 1){
			setSystemMessage("한개 이상의 주문을 체크 해야 합니다.");
		}else if(orderSeqArr.length > 0){
			$("#checkReleaseBtn").prop("disabled", true);
			setSystemMessage("출고 체크를 시작 합니다.");
			params.orderSeqArr = orderSeqArr;
			params.orderDate = getOrderDate();
			params.panelNumber = getPanelNumber();
			var url = "/jewsol/factory/release/checkRelease.do";
			setTimeout(sendRequest(url, params, afterCheckRelease, "POST"), 300);
		}
	});
	
});

function afterCheckRelease(){
	if(request.readyState == 4 && request.status == 200){
		setOrderListPanel(request.responseText);	
		initOrderListEvent();
		$("#checkReleaseBtn").prop("disabled", false);
		setSystemMessage("출고 체크가 완료 되었습니다.");
	}
}

function getPanelLengthByOrderDate(orderDate){
	setSystemMessage("판번호를 갱신 합니다.");
	setOrderListPanel("");
	var params = {};
	params.orderDate = orderDate;
	sendRequest("/jewsol/factory/release/getPanelLengthByOrderDate.do", params, getPanelLengthByOrderDateResult, "POST");
}

function getPanelLengthByOrderDateResult(){
	if(request.readyState == 4 && request.status == 200){
		setPanelLength($.trim(request.responseText));
		checkPanelLength();
		setSystemMessage("판번호 갱신이 완료 되었습니다.");
	}
}

function getOrderListForRelease(orderDate, panelNumber){
	setSystemMessage("리스트를 갱신 합니다.");
	setOrderListPanel("");
	var params = {};
	params.orderDate = orderDate;
	params.panelNumber = panelNumber;
	sendRequest("/jewsol/factory/release/getOrderListForRelease.do", params, orderListForReleaseResult, "POST");
}

function orderListForReleaseResult(){
	if(request.readyState == 4 && request.status == 200){
		setOrderListPanel(request.responseText);	
		initOrderListEvent();
		setSystemMessage("리스트 갱신이 완료 되었습니다.");
	}
}

function getOrderSeqArr(){
	var arr = [];
	var elems = $("#orderList tr.check");
	if(elems.length > 0){
		for(var i = 0; i < elems.length; i++){
			var elem = elems[i];
			arr.push($.trim($(elem).attr("id")));
		}
	}
	return arr;
	
}

function checkPanelLength(){
	if(getPanelLength() == 0){
		$("#plusPanelNumberBtn").prop("disabled", true);
		$("#minusPanelNumberBtn").prop("disabled", true);
	}else{
		$("#plusPanelNumberBtn").prop("disabled", false);
		$("#minusPanelNumberBtn").prop("disabled", false);
	}
}

function initOrderListEvent(){
	if(getPanelLength() > 0){
		var elems = $("#orderList tr");
		var length = $(elems).length;
		if(length > 0){
			for(var i = 0; i < length; i++){
				var elem = elems[i];
				if($(elem).attr("class") != "released"){
					$(elem).mouseenter(function(){
						redBorder(this);
					}).mouseleave(function(){
						$(this).removeAttr("style");
					});
					$(elem).click({index : i},function(e){
						if($(this).attr("class") == "unReleased"){
							$(this).attr("class","check");
						}else if($(this).attr("class") == "check"){
							$(this).attr("class","unReleased");
						}
					});
				}
			}
		}
	}
}

function setOrderListPanel(data){
	$("#orderListPanel").html(data);
}

function getPanelNumber(){
	return Number($("#panelNumber").val());
}

function getPanelLength(){
	return Number($("#panelLength").val());
}

function setPanelNumber(number){
	$("#panelNumber").val(number);
}

function setPanelLength(length){
	$("#panelLength").val(length);
}