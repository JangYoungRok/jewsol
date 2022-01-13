var KEYCODE_ARROW_UP = 38;
var KEYCODE_ARROW_DOWN = 40;
var KEYCODE_BACKSPACE = 8;
var KEYCODE_ENTER = 13;
var OptionReady = false;
var SelectedOrderIndex = -1;

var OrderParams = {
	orderDate : "",
	originalSheetNumber : 0,
	customerSeq : 0,
	storeProductSeq : 0,
	orderOptionName : "",
	optionNumber : 0,
	orderMemberSeq : 0,
	kSeq : 0,
	orderK : "14",
	orderSubColor : "골드",
	orderMainColor : "골드",
	orderSize : "",
	orderEtc : "",
	orderHurry : "F",
	orderHalf : "F",
	plateSeq : 0,
	czSeqArr : "",
	czQtyArr : "",
	czNameArr : "",
	czSizeArr : "",
	stoneSeqArr :"",
	stoneQtyArr : "",
	stoneNameArr : "",
	stoneSizeArr : "",
	partSeqArr : "",
	partNameArr : "",
	addSeqArr : "",
	addNameArr : ""
};


$(document).ready(function(){
	initK();
	initOrderMember();
	initMainColor();
	initSubColor();
	
	CustomerListInterface.input = $("#customer");
	CustomerListInterface.div = $("#customerListDiv");
	
	StoreProductListInterface.input = $("#storeProduct");
	StoreProductListInterface.div = $("#storeProductListDiv");
	$("#orderDate").prop("readonly",true);
	$("#insertOrderBtn").prop("disabled",true);
	$("#updateOrderBtn").prop("disabled",true);
	$("#cancleUpdateOrderBtn").prop("disabled",true);
	$("#deleteOrderBtn").prop("disabled",true);
	$("#registerOrderBtn").prop("disabled",true);
	
	$("#originalSheet").blur(function(){
		emptySystemMessage();
		var originalSheetNumber = $(this).val();
		if(!validateOriginalSheetNmber(originalSheetNumber)){
			setSystemMessage("원장 번호는 0보다 큰 정수 이어야 합니다.");
			$(this).focus();
		}else{
			setSystemMessage("원장 번호를 체크 합니다.");
			var params = {};
			params.originalSheetNumber = originalSheetNumber;
			sendRequest("/jewsol/store/originalSheet/checkOriginalSheetNumber.do", params, atferOriginalSheetNumber, "GET");
		}
	});
	
	$("#originalSheet").keydown(function(event){
		switch( event.keyCode ) {
		case KEYCODE_ENTER :
			$("#orderMember").focus();
			break;
		}
	});
	
	$("#orderMember").keydown(function(event){
		switch( event.keyCode ) {
		case KEYCODE_ENTER :
			$("#customer").focus();
			break;
		}
	});
	
	$("#customer").keydown(function(event){
		return CustomerListInterface.keyDown(event);
	});
	
	$("#customer").blur(function(){
		CustomerListInterface.blur();
	});
	
	$("#storeProduct").keydown(function(event){
		return StoreProductListInterface.keyDown(event);
	});
	
	$("#storeProduct").blur(function(){
		StoreProductListInterface.blur();
	});
	
	$("#optionList").focus(function(){
		if(!OptionReady){
			$("#storeProduct").focus();
		}
	});
	
	$("#optionList").change(function(){
		setSystemMessage("상세옵션을 가져 옵니다.");
		$("#orderOptionPanel").html("");
		var params = {};
		var url = "";
		var optionNumber = $.trim($("#optionList option:selected").text().split("--")[0]);
		
		if(optionNumber == 0){
			url = "/jewsol/store/order/getOrderOption.do";
			params.orderOptionSeq = $("#optionList").val();
		}else{
			params.optionNumber = optionNumber;
			params.storeProductSeq = OrderParams.storeProductSeq;
			url = "/jewsol/store/option/getOptionForOrder.do";
		}
		sendRequest(url, params, afterChangeOptionForOrder, "GET");
	});
	
	$("#optionList").keydown(function(event){
		if(event.keyCode == KEYCODE_ENTER){
			$("#k").focus();
			return false;
		}
	});
	
	$("#k").keydown(function(event){
		if(event.keyCode == KEYCODE_ENTER){
			$("#mainColor").focus();
			return false;
		}
		
	});
	
	$("#mainColor").keydown(function(event){
		if(event.keyCode == KEYCODE_ENTER){
			$("#subColor").focus();
			return false;
		}
		
	});
	
	$("#mainColor").blur(function(){
		var mainColor = $("#mainColor option:selected").text();
		var subColors = $("#subColor option");
		if(mainColor == "화이트" || mainColor == "화이트" || mainColor == "삼색"){
			$("#plate option:eq(1)").prop("selected", true);
		}else{
			$("#plate option:eq(0)").prop("selected", true);
		}
		for(var i = 0; i < subColors.length; i++){
			if($("#subColor option:eq("+i+")").text() == mainColor){
				$("#subColor option:eq("+i+")").prop("selected", true);
			}
		}
	});
	
	$("#subColor").keydown(function(event){
		if(event.keyCode == KEYCODE_ENTER){
			$("#orderSize").focus();
			return false;
		}
		
	});
	
	$("#subColor").blur(function(){
		if($("#mainColor option:selected").text() == "화이트" || $("#subColor option:selected").text() == "화이트" || $("#subColor option:selected").text() == "삼색"){
			$("#plate option:eq(1)").prop("selected", true);
		}else{
			$("#plate option:eq(0)").prop("selected", true);
		}
	});	
	
	$("#orderSize").keydown(function(event){
		if(event.keyCode == KEYCODE_ENTER){
			emptySystemMessage();
			if(validateSize($("#orderSize").val())){
				setSystemMessage("사이즈값을 확인해 주세요.");
				$("#orderSize").focus();
			}else{
				$("#orderEtc").focus();
				
			}
			return false;
		}
		
	});
	
	$("#orderEtc").keydown(function(event){
		if(event.keyCode == KEYCODE_ENTER){
			$("#orderHurry").focus();
			return false;
		}
		
	});
	
	$("#orderHurry").keydown(function(event){
		if(event.keyCode == KEYCODE_ENTER){
			$("#insertOrderBtn").focus();
			return false;
		}
		
	});
	
	$("#insertOrderBtn").click(function(){
		emptySystemMessage();
		var check = (checkQty("czQty") && checkQty("stoneQty"));
		
		if(check){
			if($("#optionList option").length == 0){
				setSystemMessage("옵션은 선택 되어야 합니다.");
				return;
			}else if(!validateOriginalSheetNmber($("#originalSheet").val())){
				setSystemMessage("원장 번호를 확인해 주세요.");
				return;
			}else if(OrderParams.customerSeq == 0){
				setSystemMessage("거래처를 선택 해야 합니다.");
				return;
			}else if(OrderParams.storeProductSeq == 0){
				setSystemMessage("제품번호를 입력 해야 합니다.");
				return;
			}
			setSystemMessage("주문을 입력 합니다.");
			getParamsOptionDetail(OrderParams);
			getOrderParams(OrderParams);
			
			$("#insertOrderBtn").prop("disabled", true);
			setTimeout(sendRequest("/jewsol/store/order/insert/insertOrder.do", OrderParams, afterInsertOrder, "POST"), 300);
			
		}
		
	});
	
	$("#updateOrderBtn").click(function(){
		emptySystemMessage();
		var check = (checkQty("czQty") && checkQty("stoneQty"));
		
		if(check){
			if($("#optionList option").length == 0){
				setSystemMessage("옵션은 선택 되어야 합니다.");
				return;
			}else if(!validateOriginalSheetNmber($("#originalSheet").val())){
				setSystemMessage("원장 번호를 확인해 주세요.");
				return;
			}else if(OrderParams.customerSeq == 0){
				setSystemMessage("거래처를 선택 해야 합니다.");
				return;
			}else if(OrderParams.storeProductSeq == 0){
				setSystemMessage("제품번호를 입력 해야 합니다.");
				return;
			}
			setSystemMessage("주문을 수정 합니다.");
			getParamsOptionDetail(OrderParams);
			getOrderParams(OrderParams);
			OrderParams.orderSeq = $("#orderList tr:eq("+SelectedOrderIndex+")").attr("id");
			
			sendRequest("/jewsol/store/order/update/updateOrder.do", OrderParams, afterUpdateOrder, "POST");
		}
	});
	$("#cancleUpdateOrderBtn").click(function(){
		cancleUpdateOrder();
		setSystemMessage("수정이 취소 되었습니다.");
	});
	
	$("#deleteOrderBtn").click(function(){
		var params = {};
		params.orderSeq = $("#orderList tr:eq("+SelectedOrderIndex+")").attr("id");
		sendRequest("/jewsol/store/order/update/deleteOrder.do", params, afterDeleteOrder, "POST");
	});
	
	$("#registerOrderBtn").click(function(){
		emptySystemMessage();
		if($("#orderList tr").length > 0){
			if(confirm("원장 마감을 하시겠습니까?")){
				setSystemMessage("주문 마감을 시작 합니다.");
				var params = {};
				params.orderDate = $("#orderDate").val();
				params.originalSheetNumber = $("#originalSheet").val();
				sendRequest("/jewsol/store/order/update/registerOrder.do", params, afterRegisterOrder, "POST");
			}
		}else{
			setSystemMessage("최소한 한개 이상의 주문은 입력 해야 합니다.");
		}
		
		
	});
	
});//end of document ready

