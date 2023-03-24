<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<style>
*{box-sizing:border-box; font-family:"noto sans", sans-serif;}
.pmain {
	position:absolute;
	width:1240px;
    left: 50%;
    transform: translate(-50%, 0%);
	height:100%;
	color:black;
}
#search_div {
    position: absolute;
    top: 60px;
    width: 100%;
}

#search_div > form {
	text-align:center;
}

#search_word {
box-sizing: border-box;
height:50px;
padding:0px 10px; 
width:500px;
border:1px solid #C4C5C8;
outline:none;
border-radius:5px;
}
#search_word:focus{
outline:none;
	border:2px solid #009CFF
}
.search_click{
height:50px;
width:90px;
    background: #009CFF;
    color: white;
    border: 1px solid #009CFF;
    outline: none;
    border-radius: 5px;
}
.search_click:hover{
	background-color:#26abff;
	border:1px solid #009CFF;
}
#projects {
    border: 1px solid #F3F6F9;
    clear: both;
    position: absolute;
    top: 150px;
    width: 1080px;
    margin-left:80px;
    margin-right:80px;
    height: 450px;
    background:#F3F6F9;
    border-radius:20px;
}
#projects{
	overflow-y:scroll
	}

	
#projects::-webkit-scrollbar {
    	width: 2px !important;
  	}
  	
#projects::-webkit-scrollbar-thumb {
    height: 30% !important; /* 스크롤바의 길이 */
    background: transparent !important; /* 스크롤바의 색상 */
    
    border-radius: 10px !important;
}	 
#projects > .project_innerdiv:first-child { margin-top:0px;}

#projects > .project:nth-child(3n+1){
	margin-left:80px;
}
#projects > .project:nth-child(3n+3){
	margin-right:80px;
}
.project{
	width:250px;
	height:250px;
	border:1px solid white;
	float:left;
	margin:40px;
	text-align:center;
	border-radius: 23%;
	padding:15px;
	position: relative;
	background:white;
	font-weight:bold;
}

.project_people {
    position: absolute;
    left: 23px;
    top: 16px;
    
}
.project_people > img {width:20px;height:20px;margin-top:-2.2px;}
.project_peopleimg + span {
    display: inline-block !important;
    margin-left: 8px !important;
    margin-top: 1px !important;
    color:black !important;
}
.project_editimg {
    width: 30px;
    height: 30px;
    position: absolute;
    right: 21px;
}
.project_deleteimg {
    width: 20px;
    height: 20px;
    position: absolute;
    right: 22px;
    top:20px;
}
.project_innerdiv{
	text-align:center;
	margin:10px 0px;
}
.project_content {
    position: relative;
    top: 23%;
}

.progress{
	width:200px;
	margin-left:10px;
	margin-right:10px;
}

#project_openmenu {
    width: 70px;
    height: 70px;
    position: absolute;
    top: 510px;
    left: 1075px;
}
#project_openedmenu {
    width: 120px;
    height: 120px;
    border: 1px solid lightgray;
    text-align: center;
    line-height: 150px;
    position: relative;
    left: 940px;
    top: 490px;
    border-radius: 20px;
    box-sizing: border-box;
    background: white;
    display:none;
}
#project_openedmenu>div {
    height: 40px;
    line-height: 40px;
    
}

#project_openedmenu>div:hover {
    color: white;
    background: #26abff;
    border-radius: 20px 20px 20px 20px;
}
</style>

</head>

<body>
<jsp:include page="../home/side.jsp" />


