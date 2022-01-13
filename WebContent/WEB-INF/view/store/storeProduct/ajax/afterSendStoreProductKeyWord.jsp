<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<ul id="storeProductList">
	<c:forEach var="storeProduct" items="${storeProductList }">
		<li id="${storeProduct.storeProductSeq }">
			${storeProduct.storeProductName } <span class="storeProductImage">${storeProduct.storeProductImage }</span> 
		</li>
	</c:forEach>
</ul>