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
	<link rel="stylesheet" href="/jewsol/css/store/storeProduct/adminStoreProduct.css">
	<title>타사 제품 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/factory/product/checkProduct.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/storeProduct/admin/adminStoreProduct.js"></script>
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<form name="updateStoreProductForm" enctype="multipart/form-data" method="POST" action="/jewsol/store/storeProduct/updateStoreProduct.do">
	<section id="mainPanel">
		<article id="searchPanel">
			<jsp:include page="${searchProduct }" />
		</article>
		<article id="productPanel">
			<jsp:include page="${productPanel }" />
		</article>
	</section>
	</form>
	<footer id="footer">
		<article id="systemMessagePanel">
			<p>시스템 메세지</p>
			<p><span id="systemMessage">${systemMessage }<br>
			</span></p>
		</article>
	</footer>
</body>
</html>