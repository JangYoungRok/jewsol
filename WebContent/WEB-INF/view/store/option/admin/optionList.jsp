<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="exist" value="false" />
<c:set var="selected" value="false" />
<c:forEach var="i" begin="1" end="8">
	<c:forEach var="optionDto" items="${optionDtoList }">
		<c:if test="${i == optionDto.optionNumber }">
			<c:set var="exist" value="true" />
			<c:if test="${optionDto.optionNumber == optionNumber }">
				<c:set var="selected" value="true" />
			</c:if>
		</c:if>
	</c:forEach>
	<c:if test="${exist == true && selected == true}">
		<c:forEach var="optionDto" items="${optionDtoList }">
			<c:if test="${i == optionDto.optionNumber}">
				<option value="${optionDto.optionSeq }" selected="selected">${optionDto.optionNumber} -- ${optionDto.optionName}</option>
			</c:if>
		</c:forEach>
	</c:if>
	<c:if test="${exist == true && selected == false }">
		<c:forEach var="optionDto" items="${optionDtoList }">
			<c:if test="${optionDto.optionNumber == i}">
				<option value="${optionDto.optionSeq }">${optionDto.optionNumber} -- ${optionDto.optionName}</option>
			</c:if>
		</c:forEach></c:if>
	<c:if test="${exist == false && selected == false }">
		<option value="0">${i} -- 미입력</option>
	</c:if>
	<c:set var="exist" value="false" />
	<c:set var="selected" value="false" />
</c:forEach>


			

			