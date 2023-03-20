<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.memolist{font-family:"noto sans", sans-serif;}
.memohead{width:100%;height:60px;}
.memolist{
	background-size: 100% 100%;
		width: 580px;
		height: 480px;
		position:fixed;
		z-index:2000;
		left: 320px;
  		top: 140px;
  		background:#F9E5A7;
  		border-radius: 30px;
}

.postmain {
    width: 100%;
    height: 430px;
    padding: 10px 30px 20px 30px;
    box-sizing: border-box;
}
.postitlist {
    width: 100%;
    height: 290px;
}
	
	.postitlist{
	overflow-y:scroll
	}
	
	.postitlist::-webkit-scrollbar {
    	width: 2px;
  	}
  	
 .postitlist::-webkit-scrollbar-thumb {
    height: 30%; /* 스크롤바의 길이 */
    background: black; /* 스크롤바의 색상 */
    
    border-radius: 10px;
}	 

  	
  	
.postits{
	float:left;
	background-size:100% 100%;
	width:150px;
	height:80px;
	margin:11px;
	line-height:80px;
	text-align:center;
	
}
.memolist_subject{
	font-size:14px;
	font-weight:bold;
}
.memolist .btnClose {
    width: 10px;
    cursor: pointer;
    float: right;
    margin-right: 35px;
    font-size: 35px;
    color: black;
}


.btnClose + img {
    width: 30px;
    float: right;
    margin-right: 35px;
    margin-top: 13px;
    outline: none;
    height: 30px;
}
#memotitle{
	margin-top:21px; margin-left:35px;
	display:inline-block;
	font-size:19px;
	font-weight:bold
}

#creatememo{
	width:50px;
	height:50px;
	margin-left: 85%;
    margin-top: 3.5%;
}
</style>
</head>
<body>
<div class="memolist">
	<div class="memohead">
	<span id="memotitle">메모장</span>
	<div class="btnClose">&times;</div>
	<img src="${pageContext.request.contextPath}/memo/image/deleteicon.png">
	</div>

	<div class="postmain">
	
	<div class="postitlist">
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-yellow.png')">
			<span class="memolist_subject">프로젝트 활동계획서</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-green.png')">
			<span class="memolist_subject">주소록 목록</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-pink.png')">
			<span class="memolist_subject">캘린더 일정관리</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-pink.png')">
			<span class="memolist_subject">캘린더 일정관리</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-green.png')">
			<span class="memolist_subject">주소록 목록</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-blue.png')">
			<span class="memolist_subject">프로젝트 활동계획서</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-red.png')">
			<span class="memolist_subject">캘린더 일정관리</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-orange.png')">
			<span class="memolist_subject">캘린더 일정관리</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-pink.png')">
			<span class="memolist_subject">캘린더 일정관리</span>		
		</div>
		
		<div class="postits" style="background-image: url('${pageContext.request.contextPath}/memo/image/postit-blue.png')">
			<span class="memolist_subject">캘린더 일정관리</span>		
		</div>
	</div>
	
	<img id="creatememo" src="${pageContext.request.contextPath}/memo/image/creatememo5.png">
	</div>
</div>
</body>
</html>