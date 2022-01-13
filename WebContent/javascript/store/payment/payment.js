var SelectedCustomerSeq = 0;
var KEYCODE_ARROW_UP = 38;
var KEYCODE_ARROW_DOWN = 40;
var KEYCODE_BACKSPACE = 8;
var KEYCODE_ENTER = 13;

$(document).ready(function(){
	$("#paymentDate").prop("readonly", true);
	$("#customerBalanceGold").prop("readonly", true);
	$("#customerBalanceCash").prop("readonly", true);
	$("#balanceGold").prop("readonly", true);
	$("#balanceCash").prop("readonly", true);
	
	setCustomerBalanceGold(0.0);
	setCustomerBalanceCash(0);
	setBalanceGold(0);
	setBalanceCash(0);
	
	CustomerListInterface.input = $("#customer");
	CustomerListInterface.div = $("#customerListDiv");
	
	$("#customer").keydown(function(){
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
			setSystemMessage("거래처 자료를 검색 합니다.");
			disablePayment();
			setPaymentListPanel("");
			setCustomerBalanceGold(0);
			setCustomerBalanceCash(0);
			
			var url = "/jewsol/store/payment/searchCustomer.do";
			var params = {};
			params.customerSeq = SelectedCustomerSeq;
			disableSearchCustomerBtn();
			setTimeout(sendRequest(url, params, searchCustomerResult, "GET"), "300");
		}
	});
	
	$("#paymentGold").blur(function(){
		var customerBalanceGold = getCustomerBalanceGold();
		var paymentGold = getPaymentGold();
		var balanceGold = 0;
		balanceGold = customerBalanceGold - paymentGold;
		balanceGold = balanceGold.toFixed(2);
		
		setBalanceGold(balanceGold);
	});
	
	$("#paymentCash").blur(function(){
		var customerBalanceCash = getCustomerBalanceCash();
		var paymentCash = getPaymentCash();
		var paymentDC = getPaymentDC();
		setBalanceCash(customerBalanceCash - paymentCash - paymentDC);
	});
	
	$("#insertPaymentBtn").click(function(){
		emptySystemMessage();
		var url = "/jewsol/store/payment/insertPayment.do";
		var params = {};
		if(SelectedCustomerSeq > 0){
			params.paymentContent = getPaymentContent();
			params.paymentGold = getPaymentGold() == ""? 0.0 : getPaymentGold().toFixed(2);
			params.paymentCash = getPaymentCash() == ""? 0 : getPaymentCash();
			params.paymentDC = getPaymentDC() == ""? 0 : getPaymentDC();
			params.customerSeq = SelectedCustomerSeq;
			params.paymentDate = getPaymentDate();
			
			setSystemMessage("결제 입력을 시작 합니다.");
			setPaymentListPanel("");
			setCustomerBalanceGold("");
			setCustomerBalanceCash("");
			setPaymentContent("");
			setPaymentGold("");
			setPaymentCash("");
			setBalanceGold("");
			setBalanceCash("");
			disableInsertPaymentBtn();
			disablePayment();
			
			setTimeout(sendRequest(url, params, afterInsertPayment, "POST"), 300);
		}else{
			setSystemMessage("거래처를 선택하고 값을 입력 해야 합니다.");
			return false;
		}
	});
	
	
});

function afterInsertPayment(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		ableInsertPaymentBtn();
		ablePayment();
		setCustomerBalanceGold(results[0]);
		setCustomerBalanceCash(results[1]);
		setPaymentListPanel(results[2]);
		initPaymentListEvent();
		setSystemMessage("결제 입력을 완료 하였습니다.");
	}
}

function searchCustomerResult(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		ableSearchCustomerBtn();
		ablePayment();
		setCustomerBalanceGold(results[0]);
		setCustomerBalanceCash(results[1]);
		setPaymentListPanel(results[2]);
		initPaymentListEvent();
		setSystemMessage("거래처 자료 검색을 완료 하였습니다.");
	}
}

function initPaymentListEvent(){
	var elems = $("#paymentList tr");
	for(var i = 1; i < elems.length; i++){
		var elem = elems[i];
		$(elem).mouseenter(function(){
			$(this).css("background-color", "lightgray");
		}).mouseleave(function(){
			$(this).css("background-color", "");
		}).click(function(){
			var paymentSeq = $(this).attr("id");
			window.open("/jewsol/store/payment/printPayment.do?paymentSeq="+paymentSeq,"printReleaseSheetWindow","menubar=no, toolbar=no, scrollbars=yes, location=no, left=10, top=3, resizable=yes, width=900, height=700");
		});
	}
}



function disableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", true);
}
function ableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", false);
}

function disablePaymentContent(){
	$("#paymentContent").prop("disabled", true);
}
function disablePaymentGold(){
	$("#paymentGold").prop("disabled", true);
}
function disablePaymentCash(){
	$("#paymentCash").prop("disabled", true);
}
function disableInsertPaymentBtn(){
	$("#insertPaymentBtn").prop("disabled", true);
}
function ableInsertPaymentBtn(){
	$("#insertPaymentBtn").prop("disabled", false);
}

function ablePaymentContent(){
	$("#paymentContent").prop("disabled", false);
}
function ablePaymentGold(){
	$("#paymentGold").prop("disabled", false);
}
function ablePaymentCash(){
	$("#paymentCash").prop("disabled", false);
}
function albeInsertPaymentBtn(){
	$("#insertPaymentBtn").prop("disabled", false);
}

function disablePayment(){
	disablePaymentContent();
	disablePaymentGold();
	disablePaymentCash();
	disableInsertPaymentBtn();
}

function ablePayment(){
	ablePaymentContent();
	ablePaymentGold();
	ablePaymentCash();
	ableInsertPaymentBtn();
}


function setPaymentListPanel(list){
	$("#paymentListPanel").html(list);
}

function getPaymentDate(){
	return trim($("#paymentDate").val());
};
function getCustomerBalanceGold(){
	return parseNumber($("#customerBalanceGold").val());
}
function setCustomerBalanceGold(value){
	$("#customerBalanceGold").val(value);
}
function getCustomerBalanceCash(){
	return parseNumber($("#customerBalanceCash").val());
}
function setCustomerBalanceCash(value){
	$("#customerBalanceCash").val(value);
}
function getPaymentContent(){
	return $("#paymentContent").val();
}
function setPaymentContent(value){
	$("#paymentContent").val(value);
}
function getPaymentGold(){
	return parseNumber($("#paymentGold").val());
}
function setPaymentGold(value){
	trim($("#paymentGold").val(value));
}
function getPaymentCash(){
	return parseNumber($("#paymentCash").val());
}
function setPaymentCash(value){
	$("#paymentCash").val(value);
}
function getPaymentDC(){
	return parseNumber($("#paymentDC").val());
}
function setPaymentDC(value){
	$("#paymentDC").val(value);
}
function getBalanceGold(){
	return parseNumber($("#balanceGold").val());
}
function setBalanceGold(value){
	$("#balanceGold").val(value);
}
function getBalanceCash(){
	return parseNumber($("#balanceCash").val());
}
function setBalanceCash(value){
	$("#balanceCash").val(value);
}