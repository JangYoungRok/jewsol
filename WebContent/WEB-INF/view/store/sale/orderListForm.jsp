<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<table id="orderList">
	<tbody>
		<c:forEach var="order" items="${orderList }">
			<tr id="${order.orderSeq }">
				<td>
					<div class="orderK">
						${order.orderK }
					</div>
				</td>
				<td>
					<div class="storeProductImage">
						<img src="/jewsol/image/${order.storeProductImage }">
					</div>
				</td>
				<td>
					<div class="storeProductName">
						${order.storeProductName }
					</div>
				</td>
				<td>
					<div class="orderOptionName">
						${order.orderOptionName }
					</div>
				</td>
				<td>
					<div class="orderWeight">
						${order.orderWeight }g
					</div>
				</td>
				<td>
					<div class="orderReleasePrice">
						${order.orderReleasePrice }원
					</div>
				</td>
				<td>
					<div>
						<input type="text" value="${order.orderWeight }" class="orderSaleWeight">g
						<input type="text" value="${order.orderReleasePrice }" class="orderSalePrice">원
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>