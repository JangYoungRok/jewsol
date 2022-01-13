function prevElem(elem){
	do{
		elem = elem.previousSibling;
	}while(elem && elem.nodeType != 1);
	return elem;
}

function lastElem(elem){
	elem = elem.lastChild;
	return elem && elem.nodeType != 1 ? prevElem(elem) : elem;
}

function nextElem(elem){
	do{
		elem = elem.nextSibling;
	}while(elem && elem.nodeType != 1);
	return elem;
}

function parentElem(elem, num){
	num = num || 1;
	for(var i = 0; i < num; i++)
		if(elem != null) elem = elem.parentNode;
	return elem;	
}

function firstElem(elem){

	elem = elem.firstChild;

	return elem && elem.nodeType != 1 ? nextElem( elem ) : elem;
}

function checkElem(elem){
	//문자열만 전달된 경우 텍스트 노드로 전환
	return elem && elem.constructor == String ? document.createTextNode(elem) : elem;
}

function appendElem(parent, elem){
	parent.appendChild( checkElem(elem) );
}

function emptyElem(elem){
	while(elem.firstChild){
		removeElem(elem.firstChild);
	}
}

function removeElem(elem){
	if(elem){
		elem.parentNode.removeChild(elem);
	}
}

function showElem(id){
	var elem = document.getElementById(id);
	
	if(elem){
		elem.style.display = '';
	}
}

function hideElem(id){
	var elem = document.getElementById(id);
	
	if(elem){
		elem.style.display = 'none';
	}
}

function getText(e){
	var t = "";
	
	e = e.childNodes || e;
	
	for(var j = 0; j < e.length; j++){
		t += e[j].nodeType != 1 ? e[j].nodeValue : getText(e[j].childNodes);
	}
	
	return t;
}


function nullCheckElem(elem){
	if(elem.value.length == 0 || elem.value == ""){
		return false;
	}
	
	return true;
}

function nullCheckValue(value){
	if(value.length == 0 || value == ""){
		return true;
	}
	
	return false;
}

function refreshElem(elemId,list){
	
	$(elemId).html(list);
}

function getParamsProduct(params){
	params.categorySeq = $("#category").val();
	params.productCode = $("#productCode").val();
	params.typeSeq = $("#type").val();
	
	return params;
}

function setProduct(productImage, productName, productAveWeight){
	
	$("#prev_img").removeAttr('src').attr("src","/jewsol/image/"+productImage);
	$("#productName").val(productName);
	$("#productAveWeight").val(productAveWeight);
}

function ableButtons(panel){
	var inputElems = $("#"+panel+" input:disabled");
	for(var i = 0; i < inputElems.length; i++){
		var elem = inputElems[i];
		$(elem).attr("disabled",false);
	}
}

function disableButtons(panel){
	var inputElems = $("#"+panel+" input:enabled");
	for(var i = 0; i < inputElems.length; i++){
		var elem = inputElems[i];
		$(elem).attr("disabled",true);
	}
}

function readonlyInput(panel){
	var inputElems = $("#"+panel+" input[type=text]");
	for(var i = 0; i < inputElems.length; i++){
		var elem = inputElems[i];
		$(elem).attr("readonly",true);
	}
}

function writableInput(panel){
	var inputElems = $("#"+panel+" input[type=text]");
	for(var i = 0; i < inputElems.length; i++){
		var elem = inputElems[i];
		$(elem).attr("readonly",false);
	}
}

function setSystemMessage(text){
	$("#systemMessage").html(text);
}


function emptySystemMessage(){
	$("#systemMessage").html("");
}

function initSearchProduct(){
	$("#category option:selected").attr("selected",false);
	$("#category option:eq(0)").attr("selected",true);
	$("#productCode").val("");
	$("#type option:selected").attr("selected",false);
	$("#type option:eq(0)").attr("selected",true);
}

function initProductInfo(){
	setProduct("noPic.jpg", "", "");
}

