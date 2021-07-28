<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="tap">
			<nav>
				<a href="http://localhost:9000/myjeju/index.do"><img src="http://localhost:9000/myjeju/images/index/logo2.png"></a>
				<ul>
				<c:if test= "${empty session_id}">
					<li><a href="http://localhost:9000/myjeju/login.do">LOGIN </a><div></div></li>
					<li><a href="http://localhost:9000/myjeju/join.do">JOIN</a></li>
				</c:if>
				<c:if test= "${!empty session_id && session_id ne 'admin'}">
					<li><a href="#">안녕하세요. ${session_name}님 </a><div></div></li>
					<li><a href="http://localhost:9000/myjeju/mypage.do">MYPAGE</a><div></div></li>
					<li><a href="logout.do">LOGOUT</a></li>
				</c:if>
				<c:if test= "${session_id eq 'admin'}">
					<li><a href="http://localhost:9000/myjeju/adminindex.do">ADMIN </a><div></div></li>
					<li><a href="logout.do">LOGOUT</a></li>
				</c:if>
				</ul>
			</nav>
		</div>
		<div class="menu_tap">
			<ul class="menu_list">
				<li><a href="http://localhost:9000/myjeju/house.do">숙소</a>
					<!-- <ul class="drop_menu">
						<li><a href="http://localhost:9000/myjeju/accomodation.do">호텔</a></li>					
						<li><a href="#">에어비앤비</a></li>					
					</ul> -->
				</li>
				<li><a href="http://localhost:9000/myjeju/food.do">맛집</a>
					<ul class="drop_menu">
						<li><a href="http://localhost:9000/myjeju/food.do">음식점</a></li>					
						<li><a href="http://localhost:9000/myjeju/cafe.do">카페</a></li>					
					</ul>
				</li>
				<li><a href="http://localhost:9000/myjeju/travel.do">여행지</a>
					<!-- <ul class="drop_menu">
						<li><a href="http://localhost:9000/myjeju/travel.do">관광지</a></li>					
						<li><a href="http://localhost:9000/myjeju/photo_spot.do">포토스팟</a></li>					
						<li><a href="http://localhost:9000/myjeju/car_spot.do">차박스팟</a></li>					
					</ul> -->
				</li>
				<li><a href="http://localhost:9000/myjeju/store.do">스토어</a></li>
				<li><a href="http://localhost:9000/myjeju/free_board.do">커뮤니티</a>
					<ul class="drop_menu">
						<li><a href="http://localhost:9000/myjeju/free_board.do">자유게시판</a></li>					
						<li><a href="http://localhost:9000/myjeju/request_board.do">요청게시판</a></li>					
					</ul>
				</li>
			</ul>
		</div>
	</header>
</body>
</html>