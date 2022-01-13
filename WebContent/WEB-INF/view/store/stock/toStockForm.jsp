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
	<link rel="stylesheet" href="/jewsol/css/store/stock/toStock.css">
	<title>입고 확인</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/stock/toStock.js"></script>
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<article id="releaseSheetListPanel">
			<jsp:include page="${releaseSheetListForm }" />
		</article>
		<article id="column">
			<section id="triggerPanel">
				<jsp:include page="${branchListForm }" />
				입고 일자 <input type="text" id="toStockDate" value="${toStockDate }">
				<input type="button" id="selectAllBtn" value="전체 선택">
				<input type="button" id="toStockBtn" value="입고 확인">
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