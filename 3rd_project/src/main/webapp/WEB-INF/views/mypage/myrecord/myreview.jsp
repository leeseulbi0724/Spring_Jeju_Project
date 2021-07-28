<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>My 후기 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<style>
	section { text-align:center; margin-bottom:50px;  }
	.center { 
		margin-top:100px; 
		width:900px; 
		display:inline-block; 
	}
	h3 { 
		border-bottom:5px solid #4fa9de; 
		font-size:20px; 
		font-weight:bold; 
		display:inline-block;
	}	
	
	#delete { 
		float:right;
		text-decoration:none; 
		color:gray; 
		border:1px solid lightgray; 
		border-radius:5px; 
		padding:3px 10px;
		font-size:14px;
		margin-bottom:10px;
		
	}
	
	.table { font-size:14px; width:100%; }
	.table tr:first-child th { background-color:rgb(247,248,249); border-top:1px solid lightgray; }
	
	.inner-star::before{color: #FF9600;}
	.outer-star {position: relative;display: inline-block;color: #CCCCCC;}
	.inner-star {position: absolute;left: 0;top: 0;width: 0%;overflow: hidden;white-space: nowrap;}
	.outer-star::before, .inner-star::before {content: '\f005 \f005 \f005 \f005 \f005';font-family: 'Font Awesome 5 free';font-weight: 900;}
	
	#house, #food, #cafe, #travel, #store {
		float:left; 
		margin-right:10px; 
		text-decoration:none; 
		margin-bottom:10px;
		color : gray;
		padding-right : 10px;
	}
	
	#house, #food, #cafe, #travel {
		border-right : 1px solid lightgray;
	}
	
	.table tr td a {
		color : gray;
		text-decoration : none;
	}
	
	.table tr td a:hover {
		color : blue;
	}
</style>
<script>
	
	$(document).ready(function() {
		
		$("#delete").click(function() {
			
			if($("input[type^=checkbox]").is(":checked") == false) {
				alert("삭제할 리뷰를 선택해주세요");
			} else {
				var result = confirm("해당 리뷰를 삭제하시겠습니까?");
				
				if(result) {
					var reid = $("input[type^=checkbox]:checked").attr("name");
					var change_id = $("input[type^=checkbox]:checked").attr("id");
					
					$.ajax({
						type : "post",
						url : "myreview_delete.do",
						data : {
							reid : reid,
							change_id : change_id
							},
						dataType : "json",
						success : function(result) {
							location.reload();
						}
					});
					
					
				}

			}
		});
		
		
		
		$(".house_list_table").show();
		$(".food_list_table").css("display", "none");
		$(".cafe_list_table").css("display", "none");
		$(".travel_list_table").css("display", "none");
		$(".store_list_table").css("display", "none");
		
		$("#house").click(function() {
			$(".house_list_table").show();
			$(".food_list_table").css("display", "none");
			$(".cafe_list_table").css("display", "none");
			$(".travel_list_table").css("display", "none");
			$(".store_list_table").css("display", "none");
		});
		
		$("#food").click(function() {
			$(".house_list_table").css("display", "none");
			$(".food_list_table").show();
			$(".cafe_list_table").css("display", "none");
			$(".travel_list_table").css("display", "none");
			$(".store_list_table").css("display", "none");
		});
		
		$("#cafe").click(function() {
			$(".house_list_table").css("display", "none");
			$(".food_list_table").css("display", "none");
			$(".cafe_list_table").show();
			$(".travel_list_table").css("display", "none");
			$(".store_list_table").css("display", "none");
		});
		
		$("#travel").click(function() {
			$(".house_list_table").css("display", "none");
			$(".food_list_table").css("display", "none");
			$(".cafe_list_table").css("display", "none");
			$(".travel_list_table").show();
			$(".store_list_table").css("display", "none");
		});
		
		$("#store").click(function() {
			$(".house_list_table").css("display", "none");
			$(".food_list_table").css("display", "none");
			$(".cafe_list_table").css("display", "none");
			$(".travel_list_table").css("display", "none");
			$(".store_list_table").show();
		});
		
		
		
		

	});

</script>
</head>
<body>
<jsp:include page="../../header.jsp"></jsp:include>

