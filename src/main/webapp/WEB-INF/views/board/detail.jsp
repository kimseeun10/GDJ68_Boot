<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/headCSS.jsp"></c:import>
</head>
<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>
		
		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
        	<div id="content">
        		<c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
        		
        		<div class="container-fluid">
        		<!-- page 실제 내용 -->
        		<h1>Detail Page</h1>
					
		<div class="text-conter">
		<table class="table">
		
		    <tr>
		        <th>Title</th>
		        <td>${vo.boardTitle}</td>
		    </tr>
		    <tr>
		        <th>Write</th>
		        <td>${vo.boardWriter}</td>
		    </tr>
		    <tr>
		        <th>Date</th>
		        <td>${vo.boardDate}</td>
		    </tr>
	 
		</table>
		<div class="mb-3">
			<label for="boardContents" class="form-label"></label>
			<textarea class="form-control" name="boardContents" id="boardContents" readonly="readonly" style="height: 200px;">${vo.boardContents}</textarea>
		 </div>
	</div>
	<br><br>
        		<a class="btn btn-outline-secondary" href="./update?boardNo=${vo.boardNo}">수정</a>
				<a class="btn btn-danger" href="./delete?boardNo=${vo.boardNo}">삭제</a>    		
			</div>
        		</div>
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        	</div>
        </div>

	
<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>