<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/mainLayout.css">
	<link rel="stylesheet" href="/jewsol/css/menu.css">
	<link rel="stylesheet" href="/jewsol/css/store/order/insert/insertOrder.css">
	<title>주문 입력</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/order/insert/customerListInterface.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/order/insert/storeProductListInterface.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/order/insert/insertOrder.js"></script>
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<article id="orderInsertPanel">
			<div id="storeProductImage">
				<img id="prev_img" src="/jewsol/image/noPic.jpg" alt="제품이미지">
			</div>
			<div>
				날짜 <input id="orderDate" type="text" value="${orderDate }"> 원장번호 : <input id="originalSheet" type="text" value=""> 
				주문자 <select id="orderMember">
					<c:forEach var="member" items="${memberDtoList }">
						<option value="${member.memberSeq }">${member.memberName }</option>
					</c:forEach>
				</select> 
				거래처 <input id="customer" type="text" value="">
				
			</div>
			<div id="customerListDiv">
			</div>
			<div>
				제품번호 <input id="storeProduct" type="text" value=""> 
				옵션 <select id="optionList"></select> 
				K <select id="k">
					<c:forEach var="k" items="${kList }">
						<option value="${k.kSeq }">${k.kName }</option>
					</c:forEach>
				</select> 
				메인 <select id="mainColor">
					<c:forEach var="color" items="${mainColorList }">
						<option value="${color.colorSeq }">${color.colorName }</option>
					</c:forEach>
				</select>
				보조 <select id="subColor">
					<c:forEach var="color" items="${subColorList }">
						<option value="${color.colorSeq }">${color.colorName }</option>
					</c:forEach>
				</select>
			</div>
			<div id="storeProductListDiv">
			</div>
			<div>
				사이즈 <input id="orderSize" value=""> 
				기타 <input id="orderEtc" value=""> 
				급 <input type="checkbox" id="orderHurry" value="T"> 
				반벌 <input type="checkbox" id="orderHalf" value="T"> 
				도금 <select id="plate">
					<c:forEach var="plate" items="${plateList }">
						<option value="${plate.plateSeq }">${plate.plateName }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<input type="button" id="insertOrderBtn" value="주문 입력" >
				<input type="button" id="updateOrderBtn" value="주문 수정">
				<input type="button" id="cancleUpdateOrderBtn" value="수정 취소">
				<input type="button" id="deleteOrderBtn" value="주문 삭제">
				<input type="button" id="registerOrderBtn" value="원장 마감" >
			</div>
		</article>
		<article id="orderOptionPanel">
		</article>
		<article id="orderListPanel">
		</article>
		
	</section>
	<footer id="footer">
		<article id="systemMessagePanel">
			<p>시스템 메세지</p>
			<p><span id="systemMessage"><br>
			</span></p>
		</article>
	</footer>
</body>
</html>