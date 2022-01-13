<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:if test="${searchStoreProductState == 2 }">
	${searchStoreProductState },
	${systemMessage }
</c:if>
<c:if test="${searchStoreProductState == 1 }">
	${searchStoreProductState },
	${systemMessage },
	${product.productImage },
	${product.productName },
	${product.productAveWeight },
	<jsp:include page="/WEB-INF/view/store/storeProduct/register/ajaxCallBack/optionDetail.jsp" />
	
</c:if>