<section>
	<div class="center">
		<div><h3>My 후기</h3></div>		
		<a href = "#" id = "house">숙소</a>
		<a href = "#" id = "food">맛집</a>
		<a href = "#" id = "cafe">카페</a>
		<a href = "#" id = "travel">여행지</a>
		<a href = "#" id = "store">상품</a>
		<button type = "button" id="delete">삭제</button>
		<div class = "table_div">
			<table class="table store_list_table">
				<tr>
					<th></th>
					<th>구분</th>
					<th>별점</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
				
				<c:forEach var = "list" items = "${store_list}" varStatus = "status">
					<tr>
						<td><input type="checkbox" name = "${list.srid}" id = "${list.sid}"></td>
						<td>상품 </td>
						<td>
							<div class='RatingStar'>
							  <div class='RatingScore'>
							    <div class='outer-star'><div class='inner-star' style = "width : ${(list.sr_star)*20}%"></div></div>
							  </div>
							</div>
						</td>
						<td><a href = "store_content.do?sid=${list.sid}">${list.sr_review}</a></td>
						<td>${list.sr_time }</td>
					</tr>
				</c:forEach>

				<tr>
					<c:if test = "${empty store_list}">
						<td colspan = "5">작성한 상품 리뷰가 없습니다.</td>
					</c:if>
				</tr>
			</table>	
			
			<table class="table travel_list_table">	
				<tr>
					<th></th>
					<th>구분</th>
					<th>별점</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
				
				<c:forEach var = "list" items = "${travel_list}" varStatus = "status">	
					<tr>
							<td><input type="checkbox" name = "${list.reid}" id = "${list.tid}"></td>
							<td>여행지</td>
							<td>
								<div class='RatingStar'>
								  <div class='RatingScore'>
								    <div class='outer-star'><div class='inner-star' style = "width : ${list.t_star*20}%"></div></div>
								  </div>
								</div>
							</td>
							<td><a href = "travel_detail.do?tid=${list.tid}">${list.t_review}</a></td>
							<td>${list.t_time}</td>
					</tr>
				</c:forEach>
				
				<tr>
					<c:if test = "${empty travel_list}">
						<td colspan = "5">작성한 여행지 리뷰가 없습니다.</td>
					</c:if>
				</tr>

			</table>	
			
			<table class="table food_list_table">	
				<tr>
					<th></th>
					<th>구분</th>
					<th>별점</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
				
				<c:forEach var = "list" items = "${food_list}" varStatus = "status">	
					<tr>
						<td><input type="checkbox" name = "${list.reid}" id = "${list.fid}"></td>
						<td>맛집</td>
						<td>
							<div class='RatingStar'>
							  <div class='RatingScore'>
							    <div class='outer-star'><div class='inner-star' style = "width : ${list.f_star*20}%"></div></div>
							  </div>
							</div>
						</td>
						<td><a href = "food_detail.do?fid=${list.fid}">${list.f_review}</a></td>
						<td>${list.f_time}</td>
					</tr>
				</c:forEach>
				
				<tr>
					<c:if test = "${empty food_list}">
						<td colspan = "5">작성한 맛집 리뷰가 없습니다.</td>
					</c:if>
				</tr>

			</table>	
			
			<table class="table cafe_list_table">
				<tr>
					<th></th>
					<th>구분</th>
					<th>별점</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
					
				<c:forEach var = "list" items = "${cafe_list}" varStatus = "status">	
					<tr>
						<td><input type="checkbox" name = "${list.reid}" id = "${list.caid}"></td>
						<td>카페</td>
						<td>
							<div class='RatingStar'>
							  <div class='RatingScore'>
							    <div class='outer-star'><div class='inner-star' style = "width : ${list.ca_star*20}%"></div></div>
							  </div>
							</div>
						</td>
						<td><a href = "cafe_detail.do?caid=${list.caid}">${list.ca_review}</a></td>
						<td>${list.ca_time}</td>
					</tr>
				</c:forEach>
					
				<tr>
					<c:if test = "${empty cafe_list}">
						<td colspan = "5">작성한 카페 리뷰가 없습니다.</td>
					</c:if>
				</tr>
			</table>	
			
			<table class="table house_list_table">
				<tr>
					<th></th>
					<th>구분</th>
					<th>별점</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
					
				<c:forEach var = "list" items = "${house_list}" varStatus = "status">	
					<tr>
						<td><input type="checkbox" name = "${list.reid}" id = "${list.hid}"></td>
						<td>숙소</td>
						<td>
							<div class='RatingStar'>
							  <div class='RatingScore'>
							    <div class='outer-star'><div class='inner-star' style = "width : ${list.h_star*20}%"></div></div>
							  </div>
							</div>
						</td>
						<td><a href = "house_detail.do?hid=${list.hid}">${list.h_review}</a></td>
						<td>${list.h_time}</td>
					</tr>
				</c:forEach>
				
				<tr>
					<c:if test = "${empty house_list}">
						<td colspan = "5">작성한 숙소 리뷰가 없습니다.</td>
					</c:if>
				</tr>

			</table>
		</div>	
	</div>
</section>

<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>