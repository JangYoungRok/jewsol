var KEYCODE_ENTER = 13;

$(document).ready(function(){
	$("#productCode").keydown(function(event){
		switch( event.keyCode ) {
		case KEYCODE_ENTER :
			$("#inqueryOrderBtn").focus();
			break;
			return false;
		}
	});
	
	$("#inqueryOrderBtn").click(function(){
		emptySystemMessage();
		emptyOrderList();
		if(!validateProductCode()){
			setSystemMessage("제품번호로 검색 합니다.");
			var params = {};
			params.productCode = getProductCode();
			sendRequest("/jewsol/factory/factoryOrder/getInqueryFactoryOrder.do", params, inqueryOrderResult, 'POST');
		}else{
			setSystemMessage("제품번호에 값을 입력 하세요.");
		}
			
	});
});

function inqueryOrderResult(){
	if(request.readyState == 4 && request.status == 200){
		setSystemMessage("검색이 완료 되었습니다.");
		putOrderList(request.responseText);
		$("#productCode").focus();
	}
}

function validateProductCode(){
	if($("#productCode").val().length == 0){
		return true;
	}
	return false;
}

function emptyOrderList(){
	$("#orderListPanel").html("");
}

function putOrderList(orderList){
	$("#orderListPanel").html(orderList);
}

function getProductCode(){
	return $("#productCode").val();
}