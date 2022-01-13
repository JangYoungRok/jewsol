<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="balanceGold" value="0" />
<c:set var="balanceCash" value="0" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now }"  pattern="yyyy-MM-dd" />
<fmt:formatDate var="todayYearMonth" value="${now }"  pattern="yyyy-MM"/>
<c:out value="${today }" />
	<c:forEach var="j" begin="0" end="${fn:length(customerTypeList) - 1 }">
		<c:out value="${customerTypeList[j].customerType }"/>
		
		<c:set var="sumSaleSheetMap" value="${sumSaleSheetMapByCustomerType[j] }" />
		<c:set var="sumSaleSheetMapSortedKey" value="${sumSaleSheetMapSortedKeyByCustomerType[j] }" />
		
		<table>
			<c:forEach var="key" items="${sumSaleSheetMapSortedKey }">
					<tr>
						<td>
							${key }
						</td>
						<td>
							<fmt:formatNumber value="${sumSaleSheetMap[key].saleGold }" pattern="0.00"/>g
							<fmt:formatNumber value="${sumSaleSheetMap[key].saleCash }" type="number" maxFractionDigits="3" />&#8361; 
						</td>
					</tr>
					<c:if test="${todayYearMonth ne key }">
						<c:set var="balanceGold" value="${balanceGold + sumSaleSheetMap[key].saleGold}" />
						<c:set var="balanceCash" value="${balanceCash + sumSaleSheetMap[key].saleCash}" />
					</c:if>
			</c:forEach>
			<tr>
				<td>
					
				</td>
				<td>
					<fmt:formatNumber value="${balanceGold }" pattern="0.00"/>g
					<fmt:formatNumber value="${balanceCash }" type="number" maxFractionDigits="3" />&#8361; 
				</td>
			</tr>
		</table>
		<c:set var="balanceGold" value="${0}" />
		<c:set var="balanceCash" value="${0}" />
		<c:set var="totalCustomer" value="${0}" />
	</c:forEach>


 