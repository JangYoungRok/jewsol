<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:forEach var="stoneList" items="${requestScope.stoneList }" >
	<p id="${stoneList.stoneSeq }">
		<span>
			${stoneList.stoneSize }
		</span>
		
		<span>
			${stoneList.stoneName }							
		</span>
		
		<span>
			${stoneList.stoneCost }	원						
		</span>
		
		<span>
			${stoneList.stoneLabor } 원
		</span>
		
		<span>
			${stoneList.stonePrice } 원
		</span>
	</p>
</c:forEach>