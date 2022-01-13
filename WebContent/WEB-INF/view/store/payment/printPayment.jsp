<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>결제 장끼 인쇄</title>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/store/payment/printPayment.css"/>
</head>
<body>
	<section class="a4Sheet">
		<article class="sheet">
			<div class="content">
				<div class="date">
					<fmt:parseDate value="${payment.paymentDate}" var="paymentDate" pattern="yyyy-MM-dd"/>
					<fmt:formatDate value="${paymentDate }" pattern="yyyy년  MM월  dd일" />
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
						전잔 : <fmt:formatNumber value="${payment.lastBalanceGold }" pattern="0.00"/>g<br>
						미수 : <fmt:formatNumber value="${payment.balanceGold }" pattern="0.00"/>g<br>
						
					</div>
					<div class="current">
						공임<br>
						전잔 : <fmt:formatNumber type="currency" value="${payment.lastBalanceCash }" pattern="###,###"/>&#8361;<br>
						미수 : <fmt:formatNumber type="currency" value="${payment.balanceCash }" pattern="###,###"/>&#8361;<br>
					</div>
					<div class="release">
						결제<br>
						순금 : ${payment.paymentGold }g<br>
						공임 : <fmt:formatNumber type="currency" value="${payment.paymentCash }" pattern="###,###"/>&#8361;<br>
						DC : <fmt:formatNumber type="currency" value="${payment.paymentDC }" pattern="###,###"/>&#8361;<br>
					</div>
				</div>
				<div class="sale">
					<table class="saleList">
						<thead>
							<tr>
								<th>
									<div class="K">
									</div>
								</th>
								<th>
									<div class="storeProductName">
									</div>
								</th>
								<th>
									<div class="cz">
									</div>
								</th>
								<th>
									<div class="orderEtc">
									</div>
								</th>
								<th>
									<div class="orderWeight">
									</div>
								</th>
								<th>
									<div class="orderLabor">
									</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" begin="1" end="18">
								<tr>
									<td>
										<div class="K">
										</div>
									</td>
									<td>
										<div class="storeProductName">
										</div>
									</td>
									<td>
										<div class="cz">
										</div>
									</td>
									<td>
										<div class="orderEtc"> 
										</div>
									</td>
									<td>
										<div class="orderWeight">
										</div>
									</td>
									<td>
										<div class="orderLabor">
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
					<fmt:parseDate value="${payment.paymentDate}" var="paymentDate" pattern="yyyy-MM-dd"/>
					<fmt:formatDate value="${paymentDate }" pattern="yyyy년  MM월  dd일" />
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
						전잔 : <fmt:formatNumber value="${payment.lastBalanceGold }" pattern="0.00"/>g<br>
						미수 : <fmt:formatNumber value="${payment.balanceGold }" pattern="0.00"/>g<br>
						
					</div>
					<div class="current">
						공임<br>
						전잔 : <fmt:formatNumber type="currency" value="${payment.lastBalanceCash }" pattern="###,###"/>&#8361;<br>
						미수 : <fmt:formatNumber type="currency" value="${payment.balanceCash }" pattern="###,###"/>&#8361;<br>
					</div>
					<div class="release">
						결제<br>
						순금 : ${payment.paymentGold }g<br>
						공임 : <fmt:formatNumber type="currency" value="${payment.paymentCash }" pattern="###,###"/>&#8361;<br>
						DC : <fmt:formatNumber type="currency" value="${payment.paymentDC }" pattern="###,###"/>&#8361;<br>
					</div>
				</div>
				<div class="sale">
					<table class="saleList">
						<thead>
							<tr>
								<th>
									<div class="K">
									</div>
								</th>
								<th>
									<div class="storeProductName">
									</div>
								</th>
								<th>
									<div class="cz">
									</div>
								</th>
								<th>
									<div class="orderEtc">
									</div>
								</th>
								<th>
									<div class="orderWeight">
									</div>
								</th>
								<th>
									<div class="orderLabor">
									</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" begin="1" end="18">
								<tr>
									<td>
										<div class="K">
										</div>
									</td>
									<td>
										<div class="storeProductName">
										</div>
									</td>
									<td>
										<div class="cz">
										</div>
									</td>
									<td>
										<div class="orderEtc"> 
										</div>
									</td>
									<td>
										<div class="orderWeight">
										</div>
									</td>
									<td>
										<div class="orderLabor">
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
</body>
</html>