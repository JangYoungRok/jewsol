$(document).ready(function(){
	$("#optionSelect").change(function(){
		var storeProductSeq = $("#storeProductSeq").val();
		var optionNumber = $("#optionSelect option:selected").text().trim().split("-")[0];
		var url = "/jewsol/store/storeProduct/board/storeProductDetail.do?storeProductSeq="+storeProductSeq+"&optionNumber="+optionNumber;
		$(location).attr("href",url);
	});
});