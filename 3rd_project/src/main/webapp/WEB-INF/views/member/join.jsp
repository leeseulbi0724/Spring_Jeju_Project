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
	<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="http://localhost:9000/myjeju/js/address.js"></script>
	<script type="text/javascript">		
		$(function() {
			$("#id_check").on("click", check);
		})
		
		function check() {
			var id = $("#userID").val();
			 $.ajax({
                url:"id_check_proc.do",
                type:"post",
                data: {
             	 	id: id,
                }, 
                dataType: "json",
              success:function(result){
               		if (result) {
                		alert("사용하실 수 있는 아이디입니다");
                		$("#userPassword1").focus();
                		$("#id_check").attr("name", "on");
                	} else {
                		alert("존재하는 아이디입니다.")
                		$("#userID").val("").focus();
                	}
                },
                error(){
                   
                }
                
             });
		}
		
		$(document).ready(function() {
			$(".email li").click(function() {
				$("#userEmail2").val($(this).text());
			});
			
			$(".hp li").click(function() {				
				var hp = $(".hp1").text($(this).text())
				$("#hp1").val($(".hp1").text());
			});
			
			$(".join").click(function() {
				if ($("#userID").val() == "") {
					alert("아이디를 입력해주세요");
					$("#userID").focus();
				} else if ($("#userPassword1").val() == "") {
					alert("비밀번호를 입력해주세요");
					$("#userPassword1").focus();
				} else if ($("#userPassword2").val() == "") {
					alert("비밀번호 확인을 입력해주세요");
					$("#userPassword2").focus();
				} else if ($("#userName").val() == "") {
					alert("이름을 입력해주세요");
					$("#userName").focus();
				} else if ($("#userBirth").val() == "") {
					alert("생년월일을 입력해주세요");
					$("#userBirth").focus();
				} else if ($("#userEmail1").val() == "") {
					alert("이메일을 입력해주세요");
					$("#userEmail1").focus();
				} else if ($("#userEmail2").val() == "") {
					alert("이메일 주소를 입력해주세요");
					$("#userEmail2").focus();
				} else if ( $("#hp1").val() == "" ) {
					alert("휴대폰 번호를 선택해주세요");
					$("#hp1").focus();
				} else if ( $("#hp2").val() == "" ) {
					alert("휴대폰 번호를 입력해주세요");
					$("#hp2").focus();
				} else if ( $("#hp3").val() == "" ) {
					alert("휴대폰 번호를 입력해주세요");
					$("#hp3").focus();
				} else if ($("#addr1").val() == "" ) {
					alert("주소를 입력해주세요");
					$("#addr1").focus();
				} else if ($("#addr2").val() == "" ) {
					alert("상세 주소를 입력해주세요");
					$("#addr2").focus();
				} else if ($("#id_check").attr("name") == "off") {
					 alert("아이디 중복확인 버튼을 클릭해주세요");
					 $("#id_check").focus();
				}  else if ($("#enterBtn").attr("name") == "off") {
					alert("휴대폰 인증버튼을 클릭해주세요");
					$("#enterBtn").focus();
				} else {
					member_join.submit();			
				}
				
			});
			
			/* 비밀번호 - 비밀번호 확인 체크 */
			$("#userPassword2").blur(function() {
				if ($("#userPassword1").val() != $("#userPassword2").val() ) {
					$(".pass_msg").text("비밀번호가 동일하지 않습니다.").css("color","red");
					$("#userPassword2").val("").focus();
				} else {
					$(".pass_msg").text("비밀번호가 동일합니다.").css("color","blue");
				}
			});
			
			/* 생년월일 - 6자리 확인 */
			$("#userBirth").blur(function() {
				if ( $("#userBirth").val() != "" && $("#userBirth").val().length != "6") {
					$(".birth_msg").text("생년월일 형식에 맞지 않습니다.").css("color","red");
					$("#userBirth").val("").focus();
				} else if ( $("#userBirth").val() != "" && $("#userBirth").val().length == "6" ) {
					$(".birth_msg").text("");
				}
			});

			
			/** 휴대폰 인증 문자 보내기 **/
		    $("#send").click(function() {       
		       var number = Math.floor(Math.random() * 100000) + 100000;
		          if(number>100000){ number = number - 10000; }
		          $("#text").val(number);      /* 난수로 생성된 인증번호를 hidden name : text 에 숨긴다 */  
		          var hp = $("#hp1").val()+$("#hp2").val()+$("#hp3").val();
		          alert(hp);
		 
		          /** 유효성 체크 **/
		        
		         if ( $("#hp1").val() == "" ) {
					alert("휴대폰 번호를 선택해주세요");
					$("#hp1").focus();
				} else if ( $("#hp2").val() == "" ) {
					alert("휴대폰 번호를 입력해주세요");
					$("#hp2").focus();
				} else if ( $("#hp3").val() == "" ) {
					alert("휴대폰 번호를 입력해주세요");
					$("#hp3").focus();
       			} else {
		      		var con_test = confirm("해당번호로 인증문자를 발송하시겠습니까?");   /* 문자를 보낼껀지 물어본다 */          
		          	if(con_test == true){   
		               $.ajax({
		                   url:"sendSms.do",
		                   type:"post",
		                   data: {
		                	 	to: hp,
		                        text: $("#text").val()
		                   }, 
		                 success:function(){
		                   alert("해당 휴대폰으로 인증번호를 발송했습니다");
		                   $("#userNum").focus();
		                   },
		                   error(){
		                      
		                   }
		                   
		                });  
		          } else if(con_test == false) {
		        	  /** 해당번호로 인증문자를 발송하시겠습니까?에서 취소 눌렀을 때 **/
		             }
				 }
		    })
		    $("#enterBtn").click(function() {   /* 내가 작성한 번호와 인증번호를 비교한다 */
		       var userNum = $("#userNum").val();       
		       var sysNum = $("#text").val();         
		       
		       if(userNum == null || userNum == ""){ 
		    	   alert("휴대폰으로 발송된 인증번호를 입력해주세요");
		    	   $("#userNum").focus();
		    	} else{     
		          if(userNum.trim() == sysNum.trim()){
		              alert("인증번호가 일치합니다");
		              $("#enterBtn").attr("name", "on");
		           }
		           else {
		              alert("인증번호가 일치하지 않습니다");
		              $("#userNum").val("").focus();
		           }          
		       }
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
	<div style="text-align:center; width:1200px; height: 50px; margin:75px auto; margin-bottom:70px;" >
		<h3 style="width: 100px;">회원가입</h3>
		</div>
		<div class="container" style="margin-bottom:100px;">
			<form autocomplete="off" name="member_join" method="post" action="join_proc.do">
				<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
					<tbody>	
						<tr>
							<td style="width: 110px;"><h5>아이디</h5></td>
							<td><input class="form-control" type="text" id="userID" name="id" maxlength="20" placeholder="아이디를 입력하세요."></td>
							<td style="width: 110px;">
								<button class="btn btn-primary" name="off" id="id_check" type="button" style="background-color:#4fa9de; border-color:#4fa9de;">중복체크</button>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>비밀번호</h5></td>
							<td colspan="2"><input onkeyup="passwordCheckFunction();" class="form-control" id="userPassword1" type="password" name="pass" maxlength="20" placeholder="비밀번호를 입력하세요."></td>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>비밀번호 확인</h5></td>
							<td colspan="2">
								<input onkeyup="passwordCheckFunction();" class="form-control" id="userPassword2" type="password" name="cpass" maxlength="20" placeholder="비밀번호 확인을 입력하세요.">
								<div class="pass_msg" style="text-align:left;"></div>
							</td>						`	
						</tr>
						<tr>
							<td style="width: 110px;"><h5>이름</h5></td>
							<td colspan="2"><input class="form-control" id="userName" type="text"  name="name" maxlength="20" placeholder="이름를 입력하세요."></td>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>생년월일</h5></td>
							<td colspan="2">
								<input class="form-control" id="userBirth" type="text"  name="birth" maxlength="20" placeholder="생년월일 6자리를 입력하세요.">
								<div class="birth_msg" style="text-align:left;"></div>
							</td>
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
							<td>
							<div class="dropdown pull-left" style="width:10%;">
							  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" style="width:100%;">							    
							    <span id="choosevalue" class="hp1">선택</span>
							    <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu hp" aria-labelledby="dropdownMenu1">
							    <li><a>010</a></li>
							    <li><a>011</a></li>
							    <li><a>017</a></li>
							  </ul>
							</div>
							<input name="hp1" id="hp1" type="hidden">
							<span class="pull-left" style="background-color: rgba(0,0,0,0); font-color:black; font-size: 20px; margin: 0 10px;">-</span>
							<input class="form-control pull-left" id="hp2" type="text" name="hp2" maxlength="20" placeholder="핸드폰번호를 입력하세요." style="width:41.85%;">
							<span class="pull-left" style="background-color: rgba(0,0,0,0); font-color:black; font-size: 20px; margin: 0 10px;">-</span>
							<input class="form-control pull-left" id="hp3" type="text" name="hp3" maxlength="20" placeholder="핸드폰번호를 입력하세요." style="width:41.85%;">
							<td style="width: 110px;"><button class="btn btn-primary" type="button" style="background-color:#4fa9de; border-color:#4fa9de;" id="send">전송</button></td>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>인증번호</h5></td>
							<td><input class="form-control pull-left" placeholder="인증번호 6자리를 입력하세요." id="userNum" ></td>
							<td style="width: 110px;"><button class="btn btn-primary" type="button" style="background-color:#4fa9de; border-color:#4fa9de;" id="enterBtn" name="off">확인</button>
							<input type="hidden" name="text" id="text"></td>
						</tr>
						<tr>
							<td style="width: 110px;"><h5>주소</h5></td>
							<td>
							<input class="form-control pull-left" id="addr1" type="text" name="addr1" maxlength="20" placeholder="주소를 입력하세요." style="width:100%; margin-bottom:5px;">
							<input class="form-control pull-left" id="addr2" type="text" name="addr2" maxlength="20" placeholder="상세주소를 입력하세요." style="width:100%;">
							</td>							
							<td style="width: 110px;"><button class="btn btn-primary" onclick="addr()" type="button" style="margin:17px 0;background-color:#4fa9de; border-color:#4fa9de;">주소검색</button></td>
						</tr>
						<tr>
							<td Style="text-align: left;" colspan="3"><h5 style="color: red;" id="passwordCheckMessage"></h5><input class="btn btn-primary pull-right join" type="button" value="등록" style="background-color:#4fa9de; border-color:#4fa9de;"></td>
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