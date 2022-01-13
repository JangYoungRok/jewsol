<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jewsol/css/reset.css">
<link rel="stylesheet" href="/jewsol/css/mainLayout.css">
<title>로그인 페이지</title>
<style>
	#logo{
		margin: 0 auto;
		margin-top:30px;
		width: 500px;
		height: 200px;
		font-size: 100px;
		text-align:center;
		line-height:150px;
	}
	
	#loginfo{	
		margin: 0 auto;
		width : 300px;
		text-align:center;
		border: 1px solid black;
	}
	
	#loginfo p{
		margin : 10px;
	}
	
	#systemMessagePanel{
		margin: 0 auto;
		width : 700px;
		height: 80px;
		text-align:center;
		line-height:25px;
	}
	#systemMessagePanel p{
		margin : 5px;
	}
	
	
</style>
<script type="text/javascript" src="/jewsol/javascript/util.js"></script>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script>
	window.onload = initLoginForm;
	
	function initLoginForm(){
		document.getElementById("loginBtn").onclick = checkLogin;
	}

	function checkLogin(){
		
		emptySystemMessage();
		
		if(document.loginForm.loginId.value == ""){
			setSystemMessage("아이디를 입력하세요.");
		}else if(document.loginForm.loginPw.value == ""){
			setSystemMessage("비밀번호를 입력하세요.");
		}else{
			document.loginForm.submit();
		}
	}
	
</script>
</head>
<body>
	<nav id="gnb"></nav>
	<section id="mainPanel">
		<article id="logo">
			<h1>J Solution</h1>
		</article>
		<article id="loginfo">
			<form name="loginForm" method="post" action="/jewsol/core/login/login.do">
				<p>아이디&nbsp;&nbsp; : &nbsp;<input type="text" name="loginId" size="15" value="${loginId }"></p> 
				<p>비밀번호 : <input type="password" name="loginPw" size="15"></p>
				<p><input id="loginBtn" type="button" value="로그인"></p>
			</form>
		</article>
		
	</section>
	<footer id="footer">
		<article id="systemMessagePanel">
			<p>시스템 메세지</p>
			<p><span id="systemMessage">${systemMessage }</span></p>
		</article>
	</footer>
</body>
</html>