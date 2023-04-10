<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>

<head>
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<script src = "${pageContext.request.contextPath}/resources/js/todolist/modify.js"></script>
<style>
h1{
font-size:1.5rem; text-align:center; color:#1a92b9
}

table{
border-spacing: 5px !important;
width: 80%;

}
.table{
	border-color: black !important;
}

th{
	border-color: black !important;
	font-weight:bold;
}
td{
color: black;
}

label{
font-weight:bold;
margin-top: 10px;
}

.container-fluid.pt-4.px-4{
  
  height : 80%;
  width : 55%;
}
.container{
margin-bottom : 40px;
}

</style>

<script>

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

</script>

</head>
<body>

<jsp:include page="../home/side.jsp" />
<div class="content">


<jsp:include page="../home/header2.jsp" />
<div class="container-fluid pt-4 px-4"> 
	<div class="container" id="container">
	      <form action="modifyAction" method="post" name="modifyform" enctype="multipart/form-data">
	      <input type="hidden" name="BOARD_NUM" value="${todolist.board_num }">
	      <h1>MVC게시판 - 수정</h1>
	      <div class="form-group">
	         <label for="board_name"></label>
	         <input type="text" name="BOARD_NAME" class="form-control" value="${todolist.board_id }" readOnly>
	      </div>
	      <div class="form-group">
	      <label for="board_subject">제목</label>
	      <textarea name="BOARD_SUBJECT" id="board_subject" rows="1" class="form-control" maxlength="100">${todolist.board_subject }</textarea>
	      </div>
	      
	      <div class="form-group">
	      <label for="board_content">내용</label>
	      <textarea name="BOARD_CONTENT" id="board_content" rows="15" class="form-control">${todolist.board_content }</textarea>
	      </div>
	      
	      <div class="form-group">
	         <button type=submit class="btn btn-primary">수정</button>
	         <button type=reset class="btn btn-danger" onClick="history.go(-1)">취소</button>
	      </div>
	   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	      </form>
	   
	   </div><%-- class="container end --%>
	   </div>
	   </div>
</body>
</html>
	