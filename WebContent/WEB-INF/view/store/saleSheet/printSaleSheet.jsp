<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>판매 장끼 인쇄</title>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/store/saleSheet/printSaleSheet.css"/>
</head>
<body>
	<c:set var="orderLength" value="${fn:length(orderList) }"/>
	<c:set var="pageSize" value="18"/>
	<fmt:parseNumber var="totalPage" integerOnly="true" value="${((orderLength + pageSize -1) / pageSize)}"/>
	<c:if test="${totalPage == 0 }">
		<c:set var="totalPage" value="1" />
	</c:if>
	<c:forEach var="page" begin="0" end="${totalPage - 1}">
		<section class="a4Sheet">
			<article class="sheet">
				<div class="content">
					<div class="date">
						<fmt:parseDate value="${saleSheet.saleSheetDate }" var="saleDate" pattern="yyyy-MM-dd"/>
						<fmt:formatDate value="${saleDate }" pattern="yyyy년  MM월  dd일" />
					</div>
					<div class="company">
						<div class="customer">
							<p class="customerName">
								${customer.customerArea} ${customer.customerSection } ${customer.customerName }
							</p>
						</div>
						<div class="branch">
							<p class="branchName">W Jewelry</p>
							<p class="branchInfo">TEL 02)741-2615&nbsp;&nbsp;&nbsp;&nbsp;FAX 02)741-2616</p>
						</div>
					</div>
					<div class="balance">
						<div class="lastBalance">
							순금<br>
							전잔 : <fmt:formatNumber value="${saleSheet.lastBalanceGold }" pattern="0.00"/>g<br>
							미수 : <fmt:formatNumber value="${saleSheet.balanceGold }" pattern="0.00"/>g<br>
							
						</div>
						<div class="current">
							공임<br>
							전잔 : <fmt:formatNumber type="currency" value="${saleSheet.lastBalanceCash }" pattern="###,###"/>&#8361;<br>
							미수 : <fmt:formatNumber type="currency" value="${saleSheet.balanceCash }" pattern="###,###"/>&#8361;<br>
						</div>
						<div class="release">
							<div>
								14K : <fmt:formatNumber value="${saleSheet.total14Weight }" pattern="0.00"/>g<br>
								18K : <fmt:formatNumber value="${saleSheet.total18Weight }" pattern="0.00"/>g<br>
								10K : <fmt:formatNumber value="${saleSheet.total10Weight }" pattern="0.00"/>g
							</div>
							<div>
								순금 : <fmt:formatNumber value="${saleSheet.totalGoldWeight }" pattern="0.00"/>g<br>
								공임 : <fmt:formatNumber type="currency" value="${saleSheet.totalSalePrice }" pattern="###,###"/>&#8361;<br>
							</div>
						</div>
					</div>
					<div class="sale">
						<table class="saleList">
							<thead>
								<tr>
									<th>
										<div class="K">
											K
										</div>
									</th>
									<th>
										<div class="storeProductName">
											제품번호
										</div>
									</th>
									<th>
										<div class="cz">
											CZ
										</div>
									</th>
									<th>
										<div class="orderEtc">
											기타
										</div>
									</th>
									<th>
										<div class="orderWeight">
											총중량
										</div>
									</th>
									<th>
										<div class="orderLabor">
											총공임
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="i" begin="0" end="${pageSize - 1 }">
									<tr>
										<td>
											<div class="K">
												${orderList[page*pageSize+i].orderK }
											</div>
										</td>
										<td>
											<div class="storeProductName">
												${orderList[page*pageSize+i].storeProductName }
											</div>
										</td>
										<td>
											<div class="cz">
												${orderList[page*pageSize+i].totalCzQty }
											</div>
										</td>
										<td>
											<div class="orderEtc">
												${orderList[page*pageSize+i].orderOptionName } ${orderList[page*pageSize+i].orderEtc } 
											</div>
										</td>
										<td>
											<div class="orderWeight">
												<fmt:formatNumber value="${orderList[page*pageSize+i].orderSaleWeight }" pattern="0.00"/>
											</div>
										</td>
										<td>
											<div class="orderLabor">
												<fmt:formatNumber type="currency" value="${orderList[page*pageSize+i].orderSalePrice }" pattern="###,###"/>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</article>
			<article class="sheet">
				<div class="content">
					<div class="date">
						<fmt:parseDate value="${saleSheet.saleSheetDate }" var="saleDate" pattern="yyyy-MM-dd"/>
						<fmt:formatDate value="${saleDate }" pattern="yyyy년  MM월  dd일" />
					</div>
					<div class="company">
						<div class="customer">
							<p class="customerName">
								${customer.customerArea} ${customer.customerSection } ${customer.customerName }
							</p>
						</div>
						<div class="branch">
							<p class="branchName">W Jewelry</p>
							<p class="branchInfo">TEL 02)741-2615&nbsp;&nbsp;&nbsp;&nbsp;FAX 02)741-2616</p>
						</div>
					</div>
					<div class="balance">
						<div class="lastBalance">
							순금<br>
							전잔 : <fmt:formatNumber value="${saleSheet.lastBalanceGold }" pattern="0.00"/>g<br>
							미수 : <fmt:formatNumber value="${saleSheet.balanceGold }" pattern="0.00"/>g<br>
							
						</div>
						<div class="current">
							공임<br>
							전잔 : <fmt:formatNumber type="currency" value="${saleSheet.lastBalanceCash }" pattern="###,###"/>&#8361;<br>
							미수 : <fmt:formatNumber type="currency" value="${saleSheet.balanceCash }" pattern="###,###"/>&#8361;<br>
						</div>
						<div class="release">
							<div>
								14K : <fmt:formatNumber value="${saleSheet.total14Weight }" pattern="0.00"/>g<br>
								18K : <fmt:formatNumber value="${saleSheet.total18Weight }" pattern="0.00"/>g<br>
								10K : <fmt:formatNumber value="${saleSheet.total10Weight }" pattern="0.00"/>g
							</div>
							<div>
								순금 : <fmt:formatNumber value="${saleSheet.totalGoldWeight }" pattern="0.00"/>g<br>
								공임 : <fmt:formatNumber type="currency" value="${saleSheet.totalSalePrice }" pattern="###,###"/>&#8361;<br>
							</div>
						</div>
					</div>
					<div class="sale">
						<table class="saleList">
							<thead>
								<tr>
									<th>
										<div class="K">
											K
										</div>
									</th>
									<th>
										<div class="storeProductName">
											제품번호
										</div>
									</th>
									<th>
										<div class="cz">
											CZ
										</div>
									</th>
									<th>
										<div class="orderEtc">
											기타
										</div>
									</th>
									<th>
										<div class="orderWeight">
											총중량
										</div>
									</th>
									<th>
										<div class="orderLabor">
											총공임
										</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="i" begin="0" end="${pageSize - 1 }">
									<tr>
										<td>
											<div class="K">
												${orderList[page*pageSize+i].orderK }
											</div>
										</td>
										<td>
											<div class="storeProductName">
												${orderList[page*pageSize+i].storeProductName }
											</div>
										</td>
										<td>
											<div class="cz">
												${orderList[page*pageSize+i].totalCzQty }
											</div>
										</td>
										<td>
											<div class="orderEtc">
												${orderList[page*pageSize+i].orderOptionName } ${orderList[page*pageSize+i].orderEtc } 
											</div>
										</td>
										<td>
											<div class="orderWeight">
												<fmt:formatNumber value="${orderList[page*pageSize+i].orderSaleWeight }" pattern="0.00"/>
											</div>
										</td>
										<td>
											<div class="orderLabor">
												<fmt:formatNumber type="currency" value="${orderList[page*pageSize+i].orderSalePrice }" pattern="###,###"/>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</article>
		</section>
	</c:forEach>
</body>
</html>