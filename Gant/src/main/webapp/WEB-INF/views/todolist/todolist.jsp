<%@page import="org.apache.catalina.filters.ExpiresFilter.XServletOutputStream"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
	int p_no = (Integer)request.getAttribute("p_no");
	String p_name[] = null;
	String p_id[] = null;
			
	System.out.println("todolist에 넘어오는 p_no 값 : " + p_no);
	
	if(request.getAttribute("p_id") != null) {
		p_id = (String [])request.getAttribute("p_id");
	}
	
	if(request.getAttribute("p_name") != null) {
		p_name = (String [])request.getAttribute("p_name");
		
	}

	
%>
<!DOCTYPE html>
<html>

<head>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/todolist/list.js"></script>

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



</script>
<script>

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
		    <label>
		        <input type="radio" name="todo-type" class="btn-check" autocomplete="off">
		        <span class="btn btn-success">받은 할일</span>
		    </label>
		    <label>
		        <input type="radio" name="todo-type" class="btn-check" autocomplete="off">
		        <span class="btn btn-danger">보낸 할일</span>
		    </label>
		</div>
            <div class="col-auto">
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">검색</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </form>

                

                
            </div>
            <div class="col-auto">
				<button type="button"   class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/todolist/write?p_no=<%=p_no %>';" >할일 추가</button>
			</div>

        </div>
        
     	<c:if test="${listcount > 0}">
	
		<table class="table table-striped">
			<thead>
				<tr>
					<th colspan="3">할일 리스트</th>
					<th colspan="2">
						<span>글 개수 : ${listcount }</span>
					</th>
				</tr>
				<tr>
					<th><div>번호</div></th>
					<th><div>제목</div></th>
					<th><div>작성자</div></th>
					<th><div>작성일</div></th>
					<th><div>조회수</div></th>
	
				</tr>
			</thead>
			<tbody>
      <c:set var="num" value="${listcount-(page-1) * limit }"/>
      <c:forEach var="b" items="${boardlist }">
      <tr>
      <td><%--번호 --%>
         <c:out value="${num }"/><%-- num 출력 --%>
         <c:set var="num" value="${num-1 }"/> <%-- num= num-1의 의미 --%>
      </td>
      <td><%--제목 --%>
      <div>
         <%--답변글 제목 앞에 여백 처리부분 --%>
         <c:if test="${b.BOARD_RE_LEV !=0 }"><%--답글인경우 --%>
            <c:forEach var="a" begin="0" end="${b.BOARD_RE_LEV*2 }" step="1">
            &nbsp;	
            </c:forEach>
            <img src='${pageContext.request.contextPath}/resources/image/line.gif'>
         </c:if>
         <c:if test="${b.BOARD_RE_LEV ==0 }"><!--  원문 인경우 -->
         	 &nbsp;
         </c:if>
         
         <a href="detail?num=${b.BOARD_NUM }">

               <c:out value="${b.BOARD_SUBJECT}" escapeXml="true"/> <!--  html태그를 화면에서 출력 -->
				<span class="gray small">[<c:out value="${b.CNT}"/>]</span>
            </a>
         </div>
      </td>
      
      <td><div>${b.BOARD_NAME }</div></td>
      <td><div>${b.BOARD_DATE }</div></td>
      <td><div>${b.BOARD_READCOUNT }</div></td>
      </tr>
      </c:forEach>
      </tbody>
		</table>
		
		<div class="center-block">
			<ul class="pagination justify-content-center">
				<c:if test="${page<=1}">
					<li class="page-item">
						<a class="page-link gray">이전&nbsp;</a>
					</li>
				</c:if>
				<c:if test="${page>1}">
					<li class="page-item">
						<a href="list?page=${page-1}" class="page-link">이전&nbsp;</a>
					</li>
				</c:if>
				
				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a==page }">
						<li class="page-item active">
							<a class="page-link">${a}</a>
						</li>
					</c:if>
					<c:if test="${a!=page }">
						<li class="page-item">
							<a href="list?page=${a}" class="page-link">${a}</a>
						</li>
					</c:if>
				</c:forEach>
				
				<c:if test="${page >= maxpage }">
						<li class="page-item">
							<a class="page-link gray">&nbsp;다음</a>
						</li>
				</c:if>
				<c:if test="${page < maxpage }">
						<li class="page-item">
							<a href="list?page=${page+1}" class="page-link">&nbsp;다음</a>
						</li>
				</c:if>
		
			</ul>			
		</div>
	</c:if>
	
	<%-- 게시글이 없는 경우 --%>
	
	<c:if test="${listcount==0 }">
		<h3 style = "text-align:center">등록된 글이 없습니다.</h3>
	</c:if>
	
	

    </div>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="pinfo"/>

		
			<span id="loginid">${pinfo.username}</span>
		
			</sec:authorize>

		<div class="container-fluid pt-4 px-4">		</div>
</div>
</body>
</html>