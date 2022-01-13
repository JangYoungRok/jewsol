$(document).ready(function(){
	$("#productImage").change(function(){
		readURL($(this)[0]);
	});
	
	$("#checkOverLapStoreProductBtn").click(function(){
		emptySystemMessage();
		$("#insertStoreProduct").attr("disabled",true);
		if(checkProductCode()){
			var params = {};
			
			getParamsProduct(params);
			
			sendRequest("/jewsol/store/storeProduct/checkOverLapStoreProductName.do", params, resultOverLapStoreProductName, "GET");
		}
		
	});
	
	$("#storeProductOriginalName").blur(function(){
		emptySystemMessage();
		if(nullCheckValue($("#storeProductOriginalName").val())){
			$(this).addClass("checked");
			setSystemMessage("제품번호를 입력해 주세요.");
			$("#storeProductOriginalName").focus();
		}
	});
	
	$("#insertStoreProduct").click(function(){
		$(this).attr("disabled", true);
		
		if($("#productAveWeight").val().length == 0 || $("#productAveWeight").val() == ""){
			$("#productAveWeight").val("0.0");
		}
		
		if($("#storeProductPrice").val().length == 0 || $("#storeProductPrice").val() == ""){
			$("#storeProductPrice").val("0");
		}
		
		insertStoreProductForm.submit();
		
	});
	
});

function resultOverLapStoreProductName(){
	if(request.readyState == 4 && request.status == 200){
		var resultOverLap = request.responseText;
		var systemMessage = "";
		if(resultOverLap == 1){
			systemMessage = "이미 등록된 제품 입니다.";
		}else if(resultOverLap == 0){
			$("#insertStoreProduct").attr("disabled",false);
			systemMessage = "등록 가능한 제품 입니다.";
			$("#storeProductOriginalName").focus();
		}
		setSystemMessage(systemMessage);
	}
}
