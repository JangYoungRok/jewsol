$(document).ready(function(){
	alert("aaaa");
	$("#insertGoldPriceBtn").click(function(){
		var goldPrice = $("input[name=goldPrice]").val();
		var goldTaxPrice = $("input[name=goldTaxPrice]").val();
		
		if($.isNumeric(goldPrice) && $.isNumeric(goldTaxPrice)){
			if((goldPrice > 0) && (goldTaxPrice > 0)){
				
				$.post('/jewsol/store/goldPrice/insertGoldPriceAjax.do',{
					
				},function(resp){
					alert("입력이 완료 되었습니다.");
				}, 'json');
				
					
			}
					
		}
	});
	
	
});

