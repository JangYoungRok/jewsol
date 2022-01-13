var CustomerListInterface ={
		div : "",
		input : "",
		length : 0,
		curPos : -1,
		list : "",
		loop : false,
		lastKeyword : "",
		sendKeyword : "",
		selected : false,
		selectList : function(){
			$(this.list[this.curPos]).css("background-color","lightgray");
		},
		unselectList : function(){
			$(this.list[this.curPos]).css("background-color","");
		},
		insertValue : function(){
			$(this.input).val($.trim($($(this.list)[this.curPos]).text()));
		},
		focusOn : function(){
			$(this.input).focus();
		},
		initValue : function(){
			$(this.input).val("");
		},
		initListDiv : function(){
			$(this.div).html("");
			this.curPos = -1;
		},
		keyDown : function(event){
			this.list = $("#customerList li");
			this.length = this.list.length - 1;
			if(event.keyCode == KEYCODE_ARROW_DOWN){
				
				if(this.curPos == -1 && this.length > -1){
					this.lastKeyword = $(this.input).val();
					++this.curPos;
					this.selectList();
					this.insertValue();
					this.loop = false;
				}else if(this.curPos > -1 && this.length != this.curPos){
					this.initValue();
					this.unselectList();
					++this.curPos;
					this.insertValue();
					this.selectList();
				}
				return false;
				
			}else if(event.keyCode == KEYCODE_ARROW_UP){
				if(this.curPos > 0){
					this.initValue();
					this.unselectList();
					--this.curPos;
					this.insertValue();
					this.selectList();
				}else if(this.curPos == 0){
					this.initValue();
					this.unselectList();
					--this.curPos;
					this.focusOn();
					$(this.input).val(this.lastKeyword);
					this.lastKeyword = "";
				}
				return false;
				
			}else if(event.keyCode == KEYCODE_ENTER){
				if(this.curPos > -1){
					setSystemMessage("거래처가 선택 되었습니다.");
					OrderParams.customerSeq = $(this.list[this.curPos]).attr("id");
					this.selected = true;
					this.initListDiv();
					this.focusOn();
				}else if(this.selected){
					$("#storeProduct").focus();
				}
				return false;
				
			}else if(event.keyCode == KEYCODE_BACKSPACE){
				if(this.curPos > -1){
					return false;
				}else if(this.selected){
					setSystemMessage("거래처 선택이 취소 되었습니다.");
					this.initListDiv();
					this.initValue();
					this.selected = false;
					OrderParams.customerSeq = 0;
					return false;
				}else{
					if(this.selected || this.curPos > -1){
						return false;
					}else if(!this.loop && $(this.input).val() != ""){
						setSystemMessage("거래처 검색을 시작 합니다.");
						this.loop = true;
						sendCustomerKeyword();
						return;
					}
				}
				
				return;
			}else{
				if(this.selected || this.curPos > -1){
					return false;
				}else if(!this.loop && $(this.input).val() != ""){
					setSystemMessage("거래처 검색을 시작 합니다.");
					this.loop = true;
					sendCustomerKeyword();
					return;
				}
			}
		},
		blur : function(){
			if(!this.selected){
				this.loop = false;
				if(this.curPos > -1){
					$(this.input).val(this.lastKeyword);
				}
				this.initListDiv();
				OrderParams.customerSeq = 0;
			}
		},
		afterGetUpdateOrder : function(){
			this.selected = true;
		}
	};

function sendCustomerKeyword(){
	
	if(!CustomerListInterface.loop){
		setSystemMessage("검색이 종료 되었습니다.");
		CustomerListInterface.sendKeyword = "";
		return;
	}
	
	var keyword = $("#customer").val();
	if(keyword == ""){
		$("#customerListDiv").html("");
		CustomerListInterface.sendKeyword = "";
	}else{
		if(keyword != CustomerListInterface.sendKeyword){
			
			var params = {};
			params.keyword = keyword;
			CustomerListInterface.sendKeyword = keyword;
			
			sendRequest("/jewsol/store/customer/sendCustomerKeyword.do", params, afterSendCustomerKeyWord, 'POST');
		}
	}
	
	setTimeout("sendCustomerKeyword();", 500);
	
}
function afterSendCustomerKeyWord(){
	if(request.readyState == 4 && request.status == 200){
		setSystemMessage("거래처 검색이 완료 되었습니다.");
		$("#customerListDiv").html($.trim(request.responseText));
	}
}