<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<title>차박스팟 | JEJU ISLAND</title>
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/travel/travel.css">
	<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="travel_content">
		<h3>차박스팟</h3>
		<section class="recommend_zone">
			<div class="travel_title">추천 스팟</div>
			<div class="travel_spot">
				<article class="travel_spot1">
					<img src="http://localhost:9000/myjeju/images/travel/금능해변.jpg">
					<div class="spot_infor">
						<p class="spot_name">금능해수욕장 <span>해수욕장,해변</span></p>
						<p class="spot_tag">#일몰 #해수욕장 #액티비티 #가족 #차박 #여름</p>
						<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">516</button>
						<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.6 (416)</span>
					</div>
				</article>
				<article class="travel_spot2">
					<img src="http://localhost:9000/myjeju/images/travel/광치기해변.jpg">
					<div class="spot_infor">
						<p class="spot_name">광치기해변 <span>해수욕장,해변</span></p>
						<p class="spot_tag">#일출 #해변 #경관/포토 #차박 #봄</p>
						<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">452</button>
						<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.5 (594)</span>
					</div>
				</article>
				<article class="travel_spot3">
					<img src="http://localhost:9000/myjeju/images/travel/함덕해수욕장.jpg">
					<div class="spot_infor">
						<p class="spot_name">함덕해수욕장 <span>해수욕장,해변</span></p>
						<p class="spot_tag">#해수욕장 #액티비티 #차박 #맑음 #여름</p>
						<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">248</button>
						<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.4 (268)</span>
					</div>
				</article>
			</div>
		</section>
		<section class="map_zone">
			<div class="travel_title">차박스팟 찾기</div>
			<div class="travel_map">지도
				
			</div>
		</section>
		<section class="list_zone">
			<div class="travel_title">차박스팟 리스트</div>
			<div class="travel_search_zone">
				<input type="text" name="travel_search" id="travel_search" placeholder="차박스팟을 검색하세요.">
				<button type="button" class="btn_style3" id="travel_search_btn">검색</button>
			</div>
			<table class="travel_list">
				<tbody>
					<tr class="travel_list1">
						<td class="travel_list_pic">
							<img src="http://localhost:9000/myjeju/images/travel/신창풍차해안2.jpg">
						</td>
						<td>
							<p class="spot_name">신창풍차해안 <span>드라이브</span></p>
							<p class="spot_addr">제주특별자치도 제주시 한경면 신창리 1322-1</p>
							<div>
								<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.4 (268)</span>
							</div>
						</td>
						<td>
							<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">1,674</button>
							<button type="button" class="btn_style4" id="more_infor">상세정보</button>
						</td>
					</tr>
					<tr class="travel_list2">
						<td class="travel_list_pic">
							<img src="http://localhost:9000/myjeju/images/travel/표선해수욕장.jpg">
						</td>
						<td>
							<p class="spot_name">표선해수욕장 <span>해수욕장,해변</span></p>
							<p class="spot_addr">제주특별자치도 서귀포시 표선면 표선리</p>
							<div>
								<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.1 (683)</span>
							</div>
						</td>
						<td>
							<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">354</button>
							<button type="button" class="btn_style4" id="more_infor">상세정보</button>
						</td>
					</tr>
					<tr class="travel_list3">
						<td class="travel_list_pic">
							<img src="http://localhost:9000/myjeju/images/travel/수월봉.jpg">
						</td>
						<td>
							<p class="spot_name">수월봉 <span>봉우리,고지</span></p>
							<p class="spot_addr">제주특별자치도 제주시 한경면 고산리 3760</p>
							<div>
								<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.3 (433)</span>
							</div>
						</td>
						<td>
							<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">287</button>
							<button type="button" class="btn_style4" id="more_infor">상세정보</button>
						</td>
					</tr>
				</tbody>
			</table>
		</section>
		<button type="button" class="btn_style5" id="more_btn">more
			<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn2.png">
			<img src="http://localhost:9000/myjeju/images/travel/bill_list_btn.png">
		</button>
	</div>
	
	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>