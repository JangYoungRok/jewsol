<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<div>
	<c:if test="${fn:length(productCzSizeList) > 0 }">
		<p class="productOption_title">조각</p>
		<c:forEach var="productCzSizeList" items="${requestScope.productCzSizeList }">
			<p>${productCzSizeList.czSize } = ${productCzSizeList.czQty }</p>
		</c:forEach>
	</c:if>
</div>
<div>
	<c:if test="${fn:length(productStoneSizeList) > 0 }">
		<p class="productOption_title">스톤</p>
		<c:forEach var="productStoneSizeList" items="${requestScope.productStoneSizeList }">
			<p>${productStoneSizeList.stoneSize } = ${productStoneSizeList.stoneQty }</p>
		</c:forEach>
	</c:if>
</div>
<div>
	<c:if test="${fn:length(productPartAttributeLsit) > 0 }">
		<p class="productOption_title">부속</p>
		<c:forEach var="productPartAttributeLsit" items="${requestScope.productPartAttributeLsit }">
			<p>${productPartAttributeLsit.partAttribute }</p>
		</c:forEach>
	</c:if>
</div>