
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
		var result = ${result};
		if(result == "1"){
			alert("일괄예약에 성공했습니다.");
		}else if (result=="2") {
			alert("일괄예약에 실패했습니다.\n상세예약 또는 방별 일괄 예약을 진행해 주십시오.");
		}else if (result=="3") {
			alert("방 삭제에 성공했습니다");
		}else if (result=="4") {
			alert("방 삭제에 실패했습니다");
		}else if (result=="5") {
			alert("월간 예약 데이터가 없습니다.\n일괄예약을 먼저 실행해주세요.");
		}
		
		var hdid = $(".hdid").val();
		
		$("#btn_res_all").click(function() {
			var year = $("#countyear").val();
			var month = $("#countmonth").val();
			if($("#countyear").val() == "") {
				alert("년도를 입력해주세요");
				$("#countyear").focus();
				return false;
			}else if($("#countmonth").val() == "") {
				alert("월을 입력해주세요");
				$("#countmonth").focus();
				return false;
			}else {
				location.href = "adhouse_res_all.do?hdid="+hdid+"&month="+month+"&year="+year;
			}
		});
		$(".btn_res_each").click(function() {
			var year = $("#countyear").val();
			var month = $("#countmonth").val();
			var roomid = $(this).val();
			if($("#countyear").val() == "") {
				alert("년도를 입력해주세요");
				$("#countyear").focus();
				return false;
			}else if($("#countmonth").val() == "") {
				alert("월을 입력해주세요");
				$("#countmonth").focus();
				return false;
			}else {
				location.href = "adhouse_res_each.do?hdid="+hdid+"&month="+month+"&year="+year+"&roomid="+roomid;
			}
		});
		
		$(".btn_deres").click(function() {
			var year = $("#countyear").val();
			var month = $("#countmonth").val();
			if($("#countyear").val() == "") {
				alert("년도를 입력해주세요");
				$("#countyear").focus();
				return false;
			}else if($("#countmonth").val() == "") {
				alert("월을 입력해주세요");
				$("#countmonth").focus();
				return false;
			}else{
				$(".deyear").val(year);
				$(".demonth").val(month);
			}
		});
	});

</script>
<style>
	.write { 
		background-color:rgb(20,86,184); 
		color:white; 
		padding:5px 10px;
		border-radius:4px;
		float:right;
		margin:10px 0;
	}
</style>
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
					<li><a href="#">메인</a>
					<li><a href="adnotice.do">공지사항관리</a></li>
					<li><a href="adboard.do">게시판관리</a></li>
					<li><a href="adrequest.do">요청관리</a></li>
					<li><a href="http://localhost:9000/myjeju/admember.do">회원관리<span id="unread" class="label label-info"></span></a></li>
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
			<div> 방관리 </div>
			<div></div>
		</section>
	
		<section class ="setup_faq_list">
			<a href="adhouse_de_room_write.do?hdid=${hdid}" class="write" >방추가</a>
			<table class = "content_layout_setup_faq">
				<tr>
					<th> 번호 </th>
					<th> 객실아이디 </th>
					<th> 방아이디 </th>
					<th> 방이름 </th>
					<th> 예약처리 </th>
					<th> 방삭제 </th>
				</tr>
			 	<c:forEach var="vo" items="${list}">
				<tr>
					<td>${vo.rno}</td>
					<td onclick="#'">${vo.hdid}</td>
					<td onclick="#'">${vo.roomid}</td>
					<td onclick="#'">${vo.room_name}</td>
					<td style="width:150px;">
						<form name="deres1" action="adcalendar.do" method="post" style="display:inline-block;">
							<input type="hidden" name="roomid" value="${vo.roomid}">
							<input type="hidden" name="hdid" value="${vo.hdid}">
							<input type="hidden" name="deyear" class="deyear">
							<input type="hidden" name="demonth" class="demonth">
							<input type="hidden" name="room_name" value='${vo.room_name}'>
							<button type ="submit" class="btn_deres">상세예약</button>
						</form>
						<button type="button" class="btn_res_each" value="${vo.roomid}">일괄예약</button>
					</td>
					<td style="width:110px;">
						<form name="delete" action="adhouse_de_room_delete_proc.do" method="get">
						<input type="hidden" name="roomid" value="${vo.roomid}">
						<input type="hidden" name="hdid" value="${vo.hdid}">
						<button type="submit">삭제</button>
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>			
		</section>
		<section class = "setup_faq_search">
			<form name = "setup_counsel_form" action ="adhouse.do" method = "post">
			<input type="hidden" class="hdid" value = '${hdid}'>
				<div style="display:inline-block;">
					<select class="form-control" id="countyear" name="room_year" style="width:150px; display:inline-block;">
			 					<option value="">연도 선택
								<option value="2021">2021년
								<option value="2022">2022년
					</select>
				</div>
				<select class="form-control" id="countmonth" name="room_month" style="width:150px; display:inline-block;margin:0 10px;">
		 					<option value="">월 선택
							<option value="1">1 월
							<option value="2">2 월
							<option value="3">3 월
							<option value="4">4 월					
							<option value="5">5 월					
							<option value="6">6 월					
							<option value="7">7 월					
							<option value="8">8 월					
							<option value="9">9 월					
							<option value="10">10 월					
							<option value="11">11 월					
							<option value="12">12 월					
				</select>
				<button type = "button" class = "btn_search" id="btn_res_all" style="width:100px;">일괄 예약</button>
			</form>
		</section>
	</div>
</body>
</html>