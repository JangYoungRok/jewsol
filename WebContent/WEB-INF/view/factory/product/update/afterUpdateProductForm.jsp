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
	<link rel="stylesheet" href="/jewsol/css/factory/product/update/updateProductForm.css">
	<title>제품 수정</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/factory/product/update/updateProduct.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/factory/product/checkProduct.js"></script>
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<form name="updateProductForm" enctype="multipart/form-data" method="POST" action="/jewsol/factory/product/update/updateProduct.do">
			<article id="searchPanel">
				<label><span>분류 :</span>
					<select id="category" name="category">
						<c:forEach var="categoryList" items="${requestScope.categoryList }" varStatus="loopCount">
							<c:if test="${categoryList.categorySeq == product.categorySeq }">
								<option value="${categoryList.categorySeq }" selected="selected">${categoryList.category }</option>
							</c:if>
							<c:if test="${categoryList.categorySeq != product.categorySeq }">
								<option value="${categoryList.categorySeq }" >${categoryList.category }</option>
							</c:if>
						</c:forEach>
					</select> 
				</label>
				<label><span>제품번호 :</span> <input type="text" id="productCode" name="productCode" size="4" value="${product.productCode }" maxlength="4" required></label>
				<label><span>품목 :</span>
					<select id="type" name="type">
						<c:forEach var="typeList" items="${requestScope.typeList }" varStatus="loopCount">
							<c:if test="${typeList.typeSeq == product.typeSeq }">
								<option value="${typeList.typeSeq }" selected="selected">${typeList.type }</option>
							</c:if>
							<c:if test="${typeList.typeSeq != product.typeSeq }">
								<option value="${typeList.typeSeq }" >${typeList.type }</option>
							</c:if>
						</c:forEach>
					</select>
				</label>
				<input type="button" id="searchProduct" value="제품 검색">		
			</article>
		
			<article id="productPanel">
				<p>
					<img id="prev_img" src="/jewsol/image/${product.productImage }" alt="미리보기 이미지"><br>
					<!-- <input type="file" id="productImage" name="productImage" size="8"> -->
				</p>
				<p>
					<br>
					제품 이름&nbsp;&nbsp; &nbsp;:&nbsp;&nbsp; &nbsp;<input type="text" id="productName" value="${product.productName }" disabled="disabled"><br>
					<br>
					제품 평균 중량&nbsp;:&nbsp;<input type="text" name="productAveWeight" id="productAveWeight" value="${product.productAveWeight }">
				</p>
			</article>
			<article id="triggerPanel">
				<input type="button" id="updateProduct" value="제품 수정"><br>
				<input type="button" id="notInUseProduct" value="제품 이용 중지"><br>
				<input type="radio" name="getProductOption" value="O">제품상세수정
				<input type="radio" name="getProductOption" value="X" checked>제품상세보기
			</article>
			<article id="productOptionPanel">
				<jsp:include page="/WEB-INF/view/common/productOption.jsp" />
			</article>
		</form>
	</section>
	<footer id="footer">
		<article id="systemMessagePanel">
			<p>시스템 메세지</p>
			<p><span id="systemMessage">${product.productName } 수정이 완료 되었습니다.<br>
			</span></p>
		</article>
	</footer>
</body>
</html>