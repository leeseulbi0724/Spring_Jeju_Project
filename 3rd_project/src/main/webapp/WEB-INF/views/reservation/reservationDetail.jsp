<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>예약상세 | JEJU ISLAND</title>
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/reservation/reserDetail.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$('#success').click(function() { 
		success_frm.submit();
		return true;
	});
	
});
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- content -->
	<section class="reservationDetail">
		<div class="content">
			<div class="name_section">
				<div class="name" style="margin-bottom: 10px;">${houseVO.h_name}</div>
				<div class="subname">${hdetailVO.hd_name}</div>
			</div>
			<div class="carosel">
				<div id="demo" class="carousel slide" data-ride="carousel" style="text-align:center;">

					<!-- The slideshow -->
					<div class="carousel-inner">
						<div class="carousel-item active">
					    	<img src="http://localhost:9000/myjeju/images/house/house_detail/${img[0]}" width="100%">
					    </div>
						<c:forEach var="list" items="${img}" varStatus="status" begin="1">
						<div class="carousel-item">
					    	<img src="http://localhost:9000/myjeju/images/house/house_detail/${list}" width="100%">
					    </div>
					 	</c:forEach>
					</div>
					<!-- Left and right controls -->
					<a class="carousel-control-prev" href="#demo" data-slide="prev">
						<span class="carousel-control-prev-icon"></span>
					</a>
					<a class="carousel-control-next" href="#demo" data-slide="next">
					    <span class="carousel-control-next-icon"></span>
					</a>
				</div>
			</div>
		</div>
		<div class="text_section">
			<div class="namesection2">
				<div class="name2" style="margin-bottom: 10px;">${houseVO.h_name}</div>
				<div class="subname2">${hdetailVO.hd_name}</div>
			</div>
			<div class = "explain">${houseVO.h_infor2}</div>
			<div class="date">
				<div class="f_day">${f_day}</div> ~ <div class="s_day">${s_day}</div>
			</div>
			<div class= "price_section">
				<div class="price">${fullprice}원</div>
				<div class="seperate"></div>
				<div class="price_cal">${hdetailVO.hd_price}</div>
				<div class="x">x</div>
				<div class="night">${night}박</div>
			</div>
			<form action="http://localhost:9000/myjeju/calendar.do" method="post">
			<input type="hidden" name="hid" value='${hid}'>
			<input type="hidden" name="hdid" value='${hdid}'>
			<input type="hidden" name="roomid" value='${roomid}'>
			<input type="hidden" name="sessionid" value='${session_id}'>
			<input type="hidden" name="f_day" value='${f_day}'>
			<input type="hidden" name="s_day" value='${s_day}'>
			<input type="hidden" name="preyear" value='${preyear}'>
			<input type="hidden" name="premonth" value='${premonth}'>
			<button class="search_btn" type="submit">일정변경</button>			
			</form>
			<form name="success_frm" action="http://localhost:9000/myjeju/resersuccess.do" method="post">
			<input type="hidden" name="hid" value='${hid}'>
			<input type="hidden" name="hdid" value='${hdid}'>
			<input type="hidden" name="roomid" value='${roomid}'>
			<input type="hidden" name="sessionid" value='${session_id}'>
			<input type="hidden" name="f_day" value='${f_day}'>
			<input type="hidden" name="s_day" value='${s_day}'>
			<button class="search_btn" type="button" id="success">예약하기</button>			
			</form>
		</div>		
	</section>
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>