function afterRegisterOrder(){
	if(request.readyState == 4 && request.status == 200){
		$("#orderList tr").unbind();
		setSystemMessage("주문 마감을 완료 하였습니다.");
		$("#insertOrderBtn").prop("disabled", true);
		$("#registerOrderBtn").prop("disabled", true);
		
	}
}

function afterUpdateOrder(){
	if(request.readyState == 4 && request.status == 200){
		cancleUpdateOrder();
		setSystemMessage("주문 수정이 완료 되었습니다.");
		OptionReady = false;
	}
}

function afterDeleteOrder(){
	if(request.readyState == 4 && request.status == 200){
cancleUpdateOrder();
		setSystemMessage("주문이 삭제 되었습니다.");
		OptionReady = false;
	}
}

function atferOriginalSheetNumber(){
	if(request.readyState == 4 && request.status == 200){
		var originalSheetState = $.trim(request.responseText);
		
		if(originalSheetState == 1){
			$("#insertOrderBtn").prop("disabled", false);
			$("#registerOrderBtn").prop("disabled", false);
			$("#orderListPanel").html("");
			setSystemMessage("원장번호가 등록 되었습니다. 주문을 입력해 주세요.");
		}else if(originalSheetState == 2){
			$("#insertOrderBtn").prop("disabled", true);
			$("#registerOrderBtn").prop("disabled", true);
			$("#originalSheet").focus();
			$("#orderListPanel").html("");
			setSystemMessage("원장번호 " + $("#originalSheet").val() + "는 이미 다른 사용자가 사용하는 번호 입니다.");
			$("#originalSheet").val("");
		}else if(originalSheetState == 3){
			$("#insertOrderBtn").prop("disabled", false);
			$("#registerOrderBtn").prop("disabled", false);
			refreshOrderList();
			setSystemMessage("원장번호의 내역을 가져 왔습니다. 주문을 입력해 주세요.");
		}else if(originalSheetState == 4){
			$("#insertOrderBtn").prop("disabled", true);
			$("#registerOrderBtn").prop("disabled", true);
			getRegisterdOrderList();
			$("#orderList tr").unbind();
			setSystemMessage("입력 완료된 원장 번호 입니다.");
			
		}
	}
}
function getRegisterdOrderList(){
	$("#orderListPanel").html("");
	var params = {};
	params.orderDate = $("#orderDate").val();
	params.originalSheetNumber = $("#originalSheet").val();
	sendRequest("/jewsol/store/order/getRegisterdOrderList.do", params, getRegisteredOrderListResult, "GET");
}

