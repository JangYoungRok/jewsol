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
	<link rel="stylesheet" href="/jewsol/css/store/storeProduct/insertStoreProduct.css">
	<title>타사 제품 입력</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/factory/product/checkProduct.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/storeProduct/insert/insertStoreProduct.js"></script>
	
	
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="#{menu }" />
		</div>
	</nav>
	<form name="insertStoreProductForm" enctype="multipart/form-data" method="POST" action="/jewsol/store/storeProduct/insertStoreProduct.do">
		<section id="mainPanel">
			<article id="searchPanel">
				<br>
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
				<input type="button" id="checkOverLapStoreProductBtn" value="중복 체크">		
			</article>
			
			<article id="productPanel">
				<p>
					<br>
					<label><span>공급처 :</span>
						<select id="supplier" name="supplier">
							<c:forEach var="supplierList" items="${requestScope.supplierList }" varStatus="loopCount">
								<c:if test="${loopCount.count == 1 }">
									<option value="${supplierList.supplierSeq }" selected="selected">${supplierList.supplierName }</option>
								</c:if>
								<c:if test="${loopCount.count != 1 }">
									<option value="${supplierList.supplierSeq }" >${supplierList.supplierName }</option>
								</c:if>
							</c:forEach>
						</select> 
					</label><br>
					<br>
					<img id="prev_img" src="/jewsol/image/noPic.jpg" alt="미리보기 이미지"><br>
					<input type="file" id="productImage" name="productImage" size="8">
				</p>
				<p>
					<br>
					공급처 제품 번호&nbsp;:&nbsp;<input type="text" id="storeProductOriginalName" name="storeProductOriginalName" value=""></input><br>
					<br>
					평균 중량&nbsp;:&nbsp;<input type="text" name="productAveWeight" id="productAveWeight" value=""></input><br>
					<br>
					기성 옵션 이름&nbsp;:&nbsp;<input type="text" id="optionName" name="optionName" value=""></input><br>
					<br>
					제품 공임&nbsp;:&nbsp;<input type="text" id="storeProductPrice" name="storeProductPrice" value="" ></input><br>
					<br>
					<input type="button" id="insertStoreProduct" value="제품 등록" disabled>
				</p>
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