function getParamsOptionDetail(params){
	var czSeqArr = new Array();
	var czQtyArr = new Array();
	var czNameArr = new Array();
	var czSizeArr = new Array();
	
	for(var i = 0; i < $("[name=czSize]").length; i++){
		var czSizeSeq = $("[name=czSize]:eq("+i+")").val();
		if(czSizeSeq != 0){
			var czSeq = $("[name=cz]:eq("+i+")").val();
			if(czSeq != 0){
				var czQty = $("[name=czQty]:eq("+i+")").val();
				if(czQty > -1){
					var czName = $("[name=cz]:eq("+i+") option:selected").text();
					var czSize = $("[name=czSize]:eq("+i+") option:selected").text();
					czSeqArr.push(czSeq);
					czQtyArr.push(czQty);
					czNameArr.push(czName);
					czSizeArr.push(czSize);
				}
			}
		}
	}
	
	params.czSeqArr = czSeqArr;
	params.czQtyArr = czQtyArr;
	params.czNameArr = czNameArr;
	params.czSizeArr = czSizeArr;
	
	var stoneSeqArr = new Array();
	var stoneQtyArr = new Array();
	var stoneNameArr = new Array();
	var stoneSizeArr = new Array();
	
	for(var i = 0; i < $("[name=stoneSize]").length; i++){
		var stoneSizeSeq = $("[name=stoneSize]:eq("+i+")").val();
		if(stoneSizeSeq != 0){
			var stoneSeq = $("[name=stone]:eq("+i+")").val();
			if(stoneSeq != 0){
				var stoneQty = $("[name=stoneQty]:eq("+i+")").val();
				if(stoneQty > -1){
					var stoneName = $("[name=stone]:eq("+i+") option:selected").text();
					var stoneSize = $("[name=stoneSize]:eq("+i+") option:selected").text();
					stoneSeqArr.push(stoneSeq);
					stoneQtyArr.push(stoneQty);
					stoneNameArr.push(stoneName);
					stoneSizeArr.push(stoneSize);
				}
			}
		}
	}
	
	params.stoneSeqArr = stoneSeqArr;
	params.stoneQtyArr = stoneQtyArr;
	params.stoneNameArr = stoneNameArr;
	params.stoneSizeArr = stoneSizeArr;
	
	var partSeqArr = new Array();
	var partNameArr = new Array();
	
	for(var i = 0; i < $("[name=partAttribute]").length; i++){
		var partAttributeSeq = $("[name=partAttribute]:eq("+i+")").val();
		if(partAttributeSeq != 0){
			var partSeq = $("[name=part]:eq("+i+")").val();
			if(partSeq != 0){
				var partName =  $("[name=part]:eq("+i+") option:selected").text();
				partSeqArr.push(partSeq);
				partNameArr.push(partName);
			}
		}
	}
	
	params.partSeqArr = partSeqArr;
	params.partNameArr = partNameArr;
	
	var addSeqArr = new Array();
	var addNameArr = new Array();
	
	for(var i = 0; i < $("[name=add]:checked").length; i++){
		var elem = $("[name=add]:checked:eq("+i+")");
		var addSeq = $(elem).val();
		var addName = $(elem).next("label").text();
		
		addSeqArr.push(addSeq);
		addNameArr.push(addName);
	}
	
	params.addSeqArr = addSeqArr;
	params.addNameArr = addNameArr;
	
	return params;
	
}

function valueSelector(selectId, selectValue){
	for(var i = 0; i < $("#"+selectId+" option").length; i++){
		if($("#"+selectId+" option:eq("+i+")").val() == $.trim(selectValue)){
			$("#"+selectId+" option:eq("+i+")").prop("selected", true);
		}
	}
}
function textSelector(selectId, selectText){
	for(var i = 0; i < $("#"+selectId+" option").length; i++){
		if($.trim($("#"+selectId+" option:eq("+i+")").text()) == $.trim(selectText)){
			$("#"+selectId+" option:eq("+i+")").prop("selected", true);
		}
	}
}
function checker(checkboxId, checkValue){
	$("#"+checkboxId).prop("checked", false);
	if($("#"+checkboxId).val() == $.trim(checkValue)){
		$("#"+checkboxId).prop("checked", true);
	}
}


function initCzSizeEvent(){
	for(var i = 0; i < $("[name=czSize]").length; i++){
		$("[name=czSize]:eq("+i+")").change({index : i},function(e){
			var params = {};
			params.index = e.data.index;
			params.czSizeSeq = $(this).val();
			
			sendRequest("/jewsol/store/storeProduct/getCzList.do", params, appendCzList, "GET");
		});
	}
}

function appendCzList(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		var index = $.trim(results[0]);
		var list = results[1];
		
		$("[name=cz]:eq("+index+")").html(list);
	}
}

function initStoneSizeEvent(){
	for(var i = 0; i < $("[name=stoneSize]").length; i++){
		$("[name=stoneSize]:eq("+i+")").change({index : i},function(e){
			var params = {};
			params.index = e.data.index;
			params.stoneSizeSeq = $(this).val();
			
			sendRequest("/jewsol/store/storeProduct/getStoneList.do", params, appendStoneList, "GET");
		});
	}
	
}

function appendStoneList(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		var index = $.trim(results[0]);
		var list = results[1];
		
		$("[name=stone]:eq("+index+")").html(list);
	}
	
}

function initPartAttributeEvent(){
	for(var i = 0; i < $("[name=partAttribute]").length; i++){
		$("[name=partAttribute]:eq("+i+")").change({index : i},function(e){
			var params = {};
			params.index = e.data.index;
			params.partAttributeSeq = $(this).val();
			
			sendRequest("/jewsol/store/storeProduct/getPartList.do", params, appendPartList, "GET");
		});
	}
	
}

function appendPartList(){
	if(request.readyState == 4 && request.status == 200){
		var results = request.responseText.split(",");
		var index = $.trim(results[0]);
		var list = results[1];
		
		$("[name=part]:eq("+index+")").html(list);
	}
	
}

function getDate(dateString){
	var stringArr = dateString.split('-');
	if(stringArr[1].length == 1){
		stringArr[1] = "0" + stringArr[1];
	}
	if(stringArr[2].length == 1){
		stringArr[2] = "0" + stringArr[2];
	}
	var date = stringArr[0] + "-" + stringArr[1] + "-" + stringArr[2];
	return date;
}

function getYesterday(date){
	var dt = new Date(getDate(date));
	dt.setDate(dt.getDate() - 1);
	return dt.getFullYear() + "-" + (dt.getMonth() + 1) + "-" + dt.getDate();
}

function getTomorrow(date){
	var dt = new Date(getDate(date));
	dt.setDate(dt.getDate() + 1);
	return dt.getFullYear() + "-" + (dt.getMonth() + 1) + "-" + dt.getDate();
}

function getOrderDate(){
	return $("#orderDate").val();
}

function setOrderDate(date){
	$("#orderDate").val(date);
}
function parseNumber(string){
	return Number($.trim(string));
}

function trim(value){
	return $.trim(value);
}

function disableButton(buttonElem){
	$(buttonElem).prop("disabled", true);
}

function ableButton(buttonElem){
	$(buttonElem).prop("disabled", false);
}