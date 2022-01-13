var SelectedCustomerSeq = 0;
var KEYCODE_ARROW_UP = 38;
var KEYCODE_ARROW_DOWN = 40;
var KEYCODE_BACKSPACE = 8;
var KEYCODE_ENTER = 13;

$(document).ready(function(){
	$("#saleSheetDate").prop("readonly", true);
	$("#lastBalanceGold").prop("readonly", true);
	$("#lastBalanceCash").prop("readonly", true);
	$("#balanceGold").prop("readonly", true);
	$("#balanceCash").prop("readonly", true);
	
	setLastBalanceCash(0);
	setLastBalanceGold(0.0);
	setBalanceCash(0);
	setBalanceGold(0.0);
	
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
			disableSaleSheet();
			setLastBalanceGold(0.0);
			setLastBalanceCash(0);
			setBalanceCash(0);
			setBalanceGold(0.0);
			var url = "/jewsol/store/saleSheet/searchCustomer.do";
			var params = {};
			params.customerSeq = SelectedCustomerSeq;
			disableSearchCustomerBtn();
			setTimeout(sendRequest(url, params, searchCustomerResult, "GET"), "300");
		}
	});

	$("#saleGold").blur(function(){
		var lastBalanceGold = getLastBalanceGold();
		var saleGold = getSaleGold();
		setBalanceGold(lastBalanceGold + saleGold);
	});
	
	$("#saleCash").blur(function(){
		var lastBalanceCash = getLastBalanceCash();
		var saleCash = getSaleCash();
		setBalanceCash(lastBalanceCash + saleCash);
	});
	
	$("#insertSaleSheetBtn").click(function(){
		emptySystemMessage();
		var url = "/jewsol/store/saleSheet/insertSaleSheet.do";
		var params = {};
		if(SelectedCustomerSeq > 0){
			params.saleGold = getSaleGold() == ""? 0.0 : getSaleGold();
			params.saleCash = getSaleCash() == ""? 0 : getSaleCash();
			params.customerSeq = SelectedCustomerSeq;
			params.saleSheetDate = getSaleSheetDate();
			
			setSystemMessage("결제 입력을 시작 합니다.");
			setLastBalanceGold("");
			setLastBalanceCash("");
			setSaleGold("");
			setSaleCash("");
			setBalanceGold("");
			setBalanceCash("");
			disableInsertSaleSheetBtn();
			disableSaleSheet();
			
			setTimeout(sendRequest(url, params, afterInsertSaleSheet, "POST"), 300);
		}else{
			setSystemMessage("거래처를 선택하고 값을 입력 해야 합니다.");
			return false;
		}
	});	

});

function afterInsertSaleSheet(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		ableInsertSaleSheetBtn();
		ableSaleSheet();
		setLastBalanceGold(results[0]);
		setLastBalanceCash(results[1]);
		setSystemMessage("기성 판매 입력을 완료 하였습니다.");
	}
}

function searchCustomerResult(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		ableSearchCustomerBtn();
		ableSaleSheet();
		setLastBalanceGold(results[0]);
		setLastBalanceCash(results[1]);
		setSystemMessage("거래처 자료 검색을 완료 하였습니다.");
	}
}


function disableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", true);
}
function ableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", false);
}
function disableSaleGold(){
	$("#saleGold").prop("disabled", true);
}
function disableSaleCash(){
	$("#saleCash").prop("disabled", true);
}
function disableInsertSaleSheetBtn(){
	$("#insertSaleSheetBtn").prop("disabled", true);
}
function ableInsertSaleSheetBtn(){
	$("#insertSaleSheetBtn").prop("disabled", false);
}
function ableSaleGold(){
	$("#saleGold").prop("disabled", false);
}
function ableSaleCash(){
	$("#saleCash").prop("disabled", false);
}
function albeInsertSaleSheetBtn(){
	$("#insertSaleSheetBtn").prop("disabled", false);
}

function disableSaleSheet(){
	disableSaleGold();
	disableSaleCash();
	disableInsertSaleSheetBtn();
}

function ableSaleSheet(){
	ableSaleGold();
	ableSaleCash();
	ableInsertSaleSheetBtn();
}

function getSaleSheetDate(){
	return trim($("#saleSheetDate").val());
};
function getLastBalanceGold(){
	return parseNumber($("#lastBalanceGold").val());
}
function setLastBalanceGold(value){
	$("#lastBalanceGold").val(value);
}
function getLastBalanceCash(){
	return parseNumber($("#lastBalanceCash").val());
}
function setLastBalanceCash(value){
	$("#lastBalanceCash").val(value);
}
function getSaleGold(){
	return parseNumber($("#saleGold").val());
}
function setSaleGold(value){
	parseNumber($("#saleGold").val(value));
}
function getSaleCash(){
	return parseNumber($("#saleCash").val());
}
function setSaleCash(value){
	$("#saleCash").val(value);
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
