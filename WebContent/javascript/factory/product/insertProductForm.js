window.onload = initInsertProductForm;

function initInsertProductForm(){
	document.getElementById("productImage").onchange = function(){
		readURL(document.getElementById("productImage"));
		
	};
	document.getElementById("category").onchange = checkProductName;
	document.getElementById("type").onchange = checkProductName;
	document.getElementById("productCode").onblur = checkProductName;
	document.getElementById("checkOverLapBtn").onclick = checkOverLapProductName;
	
	$("#insertProductBtn").click(function(){
		if(checkProductAveWeight()){
			document.insertProductForm.submit();
		}
	});
	
}

function getProductAveWeight(){
	return document.getElementById("productAveWeight").value;
}

		