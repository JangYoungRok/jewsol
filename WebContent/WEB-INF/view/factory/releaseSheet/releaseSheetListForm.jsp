<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<table id="releaseSheetList">
	<thead>
		<tr>
			<th>
				<div class="branchName">
					지점
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
			<th>
				<div class="releaseTime">
					시간
				</div>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="releaseSheet" items="${releaseSheetList }">
			<tr id="${releaseSheet.releaseSheetSeq }">
				<td>
					<div class="branchName">
						${releaseSheet.branchName }
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
				<td>
					<div class="releaseTime">
						${releaseSheet.releaseTime }
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
