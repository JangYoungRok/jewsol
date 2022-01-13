<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/store/storeProduct/board/storeProductDetail.css">
	<title>상세정보</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/storeProduct/board/detail/storeProductDetail.js"></script>
	<section id="mainPanel">
		<article id="storeProduct">
			<jsp:include page="${storeProduct }" />
		</article>
		<article id="option">
			<jsp:include page="${optionList }" />
		</article>
		<article id="optionDetail">
			<jsp:include page="${optionDetail }" />
		</article>
	
	</section>
</body>
</html>