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
</style>
<script>
	  $(function(){
		  $(".com_delete").on("click", com_delete);
	    });	  	   
	  function com_delete() {			  
        var cid = $(this).attr("id");
       $.ajax({
            type: "post",
            url: "comment_delete.do",
            data:{cid:cid} ,
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
		  var fid = "${vo.fid}";
		 var con_test = confirm("게시글을 삭제하시겠습니까?"); 		 
      	if(con_test == true){   
      	   $.ajax({
               type: "post",
               url: "admin_board_delete.do",
               data:{fid:fid} ,
               dataType: 'json',
               success: function (result) {
            	   if (result) {
            		   alert("삭제가 완료되었습니다");
            		   location.replace("adboard.do");
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
					<li  class="active"><a href="adboard.do">게시판관리</a></li>
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
			<div> 게시판관리 </div>
			<div></div>
		</section>
	
		<section class ="setup_faq_list">
		<p class="info" style="float:right">${vo.id } | <span class="date">${vo.fdate }</span><input type="hidden" value="${vo.fid }" id="fid"></p>
			<table class="table">
				<tr>
					<th>${vo.ftitle }</th>
				</tr>
				<tr>
					<td>					 	
						<textarea class="form-control"  readonly style="resize: none;" id="con_textarea">${vo.fcontent }</textarea>
						<c:if test = "${!empty vo.fsfile}">
							<img src="http://localhost:9000/myjeju/upload/${vo.fsfile }" width=100% height=300px>
						</c:if>		
					</td>
				</tr>
		</table>
		<a class="btn btn-primary" id="del" style="float:right" >삭제</a>
		<div class="comment_box" style="display:inline-block; width:100%">
		<p class="text">댓글</p><br>
			<ul>
				<li>
					<c:forEach var = "vo"  items="${list}" varStatus="status">				
						<div class="com_box" style="width:900px">
							<p>${vo.id }</p>
							<p>${vo.ccomment }</p>
								<p class="date">${vo.cdate }									
								<a class="com_delete" id="${vo.cid }" >삭제</a>
								</p>
						</div>	
						</c:forEach>
				</li>
				<li>					
				</li>
			</ul>		
		</div>
		</section>		
	</div>
</body>
</html>