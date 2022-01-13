<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<select id="branchList">
	<c:forEach var="branch" items="${branchList }">
		<option id="${branch.branchSeq} ">
			${branch.branchName}
		</option>
	</c:forEach>
</select>
