<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

	<table>
		<thead>
			<tr>
				<th>
					<div class="orderNumber">
						번호
					</div>	
				</th>
				<th>
					<div class="orderK">
						k
					</div>	
				</th>
				<th>
					<div class="customer">
						거래처
					</div>	
				</th>
				<th>
					<div class="storeProductName">
						제품번호
					</div>	
				</th>
				<th>
					<div class="orderSize">
						사이즈
					</div>	
				</th>
				
				<th>
					<div class="orderColor">
						색상
					</div>	
				</th>
				<th>
					<div class="optionName">
						옵션이름
					</div>	
				</th>
				<th>
					<div class="orderEtc">
						기타
					</div>	
				</th>
			</tr>
		</thead>
		<tbody id="orderList">
			<c:forEach var="viewOrder" items="${requestScope.viewOrderList }" varStatus="status">
				<tr id="${viewOrder.orderSeq }">
					<td>
						<div class="orderNumber">
							${fn:length(viewOrderList) - status.count + 1 } 
						</div>
					</td>
					<td>
						<div class="orderK">
							${viewOrder.orderK }
						</div>
					</td>
					<td>
						<div class="customer">
							${viewOrder.customerName }
						</div>
					</td>
					<td>
						<div class="storeProductName">
							${viewOrder.storeProductName }
						</div>
					</td>
					<td>
						<div class="orderSize">
							${viewOrder.orderSize }
						</div>
					</td>
					<td>
						<div class="orderColor">	
							${viewOrder.orderMainColor }/${viewOrder.orderSubColor }
						</div>
					</td>
					<td>
						<div class="optionName">
							${viewOrder.orderOptionName }
						</div>
					</td>
					<td>
						<div class="orderEtc">
							<c:if test="${viewOrder.orderHurry == 'T' }">
								급
							</c:if>
							&nbsp;${viewOrder.orderEtc }
							&nbsp;${viewOrder.orderOptionPartName }
							&nbsp;${viewOrder.orderOptionAddName }
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
 		
	
