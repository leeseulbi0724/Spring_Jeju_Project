<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<title>JEJU ISLAND</title>
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- header -->
	<div class="main_header">
		<div class="tap">
			<nav>
				<a href="http://localhost:9000/myjeju/index.do"><img src="http://localhost:9000/myjeju/images/index/logo3.png"></a>
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
	</div>
	
	<!-- content -->
	<!-- carousel 시작-->
	<div id="demo" class="carousel slide" data-ride="carousel" style="text-align:center;">

	<!-- Indicators -->
	<ul class="carousel-indicators">
	  	<li data-target="#demo" data-slide-to="0" class="active"></li>
	    <li data-target="#demo" data-slide-to="1"></li>
	    <li data-target="#demo" data-slide-to="2"></li>
	    <li data-target="#demo" data-slide-to="3"></li>
	</ul>
	  
	<!-- The slideshow -->
	<div class="carousel-inner">
		<div class="carousel-item active">
	    	<img src="http://localhost:9000/myjeju/images/index/main1.jpg" width="100%">
	    </div>
	    <div class="carousel-item">
	    	<img src="http://localhost:9000/myjeju/images/index/main2.jpg" width="100%">
	    </div>
	    <div class="carousel-item">
	    	<img src="http://localhost:9000/myjeju/images/index/main3.jpeg" width="100%">
	    </div>
	    <div class="carousel-item">
	    	<img src="http://localhost:9000/myjeju/images/index/main4.jpeg" width="100%">
	    </div>
	</div>
	  
	<!-- Left and right controls -->
	<a class="carousel-control-prev" href="#demo" data-slide="prev">
		<span class="carousel-control-prev-icon"></span>
	</a>
	<a class="carousel-control-next" href="#demo" data-slide="next">
	    <span class="carousel-control-next-icon"></span>
	</a>
	</div>
	<!-- carousel 종료-->
	
	<div class="index_content">
	 	<section class="rest_zone">
	 		<div class="welcome">
	 			<span>WELCOME</span><br>
	 			<span>TO</span><br>
	 			<span>JEJU</span>
	 		</div>
	 		<div class="rest_pic">
	 			<div class="figure"></div>
	 			<img src="http://localhost:9000/myjeju/images/index/airbnb2.jpg">
	 		</div>
	 		<div class="rest_infor">
	 			<p>
	 				자연의 섬,<br>
					제주도에서 휴식을 즐기세요!
				</p>	
				<div></div>
				<p>	
					다양한 숙소를 검색하고,<br>
					예약까지 한번에 할 수 있어요.<br>
					지금 예약하세요!
	 			</p>
	 			<button type="button" class="btn_style" id="reserve_btn" onclick="location.href='http://localhost:9000/myjeju/house.do'">예약하기</button>
	 		</div>
	 	</section>
	 	<section class="travel_zone">
	 		<div class="travel_infor">
	 			<p>
	 				제주도의 포토스팟
				</p>	
				<div></div>
				<p>	
					인기 관광지와<br>
					포토스팟, 차박스팟을<br> 
					직접 경험해보세요!<br>
					좋은 추억을 선사합니다.
	 			</p>
	 			<button type="button" class="btn_style2" id="more_btn" onclick="location.href='http://localhost:9000/myjeju/travel.do'" style="width:110px">자세히보기</button>
	 		</div>
	 		<div class="travel_pic">
	 			<img src="http://localhost:9000/myjeju/images/index/photospot3.jpg" class="photospot">
	 		</div>
	 	</section>
	 	<section class="other_zone">
	 		<div class="other_pic">
	 			<a href="http://localhost:9000/myjeju/cafe.do"><img src="http://localhost:9000/myjeju/images/index/food1.jpg"></a>
	 		</div>
	 		<div class="other_pic">
	 			<a href="http://localhost:9000/myjeju/food.do"><img src="http://localhost:9000/myjeju/images/index/food7.jpg"></a>
	 		</div>
	 		<div class="other_pic">
	 			<a href="http://localhost:9000/myjeju/travel.do"><img src="http://localhost:9000/myjeju/images/index/car4.jpg"></a>
	 		</div>
	 		<div class="other_pic">
	 			<a href="http://localhost:9000/myjeju/store.do"><img src="http://localhost:9000/myjeju/images/index/goods1.jpg"></a>
	 		</div>
	 		<div class="other_infor">
	 			<p>	
					제주도의 모든 것,<br> 
					<span>'MY JEJU ISLAND'</span> 에서 확인하세요!
	 			</p>
	 			<div></div>
	 			<p>	
					다양한 맛집, 제주도만의 특별한 굿즈,<br>
					그리고 사람들의 실시간 후기를 지금 바로 확인해보세요!
	 			</p>
	 		</div>
	 	</section>
	 </div>
	 
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>