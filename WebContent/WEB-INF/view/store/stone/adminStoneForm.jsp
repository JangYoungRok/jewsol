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
	<link rel="stylesheet" href="/jewsol/css/store/stone/adminStoneForm.css">
	<title>스톤 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/stone/adminStoneForm.js"></script>
	
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
		</div>
	</nav>
	<section id="mainPanel">
		<article id="stoneInsertInfoPanel">
			<p>스톤 사이즈<br>
				<select id="stoneSize">
					<c:forEach var="stoneSizeList" items="${requestScope.stoneSizeList }">
						<option value="${stoneSizeList.stoneSizeSeq }" >${stoneSizeList.stoneSize }</option>
					</c:forEach>
				</select> 
			</p>
		
			<p>스톤 이름<br>
				<input type="text" id="stoneName" size="8" required>
			</p>
			
			<p>스톤 매입 가격<br>
				<input type="text" id="stoneCost" value="0" size="8" required>
			</p>
			
			<p>스톤 판매 가격<br>
				<input type="text" id="stonePrice" value="0" size="8" required>
			</p>
			
			<p class="insertButton">
				<input type="button" id="insertStoneBtn" value="스톤 입력">
			</p>
		</article>
		<article id="stoneUpdateButtonPanel"> 
			<p>
				<input type="button" id="notInUseStoneBtn" value="이용 중지" disabled="disabled"> <input type="button" id="unselectStoneBtn" value="선택 취소" disabled="disabled">
			</p>
		</article>
		<article id="stoneListPanel"> 
			<p class="title">
				<span>
					스톤 사이즈
				</span>
				
				<span>
					스톤 이름
				</span>
				
				<span>
					스톤 매입 가격
				</span>
				
				<span>
					스톤 공장 공임
				</span>
				
				<span>
					스톤 판매 가격
				</span>
			</p>
			<div id="stoneListDiv">
				<c:forEach var="stoneList" items="${requestScope.stoneList }" >
					<p id="${stoneList.stoneSeq }">
						<span>
							${stoneList.stoneSize }
						</span>
						
						<span>
							${stoneList.stoneName }							
						</span>
						
						<span>
							${stoneList.stoneCost }	원						
						</span>
						
						<span>
							${stoneList.stoneLabor } 원
						</span>
						
						<span>
							${stoneList.stonePrice } 원
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