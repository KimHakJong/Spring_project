<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
	border-left:none;
}
.reserve_table td:nth-child(2n){
	border-right:none !important;
}
.reserve_table td:last-child{
	border-right:none;
}
.reserve_table tr td:nth-child(n+2){
background-clip: content-box !important;
	padding:16px 0px;
	background:#3abff7;
	color:white;
	font-size:14px;
}
</style>
<script>
   $(function() {
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
		        console.log(dateString);
		        $(".ui-datepicker-inline").toggle();
	   			$(".show_date span").text(dateString);
		    }
		});
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

			<div class="show_date"><span></span><img src="../resources/image/reserve/calendar6.png"></div>
			<div class="head_buttondiv">
			<button type="button" class="head_button1">예약현황</button>
			<button type="button" class="head_button2">예약</button>
			</div>
			<div class="datepicker"></div>
		</div>
		
		<div class="reserve_body">
			<div class="reserve_time">
				<span id="type_span">
					<select id="type_select">
						<option>차량</option>
						<option>회의실</option>
						<option>기기</option>
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
					<tr><td>세미나실</td>
								<td>김학종</td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr><td>전략회의실1</td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr><td>전략회의실2</td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr><td>전략회의실3</td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr><td>전략회의실4</td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr><td>전략회의실5</td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
								<td></td><td></td><td></td><td></td><td></td>
					</tr>
					
			</table>
		</div>
	</div>

</div> <!-- class content -->

<footer>
<jsp:include page="../home/bottom.jsp" />
</footer>

</body>
</html>