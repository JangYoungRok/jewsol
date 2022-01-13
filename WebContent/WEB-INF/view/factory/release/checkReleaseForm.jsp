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
	<link rel="stylesheet" href="/jewsol/css/factory/release/checkRelease.css?v=3">
	<title>출고 확인</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/factory/release/checkRelease.js?v=5"></script>
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
			<input type="button" id="setYesterdayBtn" value="<<"> <input type="text" id="orderDate" value="${orderDate }"> <input type="button" id="setTomorrowBtn" value=">>">
			<input type="button" id="minusPanelNumberBtn" value="-">  <input type="text" id="panelNumber" value="${panelNumber }"> / <input type="text" id="panelLength" value="${panelLength}"> <input type="button" id="plusPanelNumberBtn" value="+">
			<input type="button" id="getPanelListBtn" value="불러 오기">
			<input type="button" id="checkReleaseBtn" value="출고 확인">
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