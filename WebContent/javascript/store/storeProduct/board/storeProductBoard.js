window.onload = initStoreProductBoard;

function initStoreProductBoard(){
	//이벤트 할당
	//제품검색버튼
	$("#searchProductBtn").click(searchProduct);
	//productBoard
	initProductBoardEvent();
	//pnb
	initPnbEvent();
	
}

function initPnbEvent(){
	var productBoardSpanList = $("#pnb").find("span");
	
	for(var i = 0; i < productBoardSpanList.length; i ++){
		var elem = $(productBoardSpanList)[i];
		
		if($(elem).attr("id") != ""){
			$(elem).click(function(){
				getProductPage($(this).attr("id"));
			});
			
			$(elem).mouseenter(function(){
				$(this).css('background-color', 'lightgray');
				
			}).mouseleave(function(){
				$(this).css('background-color', '');
				
			});
		}
	}
	
}

function getProductPage(selectedPage){
	var params = {};
	
	params.category = $("#retrievedCategory").text();
	params.productCode = $("#retrievedProductCode").text();
	params.selectedPage = selectedPage;
	
	sendRequest("/jewsol/store/storeProduct/board/getProductPage.do", params, refreshStoreProductBoard, "GET");

	
}

function initProductBoardEvent(){
	var productBoardSpanList = $("#productBoard").find("span");
	
	for(var i = 0; i < productBoardSpanList.length; i ++){
		var elem = $(productBoardSpanList)[i];
		
		if($(elem).attr("id") != ""){
			$(elem).click(function(){
				getProductDetail($(this).attr("id"));
			});
			
			$(elem).mouseenter(function(){
				$(this).addClass("hover");
				
			}).mouseleave(function(){
				$(this).removeClass();
				
			});
		}
	}
}

function getProductDetail(storeProductSeq){
	window.open("/jewsol/store/storeProduct/board/storeProductDetail.do?storeProductSeq="+storeProductSeq,"storeProductDetailWindow","menubar=no, toolbar=no, location=no, left=10, top=3, resizable=yes, width=1000, height=800");
}

function searchProduct(){
	var params = {};
	
	params.categorySeq = $("#category").val();
	params.productCode = $("#productCode").val();
	if($(".selectedPage").attr("id") == undefined){
		params.selectedPage = 1;
	}else{
		params.selectedPage = $(".selectedPage").attr("id");
	}
	
	
	sendRequest("/jewsol/store/storeProduct/board/searchStoreProduct.do", params, refreshStoreProductBoard, "GET");

	
}

function refreshStoreProductBoard(){
	if(request.readyState == 4 && request.status == 200){
		
		var resultHTML = request.responseText.split(",");
		var category = $.trim(resultHTML[0]);
		var productCode = $.trim(resultHTML[1]);
		
		$("#retrievedCategory").html(category);
		$("#retrievedProductCode").html(productCode);
		refreshElem("#productBoard", $.trim(resultHTML[2]));
		refreshElem("#pnb", $.trim(resultHTML[3]));
		initProductBoardEvent();
		initPnbEvent();
	}
}