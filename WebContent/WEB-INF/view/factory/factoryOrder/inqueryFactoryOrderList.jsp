<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<table id="orderList">
	<thead>
		<tr>
			<th>
				<p class="oderDate">
					주문날짜
				</p>
			</th>
			<th>
				<p class="branchName">
					지점
				</p>
			</th>
			<th>
				<p class="orderK">
					K
				</p>
			</th>
			<th>
				<p class="storeProductName">
					제품번호
				</p>
			</th>
			<th>
				<p class="orderSize">
					사이즈
				</p>
			</th>
			<th>
				<p class="orderColor">
					색상
				</p>
			</th>
			<th>
				<p class="optionName">
					옵션이름
				</p>
			</th>
			<th>
				<p class="orderEtc">
					기타
				</p>
			</th>
			<th>
				<p class="panelNumber">
					판번호
				</p>
			</th>
			<th>
				<p class="orderState">
					주문상태
				</p>
			</th>
			<th>
				<p class="releaseSheetDate">
					출고일자
				</p>
			</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach var="orderDto" items="${inqueryOrderList }" varStatus="loopCount">
			<tr>
				<td>
					<p class="orderDate">
						${orderDto.orderDate}
					</p>
				</td>
				<td>
					<p class="branchName">
						${orderDto.branchName}
					</p>
				</td>
				<td>
					<p class="orderK">
						${orderDto.orderK }
					</p>
				</td>
				<td>
					<p class="storeProductName">
						${orderDto.storeProductName }
					</p>
				</td>
				<td>
					<p class="orderSize">
						<c:if test="${orderDto.orderSize == null }">
							&nbsp;
						</c:if>
						<c:if test="${orderDto.orderSize != null }">
							${orderDto.orderSize }
						</c:if>
					</p>
				</td>
				<td>
					<p class="orderColor">
						${orderDto.orderMainColor }/${orderDto.orderSubColor }
					</p>
				</td>
				<td>
					<p class="optionName">
						<c:if test="${orderDto.orderOptionName == null }">
							&nbsp;
						</c:if>
						<c:if test="${orderDto.orderOptionName != null }">
							${orderDto.orderOptionName }
						</c:if>
					</p>
				</td>
				<td>
					<p class="orderEtc">
						<c:if test="${orderDto.orderEtc == null }">
							&nbsp;
						</c:if>
						<c:if test="${orderDto.orderEtc != null }">
							${orderDto.orderEtc }
						</c:if>
					</p>
				</td>
				<td>
					<p class="panelNumber">
						${orderDto.panelNumber }
					</p>
				</td>
				<td>
					<p class="orderState">
						${orderDto.orderState }
					</p>
				</td>
				<td>
					<p class="releaseSheetDate">
						<c:if test="${orderDto.releaseSheetDate == null }">
							&nbsp;
						</c:if>
						<c:if test="${orderDto.releaseSheetDate != null }">
							${orderDto.releaseSheetDate }
						</c:if>
					</p>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
