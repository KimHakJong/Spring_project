<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
#insert_title{
	text-align:center;
	margin-top:5%;
}
.reserve_insert {
    width: 50%;
    margin-left: 25%;
    position: relative;
    margin-top: 5%;
}
#insert_title{
	text-align:center ;
	margin-top:40px;
}
#insert_close{
    border: none;
    outline: none;
    background: transparent;
    color: black;
    position: absolute;
    left: 93%;
    top: -1%;
    font-size: 35px;
}

#insertForm{
	padding:10px 30px;
}
.insert_menudiv:first-child{
	margin-top:15px !important;
}
.insert_menudiv{
	clear:both;
	margin: 30px 0px;
	height:50px;
}
#insertForm label{
display:inline-block;
width:25%;
color:black;
line-height:50px;
}
#insertForm input{
	width:73%;
	float:right;
	height: 50px;
    border: 1px solid #C4C5C8;
    border-radius: 10px;
    padding: 15px;
	
}

#names_label{
	position:absolute;
}
.wrap_namediv{
	height:50px;
}

div#insert_namediv {
    width: 54%;
    display: inline-block;
    margin-left:26.3%;
}

.insert_name{
	display:inline-block;
	background:#3abff7;
	color:white;
	font-weight:bold;
	border-radius:25px;
	font-size:14px;
	padding:7px;
	margin:4px 3px;
	
}
.etc_name {
    background: #C3C3C3;
    width: 55.99px;
    text-align: center;
}
.etc_show{
	display:none;
}
#member_search {
    border: 2px solid #26abff;
    position: absolute;
    margin-top: 2px;
    right: 32px;
    height: 50px;
    border-radius: 5px;
    color: #26abff;
    background: white;
    width: 100px;
    font-weight:bold;
}

#insert_type, #insert_resource_name{
	height:50px;
	border:1px solid #C4C5C8;
	border-radius:5px;
	width:32%;
}
#insert_type{
}

#insert_resource_name{
	float:right;
	width:38%;
}
#insert_day{
	width:37% !important;
	float:none !important;
}

.wrap_daydiv{
	margin-bottom:25px !important;
}
.wrap_timediv{
	margin-top:25px !important;
}
#insert_start_time{
	width:30% !important;
	float:none !important;
}
#insert_start_time + span{
	margin-left:2%;
	color:black;
}
#insert_end_time{
	width:30% !important;
}

#insert_submit{
	height:55px !important; width:100%;display:block; 
	font-weight:bold; margin:0 auto; opacity:1;
    border-radius:5px;height:50px; font-size:18px;
    background-color:#26abff; color:white; border:1px solid #26abff;
    outline:none;}
    
#insert_submit:hover{
	background-color:#009CFF;
	border:1px solid #009CFF;
}    
/* 생성 모달 끝 */
/* 명단 검색 */
#msearch{
	z-index:5000;
}

#msearch .modal-dialog {
	left:-1%;
	top:15%;
}
#msearch .modal-content {
    width: 500px;
    height: 500px;
}
#msearch_subject {
    text-align: center;
    margin-top: 35px;
}
#msearch_input {
    height: 45px;
    width: 300px;
    border:1px solid #ced4da;
    margin: 0 auto;
    display: block;
    border-radius: 10px;
    margin-bottom:20px;
    padding:15px;
}
#msearch_input:focus{
	border:2px solid #009CFF;
	outline:none;
}

#msearch_input + span {
    font-size: 25px;
    position: absolute;
    right: 110px;
    top: 18px;
    color: black;
}

.msearch_table_head{
	margin-bottom:0px !important;
}
#msearch_table_div h5 {
	margin-top:25%
}
#msearch_table_div::-webkit-scrollbar {
           width: 0px; /*스크롤바의 너비*/
}
#msearch_table_div::-webkit-scrollbar-thumb {
    height: 30%; /* 스크롤바의 길이 */
    background: transparent; /* 스크롤바의 색상 */
    
    border-radius: 10px;
}

