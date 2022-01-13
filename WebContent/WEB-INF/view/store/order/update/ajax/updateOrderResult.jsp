<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
${customer.customerArea } ${customer.customerSection } ${customer.customerName }, ${customer.customerSeq },
${storeProduct.storeProductName }, ${storeProduct.storeProductSeq }, ${storeProduct.storeProductImage },
<jsp:include page="${optionList }" />,
<jsp:include page="${optionDetail }" />,
${orderDetail.kSeq }, ${orderDetail.orderK },
${orderDetail.orderMainColor }, ${orderDetail.orderSubColor },
${orderDetail.orderSize }, ${orderDetail.orderEtc }, ${orderDetail.orderHurry }, ${orderDetail.plateSeq },
${order.orderMemberSeq }, ${orderDetail.orderHalf }
