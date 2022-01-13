<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<div>
	<p class="productOption_title">조각 사이즈 선택 개수</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(productCzSizeList)}">
			<p>
				<span>조각${i} :</span>
				<select id="czSize${i}" name="czSize${i}">
					<option value="0">--</option>
					<c:forEach var="czSize" items="${requestScope.czSizeList }">
						<c:if test="${productCzSizeList[i-1].czSizeSeq == czSize.czSizeSeq }">
							<option value="${czSize.czSizeSeq }" selected>${czSize.czSize }</option>
						</c:if>
						<c:if test="${productCzSizeList[i-1].czSizeSeq != czSize.czSizeSeq }">
							<option value="${czSize.czSizeSeq }">${czSize.czSize }</option>
						</c:if>
					</c:forEach>	
				</select>
				<input type="text" id="czQty${i}" name="czQty${i}" size="4" maxlength="4" value ="${productCzSizeList[i-1].czQty }">
			</p>
		</c:if>
		<c:if test="${i > fn:length(productCzSizeList)}">
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
		</c:if>
	</c:forEach>
</div>
<div>
	<p class="productOption_title">스톤 사이즈 선택 개수</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(productStoneSizeList)}">
			<p>
				<span>스톤${i} :</span>
				<select id="stoneSize${i}" name="stoneSize${i}">
					<option value="0">--</option>
					<c:forEach var="stoneSize" items="${requestScope.stoneSizeList }">
						<c:if test="${productStoneSizeList[i-1].stoneSizeSeq == stoneSize.stoneSizeSeq }">
							<option value="${stoneSize.stoneSizeSeq }" selected>${stoneSize.stoneSize }</option>
						</c:if>
						<c:if test="${productStoneSizeList[i-1].stoneSizeSeq != stoneSize.stoneSizeSeq }">
							<option value="${stoneSize.stoneSizeSeq }">${stoneSize.stoneSize }</option>
						</c:if>
					</c:forEach>	
				</select>
				<input type="text" id="stoneQty${i}" name="stoneQty${i}" size="4" maxlength="4" value ="${productStoneSizeList[i-1].stoneQty }">
			</p>
		</c:if>
		<c:if test="${i > fn:length(productStoneSizeList)}">
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
		</c:if>
	</c:forEach>
</div>
<div>
	<p class="productOption_title">부속 속성 선택</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(productPartAttributeLsit)}">
			<p>
				<span>부속${i} :</span>
				<select id="partAttribute${i}" name="partAttribute${i}"  class="productAttributeSelect">
					<option value="0" selected>--</option>
					<c:forEach var="partAttribute" items="${requestScope.partAttributeList }">
						<c:if test="${productPartAttributeLsit[i-1].partAttributeSeq == partAttribute.partAttributeSeq }">
							<option value="${partAttribute.partAttributeSeq }" selected>${partAttribute.partAttribute }</option>
						</c:if>
						<c:if test="${productPartAttributeLsit[i-1].partAttributeSeq != partAttribute.partAttributeSeq }">
							<option value="${partAttribute.partAttributeSeq }">${partAttribute.partAttribute }</option>ion>
						</c:if>
					</c:forEach>	
				</select>
			</p>
		</c:if>
		<c:if test="${i > fn:length(productPartAttributeLsit)}">
			<p>
				<span>부속${i} :</span>
				<select id="partAttribute${i}" name="partAttribute${i}"  class="productAttributeSelect">
					<option value="0" selected>--</option>
					<c:forEach var="partAttribute" items="${requestScope.partAttributeList }">
						<option value="${partAttribute.partAttributeSeq }">${partAttribute.partAttribute }</option>
					</c:forEach>	
				</select>
			</p>
		</c:if>
	</c:forEach>
</div>