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
        		<div class="row">
        			<form action="add" method="post" enctype="multipart/form-data">
        				  <div class="mb-3">
						    <label for="boardTitle" class="form-label">Title</label>
						    <input type="text" class="form-control" name="boardTitle" id="boardTitle" placeholder="제목을 입력하세요.">
						  </div>
        				  <div class="mb-3">
						    <label for="boardWriter" class="form-label">Writer</label>
						    <input type="text" class="form-control" name="boardWriter" id="boardWriter" placeholder="작성자를 입력하세요.">
						  </div>
						 <div class="mb-3">
						    <label for="boardContents" class="form-label">Contents</label>
						    <textarea class="form-control" name="boardContents" id="boardContents" placeholder="내용을 입력하세요."></textarea>
						  </div>
						  <div class="mb-3">
						    <input type="file" class="form-control" name="files">
						  </div>
						  <div class="mb-3">
						    <input type="file" class="form-control" name="files">
						  </div>
						  <button class="btn btn-secondary">작성완료</button>
        			</form>
        		</div>
        		</div>
        		
        	</div>
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        </div>
		
	</div>
	
<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>