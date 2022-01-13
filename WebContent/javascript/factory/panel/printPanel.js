$(document).ready(function(){
	$("#orderDate").prop("readonly", true);
	
	disableButton($("#printPanelBtn"));
	disableButton($("#printStickerBtn"));
	
	$("#setYesterdayBtn").click(function(){
		setOrderDate(getYesterday($("#orderDate").val()));
		checkOrderDate(getOrderDate());
		//getOrderForPanelByOrderDate(getOrderDate());
	});
	
	$("#setTomorrowBtn").click(function(){
		setOrderDate(getTomorrow($("#orderDate").val()));
		checkOrderDate(getOrderDate());
		//getOrderForPanelByOrderDate(getOrderDate());
		
	});
	
	$("#printPanelBtn").click(function(){
		/*var length = $("#orderList tr").length;
		emptySystemMessage();
		if(length < 2){
			setSystemMessage("한개 이상의 주문이 등록 되어 있어야 합니다.");
			return false;
		}else{
			setSystemMessage("판 주문장 인쇄내용을 가져 옵니다.");
			var orderDate  = getOrderDate();
			window.open("/jewsol/factory/panel/getPrintPanel.do?orderDate="+orderDate,"printPanelWindow","menubar=no, toolbar=no, location=no, scrollbars=yes, left=10, top=3, resizable=yes, width=1000, height=700");
		}*/
		var orderDate  = getOrderDate();
		window.open("/jewsol/factory/panel/getPrintPanel.do?orderDate="+orderDate,"printPanelWindow","menubar=no, toolbar=no, location=no, scrollbars=yes, left=10, top=3, resizable=yes, width=1000, height=700");
	});
	
	$("#printStickerBtn").click(function(){
		/*var length = $("#orderList tr").length;
		emptySystemMessage();
		if(length < 2){
			setSystemMessage("한개 이상의 주문이 등록 되어 있어야 합니다.");
			return false;
		}else{
			setSystemMessage("스티커 인쇄내용을 가져 옵니다.");
			var orderDate  = getOrderDate();
			window.open("/jewsol/factory/panel/getPrintSticker.do?orderDate="+orderDate,"printStickerWindow","menubar=no, toolbar=no, location=no, scrollbars=yes, left=10, top=3, resizable=yes, width=1000, height=700");
			
		}*/
		var orderDate  = getOrderDate();
		window.open("/jewsol/factory/panel/getPrintSticker.do?orderDate="+orderDate,"printStickerWindow","menubar=no, toolbar=no, location=no, scrollbars=yes, left=10, top=3, resizable=yes, width=1000, height=700");
	});
	
});

function checkOrderDate(orderDate){
	disableButton($("#setYesterdayBtn"));
	disableButton($("#setTomorrowBtn"));
	
	var params = {};
	params.orderDate = orderDate;
	sendRequest("/jewsol/factory/panel/checkOrderDate.do", params, checkOrderDateResult, 'POST');
}

function checkOrderDateResult(){
	if(request.readyState == 4 && request.status == 200){
		if($.trim(request.responseText) == "1"){
			ableButton($("#printPanelBtn"));
			ableButton($("#printStickerBtn"));
		}else{
			disableButton($("#printPanelBtn"));
			disableButton($("#printStickerBtn"));
		}
	}
	
	ableButton($("#setYesterdayBtn"));
	ableButton($("#setTomorrowBtn"));
}


/*function getOrderForPanelByOrderDate(orderDate){
	setSystemMessage("주문 내역을 가져 옵니다.");
	var params = {};
	params.orderDate = orderDate;
	sendRequest("/jewsol/factory/panel/getOrderForPanelByOrderDate.do", params, orderForPanelByOrderDateResult, 'POST');

	
}*/

/*function orderForPanelByOrderDateResult(){
	if(request.readyState == 4 && request.status == 200){
		$("#orderListPanel").html(request.responseText);
		setSystemMessage("주문 내역을 가져왔습니다.");
	}
}*/