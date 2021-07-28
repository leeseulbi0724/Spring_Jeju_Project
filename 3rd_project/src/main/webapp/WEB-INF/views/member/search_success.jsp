<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/admincss/bootstrap.css">
	<link rel="stylesheet" href="css/member/custom.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<title>JEJU ISLAND</title>
	<style>
		a, button { margin:20px 0;	}
		.form-control { display:inline-block; width:500px; margin-bottom:5px; }
		
	</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
		<c:if test = "${type  eq 'id'}">
			<c:if test = "${!empty id }">
				<div style="text-align:center; width:1200px; height: 300px; margin:85px auto; margin-bottom:80px;" >
					<h3 style="width: 80px;">ID찾기</h3>
					<p style="margin:20px 0; font-size:18px; ">회원님의 아이디는 <strong>${id }</strong>입니다.</p>
					<a href="login.do" class="btn btn-primary">로그인 하기</a>
					<a href="passsearch.do" class="btn btn-primary">비밀번호 찾기</a>
				</div>
			</c:if>
			
			<c:if test = "${empty id }">
				<div style="text-align:center; width:1200px; height: 300px; margin:85px auto; margin-bottom:80px;" >
					<h3 style="width: 80px;">ID찾기</h3>
					<p style="margin:20px 0; font-size:18px; ">회원님의 아이디가 존재하지 않습니다.</p>
					<a href="join.do" class="btn btn-primary">회원가입 하기</a>
				</div>
			</c:if>
		</c:if>
		
		<c:if test = "${type  eq 'pass'}">
			<c:if test = "${!empty id }">
				<div style="text-align:center; width:1200px; height: 300px; margin:85px auto; margin-bottom:80px;" >
					<h3 style="width: 150px;">PASS 찾기</h3>
					<p style="margin:20px 0; font-size:18px; ">새로운 비밀번호로 변경이 가능합니다.</p>
					<form name="pass_update" action="pass_update_proc.do" method="post">
						<input type="hidden" name="id" value=${id }>
						<input type="password" placeholder="새 비밀번호" class="form-control" name="pass"><br>
						<input type="password" placeholder="새 비밀번호 확인" class="form-control"><br>
						<a href="login.do" class="btn btn-primary">돌아가기</a>
						<button type="submit" id="update" class="btn btn-primary">변경</button>
					</form>
				</div>
			</c:if>
			
			<c:if test = "${empty id }">
				<div style="text-align:center; width:1200px; height: 300px; margin:85px auto; margin-bottom:80px;" >
					<h3 style="width: 150px;">PASS 찾기</h3>
					<p style="margin:20px 0; font-size:18px; ">일치하는 정보가 없습니다.</p>
					<a href="join.do" class="btn btn-primary">회원가입 하기</a>
				</div>
			</c:if>
		</c:if>
		
		
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>