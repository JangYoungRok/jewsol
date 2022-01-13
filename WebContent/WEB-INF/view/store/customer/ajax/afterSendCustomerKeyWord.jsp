<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<ul id="customerList">
	<c:forEach var="customer" items="${customerList }">
		<li id="${customer.customerSeq }">
			${customer.customerArea } ${customer.customerSection } ${customer.customerName }  
		</li>
	</c:forEach>
</ul>