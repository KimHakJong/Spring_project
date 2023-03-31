<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.reserve_main {
    position: absolute;
    width: 85%;
    height: 80%;
    left: 50%;
    transform: translate(-50%, 0%);
    top: 17%;
}
.content{
	overflow:hidden;
}
.reserve_head{
	width:100%;
	height:120px;
	text-align:center;
}

#add_resource{
	width:105px;
	height:45px;
	background:white;
	color:#33C43C;
	border-radius:10px;
	border:2px solid #33C43C;
	position:absolute;
	left:0px;
	font-weight:bold;
	display:none;
}

.show_date {
    display: inline-block;
    width: 200px;
    font-weight: bold;
    color: black;
    font-size: 20px;
    margin-top:60px;
}

.show_date img {
    width: 27px;
    position: relative;
    height: 27px;
    margin-left: 10px;
    top: -2.5px;
}

.datepicker {
	display:block;
	position:absolute;
    left: 36%;
}

.head_buttondiv {
	position:absolute;
	right:0px;
	top:0px;
   
}
.head_button1, .head_button2{
 height: 45px;
    width: 105px;
    border: none;
    color: white;
    background: #26abff;
    font-weight: bold;
    border-radius: 5px;
}
button.head_button1 {
    color: #26abff;
    background: white;
    border: 2px solid #26abff;
    margin-right: 10px;
}
.ui-datepicker-inline.ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all {
  padding: 10px 20px 10px 20px;
}
.ui-widget-header { border: none !important; background-color: transparent !important; }

.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default, .ui-button, html .ui-button.ui-state-disabled:hover, html .ui-button.ui-state-disabled:active {
    border: 1px solid #fff !important;
    background: #fff !important;
    font-weight: bold !important;
    color: #454545 !important;
    text-align: center !important;
    font-size: 14px;
    padding: 8px 0px 8px 0px;
}
.ui-datepicker .ui-datepicker-prev {
  left: 7px !important;
}
.ui-datepicker .ui-datepicker-next {
  right: 8px !important;
}
.ui-widget.ui-widget-content {
    width: 350px;
}

.ui-datepicker .ui-datepicker-title {
    font-size: 16px;
}

.ui-datepicker .ui-datepicker-header {
    width: 100%;
}

.ui-datepicker th {
    font-size: 15px;
    padding: 0.4em !important;
}

.ui-datepicker-calendar th:first-child {
	color: #ff2e47 !important;
}

.ui-datepicker-calendar th:nth-last-child(1) {
	color: #2baaff !important;
}

.ui-datepicker-calendar tr td:first-child a {
  color: #ff2e47 !important;
}
.ui-datepicker-calendar tr td:nth-last-child(1) a {
	color: #2baaff !important;
}
.ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight{
	background:#26abff !important;
	color:white !important;
	border-radius:50% !important;
}

.reserve_time{
	width:100%;
	height:40px;
}
/*생성 모달*/
#reserve_insert .modal-dialog{
    max-width: 600px !important;
    top:1%;
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
    right: 47px;
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

select option:disabled{
	display: none;
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
/* 자원 생성 */
#resource_modal .modal-dialog{
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width:400px;
}
#resource_modal .modal-content{
	padding:0px 30px;
}
#resource_subject{
  margin-top: 38px;
  text-align:center;
  color:#26abff;
}
.resource_input{
    display: block;
  width: 100%;
  margin: 20px 0;
  border-radius: 7px;
  border: 1px solid #C4C5C8;
  outline: none;
  padding: 1rem;
  font-size: 1.1rem;
}

.resource_input:first-child{
	margin-bottom:25px;
}

#resource_buttondiv {
    margin-top: 28px;
    margin-bottom:10px;
}

