<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
function deleteFunction() {
	if (confirm("정말 이 데이터를 삭제 하시겠습니까?")) {
		faq_content.submit();         
    } else {
    	alert("삭제을 취소했습니다.");
    }
}

$(document).ready(function() {
	
	$("#delete").click(function() {
		var del = confirm("해당 상품을 삭제하시겠습니까?");
		
		if(del) {
			location.replace("adhouse_delete_proc.do?hid=${vo.hid}");
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
	<div class = "content_setup_faq" style="margin-top:45px;">
		<section>
			<form name = "faq_content" action = "../../MenuDeleteServlet" method = "post">
				<input type="hidden" name="hid" value="${vo.hid}">
				<h3>${vo.h_name}</h3>
				<div>${vo.h_infor}</div>
				<hr style="display: inline-block; width:100%; border-top:1px solid #006DCC; opacity:0.5;">
				<div class="content" style="text-align:left; padding-left:300px;">
					<div style="margin-bottom: 30px;margin-top: 5px; padding-left:197px;">
						<img src="http://localhost:9000/myjeju/resources/images/house/${vo.h_file}" style ="width:200px; height:200px;">
					</div>
					<div style="margin-bottom: 30px">설명 : ${vo.h_infor2}</div>
					<div style="margin-bottom: 30px">태그정보 : ${vo.h_tag}</div>
					<div style="margin-bottom: 30px">주소 : ${vo.h_addr}</div>
					<div style="margin-bottom: 30px">경도 : ${vo.h_vpoint}</div>
					<div style="margin-bottom: 30px">위도 : ${vo.h_hpoint}</div>
					<div style="margin-bottom: 30px">HP : ${vo.h_hp}</div>
					<div style="margin-bottom: 30px">좋아요 : ${vo.h_like}</div>
					<div style="margin-bottom: 30px">이메일 : ${vo.email}</div>
				</div>
				<hr style="border-top:1px solid #006DCC; opacity:0.5;">
				<button type = "button" class = "btn_setup_faq" onclick="location.href='adhouse_update.do?hid=${hid}'">수정</button>
				<button type = "button" class = "btn_setup_faq"  id="delete">삭제</button>
				<button type = "button" class = "btn_setup_faq" onclick="location.href='adhouse_de.do?hid=${hid}'">객실정보</button>
				
				<a href = "adhouse.do"><button type = "button" class = "btn_setup_faq">목록</button></a>
				<a href = "http://localhost:9000/myjeju/adminindex.do"><button type = "button" class = "btn_setup_faq">홈으로</button></a>
			</form>	
		</section>
	</div>
</body>
</html>
	
	
	