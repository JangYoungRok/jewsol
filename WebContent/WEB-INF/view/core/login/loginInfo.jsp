<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<p>${sessionScope.sBranchName }</p>
<p>${sessionScope.sMemberName} 님</p>
<input type="button" value="로그아웃" onclick="location.href='/jewsol/core/login/logout.do'">