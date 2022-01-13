var SelectedOrderIndex = -1;

$(document).ready(function(){
	$("#originalSheetDate").prop("readonly", true);
	disableGetPrintOriginalSheetBtn();
	initOriginalSheetListEvent();
	$("#setYesterdayBtn").click(function(){
		getOriginalSheetList(getYesterday($("#originalSheetDate").val()));
	});
	
	$("#setTomorrowBtn").click(function(){
		getOriginalSheetList(getTomorrow($("#originalSheetDate").val()));
	});
	
	$("#getPrintOriginalSheetBtn").click(function(){
		var originalSheetSeq = getSelectedOriginalSheetSeq();
		window.open("/jewsol/store/originalSheet/getPrintOriginalSheet.do?originalSheetSeq="+originalSheetSeq,"printOriginalSheetWindow","menubar=no, toolbar=no, location=no, scrollbars=yes, left=10, top=3, resizable=yes, width=900, height=700");
	});
});

function initOriginalSheetListEvent(){
	var elems = $("#originalSheetList tr");
	var length = $(elems).length;
	if(length > 0){
		for(var i = 0; i < length; i++){
			var elem = $(elems)[i];
			$(elem).mouseenter(function(){
				$(this).css('background-color', 'lightgray');
				
			}).mouseleave(function(){
				$(this).css('background-color', '');
				
			});
			
			$(elem).click({index : i},function(e){
				ableGetPrintOriginalSheetBtn();
				setSystemMessage("원장 내용을 가져 옵니다.");
				emptyOriginalSheetContent();
				if(SelectedOrderIndex > -1){
					 clearBorder($("#originalSheetList tr:eq("+SelectedOrderIndex+")"));
				}
				SelectedOrderIndex = e.data.index;
				redBorder(this);
				
				var params = {};
				params.originalSheetSeq = getSelectedOriginalSheetSeq();
				sendRequest("/jewsol/store/originalSheet/getOriginalSheetContent.do", params, originalSheetContentResult, "GET");
			});
		}
	}
}
function originalSheetContentResult(){
	if(request.readyState == 4 && request.status == 200){
		setOriginalSheetContent(request.responseText);
		setSystemMessage("원장 내용을 가져 왔습니다.");
	}
}

function getOriginalSheetList(originalSheetDate){
	$("#originalSheetDate").val(originalSheetDate);
	setSystemMessage(originalSheetDate + "원장을 가져 옵니다.");
	disableGetPrintOriginalSheetBtn();
	emptyOriginalSheetList();
	emptyOriginalSheetContent();
	var params = {};
	params.originalSheetDate = originalSheetDate;
	sendRequest("/jewsol/store/originalSheet/getOriginalSheetList.do", params, originalSheetListResult, "GET");
}
function originalSheetListResult(){
	if(request.readyState == 4 && request.status == 200){
		setOriginalSheetList(request.responseText);
		initOriginalSheetListEvent();
		setSystemMessage("원장 리스트를 가져 왔습니다.");
	}
}

function getSelectedOriginalSheetSeq(){
	return $("#originalSheetList tr:eq("+SelectedOrderIndex+")").attr("id");
}
function emptyOriginalSheetList(){
	$("#originalSheetListPanel").html("");
}
function setOriginalSheetList(originalSheetList){
	$("#originalSheetListPanel").html(originalSheetList);
}
function ableGetPrintOriginalSheetBtn(){
	$("#getPrintOriginalSheetBtn").prop("disabled", false);
}
function disableGetPrintOriginalSheetBtn(){
	$("#getPrintOriginalSheetBtn").prop("disabled", true);
}
function emptyOriginalSheetContent(){
	$("#originalSheetContentPanel").html("");
}
function setOriginalSheetContent(originalSheetContent){
	$("#originalSheetContentPanel").html(originalSheetContent);
}