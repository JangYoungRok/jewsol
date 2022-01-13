<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<select id="saleType">
	<c:forEach var="saleType" items="${saleTypeList }">
		<option value="${saleType.saleTypeSeq }">${saleType.saleTypeName }</option>
	</c:forEach>
</select>