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
	<link rel="stylesheet" href="/jewsol/css/store/saleSheet/insertSaleSheet.css">
	<title>기성 판매</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/saleSheet/insertSaleSheet.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/saleSheet/customerListInterfaceForSaleSheet.js"></script>
	
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<article id="triggerPanel">
			<div>
				<input type="text" id="saleSheetDate" value="${saleSheetDate }">
				<label for="customer">거래처</label><input type="text" id="customer">
				<input type="button" id="searchCustomerBtn" value="거래처 검색"><br>
				<label for="lastBalanceGold">미수순금</label><input type="text" id="lastBalanceGold">g
				<label for="lastBalanceCash">미수현금</label><input type="text" id="lastBalanceCash">&#8361;<br>
				<label for="saleGold">결제순금</label><input type="text" id="saleGold">g
				<label for="saleCash"></label>결제현금<input type="text" id="saleCash">&#8361;<br>
				<label for="balanceGold">잔 순금</label><input type="text" id="balanceGold">g
				<label for="balanceCash">잔 현금</label><input type="text" id="balanceCash">&#8361;
				<input type="button" id="insertSaleSheetBtn" value="기성 판매">
			</div>
			<div id="customerListDiv">
			</div>
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