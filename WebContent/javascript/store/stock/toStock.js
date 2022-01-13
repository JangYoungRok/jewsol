var POS = 0;

$(document).ready(function(){
	$("#toStockDate").prop("readonly", true);
	
	initReleaseSheetListEvent();
	
	$("#selectAllBtn").click(function(){
		setSystemMessage("모든 주문 내역을 선택 하였습니다.");
		var elems = $("#orderList tr");
		for(var i = 0; i < elems.length; i++){
			var elem = elems[i];
			if($(elem).attr("class") != "checked"){
				$(elem).addClass("checked");
			}
		}
	});
	
	$("#toStockBtn").click(function(){
		disableToStockBtn();
		emptySystemMessage();
		
		var orderSeqArr = getOrderSeqArr();
		if(orderSeqArr.length < 1){
			setSystemMessage("한개 이상의 주문을 선택 해야 합니다.");
			ableToStockBtn();
		}else{
			setSystemMessage("입고를 시작 합니다.");
			var url = "/jewsol/store/stock/toStock.do";
			var method = "POST";
			var params = {};
			params.orderSeqArr = orderSeqArr;
			params.toStockDate = getToStockDate();
			params.unCheckedOrderSeqArr = getUnCheckedOrderSeqArr();
			params.releaseSheetSeq = $("#releaseSheetList tr:eq("+POS+")").attr("id");
			disableToStockBtn();
			emptyOrderListPanel();
			emptyReleaseSheetListPanel();
			setTimeout(sendRequest(url, params, afterToStock, method), 300);
		}
	});
	
	
});

function afterToStock(){
	if(request.readyState == 4 && request.status == 200){
		setReleaseSheetListPanel(request.responseText);
		initReleaseSheetListEvent();
		ableToStockBtn();
		setSystemMessage("입고가 완료 되었습니다.");
	}
}

function initReleaseSheetListEvent(){
	var elems = $("#releaseSheetList tr");
	for(var i = 1; i < elems.length; i++){
		var elem = elems[i];
		$(elem).mouseenter(function(){
			$(this).css("background-color", "lightgray");
		}).mouseleave(function(){
			$(this).css("background-color", "");
		}).click({index : i}, function(e){
			if(POS > 0){
				clearBorder( $("#releaseSheetList tr:eq("+POS+")"));
			}
			redBorder(this);
			POS = e.data.index;
			emptyOrderListPanel();
			setSystemMessage("주문 내역을 가져 옵니다.");
			var params = {};
			params.releaseSheetSeq = $(this).attr("id");
			var url = "/jewsol/store/stock/getOrderListByReleaseSheetSeq.do";
			sendRequest(url, params, orderListByReleaseSheetSeqResult, "GET");
		});
	}
}
function orderListByReleaseSheetSeqResult(){
	if(request.readyState == 4 && request.status == 200){
		setOrderListPanel(request.responseText);
		initOrderListEvent();
		setSystemMessage("주문 내역을 갱신 했습니다.");
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



function disableToStockBtn(){
	$("#toStockBtn").prop("disabled", true);
}

function ableToStockBtn(){
	$("#toStockBtn").prop("disabled", false);
}

function emptyReleaseSheetListPanel(){
	$("#releaseSheetListPanel").html("");
}

function setReleaseSheetListPanel(data){
	$("#releaseSheetListPanel").html(data);
}



function emptyOrderListPanel(){
	$("#orderListPanel").html("");
}

function setOrderListPanel(data){
	$("#orderListPanel").html(data);
}


function getToStockDate(){
	return $.trim($("#toStockDate").val());
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

function getUnCheckedOrderSeqArr(){
	var arr = [];
	var elems = $("#orderList tr:not(.checked)");
	for(var i = 0; i < elems.length; i++){
		var elem = elems[i];
		arr.push($(elem).attr("id"));
	}
	return arr;
}