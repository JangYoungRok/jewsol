var KEYCODE_ARROW_UP = 38;
var KEYCODE_ARROW_DOWN = 40;
var KEYCODE_ENTER = 13;

$(document).ready(function(){
	getOrderListByBranchNameState3();
	
	$("#branchList").change(function(){
		getOrderListByBranchNameState3();
	});
	
	$("#insertReleaseBtn").click(function(){
		emptySystemMessage();
		if($(".inserted").length < 1){
			setSystemMessage("한개 이상의 제품에 중량을 입력 해야 합니다.");
		}else{
			setSystemMessage("출고 입력을 시작 합니다");
			$("#insertReleaseBtn").prop("disabled", true);
			var params = {};
			params.orderSeqArr = getOrderSeqArr();
			params.orderWeightArr = getOrderWeightArr();
			params.branchName = getBranchName();
			var url = "/jewsol/factory/release/insertRelease.do";
			setTimeout(sendRequest(url, params, afterInsertRelease, 'POST'), 300);
		}
		
	});
});
function afterInsertRelease(){
	if(request.readyState == 4 && request.status == 200){
		setOrderListPanel(request.responseText);
		initOrderListEvent();
		setSystemMessage("출고 입력을 완료 하였습니다.");
		$("#insertReleaseBtn").prop("disabled", false);
	}
}

function getOrderListByBranchNameState3(){
	setSystemMessage("지점에 해당하는 주문 내역을 가져 옵니다.");
	emptyOrderListPanel();
	var params = {};
	params.branchName = getBranchName();
	sendRequest("/jewsol/factory/release/getOrderListByBranchNameState3.do", params, orderListByBranchNameState3Result, 'POST');
}
function orderListByBranchNameState3Result(){
	if(request.readyState == 4 && request.status == 200){
		setOrderListPanel(request.responseText);
		initOrderListEvent();
		setSystemMessage("지점에 해당하는 주문 내역을 가져 왔습니다.");
	}
}

function getOrderSeqArr(){
	var orderSeqArr = [];
	var elems = $(".inserted");
	for(var i = 0; i < elems.length; i++){
		var elem = elems[i];
		orderSeqArr.push($(elem).attr("id"));
	}
	return orderSeqArr; 
}

function getOrderWeightArr(){
	var orderWeightArr = [];
	for(var i = 0; i < $(".inserted").length; i++){
		var elem = $(".inserted:eq("+i+") >:last > >");
		orderWeightArr.push($(elem).val());
	}
	return orderWeightArr;
}

function initOrderListEvent(){
	var elems = $(".orderWeight input");
	var length =  elems.length;
	for(var i = 0; i < length; i++){
		var elem = elems[i];
		var pos = "";
		var value = "";
		$(elem).keydown({index : i},function(e){
			pos = e.data.index;
			switch( event.keyCode ) {
			case KEYCODE_ENTER :
				emptySystemMessage();
				value = $(this).val();
				if(value.length == 0 || value == ""){
					if(pos >= (length - 1)){
						return false;
					}else{
						moveDown(elems, pos, length);
					}
				}else if(!$.isNumeric(value)){
					setSystemMessage("숫자를 입력해 주세요.");
				}else if(value <= 0){
					setSystemMessage("0보다 큰 숫자를 입력해 주세요.");
				}else{
					emptySystemMessage();
					$("#orderList tr:eq("+pos+")").attr("class","inserted");
					$(this).prop("readonly", true);
					if(pos >= (length - 1)){
						return false;
					}else{
						moveDown(elems, pos, length);
					}
				}
				break;
			case KEYCODE_ARROW_UP :
				if(pos < 1){
					return false;
				}else{
					moveUp(elems, pos);
				}	
				break;
			case KEYCODE_ARROW_DOWN :
				if(pos >= (length - 1)){
					return false;
				}else{
					moveDown(elems, pos, length);
				}
				break;
			}
		});
		
		$("#orderList tr:eq("+i+")").click({index : i},function(e){
			pos = e.data.index;
			
			if($("#orderList tr:eq("+pos+")").attr("class") == "inserted"){
				$("#orderList tr:eq("+pos+")").removeClass();
				$("#orderList tr:eq("+pos+") >:last > >").prop("readonly", false);
				$("#orderList tr:eq("+pos+") >:last > >").val("");
				$("#orderList tr:eq("+pos+") >:last > >").focus();
			}else{
				
				
			}
		});
	}
}



function moveUp(elems, pos){
		pos = pos - 1;
		while($("#orderList tr:eq("+pos+")").attr("class") == "inserted"){
			if(pos < 1){
				return false;
			}else{
				pos = pos - 1;
			}
		}
		elems[pos].focus();
}

function moveDown(elems, pos, length){
	pos = pos + 1;
	while($("#orderList tr:eq("+pos+")").attr("class") == "inserted"){
		if(pos >= (length - 1)){
			return false;
		}else{
			pos = pos + 1;
		}
	}
	elems[pos].focus();
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