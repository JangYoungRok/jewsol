function validateStringValueNull(elem){
	if($(elem) != null){
		var stringValue = $(elem).val();
		
		if(stringValue == "" || stringValue.length == 0){
			return false;
		}else{
			return true;
		}
	}	
}
function validateIsNumber(elem){
	if($(elem) != null){
		var numberValue = $(elem).val();
		return !isNaN(numberValue);
	}
}

function redBorder(elem){
	$(elem).css('border', '1px solid red');
}

function clearBorder(elem){
	$(elem).css('border', '');
}

function validateQty(value){
	if($.isNumeric(value) && value >= 0){
		return true;
	}else{
		return false;
	}
}

function validateOriginalSheetNmber(value){
	if($.isNumeric(value) && value >= 1){
		return true;
	}else{
		return false;
	}
}

function validateSize(value){
	var regNecklaceSize = /^\d{1,2}-\d{1,2}$/;
	if(!$.isNumeric(value) || value < 0){
		if(value == "기성"){
			return false;
		}else if(value == ""){
			return false;
		}else if(regNecklaceSize.test(value)){
			checkValue = value.split("-");
			if(checkValue[0] < 1 || checkValue[1] < 1 || checkValue[0] < checkValue[1]){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}else{
		return false;
	}
}

function checkQty(name){
	var check =false;
	for(var i = 0; i < $("[name="+name+"]").length; i++){
		var elem = $("[name="+name+"]:eq("+i+")");
		clearBorder(elem);
		var value = $(elem).val();
		if(!validateQty(value)){
			redBorder($("[name="+name+"]:eq("+i+")"));
			setSystemMessage("개수를 확인 해 주세요.");
			check = true;
		}
	}
	return !check;
}

function checkQtyElemsValue(elems){
	var check =true;
	for(var i = 0; i < elems.length; i++){
		var elem = elems[i];
		clearBorder(elem);
		var value = $(elem).val();
		if(!validateQty(value)){
			redBorder(elem);
			setSystemMessage("입력한 값을 확인해 주세요.");
			check = false;
		}
	}
	return check;
}
