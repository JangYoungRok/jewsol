$(document).ready(function(){
	$("#cancleReleaseBtn").click(function(){
		emptySystemMessage();
		if($(".selected").length < 1){
			setSystemMessage("한개 이상의 제품을 선택 해야 합니다.");
		}else if(confirm("선택한 주문의 출고 선택을 취소 하시겠습니까?")){
			setSystemMessage("출고 취소를 시작합니다.");
			var params = {};
			var url = "/jewsol/factory/release/cancleRelease.do";
			params.orderSeqArr = getOrderSeqArr();
			$("#cancleReleaseBtn").prop("disabled", true);
			emptyOrderList();
			setTimeout(sendRequest(url, params, afterCancleRelease, 'POST'), 300);
		}
	});
	
	initOrderListEvent();
	
});

function initOrderListEvent(){
	var length =  $("#orderList tr").length;
	for(var i = 0; i < length; i++){
		$("#orderList tr:eq("+i+")").click({index : i},function(e){
			pos = e.data.index;
			
			if($("#orderList tr:eq("+pos+")").attr("class") == ""){
				$("#orderList tr:eq("+pos+")").addClass("selected");
			}else if($("#orderList tr:eq("+pos+")").attr("class") == "selected"){
				$("#orderList tr:eq("+pos+")").removeClass();
			}
		});
	}
}

function afterCancleRelease(){
	if(request.readyState == 4 && request.status == 200){
		setOrderList(request.responseText);
		$("#cancleReleaseBtn").prop("disabled", false);
		setSystemMessage("출고 취소를 완료 하였습니다.");
		initOrderListEvent();
	}
}

function setOrderList(data){
	$("#orderListPanel").html($.trim(data));
}

function emptyOrderList(){
	$("#orderListPanel").html("");
}

function getOrderSeqArr(){
	var orderSeqArr = [];
	var elems = $(".selected");
	for(var i = 0; i < elems.length; i++){
		var elem = elems[i];
		orderSeqArr.push($(elem).attr("id"));
	}
	return orderSeqArr; 
}
	
