<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<div id="NAVI">
	<ul class="menu">
		<li><a href="#">제품</a>
			<ul class="sub">
				<li><a href="/jewsol/store/storeProduct/board/storeProductBoard.do">제품조회</a></li>
				<li><a href="/jewsol/store/storeProduct/registerStoreProductForm.do">제품등록</a></li>
				<li><a href="/jewsol/store/storeProduct/insertStoreProductForm.do">타사제품입력</a></li>
				<li><a href="/jewsol/store/option/adminOptionForm.do">옵션관리</a></li>
			</ul>
		</li>
		
		<li><a href="#">주문</a>
			<ul class="sub">
				<li><a href="/jewsol/store/order/insert/insertOrderForm.do">주문입력</a></li>
				<li><a href="/jewsol/store/order/inquery/inqueryOrderForm.do">주문조회</a></li>
				<li><a href="/jewsol/store/originalSheet/printOriginalSheetForm.do">원장 인쇄</a></li>
				<li><a href="/jewsol/store/order/downloadOrderListForm.do">주문다운로드</a></li>	
			</ul>
		</li>
		<li><a href="#">판매</a>
			<ul class="sub">
				<li><a href="/jewsol/store/customer/inqueryCustomerBalanceForm.do">미수조회</a></li>
				<li><a href="/jewsol/store/customer/inqueryCustomerBalanceByCustomerTypeForm.do">지역미수조회</a></li>
				<li><a href="/jewsol/store/customer/inqueryCustomerBalanceMonthByCustomerTypeForm.do">지역미수월별</a></li>
				<li><a href="/jewsol/store/payment/inqueryPaymentForm.do">결제조회</a></li>
				<li><a href="/jewsol/store/sale/saleForm.do">판매입력</a></li>
			</ul>
		</li>
		<li><a href="#">재고</a>
			<ul class="sub">
				<li><a href="/jewsol/store/stock/toStockForm.do">입고확인</a></li>
			</ul>
		</li>
		<li><a href="#">매장관리</a>
			<ul class="sub">
				<li><a href="/jewsol/store/goldPrice/adminGoldPriceForm.do">금시세</a></li>
				<li><a href="/jewsol/store/cz/adminCzForm.do">조각</a></li>
				<li><a href="/jewsol/store/stone/adminStoneForm.do">스톤</a></li>	
				
			</ul>
		</li>
	</ul>
</div>
