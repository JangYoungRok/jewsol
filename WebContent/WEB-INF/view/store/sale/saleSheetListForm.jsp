<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table id="saleSheetList">
	<thead>
		<tr>
			<th>
				<div class="saleSheetDate">
					날짜
				</div>
			</th>
			<th>
				<div class="saleSheetNumber">
					번호
				</div>
			</th>
			<th>
				<div class="totalSalePrice">
					공임
				</div>
			</th>
			<th>
				<div class="totalGoldWeight">
					금중량
				</div>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="saleSheet" items="${saleSheetList }">
			<tr id="${saleSheet.saleSheetSeq }">
				<td>
					<div class="saleSheetDate">
						<fmt:parseDate value="${saleSheet.saleSheetDate }" var="saleDate" pattern="yyyy-MM-dd"/>
						<fmt:formatDate value="${saleDate }" pattern="MM-dd" />
					</div>
				</td>
				<td>
					<div class="saleSheetNumber">
						${saleSheet.saleSheetNumber }
					</div>
				</td>
				<td>
					<div class="totalSalePrice">
						<fmt:formatNumber type="currency" value="${saleSheet.totalSalePrice }" pattern="###,###"/>&#8361;
					</div>
				</td>
				<td>
					<div class="totalGoldWeight">
						<fmt:formatNumber value="${saleSheet.totalGoldWeight }" pattern="0.00"/>g
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
