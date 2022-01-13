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
	<link rel="stylesheet" href="/jewsol/css/store/cz/adminCzForm.css">
	<title>조각 관리</title>
</head>
<body>
	<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/menu.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/validate.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/httpRequest.js"></script>
	<script type="text/javascript" src="/jewsol/javascript/store/cz/adminCzForm.js"></script>
	
	<nav id="gnb">
		<div id="loginInfoPanel">
			<jsp:include page="${loginInfo }" />
		</div>
		<div id="navigationPanel">
			<jsp:include page="${menu }" />
			
		</div>
	</nav>
	<section id="mainPanel">
		<article id="czInsertInfoPanel">
			<p>조각 사이즈<br>
				<select id="czSize">
					<c:forEach var="czSizeList" items="${requestScope.czSizeList }">
						<option value="${czSizeList.czSizeSeq }" >${czSizeList.czSize }</option>
					</c:forEach>
				</select> 
			</p>
		
			<p>조각 이름<br>
				<input type="text" id="czName" size="8" required>
			</p>

			<p>조각 공장 공임<br>
				<input type="text" id="czLabor" value="0" size="8" required>
			</p>

			<p>조각 판매 공임<br>
				<input type="text" id="czPrice" value="0" size="8" required>
			</p>
			<p class="insertButton">
				<input type="button" id="insertCzBtn" value="조각 입력">
			</p>
		</article>
		<article id="czUpdateButtonPanel"> 
			<p>
				<input type="button" id="notInUseCzBtn" value="이용 중지" disabled="disabled"> <input type="button" id="cancelUpdateCzBtn" value="선택 취소" disabled="disabled"> <input type="button" id="updateCzBtn" value="조각 수정" disabled="disabled"/>
			</p>
		</article>
		<article id="czListPanel"> 
			<p class="title">
				<span>
					조각 사이즈
				</span>
				
				<span>
					이름
				</span>
				
				<span>
					조각 공장 공임
				</span>
				
				<span>
					조각 판매 공임
				</span>
			</p>
			<section id="czListSection">
				<c:forEach var="czList" items="${requestScope.czList }" >
					<p id="${czList.czSeq }">
						<span>
							${czList.czSize }
						</span>
						
						<span>
							<input type="text" name="czName" value="${czList.czName }" readonly="readonly">							
						</span>
						
						<span>
							<input type="text" name="czLabor" value="${czList.czLabor }" readonly="readonly">원
						</span>
						
						<span>
							<input type="text" name="czPrice" value="${czList.czPrice}" readonly="readonly">원
						</span>
					</p>
				</c:forEach>
			</section>
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