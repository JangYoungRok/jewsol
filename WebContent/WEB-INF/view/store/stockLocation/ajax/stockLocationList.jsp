<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:forEach var="stockLocationList" items="${requestScope.stockLocationList }" >
	<p id="${stockLocationList.stockLocationSeq }">
		<span>
			${stockLocationList.stockLocationName }							
		</span>
		
		<span>
			${stockLocationList.stockLocationType }						
		</span>
	</p>
</c:forEach>