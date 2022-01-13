<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
${retrievedCategory },
${retrievedProductCode },
<c:forEach var="i" begin="0" end="3">
	<p>
	<c:forEach var="j" begin="0" end="3">
		<c:if test="${storeProductList[i*4+j].storeProductSeq == null }">
			<span id=""></span>
		</c:if>
		<c:if test="${storeProductList[i*4+j].storeProductSeq != null }">
			<span id="${storeProductList[i*4+j].storeProductSeq }">
				<img src="/jewsol/image/${storeProductList[i*4+j].storeProductImage }" alt="${storeProductList[i*4+j].storeProductName }"><br>
				${storeProductList[i*4+j].storeProductName } [${storeProductList[i*4+j].storeProductAveWeight }g]
			</span>
		</c:if>
	</c:forEach>
	</p>
</c:forEach>
,${pageNavigation }