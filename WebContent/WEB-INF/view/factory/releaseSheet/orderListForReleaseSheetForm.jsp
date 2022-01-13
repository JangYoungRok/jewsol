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
					<div class="orderDate">
						${order.orderDate }(${order.orderNumber })
					</div>
				</td>
				<td>
					<div class="storeProductImage">
						<img src="/jewsol/image/${order.storeProductImage }">
					</div>
				</td>
				<td>
					<div class="customerName">
						${order.customerName }
					</div>
				</td>
				<td>
					<div class="storeProductName">
						${order.storeProductName }
					</div>
				</td>
				<td>
					<div class="orderSize">
						${order.orderSize }
					</div>
				</td>
				<td>
					<div class="orderColor">
						${order.orderMainColor }/${order.orderSubColor }
					</div>
				</td>
				<td>
					<div class="orderEtc">
						${order.orderOptionName } ${order.orderEtc } ${order.orderHurry }
					</div>
				</td>
				<td>
					<div class="orderWeight">
						${order.orderWeight }g
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>