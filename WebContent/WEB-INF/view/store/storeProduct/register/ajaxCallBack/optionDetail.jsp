<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<div>
	<p class="productOption_title">조각</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(productCzSizeList)}">
			<p>
				<select name="czSize">
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
				<input type="text" name="czQty" size="4" maxlength="4" value ="${productCzSizeList[i-1].czQty }">
				<select name="cz">
					<option value="0" selected>--</option>
					<c:forEach var="czList" items="${requestScope.czListResponse[i-1] }">
						<option value="${czList.czSeq }">${czList.czName }</option>
					</c:forEach>
				</select>
			</p>
		</c:if>
		<c:if test="${i > fn:length(productCzSizeList)}">
			<p>
				<select name="czSize">
					<option value="0" selected>--</option>
					<c:forEach var="czSize" items="${requestScope.czSizeList }">
						<option value="${czSize.czSizeSeq }">${czSize.czSize }</option>
					</c:forEach>	
				</select>
				<input type="text" name="czQty" size="4" maxlength="4" value ="0">
				<select name="cz">
					<option value="0" selected>--</option>
				</select>
			</p>
		</c:if>
	</c:forEach>
</div>
<div>
	<p class="productOption_title">스톤</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(productStoneSizeList)}">
			<p>
				<select name="stoneSize">
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
				<input type="text" name="stoneQty" size="4" maxlength="4" value ="${productStoneSizeList[i-1].stoneQty }">
				<select name="stone">
					<option value="0" selected>--</option>
					<c:forEach var="stoneList" items="${requestScope.stoneListResponse[i-1] }">
						<option value="${stoneList.stoneSeq }">${stoneList.stoneName }</option>
					</c:forEach>
				</select>
			</p>
		</c:if>
		<c:if test="${i > fn:length(productStoneSizeList)}">
			<p>
				<select name="stoneSize">
					<option value="0" selected>--</option>
					<c:forEach var="stoneSize" items="${requestScope.stoneSizeList }">
						<option value="${stoneSize.stoneSizeSeq }">${stoneSize.stoneSize }</option>
					</c:forEach>	
				</select>
				<input type="text" name="stoneQty" size="4" maxlength="4" value ="0">
				<select name="stone">
					<option value="0" selected>--</option>
				</select>
			</p>
		</c:if>
	</c:forEach>
</div>
<div>
	<p class="productOption_title">부속</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(productPartAttributeList)}">
			<p>
				<select name="partAttribute" >
					<option value="0" selected>--</option>
					<c:forEach var="partAttribute" items="${requestScope.partAttributeList }">
						<c:if test="${productPartAttributeList[i-1].partAttributeSeq == partAttribute.partAttributeSeq }">
							<option value="${partAttribute.partAttributeSeq }" selected>${partAttribute.partAttribute }</option>
						</c:if>
						<c:if test="${productPartAttributeList[i-1].partAttributeSeq != partAttribute.partAttributeSeq }">
							<option value="${partAttribute.partAttributeSeq }">${partAttribute.partAttribute }</option>ion>
						</c:if>
					</c:forEach>	
				</select>
				<select name="part">
					<option value="0" selected>--</option>
					<c:forEach var="partList" items="${requestScope.partListResponse[i-1] }">
						<option value="${partList.partSeq }">${partList.partName }</option>
					</c:forEach>
				</select>
			</p>
		</c:if>
		<c:if test="${i > fn:length(productPartAttributeList)}">
			<p>
				<select name="partAttribute">
					<option value="0" selected>--</option>
					<c:forEach var="partAttribute" items="${requestScope.partAttributeList }">
						<option value="${partAttribute.partAttributeSeq }">${partAttribute.partAttribute }</option>
					</c:forEach>	
				</select>
				<select name="part">
					<option value="0" selected>--</option>
				</select>
			</p>
		</c:if>
	</c:forEach>
</div>
<div>
	<p class="productOption_title">추가 공정</p>
	<c:forEach var="addList" items="${requestScope.addList }">
		<p>
			<input type="checkbox" name="add" value="${addList.addSeq}" ><label for="add">${addList.addName }</label>
		</p>
	</c:forEach>
</div>