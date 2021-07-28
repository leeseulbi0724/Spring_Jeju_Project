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
	.write, .list { 				
		padding:5px 10px;
		border-radius:4px;
		margin:10px 0; 
	}
	.write { background-color:rgb(20,86,184); color:white;  }
	.list { background-color:rgb(228,228,228); color:black; }
	
	.table tr { border-bottom:1px solid lightgray; }
	.table .title {
		background-color:rgb(248,248,248);
		vertical-align : middle;		
	}
	.table #hid { width:60px; display:inline-block; }
	
	.img_name, .file_name, .old_name { display:none; }
	th>div>button { display:inline-block; margin-left:5px; }
	#file { width:600px; display:inline-block; }
</style>
<script>
$(document).ready(function() {
	$("#img_name").val($(".img_name").text());
	$("#file_name").val($(".file_name").text());
	
	$(".write").click(function() {
		if ($("#name").val() == "") {
			alert("객실 이름을 입력해주세요");
			$("#name").focus();		
		} else if ($("#price").val() == "") {
			alert("가격을 입력해주세요");
			$("#price").focus();		
		} else if ($("#count").val() == "") {
			alert("명수를 선택해주세요");
			$("#count").focus();		
		} else {
			/* var img_name = $(".img_name").text();
			var file_name = $(".file_name").text();
			var form1 = $("#form").serialize();
			$.ajax({
                type: "post",
                url: "adhouse_de_update_proc.do",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                data:{form1:form1, img:img_name, file:file_name},
                dataType: 'json',
                success: function (result) {
                	
                },
           });  */
           admin_house.submit();
		}
	});
	
	
	$("button[class='minus']").click(function() {
		$(this).closest("div").next().next().remove();
		$(this).closest("div").next().remove();
		$(this).closest("div").remove();
		
		$("#img_name").val($(".img_name").text());
		$("#file_name").val($(".file_name").text());
		
		$(".old_name").append($(this).prev().attr("name")+",");
		$("#old_name").val($(".old_name").text());
	});
});
</script>
<body style="height:900px">
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
					<li><a href="adrequest.do">요청관리</a></li>
					<li><a href="admember.do">회원관리<span id="unread" class="label label-info"></span></a></li>
					<li class="active"><a href="adhouse.do">숙소관리</a></li>
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
			<div> 숙소관리 </div>
			<div></div>
		</section>
			<div style="width:1000px; display:inline-block;">
			<form id="form"  name="admin_house" enctype="multipart/form-data" method="post" action="adhouse_de_update_proc.do">
			<table class="table">
		 		<tr>
		 			<th class="title">숙소 아이디</th>
		 			<th><input type="text" class="form-control" value="${vo.hid }" readonly id="hid" name="hid"></th>
		 			<th class="title">객실 이름</th>
		 			<th><input type="text" class="form-control" placeholder="객실 이름을 입력해주세요" id="name" name="hd_name" value="${vo.hd_name }"></th>
		 			<th class="title">가격</th>
		 			<th><input type="text" class="form-control" placeholder="(1박기준)가격을 입력해주세요" id="price" name="hd_price" value="${vo.hd_price }"></th>
		 			<th class="title">최대 숙박 명수</th>
		 			<th>
		 				<select class="form-control" id="count" name="hd_people">
		 					<c:if test = "${vo.hd_people eq 1 }">
			 					<option value="">선택
								<option value="1" selected>1명
								<option value="2">2명
								<option value="3">3명
								<option value="4">4명			
							</c:if>	
							<c:if test = "${vo.hd_people eq 2 }">
			 					<option value="">선택
								<option value="1">1명
								<option value="2"  selected>2명
								<option value="3">3명
								<option value="4">4명			
							</c:if>	
							<c:if test = "${vo.hd_people eq 3 }">
			 					<option value="">선택
								<option value="1">1명
								<option value="2"  >2명
								<option value="3" selected>3명
								<option value="4">4명			
							</c:if>		
							<c:if test = "${vo.hd_people eq 4 }">
			 					<option value="">선택
								<option value="1">1명
								<option value="2"  >2명
								<option value="3">3명
								<option value="4"  selected>4명			
							</c:if>	
						</select>
		 			</th>		 			
		 		</tr>		
			 	<tr>
		 			<th class="title" >객실 사진 추가</th>
		 			<th colspan="7"><input type="file" class="form-control" multiple="multiple" name="file" id="file"></th>
		 		</tr> 		
		 		<tr>
		 			<th class="title" rowspan="3">기존 객실 사진</th>
		 			<th colspan="7">
				 		<c:forEach var="vo"  items="${list}">
			 				<div>
			 					<input type="text" class="form-control"  id="file" readonly value="${vo.hd_img }" name="${vo.hd_file }">
			 					<button type="button" class="minus">-</button>
			 				</div>
		 					<span class="img_name">${vo.hd_img},</span>
		 					<span class="file_name">${vo.hd_file},</span>		 					
				 		</c:forEach>
		 			</th>		 			
		 		</tr> 			 		
		 	</table>
		 		<input type="hidden" name="hdid" value="${vo.hdid }">
		 		<input  id="img_name"  type="hidden" name="hd_img">
		 		<input  id="file_name" type="hidden" name="hd_file">
		 		<input  id="old_name" type="hidden" name="old_name">
		 		<span class="old_name"></span>
				<a href="adhouse_de.do?hid=${hid }" class="list" >취소</a>
				<a class="write" >수정</a>
			</form>
			</div>
		<section class ="setup_faq_list">
		
		</section>
		
	</div>
</body>
</html>