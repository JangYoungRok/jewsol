<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>판 인쇄</title>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/factory/panel/printPanelWindow.css">
</head>
<body>
	<section id="mainPanel">
		<c:set var="class" value="${''}"/>
		<c:forEach var="panel" items="${panelDtoList }" varStatus="loopCount">
			<section class="a4Sheet">
				<article class="sheet">
				
					<c:if test="${panel.panelGroup == 'R14' || panel.panelGroup == 'P14' || panel.panelGroup == 'N14' || panel.panelGroup == 'H14'}">
						<c:set var="class" value="${'K14'}"/>
					</c:if>
					<c:if test="${panel.panelGroup == 'R18' || panel.panelGroup == 'P18' || panel.panelGroup == 'N18' || panel.panelGroup == 'H18'}">
						<c:set var="class" value="${'K18'}"/>
					</c:if>
					<c:if test="${panel.panelGroup == '10SV'}">
						<c:set var="class" value="${'K10'}"/>
					</c:if>
					
					<div class="pageTitle">날짜 : ${orderDate } 판분류 : ${panel.panelGroup } 판번호 : ${panel.panelNumber } / ${fn:length(panelDtoList)}</div>
					<table class="${class }">
						<tbody>
							<c:forEach var="orderDto" items="${panel.orderForPanelList }">
								<tr>
									<td>
										<div class=orderNumber>
											${orderDto.orderNumber}
										</div>
									</td>
									<td>
										<div class="branchName">
											${orderDto.branchName}
										</div>
									</td>
									<td>
										<div class="storeProductImage">
										<img id="storeProductImage" src="/jewsol/image/${orderDto.storeProductImage}" alt="제품이미지">
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
											${orderDto.orderSize}
										</div>
									</td>
									<td>
										<div class="orderColor">
											<c:choose>
												<c:when test="${orderDto.orderMainColor == '골드' }">
													<c:choose>
														<c:when test="${orderDto.orderSubColor == '골드' }">
															골드
														</c:when>
														<c:when test="${orderDto.orderSubColor == '화이트' }">
															골드/<span class="fontBlue">화이트</span>
														</c:when>
														<c:when test="${orderDto.orderSubColor == '적금' }">
															골드/<span class="fontRed">적금</span>
														</c:when>
														<c:when test="${orderDto.orderSubColor == '삼색' }">
															골드/3C
														</c:when>
													</c:choose>
												</c:when>
												<c:when test="${orderDto.orderMainColor == '화이트' }">
													<c:choose>
														<c:when test="${orderDto.orderSubColor == '골드' }">
															<span class="fontBlue">화이트</span>/골드
														</c:when>
														<c:when test="${orderDto.orderSubColor == '화이트' }">
															<span class="fontBlue">화이트</span>
														</c:when>
														<c:when test="${orderDto.orderSubColor == '적금' }">
															<span class="fontBlue">화이트</span>/<span class="fontRed">적금</span>
														</c:when>
														<c:when test="${orderDto.orderSubColor == '삼색' }">
															<span class="fontBlue">화이트</span>/3C
														</c:when>
													</c:choose>										
												</c:when>
												<c:when test="${orderDto.orderMainColor == '적금' }">
													<c:choose>
														<c:when test="${orderDto.orderSubColor == '골드' }">
															<span class="fontRed">적금</span>/골드
														</c:when>
														<c:when test="${orderDto.orderSubColor == '화이트' }">
																<span class="fontRed">적금</span>/<span class="fontBlue">화이트</span>
														</c:when>
														<c:when test="${orderDto.orderSubColor == '적금' }">
															<span class="fontRed">적금</span>
														</c:when>
														<c:when test="${orderDto.orderSubColor == '삼색' }">
															<span class="fontRed">적금</span>/3C
														</c:when>
													</c:choose>																								
												</c:when>
											</c:choose>
										</div>
									</td>
									<td>
										<div class="orderEtc">
											${orderDto.storeProductPrice }
											${orderDto.orderOptionName }
											${orderDto.orderEtc}
											<c:if test="${orderDto.orderHurry == 'T'}">
												급
											</c:if><br>
											${orderDto.orderOptionPartName }
											${orderDto.orderOptionAddName}
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</article>
			</section>	
		</c:forEach>
	</section>
</body>
</html>