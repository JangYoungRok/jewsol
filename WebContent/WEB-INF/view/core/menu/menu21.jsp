<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<div id="NAVI">
	<ul class="menu">
		<li><a href="#">제품</a>
			<ul class="sub">
				<li><a href="/jewsol/store/storeProduct/board/storeProductBoard.do">제품조회</a></li>
			</ul>
		</li>
		<li><a href="#">주문</a>
			<ul class="sub">
				<li><a href="/jewsol/store/order/inquery/inqueryOrderForm.do">주문조회</a></li>
				<li><a href="/jewsol/store/originalSheet/printOriginalSheetForm.do">원장 인쇄</a></li>
			</ul>
		</li>
		<li><a href="#">판매</a>
			<ul class="sub">
				<li><a href="/jewsol/store/customer/inqueryCustomerBalanceForm.do">미수조회</a></li>
				<li><a href="/jewsol/store/customer/inqueryCustomerBalanceByCustomerTypeForm.do">지역미수조회</a></li>
				<li><a href="/jewsol/store/customer/inqueryCustomerBalanceMonthByCustomerTypeForm.do">지역미수월별</a></li>
			</ul>
		</li>
	</ul>
</div>
