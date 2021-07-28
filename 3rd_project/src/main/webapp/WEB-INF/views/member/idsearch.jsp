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
	<script src="http://localhost:9000/myjeju/js/search_sms.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$(".email li").click(function() {
			$("#userEmail2").val($(this).text());
		});
	});
	</script>
</head>
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
		<div style="text-align:center; width:1200px; height: 50px; margin:85px auto; margin-bottom:80px;" >
		<h3 style="width: 80px;">ID찾기</h3>
		</div>
		<div class="container" style="margin: 100px auto;">
			<form method="post" action="id_search_proc.do" style="margin-bottom:110px;">
				<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
					<tbody>
						<tr>
							<td style="width: 110px;"><h5>이름</h5></td>
							<td colspan="2"><input class="form-control" id="userName" type="text"  name="name" maxlength="20" placeholder="이름를 입력하세요."></td>
						</tr>
					<tr>
							<td style="width: 110px;"><h5>이메일</h5></td>
							<td colspan="2">
							<input class="form-control pull-left" id="userEmail1" type="text" name="email1" maxlength="20" placeholder="이메일을 입력하세요." style="width:42%;">
							<span class="pull-left" style="background-color: rgba(0,0,0,0); font-color:black; font-size: 17px; margin: 4px 10px;">@</span>
							<input class="form-control pull-left" id="userEmail2" type="text" name="email2" maxlength="20" placeholder="이메일주소을 입력하세요." style="width:42%;">
							<div class="dropdown pull-left" style="width:10%; margin-left:10px;">
							  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="width:100%;">
							    <span id="choosevalue">선택</span>
							    <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu email" aria-labelledby="dropdownMenu1">
							    <li><a>naver.com</a></li>
							    <li><a>gmail.com</a></li>
							    <li><a>daum.net</a></li>
							    <li role="separator" class="divider"></li>
							    <li><a>직접입력</a></li>
							  </ul>
							</div>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>휴대폰번호</h5></td>
							<td colspan="2"><input class="form-control" id="hp" type="text"  name="hp" maxlength="20" style="width:91%; display:inline-block; float:left; "placeholder="'-'를 포함한 휴대폰번호를 입력하세요.">
							<button type="button" class="btn btn-primary pull-right" id="send">인증번호</button>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>인증번호</h5></td>
							<td colspan="2"><input class="form-control" id="num" type="text"  name="hp_num" maxlength="20" style="width:94%; display:inline-block; float:left; "placeholder="인증번호 6자리를 입력하세요.">
							<button type="button" class="btn btn-primary pull-right" id="enterBtn">확인</button>
							<input type="hidden" name="text" id="text"></td>
						</tr>
						<tr>
							<td style="text-align : left" colspan="2">
							<input class="btn btn-primary pull-right" type="submit" value="아이디찾기"  id="search" style="margin-right: 10px; background-color:#4fa9de; border-color:#4fa9de;" disabled>
							</td>
						</tr>
					</tbody>
				</table>
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