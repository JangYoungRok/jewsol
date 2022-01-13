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
	<link rel="stylesheet" href="/jewsol/css/store/storeProduct/board/storeProductBoard.css">
	<title>지점 제품 조회</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/storeProduct/board/storeProductBoard.js"></script>
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<article id="function">
			<p id="trigger">
				<select id="category">
					<option value="0" selected="selected">전체</option>
					<c:forEach var="categoryList" items="${requestScope.categoryList }">
						<option value="${categoryList.categorySeq }" >${categoryList.category }</option>
					</c:forEach>
				</select>		
				<input type="text" id="productCode" name="productCode" size="4" maxlength="4">
				<input type="button" id="searchProductBtn" value="제품검색">
			</p>
			<p id="searchInfo">
				최근 검색 :
				<span id="retrievedCategory">${retrievedCategory }</span>&nbsp;&nbsp;
				<span id="retrievedProductCode">${retrievedProductCode }</span>
			</p>
			
		</article>
		
		<article id="productBoard">
			<c:forEach var="i" begin="0" end="3">
				<p>
				<c:forEach var="j" begin="0" end="3">
					<c:if test="${storeProductList[i*4+j].storeProductSeq == null }">
						<span id=""></span>
					</c:if>
					<c:if test="${storeProductList[i*4+j].storeProductSeq != null }">
						<span id="${storeProductList[i*4+j].storeProductSeq }">
							<img src="/jewsol/image/${storeProductList[i*4+j].storeProductImage }" alt="${storeProductList[i*4+j].storeProductName }"><br>
							${storeProductList[i*4+j].storeProductName } [${storeProductList[i*4+j].storeProductAveWeight }g]
						</span>
					</c:if>
				</c:forEach>
				</p>
			</c:forEach>
		</article>
		
		<article id="pnb">
			${pageNavigation }
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