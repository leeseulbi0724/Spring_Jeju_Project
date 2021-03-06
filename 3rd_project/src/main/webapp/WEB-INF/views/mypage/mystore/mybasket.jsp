<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var = "price_total" value = "0" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>장바구니 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" ></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/mypage/mybasket.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
</head>
<script>
	$(document).ready(function() {
		
		$(function(){
			  $("button[id='delete']").on("click", drop);
		    });		  
		    function drop() {
		    	var sid = $(this).attr("name");
		      $.ajax({
		                type: "post",
		                url: "basket_delete.do",
		                data:{sid:sid},
		                dataType: 'json',
		                success: function (result) {
		                    location.reload();
		                },

		            });
		    };
		    
		    $("#all_check").click(function(){
		    	//테이블 th 쪽 전체 체크버튼 클릭 시
				 var chk = $("#all_check").prop("checked"); //체크버튼 클릭이 되었을 때
				 if(chk) {
				  $("input[id=check]").prop("checked", true); //전체체크버튼 클릭
				  	itemSum(); //총 결제금액 계산
				 } else {
				  $("input[id=check]").prop("checked", false); //전체체크버튼 해제
				  	itemSum(); //총 결제금액 계산
				 }
			});
		    
		    $("input[id=check]").click(function() {
		    	//테이블 td 쪽 체크버튼을 클릭했을 때
		    	itemSum();  //총 결제 금액 계산
		    });		    
		    
		    function itemSum(){
		 	   var str = "";
		 	   var sum = 0;
		 	   var count = $("input[id=check]").length; //체크 된 input 갯수 구하기
		 	   for(var i=0; i < count; i++ ){
		 	       if( $("input[id=check]")[i].checked == true ){ 
		 		    sum += parseInt($("input[id=check]")[i].value);	 //체크된 값들의 value값을 sum에 넣기		
		 	       }
		 	   }
		 	   $(".total").html(sum+" 원"); //총 결제 금액에 sum값으로 덮어쓰기
		 	}		   
		    
		    
		    
		    
		    /* store_buy.do */
		    $(".pay").click(function() {		    	
		    	sidList();
		    });
			function sidList() {
				var count = $("input[id=check]").length;
				var list = [];
			    for(var i=0; i < count; i++ ){
		 	       if( $("input[id=check]")[i].checked == true ){
		 		    	list.push($("input[id=check]")[i].name);
		 		    	 location.replace("store_buy.do?list="+list+"&type=mypage");
		 	       }
				}
			}
		    
		
	});
</script>
<body>
<jsp:include page="../../header.jsp"></jsp:include>

<section>
	<div class="center">
		<table class="table">
			<tr>
				<th class="count">상품(${column })</th>
			</tr>
			<tr>
				<th class="check"><input type="checkbox"  checked id="all_check"></th>
				<th class="info">상품정보</th>
				<th>수량</th>
				<th>판매가</th>
				<th></th>
			</tr>
			
			<tbody>			
			<c:forEach var = "list" items = "${list}">		
				<tr>
				
					<!-- 체크박스 value에 (상품 가격x갯수) 넣어놓기 -->
					<td><input type="checkbox" checked id="check" value="${list.s_price * list.b_count }" name="${list.sid }"></td>
					
					
					<td>
						<img src="http://localhost:9000/myjeju/images/store/store_detail/${list.s_image}" width=50 height=30>
						<a href="#">${list.s_name}</a>
					</td>
					<td>
						<input class="form-control" type="number" value="${list.b_count }" disabled>
					</td>
					<c:set var= "price_total" value="${price_total + list.s_price * list.b_count  }"/>
					<td class="price">${list.s_price * list.b_count }원</td>
					<td><button name="${list.sid }" class="btn delete" id="delete">삭제</button></td>
				</tr>	
			</c:forEach>
			</tbody>	
		</table>
		<div class="coin_box">

			<p>총 결제 금액 : <span class="total">${price_total }원</span></p>
		</div>		
		<a href="store.do" class="btn store">계속 쇼핑하기</a>
		<a class="btn pay">결제하기</a>		
	</div>
</section>

<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>