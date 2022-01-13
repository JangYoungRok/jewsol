<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:forEach var="addList" items="${requestScope.addList }" >
	<p id="${addList.addSeq }">
		
		<span>
			${addList.addName }							
		</span>
		
		<span>
			${addList.addCost } 원						
		</span>
		
		<span>
			${addList.addLabor } 원
		</span>
		
		<span>
			${addList.addPrice } 원
		</span>
	</p>
</c:forEach>