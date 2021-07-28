<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>자유게시판 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/community/board.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/myjeju/js/am-pagination.js"></script>
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/am-pagination.css">
 <script>
$(document).ready(function(){
	
	var pager = jQuery('#ampaginationsm').pagination({
	
	    maxSize: 7,	    		// max page size
	    totals: ${dbcount},	// total pages	
	    page: ${rpage},		// initial page	
	    pageSize: ${pagesize},			// max number items per page
	
	    // custom labels		
	    lastText: '&raquo;&raquo;', 		
	    firstText: '&laquo;&laquo;',		
	    prevText: '&laquo;',		
	    nextText: '&raquo;',
			     
	    btnSize:'sm'	// 'sm'  or 'lg'		
	});
	
	jQuery('#ampaginationsm').on('am.pagination.change',function(e){
		   jQuery('.showlabelsm').text('The selected page no: '+e.page);
           $(location).attr('href', "http://localhost:9000/myjeju/free_board.do?rpage="+e.page);         
    });
	
	  $("#search").click (function() {	
	        var value = $("#search_input").val().toLowerCase();
	        $(".free_tr").hide();
	        var select = $("#select").val();
		 	if (select == "user") {
		 		var value = $(".tbody td:nth-child(5n+3):contains('" + value + "') ");
		 		$(value).parent().show();
		 	} else if ( select == "title") {
		 		var value = $(".tbody td:nth-child(5n+2):contains('" + value + "') ");
		 		$(value).parent().show();
		 	} 
	        
	    });
	
});
</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<h3>자유게시판</h3>
		<div class="container">
		<div class="search">
		<a class="btn btn-primary"  id="write" href="free_board_write.do">글쓰기</a>
			<select class="form-select" id="select">
				<option value="user">작성자</option>
				<option value="title">제목</option>
			</select>
			<input type="text" class="form-control" id="search_input">
			<a class="btn btn-outline-secondary" id="search" >검색</a>			
		</div>
		<table class="table" >
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody class="notice_tbody" >			
			<c:forEach var = "vo"  items="${nlist}">
				<tr>
					<td><a class="noticeBtn">공지</a></td>
					<td><a href="notice_content.do?nid=${vo.nid }" class="notice_title">${vo.ntitle }</a></td>
					<td>관리자</td>
					<td>${vo.ndate }</td>
					<td>${vo.ncount }</td>
				</tr>			
			</c:forEach>					
			</tbody>
			<tbody class="tbody">
				<c:forEach var = "vo"  items="${list}">
				<tr class="free_tr">
					<td>${vo.rno }</td>
					<td><a href="http://localhost:9000/myjeju/free_board_content.do?fid=${vo.fid }">${vo.ftitle }</a></td>
					<td>${vo.id }</td>
					<td>${vo.fdate }</td>
					<td>${vo.fhit }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>	
		<div id="ampaginationsm"></div>	
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>