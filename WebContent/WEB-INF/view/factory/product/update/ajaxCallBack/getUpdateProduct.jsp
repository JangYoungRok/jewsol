<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:if test="${searchProductState == 2 }">
	${searchProductState },
	없는 제품 입니다.
</c:if>
<c:if test="${searchProductState == 1 }">
	${searchProductState },
	검색이 완료 되었습니다.,
	${product.productImage },
	${product.productName },
	${product.productAveWeight },
	<jsp:include page="/WEB-INF/view/common/productOption.jsp" />
	
</c:if>
