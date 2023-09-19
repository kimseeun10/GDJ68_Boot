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
	
		<thead>
			<th scope="col">NO</th>
			<th scope="col" >SUBJECT</th>
			<th scope="col">NAME</th>
			<th scope="col">DATE</th>
		</thead>
		<tbody class="table-group-divider">
		<tr>
			<td>${vo.boardNo}</td>
			<td>${vo.boardTitle}</td>
			<td>${vo.boardWriter}</td>
			<td>${vo.boardDate}</td>
		</tr>
		<tr>
			<td>
			<div class = "d-none d-lg-block">
			<textarea rows="10%" style="width:260%; border: 0;" readonly="readonly">${vo.boardContents}</textarea>
			</div>
			</td>
		</tr>
		
		</tbody>
		</table>
		</div>
	
        		<a class="btn btn-outline-secondary" href="./update?boardNo=${vo.boardNo}" style='width:80px; float: left; background-color: #f1f3f5; line-height: 30px; border: 0px;'>수정</a>
				<button id="del" class="btn btn-danger c1" style='width:80px; height:40px; left; background-color: grey; line-height: 30px; border: 0px;'>삭제</button>     		
			</div>
        		</div>
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        	</div>
        </div>

	
<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>