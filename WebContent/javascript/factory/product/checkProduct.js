function checkProductAveWeight(){
	emptySystemMessage();
	
	if(isNaN($("#productAveWeight").val())){
		setSystemMessage("제품 중량에 숫자를 입력해 주세요.");
		return false;
	}else if($("#productAveWeight").val() > 1000){
		setSystemMessage("제품중량에 1000보다 낮은 숫자를 입력해 주세요.");
		return false;
	}else{
		emptySystemMessage();
		return true;
	}
}

function checkProductCode(){
	emptySystemMessage();
	var productCode = document.getElementById("productCode").value;
	
	if(productCode.length == 0 || productCode == ""){
		setSystemMessage("제품번호를 입력해 주세요.");
		return false;
	}else{
		emptySystemMessage();
		return true;
	}
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#prev_img').removeAttr('src').attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function getProductCode(){
	return document.getElementById("productCode").value;
}

function getCategorySeq(){
	return document.getElementById("category").value;
}

function getTypeSeq(){
	return document.getElementById("type").value;
}