<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<div>
	<p class="title">조각</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(orderOptionCzList)}">
			<p>
				<select name="czSize">
					<c:forEach var="czSize" items="${requestScope.czSizeList }">
						<c:if test="${orderOptionCzSizeSeqList[i-1] == czSize.czSizeSeq }">
							<option value="${czSize.czSizeSeq }" selected>${czSize.czSize }</option>
						</c:if>
					</c:forEach>	
				</select>
				<input type="text" name="czQty" size="4" maxlength="4" value ="${orderOptionCzList[i-1].orderOptionCzQty }" readonly>
				<select name="cz">
					<c:forEach var="czList" items="${requestScope.czListResponse[i-1] }">
						<c:if test="${orderOptionCzList[i-1].czSeq == czList.czSeq }">
							<option value="${czList.czSeq }" selected="selected">${czList.czName }</option>
						</c:if>
					</c:forEach>
				</select>
			</p>
		</c:if>
	</c:forEach>
</div>
<div>
	<p class="title">스톤</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(orderOptionStoneList)}">
			<p>
				<select name="stoneSize">
					<c:forEach var="stoneSize" items="${requestScope.stoneSizeList }">
						<c:if test="${orderOptionStoneSizeSeqList[i-1] == stoneSize.stoneSizeSeq }">
							<option value="${stoneSize.stoneSizeSeq }" selected>${stoneSize.stoneSize }</option>
						</c:if>
					</c:forEach>	
				</select>
				<input type="text" name="stoneQty" size="4" maxlength="4" value ="${orderOptionStoneList[i-1].orderOptionStoneQty }" readonly>
				<select name="stone">
					<c:forEach var="stoneList" items="${requestScope.stoneListResponse[i-1] }">
						<c:if test="${orderOptionStoneList[i-1].stoneSeq == stoneList.stoneSeq }">
							<option value="${stoneList.stoneSeq }" selected="selected">${stoneList.stoneName }</option>
						</c:if>
					</c:forEach>
				</select>
			</p>
		</c:if>
	</c:forEach>
</div>
<div id="optionPartDiv">
	<p class="title">부속</p>
	<c:forEach var="i" begin="1" end="9">
		<c:if test="${i <= fn:length(orderOptionPartList)}">
			<p>
				<select name="partAttribute" >
					<option value="0" selected>--</option>
					<c:forEach var="partAttribute" items="${requestScope.partAttributeList }">
						<c:if test="${orderOptionPartAttributeSeqList[i-1] == partAttribute.partAttributeSeq }">
							<option value="${partAttribute.partAttributeSeq }" selected>${partAttribute.partAttribute }</option>
						</c:if>
						<c:if test="${orderOptionPartAttributeSeqList[i-1] != partAttribute.partAttributeSeq }">
							<option value="${partAttribute.partAttributeSeq }">${partAttribute.partAttribute }</option>
						</c:if>
					</c:forEach>	
				</select>
				<select name="part">
					<option value="0">--</option>
					<c:forEach var="partList" items="${requestScope.partListResponse[i-1] }">
						<c:if test="${orderOptionPartList[i-1].partSeq == partList.partSeq }">
							<option value="${partList.partSeq }" selected="selected">${partList.partName }</option>
						</c:if>
						<c:if test="${orderOptionPartList[i-1].partSeq != partList.partSeq }">
							<option value="${partList.partSeq }">${partList.partName }</option>
						</c:if>
					</c:forEach>
				</select>
			</p>
		</c:if>
		<c:if test="${i > fn:length(orderOptionPartList)}">
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
	<p class="title">추가 공정</p>
		
		<c:set var="checkAdd" value="false" />
		<c:forEach var="addList" items="${requestScope.addList }">
		<p>
			<c:forEach var="orderOptionAdd" items="${requestScope.orderOptionAddList }">
				<c:if test="${orderOptionAdd.addSeq eq addList.addSeq}">
					<input type="checkbox" name="add" value="${addList.addSeq}" checked="checked"><label for="add">${addList.addName }</label>
					<c:set var="checkAdd" value="true" />
				</c:if>
			</c:forEach>
			<c:if test="${not checkAdd}">
				<input type="checkbox" name="add" value="${addList.addSeq}" ><label for="add">${addList.addName }</label>
			</c:if>
			<br>
			<c:set var="checkAdd" value="false" />
		</p>
		</c:forEach>
</div>