button#resource_ok {
    height: 55px;
    background: #009CFF;
    outline: none;
    border: 1px solid #009CFF;
    width: 140px;
    border-radius:10px;
    color:white;
        font-size:18px;
    font-weight:bold;
}
button#resource_ok:hover {
    background: #26abff;
    border: 1px solid #26abff;
}
button#resource_cancel {
    height: 55px;
    width: 140px;
    border: 2px solid #26abff;
    background: white;
    border-radius: 10px;
    float: right;
    font-size: 18px;
    font-weight: bold;
    color: #26abff;
}
button#resource_cancel:hover{
	background: #009CFF;
    border: 1px solid #009CFF;
    color:white;
}
/* 자원 생성 끝 */
#type_span {
  width: 11.5%;
  display: inline-block;
  padding: 0px 0px 0px 0px;
  float:left;
  color:black;
}
#type_select{
	width:80px;
	height:30px;
	border:1px solid #ced4da;
	border-radius:5px;
}
.time_span{
	display:inline-block;
	width:8.7%;
	float:left;
	color:black;
}
.time_span:last-child{
	width:auto !important;
}
.reserve_table{
	border-collapse:collapse;
	width:100%;
}
.last_before{
	width:6.8%;
}
.reserve_table td {
	width:4.35%;
	height:60px;
	border-top:1px solid #ced4da;
	border-bottom:1px solid #ced4da;
}
.reserve_table td:first-child{
	width:13% !important;
	padding:0px 10px 0px 25px;
	color:black;
}
.reserve_table td:nth-child(2n+1){
	border-right:1px solid #ced4da;
	/*border-left:none;*/
	border-left:1px solid #ced4da;
}
.reserve_table td:nth-child(2n){
	/*border-right:none !important;*/
	border-right:1px solid #ced4da !important;
}
.reserve_table td:last-child{
	border-right:none;
}
.reserved{
	background-clip: content-box !important;
	padding:16px 0px;
	background:#3abff7;
	color:white;
	font-size:14px;
}
</style>
<script>
   $(function() {
	   let token = $("meta[name='_csrf']").attr("content");
	   let header = $("meta[name='_csrf_header']").attr("content");
		
	   //관리자인 경우 : 자원추가버튼 보임
	   if('${admin}'=='true'){
	   	$("#add_resource").css('display','block');
	   }
	   //자원추가 성공
	   if('${add_item}'==1){
		   alert("예약자원 등록을 성공했습니다.");
	   }
	   
	   const now = new Date();
	   const nowyear = now.getFullYear();
	   const nowmonth = ("0" + (now.getMonth()+1).toString()).slice(-2);
	   const nowdate = ("0" + now.getDate().toString()).slice(-2);
	   const nowday = now.getDay();
	   var weekday = new Array(7);
	   weekday[0] = "일";
	   weekday[1] = "월";
	   weekday[2] = "화";
	   weekday[3] = "수";
	   weekday[4] = "목";
	   weekday[5] = "금";
	   weekday[6] = "토";
	   
	   //오늘날짜 표시
	   $('.show_date span').text(nowyear+". " + nowmonth + ". " + nowdate +" (" + weekday[nowday] + ")");
	   
	   //초기값 오늘
	   $('.datepicker').datepicker('setDate', 'today');
	   
	   $(".show_date").click(function(){
		   $(".ui-datepicker-inline").toggle();
	   $('.datepicker').datepicker({
		   	dateFormat: 'yy. mm. dd (D)',
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        showOtherMonths: true,
	        yearSuffix: '년',
	        minDate: "0D",
	        maxDate: "+1M",
	        todayHighlight :true,
		    onSelect: function(dateString) {
		        $(".ui-datepicker-inline").toggle();
	   			$(".show_date span").text(dateString);
		    }
		});
	   });
	   
	   //자원종류선택 변경 시 선택된 종류의 자원표시
	   $("#type_select").change(function(){
			let type = $("#type_select option:selected").text();
			$.ajax({
				url : "loadResource_ajax",
				type : "post",
				data : {"type" : type},
				dataType : "json",
				async: false,
				beforeSend : function(xhr)
	  			 {   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
	    			xhr.setRequestHeader(header, token);			
	    		 },
	    		success : function(rdata){

	    			//종류마다 테이블 행(자원개수)이 다르므로 초기화 후 해당 자원이름 넣는 작업
	    			$(".reserve_table").empty();
	    			 $(rdata).each(function(){
	    				let output = '';
	    				output += "<tr><td>" + this + "</td>"
	    					    + "<td></td><td></td><td></td><td></td><td></td>"
	    				        + "<td></td><td></td><td></td><td></td><td></td>"
	    				        + "<td></td><td></td><td></td><td></td><td></td>"
	    				        + "<td></td><td></td><td></td><td></td><td></td></tr>";
	    				$(".reserve_table").append(output);
	    			 });
	    		 }//success
			});//ajax
			
			loadTime();
	   });
	   
	   //해당 자원명에 대한 예약시간조회
	   loadTime();
	   //테이블 예약가능시간 조회
	   function loadTime(){
		   let day = $(".show_date span").text().substring(0,12);
		   $(".reserve_table tr").each(function(){
			  let resource_name = $(this).children().eq(0).text();
			  loadTime_ajax(resource_name, day, $(this));
		   });
		   
		   function loadTime_ajax(resource_name, day, selector){
			  	  $.ajax({
			   	  	 url: "loadTime_ajax",
			   	  	 type : "post",
			   	  	 data : {"resource_name" : resource_name, "day" : day},
			   	  	 dataType : "json",
			   		 beforeSend : function(xhr)
		  			 {   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
		    			xhr.setRequestHeader(header, token);			
		    		 },
		    		 success : function(rdata){
		    			if(rdata!=null){
			    			$(rdata).each(function(){
		    					selector.children().eq(this.reserved_time).addClass('reserved');
			    			});
		    			}
		    		 }
			  	  });
			 }//loadTime_ajax function
	   }//loadTime funciton
	   
	   //예약자명,예약자아이디를 예약추가모달에 넣음
	   $("#insert_name").val($(".side_userid").next().text());
	   $("#insert_id").val($(".side_userid").text());
	   
	   //예약추가 후 등록했을 때
	   $("#insertForm").submit(function(){
		   
	   });
	   
	   
		//인원 더보기 클릭 시 더보고, div크기 늘린다.
		$('body').on('click','.etc_name',function(){
			$(this).css('display','none');
			$(".etc_show").css('display','inline-block');
			setTimeout(function(){
				$(".wrap_namediv").css('height',$("#insert_namediv").css('height'));
				}, 100);
		});
	   
	   //예약추가 모달 자원 종류에 따른 자원명 셀렉트 박스 불러옴 
	   $("#insert_type").change(function(){
		   let type = $("#insert_type option:selected").text();
		  
		   $.ajax({
				url : "loadResource_ajax",
				type : "post",
				data : {"type" : type},
				dataType : "json",
				async: false,
				beforeSend : function(xhr)
	  			 {   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
	    			xhr.setRequestHeader(header, token);			
	    		 },
	    		success : function(rdata){
	    			$("#insert_resource_name").empty();
	    			//종류에 따른 자원명을 option에 넣음
	    			$("#insert_resource_name").append('<option value="" disabled selected>이름 선택</option>');
	    			$(rdata).each(function(){
	    				let output = "<option value='"+ this +"'>" + this + "</option>";
	    				$("#insert_resource_name").append(output);
	    			});
	    		 }//success
			});//ajax
	   });

		//모달닫으면 명단들 사라짐
		$("#insert_close").click(function(){
			$("#insert_namediv").empty();
		});
		
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
			
			console.log("현재체크된 이름="+check_name);
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
					$(".wrap_namediv").css('height',$("#insert_namediv").css('height'));
					}, 100);
			}
			

		});
		
   });
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../home/side.jsp" />


