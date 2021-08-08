<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    <c:set var="today" value="<%=new java.util.Date()%>" />	
    <c:set var="date"><fmt:formatDate value="${today}" pattern="yyyy-MM-dd" /></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>결제 내역 | JEJU ISLAND</title>
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
		width:100%;
		background-color:rgb(248,249,251); 
		border:1px solid lightgray;
		text-align:right;
	}	
	.store { background-color:rgb(175,182,196); color:white; float:left; font-weight:bold; border:none; padding:10px; }
	.reservation { background-color:rgb(248,249,251);  color:rgb(175,182,196); float:left; font-weight:bold; border:none; padding:10px; }
	.select { background-color:rgb(82,95,120); color:white; padding:2px; font-size:14px; border:none; border-radius:4px; }
	
	.title .form-control { display:inline-block; width:150px; font-size:13px; margin-top:5px; }
	
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
	.reservation_content { display:none; }
	
	.box { display:inline-block; width:900px; border-bottom:1px solid lightgray; padding:20px 0; }
	.box img { border:2px solid lightgray; float:left; }
	.box>p.info { color:gray; float:left; margin:15px; font-size:14px; text-align:left; }
	.box>p.date  { float:left; margin:15px 50px; font-size:14px; text-align:left; }
	.box>p.info>a { color:black; text-decoration:none; }
	.box>p.info>a:hover { text-decoration:underline; }
	.box>p.price { float:right; font-weight:bold; margin-top:25px; }
	
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
	
	.btn { padding:5px; font-size:13px; margin-top:40px; }
</style>
</head>
<script>
	$(document).ready(function() {		
		$(".reservation").click(function() {
			$(".store_content").css("display","none");
			$(".reservation_content").css("display","block");
			$(this).css({"color":"white", "background-color":"rgb(175,182,196)"});
			$(".store").css({"color":"rgb(175,182,196)", "background-color":"rgb(248,249,251)"});
			
		});
		
		$(".store").click(function() {
			$(".store_content").css("display","block");
			$(".reservation_content").css("display","none");
			$(this).css({"color":"white", "background-color":"rgb(175,182,196)"});
			$(".reservation").css({"color":"rgb(175,182,196)", "background-color":"rgb(248,249,251)"});
		});
		
		$(".store_more").click(function() {
			$(".store_more_box").css("display","inline-block");
		});
		
		$(".reservation_more").click(function() {
			$(".reservation_more_box").css("display","inline-block");
		});		
		
		$("button[id=cancel]").click(function() {
			var rid = $(this).attr("name");
			var con_test = confirm("게시글을 삭제하시겠습니까?"); 
        	if(con_test == true){   
        		location.replace("myorder_delete.do?rid="+rid);
        	}
		});
		
	});
</script>
<body>
<jsp:include page="../../header.jsp"></jsp:include>

<section>
	<div class="center">
		<div class="title">
			<button class="store">스토어 구매내역</button>
			<button class="reservation">예약 내역</button>		
		
		</div>
		<div class="content store_content">
			<p>2021.08</p>
			<c:if test="${not empty list }">
				<c:forEach var = "vo"  items="${list}" begin="0" end="3">
					<div class="box">
						<img src="http://localhost:9000/myjeju/images/store/store_detail/${vo.o_file }" width=100 height=80>
						<p class="info">${vo.o_date }<br>${vo.o_name }<br><span>총 ${vo.o_count }개</span></p>
						<p class="price"><fmt:formatNumber value="${vo.o_price }" pattern="#,###" />원</p>
					</div>
				</c:forEach>
				<c:forEach var = "vo"  items="${list}" begin="4" end="${fn:length(list)-1}" >					
					<div class="box more_box store_more_box">
						<img src="http://localhost:9000/myjeju/images/store/store_detail/${vo.o_file }" width=100 height=80>
						<p class="info">${vo.o_date }<br>${vo.o_name }<br><span>총 ${vo.o_count }개</span></p>
						<p class="price"><fmt:formatNumber value="${vo.o_price }" pattern="#,###" />원</p>
					</div>
				</c:forEach>	
			</c:if>
			<div class="more store_more">
				<div class="img"></div><span>더보기</span>
			</div>	
		</div>		
		<div class="content reservation_content">
			<p>2021.08</p>
			<c:if test="${not empty mlist }">
				<c:forEach var = "vo"  items="${mlist}" begin="0" end="3">
				<fmt:parseDate value="${vo.firstday }" var="strPlanDate" pattern="yyyy-MM-dd"/>
				<fmt:parseNumber value="${strPlanDate.time / (1000*60*60*24)}" integerOnly="true" var="strDate"></fmt:parseNumber>
				<fmt:parseDate value="${vo.lastday }" var="endPlanDate" pattern="yyyy-MM-dd"/>
				<fmt:parseNumber value="${endPlanDate.time / (1000*60*60*24)}" integerOnly="true" var="endDate"></fmt:parseNumber>
					<div class="box">
						<img src="http://localhost:9000/myjeju/images/house/${vo.h_file }" width=100 height=80>
						<p class="info">${vo.rdate }<br><a href="house_detail.do?hid=${vo.hid }">${vo.h_name }</a><br><span>${vo.hd_name }</span></p>
						<p class="date"><strong>예약일</strong> <br><strong>입실</strong> ${vo.firstday }<br><strong>퇴실</strong> ${vo.lastday }</p>
						<p class="price"><fmt:formatNumber value="${ (endDate - strDate)*vo.hd_price }" pattern="#,###" />원</p>
						<c:if test = "${ date < vo.firstday }">
							<button class="btn btn-outline-secondary" id="cancel" name="${vo.rid }">예약취소</button>
						</c:if>
					</div>
				</c:forEach>
				<c:forEach var = "vo"  items="${mlist}" begin="4" end="${fn:length(mlist)-1}" >		
				<fmt:parseDate value="${vo.firstday }" var="strPlanDate" pattern="yyyy-MM-dd"/>
				<fmt:parseNumber value="${strPlanDate.time / (1000*60*60*24)}" integerOnly="true" var="strDate"></fmt:parseNumber>
				<fmt:parseDate value="${vo.lastday }" var="endPlanDate" pattern="yyyy-MM-dd"/>
				<fmt:parseNumber value="${endPlanDate.time / (1000*60*60*24)}" integerOnly="true" var="endDate"></fmt:parseNumber>
					<div class="box more_box reservation_more_box">
						<img src="http://localhost:9000/myjeju/images/house/${vo.h_img }" width=100 height=80>
						<p class="info">${vo.rdate }<br><a href="house_detail.do?hid=${vo.hid }">${vo.h_name }</a><br><span>${vo.hd_name }</span></p>
						<p class="date"><strong>예약일</strong> <br><strong>입실</strong> ${vo.firstday }<br><strong>퇴실</strong> ${vo.lastday }</p>
						<p class="price"><fmt:formatNumber value="${ (endDate - strDate)*vo.hd_price }" pattern="#,###" />원</p>
						<c:if test = "${ date < vo.firstday }">
							<button class="btn btn-outline-secondary" id="cancel" name="${vo.rid }">예약취소</button>
						</c:if>
					</div>
				</c:forEach>
				</c:if>	
			<div class="more reservation_more">
				<div class="img"></div><span>더보기</span>
			</div>
		</div>

	</div>
</section>

<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>