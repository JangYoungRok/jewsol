<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="balanceGold" value="0" />
<c:set var="balanceCash" value="0" />
<c:set var="totalCustomer" value="0" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now }"  pattern="yyyy-MM-dd" />
<fmt:parseDate value="${today }" var="today" pattern="yyyy-MM-dd"/>
<fmt:parseNumber var="todayDate" value="${today.time/(24*60*60*1000)}" integerOnly="true"/>
<c:set var="LastPaymentClass" value="" />

	<c:forEach var="j" begin="0" end="${fn:length(customerTypeList) - 1 }">
		<c:out value="${customerTypeList[j].customerType }"/>
		<c:set var="customerList" value="${customerListByCustomerType[j] }" />
		<c:set var="lastSaleSheetBeforePaymentDateList" value="${lastSaleSheetBeforePaymentDateListByCustomerType[j] }" />
		<c:set var="customerBalanceList" value="${customerBalanceListByCustomerType[j] }" />
		<c:set var="lastSaleSheetList" value="${lastSaleSheetListByCustomerType[j] }" />
		<c:set var="lastPaymentList" value="${lastPaymentListByCustomerType[j] }" />
		<table>
			<c:forEach var="i" begin="0" end="${fn:length(customerList) - 1 }">
				<c:if test="${fn:length(customerList) > 0 }">
					<fmt:parseDate var="saleSheetDate" value="${lastSaleSheetBeforePaymentDateList[i].saleSheetDate }" pattern="yyyy-MM-dd" />
					<fmt:parseNumber var="saleSheetDateDay" value="${saleSheetDate.time / (1000*60*60*24) }" integerOnly="true" />
					<c:set var="paymentDate" value="${todayDate - saleSheetDateDay }" />
					<c:choose>
						<c:when test="${paymentDate > 30}">
							<c:set var="LastPaymentClass" value="latePayment" />
						</c:when>
					</c:choose>
					
						<c:if test="${(customerBalanceList[i].customerBalanceGold > 0 || customerBalanceList[i].customerBalanceGold < 0) || (customerBalanceList[i].customerBalanceCash > 0 || customerBalanceList[i].customerBalanceCash < 0) }">
							<tr class="${LastPaymentClass }">
								<td>
									${customerList[i].customerArea } ${customerList[i].customerSection } ${customerList[i].customerName } 
								</td>
								<td>
									<fmt:formatNumber value="${customerBalanceList[i].customerBalanceGold }" pattern="0.00"/>g
								</td>
								<td>
									<fmt:formatNumber type="currency" value="${customerBalanceList[i].customerBalanceCash }" pattern="###,###"/>&#8361; 
								</td>
								<td>
									${lastSaleSheetList[i].saleSheetDate} 
								</td>
								<td>
									${lastPaymentList[i].paymentDate} 
								</td>
								<td>
									<c:choose>
										<c:when test="${lastSaleSheetBeforePaymentDateList[i].saleCash eq 0}">
										</c:when>
										<c:when test="${lastSaleSheetBeforePaymentDateList[i] ne null}">
											${lastSaleSheetBeforePaymentDateList[i].saleSheetDate } ${lastSaleSheetBeforePaymentDateList[i].saleGold } ${lastSaleSheetBeforePaymentDateList[i].saleCash }
										</c:when>
									</c:choose>
									
								</td>
								<td>
									<c:choose>
										<c:when test="${paymentDate eq todayDate}">
										</c:when>
										<c:when test="${paymentDate ne todayDate}">
											${paymentDate }일
										</c:when>
									</c:choose>
									
								</td>
							</tr>
							<c:set var="balanceGold" value="${balanceGold +  customerBalanceList[i].customerBalanceGold}" />
							<c:set var="balanceCash" value="${balanceCash +  customerBalanceList[i].customerBalanceCash}" />
							<c:set var="totalCustomer" value="${totalCustomer +  1}" />
						</c:if>
					
					<c:set var="LastPaymentClass" value="" />
				</c:if>
			</c:forEach>
			<tr>
				<td>
					(${totalCustomer}) 
				</td>
				<td>
					<fmt:formatNumber value="${balanceGold }" pattern="0.00"/>g
				</td>
				<td>
					<fmt:formatNumber value="${balanceCash }" type="number" maxFractionDigits="3" />&#8361;
				</td>
				<td>
				</td>
				<td>
				</td>
				<td>
				</td>
			</tr>
		</table>
		<c:set var="balanceGold" value="${0}" />
		<c:set var="balanceCash" value="${0}" />
		<c:set var="totalCustomer" value="${0}" />
	</c:forEach>


 