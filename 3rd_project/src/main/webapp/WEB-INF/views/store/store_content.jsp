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
<link rel="stylesheet" href="css/admincss/bootstrap.css">
<link rel="stylesheet" href="css/admincss/bootstrap.min.css">
<script src="http://localhost:9000/myjeju/js/bootstrap.min.js"></script>
<style>
	
	.star-rating { 
		width:100px; 
		float:left; 
		margin-top:15px; 
		margin-left:15px; 
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
	
	.title-box>span:last-child {
		display : inline-block;
		margin-top : 12px;
		margin-left : 20px;
		font-size : 18px;
		font-weight : 500;
	} 

</style>
<script>

	$(document).ready(function() {
		
		/* 상단 카테고리 클릭 시 스크롤 다운 */
		$("#move1").click(function() {
			var offset = $("#here1").offset();
			$('html').animate({scrollTop : offset.top}, 100);
		});
		
		$("#move2").click(function() {
			var offset = $("#here2").offset();
			$('html').animate({scrollTop : offset.top}, 0);
		});
		
		$("#move3").click(function() {
			var offset = $("#here3").offset();
			$('html').animate({scrollTop : offset.top}, 0);
		});
		
		$("#move4").click(function() {
			var offset = $("#here4").offset();
			$('html').animate({scrollTop : offset.top}, 0);
		});
		
		
		/* 메뉴바 상단 고정 */
		var menu = $(".store_content_category").offset();
		
		$(window).scroll(function() {
			if($(document).scrollTop() > menu.top) {
				$('.store_content_category').addClass('menu-fixed');
			} else {
				$('.store_content_category').removeClass('menu-fixed');
			}
		});
		
		
		/* 장바구니 클릭시 */
		$("#storeBasket").click(function() {
			if($("#b_count").val() == 0) {
				alert("수량을 입력해주세요");
				$("#b_count").focus();
				return false;
			} else {				
					var form1 = $("#store_form").serialize();
					 $.ajax({
					        url:"mybasket_proc1.do",
					        type:"post",
					        data: form1, 
					        dataType: "json",
					      success:function(result){
					       		if ( result ) {
					       		 location.replace("mybasket.do");
					       		} else {
					       			alert("장바구니 담기 실패");
					       		}
					       	},		
					    });
			}
		});	
		
		
			
		/* 구매하기 버튼 클릭 시 */
		$("#store_buy").click(function() {
			if($("#b_count").val() == 0) {
				alert("수량을 입력해주세요");
				$("#b_count").focus();
				return false;
			} else {
				store_form.submit();
			}
		});
		
		
		/* 상품평 유효성 체크 및 등록 */
		$("#reviewInsert").click(function() {
			if($("#sr_review").val() == "") {
				alert("상품평을 입력해주세요");
				$("#sr_review").focus();
				return false;
			} else {
				var store_review_form = $("#store_review_form").serialize();
				
				$.ajax({
					url : "store_review_proc1.do",
					type : "post",
					data : store_review_form,
					dataType : "json",
					
					success : function(result) {
						if(result) {
							location.reload();
						} else {
							alert("실패실패");
						}
					}
				});
				
				return true;
			}
		});
		
		
		/* 상품평 수정 - 내용 가져오기 - 모달 띄우기 */
		$("button[id^=reviewUpdateBtn]").click(function() {
			var update = confirm("수정하시겠습니까??");
			
			if(update) {
				var srid = $(this).attr("name");
				$("#srid2").val(srid);
				
				$('#reviewUpdateModal').modal("show");
			}
		});
		
		/* 상품평 수정 모달에서 수정하기 버튼 */
		$("#modalY3").click(function() {
			reviewUpdate_form.submit();
		});
	
		
		/* 상품평 삭제 */
		//$("#reviewDeleteBtn").click(function() {
		$("button[id^=reviewDeleteBtn]").click(function() {
			var reviewdelete = confirm("상품평을 삭제하시겠습니까??");
			
			if(reviewdelete) {
				var srid = $(this).attr("name");
				
				$.ajax({
					type : "post",
					url : "review_delete.do",
					data : {srid : srid},
					dataType : 'json',
					success : function(result) {
						location.reload();
					},
				});
			}
		});
		
		
		/* 상품평 더보기 */
		$("#less_review").hide();
		
		$("#more_review").click(function() {
			$(this).next("div").toggleClass("hide");
			$(this).hide();
			$("#less_review").show();
		});
		
		$("#less_review").click(function(){
			$(this).prev("div").toggleClass("hide");
			$(this).hide();
			$("#more_review").show();
		});
		
		/* 상품문의 더보기 */
		$("#less_faq").hide();
		
		$("#more_faq").click(function() {
			$(this).next("div").toggleClass("hide");
			$(this).hide();
			$("#less_faq").show();
		});
		
		$("#less_faq").click(function(){
			$(this).prev("div").toggleClass("hide");
			$(this).hide();
			$("#more_faq").show();
		});
		

		
		
		/* 문의하기 버튼 클릭시 모달창*/
		$('#storefaqBtn').click(function(e){
			e.preventDefault();
			$('#faqModal').modal("show");
		});
		
		/* 답변하기 버튼 클릭시 모달창 */
		//$('#storereplyBtn').click(function(e){
		$("button[id^='storereplyBtn']").click(function(e) {
			e.preventDefault();
			
			var st_id = $(this).attr("name");
			$("#st_id2").val(st_id);

			
			$('#replyModal').modal("show");
		});
		
		
		/* 문의하기 모달에서 문의하기 버튼 */
		$("#modalY1").click(function() {
			stofaq_form.submit();
		});
		
		/* 답변하기 모달에서 답변하기 버튼 */
		//$("#modalY2").click(function(){
		$("button[id^='modalY2']").click(function() {	
			storep_form.submit();
		});
		
		/* 상품문의 삭제 */
		$("button[id^='faqDeleteBtn']").click(function() {
			var faqdelete = confirm("상품문의를 삭제하시겠습니까??");
			
			if(faqdelete) {
				var st_id = $(this).attr("name");
				
				$.ajax({
					type : "post",
					url : "faq_delete.do",
					data : {st_id : st_id},
					dataType : 'json',
					success : function(result) {
						location.reload();
					}
				});
			}
		});

		
	});

	
	
</script>

</head>
<body>

	<!-- Header -->
	<jsp:include page = "../header.jsp"></jsp:include>
	
	<div class = "store_content">
		
		<div><h3>상품 상세페이지</h3></div>
		
		<div class = "store_content_1">
			<div class = "store_content_top">
				<form name = "store_form" action = "store_buy2.do" method = "POST" class = "store_content_form" id = "store_form">					
					<div class = "img_area">
						<img src = "http://localhost:9000/myjeju/images/store/store_detail/${vo.s_sfile}">
					</div>
					
					<div class = "text_area">
						<div class = "title-box">
							<div class = "store_content_title">[${vo.s_category}] ${vo.s_name}</div>
							<div class = "store_content_price">가격　　　　　　${vo.s_price}원</div>
							<input type = "hidden" name = "s_name" value = "${vo.s_name}">
							<input type = "hidden" name = "s_price" value = "${vo.s_price}">
							<input type = "hidden" name = "s_image" value = "${vo.s_sfile}">
							<input type = "hidden" name = "s_sfile" value = "${vo.s_ssfile}">
							
							<span class='star-rating'>
								<span style ="width : ${vo.star_avg*20}%"></span>   
							</span>   
							<span>${vo.star_avg} (${vo.star_count})</span>
							
						</div>
						
						<div class = "option-box">
							<div>수량　　<input type = "number" min = "0" max = "10" id = "b_count" name = "b_count"></div>
						</div>						
						<input type = "hidden" value = "${id}" name = "id"  id = "id">
						<input type = "hidden" value = "${sid}" name = "sid"  id = "sid">
						<button type = "button" class = "store_btn_style1" id = "storeBasket">장바구니</button>
						<%-- <a href = "mybasket_proc.do?id=${id}"><button type = "button" class = "store_btn_style1" id = "storeBasket">장바구니</button></a> --%>
						<button type = "submit" class = "store_btn_style2" id = "store_buy">바로구매</button>
					</div>
				</form>
	
			</div>
		
		
			<div class = "store_content_middle">
				<div class= "store_content_category">
					<button type = "button" class = "content_category_btn" id = "move1">상품상세</button>
					<button type = "button" class = "content_category_btn" id = "move2">상품평</button>
					<button type = "button" class = "content_category_btn" id = "move3">상품문의</button>
					<button type = "button" class = "content_category_btn" id = "move4">배송/교환/반품 안내</button>
				</div>
			
				<div class = "content_area">
					<img src = "http://localhost:9000/myjeju/images/store/store_detail/${vo.s_ssfile}" id = "here1">
				</div>
				
				<div class = "store_product_review" id = "here2">
					<div>상품평</div>
					
					<article class = "store_review_write">
						<form name = "store_review_form" action = "store_review_proc1.do" method = "POST" id = "store_review_form">
							<input type = "hidden" name = "id" id = "id" value = "${id}">
							<input type = "hidden" name = "sid" id = "sid" value = "${sid}">
							<select name="sr_star" id="sr_star">
								<option id="star5" value="5">★★★★★</option>
								<option id="star4" value="4">★★★★☆</option>
								<option id="star3" value="3">★★★☆☆</option>
								<option id="star2" value="2">★★☆☆☆</option>
								<option id="star1" value="1">★☆☆☆☆</option>
							</select>
							<span>상품평</span>
							<c:if test ="${result eq 'true' }">
								<input type = "text" name = "sr_review" id = "sr_review">
								<button type = "button" id = "reviewInsert" class = "StoreReviewBtn">등록</button>
							</c:if>
							<c:if test ="${result eq 'false' }">
								<input type = "text" name = "sr_review" id = "sr_review" disabled placeholder="주문 후 이용 가능합니다">
								<button type = "button" id = "reviewInsert" class = "StoreReviewBtn" disabled>등록</button>
							</c:if>
						</form>
					</article>
					
					<!-- 상품평 수정 Modal-->
					<div class="modal fade" id="reviewUpdateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title" id="exampleModalLabel">상품평 수정하기</h3>
								</div>
								
								<form name = "reviewUpdate_form" action = "reviewUpdate_proc.do" method = "GET" class = "reviewUpdate_form">
									<div class="modal-body">
										<input type = "hidden" value = "${id}" name = "id" id = "id">
										<input type = "hidden" value = "${sid}" name = "sid" id = "sid">
										<select name="sr_star2" id="sr_star2">
											<option id="star5" value="5">★★★★★</option>
											<option id="star4" value="4">★★★★☆</option>
											<option id="star3" value="3">★★★☆☆</option>
											<option id="star2" value="2">★★☆☆☆</option>
											<option id="star1" value="1">★☆☆☆☆</option>
										</select>
										<input type = "hidden" value = "" name = "srid2" id = "srid2">
										<div><input type = "text" placeholder = "수정할 상품평을 입력해주세요." name = "sr_review2" id = "sr_review2" width = "100px"></div>
									</div>
									
									<div class="modal-footer">
										<button class="btn" type="button" id = "modalY3">수정하기</button>
										<button class="btn" type="button" data-dismiss="modal">닫기</button>
									</div>
								</form>
								
							</div>
						</div>
					</div>
				
					<c:forEach var = "rlist" items = "${rlist}" begin="0" end="2">
						<article class = "store_review_list">
							<div>
								<img src="http://localhost:9000/myjeju/images/travel/travel_detail/human.png" width = "40">
								&nbsp;&nbsp;ID&emsp;<span>${rlist.id}</span>
							</div>
							<div><span>
								<c:choose>
									<c:when test="${rlist.sr_star == 1}">
										★☆☆☆☆
									</c:when>
									<c:when test="${rlist.sr_star == 2}">
										★★☆☆☆
									</c:when>
									<c:when test="${rlist.sr_star == 3}">
										★★★☆☆
									</c:when>
									<c:when test="${rlist.sr_star == 4}">
										★★★★☆
									</c:when>
									<c:otherwise>
										★★★★★
									</c:otherwise>
								</c:choose>
							</span></div>
							<div><span>${rlist.sr_review}</span></div>
							<div><span>${rlist.sr_time}</span></div>
							
							<c:if test = "${rlist.id eq session_id}">
								<button type = "button" name = "${rlist.srid}" id = "reviewUpdateBtn" class = "reviewBtn">수정</button>
								<button type = "button" name = "${rlist.srid}" id = "reviewDeleteBtn" class = "reviewBtn">삭제</button>
							</c:if>
							
						</article>
					</c:forEach>
					
					<img src = "http://localhost:9000/myjeju/images/travel/bill_list_btn2.png" id = "more_review" class = "more_review">
					
					<div class = "hide">
						<c:forEach var = "rlist" items = "${rlist}" begin="3">
							<article class = "store_review_list">
								<div>
									<img src="http://localhost:9000/myjeju/images/travel/travel_detail/human.png" width = "40">
									&nbsp;&nbsp;ID&emsp;<span>${rlist.id}</span>
								</div>
								<div><span>
									<c:choose>
										<c:when test="${rlist.sr_star == 1}">
											★☆☆☆☆
										</c:when>
										<c:when test="${rlist.sr_star == 2}">
											★★☆☆☆
										</c:when>
										<c:when test="${rlist.sr_star == 3}">
											★★★☆☆
										</c:when>
										<c:when test="${rlist.sr_star == 4}">
											★★★★☆
										</c:when>
										<c:otherwise>
											★★★★★
										</c:otherwise>
									</c:choose>
								</span></div>
								<div><span>${rlist.sr_review}</span></div>
								<div><span>${rlist.sr_time}</span></div>
								<c:if test = "${rlist.id eq session_id}">
									<button type = "button" name = "${rlist.srid}" id = "reviewUpdateBtn" class = "reviewBtn">수정</button>
									<button type = "button" name = "${rlist.srid}" id = "reviewDeleteBtn" class = "reviewBtn">삭제</button>
								</c:if>
							</article>
						</c:forEach>
					</div>
					
					<img src = "http://localhost:9000/myjeju/images/travel/bill_list_btn4.png" id = "less_review" class = "less_review">	
				

				</div>
				
				<div class = "store_product_faq" id = "here3">
					<div class = "store_product_faq_top">
						<div>상품문의</div>
						<button type = "button" id = "storefaqBtn">문의하기</button>
					</div>
					
					  <!-- 문의하기 Modal-->
						<div class="modal fade" id="faqModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span></button>
										<h3 class="modal-title" id="exampleModalLabel">문의하기</h3>
									</div>
									
									<form name = "stofaq_form" action = "store_faq_proc.do" method = "GET" class = "stofaq_form">
										<div class="modal-body">
											<input type = "hidden" value = "${id}" name = "id" id = "id">
											<input type = "hidden" value = "${sid}" name = "sid" id = "sid">
											<input type = "text" value = "${vo.s_name}" readonly>
											<div><input type = "text" placeholder = "문의할 내용을 입력해주세요" name = "st_content" id = "st_content1"></div>
										</div>
										
										<div class="modal-footer">
											<button class="btn" type="button" id = "modalY1">문의하기</button>
											<button class="btn" type="button" data-dismiss="modal">닫기</button>
										</div>
									</form>
									
								</div>
							</div>
						</div>
					
					<div class = "store_product_faq_content">
						
						<ul>
							<li>
								<c:forEach var = "flist" items = "${flist}" begin = "0" end = "2">
									<div class = "store_faq_all">
										<div class = "store_faq_list">
											<img src="http://localhost:9000/myjeju/images/store/question.png" width = "20">
											<span>${flist.id}</span>
											<span>${flist.st_content}</span>
											<span>${flist.st_time}</span>
											<input type = "hidden" value = "${sid}" name = "sid"  id = "sid">
											<input type = "hidden" id = "st_id1" name = "st_id1" value = "${flist.st_id}">
											
											<c:choose>
												<c:when test = "${session_id eq 'admin'}">
													<button type = "button" id = "storereplyBtn" name = "${flist.st_id}" class = "btn">답변하기</button>
												</c:when>
												
												<c:when test = "${session_id eq flist.id}">
													<button type = "button" id = "faqDeleteBtn" name = "${flist.st_id}" class = "btn">삭제</button>
												</c:when>
											</c:choose>
										
										</div>
										
										<div class = "store_ans_list">
											<c:if test = "${!empty flist.re_content}">
												<img src="http://localhost:9000/myjeju/images/store/answer1.png" width = "15">
												<span>제주아일랜드 관리자</span>
												<span>${flist.re_content}</span>
												<span>${flist.re_time }</span>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</li>
						</ul>
						
						<img src = "http://localhost:9000/myjeju/images/travel/bill_list_btn2.png" id = "more_faq" class = "more_faq">
						
						<div class = "hide">
							<ul>
								<li>
									<c:forEach var = "flist" items = "${flist}" begin = "3">
										<div class = "store_faq_all">
											<div class = "store_faq_list">
												<img src="http://localhost:9000/myjeju/images/store/question.png" width = "20">
												<span>${flist.id}</span>
												<span>${flist.st_content}</span>
												<span>${flist.st_time}</span>
												<input type = "hidden" value = "${sid}" name = "sid"  id = "sid">
												<input type = "hidden" id = "st_id1" name = "st_id1" value = "${flist.st_id}">
												
												<c:choose>
													<c:when test = "${session_id eq 'admin'}">
														<button type = "button" id = "storereplyBtn" name = "${flist.st_id}" class = "btn">답변하기</button>
													</c:when>
													
													<c:when test = "${session_id eq flist.id}">
														<button type = "button" id = "faqDeleteBtn" name = "${flist.st_id}" class = "btn">삭제</button>
													</c:when>
												</c:choose>
											</div>
											
											<div class = "store_ans_list">
												<c:if test = "${!empty flist.re_content}">
													<img src="http://localhost:9000/myjeju/images/store/answer1.png" width = "15">
													<span>제주아일랜드 관리자</span>
													<span>${flist.re_content}</span>
													<span>${flist.re_time }</span>
												</c:if>
											</div>
										</div>
									</c:forEach>
								</li>
							</ul>
						</div>
						
						<img src = "http://localhost:9000/myjeju/images/travel/bill_list_btn4.png" id = "less_faq" class = "less_faq">
					</div>
					
						
					
					<!-- 답변하기 Modal-->
						<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span></button>
										<h3 class="modal-title" id="exampleModalLabel">답변하기</h3>
									</div>
									
									<form name = "storep_form" action = "store_faq_reply_proc.do" method = "GET" class = "storep_form">
										<div class="modal-body">
											<input type = "hidden" value = "${id}" name = "id" id = "id">
											<input type = "hidden" value = "${sid}" name = "sid" id = "sid">
											<input type = "hidden" name = "st_id" id = "st_id2" value = "" readonly>
											<div><input type = "text" placeholder = "답변할 내용을 입력해주세요" name = "re_content" id = "re_content"></input></div>
										</div>
										
										<div class="modal-footer">
											<%-- <a class="btn" id="modalY2" href="store_faq_reply_proc.do?sid=${sid}">답변하기</a> --%>
											<button class="btn" type="submit" id = "modalY2">답변하기</button>
											<button class="btn" type="button" data-dismiss="modal">닫기</button>
										</div>
									</form>	
								</div>
							</div>
						</div>
				
				</div>
				
				<div class = "store_product_ship" id = "here4">
					<div>배숑/교환/반품 안내</div>
					<div class = "store_product_ship_content">
						<div><h4>배송정보</h4></div>
						<table>
							<tr>
								<td>배송방법</td>
								<td>순차배송</td>
								<td rowspan = "2">배송비</td>
								<td rowspan = "2">3,000원</td>
							</tr>
						
							<tr>
								<td>배송사</td>
								<td>쌍용택배</td>
							</tr>
							
							<tr>
								<td>묶음배송 여부</td>
								<td colspan = "3">불가능</td>
							</tr>
							
							<tr>
								<td>배송기간</td>
								<td colspan = "3">도서산간 지역 등은 배송에 3-5일이 더 소요될 수 있습니다.</td>
							</tr>
						</table>
					
						<div><h4>교환/반품 안내</h4></div>
						<table>
							<tr>
								<td>교환/반품 비용</td>
								<td colspan = "2">6,000원 - 단, 고객 변심의 경우에만 발생</td>
							</tr>
							
							<tr>
								<td>교환/반품 신청 기준일</td>
								<td>ㆍ단순변심에 의한 교환/반품은 제품 수령 후 7일 이내까지, 교환/반품 제한사항에 해당하지 않는 경우에만 가능 (배송비용과 교환/반품 비용 왕복배송비 고객부담)<br>
									ㆍ상품의 내용이 표시·광고의 내용과 다른 경우에는 상품을 수령한 날부터 3개월 이내, 그 사실을 안 날 또는 알 수 있었던 날부터 30일 이내에 청약철회 가능</td>
							</tr>
							
						</table>
					
						<div><h4>교환/반품 제한사항</h4></div>
						<div>ㆍ주문/제작 상품의 경우, 상품의 제작이 이미 진행된 경우</div>
						<div>ㆍ상품 포장을 개봉하여 사용 또는 설치 완료되어 상품의 가치가 훼손된 경우 (단, 내용 확인을 위한 포장 개봉의 경우는 예외)</div>
						<div>ㆍ고객의 사용, 시간경과, 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우</div>
						<div>ㆍ세트상품 일부 사용, 구성품을 분실하였거나 취급 부주의로 인한 파손/고장/오염으로 재판매 불가한 경우</div>
						<div>ㆍ모니터 해상도의 차이로 인해 색상이나 이미지가 실제와 달라, 고객이 단순 변심으로 교환/반품을 무료로 요청하는 경우</div>
						<div>ㆍ제조사의 사정 (신모델 출시 등) 및 부품 가격 변동 등에 의해 무료 교환/반품으로 요청하는 경우</div>
					
						<div><h4>※ 각 상품별로 아래와 같은 사유로 취소/반품이 제한 될 수 있습니다.</h4></div>
						<table>
							<tr>
								<td>의류/잡화/수입명품</td>
								<td>ㆍ상품의 택(TAG) 제거, 라벨 및 상품 훼손, 구성품 누락으로 상품의 가치가 현저히 감소된 경우</td>
							</tr>
							
							<tr>
								<td>계절상품/식품/화장품</td>
								<td>ㆍ신선냉동 식품의 단순변심의 경우 <br>
									ㆍ뷰티 상품 이용 시 트러블(알러지, 붉은 반점, 가려움, 따가움)이 발생하는 경우, 진료 확인서 및 소견서 등을 증빙하면 환불이 가능 (제반비용 고객부담)</td>
							</tr>
							
							<tr>
								<td>전자/가전/설치상품</td>
								<td>ㆍ설치 또는 사용하여 재판매가 어려운 경우, 액정이 있는 상품의 전원을 켠 경우</td>
							</tr>
							
							<tr>
								<td>자동차용품</td>
								<td>ㆍ상품을 개봉하여 장착한 이후 단순변심인 경우</td>
							</tr>
							
							<tr>
								<td>CD/DVD/GAME/BOOK</td>
								<td>ㆍ복제가 가능한 상품의 포장 등을 훼손한 경우</td>
							</tr>
							
						</table>
						
						<div><h4>판매자정보</h4></div>
						<table>
							<tr>
								<td>상호/대표자</td>
								<td>쌍용 제주아일랜드</td>
								<td>사업장 소재지</td>
								<td>서울특별시 강남구 역삼동</td>
							</tr>
							
							<tr>
								<td>e-mail</td>
								<td>jeju@jeju.co.kr</td>
								<td>연락처</td>
								<td>02-1234-5678</td>
							</tr>
							
							<tr>
								<td>통신판매업 신고번호</td>
								<td>제1-서울강남-0001호</td>
								<td>사업자번호</td>
								<td>123-45-678</td>
							</tr>
						
						</table>
				
					</div>
				</div> 
				
			</div>
		
		</div>
		
	</div>
	
	
	<!-- Footer -->
	<jsp:include page = "../footer.jsp"></jsp:include>

</body>
</html>