#msearch_table_div::-webkit-scrollbar-track {
    background-color: transparent;  /*스크롤바 뒷 배경 색상*/
} 
.msearch_table_head, .msearch_table_body {
	text-align:center; color:black !important;
} 
			.msearch_table_head td:nth-child(1) {width:20%;border-top:none;font-weight:bold;border-bottom:1px solid #ced4da}
			.msearch_table_head td:nth-child(2) {width:40%;border-top:none;font-weight:bold;border-bottom:1px solid #ced4da}
			.msearch_table_head td:nth-child(3) {width:40%;border-top:none;font-weight:bold;border-bottom:1px solid #ced4da}
			.msearch_table_body td:nth-child(1) {width:20%;height:43px;border-bottom:1px solid #ced4da}
			.msearch_table_body td:nth-child(2) {width:40%;border-bottom:1px solid #ced4da}
			.msearch_table_body td:nth-child(3) {width:40%;border-bottom:1px solid #ced4da}
#mesearch_buttondiv {
    margin-top: 28px;
}
#msearch .modal-body {
    padding: 1rem 100px !important;
}
button#msearch_ok {
    height: 55px;
    background: #009CFF;
    outline: none;
    border: 1px solid #009CFF;
    width: 135px;
    border-radius:10px;
    color:white;
        font-size:18px;
    font-weight:bold;
}
button#msearch_ok:hover {
    background: #26abff;
    border: 1px solid #26abff;
}
button#msearch_cancel {
    height: 55px;
    width: 135px;
    border: 1px solid #ced4da;
    background: white;
    border-radius:10px;
    float:right;
    font-size:18px;
    font-weight:bold;
}
button#msearch_cancel:hover{
	background: #009CFF;
    border: 1px solid #009CFF;
    color:white;
}
/* 명단 검색 끝 */
</style>
<script>
$(function() {
	   let token = $("meta[name='_csrf']").attr("content");
	   let header = $("meta[name='_csrf_header']").attr("content");
		
$("body").on('change','input[type="checkbox"]',function(){
	let change_name = $(this).parent().next().text(); //체크된 이름
	if($(this).is(":checked")){//새로 체크되었을 때
		if(!check_name.includes(change_name+",")){ //기존 체크 이름목록에 방금 체크한 유저가 없는 경우 추가
			if(check_name.substr(-1)==',' || check_name.length==0){ //맨마지막이 ,인 경우
				check_name += change_name + ",";
			}else{
				check_name += "," + change_name + ",";
			}
		}
	}else {//체크해제된 경우
		if(check_name.includes(change_name+",")){//기존 체크이름목록에 방금 체크해제된 유저가 포함된 경우 제거
				check_name = check_name.replace(change_name+",","");
		}
	}
	
	console.log(check_name);
});


$("#msearch_input").keyup(function(){
	name = $(this).val();
	
	$.ajax({
		url : "../pmain/searchMemberList_ajax ",
		type : "post",
		data : { "name" : name},
		dataType : "json",
		async: false,
		beforeSend : function(xhr)
			{   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
			xhr.setRequestHeader(header, token);			
		},
		success : function(rdata){
			console.log("명단입력후ajax성공");
			
			let output ='';
			if(rdata!=null){
				output += "<table class='table msearch_table_body'>";
				$(rdata).each(function(){
					if(check_name.includes(this.name+",")){ //check_name에 값이 있다면 자동으로 체크되도록!
					output += '<tr><td><input type="checkbox" value="' + this.id +'" checked></td><td>'+this.name+'</td><td>'+this.department+'</td></tr>';
					}else{
					output += '<tr><td><input type="checkbox" value="' + this.id +'"</td><td>'+this.name+'</td><td>'+this.department+'</td></tr>';
					}
				});
				output += '</table>';
			}else{
				output += "<h5 style='text-align:center'>조회된 회원 명단이 없습니다.</h5>";
			}
			$("#msearch_table_div").empty();
			$("#msearch_table_div").append(output); 	
		}
	});
});


$("#member_search").click(function(){
	console.log("명단검색클릭");
	if($("#insert_names").val()==""){
		check_name = $("#insert_names").val();
	}else{
		check_name = $("#insert_names").val()+",";
	}
	$("#msearch_table_div").empty();
	
	$.ajax({
		url : "../pmain/memberlist_ajax",
		type: "post",
		dataType: "json",
		beforeSend : function(xhr)
			{   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
			xhr.setRequestHeader(header, token);			
		},
		success : function(rdata){
			console.log("ajax명단검색성공");
			
			let output ='';
			if(rdata!=null){
				output += "<table class='table msearch_table_body'>";
				$(rdata).each(function(){
					if(check_name.includes(this.name+",")){ //명단에 있으면 다시 명단 검색갔을 때 체크되어있다.
					output += '<tr><td><input type="checkbox" value="' + this.id +'" checked></td><td>'+this.name+'</td><td>'+this.department+'</td></tr>';
					}else{
					output += '<tr><td><input type="checkbox" value="' + this.id +'"></td><td>'+this.name+'</td><td>'+this.department+'</td></tr>';
					}
				});
				output += '</table>';
			}else{
				output += "<h5 style='text-align:center'>조회된 회원 명단이 없습니다.</h5>";
			}
			$("#msearch_table_div").append(output); 
		}
	});
});

//$("#msearch").draggable();
		//명단검색에서 취소 : 체크박스 체크 해제
		$("#msearch_cancel").click(function(){ 
			$(".msearch_table_body").find('input[type=checkbox]').each(function(){
				$(this).attr('checked',false);
			});
			$("#msearch_input").val(""); //검색창
			$("#insert_names").val("");//명단 이름
			$("#insert_namediv").empty();
			setTimeout(function(){
				$("#insert_namediv").css('height','50px');
				$(".wrap_namediv").css('height','50px');
				}, 100);
		});
		
		//명단검색에서 체크 후 확인 : 모달 명단에 자동입력
		$("#msearch_ok").click(function(){
			$("#msearch_input").val(""); //검색창

			//맨 마지막 쉼표 제거하기 위함
			if(check_name.substr(-1)==','){
				check_name = check_name.substring(0,check_name.length-1);
			}
			console.log("명단확인버튼(이름):"+check_name);
			
			//자동으로 개별 이름 div가 생성되도록
			$("#insert_names").val(check_name);
			let insert_name = $("#insert_names").val().split(",");
			$("#insert_namediv").empty();
			let output ="";
			
			if($("#insert_names").val()){ //값이 존재할 때
				
				for(var i=0; i<insert_name.length; i++){
					if(i==7){
						output += '<div class="insert_name etc_name">'+ "·&nbsp;·&nbsp;·"+'</div>';	
						output += '<div class="insert_name etc_show">'+ insert_name[i] +'</div>';	
					}else if(i>7){
						output += '<div class="insert_name etc_show">'+insert_name[i]+'</div>';
					}else{
						output += '<div class="insert_name">'+insert_name[i]+'</div>';
					}
				
				}
				$("#insert_namediv").append(output);
				setTimeout(function(){
					console.log($("#insert_namediv").css('height'));
					$(".wrap_namediv").css('height',$("#insert_namediv").css('height'));
					}, 100);
			}
			

		});
		
//인원 더보기 클릭 시 더보고, div크기 늘린다.
$('body').on('click','.etc_name',function(){
	$(this).css('display','none');
	$(".etc_show").css('display','inline-block');
	setTimeout(function(){
		console.log($("#insert_namediv").css('height'));
		$(".wrap_namediv").css('height',$("#insert_namediv").css('height'));
		}, 100);
});
});
</script>
</head>
<body>
<jsp:include page="../home/side.jsp" />