function getRegisteredOrderListResult(){
	if(request.readyState == 4 && request.status == 200){
		$("#orderListPanel").html(request.responseText);
	}
}

function refreshOrderList(){
	$("#orderListPanel").html("");
	var params = {};
	params.orderDate = $("#orderDate").val();
	params.originalSheetNumber = $("#originalSheet").val();
	sendRequest("/jewsol/store/order/refreshTempOrderList.do", params, afterRefreshOrderList, "GET");
}
function afterRefreshOrderList(){
	if(request.readyState == 4 && request.status == 200){
		$("#orderListPanel").html(request.responseText);
		initOrderListEvent();
		SelectedOrderIndex = -1;
	}
}

function afterChangeOptionForOrder(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		setOrderOptionPanel(results[1]);
		setSystemMessage("상세옵션 가져오기를 완료 하였습니다.");
		readonlyOptionDetail();
	}
}

function setOptionList(optionList){
	$("#optionList").html($.trim(optionList));
}

function setOrderOptionPanel(orderOption){
	$("#orderOptionPanel").html($.trim(orderOption));
	initCzSizeEvent();
	initStoneSizeEvent();
	initPartAttributeEvent();
	readonlyOptionDetail();
}

function getOptionForOrder(storeProductSeq){
	$("#orderOptionPanel").html("");
	$("#optionList").html("");
	OptionReady = false;
	var params = {};
	params.optionNumber = 1;
	params.storeProductSeq = OrderParams.storeProductSeq;

	sendRequest("/jewsol/store/option/getOptionForOrder.do", params, afterGetOptionForOrder, "GET");
}
function afterGetOptionForOrder(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		setOptionList(results[0]);
		setOrderOptionPanel(results[1]);
		OptionReady = true;
		
	}
}

