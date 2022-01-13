<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:forEach var="optionDto" items="${optionDtoList }">
	<option>${optionDto.optionNumber} -- ${optionDto.optionName}</option>
</c:forEach>
<option>9 -- 변형</option>

			

			