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
		if (confirm("해당 카페을 삭제하시겠습니까?")) {	
			$.ajax({
                type: "post",
                url: "adcafe_delete.do",             
                data:{caid:"${vo.caid}"},
                dataType: 'json',
                success: function (result) {
                	location.replace("adcafe.do");
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
					<li><a href="adfood.do">음식점관리</a></li>
					<li class="active"><a href="adcafe.do">카페관리</a></li>
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
				<input type="hidden" name="hid" value="${vo.caid}">
				<h3>${vo.ca_name}</h3>
				<div>${vo.ca_infor}</div>
				<hr style="display: inline-block; width:100%; border-top:1px solid #006DCC; opacity:0.5;">
				<div class="content" style="text-align:left;">
					<div style="margin:5px 0 30px 0; text-align:center;">
					<c:forEach var="img_name" items="${img}">
						<img src="http://localhost:9000/myjeju/resources/images/cafe/cafe_detail/${img_name}" style ="width:200px; height:200px;">
					</c:forEach>
					</div>
					<div style="padding-left:100px;">
						<div style="margin-bottom: 30px">설명 : ${vo.ca_infor2}</div>
						<div style="margin-bottom: 30px">태그정보 : ${vo.ca_tag}</div>
						<div style="margin-bottom: 30px">주소 : ${vo.ca_addr}</div>
						<div style="margin-bottom: 30px">경도 : ${vo.ca_vpoint}</div>
						<div style="margin-bottom: 30px">위도 : ${vo.ca_hpoint}</div>
						<div style="margin-bottom: 30px">HP : ${vo.ca_hp}</div>
						<div style="margin-bottom: 30px">좋아요 : ${vo.ca_like}</div>
					</div>
				</div>
				<hr style="border-top:1px solid #006DCC; opacity:0.5;">
				<button type = "button" class = "btn_setup_faq" onclick="location.href='adcafe_update.do?caid=${vo.caid}'">수정</button>
				<button type = "button" class = "btn_setup_faq" id="delete" >삭제</button>
				
				<a href = "adcafe.do"><button type = "button" class = "btn_setup_faq">목록</button></a>
				<a href = "http://localhost:9000/myjeju/adminindex.do"><button type = "button" class = "btn_setup_faq">홈으로</button></a>
			</form>	
		</section>
	</div>
</body>
</html>
	
	
	