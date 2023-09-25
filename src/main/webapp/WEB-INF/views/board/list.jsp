<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        		<h1>List Page</h1>
        		
				<div class="card shadow mb-4">
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Title</th>
                                            <th>Writer</th>
                                            <th>Date</th>
                                            <th>Hit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
	                                   <c:forEach items="${list}" var="vo">
	                                        <tr>
	                                            <td>${vo.boardNo}</td>
	                                            <td><a href="./detail?boardNo=${vo.boardNo}">${vo.boardTitle}</a></td>
	                                            <td>${vo.boardWriter}</td>
	                                            <td>${vo.boardDate}</td>
	                                            <td>${vo.boardHit}</td>
	                                        </tr>  
	                                   </c:forEach>
	                                    
                                    </tbody>                               								
							</table>
						</div>
						<div class="row">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
							<div class="col=sm-12 col-md-5">
							   <a href="./add" class="btn btn-primary btn-icon-split">
	                              <span class="icon text-white-50">
	                                <i class="fas fa-flag"></i>
	                              </span>
	                              <span class="text">글 추가</span>
	                            </a>
							</div>

					</sec:authorize>
						<div class="col=sm-12 col-md-7" style="fl">
						<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
						<!-- 페이지 -->
						  <ul>
							  <c:if test="${pager.pre}">
							      <a class="" href="./list?page=${pager.startNum-1}&kind=${param.kind}&search=${param.search}" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							   </c:if>
									<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">		    
								    	<a class="" href="./list?page=${i}&kind=${param.kind}&search=${param.search}" style="margin-left: 20px;">${i}</a>
									</c:forEach>
								<c:if test="${pager.next}">
								  	   	<a class="" href="./list?page=${pager.lastNum+1}&kind=${param.kind}&search=${param.search}" aria-label="Next">
								    	<span aria-hidden="true">&nbsp;&raquo;</span>
								      	</a>
								   </c:if>
						   </ul>
						</div> 
						</div>
						</div>
					</div>
				</div>        		
      	  </div>       		
       </div>
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        </div>		
	</div>

<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>