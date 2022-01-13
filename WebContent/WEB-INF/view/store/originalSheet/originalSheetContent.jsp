<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="orderLength" value = "${fn:length(viewOrderDtoList)}" />
<table id="originalSheetContent">
	<thead>
		<tr>
			<th>
				<p class="orderNumber">
					번호
				</p>
			</th>
			<th>
				<p class="orderK">
					K
				</p>
			</th>
			<th>
				<p class="customerName">
					거래처
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
			
		</tr>
	</thead>
	<tbody>
		<c:forEach var="viewOrderDto" items="${viewOrderDtoList }" varStatus="loopCount">
			<tr>
				<td>
					<p class="orderNumber">
						${loopCount.count}&nbsp;
					</p>
				</td>
				<td>
					<p class="orderK">
						${viewOrderDto.orderK }&nbsp;
					</p>
				</td>
				<td>
					<p class="customerName">
						${viewOrderDto.customerName }&nbsp;
					</p>
				</td>
				<td>
					<p class="storeProductName">
						${viewOrderDto.storeProductName }&nbsp;
					</p>
				</td>
				<td>
					<p class="orderSize">
						${viewOrderDto.orderSize }&nbsp;
					</p>
				</td>
				<td>
					<p class="orderColor">
						${viewOrderDto.orderMainColor }/${viewOrderDto.orderSubColor }&nbsp;
					</p>
				</td>
				<td>
					<p class="optionName">
						${viewOrderDto.orderOptionName }&nbsp;
					</p>
				</td>
				<td>
					<p class="orderEtc">
						${viewOrderDto.orderEtc }&nbsp;
					</p>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
