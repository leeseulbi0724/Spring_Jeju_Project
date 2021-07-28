<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>My 게시글 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/mypage/myboard.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
</head>
<script>
	$(document).ready(function() {
		$("#comment").click(function() {
			$(".board_table").css("display","none");
			$(".comment_table").css("display","block");
			$(this).css("color","rgb(3,199,90)")
			$("#board").css("color","black");
		});
		
		$("#board").click(function() {
			$(".comment_table").css("display","none");
			$(".board_table").css("display","block");
			$(this).css("color","rgb(3,199,90)")
			$("#comment").css("color","black");
		});
		
		$("button[id=free_delete]").click(function() {
			var fid = $(this).attr("name");
			var con_test = confirm("게시글을 삭제하시겠습니까?"); 
        	if(con_test == true){   
        		location.replace("free_board_delete.do?fid="+fid+"&type=mypage");
        	}
		});		
				
		
	});
</script>
<body>
<jsp:include page="../../header.jsp"></jsp:include>

<section>
	<div class="center">
		<a href="#" id="board">작성 글</a>
		<a href="#" id="comment">작성 댓글</a>		
		<div class="table_div">
			<table class="table board_table free_table">
				<tr>
					<th><strong>자유게시판</strong></th>
					<th>제목</th>
					<th>작성일</th>
					<th></th>
				</tr>
				<c:if test = "${empty list1}">
					<tr>
						<td  style="width:150px"></td>
						<td style="width:1330px">작성된 글이 없습니다</a></td>
						<td style="width:10px"></td>
						<td style="width:5px"></td>
					</tr>
				</c:if>
				<c:forEach var = "vo"  items="${list1}">
					<tr class="free_tr">
						<td>자유</td>
						<td><a href="http://localhost:9000/myjeju/free_board_content.do?fid=${vo.fid }">${vo.ftitle }</a></td>
						<td>${vo.fdate }</td>
						<td><button id="free_delete"  type="button" name="${vo.fid }" >삭제</button></td>
					</tr>
				</c:forEach>
			</table>
			<table class="table board_table request_table">
				<tr>
					<th><strong>요청게시판</strong></th>
					<th>제목</th>
					<th>작성일</th>
					<th></th>
				</tr>
				<c:if test = "${empty list2}">
					<tr>
						<td  style="width:150px"></td>
						<td style="width:1330px">작성된 글이 없습니다</a></td>
						<td style="width:10px"></td>
						<td style="width:5px"></td>
					</tr>
				</c:if>
				<c:forEach var = "vo"  items="${list2}">
					<tr class="request_tr">
						<td>요청</td>
						<td><a href="http://localhost:9000/myjeju/request_board_number.do?rid=${vo.rid }">${vo.rtitle }</a></td>
						<td>${vo.rdate }</td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
			
			<table class="table comment_table">
				<tr>
					<th colspan="2">댓글</th>
					<th>작성일</th>
				</tr>
				<c:if test = "${empty list3}">
					<tr>
						<td style="width:1000px">작성된 댓글이 없습니다</td>
						<td style="width:10px"></td>
						<td style="width:100px"></td>
					</tr>
				</c:if>
				<c:forEach var = "vo"  items="${list3}">
				<c:if test = "${not empty vo.ccomment}">
					<tr>
						<td>${vo.ccomment }</td>
						<td><a href="http://localhost:9000/myjeju/free_board_content.do?fid=${vo.fid}"}>게시글보기▶</a></td>
						<td>${vo.cdate }</td>
					</tr>
				</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</section>

<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>