function getOrderParams(params){
	params.orderDate = $("#orderDate").val();
	params.originalSheetNumber = $("#originalSheet").val();
	params.orderMemberSeq = $("#orderMember option:selected").val();
	var orderOptionName = $.trim($("#optionList option:selected").text().split("--")[1]);
	if(orderOptionName == "변형"){
		params.orderOptionName = "";
	}else{
		params.orderOptionName = orderOptionName;
	}
	params.optionNumber = $.trim($("#optionList option:selected").text().split("--")[0]);
	params.kSeq = $("#k option:selected").val();
	params.orderK = $("#k option:selected").text();
	params.orderMainColor = $("#mainColor option:selected").text();
	params.orderSubColor = $("#subColor option:selected").text();
	params.orderSize = $("#orderSize").val();
	params.orderEtc = $("#orderEtc").val();
	var orderHurry = "F";
	var orderHalf = "F";
	if($("#orderHurry").is(":checked")){
		orderHurry = "T";
	}
	if($("#orderHalf").is(":checked")){
		orderHalf = "T";
	}
	params.orderHurry = orderHurry;
	params.orderHalf = orderHalf;
	params.plateSeq = $("#plate option:selected").val();
}

function cancleUpdateOrder(){
	afterInsertOrderParams(OrderParams);
	afterInsertOrderPanel();
	$("#customer").val("");
	StoreProductListInterface.selected = false;
	StoreProductListInterface.lastKeyword = "";
	StoreProductListInterface.sendKeyword = "";
	CustomerListInterface.selected = false;
	StoreProductListInterface.lastKeyword = "";
	StoreProductListInterface.sendKeyword = "";
	refreshOrderList();
	$("#originalSheet").prop("disabled",false);
	$("#insertOrderBtn").prop("disabled",false);
	$("#updateOrderBtn").prop("disabled",true);
	$("#cancleUpdateOrderBtn").prop("disabled",true);
	$("#deleteOrderBtn").prop("disabled",true);
	$("#registerOrderBtn").prop("disabled",false);
	OptionReady = false;
}

function afterInsertOrder(){
	if(request.readyState == 4 && request.status == 200){
		$("#insertOrderBtn").prop("disabled", false);
		afterInsertOrderParams(OrderParams);
		afterInsertOrderPanel();
		StoreProductListInterface.selected = false;
		StoreProductListInterface.lastKeyword = "";
		StoreProductListInterface.sendKeyword = "";
		refreshOrderList();
		$("#customer").focus();
	}
}
function afterInsertOrderPanel(){
	$("#storeProduct").val("");
	$("#optionList").html("");
	OptionReady = false;
	$("#orderOptionPanel").html("");
	initK();
	initMainColor();
	initSubColor();
	$("#orderSize").val("");
	$("#orderEtc").val("");
	$("#orderHurry").prop("checked", false);
	$("#orderHalf").prop("checked", false);
}
function afterInsertOrderParams(params){
	params.storeProductSeq = 0;
	params.orderOptionName = "";
	params.orderMemberSeq = 0;
	params.kSeq = 0;
	params.orderK = "14";
	params.orderSubColor = "골드";
	params.orderMainColor = "골드";
	params.orderSize = "";
	params.orderEtc = "";
	params.orderHurry = "F";
	params.orderHalf = "F";
	params.plateSeq = 0;
	params.czSeqArr = "";
	params.czQtyArr = "";
	params.czNameArr = "";
	params.czSizeArr = "";
	params.stoneSeqArr = "";
	params.stoneQtyArr = "";
	params.stoneNameArr = "";
	params.stoneSizeArr = "";
	params.partSeqArr = "";
	params.partNameArr = "";
	params.addSeqArr = "";
	params.addNameArr = "";
}

