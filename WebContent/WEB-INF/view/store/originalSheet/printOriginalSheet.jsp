<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
	<title>원장 인쇄</title>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/store/originalSheet/printOriginalSheet.css">
</head>
<body>
	<c:set var="orderLength" value = "${fn:length(viewOrderDtoList)}" />
	<c:set var="pageSize" value="25" />
	<fmt:parseNumber var="totalPage" integerOnly="true" value="${((orderLength + pageSize - 1) / pageSize)}" />
	<c:forEach var="page" begin="0" end="${(totalPage-1) }">
		<section class="a4Sheet">
			<article class="sheet">
				<div class="pageTitle">지점 : ${branchName } 날짜 : ${originalSheetDto.originalSheetDate } 원장 번호 : ${originalSheetDto.originalSheetNumber }</div>
				<table class="content">
					<thead>
						<tr>
							<th>
								<div class="orderNumber">
									번호
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
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" begin="0" end="${pageSize - 1}">
							<c:if test="${viewOrderDtoList[page*pageSize+i] == null}">
								<tr>
									<td>
										<div class="orderNumber">
											${page * pageSize + i + 1}
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
								</tr>
							</c:if>
							<c:if test="${viewOrderDtoList[page*pageSize+i] != null}">
								<tr>
									<td>
										<div class="orderNumber">
											${page * pageSize + i + 1}
										</div>
									</td>
									<td>
										<div class="orderK">
											${viewOrderDtoList[page*pageSize+i].orderK }
										</div>
									</td>
									<td>
										<div class="customerName">
											${viewOrderDtoList[page*pageSize+i].customerName }
										</div>
									</td>
									<td>
										<div class="storeProductName">
											${viewOrderDtoList[page*pageSize+i].storeProductName }
										</div>
									</td>
									<td>
										<div class="orderSize">
											${viewOrderDtoList[page*pageSize+i].orderSize }
										</div>
									</td>
									<td>
										<div class="orderColor">
											${viewOrderDtoList[page*pageSize+i].orderMainColor }/${viewOrderDtoList[page*pageSize+i].orderSubColor }
										</div>
									</td>
									<td>
										<div class="orderEtc">
											${viewOrderDtoList[page*pageSize+i].orderOptionName } ${viewOrderDtoList[page*pageSize+i].orderEtc }
										</div>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				<p class="pageInfo">${page + 1 } / ${totalPage }</p>
			</article>
		</section>
	</c:forEach>
</body>
</html>