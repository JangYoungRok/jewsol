$(document).ready(function(){
	$("#releaseSheetDate").prop("readonly", true);
	$("#releaseSheetNumber").prop("readonly", true);
	
	initReleaseSheetListEvent();
	initOrderListEvent();
	
	$("#branchList").change(function(){
		setSystemMessage("지점 출고 내역을 가져 옵니다.");
		emptyOrderListPanel();
		var params = {};
		params.branchName = getBranchName();
		sendRequest("/jewsol/factory/releaseSheet/getOrderListByBranch.do", params, orderListByBranchResult, "GET");
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
	
	$("#insertReleaseBtn").click(function(){
		disableInsertReleaseBtn();
		emptySystemMessage();
		var orderSeqArr = getOrderSeqArr();
		if(orderSeqArr.length < 1){
			setSystemMessage("한개 이상의 주문을 선택 해야 합니다.");
			ableInsertReleaseBtn();
		}else{
			setSystemMessage("출고장 입력을 시작 합니다.");
			emptyOrderListPanel();
			emptyReleaseSheetListPanel();
			var url = "/jewsol/factory/releaseSheet/insertReleaseSheet.do";
			var params = {};
			params.orderSeqArr = orderSeqArr;
			params.branchName = getBranchName();
			params.releaseDate = getReleaseSheetDate();
			disableInsertReleaseBtn();
			setTimeout(sendRequest(url, params, afterInsertRelease, 'POST'), 300);
		}
	});
});

function afterInsertRelease(){
	if(request.readyState == 4 && request.status == 200){
		
		var results = request.responseText.split(",");
		setReleaseSheetListPanel(results[1]);
		setOrderListPanel(results[2]);
		ableInsertReleaseBtn();
		initOrderListEvent();
		initReleaseSheetListEvent();
		setSystemMessage("출고장 입력이 완료 되었습니다.");
		
	}
}

function orderListByBranchResult(){
	if(request.readyState == 4 && request.status == 200){
		setOrderListPanel(request.responseText);
		initOrderListEvent();
		setSystemMessage("주문 내역을 갱신 하였습니다.");
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


function initReleaseSheetListEvent(){
	var elems = $("#releaseSheetList tr");
	for(var i = 1; i < elems.length; i++){
		var elem = elems[i];
		$(elem).mouseenter(function(){
			$(this).css("background-color", "lightgray");
		}).mouseleave(function(){
			$(this).css("background-color", "");
		}).click(function(){
			var releaseSheetSeq = $(this).attr("id");
			window.open("/jewsol/factory/releaseSheet/printReleaseSheet.do?releaseSheetSeq="+releaseSheetSeq,"printReleaseSheetWindow","menubar=no, toolbar=no, location=no, left=10, top=3, scrollbars=yes, resizable=yes, width=900, height=700");
		});
		
	}
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

function disableInsertReleaseBtn(){
	$("#insertReleaseBtn").prop("disabled", true);
}

function ableInsertReleaseBtn(){
	$("#insertReleaseBtn").prop("disabled", false);
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

function getBranchName(){
	return $.trim($("#branchList option:selected").text());
}

function getReleaseSheetDate(){
	return $.trim($("#releaseSheetDate").val());
}

function getReleaseSheetNumber(){
	return $.trim($("releaseSheetNumber").val());
	
}

function setReleaseSheetNumber(data){
	return $("#releaseSheetNumber").val($.trim(data));
}