<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/mainLayout.css">
	<link rel="stylesheet" href="/jewsol/css/menu.css">
	<link rel="stylesheet" href="/jewsol/css/store/stockLocation/adminStockLocationForm.css">
	<title>제고위치 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/basicForm.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/stockLocation/adminStockLocationForm.js"></script>
	
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
		
			<p>제고위치 이름<br>
				<input type="text" id="stockLocationName" size="8" required>
			</p>
			
			<p>제고위치 분류<br>
				<select id="stockLocationType">
					<c:forEach var="stockLocationTypeList" items="${requestScope.stockLocationTypeList }">
						<option value="${stockLocationTypeList.stockLocationTypeSeq }" >${stockLocationTypeList.stockLocationType }</option>
					</c:forEach>
				</select>
			</p>
			
			<p class="insertButton">
				<input type="button" id="insertBtn" value="제고위치 입력">
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
					제고위치 이름
				</span>
				
				<span>
					제고위치 분류
				</span>
			</p>
			<div id="listDiv">
				<c:forEach var="stockLocationList" items="${requestScope.stockLocationList }" >
					<p id="${stockLocationList.stockLocationSeq }">
						
						<span>
							${stockLocationList.stockLocationName }							
						</span>
						
						<span>
							${stockLocationList.stockLocationType }						
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