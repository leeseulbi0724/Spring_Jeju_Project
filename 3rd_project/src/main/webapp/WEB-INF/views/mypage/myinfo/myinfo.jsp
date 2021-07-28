<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>기본정보 수정 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/mypage/myinfo.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://localhost:9000/myjeju/js/address.js"></script>
</head>
<script  type="text/javascript">
	$(document).ready(function() {
		$(".email_select").change(function() {
			$("#email2").val($(".email_select").val());
		});
		
		$(".hp_select").change(function() {
			$("#hp1").val($(".hp_select").val());
		});
	});
	
	  $(function(){
		  $("#ok").on("click", update);
	    });
	  
	    function update() {
	            var form1 = $("#form").serialize();

	            console.log(form1);
	            $.ajax({
	                type: "post",
	                url: "info_update.do",
	                data: form1,
	                dataType: 'json',
	                success: function (result) {
	                    if (result) {
	                    	alert("정보 수정이 완료되었습니다");	                    	
	                    } else {
	                    	alert("정보 수정이 실패되었습니다");	                    	
	                    }
	                    location.reload();
	                },

	            });
	    };
</script>
<body>
<jsp:include page="../../header.jsp"></jsp:include> 

<section>
	<div><h3>기본정보 수정</h3></div>
	<div class="center">	
	<form name = "info_update" method="post" id="form">
	<table class="table">
		<tr>
			<th>아이디</th>
			<th>${vo.id }</th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<th><a class="btn btn-outline-dark" href="myinfo_pass.do">비밀번호 변경</a></th>
		</tr>
		<tr>
			<th>이름</th>
			<th>${vo.name }</th>
		</tr>
		<tr>
			<th>생년월일</th>
			<th>${vo.birth }</th>
		</tr>
		<tr>
			<th>이메일</th>
			<th>
				<input type="text" class="form-control" value="${vo.email1}" name="email1">@<input type="text" id="email2" name="email2" class="form-control" value="${vo.email2}">
				<select class="form-select email_select">
					<option value="">직접입력
					<option value="naver.com">네이버
					<option value="gamil.com">구글
					<option value="daum.net">다음
				</select>
			</th>
		</tr>
		<tr>
			<th>휴대폰번호</th>
			<th>
				<select class="form-select hp_select">
					<c:if test = "${vo.hp1 eq '010'}">
						<option value="선택">선택
						<option value="010" selected>010
						<option value="011">011
						<option value="017">017
					</c:if>
					<c:if test = "${vo.hp1 eq '011'}">
						<option value="선택">선택
						<option value="010" >010
						<option value="011" selected>011
						<option value="017">017
					</c:if>
					<c:if test = "${vo.hp1 eq '017'}">
						<option value="선택">선택
						<option value="010" >010
						<option value="011" >011
						<option value="017" selected>017
					</c:if>
				</select>-
				<input type="hidden" id="hp1" name="hp1" value="${vo.hp1 }">
				<input type="text" class="form-control" name="hp2" value="${vo.hp2 }">-
				<input type="text" class="form-control" name="hp3" value="${vo.hp3 }">
			</th>
		</tr>
		<tr>
			<th>주소</th>
			<th>
				<input type="text" class="form-control addr" name="addr1" value="${vo.addr1 }" id="addr1">
				<input type="text" class="form-control addr" name="addr2" value="${vo.addr2 }" id="addr2">
				<button type="button" class="btn btn-outline-dark"  onclick="addr()" >주소 검색</button>
			</th>
		</tr>
	</table>
	<a class="btn btn-outline-secondary" href="mypage.do" id="cancle">취소</a>
	<button class="btn" id="ok" type="button">수정</button>
	</form>
	</div>
</section>

<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>