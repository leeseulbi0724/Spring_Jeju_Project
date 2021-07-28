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
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/community/board_write.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
</head>
<script>
	$(document).ready(function() {
		$(".select").change(function() {
			if ($(this).val() == "house") {
				$("#title").val("숙소 요청합니다!");
				$("#category").val($(this).val());
			} else if ($(this).val() == "food") {
				$("#title").val("맛집 요청합니다!");
				$("#category").val($(this).val());
			} else if ($(this).val() == "travel") {
				$("#title").val("여행지 요청합니다!");
				$("#category").val($(this).val());
			} else if ($(this).val() == "") {
				$("#title").val("");
				$("#category").val($(this).val());
			}
		});
		
		$("#write").click(function() {
			if ($(".select").val() == "") {
				alert("카테고리를 선택해주세요");
			} else if ($("#title").val() == "") {
				alert("제목을 입력해주세요");
				$("#title").focus();
			} else if ($("#content").val() == "") {
				alert("내용을 입력해주세요");
				$("#content").focus();
			} else if ($("#number").val() == "") {
				alert("비밀번호를 입력해주세요");
				$("#number").focus();
			} else {
				request_write.submit();
			}
		});
	});
</script>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<h3>요청게시판</h3>
<div class="container">
	<form autocomplete="off"  name="request_write" action="request_write_proc.do" method="post" enctype= "multipart/form-data">
		<select class="form-select select">
			<option value="">선택
			<option value="house">숙소
			<option value="food">맛집
			<option value="travel">여행지
		</select>
		<input type="hidden" name="rcategory" id="category">
		<input type="text" class="form-control input" name="rtitle" placeholder="제목" id="title">
		<textarea name="rcontent" style="resize: none;" class="form-control textarea" placeholder="내용" id="rcontent"></textarea>
		<input type="file" class="form-control input" name="file1">
		<input type="password" name="rnumber" class="form-control input" id="number" placeholder="'비밀번호 4자리를 입력해주세요">
	<a class="btn" href="request_board.do" id="list">목록</a>
	<button type="button" class="btn"  id="write">등록</button>
	</form>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>