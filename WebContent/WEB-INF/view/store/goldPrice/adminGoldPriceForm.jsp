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
	<link rel="stylesheet" href="/jewsol/css/store/cz/adminCzForm.css">
	<title>금시세 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/goldPrice/adminGoldPriceForm.js"></script>
	
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
			
		</div>
	</nav>
	<section id="mainPanel">
		최근 입력<br/> 
		${goldPrice.goldPriceDate } 금시세 : ${goldPrice.goldPrice } 원  부가세 :${goldPrice.goldTaxPrice } 원 <br/><br/>
		금시세 : <input type="text" name="goldPrice"> 부가세 : <input type="text" name="goldTaxPrice"> <input type="button" value="금시세 입력" id="insertGoldPriceBtn">
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