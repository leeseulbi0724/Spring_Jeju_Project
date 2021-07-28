<%@page import="org.apache.maven.shared.invoker.SystemOutHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%	
	String[] days = {"일","월","화","수","목","금","토"};
	
	String hid = request.getParameter("hid");
	String hdid = request.getParameter("hdid");
	String month = "0"; 
	String day = "0";
	String smonth = "0"; 
	String sday = "0";
%>
<!DOCTYPE html>
<html>
<head>
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>예약하기 | JEJU ISLAND</title>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" href="css/admincss/bootstrap.css">
	<link rel="stylesheet" href="css/admincss/custom.css">
	<link rel="stylesheet" href="css/admincss/setupforcounsel.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/reservation/reservation.css">
<script>
$(document).ready(function(){
	$('.month[value='+ ${month} +']').parent().css({"background-color":"#e3ff90"});
    $('.avail[value='+ -1 +']').parent().css({"color":"white","background-color":"#ff4040"});
    $('.avail[value='+ 2 +']').parent().css({"background-color":"yellow"});
	$('.month[value='+ ${month-1} +']').parent().css({"color":"rgba(0,0,0,0)","background-color":"rgba(0,0,0,0)"});
	$('.month[value='+ ${month+1} +']').parent().css({"color":"rgba(0,0,0,0)","background-color":"rgba(0,0,0,0)"});
	
	$('.month[value='+ ${month} +']').parent().click(function() {
		var year = $(this).children(".year").val();
		var month = $(this).children(".month").val();
		var day = $(this).children(".day").val();
		var roomid = $(".roomid").val();
		$.fn.notavail(roomid,year,month,day);
		location.reload();
	});
	
	$('.avail[value='+ 2 +']').parent().off('click');
	$('.avail[value='+ 2 +']').parent().click(function() {
		var year = $(this).children(".year").val();
		var month = $(this).children(".month").val();
		var day = $(this).children(".day").val();
		var roomid = $(".roomid").val();
		$.fn.toavail(roomid,year,month,day);
		location.reload();	
	});
	
	$('.avail[value='+ -1 +']').parent().off('click');
	$('.avail[value='+ -1 +']').parent().click(function() {
		alert("이미 고객님께서 예약한 날짜입니다.");
	});
	
	$.fn.notavail = function(roomid,year,month,day) {
    	$.ajax({
			type: "POST",
			url: "notavail",
			data: {
				roomid : roomid,
				year : year,
				month : month,
				day : day
			},
			success: function(result) {
				alert(result);
			},
			error: function(){
                alert("simpleWithObject err");
            }
		});
    }
	$.fn.toavail = function(roomid,year,month,day) {
    	$.ajax({
			type: "POST",
			url: "toavail",
			data: {
				roomid : roomid,
				year : year,
				month : month,
				day : day
			},
			success: function(result) {
				alert(result);
			},
			error: function(){
                alert("simpleWithObject err");
            }
		});
    }
	$('.search_btn').click(function() {
		var hdid = $('.hdid').val();
		location.href = "adhouse_de_room.do?hdid="+hdid;
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
	<div class="calendar_content">
		<h3 style="margin-bottom: 10px;">예약하기</h3>
		<br>
		<h3 class="date" style="font-size:25px; margin-bottom: 10px;">${year}.${month}</h3>
		
		<section class="calendar">
			<%
			for(int i = 0; i< days.length; i++) {
			%>
				<div class="day" style="padding:40px;padding-top:0px;"><%= days[i] %></div>	
			<%
			}
			%>
			<br>
			<div class="days_sun">
				<input type="hidden" class="month" value = "${calvalue[0].month}">
				<input type="hidden" class="day" value = "${calvalue[0].day}">
				<input type="hidden" class="dayval" value = "${calvalue[0].month*100 + calvalue[0].day}">
				<input type="hidden" class="avail" value = "${availlast[0]}">
				<input type="hidden" class="year" value = "${calvalue[0].year}">
				<div>${calvalue[0].day}</div> 
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="1" end="5">
			<div class="days" id="days">
				<input type="hidden" class="month" value = "${list.month}"> 
				<input type="hidden" class="day" value = "${list.day}">
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}"> 
				<div>${list.day }</div> 
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" class="month" value = "${calvalue[6].month}"> 
				<input type="hidden" class="day" value = "${calvalue[6].day}">
				<input type="hidden" class="dayval" value = "${calvalue[6].month*100 + calvalue[6].day}"> 
				<input type="hidden" class="avail" value = "${availlast[6]}">
				<input type="hidden" class="year" value = "${calvalue[6].year}"> 
				<div>${calvalue[6].day}</div>
			</div>
			<br>
			<div class="days_sun">
				<input type="hidden" class="month" value = "${calvalue[7].month}"> 
				<input type="hidden" class="day" value = "${calvalue[7].day}">
				<input type="hidden" class="dayval" value = "${calvalue[7].month*100 + calvalue[7].day}">  
				<input type="hidden" class="avail" value = "${availlast[7]}">
				<input type="hidden" class="year" value = "${calvalue[7].year}">  
				<div>${calvalue[7].day}</div>
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="8" end="12">
			<div class="days" id="days">
				<input type="hidden" class="month" value = "${list.month}"> 
				<input type="hidden" class="day" value = "${list.day}">
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}"> 
				<div>${list.day }</div>
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" class="month" value = "${calvalue[13].month}"> 
				<input type="hidden" class="day" value = "${calvalue[13].day}">
				<input type="hidden" class="dayval" value = "${calvalue[13].month*100 + calvalue[13].day}"> 
				<input type="hidden" class="avail" value = "${availlast[13]}">
				<input type="hidden" class="year" value = "${calvalue[13].year}"> 
				<div>${calvalue[13].day}</div> 
			</div>
			<br>
			<div class="days_sun">
				<input type="hidden" class="month" value = "${calvalue[14].month}"> 
				<input type="hidden" class="day" value = "${calvalue[14].day}">
				<input type="hidden" class="dayval" value = "${calvalue[14].month*100 + calvalue[14].day}"> 
				<input type="hidden" class="avail" value = "${availlast[14]}">
				<input type="hidden" class="year" value = "${calvalue[14].year}"> 
				<div>${calvalue[14].day}</div> 
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="15" end="19">
			<div class="days" id="days">
				<input type="hidden" class="month" value = "${list.month}"> 
				<input type="hidden" class="day" value = "${list.day}">
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}"> 
				<div>${list.day }</div> 
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" class="month" value = "${calvalue[20].month}"> 
				<input type="hidden" class="day" value = "${calvalue[20].day}">
				<input type="hidden" class="dayval" value = "${calvalue[20].month*100 + calvalue[20].day}">
				<input type="hidden" class="avail" value = "${availlast[20]}">
				<input type="hidden" class="year" value = "${calvalue[20].year}">
				<div>${calvalue[20].day}</div>
			</div>
			<br>
			<div class="days_sun">
				<input type="hidden" class="month" value = "${calvalue[21].month}"> 
				<input type="hidden" class="day" value = "${calvalue[21].day}">
				<input type="hidden" class="dayval" value = "${calvalue[21].month*100 + calvalue[21].day}">
				<input type="hidden" class="avail" value = "${availlast[21]}"> 
				<input type="hidden" class="year" value = "${calvalue[21].year}">
				<div>${calvalue[21].day}</div>
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="22" end="26">
			<div class="days" id="days">
				<input type="hidden" class="month" value = "${list.month}"> 
				<input type="hidden" class="day" value = "${list.day}">
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}">  
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}">  
				<div>${list.day }</div> 
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" class="month" value = "${calvalue[27].month}"> 
				<input type="hidden" class="day" value = "${calvalue[27].day}">
				<input type="hidden" class="dayval" value = "${calvalue[27].month*100 + calvalue[27].day}"> 
				<input type="hidden" class="avail" value = "${availlast[27]}"> 
				<input type="hidden" class="year" value = "${calvalue[27].year}"> 
				<div>${calvalue[27].day}</div> 
			</div>
			<br>
			<div class="days_sun">
				<input type="hidden" class="month" value = "${calvalue[28].month}"> 
				<input type="hidden" class="day" value = "${calvalue[28].day}">
				<input type="hidden" class="dayval" value = "${calvalue[28].month*100 + calvalue[28].day}">
				<input type="hidden" class="avail" value = "${availlast[28]}">  
				<input type="hidden" class="year" value = "${calvalue[28].year}">
				<div>${calvalue[28].day}</div> 
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="29" end="33">
			<div class="days" id="days">
				<input type="hidden" class="month" value = "${list.month}"> 
				<input type="hidden" class="day" value = "${list.day}">
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}"> 
				<div>${list.day }</div> 
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" class="month" value = "${calvalue[34].month}">
				<input type="hidden" class="day" value = "${calvalue[34].day}"> 
				<input type="hidden" class="dayval" value = "${calvalue[34].month*100 + calvalue[34].day}">
				<input type="hidden" class="avail" value = "${availlast[34]}">
				<input type="hidden" class="year" value = "${calvalue[34].year}">
				<div>${calvalue[34].day}</div> 
			</div>
			<br>
			
			<c:if test = "${maxrow == 6}">
				<c:forEach var="list" items="${calvalue}" varStatus="status" begin="41" end="41">
				<div class="days_sun">
					<input type="hidden" class="month" value = "${calvalue[35].month}"> 
					<input type="hidden" class="day" value = "${calvalue[35].day}">
					<input type="hidden" class="dayval" value = "${calvalue[35].month*100 + calvalue[35].day}"> 
					<input type="hidden" class="avail" value = "${availlast[35]}">
					<input type="hidden" class="year" value = "${calvalue[35].year}"> 
					<div>${calvalue[35].day}</div> 
				</div>
				</c:forEach>
			</c:if>
			<c:if test = "${maxrow == 6}">
				<c:forEach var="list" items="${calvalue}" varStatus="status" begin="36" end="40">
				<div class="days" id="days">
					<input type="hidden" class="month" value = "${list.month}"> 
					<input type="hidden" class="day" value = "${list.day}">
					<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
					<input type="hidden" class="avail" value = "${availlast[status.index]}">
					<input type="hidden" class="year" value = "${list.year}"> 
					<div>${list.day}</div> 
				</div>
				</c:forEach>
			</c:if>
			<c:if test = "${maxrow == 6}">
				<c:forEach var="list" items="${calvalue}" varStatus="status" begin="41" end="41">
				<div class="days_sat">
					<input type="hidden" class="month" value = "${calvalue[41].month}">
					<input type="hidden" class="day" value = "${calvalue[41].day}">	
					<input type="hidden" class="dayval" value = "${calvalue[41].month*100 + calvalue[41].day}"> 
					<input type="hidden" class="avail" value = "${availlast[41]}">
					<input type="hidden" class="year" value = "${calvalue[41].year}"> 
					<div>${calvalue[41].day}</div> 
				</div>
				</c:forEach>
			</c:if>
		</section>
		
		<h3>${hdname}</h3>
		<h3>${roomname}</h3>
		
		<br>
		<div class="date">
			<button class="search_btn" type="button" style="position:relative; top:0px;">완료하기</button>
		</div>
	</div>
	<input type="hidden" class="roomid" value="${roomid}">
	<input type="hidden" class="hdid" value="${hdid}">
</body>
</html>