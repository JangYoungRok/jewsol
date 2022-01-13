<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:if test="${fn:length(customerList) == 0 }">
</c:if>
<c:if test="${fn:length(customerList) != 0 }">
<ul id="customerList">
	<c:forEach var="customerList" items="${customerList }">
	<li id="${customerList.customerSeq }">
		${customerList.customerArea } ${customerList.customerSection } ${customerList.customerName }
	</li>
	</c:forEach>
</ul>
</c:if>