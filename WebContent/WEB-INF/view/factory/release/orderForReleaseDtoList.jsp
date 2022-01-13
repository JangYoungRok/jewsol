<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<table id="orderList">
	<tbody>
		<c:forEach var="orderDto" items="${orderForPanelList }">
		<c:set var="class" value="unReleased" />
		<c:if test="${orderDto.orderState > 2}">
			<c:set var="class" value="released" />
		</c:if>
			<tr id="${orderDto.orderSeq }" class="${class }">
				<td>
					<div class="orderNumber">
						${orderDto.orderNumber}
					</div>
				</td>
				<td>
					<div class="branchName">
						${orderDto.branchName}
					</div>
				</td>
				<td>
					<div class="orderK">
						${orderDto.orderK }
					</div>
				</td>
				<td>
					<div class="storeProductImage">
						<img src="/jewsol/image/${orderDto.storeProductImage}" alt="${orderDto.storeProductImage}">
					</div>
				</td>
				<td>
					<div class="customerName">
						${orderDto.customerName }
					</div>
				</td>
				<td>
					<div class="storeProductName">
						${orderDto.storeProductName }
					</div>
				</td>
				<td>
					<div class="orderSize">
						${orderDto.orderSize }
					</div>
				</td>
				<td>
					<div class="orderColor">
						${orderDto.orderMainColor }/${orderDto.orderSubColor }
					</div>
				</td>
				<td>
					<div class="optionName">
						${orderDto.orderOptionName }
					</div>
				</td>
				<td>
					<div class="orderEtc">
						${orderDto.orderEtc }
						<c:if test="${orderDto.orderHurry == 'T' }">
							급
						</c:if>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>