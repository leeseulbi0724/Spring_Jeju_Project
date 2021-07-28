<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "javax.servlet.jsp.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="test/html; charset=UTF-8">
	<meta name ="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/admincss/bootstrap.css">
	<link rel="stylesheet" href="css/admincss/custom.css">
	<link rel="stylesheet" href="css/admincss/setupforcounsel.css">
	<title>Myjeju 관리자 메뉴</title>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript"></script>
</head>
<style>
	.list, .update, .delete { 				
		padding:5px 10px;
		border-radius:4px;
		margin:10px 0; 
	}
	.update, .delete { background-color:rgb(20,86,184); color:white; float:right; margin-left:5px; }
	.list { background-color:rgb(228,228,228); color:black; float:left; }
	
	.table tr { border-bottom:1px solid lightgray; }
	.button tr { border-top:1px solid lightgray; }
	.table th { font-weight:normal; text-align:left; padding-left:15px; }
	.table tr:first-child td, .button th:first-child { background-color:rgb(248,248,248); }
	.table th:first-child { width:100px; }
	.table td>textarea { width:100%; background-color:white; border:none; }
</style>
<script src="http://rawgit.com/jackmoored/autosize/master/dist/autosize.min.js"></script>
<script>
$(document).ready(function() {
	$("#content").css("height", $("textarea").prop('scrollHeight')+5);
	
	$(".write").click(function() {
		if ($("#title").val() == "") {
			alert("제목을 입력해주세요");
			$("#title").focus();		
		} else if ($("#content").val() == "") {
			alert("내용을 입력해주세요");
			$("#content").focus();		
		} else {
			 admin_notice.submit();
		}
	});
	
	 $(".delete").click(function() {
		  var con_test = confirm("게시글을 삭제하시겠습니까?"); 
       	if(con_test == true){   
       		location.replace("adnotice_delete_proc.do?nid=${vo.nid}");
       	}
	  });
});
</script>
<body style="height:700px">
	<nav class="navbar navbar-default">
		<div class ="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expeanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://localhost:9000/myjeju/adminindex.do">Myjeju 관리자 메뉴</a>
		</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="http://localhost:9000/myjeju/adminindex.do">메인</a>
					<li  class="active"><a href="adnotice.do">공지사항관리</a></li>
					<li ><a href="adboard.do">게시판관리</a></li>
					<li><a href="adrequest.do">요청관리</a></li>
					<li><a href="admember.do">회원관리<span id="unread" class="label label-info"></span></a></li>
					<li><a href="adhouse.do">숙소관리</a></li>
					<li><a href="adfood.do">음식점관리</a></li>
					<li><a href="adcafe.do">카페관리</a></li>
					<li><a href="adtravel.do">여행지관리</a></li>
					<li><a href="adstore.do">상품관리</a>
				</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="http://localhost:9000/myjeju/index.do">메인으로</a>
				</li>
			</ul>						
			</div>
		</nav>
			<!-- content -->
	<div class = "content_setup_faq">
	
		<section class = "section_setup_faq">
			<div></div>
			<div> 공지사항관리 </div>
			<div></div>
		</section>
			<div style="width:1000px; display:inline-block; margin-bottom:30px;">
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
		 				<textarea disabled style="resize: none;" id="content">${vo.ncontent }</textarea>
		 				<c:if test = "${!empty vo.nsfile}">
							<img src="http://localhost:9000/myjeju/upload/${vo.nsfile }" width=50% height=500px >
						</c:if>		
		 			</td>
		 		</tr>
		 	</table>
		 	<a href="adnotice.do" class="list">목록</a>
		 	<a href="adnotice_update.do?nid=${vo.nid }" class="update">수정</a>
		 	<a class="delete">삭제</a>
			</div>
		<section class ="setup_faq_list">
		
		</section>
		
	</div>
</body>
</html>