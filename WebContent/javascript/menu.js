$(function(){
	/* calss="sub" 로 설정된 모든 엘리먼트(html 요소)에 j Query hide() 함수를 호출하여 실제 화면에서는 보이지 않게함 */
	$(".sub").hide();
	/* class="meun" 의 li 요소에 hover이벤트(마우스가 올라갈 경우 함수를 호출함 이벤트에 콜백으로 새로운 function을 설정
	 hover이벤트 적용의 경우 , 후에 나오는 function은 hover가 풀릴경우 콜백 함수를 적용
	 
	 따라서 class="menu"의 li 요소에 hover될 경우
	 jQuery slideDown함수 숨겨진 " this = "menu.il" 안의 ul"요소인데 를 아래로 내리는 효과를 보여줌 인자는 fast 빨리 slow 느리게 normal 보통
	 으로 속도 조정 가능
	 그리고 , 다음 함수는 hover가 풀릴 경우
	 jQuery 함수 sildeUp()호출 " this = "menu.il" 안의 ul"요소 에 slideUp 함수를 호출
	*/
	/*$(".menu li").mouseenter(function(){
		$("ul",this).slideDown("fast");
	}).mouseleave(function(){
		$("ul",this).slideUp("fast");
	});*/
	$(".menu li").mouseenter(function(){
		$("ul",this).show();
	}).mouseleave(function(){
		$("ul",this).hide();
	});	


});

