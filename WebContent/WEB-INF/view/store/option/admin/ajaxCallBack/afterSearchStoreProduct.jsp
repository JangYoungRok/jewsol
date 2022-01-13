<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:if test="${searchOptionState == 1 }">
	${searchOptionState},
	${storeProductName},
	${optionName},
	<jsp:include page="${optionList }" />,
	<jsp:include page="${optionDetail }" />
</c:if>
<c:if test="${searchOptionState == 2 }">
	${searchOptionState}
</c:if>
<c:if test="${searchOptionState == 3 }">
	${searchOptionState}
</c:if>


