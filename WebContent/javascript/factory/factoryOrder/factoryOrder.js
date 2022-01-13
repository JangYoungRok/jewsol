
$(document).ready(function(){
	$("#orderDate").prop("readonly", true);
	
	$("#setYesterdayBtn").click(function(){
		setOrderDate(getYesterday($("#orderDate").val()));
		checkCloseOrder(getOrderDate());
	});
	
	$("#setTomorrowBtn").click(function(){
		setOrderDate(getTomorrow($("#orderDate").val()));
		checkCloseOrder(getOrderDate());
	});
	
	$("#closeOrderBtn").click(function(){
		setSystemMessage("주문 마감을 시작 합니다.");
		disableCloseOrderBtn();
		var params = {};
		params.orderDate = getOrderDate();
		sendRequest("/jewsol/factory/factoryOrder/closeOrder.do", params, afterCloseOrder, "GET");
	});
});

function checkCloseOrder(date){
	var params = {};
	params.orderDate = date;
	sendRequest("/jewsol/factory/factoryOrder/checkCloseOrder.do", params, afterCheckCloseOrder, "GET");
}
function afterCheckCloseOrder(){
	if(request.readyState == 4 && request.status == 200){
		if($.trim(request.responseText) == 'T'){
			disableCloseOrderBtn();
		}else if($.trim(request.responseText) == 'F'){
			ableCloseOrderBtn();
		}
	}
}

function afterCloseOrder(){
	if(request.readyState == 4 && request.status == 200){
		setSystemMessage("주문 마감이 완료 되었습니다.");
	}
}

function disableCloseOrderBtn(){
	$("#closeOrderBtn").prop("disabled", true);
}

function ableCloseOrderBtn(){
	$("#closeOrderBtn").prop("disabled", false);
}

