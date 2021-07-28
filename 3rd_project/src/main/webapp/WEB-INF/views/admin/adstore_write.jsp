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
<script>

	$(document).ready(function() {
		
		$("#adstoreWriteBtn").click(function() {
			if($("#s_category").val() == "choice") {
				alert("카테고리를 입력해주세요");
				$("#s_category").focus();
				return false;
			} else if ($("#s_name").val() == "") {
				alert("상품명을 입력해주세요");
				$("#s_name").focus();
				return false;
			} else if ($("#s_price").val() == "") {
				alert("가격을 입력해주세요");
				$("#s_price").focus();
				return false;
			} else if ($("#s_image").val() == "") {
				alert("메인이미지를 삽입해주세요");
				$("#s_image").focus(); 
				return false;
			} else if ($("#s_content").val() == "") {
				alert("상세이미지를 삽입해주세요");
				$("#s_content").focus(); 
				return false;	
			} else {
				adstore_write_form.submit();
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
				<li><a href="adhouse.do">숙소관리</a></li>
				<li><a href="adfood.do">음식점관리</a></li>
				<li><a href="adcafe.do">카페관리</a></li>
				<li><a href="adtravel.do">여행지관리</a></li>
				<li class="active"><a href="adstore.do">상품관리</a>
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
			<div>상품등록</div>
			<div></div>
		</section>
		
		<div style="width:1000px; display:inline-block;">
			<form name = "adstore_write_form" action = "adstore_write_proc.do" method = "post" enctype = "multipart/form-data">
				<select name = "s_category" id = "s_category" class = "form-control" style="margin-bottom:20px;">
					<option value = "choice">카테고리를 선택해주세요</option>
					<option value = "식품">식품</option>
					<option value = "기념품">기념품</option>
					<option value = "잡화">잡화</option>
				</select>
				
				<input type = "text" name = "s_name" id = "s_name" class = "form-control" placeholder = "상품명을 입력해주세요" style="margin-bottom:20px;">
				<input type = "text" name = "s_price" id = "s_price"  class = "form-control" placeholder = "가격을 입력해주세요" style="margin-bottom:20px;">
				<input type = "file" name = "sfile1" id = "s_image" class="form-control" style="margin-bottom:20px;">
				<input type = "file" name = "sfile2" id = "s_content" class="form-control" style="margin-bottom:20px;">
			
				<button type = "button" id = "adstoreWriteBtn">상품등록</button>
				<a href = "adstore.do"><button type ="button">스토어 홈으로</button></a>
			</form>
		</div>
	</div>

	
</body>
</html>