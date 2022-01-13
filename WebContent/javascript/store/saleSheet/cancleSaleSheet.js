var SelectedOrderIndex = -1;
var SelectedCustomerSeq = 0;
var KEYCODE_ARROW_UP = 38;
var KEYCODE_ARROW_DOWN = 40;
var KEYCODE_BACKSPACE = 8;
var KEYCODE_ENTER = 13;

$(document).ready(function(){
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
			
			var url = "/jewsol/store/saleSheet/getSaleSheetListByCustomer.do";
			var params = {};
			params.customerSeq = SelectedCustomerSeq;
			disableSearchCustomerBtn();
			setTimeout(sendRequest(url, params, saleSheetListByCustomerResult, "GET"), "300");
		}
	});

	$("#cancleSaleSheetBtn").click(function(){
		emptySystemMessage();
		if(SelectedOrderIndex < 0){
			setSystemMessage("한개 이상의 판매 장끼를 선택 해야 합니다.");
			return false;
		}else{
			setSystemMessage("판매 취소를 시작합니다.");
			var params = {};
			var url = "/jewsol/store/saleSheet/cancleSaleSheet.do";
			params.customerSeq = SelectedCustomerSeq;
			params.saleSheetSeq = $("#saleSheetList tr:eq("+SelectedOrderIndex+")").attr("id");
			
			emptySaleSheetListPanel();
			emptyOrderListPanel();
			
			disableCancleSaleSheetBtn();
			setTimeout(sendRequest(url, params, afterCancleSaleSheet, "POST"), 300);
		}
	});
});

function afterCancleSaleSheet(){
	if(request.readyState == 4 && request.status == 200){
		SelectedOrderIndex = -1;
		setSaleSheetListPanel(request.responseText);
		initSaleSheetListEvent();
		ableCancleSaleSheetBtn();
		setSystemMessage("판매 취소를 완료 하였습니다.");
	}
}

function saleSheetListByCustomerResult(){
	if(request.readyState == 4 && request.status == 200){
		setSaleSheetListPanel(request.responseText);
		initSaleSheetListEvent();
		ableSearchCustomerBtn();
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
			var url = "/jewsol/store/saleSheet/getSaleSheetContent.do";
			var params = {};
			params.saleSheetSeq = saleSheetSeq;
			if(SelectedOrderIndex > -1){
				clearBorder($("#saleSheetList tr:eq("+SelectedOrderIndex+")"));
			}
			
			SelectedOrderIndex = $("#saleSheetList tr").index(this);
			redBorder($(this));
			sendRequest(url, params, saleSheetContentResult, "GET");
		});
	}
}

function saleSheetContentResult(){
	if(request.readyState == 4 && request.status == 200){
		setOrderListPanel(request.responseText);
		setSystemMessage("판매 내역을 가져 왔습니다.");
	}
}

function disableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", true);
}
function ableSearchCustomerBtn(){
	$("#searchCustomerBtn").prop("disabled", false);
}
function disableCancleSaleSheetBtn(){
	$("#cancleSaleSheetBtn").prop("disabled", true);
}
function ableCancleSaleSheetBtn(){
	$("#cancleSaleSheetBtn").prop("disabled", false);
}
function setOrderListPanel(data){
	$("#orderListPanel").html(data);
}
function setSaleSheetListPanel(data){
	$("#saleSheetListPanel").html(data);
}
function emptyOrderListPanel(){
	$("#orderListPanel").html("");
}
function emptySaleSheetListPanel(){
	$("#saleSheetListPanel").html("");
}