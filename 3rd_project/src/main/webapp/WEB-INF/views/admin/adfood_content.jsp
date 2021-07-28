<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="test/html; charset=UTF-8">
	<meta name ="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/admincss/bootstrap.css">
	<link rel="stylesheet" href="css/admincss/custom.css">
	<link rel ="stylesheet" href ="css/admincss/setupforcounsel.css">
	<title>Myjeju 관리자 메뉴</title>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/bootstrap.js"></script>
<script>
$(document).ready(function() {
	$("#delete").click(function() {
		if (confirm("해당 음식점을 삭제하시겠습니까?")) {	
			$.ajax({
                type: "post",
                url: "adfood_delete.do",             
                data:{fid:"${vo.fid}"},
                dataType: 'json',
                success: function (result) {
                	location.replace("adfood.do");
                },
           }); 
		} else {

		}		
	});
});
</script>
</head>
<body>
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
					<li  class="active"><a href="adfood.do">음식점관리</a></li>
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
	<div class = "content_setup_faq" style="margin-top:45px;">
		<section>
			<form name = "faq_content" action = "../../MenuDeleteServlet" method = "post">
				<input type="hidden" name="hid" value="${vo.fid}">
				<h3>${vo.f_name}</h3>
				<div>${vo.f_infor}</div>
				<hr style="display: inline-block; width:100%; border-top:1px solid #006DCC; opacity:0.5;">
				<div class="content" style="text-align:left;">
					<div style="margin:5px 0 30px 0; text-align:center;">
					<c:forEach var="img_name" items="${img}">
						<img src="http://localhost:9000/myjeju/resources/images/food/food_detail/${img_name}" style ="width:200px; height:200px;">
					</c:forEach>
					</div>
					<div style="padding-left:100px;">
						<div style="margin-bottom: 30px">설명 : ${vo.f_infor2}</div>
						<div style="margin-bottom: 30px">태그정보 : ${vo.f_tag}</div>
						<div style="margin-bottom: 30px">주소 : ${vo.f_addr}</div>
						<div style="margin-bottom: 30px">경도 : ${vo.f_vpoint}</div>
						<div style="margin-bottom: 30px">위도 : ${vo.f_hpoint}</div>
						<div style="margin-bottom: 30px">HP : ${vo.f_hp}</div>
						<div style="margin-bottom: 30px">좋아요 : ${vo.f_like}</div>
					</div>
				</div>
				<hr style="border-top:1px solid #006DCC; opacity:0.5;">
				<button type = "button" class = "btn_setup_faq" onclick="location.href='adfood_update.do?fid=${vo.fid}'">수정</button>
				<button type = "button" class = "btn_setup_faq" id="delete" >삭제</button>
				
				<a href = "adfood.do"><button type = "button" class = "btn_setup_faq">목록</button></a>
				<a href = "http://localhost:9000/myjeju/adminindex.do"><button type = "button" class = "btn_setup_faq">홈으로</button></a>
			</form>	
		</section>
	</div>
</body>
</html>
	
	
	