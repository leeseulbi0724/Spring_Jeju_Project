<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>마이페이지 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" ></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/mypage/mypage.css">
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<section>
	<div class="left">
		<p>${session_name }<span>님의 마이페이지</span></p>
		<div class="img_div"><img src="http://localhost:9000/myjeju/images/mypage/human.png" width=300 height=300 ></div>
		<div class="point_div">총 보유 포인트 <strong>${point }</strong>p</div>
		<a href="mypoint.do">포인트 내역보기</a>
	</div>
	
	<div class="center1">
		<div>
			<p>개인정보 수정</p>
			<div>
				<a href="myinfo_request.do">기본정보 수정</a>
				<a href="myinfo_pass.do">비밀번호 변경</a>
				<a href="myinfo_out.do">회원탈퇴</a>
			</div>
		</div>
	</div>
	<div class="center2">
		<div>
			<p>결제 및 예약</p>
			<div>
				<a href="mybasket.do">장바구니</a>
				<a href="myorder.do">결제 내역</a>
			</div>
		</div>
	</div>
	<div class="center3">
		<div>
			<p>My 이력</p>
			<div>
				<a href="myboard.do">My 게시글</a>
				<a href="myreview.do">My 후기</a>
				<a href="myheart.do">My 좋아요</a>
			</div>
		</div>
	</div>
</section>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>