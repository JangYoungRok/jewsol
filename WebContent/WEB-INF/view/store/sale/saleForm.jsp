<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/mainLayout.css">
	<link rel="stylesheet" href="/jewsol/css/menu.css">
	<link rel="stylesheet" href="/jewsol/css/store/sale/sale.css">
	<title>판매 입력</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/sale/sale.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/sale/customerListInterfaceForSale.js"></script>
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<article id="saleSheetListPanel">
		</article>
		<article id="column">
			<section id="triggerPanel">
				<div>
					<input type="text" id="customer"> <input type="button" id="searchCustomerBtn" value="거래처 검색"> <input type="text" id="saleDate" value="${saleDate }">&nbsp;&nbsp;&nbsp;
					 금시세 : <fmt:formatNumber type="currency" value="${goldPrice.goldPrice }" pattern="###,###"/>&#8361; &nbsp;&nbsp;&nbsp;
					 부가세 : <fmt:formatNumber type="currency" value="${goldPrice.goldTaxPrice }" pattern="###,###"/>&#8361; <br>
					 <jsp:include page="${saleTypeListForm }" />  <input type="button" id="selectAllBtn" value="전체 선택"> <input type="button" id="insertSaleBtn" value="판매 입력">
				</div>
				<div id="customerListDiv">
				</div>
			</section>
			<section id="orderListPanel">
			</section>
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