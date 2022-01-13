<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:forEach var="customerList" items="${requestScope.customerList }" >
	<p id="${customerList.customerSeq }">
		<span>
			${customerList.stockLocationType }
		</span>
		
		<span>
			${customerList.customerArea }							
		</span>
		
		<span>
			${customerList.customerSection } 					
		</span>
		
		<span>
			${customerList.customerName }
		</span>
	</p>
</c:forEach>