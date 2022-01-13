window.onload = initUpdateProduct;

function initUpdateProduct(){
	$("#updateProduct").click(function(){
		if(checkProductAveWeight()){
			document.updateProductForm.submit();
		}
		
	});
	
	/*$("#productImage").change(function(){
		readURL($("#productImage")[0]);
		
	});*/
	
	$("#searchProduct").click(function(){
		if(checkProductCode()){
			disableButtons("triggerPanel");
			initProductInfo();
			checkGetProductDetail('X');
			emptySystemMessage();
			emptyProductOption();
			
			var params = {};
			params = getParamsProduct(params);
			
			sendRequest("/jewsol/factory/product/update/searchProduct.do", params, refreshUpdateProduct, "GET");
		}
	});
	
	$("input[name=getProductOption]").change(function(){
		var value = $(this).val();
		var params = {};
		params = getParamsProduct(params);
		if(value == "O"){
			sendRequest("/jewsol/factory/product/update/getUpdateProductOption.do", params, refreshProductOption, "GET");
		}else if(value == "X"){
			sendRequest("/jewsol/factory/product/update/getProductOption.do", params, refreshProductOption, "GET");
		}
	});
	
	$("#notInUseProduct").click(function(){
		var params = {};
		params = getParamsProduct(params);
		sendRequest("/jewsol/factory/product/update/notInUseProduct.do", params, restartUpdateProduct, "POST");
	});
}

function restartUpdateProduct(){
	if(request.readyState == 4 && request.status == 200){
		initSearchProduct();
		initProductInfo();
		checkGetProductDetail('X');
		disableButtons("triggerPanel");
		emptyProductOption();
		setSystemMessage($.trim(request.responseText) + "가 이용 중지 되었습니다.");
	}
	
}



function refreshProductOption(){
	if(request.readyState == 4 && request.status == 200){
		$("#productOptionPanel").html(request.responseText);
	}
	
}

function checkGetProductDetail(value){
	$("input[name='getProductOption']").attr("checked",false);
	$("input[name='getProductOption'][value='"+value+"']").attr("checked",true);
	$("input[name='getProductOption'][value='"+value+"']").prop("checked",true);
	
}

function emptyProductOption(){
	$("#productOptionPanel div").empty();
}

function refreshUpdateProduct(){
	if(request.readyState == 4 && request.status == 200){
		var resultHTML = request.responseText.split(",");
		var searchProductState = resultHTML[0];
		var systemMessage = resultHTML[1];
		var productImage = "";
		var productName = "";
		var productAveWeight = "";
		var productOption = "";
		
		if(searchProductState == 1){
			productImage = $.trim(resultHTML[2]);
			productName = $.trim(resultHTML[3]);
			productAveWeight = $.trim(resultHTML[4]);
			productOption = resultHTML[5];
			
			setProduct(productImage, productName, productAveWeight);
			$("#productAveWeight").attr("readonly",false);
			$("#productOptionPanel").html(productOption);
			ableButtons("triggerPanel");
		}
		
		setSystemMessage(systemMessage);
		
	}
}

