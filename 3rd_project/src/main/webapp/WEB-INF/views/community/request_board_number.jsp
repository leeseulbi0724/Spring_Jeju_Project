<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>요청게시판 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/community/board_content.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<script>
	$(document).ready(function() {
		$(function(){
			  $("#ok").on("click", pass_check);
		    });
		  
		    function pass_check() {
		    		var pass = $(".number").val();		    
					var rid = "${rid}";
		            $.ajax({
		                type: "post",
		                url: "board_pass_check.do",
		                data: {pass:pass, rid:rid},
		                dataType: 'json',
		                success: function (result) {
		                    if (result) {
		                    	 location.replace("request_board_content.do?rid=${rid}");
		                    } else {
		                    	alert("비밀번호가 일치하지 않습니다");
		                    	$("#pass").val("").focus();
		                    }
		                },

		            });
			};
});
</script>
<jsp:include page="../header.jsp"></jsp:include>

<h3>요청게시판</h3>
<section>
<div class="content_box">
	<div class="box">
		<p>비밀번호 4자리를 입력해주세요</p>
		<input type="password" class="form-control input number">
		<button class="btn" id="ok">확인</button>
	</div>
</div>
</section>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>