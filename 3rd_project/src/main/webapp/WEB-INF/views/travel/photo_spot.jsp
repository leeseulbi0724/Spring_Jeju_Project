<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<title>포토스팟 | JEJU ISLAND</title>
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/travel/travel.css">
	<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="travel_content">
		<h3>포토스팟</h3>
		<section class="recommend_zone">
			<div class="travel_title">추천 스팟</div>
			<div class="travel_spot">
				<article class="travel_spot1">
					<img src="http://localhost:9000/myjeju/images/travel/photospot2.jpg">
					<div class="spot_infor">
						<p class="spot_name">새별오름 나홀로나무 <span>지역명소</span></p>
						<p class="spot_tag">#오름 #나무 #커플 #걷기/등산 #아이 #봄</p>
						<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">516</button>
						<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.4 (820)</span>
					</div>
				</article>
				<article class="travel_spot2">
					<img src="http://localhost:9000/myjeju/images/travel/photospot23.jpg">
					<div class="spot_infor">
						<p class="spot_name">갯깍주상절리  <span>자연명소</span></p>
						<p class="spot_tag">#해변 #경관/포토 #친구 #커플 #노을</p>
						<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">452</button>
						<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.2 (594)</span>
					</div>
				</article>
				<article class="travel_spot3">
					<img src="http://localhost:9000/myjeju/images/travel/photospot11.jpg">
					<div class="spot_infor">
						<p class="spot_name">이호테우 말등대 <span>해수욕장,해변</span></p>
						<p class="spot_tag">#등대 #일몰 #경관/포토 #커플 #맑음 #가을</p>
						<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">248</button>
						<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.1 (268)</span>
					</div>
				</article>
			</div>
		</section>
		<section class="map_zone">
			<div class="travel_title">포토스팟 찾기</div>
			<div class="travel_map">지도
				
			</div>
		</section>
		<section class="list_zone">
			<div class="travel_title">포토스팟 리스트</div>
			<div class="travel_search_zone">
				<input type="text" name="travel_search" id="travel_search" placeholder="포토스팟을 검색하세요.">
				<button type="button" class="btn_style3" id="travel_search_btn">검색</button>
			</div>
			<table class="travel_list">
				<tbody>
					<tr class="travel_list1">
						<td class="travel_list_pic">
							<img src="http://localhost:9000/myjeju/images/travel/photospot6.jpg">
						</td>
						<td>
							<p class="spot_name">월정리해변 <span>해수욕장,해변</span></p>
							<p class="spot_addr">제주특별자치도 제주시 구좌읍 해맞이해안로 480-1</p>
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
							<img src="http://localhost:9000/myjeju/images/travel/photospot3.jpg">
						</td>
						<td>
							<p class="spot_name">동백수목원 <span>식물원,수목원</span></p>
							<p class="spot_addr">제주특별자치도 서귀포시 남원읍 위미중앙로300번길 15</p>
							<div>
								<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.4 (433)</span>
							</div>
						</td>
						<td>
							<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">287</button>
							<button type="button" class="btn_style4" id="more_infor">상세정보</button>
						</td>
					</tr>
					<tr class="travel_list3">
						<td class="travel_list_pic">
							<img src="http://localhost:9000/myjeju/images/travel/photospot18.jpg">
						</td>
						<td>
							<p class="spot_name">성이시돌목장 <span>농공시설</span></p>
							<p class="spot_addr">제주특별자치도 제주시 한림읍 산록남로 53</p>
							<div>
								<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.1 (683)</span>
							</div>
						</td>
						<td>
							<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">354</button>
							<button type="button" class="btn_style4" id="more_infor">상세정보</button>
						</td>
					</tr>
					<!-- <tr class="travel_list3">
						<td class="travel_list_pic">
							<img src="http://localhost:9000/myjeju/images/travel/photospot21.jpg">
						</td>
						<td>
							<p class="spot_name">용머리해안 <span>해수욕장,해변</span></p>
							<p class="spot_addr">제주특별자치도 서귀포시 안덕면 사계리</p>
							<div>
								<img src="http://localhost:9000/myjeju/images/travel/star.png"><span class="star_score">4.4 (433)</span>
							</div>
						</td>
						<td>
							<button type="button" class="btn_style" id="heart_btn"><img src="http://localhost:9000/myjeju/images/travel/empty_heart.png">287</button>
							<button type="button" class="btn_style4" id="more_infor">상세정보</button>
						</td>
					</tr> -->
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