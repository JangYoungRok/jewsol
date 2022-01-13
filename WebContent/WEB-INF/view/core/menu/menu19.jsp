<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
 <div id="NAVI">
	<ul class="menu">
		<li><a href="#">제품</a>
			<ul class="sub">
				<li><a href="/jewsol/factory/product/insertProductForm.do">제품입력</a></li>
				<li><a href="/jewsol/factory/product/update/updateProductForm.do">제품수정</a></li>
				<li><a href="/jewsol/factory/product/board/productBoard.do">제품조회</a></li>
			</ul>
		</li>
		<li><a href="#">주문</a>
			<ul class="sub">
				<li><a href="/jewsol/factory/factoryOrder/closeOrderForm.do">주문마감</a></li>
				<li><a href="/jewsol/factory/factoryOrder/inqueryFactoryOrderForm.do">주문조회</a></li>
				<li><a href="/jewsol/factory/panel/printPanelForm.do">주문인쇄</a></li>
				<li><a href="#">주문수정</a></li>
				<li><a href="#">주문취소</a></li>
			</ul>
		</li>
		<li><a href="#">출고</a>
			<ul class="sub">
				<li><a href="/jewsol/factory/release/checkReleaseForm.do">출고확인</a></li>
				<li><a href="/jewsol/factory/release/insertReleaseForm.do">출고입력</a></li>
				<li><a href="/jewsol/factory/releaseSheet/printReleaseSheetForm.do">출고인쇄</a></li>
				<li><a href="/jewsol/factory/release/cancleReleaseForm.do">출고취소</a></li>
				<li><a href="#">기타출고</a></li>
				<li><a href="#">미출고조회</a></li>
			</ul>
		</li>
		<li><a href="#">미수</a>
			<ul class="sub">
				<li><a href="#">미수조회</a></li>
				<li><a href="#">결제입력</a></li>
			</ul>
		</li>
	</ul>
</div>