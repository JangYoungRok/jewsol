var SelectedCustomerSeq = 0;
var KEYCODE_ARROW_UP = 38;
var KEYCODE_ARROW_DOWN = 40;
var KEYCODE_BACKSPACE = 8;
var KEYCODE_ENTER = 13;

$(document).ready(function(){
	CustomerListInterface.input = $("#customer");
	CustomerListInterface.div = $("#customerListDiv");
	
	$("#productCode").keydown(function(event){
		switch( event.keyCode ) {
		case KEYCODE_ENTER :
			$("#inqueryOrderBtn").focus();
			break;
			return false;
		}
	});
	
	$("#customer").keydown(function(){
		console.log("aaa");
		return CustomerListInterface.keyDown(event);
	});
	
	$("#customer").blur(function(){
		CustomerListInterface.blur();
	});
	
	$("#searchCustomerBtn").click(function(){
		emptySystemMessage();
		if(SelectedCustomerSeq == 0){
			setSystemMessage("거래처를 선택 해야 합니다.");
			return false;
		}else{
			setSystemMessage("거래처 판매 자료를 검색 합니다.");
			emptyOrderList();
			
			var url = "/jewsol/store/order/inquery/getOrderListByCustomer.do";
			var params = {};
			params.customerSeq = SelectedCustomerSeq;
			setTimeout(sendRequest(url, params, inqueryOrderResult, "GET"), "300");
		}
	});
	
	$("#inqueryOrderBtn").click(function(){
		emptySystemMessage();
		emptyOrderList();
		if(!validateProductCode()){
			setSystemMessage("제품번호로 검색 합니다.");
			var params = {};
			params.productCode = getProductCode();
			sendRequest("/jewsol/store/order/inquery/getInqueryOrder.do", params, inqueryOrderResult, 'POST');
		}else{
			setSystemMessage("제품번호에 값을 입력 하세요.");
		}
			
	});
});

function inqueryOrderResult(){
	if(request.readyState == 4 && request.status == 200){
		setSystemMessage("검색이 완료 되었습니다.");
		console.log("-------------------");
		console.log(request.responseText);
		putOrderList(request.responseText);
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

function disableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", true);
}
function ableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", false);
}