<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<title>카페 | JEJU ISLAND</title>
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/food/food_detail.css">
	<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			
			$(".btn_style").click(function(){
				var caid = $(this).attr("name");				
				if ("${session_id}"=="") {
					alert("로그인 후 이용바랍니다");
				} else if ($(this).attr("id") == 1) {
					$.ajax({
				                type: "post",
				                url: "heart_cafe_minus.do",
				                data:{caid:caid},
				                dataType: 'json',
				                success: function (result) {
				                    location.reload();
				                },
				           }); 
					} else {	
						$.ajax({
				                type: "post",
				               	url: "heart_cafe_plus.do",
				                data:{caid:caid},
				                dataType: 'json',
				                success: function (result) {
				                    location.reload();
				               	 },
						});
					}
			});
			
			var pnum=$(".pnum").val();
			
			var caid = $("#cafe_review_btn").attr("name");

			moreList(pnum, caid); 

			$("#cafe_review_btn").on("click", function(){
				if($("#ca_review").val() == ""){
					alert("리뷰를 입력해주세요.");
					$("#ca_review").focus();
					return false;
				}else{
					cafe_review_form.submit();
					moreList(pnum, caid);
				}
			
			
			});
			
			
			
			 function moreList(pnum,caid){
				var addListHtml="";
				
				$.ajax({
					type:"post",
					url:"cafe_review_list_proc.do",
					data:{
						pnum:pnum,
						caid:caid
					},
					dataType : "json",
					success : function(result) {
						var data = JSON.stringify(result);
						var jdata = JSON.parse(data);
						
						for(var i in jdata.jlist){
							addListHtml += "<div class='review_date' id='ca_time'>" + jdata.jlist[i].ca_time + "</div>";
							addListHtml += "<dl>";
							addListHtml += "<dt>";
							addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/travel_detail/human.png'>";
							addListHtml += "<div class='user_name'>" + jdata.jlist[i].id + "</div>";
							addListHtml += "</dt>";
							if(jdata.jlist[i].ca_star == 5){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star5.png' class='review_star'>";
								addListHtml += "</dd>";
							}else if(jdata.jlist[i].ca_star == 4){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star4.png' class='review_star'>";
								addListHtml += "</dd>";	
							}else if(jdata.jlist[i].ca_star == 3){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star3.png' class='review_star'>";
								addListHtml += "</dd>";
							}else if(jdata.jlist[i].ca_star == 2){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star2.png' class='review_star'>";
								addListHtml += "</dd>";
							}else if(jdata.jlist[i].ca_star == 1){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star1.png' class='review_star'>";
								addListHtml += "</dd>";
							}
							addListHtml += "<dd>" + jdata.jlist[i].ca_review + "</dd>";
							if(jdata.jlist[i].user_id == jdata.jlist[i].id){
								addListHtml += "<dd style='margin-top:-10px'>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/travel_detail/like_finger.png' class='like_finger'>";
								addListHtml += "<span class='like_score'>0</span>";
								addListHtml += "</dd>";
								addListHtml += "<dd style='margin:-15px 0 -10px 0'>";
								addListHtml += "<button type='button' name='" + jdata.jlist[i].reid + "'" + "class='btn_style3' id='cafe_review_delete' style='margin-right:20px'>삭제</button>";
								addListHtml += "</dd>";
							 }else{
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/travel_detail/like_finger.png' class='like_finger'>";
								addListHtml += "<span class='like_score'>0</span>";
								addListHtml += "</dd>";  
								addListHtml += "<dd>";
								addListHtml += "<button type='button' class='btn_style3' id='travel_review_delete' style='display:none'>삭제</button>";
								addListHtml += "</dd>";
							}
							addListHtml += "</dl>";
						
						}
						$(".pnum").val(jdata.jlist[0].pnum);
		                $("#cafe_review_list").append(addListHtml);
		                
		                $("button[id^=cafe_review_delete]").click(function(){
		    				var delete_confirm = confirm("리뷰를 삭제하시겠습니까?");
		    				
		    				if(delete_confirm){
		    					var reid = $(this).attr("name");
		    					
		    					$.ajax({
		    						type:"post",
		    						url:"cafe_review_delete.do",
		    						data:{reid : reid},
		    						dataType:'json',
		    						success:function(result){
		    							location.reload();
		    						},
		    						
		    					});
		    				}
		    			});
					}
				});
			} 
			
			
	        $("#more_btn").on("click", function(){
	            var pnum=$(".pnum").val();
	            var caid = $("#caid").val();
	            moreList(pnum, caid);
	        });  
	         
		});
	</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="food_detail_top">
		<img src="http://localhost:9000/myjeju/images/cafe/cafe_detail/${img[0]}">
		<div>
			<h1>${vo.ca_name}</h1>
			<h3>${vo.ca_infor}</h3>
			<p>
				${vo.ca_addr}<br>
				${vo.ca_hp}
			</p>
			<div>
				<button type="button" class="btn_style" id="${vo.status }" name="${vo.caid }">
				<c:if test="${vo.status eq 0 }">
					<img src="http://localhost:9000/myjeju/images/travel/empty_heart.png"  class="heart_img"><span class='like_num'>${vo.ca_like}</span>
				</c:if>
				<c:if test = "${vo.status eq 1 }">
					<img src="http://localhost:9000/myjeju/images/house/red_heart.png" class="heart_img"><span class='like_num'>${vo.ca_like}</span>
				</c:if>
				</button>
				<c:if test="${vo.star_avg == 5}">
					<img src="http://localhost:9000/myjeju/images/travel/star5.png" width=98 height=17><span class="star_score">${vo.star_avg} (${vo.review_count})</span>
				</c:if>
				<c:if test="${vo.star_avg > 3 && vo.star_avg < 5}">
					<img src="http://localhost:9000/myjeju/images/travel/star4.png" width=98 height=17><span class="star_score">${vo.star_avg} (${vo.review_count})</span>
				</c:if>
				<c:if test="${vo.star_avg > 2 && vo.star_avg < 4}">
					<img src="http://localhost:9000/myjeju/images/travel/star3.png" width=98 height=17><span class="star_score">${vo.star_avg} (${vo.review_count})</span>
				</c:if>
				<c:if test="${vo.star_avg > 1 && vo.star_avg < 3}">
					<img src="http://localhost:9000/myjeju/images/travel/star2.png" width=98 height=17><span class="star_score">${vo.star_avg} (${vo.review_count})</span>
				</c:if>
				<c:if test="${vo.star_avg > 0 && vo.star_avg < 2}">
					<img src="http://localhost:9000/myjeju/images/travel/star1.png" width=98 height=17><span class="star_score">${vo.star_avg} (${vo.review_count})</span>
				</c:if>
				<c:if test="${vo.star_avg == 0}">
					<img src="http://localhost:9000/myjeju/images/travel/star0.png" width=98 height=17><span class="star_score">${vo.star_avg} (${vo.review_count})</span>
				</c:if>
			</div>
		</div>
	</div>
	<div class="food_detail_content">
		<section class="detail_infor">
			<p class="infor_content" >
				${infor2}
			</p>
		</section>
		
		<section class="detail_image">
			<div class="detail_image2">
	 			<img src="http://localhost:9000/myjeju/images/cafe/cafe_detail/${img[1]}">
	 		</div>
	 		<div class="detail_image2">
	 			<img src="http://localhost:9000/myjeju/images/cafe/cafe_detail/${img[2]}">
	 		</div>
	 		<div class="detail_image2">
	 			<img src="http://localhost:9000/myjeju/images/cafe/cafe_detail/${img[3]}">
	 		</div>
	 		<div class="detail_image2">
	 			<img src="http://localhost:9000/myjeju/images/cafe/cafe_detail/${img[4]}">
	 		</div>
		</section>
		
		<section class="detail_review">
			<h3>리뷰</h3>
			<div class="food_review_zone">
				<form id="cafe_review_form" name="cafe_review_form" action="cafe_review_proc.do?caid=${vo.caid }" method="POST">
					<input type="hidden" name="caid" id="caid" value="${caid}"> 
					<dl>
						<dt>
							<img src="http://localhost:9000/myjeju/images/travel/travel_detail/human.png">
							<div class="user_name">${user_id}</div>
						</dt>
						<dd>
							<select name="ca_star" id="ca_star">
								<option id="star5" value="5">★★★★★</option>
								<option id="star4" value="4">★★★★☆</option>
								<option id="star3" value="3">★★★☆☆</option>
								<option id="star2" value="2">★★☆☆☆</option>
								<option id="star1" value="1">★☆☆☆☆</option>
							</select>
						</dd>
						<c:if test="${user_id != null}">
							<dd><input type="text" name="ca_review" id="ca_review" placeholder="카페가 어떠셨나요? 리뷰를 남겨주세요."></dd>
							<dd>
								<button type="button" name = "${caid}" class="btn_style3" id="cafe_review_btn">등록</button>
							</dd>
						</c:if>
						<c:if test="${user_id == null}">
							<dd><input type="text" name="ca_review" class="ca_review_null"id="ca_review" disabled placeholder="로그인 후 리뷰를 남겨주세요."></dd>
							<dd>
								<button type="button" class="btn_style3" id="cafe_review_btn" disabled>등록</button>
							</dd>
						</c:if>
					</dl>
				</form>
			</div>
			<div id="cafe_review_list" class="food_review_zone2">
				<!-- 리뷰존 ajax -->
			</div>
			
			<button type="button" class="btn_style6" id="more_btn">
				<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn2.png">
				<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn.png">
			</button>
			<input type="hidden" class="pnum" name="pnum">
			
		</section>
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>