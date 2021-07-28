<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>자유게시판 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/community/board_content.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<script src="http://rawgit.com/jackmoored/autosize/master/dist/autosize.min.js"></script>
<style>
	.table tr { border-bottom:1px solid lightgray; }
	.button tr { border-top:1px solid lightgray; }
	.table th { font-weight:normal; text-align:left; padding-left:15px; }
	.table tr:first-child td, .button th:first-child { background-color:rgb(248,248,248); }
	.table th:first-child { width:100px; }
	.table td>textarea { width:100%; background-color:white; border:none; }
</style>
<script>
$(document).ready(function() {		
	$("#con_textarea").css("height", $("textarea").prop('scrollHeight')+5);
});
</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<section>
<div class="content_box">
	<div class="box">
			<table class="table">
		 		<tr>
		 			<td colspan="2"><strong>${vo.ntitle }</strong></td>
		 		</tr>
		 		<tr>
		 			<th>작성일</th>
		 			<th>${vo.ndate }</th>
		 		</tr>
		 		<tr>
		 			<th>작성자</th>
		 			<th>관리자</th>
		 		</tr>
		 		<tr>
		 			<td colspan="2">
		 				<textarea disabled style="resize: none;" id="con_textarea">${vo.ncontent }</textarea>
		 				<c:if test = "${!empty vo.nsfile}">
							<img src="http://localhost:9000/myjeju/upload/${vo.nsfile }" width=50% height=500px >
						</c:if>		
		 			</td>
		 		</tr>
		 	</table>
		<a class="btn" href="free_board.do" id="list">목록</a>	
	</div>
</div>
</section>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>