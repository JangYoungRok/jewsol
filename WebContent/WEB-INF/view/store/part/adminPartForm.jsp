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
	<link rel="stylesheet" href="/jewsol/css/store/part/adminPartForm.css">
	<title>부속 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/basicForm.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/part/adminPartForm.js"></script>
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
			<p>부속 품목<br>
				<select id="typeMain">
					<c:forEach var="typeMainList" items="${requestScope.typeMainList }">
						<option value="${typeMainList.typeMainSeq }" >${typeMainList.typeMain }</option>
					</c:forEach>
				</select> 
			</p>
			
			<p>부속 속성<br>
				<select id="partAttribute">
					<c:forEach var="partAttributeList" items="${requestScope.partAttributeList }">
						<option value="${partAttributeList.partAttributeSeq }" >${partAttributeList.partAttribute }</option>
					</c:forEach>
				</select> 
			</p>
		
			<p>부속 이름<br>
				<input type="text" id="partName" size="8" required>
			</p>
			
			<p>부속 매입 가격<br>
				<input type="text" id="partCost" value="0" size="8" required>
			</p>
			
			<p>부속 공장 공임<br>
				<input type="text" id="partLabor" value="0" size="8" required>
			</p>
			
			<p>부속 판매 가격<br>
				<input type="text" id="partPrice" value="0" size="8" required>
			</p>
			
			<p class="insertButton">
				<input type="button" id="insertBtn" value="부속 입력">
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
					부속 속성
				</span>
				
				<span>
					부속 이름
				</span>
				
				<span>
					부속 매입 가격
				</span>
				
				<span>
					부속 공장 공임
				</span>
				
				<span>
					부속 판매 가격
				</span>
			</p>
			<div id="listDiv">
				<c:forEach var="partList" items="${requestScope.partList }" >
					<p id="${partList.partSeq }">
						<span>
							${partList.partAttribute }
						</span>
						
						<span>
							${partList.partName }							
						</span>
						
						<span>
							${partList.partCost } 원						
						</span>
						
						<span>
							${partList.partLabor } 원
						</span>
						
						<span>
							${partList.partPrice } 원
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