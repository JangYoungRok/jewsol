<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/factory/releaseSheet/print/printReleaseSheetTypeOrder.css">
	<title>출고장 인쇄</title>
</head>
<body>
	<c:set var="orderLength" value = "${fn:length(orderList)}" />
	<c:set var="pageSize" value="25" />
	<fmt:parseNumber var="totalPage" integerOnly="true" value="${((orderLength + pageSize - 1) / pageSize)}" />
	<c:forEach var="page" begin="0" end="${totalPage - 1 }">
		<section class="a4Sheet">
			<article class="sheet">
				<div class="pageTitle">
					${releaseSheet.branchName } &nbsp;&nbsp;&nbsp; ${releaseSheet.releaseSheetDate } - (${releaseSheet.releaseSheetNumber }) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; gold premium : 7%<br>
					14K 중량  : <fmt:formatNumber value="${releaseSheet.total14KWeight}" pattern="0.00"/>g&nbsp;&nbsp; 18K 중량  : <fmt:formatNumber value="${releaseSheet.total18KWeight}" pattern="0.00"/>g&nbsp;&nbsp; 10K 중량  : <fmt:formatNumber value="${releaseSheet.total10KWeight}" pattern="0.00"/>g<br>
					출고 공임 : <fmt:formatNumber type="currency" value="${releaseSheet.totalLabor }" pattern="###,###"/>&#8361; &nbsp;&nbsp; 순금 중량  : <fmt:formatNumber value="${releaseSheet.totalGoldWeight }" pattern="0.00"/>g &nbsp;&nbsp; 출고 개수 : ${releaseSheet.totalQty}개
				</div>
				<table class="content">
					<thead>
						<tr>
							<th>
								<div class="releaseNumber">
									번호
								</div>
							</th>
							<th>
								<div class="orderDate">
									주문날짜
								</div>
							</th>
							<th>
								<div class="orderK">
									K
								</div>
							</th>
							<th>
								<div class="customerName">
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
								<div class="orderEtc">
									기타
								</div>
							</th>
							<th>
								<div class="orderWeight">
									중량
								</div>
							</th>
							<th>
								<div class="orderLabor">
									공임
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" begin="0" end="${pageSize - 1}">
							<c:if test="${orderList[page*pageSize+i] == null}">
								<tr>
									<td>
										<div class="releaseNumber">
											${page * pageSize + i + 1}
										</div>
									</td>
									<td>
										<div class="orderDate">
										</div>
									</td>
									<td>
										<div class="orderK">
										</div>
									</td>
									<td>
										<div class="customerName">
										</div>
									</td>
									<td>
										<div class="storeProductName">
										</div>
									</td>
									<td>
										<div class="orderSize">
										</div>
									</td>
									<td>
										<div class="orderColor">
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
							</c:if>
							<c:if test="${orderList[page*pageSize+i] != null}">
								<tr>
									<td>
										<div class="orderNumber">
											${page * pageSize + i + 1}
										</div>
									</td>
									<td>
										<div class="orderDate">
											${orderList[page*pageSize+i].orderDate }(${orderList[page*pageSize+i].orderNumber })
										</div>
									</td>
									<td>
										<div class="orderK">
											${orderList[page*pageSize+i].orderK }
										</div>
									</td>
									<td>
										<div class="customerName">
											${orderList[page*pageSize+i].customerName }
										</div>
									</td>
									<td>
										<div class="storeProductName">
											${orderList[page*pageSize+i].storeProductName }
										</div>
									</td>
									<td>
										<div class="orderSize">
											${orderList[page*pageSize+i].orderSize }
										</div>
									</td>
									<td>
										<div class="orderColor">
											${orderList[page*pageSize+i].orderMainColor }/${orderList[page*pageSize+i].orderSubColor }&nbsp;
										</div>
									</td>
									<td>
										<div class="orderEtc">
											${orderList[page*pageSize+i].orderOptionName} ${orderList[page*pageSize+i].orderEtc }
											<c:if test="${orderList[page*pageSize+i].orderHurry == 'T' }">
											급
											</c:if>
										</div>
									</td>
									<td>
										<div class="orderWeight">
											<fmt:formatNumber value="${orderList[page*pageSize+i].orderWeight }" pattern="0.00"/>g
										</div>
									</td>
									<td>
										<div class="orderLabor">
											<fmt:formatNumber type="currency" value="${orderList[page*pageSize+i].orderOptionLabor }" pattern="###,###"/>&#8361;
										</div>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<div class="pageInfo">${page + 1 } / ${totalPage }</div>
			</article>
		</section>
	</c:forEach>
</body>
</html>