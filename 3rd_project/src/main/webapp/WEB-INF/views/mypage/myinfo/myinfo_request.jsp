<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>기본정보 수정 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/mypage/request.css">
</head>
<script>
$(document).ready(function() {
	$("#ok").click(function() {
		if ($("#pass").val() == "") {
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
			       		 location.replace("myinfo.do");
			       		} else {
			       			alert("비밀번호가 일치하지 않습니다");
			       			$("#pass").val("").focus();
			       		}
			       	},		
			    });
		}
	})
});
</script>
<body>
<jsp:include page="../../header.jsp"></jsp:include>

<section>
	<p>회원님의 개인정보 보호를 위해 <strong>비밀번호</strong>를 입력하셔야 합니다.<br>
	<span>로그인 시 사용하시는 비밀번호를 입력해주세요.</span></p>
	<input type="password" class="form-control" id="pass">
	<div>
		<a class="btn btn-outline-secondary" href="mypage.do" id="cancle">취소</a>
		<a class="btn" id="ok" href="#">확인</a>
	</div>
</section>

<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>