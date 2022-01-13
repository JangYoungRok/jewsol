<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:forEach var="czList" items="${requestScope.czList }" >
	<p id="${czList.czSeq }">
		<span>
			${czList.czSize }
		</span>
		
		<span>
			<input type="text" name="czName" value="${czList.czName }" readonly="readonly">							
		</span>
		
		<span>
			<input type="text" name="czLabor" value="${czList.czLabor }" readonly="readonly">원
		</span>
		
		<span>
			<input type="text" name="czPrice" value="${czList.czPrice}" readonly="readonly">원
		</span>
	</p>
</c:forEach>