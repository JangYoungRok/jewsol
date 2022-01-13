<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<p>
	<br>
	<span>공급처 :</span>
	<br>
	<br>
	<img id="prev_img" src="/jewsol/image/noPic.jpg" alt="미리보기 이미지"><br>
	<input type="file" id="productImage" name="productImage" size="8" disabled>
</p>
<p>
	<br>
	공급처 제품 번호&nbsp;:&nbsp;<input type="text" id="storeProductOriginalName" name="storeProductOriginalName" value="" disabled></input><br>
	<br>
	평균 중량&nbsp;:&nbsp;<input type="text" name="productAveWeight" id="productAveWeight" value="" disabled></input><br>
	<br>
	기성 옵션 이름&nbsp;:&nbsp;<input type="text" id="optionName" name="optionName" value="" disabled></input><br>
	<br>
	제품 공임&nbsp;:&nbsp;<input type="text" id="storeProductPrice" name="storeProductPrice" value="" disabled></input><br>
	<br>
	<input type="button" id="notInUseStoreProductBtn" value="제품 이용 중지" disabled> <input type="button" id="updateStoreProductBtn" value="제품 수정" disabled>
</p>