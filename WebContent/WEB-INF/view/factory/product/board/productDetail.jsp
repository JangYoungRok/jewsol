<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/jewsol/css/reset.css">
	<link rel="stylesheet" href="/jewsol/css/factory/product/board/productDetail.css">
	<title>${product.productName } 상세정보</title>
</head>
<body>
	<section id="mainPanel">
		<article id="productPanel">
			<form name="updateProductForm" enctype="multipart/form-data" method="POST" action="/jewsol/factory/product/update/updateProductDetail.do">
				<p id="productImg">
					<img id="prev_img" src="/jewsol/image/${product.productImage }" alt="${product.productName }">
					<input type="file" id="productImage" name="productImage" size="8">
				</p>
				<p>
					제품 번호 : ${product.productName }<br>
					평균 중량 : ${product.productAveWeight }g<br>
					수정 중량 : <input type="text" name="productAveWeight" id="productAveWeight" value="${product.productAveWeight }">g<br>
					<input type="hidden" name="productSeq" value="${productSeq }">
					<input type="submit" id="updateProduct" value="제품 수정">
				</p>
			</form>
		</article>
		
		<article id="productOptionPanel">
			<jsp:include page="/WEB-INF/view/common/productOption.jsp" />
		</article>
	
	</section>
</body>
</html>