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
	<link rel="stylesheet" href="/jewsol/css/store/add/adminAddForm.css">
	<title>추가공정 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/basicForm.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/add/adminAddForm.js"></script>
	
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
		
			<p>추가공정이름<br>
				<input type="text" id="addName" size="8" required>
			</p>
			
			<p>추가공정매입 가격<br>
				<input type="text" id="addCost" value="0" size="8" required>
			</p>
			
			<p>추가공정공장 공임<br>
				<input type="text" id="addLabor" value="0" size="8" required>
			</p>
			
			<p>추가공정판매 가격<br>
				<input type="text" id="addPrice" value="0" size="8" required>
			</p>
			
			<p class="insertButton">
				<input type="button" id="insertBtn" value="추가공정입력">
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
					추가공정 이름
				</span>
				
				<span>
					추가공정 매입 가격
				</span>
				
				<span>
					추가공정 공장 공임
				</span>
				
				<span>
					추가공정 판매 가격
				</span>
			</p>
			<div id="listDiv">
				<c:forEach var="addList" items="${requestScope.addList }" >
					<p id="${addList.addSeq }">
						
						<span>
							${addList.addName }							
						</span>
						
						<span>
							${addList.addCost } 원						
						</span>
						
						<span>
							${addList.addLabor } 원
						</span>
						
						<span>
							${addList.addPrice } 원
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