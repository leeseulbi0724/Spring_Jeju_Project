<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>카드결제 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" ></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<style>
	section { text-align:center; margin-bottom:50px;  }
	.center { 
		margin-top:100px; 
		width:1000px; 
		display:inline-block; 
	}
	
	.info {
		display:inline-block;
		background-color:white;
		border:2px solid lightgray;
		width:800px; height:130px;
		text-align:center;
	}
	.info>div {
		display:inline-block; width:230px; height:100px; border-right:1px solid lightgray;
		margin:10px; float:left;
	}
	.info>div>p { margin-top:25px; }
	.info>div:first-child { margin-left:30px; }
	.info>div:last-child { border:none; font-size:18px; }
	
	.card { 
		display:inline-block;
		width:800px;
		height:200px;
		background-color:rgb(242,244,245); 
		border:1px solid lightgray;
		font-size:14px;
	}
	.card ul { list-style:none; text-align:left; margin:45px 200px; }
	.card label { margin-bottom:5px; }
	
	.number .form-control, .day .form-control { display:inline-block; width:60px; height:25px; font-size:13px; } 
	.pass .form-control, .birth .form-control { display:inline-block; width:130px; height:25px; font-size:13px; }
	
	.card span { font-size:13px; color:gray; }
	.card>input { margin:10px 0 50px 0; }
		
	.card a {
		 text-decoration:none; border:1px solid lightgray; padding:5px 10px; border-radius:4px; }	
	.back {
		background-color:rgb(245,245,245);
		color:black;
	}
	 .pay {
	 	background-color:rgb(7,114,215); 
	 	color:white;
	 	font-size:14px;
	 	margin-top:-5px;
	 	margin-right:10px; 
	 	margin-left:10px;
	 	cursor:pointer;
	 }
	 .pay:hover { background-color:rgb(4,72,134); color:white; }
</style>
</head>
<script>
	$(document).ready(function() {
		$(".pay").click(function() {
			if ($("#card1").val() == "") {
				alert("카드번호를 입력해주세요");
				$("#card1").focus();
			} else if ($("#card2").val() == "") {
				alert("카드번호를 입력해주세요");
				$("#card2").focus();
			} else if ($("#card3").val() == "") {
				alert("카드번호를 입력해주세요");
				$("#card3").focus();
			} else if ($("#card4").val() == "") {
				alert("카드번호를 입력해주세요");
				$("#card4").focus();
			} else if ($("#date1").val() == "") {
				alert("유효기간을 입력해주세요");
				$("#date1").focus();
			} else if ($("#date2").val() == "") {
				alert("유효기간을 입력해주세요");
				$("#date2").focus();
			} else if ($("#pass").val() == "") {
				alert("비밀번호를 입력해주세요");
				$("#pass").focus();
			} else if ($("#date1").val() == "") {
				alert("생년월일을 입력해주세요");
				$("#brith").focus();
			} else if ($("input[id=check]").is(":checked") == false ) {
				alert("동의 확인을 클릭해주세요");
				$("input[type=checkbox]").focus();
			} else {
				var con_test = confirm("결제 하시겠습니까?"); 
	        	if(con_test == true){ 
	        		  $.ajax({
			                type: "post",
			                url: "payment_proc.do",
			                data:{
			                	list:"${list}",
			                	total:"${total-dis}",
			                	dis:"${dis}",
			                	option:"${option}",
			                	count:"${c}",
			                	point:"${point}"
			                	},
			                dataType: 'json',
			                success: function (result) {
			                	if (result) {
				                    alert("결제가 완료되었습니다");
				                    location.replace("myorder.do");
			                	}
			                },

			            });
	        	}
			}
		});
	});
</script>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<section>
<div class="center">
	<div class="info">
		<div>
			<p>총 상품 금액<br><strong>${total }</strong>원	</p>
		</div>
		<div>
			<p>할인 금액<br><strong style="color:red">${dis }</strong>원	</p>
		</div>
		<div>
			<p>총 결제 금액<br><strong style="color:red">${total-dis }</strong>원</p>
		</div>
	</div>
	<div class="card">
		<ul>
			<li class="number">
				<label>카드번호</label>
				<input class="form-control" id="card1" maxlength="4">-
				<input class="form-control" id="card2" maxlength="4">-
				<input class="form-control" id="card3" maxlength="4">-
				<input class="form-control" id="card4" maxlength="4">			
			<li>
			<li class="day">
				<label>유효기간</label>
				<input class="form-control" id="date1" maxlength="2">/
				<input class="form-control" id="date2" maxlength="2">
			</li>
			<li class="pass">
				<label>비밀번호</label>
				<input class="form-control" type="password" id="pass" maxlength="2"><span>앞 2자리</span>
			</li>
			<li class="birth">
				<label>생년월일</label>
				<input class="form-control" id="birth" maxlength="6"><span>생년월일 6자리</span>
			</li>
		</ul>
		<input type="checkbox" id="check">결제에 동의하시겠습니까?
		<div>
			<a href="store.do" class="back">스토어 돌아가기</a>
			<a class="pay">결제</a>
		</div>
	</div>
</div>
</section>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>