<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:choose>
	<c:when test="${overLapResult == 1 }">
		1
	</c:when>
	<c:when test="${overLapResult == 0 }">
		0
	</c:when>
</c:choose>