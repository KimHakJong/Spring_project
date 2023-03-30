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
	
				<div class="btn-group" role="group">
                  <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked="">
                    <label class="btn btn-outline-primary" for="btnradio1">할일 리스트</label>

                    <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btnradio2">게시판</label>

                    <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
                    <label class="btn btn-outline-primary" for="btnradio3">캘린더</label>
                 </div>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="pinfo"/>

		
			<span id="loginid">${pinfo.username}</span>
		
			</sec:authorize>

		<div class="container-fluid pt-4 px-4">		</div>
</div>

</body>
</html>