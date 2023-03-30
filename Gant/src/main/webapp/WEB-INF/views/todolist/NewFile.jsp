<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<meta charset="utf-8">
<script>
var token = jq1("meta[name='_csrf']").attr("content");
var header = jq1("meta[name='_csrf_header']").attr("content");

</script>
<title>Insert title here</title>
</head>
<body>


<jsp:include page="../home/side.jsp" />



<div class="content">

	<jsp:include page="../home/header2.jsp" />
	

    <div class="container">
        <h1>To-Do List</h1>
        <div class="row mb-3">
            <div class="col">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addScheduleModal">Add Schedule</button>
            </div>
            <div class="col-auto">
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
            <div class="col">
                <button type="button" class="btn btn-success">Sent Schedule</button>
                <button type="button" class="btn btn-danger">Send Schedule</button>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Schedule Details</th>
                            <th>Who Does This Schedule</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${schedules}" var="schedule">
                            <tr>
                                <td>${schedule.title}</td>
                                <td>${schedule.details}</td>
                                <td>${schedule.assignee}</td>
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