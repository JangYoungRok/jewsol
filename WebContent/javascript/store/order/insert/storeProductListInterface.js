var StoreProductListInterface = {
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
			var data = $.trim($($(this.list)[this.curPos]).text()).split(" ");
			$(this.input).val(data[0] + " " + data[1] + " " + data[2]);
			$("#prev_img").attr("src","/jewsol/image/" + data[3]);
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
			this.list = $("#storeProductList li");
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
					setSystemMessage("제품번호가 선택 되었습니다.");
					OrderParams.storeProductSeq = $(this.list[this.curPos]).attr("id");
					this.selected = true;
					this.initListDiv();
					this.focusOn();
					getOptionForOrder(OrderParams.storeProductSeq);
				}else if(this.selected){
					$("#optionList").focus();
				}
				return false;
				
			}else if(event.keyCode == KEYCODE_BACKSPACE){
				if(this.curPos > -1){
					return false;
				}else if(this.selected){
					setSystemMessage("제품번호 선택이 취소 되었습니다.");
					this.initListDiv();
					this.initValue();
					this.selected = false;
					OrderParams.storeProductSeq = 0;
					$("#optionList").html("");
					$("#orderOptionPanel").html("");
					OptionReady = false;
					return false;
				}else{
					if(this.selected || this.curPos > -1){
						return false;
					}else if(!this.loop){
						setSystemMessage("제품 검색을 시작 합니다.");
						this.loop = true;
						sendStoreProductKeyword();
						return;
					}
				}
				
				return;
			}else{
				if(this.selected || this.curPos > -1){
					return false;
				}else if(!this.loop && $(this.input).val() != ""){
					setSystemMessage("제품 검색을 시작 합니다.");
					this.loop = true;
					sendStoreProductKeyword();
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
				OrderParams.storeProductSeq = 0;
			}
		},
		afterGetUpdateOrder : function(){
			this.selected = true;
		}
	};

function sendStoreProductKeyword(){
	
	if(!StoreProductListInterface.loop){
		setSystemMessage("제품번호 검색이 종료 되었습니다.");
		StoreProductListInterface.sendKeyword = "";
		return;
	}
	
	var keyword = $("#storeProduct").val();
	if(keyword == ""){
		StoreProductListInterface.initListDiv();
		StoreProductListInterface.sendKeyword = "";
	}else{
		if(keyword != StoreProductListInterface.sendKeyword){
			
			var params = {};
			params.keyword = keyword;
			StoreProductListInterface.sendKeyword = keyword;
			
			sendRequest("/jewsol/store/storeProduct/sendStoreProductKeyword.do", params, afterSendStoreProductKeyWord, 'POST');
		}
	}
	
	setTimeout("sendStoreProductKeyword();", 500);
	
}
function afterSendStoreProductKeyWord(){
	if(request.readyState == 4 && request.status == 200){
		setSystemMessage("제품번호 검색이 완료 되었습니다.");
		$("#storeProductListDiv").html($.trim(request.responseText));
		
	}
}

