<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image⁄x-icon" href="http://localhost:9000/myjeju/images/index/icon.png">
<title>요청게시판 | JEJU ISLAND</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/index.css">
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/community/board.css">
<script src="http://localhost:9000/myjeju/js/jquery-3.6.0.min.js"></script>
<script src="http://localhost:9000/myjeju/js/am-pagination.js"></script>
<link rel="stylesheet" href="http://localhost:9000/myjeju/css/am-pagination.css">
</head>
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
           $(location).attr('href', "http://localhost:9000/myjeju/request_board.do?rpage="+e.page);         
    });
	
});
</script>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<h3>요청게시판</h3>
		<div class="container">
		<div class="search">
		<a class="btn btn-primary"  id="write" href="request_board_write.do">글쓰기</a>			
		</div>
		<table class="table " >
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var = "vo"  items="${list}">
					<tr>
						<td>${vo.rno }</td>
						<td>
							<img src="http://localhost:9000/myjeju/images/community/sky_lock.png" width=15 height=15 style="margin-left:10px">
							<a href="http://localhost:9000/myjeju/request_board_number.do?rid=${vo.rid }">${vo.rtitle }</a>
						</td>
						<td>${vo.id }</td>
						<td>${vo.rdate }</td>
					</tr>							
				</c:forEach>
			</tbody>
		</table>		
		<div id="ampaginationsm"></div>	
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>