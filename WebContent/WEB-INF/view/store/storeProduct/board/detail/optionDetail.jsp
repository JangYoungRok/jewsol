<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<div>
	<c:if test="${fn:length(optionCzList) > 0 }">
		<p class="optionTitle">조각 - ${optionCzPrice } 원</p>
		<c:forEach var="optionCzList" items="${requestScope.optionCzList }">
			<p>${optionCzList.czName } : ${optionCzList.czSize } = ${optionCzList.optionCzQty }</p>
			<p>${optionCzList.optionCzPrice }</p>
		</c:forEach>
	</c:if>
</div>
<div>
	<c:if test="${fn:length(optionStoneList) > 0 }">
		<p class="optionTitle">스톤 - ${optionStonePrice } 원</p>
		<c:forEach var="optionStoneList" items="${requestScope.optionStoneList }">
			<p>${optionStoneList.stoneName } : ${optionStoneList.stoneSize } = ${optionStoneList.optionStoneQty }</p>
			<p>${optionStoneList.optionStonePrice }</p>
		</c:forEach>
	</c:if>
</div>
<div>
	<c:if test="${fn:length(optionPartList) > 0 }">
		<p class="optionTitle">부속 - ${optionPartPrice } 원</p>
		<c:forEach var="optionPartList" items="${requestScope.optionPartList }">
			<p>${optionPartList.partName }</p>
		</c:forEach>
	</c:if>
</div>
<div>
	<c:if test="${fn:length(optionAddList) > 0 }">
		<p class="optionTitle">부속 - ${optionAddPrice } 원</p>
		<c:forEach var="optionAddList" items="${requestScope.optionAddList }">
			<p>${optionAddList.addName }</p>
		</c:forEach>
	</c:if>
</div>