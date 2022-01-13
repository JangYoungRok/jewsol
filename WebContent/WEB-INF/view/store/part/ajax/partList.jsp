<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:forEach var="partList" items="${requestScope.partList }" >
	<p id="${partList.partSeq }">
		<span>
			${partList.partAttribute }
		</span>
		
		<span>
			${partList.partName }							
		</span>
		
		<span>
			${partList.partCost } 원						
		</span>
		
		<span>
			${partList.partLabor } 원
		</span>
		
		<span>
			${partList.partPrice } 원
		</span>
	</p>
</c:forEach>