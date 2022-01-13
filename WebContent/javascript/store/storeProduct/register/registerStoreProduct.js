window.onload = initRegisterStoreProduct;

function initRegisterStoreProduct(){
	$("#checkProduct").click(function(){
		if(checkProductCode()){
			$("#optionDetailPanel div").html("");
			disableButtons("optionPanel");
			initProductInfo();
			setSystemMessage("검색중 입니다.");
			var params = {};
			params = getParamsProduct(params);
			sendRequest("/jewsol/store/storeProduct/checkStoreProduct.do", params, afterCheckStoreProduct, "GET");
		}
	});
	
	$("#registerStoreProduct").click(function(){
		emptySystemMessage();
		var validate = false;
		validate = (checkStoreProductPrice() && checkQty("czQty") && checkQty("stoneQty"));
		
		if(validate){
			setSystemMessage("제품을 등록 중 입니다.");
			var params = {};
			params = getParamsProduct(params);
			params = getParamsOption(params);
			params = getParamsOptionDetail(params);
			
			sendRequest("/jewsol/store/storeProduct/registerStoreProduct.do", params, restartRegisterStoreProduct, "GET");
			
		}
	});
}

function restartRegisterStoreProduct(){
	if(request.readyState == 4 && request.status == 200){
		var productName = $.trim(request.responseText);
		setSystemMessage(productName + "이 등록 되었습니다.");
		initSearchProduct();
		$("#optionName").val("");
		$("#storeProductPrice").val("");
		disableButtons("optionPanel");
		$("#optionDetailPanel div").html("");
		initProductInfo();
	}
}

function getParamsOption(params){
	params.optionName = $("#optionName").val();
	params.storeProductPrice = $("#storeProductPrice").val();
	
	return params;
}

function checkStoreProductPrice(){
	$("#storeProductPrice").css("border","");
	var value = $("#storeProductPrice").val();
	if(!validateQty(value)){
		setSystemMessage("제품 기본 공임에 숫자를 입력해 주세요.");
		redBorder($("#storeProductPrice"));
		return false;
	}else{
		return true;
	}
}

function afterCheckStoreProduct(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		var searchStoreProductState = results[0];
		var systemMessage = results[1];
		
		if(searchStoreProductState == 1){
			var productImage = $.trim(results[2]);
			var productName = $.trim(results[3]);
			var productAveWeight = $.trim(results[4]);
			
			setProduct(productImage, productName, productAveWeight);
			$("#optionDetailPanel").html(results[5]);
			ableButtons("optionPanel");
			initCzSizeEvent();
			initStoneSizeEvent();
			initPartAttributeEvent();
		}
		setSystemMessage(systemMessage);
	}
}
