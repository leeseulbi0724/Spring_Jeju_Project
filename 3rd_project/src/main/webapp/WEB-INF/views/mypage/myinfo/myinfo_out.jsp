<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>회원탈퇴 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/mypage/myout.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
</head>
<script>
	$(document).ready(function() {
		$("#ok").click(function() {
			if( $("#pass").val() == "" ) {
				alert("비밀번호를 입력해주세요");
				$("#pass").focus();
			} else {
				var pass = $("#pass").val();
				 $.ajax({
				        url:"pass_check.do",
				        type:"post",
				        data: {
				     	 	pass: pass,
				        }, 
				        dataType: "json",
				      success:function(result){
				       		if ( result ) {
				       		 $.ajax({
							        url:"info_out.do",
							        type:"POST",
							        dataType: "json",
							      success:function(result){
							       		if ( result ) {
							       		 alert("회원탈퇴 되었습니다. 그동안 이용해주셔서 감사합니다.");
							       		location.replace("logout.do");
							       		} else {
							       			alert("회원 탈퇴 실패하셨습니다");
							       			location.reload();
							       		}							       		
							       	},		
							    });
				       		} else {
				       			alert("비밀번호가 일치하지 않습니다");
				       			$("#pass").val("").focus();
				       		}
				       	},		
				    });
			}
		});
	});
</script>
<body>
<jsp:include page="../../header.jsp"></jsp:include>

<section>
	<div class="center">
		<h3>회원탈퇴</h3>
		<div class="box">
			[ 주의 ] JEJU ISLAND 회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해주세요.<br>
			<span>1. 탈퇴 후 삭제 내역</span>
			<ul>
				<li>개인 정보</li>
				<li>My 이력</li>
				<li>스토어 주문 내역</li>
				<li>로그인과 관련된 모든 정보</li>
			</ul>
			<span>2. 탈퇴 사유</span>
			<ul class="ul">
				<li><input type="radio" name="one"> 서비스 장애가 있어서</li>
				<li><input type="radio" name="two"> 이벤트 및 혜택이 적어서</li>
				<li><input type="radio" name="three"> 불만 및 불편사항에 대한 고객응대가 나빠서</li>
				<li><input type="radio" name="four"> 이용빈도가 낮고 개인정보 유출이 우려되어</li>
			</ul>	
			<table class="table">
			<tr>
				<th>비밀번호 입력</th>
				<th><input type="password" class="form-control" id="pass"> ※원활한 회원탈퇴를 위해 비밀번호를 입력해주세요.</th>
			</tr>		
			</table>		
		</div>
		<a class="btn btn-outline-secondary" href="mypage.do" id="cancle">취소</a>
		<a class="btn" id="ok" href="#">탈퇴</a>
	</div>
</section>

<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>