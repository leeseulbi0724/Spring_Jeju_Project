<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "javax.servlet.jsp.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageNumber" value='${pageNumber}' />
<c:set var="targetpage" value='${targetpage}' />
<c:set var="search" value='${search}' />
<c:set var="search_text" value='${search_text}' />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="test/html; charset=UTF-8">
	<meta name ="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/admincss/bootstrap.css">
	<link rel="stylesheet" href="css/admincss/custom.css">
	<link rel="stylesheet" href="css/admincss/setupforcounsel.css">
	<title>Myjeju 관리자 메뉴</title>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript"></script>
</head>
<style>
	.write { 
		background-color:rgb(20,86,184); 
		color:white; 
		padding:5px 10px;
		border-radius:4px;
		float:right;
		margin:10px 0;
	}
</style>
<body>
	<nav class="navbar navbar-default">
		<div class ="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expeanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://localhost:9000/myjeju/adminindex.do">Myjeju 관리자 메뉴</a>
		</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="http://localhost:9000/myjeju/adminindex.do">메인</a>
					<li><a href="adnotice.do">공지사항관리</a></li>
					<li><a href="adboard.do">게시판관리</a></li>
					<li><a href="adrequest.do">요청관리</a></li>
					<li><a href="admember.do">회원관리<span id="unread" class="label label-info"></span></a></li>
					<li><a href="adhouse.do">숙소관리</a></li>
					<li><a href="adfood.do">음식점관리</a></li>
					<li class="active"><a href="adcafe.do">카페관리</a></li>
					<li><a href="adtravel.do">여행지관리</a></li>
					<li><a href="adstore.do">상품관리</a>
				</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="http://localhost:9000/myjeju/index.do">메인으로</a>
				</li>
			</ul>						
			</div>
		</nav>
			<!-- content -->
	<div class = "content_setup_faq">
	
		<section class = "section_setup_faq">
			<div></div>
			<div> 카페관리 </div>
			<div></div>
		</section>
	
		<section class ="setup_faq_list">
			<a href="adcafe_write.do" class="write" >카페 추가</a>
			<table class = "content_layout_setup_faq">
				<tr>
					<th> 번호 </th>
					<th> 아이디 </th>
					<th> 이름 </th>
					<th> 업체번호 </th>
					<th> 좋아요 </th>
				</tr>
			 	<c:forEach var="vo" items="${list}">
				<tr>
					<td>${vo.rno}</td>
					<td>${vo.caid}</td>
					<td><a href="adcafe_content.do?caid=${vo.caid}">${vo.ca_name}</a></td>
					<td>${vo.ca_hp}</td>
					<td>${vo.ca_like}</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="6">
						<ul class = "pagination" style="margin: 0 auto;">
					<%	
						String pageNumber = String.valueOf(pageContext.getAttribute("pageNumber"));
						int startPage = (Integer.parseInt(pageNumber) / 10) *10 + 1; 
						if(Integer.parseInt(pageNumber) % 10 == 0) startPage -= 10;
						int targetPage = Integer.parseInt(String.valueOf(pageContext.getAttribute("targetpage")));
						String search = String.valueOf(pageContext.getAttribute("search"));
						String search_text = String.valueOf(pageContext.getAttribute("search_text"));
						if(startPage != 1) {
					%>
						<li><a href="adcafe.do?pnum=<%= startPage -1 %>&search=<%= search %>&search_text=<%= search_text %>"><span><</span></a></li>
					<%
						} else {
					%>
						<li><span style="color: gray;"><</span></li>
					<%
						}
						for(int i = startPage; i < Integer.parseInt(pageNumber); i++) {
					%>
						<li><a href="adcafe.do?pnum=<%= i %>&search=<%= search %>&search_text=<%= search_text %>" style="color: #000000;"><%= i %></a></li>
					<%
						}
					%>
						<li class="active_page" ><a href="adcafe.do?pnum=<%= pageNumber %>&search=<%= search %>&search_text=<%= search_text %>" style="background-color: #337ab7;color: #ffffff;"><%= pageNumber %></a></li>
					<%
						for(int i = Integer.parseInt(pageNumber) + 1; i <= targetPage + Integer.parseInt(pageNumber); i++) {
							if(i < startPage +10) {
					%>
						<li><a href="adcafe.do?pnum=<%= i %>&search=<%= search %>&search_text=<%= search_text %>" style="color: #000000;"><%= i %></a></li>
					<%
							}
						}
						if(targetPage + Integer.parseInt(pageNumber) > startPage + 9){
					%>
						<li><a href="adcafe.do?pnum=<%= startPage + 10 %>&search=<%= search %>&search_text=<%= search_text %>" style= "color: #000000;"><span>></span></a></li>
					<%
						} else {
					%>
						<li><span style="color: gray;">></span></li>
					<%		
						}
					%>
						</ul>	
					</td>
				</tr>
			</table>
		</section>
		<section class = "setup_faq_search">
			<form name = "setup_counsel_form" action ="adcafe.do" method = "post">
				<select class = "search" name = "search" style = "width: 70px; height: 27px">
					<option value = "name">카페명</option>
					<option value = "addr">지역명</option>
				</select>
				<input type = "text" name = "search_text" class = "search_text" style = "width: 300px; margin: 0 10px;">
				<button type = "submit" class = "btn_search">검색</button>
			</form>
		</section>
	</div>
</body>
</html>