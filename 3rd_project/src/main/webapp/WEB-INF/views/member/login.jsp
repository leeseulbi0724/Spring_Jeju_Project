<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="test/html; charset=UTF-8">
	<meta name ="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/admincss/bootstrap.css">
	<link rel="stylesheet" href="css/member/custom.css">
	<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<title>JEJU ISLAND</title>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</head>
<script>
	$(document).ready(function() {
		$("input[value='아이디찾기']").click(function() {
			location.replace("idsearch.do");
		});
		
		$("input[value='비밀번호찾기']").click(function() {
			location.replace("passsearch.do");
		});
		
		$("#login").click(function() {
			if ($("#id").val() == "") {
				alert("아이디를 입력해주세요");
				$("#id").focus();
			} else if ($("#pass").val() == "") {
				alert("비밀번호를 입력해주세요");
				$("#pass").focus();
			} else{
				 var form1 = $("#form").serialize();
		            console.log(form1);
		            $.ajax({
		                type: "post",
		                url: "login_proc.do",
		                data: form1,
		                dataType: 'json',
		                success: function (result) {
		                    if (result) {
		                    	 location.replace("index.do");
		                    } else {
		                    	alert("아이디 또는 비밀번호가 틀립니다");	                    	
		                    }
		                },

		            });
			}
		})
		
		// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
	    var key = getCookie("key");
	    $("#id").val(key); 
	     
	    if($("#id").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	        $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
	            setCookie("key", $("#id").val(), 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("key");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("#id").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            setCookie("key", $("#id").val(), 7); // 7일 동안 쿠키 보관
	        }
	    });
	});
	function setCookie(cookieName, value, exdays){
	    var exdate = new Date();
	    exdate.setDate(exdate.getDate() + exdays);
	    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	    document.cookie = cookieName + "=" + cookieValue;
	}
	 
	function deleteCookie(cookieName){
	    var expireDate = new Date();
	    expireDate.setDate(expireDate.getDate() - 1);
	    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}
	 
	function getCookie(cookieName) {
	    cookieName = cookieName + '=';
	    var cookieData = document.cookie;
	    var start = cookieData.indexOf(cookieName);
	    var cookieValue = '';
	    if(start != -1){
	        start += cookieName.length;
	        var end = cookieData.indexOf(';', start);
	        if(end == -1)end = cookieData.length;
	        cookieValue = cookieData.substring(start, end);
	    }
	    return unescape(cookieValue);
	}

</script>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		if(userID != null) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "현재 로그인이 되어 있는 상태입니다.");
			response.sendRedirect("index.jsp");
			return;
		}
	%>
	
	<jsp:include page="../header.jsp"></jsp:include>
		<div style="text-align:center; width:1200px; height: 50px; margin:75px auto; margin-bottom:10px;" >
		<h3 style="width: 80px;">로그인</h3>
		</div>
		<div class="container" style="margin: 90px auto; margin-top:60px;">
			<form id="form" name="member_login" method="post" style="margin-bottom:120px; border:1px solid rgba(0,0,0,0.1); text-align:center; padding:65px; padding-bottom:40px; width:92%; margin:0 auto;">
				<table class="table" style="text-align: center; width:55%; display:inline-block; margin-bottom:0;">
					<tbody>
						<tr>
							<td style="border:none; width: 400px;"><input class="form-control" type="text" name="id" maxlength="20" placeholder="아이디 입력" id="id" style="height: 50px; border-radius:0;"></td>
							<td rowspan=2 style="border:none;"><input class="btn btn-primary pull-right" type="button" value="로그인"  id="login" style="background-color:#4fa9de; border-color:#4fa9de; font-size: 20px; height: 115px; width:120px; border-radius:0;"></td>
						</tr>
						<tr>
							<td style="border:none;"><input class="form-control" type="password" name="pass" maxlength="20" id="pass" placeholder="패스워드 입력" style="height: 50px; border-radius:0;"></td>
						</tr>
						<tr>
							<td style="text-align : left; border:none;" colspan="2">
							<input type="checkbox" name="idsave" value ="true" id="idSaveCheck">
							<span style="margin-right: 20px; position:relative; top:-1px; font-size: 14px;">아이디 저장</span>
							<!-- <input type="checkbox" name="loginck" value ="true">
							<span style="margin-right: 20px; position:relative; top:-1px; font-size: 14px;">자동로그인</span>		  		 -->			
							<input class="btn btn-primary pull-right" type="button" value="비밀번호찾기" style="background-color:rgba(0,0,0,0); border-color:rgba(0,0,0,0); color:black; position:relative; top:-6px; padding-right:0;">
							<div class="pull-right" style="height:15px; width:1px; background-color:rgba(0,0,0,0.3); display:inline-block; margin-top:10px; margin-right:3px; position:relative; top:-6px;"></div>
							<input class="btn btn-primary pull-right" type="button" value="아이디찾기"  style="margin-right: 7px; background-color:rgba(0,0,0,0); border-color:rgba(0,0,0,0); color:black; position:relative; top:-6px;">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<form method="get" action="join.do" style="margin-bottom:120px;text-align:center; padding:65px; padding-top:40px; width:92%; margin:0 auto;">
			<span style="display:inline-block; width:100%; margin-bottom: 20px; font-size:15px; color: rgba(0,0,0,0.5);">아직 계정이 없으신가요?</span>
			<input class="btn btn-primary" type="submit" value="회원가입" style="display:inline-block; background-color:rgba(0,0,0,0.8); border-color:rgba(0,0,0,0.8); font-size: 20px; height: 60px; width:54%; border-radius:0; margin: 0 auto;">
			</form>
		</div>
		<%
			String messageContent = null;
			if(session.getAttribute("messageContent") != null) {
				messageContent = (String) session.getAttribute("messageContent");
			}
			String messageType = null;
			if(session.getAttribute("messageType") != null) {
				messageType = (String) session.getAttribute("messageType");
			}
			if(messageContent != null) {
		%>
		<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div class="modal-content <% if(messageType.equals("오류 메시지")) out.println("panel-warning"); else out.println("panel-success"); %>">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times</span>
								<span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								<%= messageType %>
							</h4>
						</div>
						<div class="modal-body">
								<%= messageContent %>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<script>
			$('#messageModal').modal("show");
		</script>
		<%
			session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
			}
		%>
		<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div id="checkType" class="modal-content panel-info">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times</span>
								<span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								확인 메시지
							</h4>
						</div>
						<div id="checkMessage" class="modal-body">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>