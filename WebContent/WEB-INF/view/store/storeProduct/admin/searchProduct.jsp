<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<br>
<label><span>분류 :</span>
	<select id="category" name="category">
		<c:forEach var="categoryList" items="${requestScope.categoryList }" varStatus="loopCount">
			<c:if test="${loopCount.count == 1 }">
				<option value="${categoryList.categorySeq }" selected="selected">${categoryList.category }</option>
			</c:if>
			<c:if test="${loopCount.count != 1 }">
				<option value="${categoryList.categorySeq }" >${categoryList.category }</option>
			</c:if>
		</c:forEach>
	</select> 
</label>
<label><span>제품번호 :</span> <input type="text" id="productCode" name="productCode" size="4" maxlength="4" required></label>
<label><span>품목 :</span>
	<select id="type" name="type">
		<c:forEach var="typeList" items="${requestScope.typeList }" varStatus="loopCount">
			<c:if test="${loopCount.count == 1 }">
				<option value="${typeList.typeSeq }" selected="selected">${typeList.type }</option>
			</c:if>
			<c:if test="${loopCount.count != 1 }">
				<option value="${typeList.typeSeq }" >${typeList.type }</option>
			</c:if>
		</c:forEach>
	</select>
</label>
<input type="button" id="searchStoreProductBtn" value="제품 검색">	