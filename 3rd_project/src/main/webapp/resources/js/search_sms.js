$(document).ready(function() {
	
		/** 휴대폰 인증 문자 보내기 **/
		    $("#send").click(function() {       
		       var number = Math.floor(Math.random() * 100000) + 100000;
		          if(number>100000){ number = number - 10000; }
		          $("#text").val(number);      /* 난수로 생성된 인증번호를 hidden name : text 에 숨긴다 */  
		          var hp = $("#hp").val();
		 
		      /*     
		       var to = $("#to").val();
		       var name = $("#name").val();
		       var first = $("#first").val();
		       var last = $("#last").val();
		       var id = $("#id").val();   	 */	      
		    
/* 			 if ( name == "" || name == null ) {
		    	  alert("이름을 입력해주세요");
		    	  $("#name").focus();
		      } else if ( first == "" || first == null ) {
		    	  alert("주민등록번호를 입력해주세요");
		    	  $("#first").focus();
		      } else if ( last == "" || last == null ) {
		    	  alert("주민등록번호를 입력해주세요");
		    	  $("#last").focus();    	  
		      } else if ( id == "" || id == null ) {
		 	      alert("아이디를 입력해주세요");
		 	      $("#id").focus();
			  } else if ( to == "" || to == null ) {
		    	  alert("휴대폰 번호를 입력해주세요");
		    	  $("#to").focus();    	 
		      } else { */
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
		                   $("#num").focus();
		                   },
		                   error(){
		                      
		                   }
		                   
		                });  
		          } else if(con_test == false) {
		        	  /** 해당번호로 인증문자를 발송하시겠습니까? 에서 취소 눌렀을 때 **/
			        	/*   $("#to").val("");
			              $("#name").val("");
			              $("#first").val("");
			              $("#last").val("");
			              $(".id").val(""); */
		             }
/* 		         }  */  
		    })
		    $("#enterBtn").click(function() {   /* 내가 작성한 번호와 인증번호를 비교한다 */
		       var userNum = $("#num").val();       
		       var sysNum = $("#text").val();         
		       
		       if(userNum == null || userNum == ""){ 
		    	   alert("휴대폰으로 발송된 인증번호를 입력해주세요");
		    	   $("#num").focus();
		    	} else{     
		          if(userNum.trim() == sysNum.trim()){
		              alert("인증번호가 일치합니다");
		              $("#search").attr("disabled", false);
		           }
		           else {
		              alert("인증번호가 일치하지 않습니다");
		              $("#userNum").val("").focus();
		           }          
		       }
		    });
});