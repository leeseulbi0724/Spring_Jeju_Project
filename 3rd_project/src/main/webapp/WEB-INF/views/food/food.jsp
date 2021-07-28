<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myjeju.vo.*,com.myjeju.dao.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head profile="http://www.w3.org/2005/10/profile">
<meta charset="UTF-8">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<title>음식점 | JEJU ISLAND</title>
	<style>
		.overlay{width: 370px; height:132px;}
		.content{ border-radius:10px; width: 370px; height:120px; margin-bottom:30px;}
		.addr{font-size:12px; font-weight:bold; margin-top:10px;  display:inline-block; margin-left:5px; }
		.tel{font-size:12px; font-weight:bold; color:#4fa9de; display:inline-block; margin-left:5px;}
		.title{background:#4fa9de; color:white; font-weight:bold; padding: 5px 10px;}
		.body a{text-decoration:none; margin-left:5px; margin-top:5px; font-size:13px; font-weight:bold;}
		.img{float:left; display:inline-block; margin-left:5px; border:3px solid #eee; margin-top:8px;}
		.img>img {width:75px; height:75px; object-fit:cover; vertical-align:top; }
	</style>
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/travel/travel.css">
	<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){ 
			
			var pnum=$(".pnum").val();
	
			moreList(pnum); 
			
			function moreList(pnum, search, search_text){
				var addListHtml="";
				
				$.ajax({
					type:"GET",
					url:"food_proc.do",
					data:{
						pnum:pnum,
						search:search,
						search_text:search_text
					},
					success:function(result){
						
						var jdata = JSON.parse(result);
						
						
						for(var i in jdata.jlist){
							addListHtml += "<tr class='travel_list1'>";
							addListHtml += "<td class='travel_list_pic'>";
							addListHtml += "<img src='http://localhost:9000/myjeju/images/food/food_detail/" + jdata.jlist[i].f_image + "'>";
							addListHtml += "</td>";
							addListHtml += "<td>";
							addListHtml += "<p class='spot_name'>" + jdata.jlist[i].f_name;
							addListHtml += "<span>" + jdata.jlist[i].f_infor + "</span>";
							addListHtml += "</p>";
							addListHtml += "<p class='spot_addr'>" + jdata.jlist[i].f_addr + "</p>";
							if(jdata.jlist[i].star_avg == 5){
								addListHtml += "<div>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star5.png' width=98 height=17>";
								addListHtml += "<span class='star_score'>" + jdata.jlist[i].star_avg + " (" + jdata.jlist[i].review_count + ")" + "</span>";
								addListHtml += "</div>";
							}else if(jdata.jlist[i].star_avg > 3 && jdata.jlist[i].star_avg < 5){
								addListHtml += "<div>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star4.png' width=98 height=17>";
								addListHtml += "<span class='star_score'>" + jdata.jlist[i].star_avg + " (" + jdata.jlist[i].review_count + ")" + "</span>";
								addListHtml += "</div>";
							}else if(jdata.jlist[i].star_avg > 2 && jdata.jlist[i].star_avg < 4){
								addListHtml += "<div>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star3.png' width=98 height=17>";
								addListHtml += "<span class='star_score'>" + jdata.jlist[i].star_avg + " (" + jdata.jlist[i].review_count + ")" + "</span>";
								addListHtml += "</div>";
							}else if(jdata.jlist[i].star_avg > 1 && jdata.jlist[i].star_avg < 3){
								addListHtml += "<div>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star2.png' width=98 height=17>";
								addListHtml += "<span class='star_score'>" + jdata.jlist[i].star_avg + " (" + jdata.jlist[i].review_count + ")" + "</span>";
								addListHtml += "</div>";
							}else if(jdata.jlist[i].star_avg > 0 && jdata.jlist[i].star_avg < 2){
								addListHtml += "<div>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star1.png' width=98 height=17>";
								addListHtml += "<span class='star_score'>" + jdata.jlist[i].star_avg + " (" + jdata.jlist[i].review_count + ")" + "</span>";
								addListHtml += "</div>";
							}else if(jdata.jlist[i].star_avg == 0){
								addListHtml += "<div>";
								addListHtml += "<img src='http://localhost:9000/myjeju/images/travel/star0.png' width=98 height=17>";
								addListHtml += "<span class='star_score'>" + jdata.jlist[i].star_avg + " (" + jdata.jlist[i].review_count + ")" + "</span>";
								addListHtml += "</div>";
							}
							addListHtml += "</td>";
							addListHtml += "<td>";
							addListHtml += "<button type='button' class='btn_style' id='"+ jdata.jlist[i].status + "'" + " name= '"+ jdata.jlist[i].fid +"'>";
							if (jdata.jlist[i].status == 0) {
								addListHtml += "<img src='http://localhost:9000/myjeju/images/house/empty_heart.png' class='heart_img'><span class='like_num'>" + jdata.jlist[i].f_like + "</span>";
							} else {
								addListHtml += "<img src='http://localhost:9000/myjeju/images/house/red_heart.png' class='heart_img'><span class='like_num'>" + jdata.jlist[i].f_like + "</span>";
							}
							addListHtml += "</button>";
							addListHtml += "<a href='http://localhost:9000/myjeju/food_detail.do?fid=" + jdata.jlist[i].fid + "'>";
							addListHtml += "<button type='button' class='btn_style4' id='more_infor'>상세정보</button>";
							addListHtml += "</a>";
							addListHtml += "</td>";
							addListHtml += "</tr>";
						}
						$(".pnum").val(jdata.jlist[0].pnum);
		                $("#list_body").append(addListHtml);
			   			$(".btn_style").click(function(){
			 				var fid = $(this).attr("name");			
			 				if ("${session_id}"=="") {
			 					alert("로그인 후 이용바랍니다");
			 				} else if ($(this).attr("id") == 1) {
			 					$.ajax({
			 				                type: "post",
			 				                url: "heart_food_minus.do",
			 				                data:{fid:fid},
			 				                dataType: 'json',
			 				                success: function (result) {
			 				                location.reload();
			 				                },
			 				           }); 
			 					} else {
			 						$.ajax({
			 				                type: "post",
			 				               	url: "heart_food_plus.do",
			 				                data:{fid:fid},
			 				                dataType: 'json',
			 				                success: function (result) {
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
	            moreList(pnum);
	        }); 
	        
	        /** 검색 **/
			$("#travel_search_btn").click(function(){
				if($("#search").val() == "f_name"){
					if($("#search_text").val() == ""){
						alert("검색할 가게명을 입력해주세요.");
						$(this).focus();
						return false;
					}else{
						var search = $("#search").val();
						var search_text = $("#search_text").val();
						$("#list_body").empty();
						$("#more_btn").remove();
						moreList(pnum, search, search_text);
					} 
				}else if($("#search").val() == "f_addr"){
					if($("#search_text").val() == ""){
						alert("검색할 지역명을 입력해주세요.");
						$(this).focus();
						return false;
					}else{
						var search = $("#search").val();
						var search_text = $("#search_text").val();
						$("#list_body").empty();
						$("#more_btn").remove();
						moreList(pnum, search, search_text);
					}
				}
				
			});
			
			/** 옵션 변경시 호출 **/
			$("#search").change(function(){
				$("#search_text").val("");
			}); 
			
		});
	
	</script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- content -->
	<div class="travel_content">
		<h3>음식점</h3>
		<section class="recommend_zone">
			<div class="travel_title">추천 음식점</div>
			<div class="travel_spot">
			<c:forEach var="toplist" items="${toplist}">
				<article class="travel_spot1">
					<a href="http://localhost:9000/myjeju/food_detail.do?fid=${toplist.fid}"><img src="http://localhost:9000/myjeju/images/food/food_detail/${toplist.f_sfile}"></a>
					<div class="spot_infor">
						<p class="spot_name">${toplist.f_name} <span>${toplist.f_infor}</span></p>
						<p class="spot_tag">${toplist.f_tag}</p>
						<button type="button" class="btn_style" id="${toplist.status }" name="${toplist.fid }">
						<c:if test = "${toplist.status eq 0 }">
							<img src="http://localhost:9000/myjeju/images/house/empty_heart.png" width=25 height=25 class="heart_img" ><span class="like_num">${toplist.f_like }</span>
						</c:if>
						<c:if test = "${toplist.status >= 1 }">
							<img src="http://localhost:9000/myjeju/images/house/red_heart.png"  width=25 height=25 class="heart_img" ><span class="like_num">${toplist.f_like }</span>
						</c:if>
						</button>
						
						<c:if test="${toplist.star_avg == 5}">
							<img src="http://localhost:9000/myjeju/images/travel/star5.png" width=98 height=17><span class="star_score">${toplist.star_avg} (${toplist.review_count})</span>
						</c:if>
						<c:if test="${toplist.star_avg > 3 && toplist.star_avg < 5}">
							<img src="http://localhost:9000/myjeju/images/travel/star4.png" width=98 height=17><span class="star_score">${toplist.star_avg} (${toplist.review_count})</span>
						</c:if>
						<c:if test="${toplist.star_avg > 2 && toplist.star_avg < 4}">
							<img src="http://localhost:9000/myjeju/images/travel/star3.png" width=98 height=17><span class="star_score">${toplist.star_avg} (${toplist.review_count})</span>
						</c:if>
						<c:if test="${toplist.star_avg > 1 && toplist.star_avg < 3}">
							<img src="http://localhost:9000/myjeju/images/travel/star2.png" width=98 height=17><span class="star_score">${toplist.star_avg} (${toplist.review_count})</span>
						</c:if>
						<c:if test="${toplist.star_avg > 0 && toplist.star_avg < 2}">
							<img src="http://localhost:9000/myjeju/images/travel/star1.png" width=98 height=17><span class="star_score">${toplist.star_avg} (${toplist.review_count})</span>
						</c:if>
						<c:if test="${toplist.star_avg == 0}">
							<img src="http://localhost:9000/myjeju/images/travel/star0.png" width=98 height=17><span class="star_score">${toplist.star_avg} (${toplist.review_count})</span>
						</c:if>
					</div>
				</article>
			</c:forEach>
			</div>
		</section>
		<section class="map_zone">
			<div class="travel_title" style="margin-bottom:20px;">음식점 찾기</div>
			<div id="map" style="width:100%;height:500px;"></div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2d54e46df64658650b7436b0cf338c67&libraries=services"></script>
			<script>
			window.onload = function() {
				
				var addval;
				
				function makeposition(addval) {
					var positionadd = 
			  				{
			 					title : addval.f_name,
			 					addr : addval.f_addr,
								tel : addval.f_hp,
								img : addval.f_image,
								idx : addval.fid,
								latlng:	new kakao.maps.LatLng(addval.f_vpoint,addval.f_hpoint)
							}
					return positionadd;
				}
				
				function addposition(pnum){
					$.ajax({
						type:"GET",
						url:"food_proc_add.do",
						async:false,
						data:{
							pnum:pnum,
						},
						success:function(result){
							addval = JSON.parse(result);
						}
					});
				}
				
				$("#more_btn").on("click", function(){
					 var pnum=Number($(".pnum").val());
					 addposition(pnum);
					 for(var i=0;i<addval.jlist.length;i++){
					 displayMarker(makeposition(addval.jlist[i]));
					 }
				});
				
				
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
			    	mapOption = { 
				        center: new kakao.maps.LatLng(33.407461303086784, 126.61957095129178), // 지도의 중심좌표
				        level: 10 // 지도의 확대 레벨
			    };
			
				var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			 	
				// 마커를 표시할 위치와 title 객체 배열입니다 
				var positions = [
					<c:forEach var="vo" items="${list}">
					{
						title : '${vo.f_name}',
						addr : '${vo.f_addr}',
						tel : '${vo.f_hp}',
						img : '${vo.f_sfile}',
						idx : '${vo.fid}',
						latlng:	new kakao.maps.LatLng(${vo.f_vpoint}, ${vo.f_hpoint})
					},
					</c:forEach>
				]
				
				for(let i=0; i < positions.length; i++){
				    var data = positions[i];
				    displayMarker(data);
				}
				
				// 지도에 마커를 표시하는 함수입니다    
				function displayMarker(data) { 
				    var marker = new kakao.maps.Marker({
				        map: map,
				        position: data.latlng
				    });
				    var overlay = new kakao.maps.CustomOverlay({
				        yAnchor: 1,
				        position: marker.getPosition()
				    });
				    
			    	var content = document.createElement('div');
			  	    content.innerHTML = "<div class='overlay'>" 
				    	+ "<div class='content'>"
			    		+ "<div class='title'>" + data.title + "</div>"
			    			+ "<div class='body'>"
			    				+ "<div class='img'>" + "<img src=" + "http://localhost:9000/myjeju/images/food/food_detail/"+data.img + ">" + "</div>" 
			    				+ "<div class='addr'>" + data.addr + "</div>" + "<br>" 
			    				+ "<div class='tel'>" + data.tel +  "</div>" + "<br>"
			    				+ "<a href="+"http://localhost:9000/myjeju/food_detail.do?fid=" + data.idx + ">상세보기</a>"
			    			+ "</div>"
			    		+ "</div>"
			    	+ "</div>";
			  	    content.style.cssText = 'background: white; border-radius:10px; margin:0; padding:0; ';
				
				    var closeBtn = document.createElement('button');
				    closeBtn.innerHTML = '닫기';
				    closeBtn.style.cssText = 'background:white; border: 1px solid white; float:right; margin-top:-132px; color:black; font-weight:bold; padding:5.5px;';
				    closeBtn.onclick = function () {
				        overlay.setMap(null);
				    };
				    content.appendChild(closeBtn);
				    overlay.setContent(content);
			
				    kakao.maps.event.addListener(marker, 'click', function() {
				        overlay.setMap(map);
				    });
				}
			}
			</script>
		</section>
		
		<section class="list_zone">
			<div class="travel_title">음식점 리스트</div>
			<div class="travel_search_zone">
				<select name="search" class="search" id="search">
					<option value="f_name">가게명</option>
					<option value="f_addr">지역명</option>
				</select>
				<input type="text" name="search_text" id="search_text" placeholder="음식점을 검색하세요.">
				<button type="submit" class="btn_style3" id="travel_search_btn">검색</button>
			</div>
			<table id="travel_list_table" class="travel_list">
				<tbody id="list_body">
				<%-- <c:forEach var="vo" items="${list}">
					<tr class="travel_list1">
						<td class="travel_list_pic">
							<img src="http://localhost:9000/myjeju/images/food/${vo.f_image1}">
						</td>
						<td>
							<p class="spot_name">${vo.f_name} <span>${vo.f_infor}</span></p>
							<p class="spot_addr">${vo.f_addr}</p>
							<div>
								<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.6 (209)</span>
							</div>
						</td>
						<td>
							<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">${vo.f_like}</button>
							<button type="button" class="btn_style4" id="more_infor" onclick="location.href='http://localhost:9000/myjeju/food_detail.do?fid=${vo.fid}'">상세정보</button>
						</td>
					</tr>
				</c:forEach> --%>
				</tbody>
			</table>
		</section>
		
		<button type="button" class="btn_style5" id="more_btn">more
			<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn2.png">
			<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn.png">
		</button>
		<input type="hidden" class="pnum" name="pnum">
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>