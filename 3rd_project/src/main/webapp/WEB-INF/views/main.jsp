<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
	<title>JEJU ISLAND</title>
	<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$(".img2").hover(function(){
		    	$(".img1").css('filter','brightness(50%)').css('transform','scale(1.1)');
		    }, function() { 
		        $(".img1").css('filter','brightness(100%)').css('transform','scale(1)');
		    });
		}); 
		
	</script>
	<style>
		*{
			margin:0; padding:0;	
		}
		.back_img1{
			overflow:hidden; 
			margin:0 auto;
		}
		.img1{
			width:100%;
			position:absolute;
			z-index:1; 
			transform:scale(1); 
			transition:transform 0.5s linear;
		}
		.img2{
			width:30%;
			position:relative;
			top:50%;
			left:50%;
			transform:translate(-50%, 85%);
			z-index:2;
		}
	</style>
</head>
<body>
	<div class="back_img1">
		<img src="http://localhost:9000/myjeju/images/index/main_1.png" class="img1">
	</div>
	<div>
		<a href="http://localhost:9000/myjeju/index.do"><img src="http://localhost:9000/myjeju/images/index/main_2.png" class="img2"></a>
	</div>
</body>
</html>