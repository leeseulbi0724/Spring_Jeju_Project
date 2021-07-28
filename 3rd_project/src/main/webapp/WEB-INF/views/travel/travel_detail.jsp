<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<title>여행지 | JEJU ISLAND</title>
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/travel/travel_detail.css">
	<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			
			var pnum=$(".pnum").val();
			
			var tid = $("#travel_review_btn").attr("name");

			moreList(pnum, tid); 
			
			$(".infor_content").each(function(){
				var content = $(".infor_content");
				var content_txt = content.text();
				var content_txt_short = content_txt.substring(0,130)+"...";
				
				if(content_txt.length >= 100){
		               content.html(content_txt_short);
				}else{
		        	$("#more_infor_btn").hide();
		        }
				
				 $("#more_infor_btn").click(toggle_content);
				 
				 function toggle_content(){
					 if($(this).hasClass('short')){
		                    // 접기 상태
		                    $(".more_img1").show();
		                    $(".more_img2").hide();
		                    content.html(content_txt_short);
		                    $(this).removeClass('short');
		                }else{
		                    // 더보기 상태
		                    $(".more_img2").show();
		                    $(".more_img1").hide();
		                    content.html(content_txt);
		                    $(this).addClass('short');
		                }
				 }
			});
			
			$(".photo_spot_btn").click(function(){
				$(".detail_photo_spot").show();
				$(".detail_car_spot").hide();
			});
			$(".car_spot_btn").click(function(){
				$(".detail_photo_spot").hide();
				$(".detail_car_spot").show();
			});
			
			$("#travel_review_btn").on("click", function(){
				if($("#t_review").val() == ""){
					alert("리뷰를 입력해주세요.");
					$("#t_review").focus();
					return false;
				}else{
					travel_review_form.submit();
					moreList(pnum, tid);
				}
				
			});
			
			
			 function moreList(pnum,tid){
				var addListHtml="";
				
				$.ajax({
					type:"post",
					url:"travel_review_list_proc.do",
					data:{
						pnum:pnum,
						tid:tid
					},
					dataType : "json",
					success : function(result) {
						var data = JSON.stringify(result);
						var jdata = JSON.parse(data);
						//alert(jdata);
						for(var i in jdata.jlist){
							addListHtml += "<div class='review_date' id='t_time'>" + jdata.jlist[i].t_time + "</div>";
							addListHtml += "<dl>";
							addListHtml += "<dt>";
							addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/travel_detail/human.png'>";
							addListHtml += "<div class='user_name'>" + jdata.jlist[i].id + "</div>";
							addListHtml += "</dt>";
							if(jdata.jlist[i].t_star == 5){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star5.png' class='review_star'>";
								addListHtml += "</dd>";
							}else if(jdata.jlist[i].t_star == 4){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star4.png' class='review_star'>";
								addListHtml += "</dd>";	
							}else if(jdata.jlist[i].t_star == 3){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star3.png' class='review_star'>";
								addListHtml += "</dd>";
							}else if(jdata.jlist[i].t_star == 2){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star2.png' class='review_star'>";
								addListHtml += "</dd>";
							}else if(jdata.jlist[i].t_star == 1){
								addListHtml += "<dd>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star1.png' class='review_star'>";
								addListHtml += "</dd>";
							}
							addListHtml += "<dd>" + jdata.jlist[i].t_review + "</dd>";
							if(jdata.jlist[i].user_id == jdata.jlist[i].id){
								addListHtml += "<dd style='margin-top:-10px'>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/travel_detail/like_finger.png' class='like_finger'>";
								addListHtml += "<span class='like_score'>0</span>";
								addListHtml += "</dd>";
								addListHtml += "<dd style='margin:-15px 0 -10px 0'>";
								addListHtml += "<button type='button' name='" + jdata.jlist[i].reid + "'" + "class='btn_style3' id='travel_review_delete' style='margin-right:20px'>삭제</button>";
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
		                $("#travel_review_list").append(addListHtml);
		                
		                
		                $("button[id^=travel_review_delete]").click(function(){
		    				var delete_confirm = confirm("리뷰를 삭제하시겠습니까?");
		    				
		    				if(delete_confirm){
		    					var reid = $(this).attr("name");
		    					
		    					$.ajax({
		    						type:"post",
		    						url:"travel_review_delete.do",
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
	            var tid = $("#tid").val();
	            moreList(pnum, tid);
	        });  
	         
			
		});
	</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="travel_detail_top">
		<img src="http://localhost:9000/myjeju/images/travel/travel_detail/${img[0]}">
		<div>
			<h1>${vo.t_name}</h1>
			<h2>${vo.t_infor}</h2>
			<p>
				${vo.t_addr}<br>
				${vo.t_hp}
			</p>
		</div>
	</div>
	
	<div class="travel_detail_content">
		<section class="detail_infor">
			<h1>${vo.t_name}</h1>
			<div></div>
			<p class="infor_content" >
				${infor2}
			</p>
			<button type="button" class="btn_style6" id="more_infor_btn">
				<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn2.png" class="more_img1">
				<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn4.png" class="more_img2">
			</button>
		</section>
		
		<section class="detail_image">
			<div class="detail_image2">
	 			<img src="http://localhost:9000/myjeju/images/travel/travel_detail/${img[1]}">
	 		</div>
	 		<div class="detail_image2">
	 			<img src="http://localhost:9000/myjeju/images/travel/travel_detail/${img[2]}">
	 		</div>
	 		<div class="detail_image2">
	 			<img src="http://localhost:9000/myjeju/images/travel/travel_detail/${img[3]}">
	 		</div>
	 		<div class="detail_image2">
	 			<img src="http://localhost:9000/myjeju/images/travel/travel_detail/${img[4]}">
	 		</div>
		</section>
		
		<section class="detail_spot">
			<div class="detail_photo_spot">
				<c:choose>
					<c:when test="${!empty photovo.ps_psfile && !empty carvo.cs_csfile}" > <!-- 포토스팟 ㅇ, 차박스팟 ㅇ -->
						<div class="detail_spot_pic">
				 			<img src="http://localhost:9000/myjeju/images/spot/${photovo.ps_pssfile}">
				 		</div>
						<div class="spot_choice">
							<h3 class="photo_spot_btn" style="border:2px solid #4fa9de; color:#4fa9de;">포토스팟</h3>
							<h3 class="car_spot_btn" style="border:2px solid lightgray; color:lightgray;">차박스팟</h3>
						</div>
						<div class="detail_spot_infor">
				 			<p class="spot_title">	
								${photovo.ps_name}<br> 
				 			</p>
				 			<p class="spot_infor">	
								${photovo.ps_infor}
				 			</p>
				 		</div>
					</c:when>
					<c:when test="${!empty photovo.ps_psfile && empty carvo.cs_csfile}"> <!-- 포토스팟 ㅇ, 차박스팟 x -->
						<div class="detail_spot_pic">
				 			<img src="http://localhost:9000/myjeju/images/spot/${photovo.ps_pssfile}">
				 		</div>
						<div class="spot_choice">
							<h3 class="photo_spot_btn" style="border:2px solid #4fa9de; color:#4fa9de; margin-left:130px;">포토스팟</h3>
							<h3 class="car_spot_btn" style="border:2px solid lightgray; color:lightgray; display:none;">차박스팟</h3>
						</div>
						<div class="detail_spot_infor">
				 			<p class="spot_title">	
								${photovo.ps_name}<br> 
				 			</p>
				 			<p class="spot_infor">	
								${photovo.ps_infor}
				 			</p>
				 		</div>
					</c:when>
		 		</c:choose>
			</div>
			<div class="detail_car_spot" style="display:none;">
				<c:choose>
					<c:when test="${!empty carvo.cs_csfile && !empty photovo.ps_psfile}"> <!-- 차박스팟 ㅇ, 포토스팟 ㅇ,  -->
						<div class="detail_spot_pic">
				 			<img src="http://localhost:9000/myjeju/images/spot/${carvo.cs_cssfile}">
				 		</div>
						<div class="spot_choice">
							<h3 class="photo_spot_btn" style="border:2px solid lightgray; color:lightgray;">포토스팟</h3>
							<h3 class="car_spot_btn" style="border:2px solid #4fa9de; color:#4fa9de;">차박스팟</h3>
						</div>
						<div class="detail_spot_infor">
				 			<p class="spot_title">	
								${carvo.cs_name}<br> 
				 			</p>
				 			<p class="spot_infor">	
				 				${carvo.cs_infor}
				 			</p>
				 		</div>
					</c:when>
					<c:when test="${!empty carvo.cs_csfile && empty photovo.ps_psfile}"> <!-- 차박스팟 ㅇ, 포토스팟 x,  -->
						<div class="detail_spot_pic">
				 			<img src="http://localhost:9000/myjeju/images/spot/${carvo.cs_cssfile}">
				 		</div>
						<div class="spot_choice">
							<h3 class="photo_spot_btn" style="border:2px solid lightgray; color:lightgray; display:none;">포토스팟</h3>
							<h3 class="car_spot_btn" style="border:2px solid #4fa9de; color:#4fa9de;">차박스팟</h3>
						</div>
						<div class="detail_spot_infor">
				 			<p class="spot_title">	
								${carvo.cs_name}<br> 
				 			</p>
				 			<p class="spot_infor">	
				 				${carvo.cs_infor}
				 			</p>
				 		</div>
					</c:when>
		 		</c:choose>
			</div>
		</section>
		
		<section class="detail_review">
			<h3>리뷰</h3> 
			<div class="travel_review_zone">
				<form id="travel_review_form" name="travel_review_form" action="travel_review_proc.do?tid=${vo.tid }" method="POST">
					<%-- <input type="hidden" name="id" id="id" value="${user_id}"> --%>
					<input type="hidden" name="tid" id="tid" value="${tid}"> 
					<dl>
						<dt>
							<img src="http://localhost:9000/myjeju/images/travel/travel_detail/human.png">
							<div class="user_name">${user_id}</div>
						</dt>
						<dd>
							<select name="t_star" id="t_star">
								<option id="star5" value="5">★★★★★</option>
								<option id="star4" value="4">★★★★☆</option>
								<option id="star3" value="3">★★★☆☆</option>
								<option id="star2" value="2">★★☆☆☆</option>
								<option id="star1" value="1">★☆☆☆☆</option>
							</select>
						</dd>
						<c:if test="${user_id != null}">
							<dd><input type="text" name="t_review" id="t_review" placeholder="여행지가 어떠셨나요? 리뷰를 남겨주세요."></dd>
							<dd>
								<button type="button" name = "${tid}" class="btn_style3" id="travel_review_btn">등록</button>
							</dd>
						</c:if>
						<c:if test="${user_id == null}">
							<dd><input type="text" name="t_review" class="t_review_null"id="t_review" disabled placeholder="로그인 후 리뷰를 남겨주세요."></dd>
							<dd>
								<button type="button" class="btn_style3" id="travel_review_btn" disabled>등록</button>
							</dd>
						</c:if>
					</dl>
				</form>
			</div>
			<div id="travel_review_list"class="travel_review_zone2">
				<%-- <c:forEach var="revo" items="${revo}">
					<div class="review_date" id="t_time">${revo.t_time}</div>
					<dl>
							<dt>
								<img src="http://localhost:9000/myjeju/images/travel/travel_detail/human.png">
								<div class="user_name">${revo.id}</div>
							</dt>
							<c:choose>
								<c:when test="${revo.t_star == 5}" >
									<dd>
										<img src="http://localhost:9000/myjeju/images/travel/star5.png" class="review_star">
									</dd>
								</c:when>
								<c:when test="${revo.t_star == 4}" >
									<dd>
										<img src="http://localhost:9000/myjeju/images/travel/star4.png" class="review_star">
									</dd>
								</c:when>
								<c:when test="${revo.t_star == 3}" >
									<dd>
										<img src="http://localhost:9000/myjeju/images/travel/star3.png" class="review_star">
									</dd>
								</c:when>
								<c:when test="${revo.t_star == 2}" >
									<dd>
										<img src="http://localhost:9000/myjeju/images/travel/star2.png" class="review_star">
									</dd>
								</c:when>
								<c:when test="${revo.t_star == 1}" >
									<dd>
										<img src="http://localhost:9000/myjeju/images/travel/star1.png" class="review_star">
									</dd>
								</c:when>
							</c:choose>
							<dd>${revo.t_review}</dd>
							<dd>
								<img src="http://localhost:9000/myjeju/images/travel/travel_detail/like_finger.png" class="like_finger">
								<span class="like_score">0</span>
								<!-- <img src="http://localhost:9000/myjeju/images/travel/travel_detail/btn-alert.png" class="btn_alert"> -->
							</dd> 
							<c:if test="${user_id eq revo.id}">
								<dd style="margin:-15px 0 -10px 0;">
									<button type="button" name="${revo.reid}" class="btn_style3" id="travel_review_update">수정</button>
									<button type="button" name="${revo.reid}" class="btn_style3" id="travel_review_delete" style="margin-right:20px;">삭제</button>
								</dd>
							</c:if>
							<c:if test="${user_id ne revo.id}">
								<dd>
									<button type="button" class="btn_style3" id="travel_review_update" style="display:none;">수정</button>
									<button type="button" class="btn_style3" id="travel_review_delete" style="display:none;">삭제</button>
								</dd>
							</c:if>
					</dl>
				</c:forEach>  --%> 
			</div>
			<button type="button" class="btn_style6" id="more_btn">
				<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn2.png">
				<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn.png">
			</button>
			<input type="hidden" class="pnum" name="pnum">
		</section>
		
		<!-- <section class="detail_recommend">
			<h3>근처 추천 숙소</h3>
			
		</section> -->
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>