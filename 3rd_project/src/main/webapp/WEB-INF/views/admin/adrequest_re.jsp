<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "javax.servlet.jsp.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageNumber" value='${pageNumber}' />
<c:set var="targetpage" value='${targetpage}' />
<c:set var="search" value='${search}' />
<c:set var="search_text" value='${search_text}' />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="test/html; charset=UTF-8">
	<meta name ="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/admincss/bootstrap.css">
	<link rel="stylesheet" href="css/admincss/custom.css">
	<link rel="stylesheet" href="css/admincss/setupforcounsel.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/community/board_content.css">
	<title>Myjeju 관리자 메뉴</title>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript"></script>
</head>
<style>
	.title:hover { text-decoration:underline; }
	html, body { height:1000px; }
	#textarea { width:80%; height:100px; display:inline-block; }
	.comment_box button {
		background-color:rgb(20,86,184); 
		color:white; 
		padding:5px 10px;
		border-radius:4px;
		float:right;
		margin-right:20px;
		margin-left:-50px;
	}
</style>
<script>
	  $(function(){
		  $("#re_btn").on("click", comment);
	    });	  	   
	  function comment() {			  
        var rid = "${vo.rid}";
        var textarea = $("#textarea").val();
       $.ajax({
            type: "post",
            url: "adrequest_comment.do",
            data:{rid:rid, textarea:textarea } ,
            dataType: 'json',
            success: function (result) {
            	  location.reload();
            },
        });
	  }	
	  
	  $(function(){
		  $("#del").on("click", board_delete);
	    });	  	   
	  function board_delete() {
		  var rid = "${vo.rid}";
		 var con_test = confirm("게시글을 삭제하시겠습니까?"); 		 
      	if(con_test == true){   
      	   $.ajax({
               type: "post",
               url: "admin_request_delete.do",
               data:{rid:rid} ,
               dataType: 'json',
               success: function (result) {
            	   if (result) {
            		   alert("삭제가 완료되었습니다");
            		   location.replace("adrequest.do");
            	   }
               },
           });
      	}
        
    
	  }	
</script>
<body id="body">
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
					<li><a href="adnotice.do">공지사항관리</a></li>
					<li ><a href="adboard.do">게시판관리</a></li>
					<li  class="active"><a href="adrequest.do">요청관리</a></li>
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
			<div> 요청관리 </div>
			<div></div>
		</section>
	
		<section class ="setup_faq_list">
		<p class="info" style="float:right">${vo.id } | <span class="date">${vo.rdate }</span><input type="hidden" value="${vo.rid }" id="fid"></p>
			<table class="table">
				<tr>
					<th>${vo.rtitle }</th>
				</tr>
				<tr>
					<td>					 	
						<textarea class="form-control"  readonly style="resize: none;" id="con_textarea">${vo.rcontent }</textarea>
						<c:if test = "${!empty vo.rsfile}">
							<img src="http://localhost:9000/myjeju/upload/${vo.rsfile }" width=100% height=300px>
						</c:if>		
					</td>
				</tr>
		</table>
		<a class="btn btn-primary" id="del" style="float:right" >삭제</a>
		<div class="comment_box" style="display:inline-block; width:100%">
		<p class="text">답변</p><br>	
		<c:if test = "${empty rvo.rcontent }">
			<textarea class="form-control"  style="resize: none;" placeholder="답변내용을 입력해주세요"  id="textarea"></textarea>
			<button type="button" id="re_btn">답변등록</button>		
			</c:if>		
			<c:if test = "${not empty rvo.rcontent }">
				<div class="com_box" style="width:900px">
					<p>관리자</p>
					<p>${rvo.rcontent }</p>
					<p class="date">${rvo.rrdate }</p>
				</div>
				<textarea class="form-control"  style="resize: none;" placeholder="이미 답변이 완료되었습니다"  id="textarea" readonly></textarea>
			</c:if>
		</div>
		</section>		
	</div>
</body>
</html>