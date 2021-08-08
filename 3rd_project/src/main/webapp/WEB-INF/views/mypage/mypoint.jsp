<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>포인트 내역 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" ></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<style>
	section { text-align:center; margin-bottom:50px; }
	.center { 
		margin-top:80px; 
		width:1000px; 
		display:inline-block; 
	}
	
	.title { 
		display:inline-block;
		border:1px solid lightgray;
		background-color:rgb(248,249,251); 
		width:100%; height:100px;
	}
	.title>p { 
		display:inline-block; 
		margin-top:20px;
	}
	
	.content {
		border-top:1px solid lightgray;
		display:inline-block;
		width:100%; 
		margin-top:50px;
		text-align:center;
	}
	.content>p { 
		background-color:white; 
		border:1px solid lightgray;
		border-radius:50px; 
		display:inline-block; 
		padding:10px 20px; 
		margin-top:-20px;
	}
	.box { display:inline-block; width:900px; border-bottom:1px solid lightgray; padding:20px 0; }
	.box>div.plus { border:1px solid gray; border-radius:50px; display:inline-block; float:left; padding:30px; color:gray; }
	.box>div.minus { border:1px solid red; border-radius:50px; display:inline-block; float:left; padding:30px; color:red; }
	.box>p.info { color:gray; float:left; margin:20px; font-size:14px; }
	.box>p.info>span { color:black; }
	.box>p.price_plus { float:right; margin:20px; }
	.box>p.price_minus { color:red; float:right; margin:20px; }
	
	.more { 
		background-color:rgb(248,249,251); 
		border:1px solid lightgray; 
		display:inline-block; 
		width:100%; height:50px; 
		margin-top:10px;
		cursor:pointer;
	}
	.more .img { 
		display:inline-block; width:10px; height:10px; border:1px solid gray; 
		background-image:url("http://localhost:9000/myjeju/images/mypage/plus.png");
		background-size:cover;
		margin-top:20px; 
		
	}
	.more span { font-size:14px; margin-left:5px; }
	.more_box { display:none; }
</style>
</head>
<script>
	$(document).ready(function() {
		$(".more").click(function() {
			$(".more_box").css("display","inline-block");
		});
	});
</script>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<section>
	<div class="center">
		<div class="title">
			<p>총 보유 포인트<br>
			<strong>${point }</strong>p</p>
		</div>
		<div class="content">
			<p>2021.08</p>
			<c:if test = "${not empty list }">
				<c:forEach var = "vo"  items="${list}" begin="0" end="3">
					<div class="box">
					<c:if test = "${vo.type eq 'plus' }">
						<div class="plus">적립</div>
						<p class="info">${vo.pdate }<br><span>상품 구매 적립</span></p>
						<p class="price_plus">+${vo.point }원</p>
					</c:if>
					<c:if test = "${vo.type eq 'minus' }">
						<div class="minus">사용</div>
						<p class="info">${vo.pdate }<br><span>상품 구매 사용</span></p>
						<p class="price_minus">-${vo.point }원</p>
					</c:if>
					</div>
				</c:forEach>
				<c:forEach var = "vo"  items="${list}" begin="3" end="${fn:length(list)-1}">
					<div class="box more_box">
					<c:if test = "${vo.type eq 'plus' }">
						<div class="plus">적립</div>
						<p class="info">${vo.pdate }<br><span>상품 구매 적립</span></p>
						<p class="price_plus">+${vo.point }원</p>
					</c:if>
					<c:if test = "${vo.type eq 'minus' }">
						<div class="minus">사용</div>
						<p class="info">${vo.pdate }<br><span>상품 구매 사용</span></p>
						<p class="price_minus">-${vo.point }원</p>
					</c:if>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="more">
			<div class="img"></div><span>더보기</span>
		</div>
	</div>
</section>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>