function initOrderListEvent(){
	$("#orderList tr").mouseenter(function(){
		$(this).css('background-color', 'lightgray');
		
	}).mouseleave(function(){
		$(this).css('background-color', '');
		
	});
	
	$("#orderList tr").click(function(){
		setSystemMessage("수정 할 주문 데이터를 가져 옵니다.");
		$("#insertOrderBtn").prop("disabled",true);
		$("#registerOrderBtn").prop("disabled",true);
		$("#updateOrderBtn").prop("disabled",false);
		$("#cancleUpdateOrderBtn").prop("disabled",false);
		$("#deleteOrderBtn").prop("disabled",false);
		if(SelectedOrderIndex > -1){
			clearBorder($("#orderList tr:eq("+SelectedOrderIndex+")"));
		}
		
		SelectedOrderIndex = $("#orderList tr").index(this);
		redBorder($(this));
		
		var params = {};
		params.orderSeq = $(this).attr("id");
		sendRequest("/jewsol/store/order/update/getUpdateOrder.do", params, afterGetUpdateOrder, "GET");
		$("#originalSheet").prop("disabled",true);
	});
}

function afterGetUpdateOrder(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		setOrderParams(results);
		setOrderInsertPanel(results);
		setSystemMessage("수정 할 주문 데이터를 불러 왔습니다.");
		StoreProductListInterface.afterGetUpdateOrder();
		CustomerListInterface.afterGetUpdateOrder();
		OptionReady = true;
	}
}
function setOrderParams(results){
	OrderParams.customerSeq = results[1];
	OrderParams.storeProductSeq = results[3];
}
function setOrderInsertPanel(results){
	$("#customer").val($.trim(results[0]));
	$("#storeProduct").val($.trim(results[2]));
	$("#prev_img").attr("src","/jewsol/image/"+$.trim(results[4]));
	setOptionList(results[5]);
	setOrderOptionPanel(results[6]);
	valueSelector("k",results[7]);
	textSelector("mainColor", results[9]);
	textSelector("subColor", results[10]);
	$("#orderSize").val($.trim(results[11]));
	$("#orderEtc").val($.trim(results[12]));
	checker("orderHurry", results[13]);
	valueSelector("plate",results[14]);
	valueSelector("orderMember",results[15]);
	checker("orderHalf", results[16]);
	
}

function readonlyOptionDetail(){
	var orderOptionNumber = $.trim($("#optionList option:selected").text().split("--")[0]);
	if(orderOptionNumber < 9){
		for(var i = 0; i < $("[name=czSize]").length; i++){
			$("[name=czSize]:eq("+i+")").prop("readonly", true);
			$("[name=cz]:eq("+i+")").prop("readonly", true);
			$("[name=czQty]:eq("+i+")").prop("readonly", true);
		}
		for(var i = 0; i < $("[name=stoneSize]").length; i++){
			$("[name=stoneSize]:eq("+i+")").prop("readonly", true);
			$("[name=stone]:eq("+i+")").prop("readonly", true);		
			$("[name=stoneQty]:eq("+i+")").prop("readonly", true);
		}
	}
}

function initK(){
	$("#k option:eq(0)").prop("selected",true);
}

function initOrderMember(){
	$("#orderMember option:eq(0)").prop("selected",true);
}

function initMainColor(){
	$("#mainColor option:eq(0)").prop("selected",true);
}

function initSubColor(){
	$("#subColor option:eq(0)").prop("selected",true);
}

function initPalte(){
	$("#plate option:eq(0)").prop("selected",true);
}

