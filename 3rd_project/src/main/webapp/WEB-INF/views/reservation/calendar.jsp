<%@page import="org.apache.maven.shared.invoker.SystemOutHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%	
	String[] days = {"일","월","화","수","목","금","토"};
	
	String preday = request.getParameter("preday");
	String presday = request.getParameter("presday");
	String hid = request.getParameter("hid");
	String hdid = request.getParameter("hdid");
	String month = "0"; 
	String day = "0";
	String smonth = "0"; 
	String sday = "0";
	
	if(preday != null ){
		if(!preday.equals("")){
		String[] prearray = preday.split("/");
		month = prearray[0]; 
		day = prearray[1];
		}
	}
	if(presday != null){
		if(!presday.equals("")){
		String[] prearray = presday.split("/");
		smonth = prearray[0]; 
		sday = prearray[1];
		}
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>예약하기 | JEJU ISLAND</title>
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/reservation/reservation.css">
<script>
$(document).ready(function(){
	
	$.fn.checkavailable = function(startvalue,month1) {
    	$.ajax({
			type: "POST",
			url: "availcheck",
			data: {
				startvalue : startvalue,
				year : ${year},
				hdid : '${hdid}',
				month : month1
			},
			success: function(result) {
				$.fn.availvalue(result);
			},
			error: function(){
                alert("simpleWithObject err");
            }
		});
    }
	$.fn.fillup = function(range) {
	    	var range_array = range.split("/");
			var f_range = Number(range_array[0])+1;     	
			var s_range = Number(range_array[1]);
	    	for(var i=f_range; i<s_range; i++) {
	    	$('[value='+ i +']').parent().children("div").css({"background-color":"#8ec8ea","color":"white"});
	    	}
	}
	  
	
	var f_day = 0;
    var s_day = 0;
    
    var month = <%= month %>;
    var day = <%= day %>;
    var smonth = <%= smonth %>;
    var sday = <%= sday %>;
    var preyears = ${year};
    if(month == 12 && ${month} == 1){
    	var preyears = ${year-1};
    }
    
    
    f_day = (month*100)+day;
    s_day = (smonth*100)+sday;
    if(f_day != 0 && s_day == 0){
    	$(".firstday").text(month+ "." + day);
    	$(".preday").val(month + "/" + day);
    	var month1 = String(month);
    	var month2 = String(month);
    	if(month1.length ==1){
    		month2 = "0" + month1;
    	}
    	var startvalue = preyears + String(month2) + String(day);
	    $.fn.checkavailable(startvalue,month1);
	    $('[value='+ f_day +']').parent().children("div").css({"background-color":"#4fa9de","color":"white","border-radius":"20px 0 0 20px"});
    }
    if(s_day != 0){
    	$(".firstday").text(month+ "." + day);
    	$(".secondday").text(smonth+ "." + sday);
    	$(".middleday").text("~");
    	$(".presday").val(smonth + "/" + sday);
    	$(".preday").val(month + "/" + day);
    	$('[value='+ f_day +']').parent().children("div").css({"background-color":"#4fa9de","color":"white","border-radius":"20px 0 0 20px"});
    	$('[value='+ s_day +']').parent().children("div").css({"background-color":"#4fa9de","color":"white","border-radius":"0 20px 20px 0"});
    	var range = f_day + "/" + s_day;
    	$.fn.fillup(range);
    }
    
    $(".days,.days_sun,.days_sat").click(function() {
    	if($(this).children('.dayval').val() < ${today} && ${year} <= ${toyear}){
    		if($(this).children('.dayval').val()<107 && month == 12){
			$.fn.myFunction(this);
    		}else{
    		alert("예약할수 없는 날짜 입니다.");
    		}
    	}else{
			$.fn.myFunction(this);
    	}
    });
    
    $('[value='+ -1 +']').parent().children("div").css({"color":"rgba(0,0,0,0.2)"});
    
    $.fn.myFunction = function(curval) {
  		if(f_day == 0){
  			if($(curval).children('.avail').val() == 0){
		       	f_day = $(curval).children().val()*100 + Number($(curval).children("div").text());
	       		$(".firstday").text($(curval).children().val() + "." + $(curval).children("div").text());
	       		$(".preday").val($(curval).children().val() + "/" + $(curval).children("div").text());
	       		$.fn.reset();
	       		$(curval).children("div").css({"background-color":"#4fa9de","color":"white","border-radius":"20px 0 0 20px"});
	       		var month = $(curval).children().val();
	       		var month1 = $(curval).children().val();
	       		if(month<10) {month= "0" + month;}
	  			var startvalue = $(curval).children(".year").val() + month + $(curval).children("div").text();
	       		$.fn.checkavailable(startvalue,month1);
  			}else{
  	    		alert("예약할수 없는 날짜 입니다.");
  	    	}
   	    }else if(f_day != 0 && s_day !=0){
   	    	if($(curval).children('.avail').val() == 0){
		       	f_day = $(curval).children().val()*100 + Number($(curval).children("div").text());
	   	    	$(".firstday").text($(curval).children().val() + "." + $(curval).children("div").text());
		     	$(".secondday").text("");
		     	$(".preday").val($(curval).children().val() + "/" + $(curval).children("div").text());
		     	$(".presday").val("");
		     	$.fn.reset();
		     	$(curval).children("div").css({"background-color":"#4fa9de","color":"white","border-radius":"20px 0 0 20px"});
		     	s_day = 0;
		     	var month = $(curval).children().val();
	       		var month1 = $(curval).children().val();
	       		if(month<10) {month= "0" + month;}
	  			var startvalue = $(curval).children(".year").val() + month + $(curval).children("div").text();
	       		$.fn.checkavailable(startvalue,month1);
   	    	}else{
   	    		alert("예약할수 없는 날짜 입니다.");
   	    	}
   		}else if(f_day != 0){
   	    	s_day = $(curval).children().val()*100 + Number($(curval).children("div").text());
   	    	if(s_day <= f_day){
	   	    	if($(curval).children('.avail').val() == 0){
		   	    	$(".firstday").text($(curval).children().val() + "." + $(curval).children("div").text());
		   	    	$(".secondday").text("");
				   	$(".preday").val($(curval).children().val() + "/" + $(curval).children("div").text());
				   	$(curval).children("div").css({"background-color":"#4fa9de","color":"white","border-radius":"20px 0 0 20px"});
				   	$.fn.reset();
		   	    	f_day = s_day;
		   	    	s_day = 0;
			   	   	var month = $(curval).children().val();
			   	   	var month1 = $(curval).children().val();
			    	if(month<10) {month= "0" + month;}
			  			var startvalue = $(curval).children(".year").val() + month + $(curval).children("div").text();
			  			$.fn.checkavailable(startvalue,month1);
	   	    		}else{
	   	     			alert("예약할수 없는 날짜 입니다.");
	   	     		}
   	    	}else if (s_day > f_day){
   	     		$(".secondday").text($(curval).children().val() + "." + $(curval).children("div").text());
		    	$(".presday").val($(curval).children().val() + "/" + $(curval).children("div").text());
		    	$(".middleday").text("~");
   	     		var range = f_day + "/" + s_day;
   	     	 	var end3 = Number($(curval).children().val())*100 + Number($(curval).children("div").text());
   	     		$.fn.reset2(end3);
   	     		$(curval).children("div").css({"background-color":"#4fa9de","color":"white","border-radius":"0 20px 20px 0"});
   	     		$('[value='+ f_day +']').parent().children("div").css({"background-color":"#4fa9de","color":"white","border-radius":"20px 0 0 20px"});
   	     	 	$.fn.fillup(range);
   	    	}
   		}
	}
   
    var deact_start = ${calvalue[0].month*100 + calvalue[0].day};
    var today = ${today};
    
    for(var i=deact_start; i<=today; i++) {
    	if(${year} <= ${toyear}) {
    		$('[value='+ i +']').parent().children("div").css({"color":"rgba(0,0,0,0.2)"});
    		}
    	}
    
    $(".prebutton").click(function() {
    	if(${month*100} < ${today} && ${year} <= ${toyear}){
    		alert("예약할수 없는 날짜입니다.");
    	}else{
    		prepage.submit();
    	}
    });
    var start = 0;
	var end = 0;
	var start2 = 0;
	var end2 = 0;
    $.fn.availvalue = function(result) {
    	var value = result.split("/");
    	start = Number(value[0]);
    	end = Number(value[1]);
    	start2 = Number(value[2]);
    	end2 = Number(value[3]);
    	$('.targetroom').val(value[4]);
    	if(start>=1226){
    		for(var i=start; i<=1231; i++) {
	        	$('[value='+ i +']').parent().children("div").css({"background-color":"#87ff87","color":"white"});
    		}
    		for(var i=101; i<=end; i++) {
	        	$('[value='+ i +']').parent().children("div").css({"background-color":"#87ff87","color":"white"});
    		}
   		}else{
    		for(var i=start; i<=end; i++) {
	        	$('[value='+ i +']').parent().children("div").css({"background-color":"#87ff87","color":"white"});
    		}
    	}
        
        for(var j=deact_start; j<start2; j++) {
    	   	$('[value='+ j +']').parent().children("div").css({"color":"rgba(0,0,0,0.2)"});
    		$('[value='+ j +']').parent().children("div").click(function() {
    	   		alert("예약할수 없는 날짜입니다.");
    	   		event.stopPropagation();
    	   	});
    	} 
        for(var h = end2; h<=1231; h++) {
        	if(end2>107){
	    	 	$('[value='+ h +']').parent().children("div").css({"color":"rgba(0,0,0,0.2)"});
	    	  	$('[value='+ h +']').parent().children("div").click(function() {
	    	 		alert("예약할수 없는 날짜입니다.");
	    	 		event.stopPropagation();
	    	  	});
        	}
    	} 
    }
    
    $.fn.reset = function() {
		$(".days,.days_sun,.days_sat").children("div").css({"background-color":"white","color":"black","border-radius":"0"});
		$(".middleday").text("");
		$(".days_sun").children("div").css({"color":"red"});
		$(".days_sat").children("div").css({"color":"blue"});
		for(var i=deact_start; i<=today; i++) {
	    	$('[value='+ i +']').parent().children("div").css({"color":"rgba(0,0,0,0.2)"});
	    	}
		$('[value='+ -1 +']').parent().children("div").css({"color":"rgba(0,0,0,0.2)"});
    }
    $.fn.reset2 = function(end3) {
    	var end3 = Number(end3) +1;
    	for(var j=deact_start; j<start2; j++) {
     	   	$('[value='+ j +']').parent().children("div").css({"background-color":"white","color":"black","border-radius":"0"});
     	    $('[value='+ j +']').parent().children("div").off('click');
    	}
    	if(end3>=1227){
	    	for(var h = end3; h<=1231; h++) {
	     	   	$('[value='+ h +']').parent().children("div").css({"background-color":"white","color":"black","border-radius":"0"});
	     	    $('[value='+ h +']').parent().children("div").off('click');
	    	}
	    	for(var h = 101; h<=106; h++) {
	     	   	$('[value='+ h +']').parent().children("div").css({"background-color":"white","color":"black","border-radius":"0"});
	     	    $('[value='+ h +']').parent().children("div").off('click');
	    	}
    	}else{
	    	for(var h = end3; h<=1231; h++) {
	     	   	$('[value='+ h +']').parent().children("div").css({"background-color":"white","color":"black","border-radius":"0"});
	     	    $('[value='+ h +']').parent().children("div").off('click');
	    	}
    		
    	}
		$('.days_sun').children("div").css({"color":"red"});
		$('.days_sat').children("div").css({"color":"blue"});
		if(${year} <= ${toyear}){
			for(var i=deact_start; i<=today; i++) {
	    		$('[value='+ i +']').parent().children("div").css({"color":"rgba(0,0,0,0.2)"});
	    	}
    	}
		$('[value='+ -1 +']').parent().children("div").css({"color":"rgba(0,0,0,0.2)"});
    }
   
});	
</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- content -->
	<div class="calendar_content">
		<h3 style="margin-bottom: 10px;">예약하기</h3>
		<br>
		<form name="prepage" action = "http://localhost:9000/myjeju/calendar.do" method="post">
			<input type = "hidden" name = "preyear" value="${year}">
			<input type = "hidden" name = "premonth" value="${month-1}">
			<input type = "hidden" class="preday" name = "preday">
			<input type = "hidden" class="presday" name = "presday">
			<input type = "hidden" name = "hid" value="${hid}">
			<input type = "hidden" name = "hdid" value="${hdid}">
			<button class="prebutton" type="button" style="cursor:pointer"><</button>
		</form>
		
		<h3 class="date" style="font-size:25px; margin-bottom: 10px;">${year}.${month}</h3>
		
		<form action ="http://localhost:9000/myjeju/calendar.do" method="post">
			<input type = "hidden" name = "preyear" value="${year}">
			<input type = "hidden" name = "premonth" value="${month+1}">
			<input type = "hidden" class="preday" name = "preday">
			<input type = "hidden" class="presday" name = "presday">
			<input type = "hidden" name = "hid" value="${hid}">
			<input type = "hidden" name = "hdid" value="${hdid}">
			<button type="submit" style="cursor:pointer;">></button>
		</form>
		<section class="calendar">
			<%
			for(int i = 0; i< days.length; i++) {
			%>
				<div class="day"><%= days[i] %></div>	
			<%
			}
			%>
			<br>
			<div class="days_sun">
				<input type="hidden" value = "${calvalue[0].month}">
				<input type="hidden" class="dayval" value = "${calvalue[0].month*100 + calvalue[0].day}">
				<input type="hidden" class="avail" value = "${availlast[0]}">
				<input type="hidden" class="year" value = "${calvalue[0].year}">
				<div>${calvalue[0].day}</div> 
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="1" end="5">
			<div class="days" id="days">
				<input type="hidden" value = "${list.month}"> 
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}"> 
				<div>${list.day }</div> 
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" value = "${calvalue[6].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[6].month*100 + calvalue[6].day}"> 
				<input type="hidden" class="avail" value = "${availlast[6]}">
				<input type="hidden" class="year" value = "${calvalue[6].year}"> 
				<div>${calvalue[6].day}</div>
			</div>
			<br>
			<div class="days_sun">
				<input type="hidden" value = "${calvalue[7].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[7].month*100 + calvalue[7].day}">  
				<input type="hidden" class="avail" value = "${availlast[7]}">
				<input type="hidden" class="year" value = "${calvalue[7].year}">  
				<div>${calvalue[7].day}</div>
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="8" end="12">
			<div class="days" id="days">
				<input type="hidden" value = "${list.month}"> 
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}"> 
				<div>${list.day }</div>
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" value = "${calvalue[13].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[13].month*100 + calvalue[13].day}"> 
				<input type="hidden" class="avail" value = "${availlast[13]}">
				<input type="hidden" class="year" value = "${calvalue[13].year}"> 
				<div>${calvalue[13].day}</div> 
			</div>
			<br>
			<div class="days_sun">
				<input type="hidden" value = "${calvalue[14].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[14].month*100 + calvalue[14].day}"> 
				<input type="hidden" class="avail" value = "${availlast[14]}">
				<input type="hidden" class="year" value = "${calvalue[14].year}"> 
				<div>${calvalue[14].day}</div> 
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="15" end="19">
			<div class="days" id="days">
				<input type="hidden" value = "${list.month}"> 
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}"> 
				<div>${list.day }</div> 
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" value = "${calvalue[20].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[20].month*100 + calvalue[20].day}">
				<input type="hidden" class="avail" value = "${availlast[20]}">
				<input type="hidden" class="year" value = "${calvalue[20].year}">
				<div>${calvalue[20].day}</div>
			</div>
			<br>
			<div class="days_sun">
				<input type="hidden" value = "${calvalue[21].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[21].month*100 + calvalue[21].day}">
				<input type="hidden" class="avail" value = "${availlast[21]}"> 
				<input type="hidden" class="year" value = "${calvalue[21].year}">
				<div>${calvalue[21].day}</div>
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="22" end="26">
			<div class="days" id="days">
				<input type="hidden" value = "${list.month}"> 
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}">  
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}">  
				<div>${list.day }</div> 
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" value = "${calvalue[27].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[27].month*100 + calvalue[27].day}"> 
				<input type="hidden" class="avail" value = "${availlast[27]}"> 
				<input type="hidden" class="year" value = "${calvalue[27].year}"> 
				<div>${calvalue[27].day}</div> 
			</div>
			<br>
			<div class="days_sun">
				<input type="hidden" value = "${calvalue[28].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[28].month*100 + calvalue[28].day}">
				<input type="hidden" class="avail" value = "${availlast[28]}">  
				<input type="hidden" class="year" value = "${calvalue[28].year}">
				<div>${calvalue[28].day}</div> 
			</div>
			<c:forEach var="list" items="${calvalue}" varStatus="status" begin="29" end="33">
			<div class="days" id="days">
				<input type="hidden" value = "${list.month}"> 
				<input type="hidden" class="dayval" value = "${list.month*100 + list.day}"> 
				<input type="hidden" class="avail" value = "${availlast[status.index]}">
				<input type="hidden" class="year" value = "${list.year}"> 
				<div>${list.day }</div> 
			</div>
			</c:forEach>
			<div class="days_sat">
				<input type="hidden" value = "${calvalue[34].month}"> 
				<input type="hidden" class="dayval" value = "${calvalue[34].month*100 + calvalue[34].day}">
				<input type="hidden" class="avail" value = "${availlast[34]}">
				<input type="hidden" class="year" value = "${calvalue[34].year}">
				<div>${calvalue[34].day}</div> 
			</div>
			<br>
			
			<c:if test = "${maxrow == 6}">
				<c:forEach var="list" items="${calvalue}" varStatus="status" begin="41" end="41">
				<div class="days_sun">
					<input type="hidden" value = "${calvalue[35].month}"> 
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
					<input type="hidden" value = "${list.month}"> 
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
					<input type="hidden" value = "${calvalue[41].month}"> 
					<input type="hidden" class="dayval" value = "${calvalue[41].month*100 + calvalue[41].day}"> 
					<input type="hidden" class="avail" value = "${availlast[41]}">
					<input type="hidden" class="year" value = "${calvalue[41].year}"> 
					<div>${calvalue[41].day}</div> 
				</div>
				</c:forEach>
			</c:if>
		</section>
		
		<h3>${currentname}</h3>
		
		<br>
		<div class="date">
			<div class="firstday"></div> <div class="middleday" style="display: inline-block;"></div> <div class="secondday"></div>
			<form class="search_form"action = "http://localhost:9000/myjeju/reservationDetail.do" method="post">
			<input type = "hidden" class="preyear" name = "preyear" value="${year}">
			<input type = "hidden" class="preday" name = "preday">
			<input type = "hidden" class="presday" name = "presday">
			<input type = "hidden" name="currenthome" value= "${currentname}">
			<input type = "hidden" name="hid" value=<%= hid %>>
			<input type = "hidden" name="hdid" value=<%= hdid %>>
			<input type = "hidden" class="targetroom" name="roomid">
			<button class="search_btn" type="submit">예약하기</button>
			</form>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>