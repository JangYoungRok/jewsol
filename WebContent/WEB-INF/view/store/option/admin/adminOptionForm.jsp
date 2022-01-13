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
	<link rel="stylesheet" href="/jewsol/css/store/option/admin/adminOption.css">
	<title>옵션 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/factory/product/checkProduct.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/option/admin/adminOption.js"></script>
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<article id="searchPanel">
			<label><span>분류 :</span>
				<select id="category" name="category">
					<c:forEach var="categoryList" items="${requestScope.categoryList }" varStatus="loopCount">
						<c:if test="${loopCount.count == 1 }">
							<option value="${categoryList.categorySeq }" selected="selected">${categoryList.category }</option>
						</c:if>
						<c:if test="${loopCount.count != 1 }">
							<option value="${categoryList.categorySeq }" >${categoryList.category }</option>
						</c:if>
					</c:forEach>
				</select> 
			</label>
			<label><span>제품번호 :</span> <input type="text" id="productCode" name="productCode" size="4" maxlength="4" required></label>
			<label><span>품목 :</span>
				<select id="type" name="type">
					<c:forEach var="typeList" items="${requestScope.typeList }" varStatus="loopCount">
						<c:if test="${loopCount.count == 1 }">
							<option value="${typeList.typeSeq }" selected="selected">${typeList.type }</option>
						</c:if>
						<c:if test="${loopCount.count != 1 }">
							<option value="${typeList.typeSeq }" >${typeList.type }</option>
						</c:if>
					</c:forEach>
				</select>
			</label>
			<input type="button" id="searchStoreProductBtn" value="제품 검색">	
			옵션 번호 : 
			<select id="option" disabled="disabled">
			</select>
		</article>
		<article id="optionPanel">
			<p>제품 이름 : <input id="storeProductName" value="" readonly="readonly"> 옵션 이름 : <input id="optionName" value="" disabled="disabled"></p>
			<p><input type="button" id="updateOptionBtn" value="옵션 수정" disabled="disabled"> <input type="button" id="deleteOptionBtn" value="옵션 삭제" disabled="disabled">	</p>
		</article>
		<article id="optionDetailPanel">
			<div></div>
			<div></div>
			<div></div>
			<div></div>
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