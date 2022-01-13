<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/mainLayout.css">
	<link rel="stylesheet" href="/jewsol/css/menu.css">
	<link rel="stylesheet" href="/jewsol/css/store/customer/adminCustomerForm.css">
	<title>거래처 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/basicForm.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/customer/adminCustomerForm.js"></script>
	
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<article id="insertInfoPanel">
			<p>제고위치분류<br>
				<select id="stockLocationType">
					<c:forEach var="stockLocationTypeList" items="${requestScope.stockLocationTypeList }">
						<option value="${stockLocationTypeList.stockLocationTypeSeq }" >${stockLocationTypeList.stockLocationType }</option>
					</c:forEach>
				</select> 
			</p>
			
			<p>제고위치<br>
				<select id="stockLocation">
					<c:forEach var="stockLocationList" items="${requestScope.stockLocationList }">
						<option value="${stockLocationList.stockLocationSeq }" >${stockLocationList.stockLocationName }</option>
					</c:forEach>
				</select> 
			</p>
		
			<p>거래처 지역<br>
				<input type="text" id="customerArea" size="8">
			</p>
			
			<p>거래처 구역<br>
				<input type="text" id="customerSection" size="8">
			</p>
			
			<p>거래처 이름<br>
				<input type="text" id="customerName" size="8">
			</p>
			
			<p class="insertButton">
				<input type="button" id="insertBtn" value="거래처 입력">
			</p>
		</article>
		<article id="triggerButtonPanel"> 
			<p>
				<input type="button" id="notInUseBtn" value="이용 중지" disabled="disabled"> <input type="button" id="unselectBtn" value="선택 취소" disabled="disabled">
			</p>
		</article>
		<article id="listPanel"> 
			<p class="title">
				<span>
					제고위치분류
				</span>
				
				<span>
					거래처 지역
				</span>
				
				<span>
					거래처 구역
				</span>
				
				<span>
					거래처 이름
				</span>
			</p>
			<div id="listDiv">
				<c:forEach var="customerList" items="${requestScope.customerList }" >
					<p id="${customerList.customerSeq }">
						<span>
							${customerList.stockLocationType }
						</span>
						
						<span>
							${customerList.customerArea }							
						</span>
						
						<span>
							${customerList.customerSection } 					
						</span>
						
						<span>
							${customerList.customerName }
						</span>
					</p>
				</c:forEach>
			</div>
		</article>
	
	</section>
	<footer id="footer">
		<article id="systemMessagePanel">
			<p>시스템 메세지</p>
			<p><span id="systemMessage"><br>
			</span></p>
		</article>
	</footer>
</body>
</html>