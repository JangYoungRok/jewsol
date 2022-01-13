$(document).ready(function(){
	$("#searchStoreProductBtn").click(function(){
		setSystemMessage("검색중 입니다.");
		initProductPanel();
		
		if(checkProductCode()){
			var params = {};
			getParamsProduct(params);
			sendRequest("/jewsol/store/storeProduct/searchStoreProduct.do", params, searchResultStoreProduct, "GET");
		}
	});
	
	initProcutPanelEvent();
});

function initProductPanel(){
	disableButtons("productPanel");
	var inputElems = $("#productPanel input[type=text]");
	for(var i = 0; i < inputElems.length; i++){
		var elem = inputElems[i];
		$(elem).val("");
	}
	$("#productImage").val("");
	$("#prev_img").removeAttr('src').attr("src","/jewsol/image/noPic.jpg");
}

function searchResultStoreProduct(){
	if(request.readyState == 4 && request.status == 200){
		
		var result = request.responseText.split(",");
		var systemMessage = "";
		if(result[0] == 2){
			systemMessage = "없는 제품 입니다.";
		}else if(result[0] == 3){
			systemMessage = "이용 중지 중인 제품 입니다.";
		}else if(result[0] == 1){
			$("#productPanel").html(result[1]);	
			initProcutPanelEvent();
		}else if(result[0] == 4){
			systemMessage = "이미 등록된 제품 입니다.";
		}
		setSystemMessage(systemMessage);
	}
}

function initProcutPanelEvent(){
	$("#productImage").change(function(){
		readURL($(this)[0]);
	});
	
	$("#notInUseStoreProductBtn").click(function(){
		var params = {};
		getParamsProduct(params);
		sendRequest("/jewsol/store/storeProduct/notInUseStoreProduct.do", params, afteNotInUseStoreProduct, "GET");
		
	});
	
	$("#updateStoreProductBtn").click(function(){
		
		if(nullCheckValue($("#storeProductOriginalName").val())){
			setSystemMessage("제품번호를 입력해 주세요.");
		}else{
			setSystemMessage("수정 중 입니다.");
			$(this).attr("disabled", true);
			$("#notInUseStoreProductBtn").attr("disabled", true);
			
			if($("#productAveWeight").val().length == 0 || $("#productAveWeight").val() == ""){
				$("#productAveWeight").val("0.0");
			}
			
			if($("#storeProductPrice").val().length == 0 || $("#storeProductPrice").val() == ""){
				$("#storeProductPrice").val("0");
			}
			
			updateStoreProductForm.submit();
		}
		
	});
}

function afteNotInUseStoreProduct(){
	if(request.readyState == 4 && request.status == 200){
		var systemMessage = request.responseText;
		setSystemMessage(systemMessage);
		initProductPanel();
	}
}

