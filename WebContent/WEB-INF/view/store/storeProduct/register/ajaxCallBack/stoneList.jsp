<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
${index },
<option value="0" selected>--</option>
<c:forEach var="stoneList" items="${requestScope.stoneList }">
	<option value="${stoneList.stoneSeq }">${stoneList.stoneName }</option>
</c:forEach>