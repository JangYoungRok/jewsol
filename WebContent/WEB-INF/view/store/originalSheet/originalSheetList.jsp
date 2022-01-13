<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<table id="originalSheetList">
	<thead>
		<tr>
			<th>원장번호</th>
			<th>주문개수</th>
		</tr>
	</thead>
	<tbody id="orderList">
		<c:forEach var="originalSheetDto" items="${requestScope.originalSheetDtoList }" varStatus="status">
			<tr id="${originalSheetDto.originalSheetSeq }">
				<td>
					${originalSheetDto.originalSheetNumber }
				</td>
				<td>
					${originalSheetDto.originalSheetQty }
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>