<div class="content">
<jsp:include page="../home/header2.jsp" />

	<div class="reserve_main">
		<div class="reserve_head">
			<button type="button" id="add_resource" data-target="#resource_modal" data-toggle="modal" data-backdrop="static">자원추가</button>
			<div class="show_date"><span></span><img src="../resources/image/reserve/calendar6.png"></div>
			<div class="head_buttondiv">
			<button type="button" class="head_button1">예약현황</button>
			<button type="button" class="head_button2" data-target="#reserve_insert" data-toggle="modal" data-backdrop="static">예약</button>
			</div>
			<div class="datepicker"></div>
		</div>
		 
		<div class="reserve_body">
			<div class="reserve_time">
				<span id="type_span">
					<select id="type_select">
						<c:forEach var="type" items="${types}" varStatus="status">
						 <c:if test="${status.index==0}">
						 	<option selected value="${type}">${type}</option>
						 </c:if>
						 <c:if test="${status.index>0}">
						 	<option value="${type}">${type}</option>
						 </c:if>
						</c:forEach>
					</select>
				</span>
				<span class="time_span">08:00</span>
				<span class="time_span">09:00</span>
				<span class="time_span">10:00</span>
				<span class="time_span">11:00</span>
				<span class="time_span">12:00</span>
				<span class="time_span">13:00</span>
				<span class="time_span">14:00</span>
				<span class="time_span">15:00</span>
				<span class="time_span">16:00</span>
				<span class="time_span last_before">17:00</span>
				<span class="time_span">18:00</span>
			</div>
			<table class="reserve_table">
				<c:forEach var="resource" items="${resources_by_type}">
					<tr><td>${resource}</td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<!-- 예약모달 시작 -->
	<div class="modal" id="reserve_insert">
		<div class="modal-dialog">
		  <div class="modal-content">
		  	<h4 id="insert_title">예약 추가</h4>
		  	<button type="button" id="insert_close" data-dismiss="modal">×</button>
		  	<%--Modal Body --%>
		  	<div class="modal-body">
		  		<form name="insertForm" id="insertForm" action="insertReserve" method="post">
		  			<div class="insert_menudiv">
		  			<input type="hidden" name="number" id="insert_number">
		  			<label for="insert_name">예약자명</label>
					<input type="text" name="name" id="insert_name" readonly>
					<input type="hidden" name="id" id="insert_id">
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
					<option value="" disabled selected>종류 선택</option>
					<c:forEach var="type" items="${types}">
						<option value="${type}">${type}</option>
					</c:forEach>
					</select>
					<select name="resource_name" id="insert_resource_name">
					<option value="" disabled selected>이름 선택</option>
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
		  </div>
		</div>
	</div>
	<!-- 예약모달 끝 -->
	
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
	<!-- 자원생성모달 -->
	<div id="resource_modal" class="modal">
		<div class="modal-dialog">
		  <div class="modal-content">
		  	<h4 id="resource_subject">자원 추가</h4>
		  	<%--Modal Body --%>
		  	<div class="modal-body">
			<form action="addResource" method="post" id="addResourceForm">
			<input type="text" name="type" class="resource_input" placeholder="자원 종류">
			<input type="text" name="resource_name" class="resource_input" placeholder="자원명">
			
		 	<div id="resource_buttondiv">
		 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		  	<button type="submit" id="resource_ok">등록</button>
		  	<button type="button" id="resource_cancel" data-dismiss="modal">취소</button>
		 	</div>
			</form>
		  	</div>
		  </div>
		</div>
	</div>
	<!-- 자원생성모달 끝 -->
</div> <!-- class content -->

<footer>
<jsp:include page="../home/bottom.jsp" />
</footer>

</body>
</html>