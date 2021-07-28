<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>스토어 | JEJU ISLAND</title>
<link rel = "stylesheet" href = "http://localhost:9000/myjeju/css/index.css">
<link rel = "stylesheet" href = "http://localhost:9000/myjeju/css/store/store.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
</head>
<style>
	.store_nav>ul>li>a {
		border : 1px solid lightgray;
	}
	
	.store_nav a.on {
		border-bottom : 2px solid #4fa9de;
	}
	
	.star-rating { 
		width:100px; 
		float:left; 
		margin-top:5px; 
		margin-left:30px; 
	}

	.star-rating, .star-rating span { 
		display : inline-block; 
		height : 18px; 
		overflow : hidden; 
		background : url("http://localhost:9000/myjeju/images/store/star99.png")no-repeat; 
	}
	
	.star-rating span { 
		background-position : left bottom; 
		line-height : 0; 
		vertical-align : top; 
		float:left; 
	}
	
</style>
<script>
	$(document).ready(function() {
		$('.store_nav a').click(function() {
			$('.store_nav li').removeClass()
				$(this).addClass('on');
		});
		
		var sid = ${best.sid}.val();
		$(".click").click(function() {
			alert(sid);
		});
	});
</script>
<body>

	<!-- Header -->
	<jsp:include page = "../header.jsp"></jsp:include>
	
<section>	
	<div class = "store">
		<div><h3>스토어</h3></div>
		
		<div class = "store_nav">
			<ul>
			  <li><a href="store.do">전체</a></li>
			  <li><a href="store_eat.do">식품</a></li>
			  <li><a href="store_souve.do">기념품</a></li>
			  <li><a href="store_etc.do">잡화</a></li>
			</ul>
		</div>
		
		<div class = store_best>
			<div>
				<div><span>BEST MENU</span></div>
			</div>
				
			<c:forEach var = "best" items = "${bestlist}">
				<ul>
					<li>
						<a href = "store_content.do?sid=${best.sid}" class = "click">
							<img src = "http://localhost:9000/myjeju/images/store/store_detail/${best.s_sfile}" width = "260">
							<p>[${best.s_category}] ${best.s_name}</p>

							<span class='star-rating'>
								<span style ="width : ${best.star_avg*20}%"></span>   
							</span>   
							<span>${best.star_avg} (${best.star_count})</span>
							
						</a>
					</li>
				</ul>
			</c:forEach>
		</div>
		
		<div class = "store_eat">
			<div>
				<div><span>식품</span></div>
				<div><a href = "store_eat.do">더 보기 +</a></div>
			</div>
			<c:forEach var = "eat" items = "${eatlist}">
				<ul>
					<li>
						<a href = "store_content.do?sid=${eat.sid}" class = "click">
							<img src = "http://localhost:9000/myjeju/images/store/store_detail/${eat.s_sfile}" width = "260">
							<p>[${eat.s_category}] ${eat.s_name}</p>
							
							<span class='star-rating'>
								<span style ="width : ${eat.star_avg*20}%"></span>   
							</span>   
							<span>${eat.star_avg} (${eat.star_count})</span>
						</a>
					</li>
				</ul>
			</c:forEach>
		</div>
		
		<div class = "store_souve">
			<div>
				<div><span>기념품</span></div>
				<div><a href = "store_souve.do">더 보기 +</a></div>
			</div>
			<c:forEach var = "souve" items = "${souvelist}">
				<ul>
					<li>
						<a href = "store_content.do?sid=${souve.sid}" class = "click">
							<img src = "http://localhost:9000/myjeju/images/store/store_detail/${souve.s_sfile}" width = "260">
							<p>[${souve.s_category}] ${souve.s_name}</p>
							
							<span class='star-rating'>
								<span style ="width : ${souve.star_avg*20}%"></span>   
							</span>   
							<span>${souve.star_avg} (${souve.star_count})</span>
						</a>
					</li>
				</ul>
			</c:forEach>
		</div>
		
		<div class = "store_etc">
			<div>
				<div><span>잡화</span></div>
				<div><a href = "store_etc.do">더 보기 +</a></div>
			</div>
			<c:forEach var = "etc" items = "${etclist}">
				<ul>
					<li>
						<a href = "store_content.do?sid=${etc.sid}" class = "click">
							<img src = "http://localhost:9000/myjeju/images/store/store_detail/${etc.s_sfile}" width = "260">
							<p>[${etc.s_category}] ${etc.s_name}</p>
							
							<span class='star-rating'>
								<span style ="width : ${etc.star_avg*20}%"></span>   
							</span>   
							<span>${etc.star_avg} (${etc.star_count})</span>
							
						</a>
					</li>
				</ul>
			</c:forEach>
		</div>
	
	</div>
</section>	
	
	
	
	<!-- Footer -->
	<jsp:include page = "../footer.jsp"></jsp:include>
	
</body>
</html>