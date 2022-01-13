$(document).ready(function(){
	
	$("#searchStoreProductBtn").click(function(){
		
		if(checkProductCode()){
			setSystemMessage("검색중 입니다.");
			$("#optionDetailPanel div").html("");
			$("#option").attr("disabled",true);
			$("#option").html("");
			
			$("#updateOptionBtn").attr("disabled",true);
			$("#deleteOptionBtn").attr("disabled",true);
			
			$("#storeProductName").val("");
			$("#optionName").attr("disabled",true);
			$("#optionName").val("");
			
			var params = {};
			getParamsProduct(params);
			sendRequest("/jewsol/store/option/admin/searchStoreProduct.do", params, afterSearchProduct, "GET");
		}
	});
	
	$("#updateOptionBtn").click(function(){	
		var check = (checkQty("czQty") && checkQty("stoneQty"));
		
		if(check){
			setSystemMessage("옵션을 수정 중 입니다.");
			
			var params = {};
			var url = "";
			var optionSeq = $("#option").val();
			params.optionSeq = optionSeq;
			params.optionName = $("#optionName").val();
			params = getParamsOptionDetail(params);
			
			if(optionSeq == 0){
				params.optionNumber = $.trim($("#option option:selected").text().split("--")[0]);
				params.originalOptionSeq = $("#option option:eq(0)").val();
				url = "/jewsol/store/optoin/admin/insertOption.do";
			}else{
				url = "/jewsol/store/optoin/admin/updateOption.do";
			}
			
			$("#option").attr("disabled",true);
			$("#option").html("");
			$("#optionName").attr("disabled",true);
			$("#optionName").val("");
			$("#optionDetailPanel div").html("");
			
			sendRequest(url, params, afterUpdateOption, "GET");
			
		}
		
		
	});
	
	$("#deleteOptionBtn").click(function(){
		var result = confirm("옵션을 삭제 하시 겠습니까?");
		
		if(result){
			setSystemMessage("옵션 삭제 중 입니다.");
			
			var params = {};
			params.optionSeq = $("#option").val();
			params.optionNumber = $.trim($("#option option:selected").text().split("--")[0]);
			params.originalOptionSeq = $("#option option:eq(0)").val();
			
			$("#option").attr("disabled",true);
			$("#option").html("");
			$("#optionName").attr("disabled",true);
			$("#optionName").val("");
			$("#optionDetailPanel div").html("");
			
			sendRequest("/jewsol/store/option/admin/deleteOption.do", params, afterDeleteOption, "POST");
		
		}
		
	});
	
	$("#option").change(function(){
		setSystemMessage("옵션 내용을 가져오는 중 입니다.");
	
		$("#optionName").val("");
		$("#optionDetailPanel div").html("");
		
		var params = {};
		var optionSeq = $("#option").val();
		var originalOptionSeq = $("#option option:eq(0)").val();
		
		if(optionSeq == 0){
			optionSeq = originalOptionSeq;
		}
		params.optionSeq = optionSeq;
		
		sendRequest("/jewsol/store/option/admin/changeOption.do", params, afterChangeOption, "POST");
	});
	
});

function afterUpdateOption(){
	if(request.readyState == 4 && request.status == 200){
		var result = request.responseText.split(",");
		
		setSystemMessage("옵션 수정이 완료 되었습니다.");
		$("#optionName").removeAttr("disabled");
		$("#optionName").val($.trim(result[0]));
		$("#option").removeAttr("disabled");
		$("#option").html($.trim(result[1]));
		renewOptionDetailPanel($.trim(result[2]));
	}
}

function afterSearchProduct(){
	if(request.readyState == 4 && request.status == 200){
		
		var result = request.responseText.split(",");
		var systemMessage = "";
		if(result[0] == 2){
			systemMessage = "없는 제품 입니다.";
		}else if(result[0] == 3){
			systemMessage = "이용 중지 중인 제품 입니다.";
		}else if(result[0] == 1){
			systemMessage = "제품 검색이 완료 되었습니다.";
			$("#storeProductName").val($.trim(result[1]));
			$("#optionName").val($.trim(result[2]));
			$("#optionName").removeAttr("disabled");
			$("#option").html($.trim(result[3]));
			$("#option").removeAttr("disabled");
			renewOptionDetailPanel($.trim(result[4]));
			$("#updateOptionBtn").removeAttr("disabled");
			$("#deleteOptionBtn").removeAttr("disabled");
			
		}
		setSystemMessage(systemMessage);
	}
}

function afterDeleteOption(){
	if(request.readyState == 4 && request.status == 200){
		var result = request.responseText.split(",");
		setSystemMessage("옵션 삭제가 완료 되었습니다.");
		$("#optionName").removeAttr("disabled");
		$("#optionName").val($.trim(result[0]));
		$("#option").removeAttr("disabled");
		$("#option").html($.trim(result[1]));
		renewOptionDetailPanel($.trim(result[2]));
		
		
	
	}
}

function afterChangeOption(){
	if(request.readyState == 4 && request.status == 200){
		var result = request.responseText.split(",");
		setSystemMessage("옵션 내용을 갱신 하였습니다.");
		$("#optionName").val($.trim(result[0]));
		renewOptionDetailPanel($.trim(result[1]));
	}
}

function renewOptionDetailPanel(optionDetail){
	$("#optionDetailPanel").html(optionDetail);
	initCzSizeEvent();
	initStoneSizeEvent();
	initPartAttributeEvent();
}
