<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<p>
옵션 번호 : 
<select id="optionSelect">
	<c:forEach var="optionDtoList" items="${requestScope.optionDtoList }" varStatus="loopCount">
		<c:if test="${optionDtoList.optionNumber == optionNumber }">
			<option value="${optionDtoList.optionSeq }" selected>${optionDtoList.optionNumber } - ${optionDtoList.optionName }</option>
		</c:if>
		<c:if test="${optionDtoList.optionNumber != optionNumber }">
			<option value="${optionDtoList.optionSeq }">${optionDtoList.optionNumber } - ${optionDtoList.optionName }</option>
		</c:if>
	</c:forEach>
</select>

총공임 : ${optionDto.optionPrice }
</p>