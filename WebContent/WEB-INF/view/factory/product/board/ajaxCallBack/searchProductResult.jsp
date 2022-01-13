<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
${retrievedCategory },
${retrievedProductCode },
<c:forEach var="i" begin="0" end="3">
	<p>
	<c:forEach var="j" begin="0" end="3">
		<c:if test="${productList[i*4+j].productSeq == null }">
			<span id=""></span>
		</c:if>
		<c:if test="${productList[i*4+j].productSeq != null }">
			<span id="${productList[i*4+j].productSeq }">
				<img src="/jewsol/image/${productList[i*4+j].productImage }" alt="${productList[i*4+j].productName }"><br>
				${productList[i*4+j].productName } [${productList[i*4+j].productAveWeight }g]
			</span>
		</c:if>
	</c:forEach>
	</p>
</c:forEach>
,${pageNavigation }