<div class="content">
<jsp:include page="../home/header2.jsp" />
<div class="pmain">
		<div id="search_div">
		<form id="search_form">
			<input type="text" id="search_word" placeholder="프로젝트명을 검색하세요 . . ."><button type="button" class="search_click">검색</button>
		</form>
		</div>
		
		<div id="projects">
			<div class="project">
			
			<div class="project_people">
			<img  class="project_peopleimg" src="../image/pmain/project_user.png"><span>6</span>
			</div>
			<img class="project_editimg" src="../image/pmain/pencil.png">
			
			<div class="project_content">
			<div class="project_innerdiv">프로젝트명입니다</div>
			<div class="project_innerdiv">2022.03.15 ~ 2022.04.15</div>
			<div class="project_innerdiv">자료수집중</div>
			<div class="project_innerdiv">
			<div class="progress">
  				<div class="progress-bar progress-bar-success" role="progressbar"
  					 aria-valuemin="0" aria-valuemax="100" style="width:40%">
  					  40%
  				</div>
  			</div>
			</div>
			
  			</div>
			</div><!-- project -->
			
			<div class="project">
			
			<div class="project_people">
			<img  class="project_peopleimg" src="../image/pmain/project_user.png"><span>6</span>
			</div>
			<img class="project_deleteimg" src="../image/pmain/clear.png">
			<div class="project_content">
			<div class="project_innerdiv">프로젝트명입니다</div>
			<div class="project_innerdiv">2022.03.15 ~ 2022.04.15</div>
			<div class="project_innerdiv">자료수집중</div>
			<div class="project_innerdiv">
			<div class="progress">
  				<div class="progress-bar progress-bar-success" role="progressbar"
  					 aria-valuemin="0" aria-valuemax="100" style="width:40%">
  					  40%
  				</div>
  			</div>
			</div>
			
  			</div>
			</div><!-- project -->
			
			<div class="project">
			
			<div class="project_content">
			<div class="project_innerdiv">프로젝트명입니다</div>
			<div class="project_innerdiv">2022.03.15 ~ 2022.04.15</div>
			<div class="project_innerdiv">자료수집중</div>
			<div class="project_innerdiv">
			<div class="progress">
  				<div class="progress-bar progress-bar-success" role="progressbar"
  					 aria-valuemin="0" aria-valuemax="100" style="width:40%">
  					  40%
  				</div>
  			</div>
			</div>
			
  			</div>
			</div><!-- project -->
			
			<div class="project">
			
			<div class="project_people">
			<img class="project_peopleimg" src="../image/pmain/project_user.png"><span>6</span>
			</div>
			
			<div class="project_content">
			<div class="project_innerdiv">프로젝트명입니다</div>
			<div class="project_innerdiv">2022.03.15 ~ 2022.04.15</div>
			<div class="project_innerdiv">자료수집중</div>
			<div class="project_innerdiv">
			<div class="progress">
  				<div class="progress-bar progress-bar-success" role="progressbar"
  					 aria-valuemin="0" aria-valuemax="100" style="width:40%">
  					  40%
  				</div>
  			</div>
			</div>
			
  			</div>
			</div><!-- project -->
			
		</div><!-- projects -->
			<img id="project_openmenu" src="../image/pmain/projectmenu3.png">
			<div id="project_openedmenu">
				<div id="create_project">
					<a class="gocreate" data-toggle="modal" href="#createmodal" data-backdrop="static">추가</a></div>
				<div id="update_project">수정</div>
				<div id="remove_project">삭제</div>
			</div>
</div><!-- pmain -->

<%--생성모달 창 --%>
<div class="modal" id="createmodal">
		<div class="modal-dialog">
		
		  <div class="modal-content">
		  	<h4>프로젝트 생성</h4>
		  	<%--Modal Body --%>
		  	<div class="modal-body">
		  		<form name="createForm" id="createForm">
		  		<label for="project_name">프로젝트명</label>
				<input type="text" name="P_NAME" id="create_name">
			
		<label for="project_term">프로젝트 기간</label>
			<input type="date" name="P_SDATE" id="password" placeholder="비밀번호 입력">
			
			<input type="date" name="P_EDATE" id="password2" placeholder="비밀번호 확인">
			
		<label for="post">명&nbsp;&nbsp;단</label>
			<input type="text" name="post" id="post" readOnly maxlength="5">
			<button type="button" id="spost">명단 검색</button>
			
		<label for="name">내&nbsp;&nbsp;용</label>
			<textarea>
			</textarea>
		 
		  		<button type="button" id="close" data-dismiss="modal">창 닫기</button>
		  		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		  		</form>
		  	</div>
		  </div>
		</div>
	</div>
	<%--모달 끝 --%>	
<div class="container-fluid pt-4 px-4">
</div>
</div> <!-- class content -->

<footer>
<jsp:include page="../home/bottom.jsp" />
</footer>
<script>
	$('#project_openmenu').click(function(){
		$("#project_openedmenu").toggle();
	});
	
	$("#create_project").click(function(){
		
	});
	
</script>
</body>

</html>