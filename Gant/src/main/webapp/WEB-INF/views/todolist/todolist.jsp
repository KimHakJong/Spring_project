<%@page import="org.apache.catalina.filters.ExpiresFilter.XServletOutputStream"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
	int p_no = (Integer)request.getAttribute("p_no");
	String p_name = null;
	String p_id = null;
			
	System.out.println(p_no);
	
	if(request.getAttribute("p_id") != null)
	{
		p_id = (String)request.getAttribute("p_id");
		String p_ids[] = p_id.split(",");
	}
	
	if(request.getAttribute("p_name") != null)
	{
		p_name = (String)request.getAttribute("p_name");
		String p_names[] = p_name.split(",");
	}
	
	if (p_name != null && p_id != null)
	{
		
		
	}
	
%>
<!DOCTYPE html>
<html>

<head>

<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<meta charset="utf-8">
<style>
#loginid{
	display: none;
}
.btn-outline-success{
	width: 75px;
}
.btn-primary{
float: right;}
.col{
	flex: 0.8 0 0 !important;
}



</style>

<title>할 일 리스트</title>
</head>
<body>


<jsp:include page="../home/side.jsp" />
<script>
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

</script>

<script>

function project_member(p_no) {
	
	var data = {
			"p_no" : p_no
			
		};
	
	console.log(data);
	console.log("cal 호출 성공");
	
	var resultdata;
	
	$.ajax({
		type : 'get',
		url : '${pageContext.request.contextPath}/pcalendar/cal?p_no='+p_no,
		data: {},
		//dataType : "text",
		
		beforeSend : function(xhr)
			{   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
			xhr.setRequestHeader(header, token);			
		},

		success : function(result) {
			console.log('cal 로딩 성공');
			console.log(result);

		},
		error : function() {
			console.log('cal 로딩 실패');
		},
		complete : function() {
			console.log('cal 완료');
		}
	})

}


</script>

<div class="content">

	<jsp:include page="../home/header2.jsp" />
	
	
	
	

    <div class="container">
    
    				  <div class="btn-group" role="group">
                  <input type="radio" class="btn-check" name="btnradio" id="btnradio1">
                    <label class="btn btn-outline-primary" for="btnradio1">게시판</label>

                    <input type="radio" class="btn-check" name="btnradio" id="btnradio2" checked="checked">
                    <label class="btn btn-outline-primary" for="btnradio2">할일 리스트</label>

                    <input type="radio" class="btn-check" name="btnradio" id="btnradio3" >
                    <label class="btn btn-outline-primary" for="btnradio3" onclick="window.location.href='${pageContext.request.contextPath}/pcalendar/cal?p_no=<%=p_no %>';" >캘린더</label>
                 </div>
    	<br><br>
        <h1>To-Do List</h1>
        <div class="row mb-3">
            <div class="col">
                
                                <button type="button" class="btn btn-success">받은 할일</button>
                <button type="button" class="btn btn-danger">보낸 할일</button>
            </div>
            <div class="col-auto">
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">검색</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </form>

                

                
            </div>
            <div class="col-auto">
				<button type="button"   class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addScheduleModal">할일 추가</button>
			</div>

        </div>
        <div class="row">
            <div class="col">
                <table class="table">
                    <thead>
                        <tr>
                            <th>제목</th>
                            <th>내용</th>
                            <th>상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${schedules}" var="schedule">
                            <tr>
                                <td>${schedule.subject}</td>
                                <td>${schedule.content}</td>
                                <td>${schedule.state}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="pinfo"/>

		
			<span id="loginid">${pinfo.username}</span>
		
			</sec:authorize>

		<div class="container-fluid pt-4 px-4">		</div>
</div>
</body>
</html>