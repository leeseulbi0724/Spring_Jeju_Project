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
</style>		
<script>

	$(document).ready(function() {
		
		$("#adhouseUpdateBtn").click(function() {
			if($("#h_name").val() == "") {
				alert("숙소명을 입력해주세요");
				$("#h_name").focus();
				return false;
			} else if ($("#h_infor").val() == "") {
				alert("숙소종류를 입력해주세요");
				$("#h_name2").focus();
				return false;
			} else if ($("#h_infor2").val() == "") {
				alert("숙소설명을 입력해주세요");
				$("#h_infor").focus();
				return false;
			} else if ($("#h_img").val() == "") {
				alert("이미지를 삽입해주세요");
				$("#h_image").focus(); 
				return false;
			} else if ($("#h_tag").val() == "") {
				alert("숙소태그을 입력해주세요");
				$("#h_tag").focus();
				return false;
			}else if ($("#h_addr").val() == "") {
				alert("숙소주소를 입력해주세요");
				$("#h_address").focus();
				return false;
			}else if ($("#h_hpoint").val() == "") {
				alert("숙소위도를 입력해주세요");
				$("#h_hpoint").focus();
				return false;
			}else if ($("#h_vpoint").val() == "") {
				alert("숙소경도를 입력해주세요");
				$("#h_vpoint").focus();
				return false;
			}else if ($("#h_hp").val() == "") {
				alert("숙소연락처를 입력해주세요");
				$("#h_hp").focus();
				return false;
			}else if ($("#email").val() == "") {
				alert("숙소연락처를 입력해주세요");
				$("#email").focus();
				return false;
			}else {
				adhouse_update_form.submit();
			}
		});
	});

</script>

<body style = "height : 900px;">

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
				<li><a href="adboard.do">게시판관리</a></li>
				<li><a href="adrequest.do">요청관리</a></li>
				<li><a href="admember.do">회원관리<span id="unread" class="label label-info"></span></a></li>
				<li class="active"><a href="adhouse.do">숙소관리</a></li>
				<li><a href="adfood.do">음식점관리</a></li>
				<li><a href="#">카페관리</a></li>
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
			<div>숙소수정</div>
			<div></div>
		</section>
		
		<div style="width:1000px; display:inline-block;">
			<form name = "adhouse_update_form" action = "adhouse_update_proc.do" method = "post" enctype = "multipart/form-data">
						<table class="table">
				<tr>
					<th class="title">이름</th>
					<th><input type = "text" name = "h_name" id = "h_name" value = "${vo.h_name}" class = "form-control" placeholder = "숙소명을 입력해주세요"></th>
					<th class="title">해시태그</th>
					<th><input type = "text" name = "h_tag" id = "h_tag" value = "${vo.h_tag}" class = "form-control" placeholder = "숙소태그를 입력해주세요"></th>
					<th class="title">종류</th>
					<th><input type = "text" name = "h_infor" id = "h_infor" value = "${vo.h_infor}" class = "form-control" placeholder = "숙소종류를 입력해주세요"></th>
				</tr>
				<tr>
					<th class="title">설명</th>
					<th colspan="5"><input type = "text" name = "h_infor2" id = "h_infor2" value = "${vo.h_infor2}" class = "form-control" placeholder = "숙소설명을 입력해주세요"></th>
				</tr>
				<tr>
					<th class="title">주소</th>
					<th colspan="4">
						<input type = "text" name = "h_addr" id = "h_addr" value = "${vo.h_addr}" class = "form-control" placeholder = "숙소주소를 입력해주세요">
					</th>
				</tr>
				<tr>
					<th class="title">경도</th>
					<th><input type = "text" name = "h_vpoint" id = "h_vpoint" value = "${vo.h_vpoint}" class = "form-control" placeholder = "숙소경도를 입력해주세요"></th>
					<th class="title">위도</th>
					<th><input type = "text" name = "h_hpoint" id = "h_hpoint" value = "${vo.h_hpoint}" class = "form-control" placeholder = "숙소위도를 입력해주세요"></th>
					<th class="title">연락처</th>
					<th><input type = "text" name = "h_hp" id = "h_hp"  class = "form-control" value="(+82)${vo.h_hpoint}" placeholder = "숙소연락처를 입력해주세요"></th>
				</tr>
				<tr>
					<th class="title">이메일</th>
					<th colspan="4">
						<input type = "text" name = "email" id = "email" value = "${vo.email}" class="form-control" placeholder = "이메일을 입력해주세요">
					</th>
				</tr>
				<tr>
					<th class="title">사진</th>
					<th colspan="5"><input type = "file" name = "hfile1" id = "hfile1" class="form-control"></th>
				</tr>		
			</table>
				<input type="hidden" name="hid" value="${vo.hid }">
				<button type = "button" id = "adhouseUpdateBtn">수정</button>
				<a href = "adhouse.do"><button type ="button">숙소 홈으로</button></a>
			</form>
		</div>
	</div>

	
</body>
</html>