<div class="content">
<jsp:include page="../home/header2.jsp" />

	<div class="reserve_insert">
		<h4 id="insert_title">예약 추가</h4>
		<form name="insertForm" id="insertForm" action="insertReserve" method="post">
			<div class="insert_menudiv">
		  		<input type="hidden" name="number" id="insert_number">
		  		<label for="insert_id">예약자명</label>
				<input type="text" name="id" id="insert_id">
		  	</div>
				
			<div class="insert_menudiv">
				<label for="insert_purpose">예약목적</label>
				<input type="text" name="purpose" id="insert_purpose">
			</div>				

			<div class="insert_menudiv wrap_namediv">
				<label id="names_label">참여명단</label>
				<div id="insert_namediv">

				</div>
				<input type="hidden" name="mnames" id="insert_names">
				<button type="button" id="member_search" data-target="#msearch" data-toggle="modal" data-backdrop="static">명단 검색</button>
			</div>
					
			<div class="insert_menudiv">
				<label for="insert_type">자원선택</label>
				<select name="type" id="insert_type">
					<option></option>
					<option></option>
					<option></option>
					<option></option>
				</select>
				<select name="resource_name" id="insert_resource_name">
					<option></option>
					<option></option>
					<option></option>
					<option></option>
				</select>
			</div>
					
			<div class="insert_menudiv wrap_daydiv">
				<label for="insert_day">예약날짜</label>
				<input type="date" name="day" id="insert_day">
			</div>
					
			<div class="insert_menudiv wrap_timediv">
				<label for="insert_start_time">예약시간</label>
				<input type="time" name="start_time" id="insert_start_time">
				<span>부터</span>
				<input type="time" name="end_time" id="insert_end_time">
			</div>
					
	  		<button type="submit" id="insert_submit" >등&nbsp;&nbsp;록</button>
	  		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
 		</form>
	</div>
	<!-- 명단검색모달 -->
	<div id="msearch" class="modal">
		<div class="modal-dialog">
		  <div class="modal-content">
		  	<h4 id="msearch_subject">명단 검색</h4>
		  	<%--Modal Body --%>
		  	<div class="modal-body">
			
			<input type="text" id="msearch_input"><span>🔍︎</span>
			
			<table class="table msearch_table_head">
					<tr><td>체크</td><td>이름</td><td>부서명</td></tr>
			</table>
			<div id="msearch_table_div" style="width:100%; height:180px; overflow:auto">

		 	</div>
		 	<div id="mesearch_buttondiv">
		  	<button type="button" id="msearch_ok" data-dismiss="modal">확인</button>
		  	<button type="reset" id="msearch_cancel" data-dismiss="modal" >취소</button>
		 	</div>
		  	</div>
		  </div>
		</div>
	</div>
	<!-- 명단검색모달 끝 -->
</div> <!-- class content -->

<footer>
<jsp:include page="../home/bottom.jsp" />
</footer>
</body>
</html>