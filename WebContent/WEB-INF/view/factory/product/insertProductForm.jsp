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
	<link rel="stylesheet" href="/jewsol/css/factory/product/insertProductForm.css">
	<title>제품 입력</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/factory/product/insertProductFormCheckOverLap.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/factory/product/insertProductForm.js"></script>
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
		<form id="insertProductForm" name="insertProductForm" enctype="multipart/form-data" method="POST" action="/jewsol/factory/product/insertProduct.do">
			<article id="productInfoArticle">
				<div id = "productImageDiv">
					<img id="prev_img" src="/jewsol/image/noPic.jpg" alt="미리보기 이미지"><br>
					<input type="file" id="productImage" name="productImage" size="8">
				</div>
				<div id = "productInfoDiv">
					<P>
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
					</P>
					<P>
						<label><span>제품번호 :</span> <input type="text" id="productCode" name="productCode" size="4" maxlength="4" required></label>
					</P>
					<P>
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
						<input type="button" id="checkOverLapBtn" value="중복체크" disabled="disabled">
					</P>
					<P>
						<label><span>평균중량 :</span>
							<input type="text" id="productAveWeight" name="productAveWeight" value="0.0" size="6" maxlength="6">
						</label>
					</P>
				</div>
			</article>
			<article id="productOptionInfoArticle">
				<div>
					<p class="productOption_title">조각 사이즈 선택 개수</p>
					<c:forEach var="i" begin="1" end="9">
						<p>
							<span>조각${i} :</span>
							<select id="czSize${i}" name="czSize${i}">
								<option value="0" selected>--</option>
								<c:forEach var="czSize" items="${requestScope.czSizeList }">
									<option value="${czSize.czSizeSeq }">${czSize.czSize }</option>
								</c:forEach>	
							</select>
							<input type="text" id="czQty${i}" name="czQty${i}" size="4" maxlength="4" value ="0">
						</p>
					</c:forEach>
				</div>
				<div>
					<p class="productOption_title">스톤 사이즈 선택 개수</p>
					<c:forEach var="i" begin="1" end="9">
						<p>
							<span>스톤${i} :</span>
							<select id="stoneSize${i}" name="stoneSize${i}">
								<option value="0" selected>--</option>
								<c:forEach var="stoneSize" items="${requestScope.stoneSizeList }">
									<option value="${stoneSize.stoneSizeSeq }">${stoneSize.stoneSize }</option>
								</c:forEach>	
							</select>
							<input type="text" id="stoneQty${i}" name="stoneQty${i}" size="4" maxlength="4" value ="0">
						</p>
					</c:forEach>
				</div>
				<div>
					<p class="productOption_title">부속 속성 선택</p>
					<c:forEach var="i" begin="1" end="9">
						<p>
							<span>부속${i} :</span>
							<select id="partAttribute${i}" name="partAttribute${i}"  class="productAttributeSelect">
								<option value="0" selected>--</option>
								<c:forEach var="partAttribute" items="${requestScope.partAttributeList }">
									<option value="${partAttribute.partAttributeSeq }">${partAttribute.partAttribute }</option>
								</c:forEach>	
							</select>
						</p>
					</c:forEach>
				</div>
			</article>
			<article id="productSubmitArticle">
				<input type="button" id="insertProductBtn" value="제품 입력" disabled="disabled">
			</article>
		</form>
	</section>
	<footer id="footer">
		<article id="systemMessagePanel">
			<p>시스템 메세지</p>
			<p><span id="systemMessage">
				<c:if test="${productName != null }">
					${productName } 제품이 입력 되었습니다.
				</c:if>
			</span></p>
		</article>
	</footer>
</body>
</html>