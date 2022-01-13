var SelectedCustomerSeq = 0;
var KEYCODE_ARROW_UP = 38;
var KEYCODE_ARROW_DOWN = 40;
var KEYCODE_BACKSPACE = 8;
var KEYCODE_ENTER = 13;

$(document).ready(function(){
	$("#saleDate").prop("readonly", true);
	$("#goldPrice").prop("readonly", true);
	$("#goldTaxPrice").prop("readonly", true);
	disableSaleType();
	disableSelectAllBtn();
	disableInsertSaleBtn();
	
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
			setSystemMessage("거래처 판매 자료를 검색 합니다.");
			emptySaleSheetListPanel();
			emptyOrderListPanel();
			disableSaleType();
			disableSelectAllBtn();
			disableInsertSaleBtn();
			
			var url = "/jewsol/store/sale/getSaleDataByCustomer.do";
			var params = {};
			params.customerSeq = SelectedCustomerSeq;
			disableSearchCustomerBtn();
			setTimeout(sendRequest(url, params, saleDataByCustomerResult, "GET"), "300");
		}
	});
	
	$("#selectAllBtn").click(function(){
		var elems = $("#orderList tr");
		for(var i = 0; i < elems.length; i++){
			var elem = elems[i];
			if($(elem).attr("class") != "checked"){
				$(elem).addClass("checked");
			}
		}
	});
	
	$("#insertSaleBtn").click(function(){
		emptySystemMessage();
		var orderSeqArr = getOrderSeqArr();
		var orderSaleWeightArr = getOrderSaleWeightArr();
		var orderSalePriceArr = getOrderSalePriceArr();
		var orderSaleWeightElems = $("#orderList .checked .orderSaleWeight");
		var orderSalePriceElems = $("#orderList .checked .orderSalePrice");
		if(orderSeqArr.length < 1){
			setSystemMessage("한개 이상의 주문을 선택해야  합니다.");
			return false;
		}else if(checkQtyElemsValue(orderSaleWeightElems) && checkQtyElemsValue(orderSalePriceElems)){
			setSystemMessage("판매 입력을 시작 합니다.");
			var params = {};
			params.customerSeq = SelectedCustomerSeq;
			params.saleDate = getSaleDate();
			params.saleTypeSeq = getSaleType();
			params.orderSeqArr = orderSeqArr;
			params.orderSaleWeightArr = orderSaleWeightArr;
			params.orderSalePriceArr = orderSalePriceArr;
			
			disableInsertSaleBtn();
			emptySaleSheetListPanel();
			emptyOrderListPanel();
			var url = "/jewsol/store/sale/insertSale.do";
			setTimeout(sendRequest(url, params, afterInsertSale, "POST"), 300);
		}
	});
	
});

function afterInsertSale(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split("//");
		setSaleSheetListPanel(results[0]);
		setOrderListPanel(results[1]);
		valueSelector("saleType", 1);
		initSaleSheetListEvent();
		initOrderListEvent();
		ableInsertSaleBtn();
		setSystemMessage("판매 입력을 완료 하였습니다.");
	}
}


function saleDataByCustomerResult(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split("//");
		setSaleSheetListPanel(results[0]);
		setOrderListPanel(results[1]);
		valueSelector("saleType", $.trim(results[2]));
		ableSearchCustomerBtn();
		ableInsertSaleBtn();
		ableSaleType();
		ableSelectAllBtn();
		initSaleSheetListEvent();
		initOrderListEvent();
		setSystemMessage("거래처 판매 자료를 가져 왔습니다.");
		
	}
}

function initSaleSheetListEvent(){
	var elems = $("#saleSheetList tr");
	for(var i = 1; i < elems.length; i++){
		var elem = elems[i];
		$(elem).mouseenter(function(){
			$(this).css("background-color", "lightgray");
		}).mouseleave(function(){
			$(this).css("background-color", "");
		}).click(function(){
			var saleSheetSeq = $(this).attr("id");
			window.open("/jewsol/store/saleSheet/printSaleSheet.do?saleSheetSeq="+saleSheetSeq,"printReleaseSheetWindow","menubar=no, toolbar=no, location=no, left=10, top=3, scrollbars=yes, resizable=yes, width=900, height=700");
		});
	}
}

function initOrderListEvent(){
	var elems = $("#orderList tr");
	for(var i = 0; i < elems.length; i++){
		var elem = elems[i];
		$(elem).click(function(){
			if($(this).attr("class") == "checked"){
				$(this).removeClass("checked");
			}else{
				$(this).addClass("checked");
			}
		});
	}
}

function getSaleType(){
	return $("#saleType option:selected").val();
}

function getSaleDate(){
	return $("#saleDate").val();
}

function getOrderSeqArr(){
	var arr = [];
	var elems = $("#orderList tr.checked");
	for(var i = 0; i < elems.length; i++){
		var elem = elems[i];
		arr.push($(elem).attr("id"));
	}
	return arr;
}

function getOrderSaleWeightArr(){
	var arr = [];
	var elems = $("#orderList .checked .orderSaleWeight");
	for(var i = 0; i < elems.length; i++){
		var elem = elems[i];
		arr.push($(elem).val());
	}
	return arr;
}

function getOrderSalePriceArr(){
	var arr = [];
	var elems = $("#orderList .checked .orderSalePrice");
	for(var i = 0; i < elems.length; i++){
		var elem = elems[i];
		arr.push($(elem).val());
	}
	return arr;
}

function arrIsNumber(arr){
	var result = true;
	for(var i = 0; i < arr.length; i++){
		if(!$.isNumeric(arr[i]) || arr[i] <= 0){
			result = false;
			setSystemMessage("입력한 값을 확인 하세요.");
		}
	}
	
	return result;
}

function selectSaleType(saleTypeSeq){
	
	
}

function emptySaleSheetListPanel(){
	$("#saleSheetListPanel").html("");
}
function setSaleSheetListPanel(data){
	$("#saleSheetListPanel").html(data);
}
function emptyOrderListPanel(){
	$("#orderListPanel").html("");
}
function setOrderListPanel(data){
	$("#orderListPanel").html(data);
}

function disableSelectAllBtn(){
	$("#selectAllBtn").prop("disabled", true);
}
function ableSelectAllBtn(){
	$("#selectAllBtn").prop("disabled", false);
}

function disableInsertSaleBtn(){
	$("#insertSaleBtn").prop("disabled", true);
}
function ableInsertSaleBtn(){
	$("#insertSaleBtn").prop("disabled", false);
}

function disableSaleType(){
	$("#saleType").prop("disabled", true);	
}
function ableSaleType(){
	$("#saleType").prop("disabled", false);
}

function disableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", true);
}
function ableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", false);
}