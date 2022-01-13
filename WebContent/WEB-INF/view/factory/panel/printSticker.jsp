<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>스티커 인쇄</title>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/factory/panel/printStickerWindow.css">
</head>
<body>
	<c:set var="orderLength" value = "${fn:length(orderList)}" />
	<c:set var="pageSize" value="24" />
	<c:set var="pageRow" value="6" />
	<c:set var="pageColumn" value="4" />
	<fmt:parseNumber var="totalPage" integerOnly="true" value="${(orderLength + pageSize - 1)/ pageSize}" />
	<c:forEach var="i" begin="0" end="${totalPage - 1}">
		<section class="a4Sheet">
			<article class="sheet">
				<c:forEach var="j" begin="0" end="${pageRow - 1}">
					<div class="row">
						<c:forEach var="k" begin="0" end="${pageColumn - 1}">
							<c:if test="${((i*24)+(j*4)+k) < orderLength}">
								<div class="col">
									<table class="sticker">
										<tr>
											<td>
												<div class="image">
													<img src="/jewsol/image/${orderList[(i*24)+(j*4)+k].storeProductImage }" >
												</div>
												<div class="barcode">
												</div>
											</td>
											<td>
												<div class="info">
													${orderList[(i*24)+(j*4)+k].branchName }&nbsp;&nbsp;&nbsp;${orderList[(i*24)+(j*4)+k].orderDate }&nbsp;&nbsp;&nbsp;( ${orderList[(i*24)+(j*4)+k].panelNumber } )<br>
													${orderList[(i*24)+(j*4)+k].customerName }<br>
													<span class="productName">${orderList[(i*24)+(j*4)+k].category } - ${orderList[(i*24)+(j*4)+k].storeProductCode } - ${orderList[(i*24)+(j*4)+k].type }</span><br>
													<c:if test="${orderList[(i*24)+(j*4)+k].orderK == 18}">
														<span class="fontRed">18</span>
													</c:if>
													<c:if test="${orderList[(i*24)+(j*4)+k].orderK == 14}">
														<span class="fontBlue">14</span>
													</c:if>
													<c:if test="${orderList[(i*24)+(j*4)+k].orderK == 10}">
														<span class="fontGreen">10</span>
													</c:if>
													<c:if test="${orderList[(i*24)+(j*4)+k].orderK == 'SV'}">
														<span>SV</span>
													</c:if>
													&nbsp;&nbsp;&nbsp;
													<c:choose>
														<c:when test="${orderList[(i*24)+(j*4)+k].orderMainColor == '골드' }">
															<c:choose>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '골드' }">
																	Y
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '화이트' }">
																	Y/<span class="fontBlue">W</span>
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '적금' }">
																	Y/<span class="fontRed">P</span>
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '삼색' }">
																	Y/3C
																</c:when>
															</c:choose>
														</c:when>
														<c:when test="${orderList[(i*24)+(j*4)+k].orderMainColor == '화이트' }">
															<c:choose>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '골드' }">
																	<span class="fontBlue">W</span>/Y
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '화이트' }">
																	<span class="fontBlue">W</span>
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '적금' }">
																	<span class="fontBlue">W</span>/<span class="fontRed">P</span>
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '삼색' }">
																	<span class="fontBlue">W</span>/3C
																</c:when>
															</c:choose>										
														</c:when>
														<c:when test="${orderList[(i*24)+(j*4)+k].orderMainColor == '적금' }">
															<c:choose>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '골드' }">
																	<span class="fontRed">P</span>/Y
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '화이트' }">
																		<span class="fontRed">P</span>/<span class="fontBlue">W</span>
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '적금' }">
																	<span class="fontRed">P</span>
																</c:when>
																<c:when test="${orderList[(i*24)+(j*4)+k].orderSubColor == '삼색' }">
																	<span class="fontRed">P</span>/3C
																</c:when>
															</c:choose>																								
														</c:when>
													</c:choose>
													<br>
													(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div class="orderEtc">
													<c:if test="${orderList[(i*24)+(j*4)+k].orderHurry == 'T' }">													
														급&nbsp;&nbsp;
													</c:if>
													[&nbsp;${orderList[(i*24)+(j*4)+k].orderSize }&nbsp;] ${orderList[(i*24)+(j*4)+k].orderOptionName } <br>
													${orderList[(i*24)+(j*4)+k].orderEtc }<br>
													${orderList[(i*24)+(j*4)+k].orderOptionPartName }<br>
													${orderList[(i*24)+(j*4)+k].orderOptionAddName }
									
												</div>
											</td>
										</tr>
									</table>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
			</article>	
		</section>
	</c:forEach>
</body>
</html>