<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<table id="releaseSheetList">
	<thead>
		<tr>
			<th>
				<div class="releaseSheetDate">
					출고일자
				</div>
			</th>
			<th>
				<div class="releaseSheetNumber">
					번호
				</div>
			</th>
			<th>
				<div class="releaseQty">
					개수
				</div>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="releaseSheet" items="${releaseSheetList }">
			<tr id="${releaseSheet.releaseSheetSeq }">
				<td>
					<div class="releaseSheetDate">
						${releaseSheet.releaseSheetDate }
					</div>
				</td>
				<td>
					<div class="releaseSheetNumber">
						${releaseSheet.releaseSheetNumber }
					</div>
				</td>
				<td>
					<div class="totalQty">
						${releaseSheet.totalQty }
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
