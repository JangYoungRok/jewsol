<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<table id="paymentList">
	<thead>
		<tr>
			<th>
				<div class="paymentDate">
					날짜
				</div>
			</th>
			<th>
				<div class="paymentContent">
					결제내역
				</div>
			</th>
			<th>
				<div class="lastBalance">
					전잔
				</div>
			</th>
			<th>
				<div class="payment">
					결제
				</div>
			</th>
			<th>
				<div class="balance">
					미수
				</div>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="payment" items="${paymentList }">
			<tr id="${payment.paymentSeq }">
				<td>
				 	<div class="paymentDate">
				 		${payment.paymentDate}
				 	</div>
				</td>
				<td>
					<div class="paymentContent">
						${payment.paymentContent }
					</div>
				</td>
				<td>
					<div class="lastBalance">
						순금 : ${payment.lastBalanceGold }g<br>
						현금 : ${payment.lastBalanceCash }원
					</div>
				</td>
				<td>
					<div class="payment">
						순금 : ${payment.paymentGold }g<br>
						현금 : ${payment.paymentCash }원<br>
						DC : ${payment.paymentDC }원
					</div>
				</td>
				<td>
					<div class="balance">
						순금 : ${payment.balanceGold }g<br>
						현금 : ${payment.balanceCash }원
					</div>
				</td>
			</tr>	
		</c:forEach>
	</tbody>
</table>