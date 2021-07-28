<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>자유게시판 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<style>
		body { text-align:center;	}
	h3 { border-bottom:5px solid #4fa9de; display:inline-block; font-size:20px; margin:50px 0; font-weight:bold; }
	
	.container { width:825px; }		
	
	.select { width:100px; display:inline-block; font-size:13px; float:left; margin-bottom:5px; }
	
	.input { width:800px; margin-bottom:5px; display:inline-block; font-size:13px; }
	.textarea { width:800px; height:400px; margin-bottom:5px; display:inline-block; font-size:13px; }
	
	#list, #write { background-color:#4fa9de; border:none; color:white; font-size:13px; }
	#write { float:right; }
	#list { float:left; margin-bottom:50px; }
	#list:hover, #write:hover { background-color:rgb(23,86,123); }
	
	.file_name {
		background-color:white;
		position:absolute;
		margin-top:5px;
		width:500px;
		text-align:left;
		left:450px;
		font-size:14px;
	}
</style>
</head>
<script>
	$(document).ready(function() {
		$("#write").click(function() {
			if ($("#title").val() == "") {
				alert("제목을 입력해주세요");
				$("#title").focus();
			} else if ($("#content").val() == "") {
				alert("내용을 입력해주세요");
				$("#content").focus();
			} else {
				free_update.submit();
			}
		});
		
		$("#file_input").change(function() {
			if (window.FileReader) {
				var filename = $(this)[0].files[0].name;
				$(".file_name").text("").text(filename);
			}
		});
	});
</script>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<h3>자유게시판</h3>
<div class="container">
	<form autocomplete="off" name="free_update" action="free_update_proc.do" method="post" enctype="multipart/form-data">
		<input type="text" class="form-control input" name="ftitle" value="${vo.ftitle }" id="title">
		<textarea name="fcontent" style="resize: none;" class="form-control textarea" id="content">${vo.fcontent }</textarea>
		<input type="file" class="form-control input" name="file1" id="file_input">
		<input type="hidden" name="fid" value="${vo.fid }">
		<span class="file_name">${vo.ffile }</span>
	<a class="btn" href="free_board.do" id="list">목록</a>
	<button type="button" class="btn"  id="write">등록</button>
	</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>