$(function(){
     
     
     //추가
    let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

    $("#comment table").hide();  //1
    let page=1; //더 보기에서 보여줄 페이지를 기억할 변수
    const count = parseInt($("#count").text());
    if(count != 0){ //댓글 갯수가 0이 아니면
       getList(1); // 첫 페이지의 댓글을 구해 옵니다. ( 한 페이지에 3개씩 가져옵니다.)   
     }else{
      $('#message').text("등록된 댓글이 없습니다.")
     }
     
     let num = 0;
     let url = '';
     let data = {};
     
     function getList(currentPage){
      $.ajax({
			type:"post",
			url: "../comment/list",
			data : {
			 "board_num" : $("#board_num").val(),
			 "page": currentPage
			 },
			dataType:"json",
			//추가
			beforeSend : function(xhr)
        {   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
        	xhr.setRequestHeader(header, token);			
        },
			success:function(rdata){
			$("#count").text(rdata.listcount);
			if(rdata.listcount > 0){
			 $("#comment table").show(); // 문서가 로딩될때 hide()했던 부분을 보이게 합니다.(1)
			 $("#comment tbody").empty(); 
			 
			 $(rdata.list).each(function() {
                 let output = '';	
                 let img = '';
                 if($("#loginid").text() == this.id) {
                 img = "<img src='../resources/image/pencil2.png' width='15px' class='update'>"	 
			         + "<img src='../resources/image/delete.png' width='15px' class='remove'>"	
			         + "<input type='hidden' value='" + this.num + "' >";
			     } //if end
			    output += "<tr><td>" + this.id + "</td>";
			    
			    //XSS(Cross-Site Scription): 권한이 없는 사용자가 웹사이트에 스크립트를 삽입하는 공격 기법
			    //이것을 방지하지 위한 방법으로 2번 처럼 <td></td> 영역을 만든 뒤 3번에서 text()안에
			    // this.content를 넣어 스크립트를 문자열로 만듭니다.
			    
			    output += "<td></td>" ; //2
			        			         
			    //2번과 3번을 이용하지 않고 4번을 이용하면 내용에 스트립트가 있는 경우 스크립트 실행됩니다.
			    //output += "<td>" + this.content + "</td>"; // 4
			    output += "<td>" + this.reg_date + img +  "</td></tr>";
			    $("#comment tbody").append(output);
			    
			    //append한 마지막 tr의 2번째 자식 td를 찾아 text()메서드로 content를 넣습니다.
			    $("#comment tbody tr:last").find("td:nth-child(2)").text(this.content); // 3
			        
			}); //each end
			
			if(rdata.listcount > rdata.list.length){ // 전체 댓글 갯수 > 현재까지 보여준 댓글 갯수
			   $("#message").text("더보기")
			}else{
			   $("#message").text("")
			}
			
            }else {    // if(rdata.listcount > 0) end
              $("#message").text("등록된 댓글이 없습니다.")
              
              $("#comment table").hide() // 1
            }
         }
     }) // ajax end
     
     }// getList 함수 end
     
     
     //글자수 50개 제한하는이벤트
     $('#content').on('input', function() {
	    let content = $(this).val(); 
	    let length = content.length;
	    if(length > 50){
	      length = 50;
	      content = content.substring(0,length);
	      $(this).val(content); 
	    }
	    $(".float-left").text(length+'/50');
	 });//'keyup','#content', function() end
	 
	 
	 
	 //더 보기를 클릭하면 page 내용이 추가로 보여집니다.
	 $('#message').click(function() {
	 getList(++page);
	 }); //click end
	 
	 
	 
	 
	 // 등록 또는 수정완료 버튼을 클릭한 경우
	 // 버튼 라벨이 '등록'인 경우는 댓글을 추가하는 경우
	 // 버튼 라벨이 '수정완료'인 경우는 댓글을 수정하는 경우
	 $('#write').click(function() {
	  const content = $('#content').val().trim();
	  if(!content){
	    alert("내용을 입력하세요")
	    return false;
	  }
	  
	  const bottonText = $('#write').text(); // 버튼의 라벨로 add할지 update할지 결정
	  
	  $('.float-left').text('총 50자까지 가능합니다.');
	  
	  if(bottonText == '등록'){ //댓글 추가하는 경우
	    url = "../comment/add";
	    data = {
	            "content" : content,
	            "id" : $("#loginid").text(),
	            "board_num" : $("#board_num").val()
	            };
	 }else{ // 댓글 수정하는 경우
	    url = "../comment/update";
	    data = {
	            "num" : num,
	            "content" : content
	            };
	    $('#write').text("등록"); // 다시 등록으로 변경
	    $('#comment .cancel').remove(); // 취소버튼 삭제
	 
	 }
     
      $.ajax({
			type:"post",
			url: url,
			data : data,
			//추가
			beforeSend : function(xhr)
        {   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
        	xhr.setRequestHeader(header, token);			
        },
			success:function(result){
			$('#content').val('');
			if(result == 1){
			//page = 1
			getList(page); // 등록, 수정완료 후 해당 페이지 보여줍니다.
			
			 } // if end
		 } // success end	 
	 })	//ajax end
  }) // $('#write') end
  

  

  //pencil2.png 클릭하는 경우(수정)
   $("#comment").on('click','.update',function(){
      //선택한 내용을 변수 before에 저장합니다.
      let before = $(this).parent().prev().text(); 
      
      // $("#content")에 before의 내용을 보여줍니다.
      $("#content").val(before); 
      
      // 수정할 글 번호를 num(전역변수)이라는 변수에 저장합니다.
      num = $(this).next().next().val(); 
      
      //$("#write")의 "등록" 라벨을 "수정완료"로 변경합니다.
      $('#write').text('수정완료'); //등록->수정완료버튼이름 변경
      
      //"수정완료" 옆에 "취소"버튼을 생성합니다.
      //이미 취소 버튼 만들어진 상태에서 또 수정하면 취소버튼 생성방지
      if($('#write').prev().text()!="취소"){
         $("#write").before("<button class='btn btn-danger float-right cancel'>취소</button>");
      }
      
      //모든 행 배경색 white색
      $("#comment tr").css('background','white');
      
      //선택한 행의 배경색만 lightgray색
      $(this).parent().parent().css('background','lightgray');
      $('.remove').prop('disabled',true); //수정완료,취소 중에는 삭제를 클릭할 수 없도록
   });//수정모양 end
  
   
   
   
    // 수정중 취소를 클릭한 경우
   $("#comment").on('click','.cancel',function(){
      // 모든 행의 style 속성을 제거 합니다.
      $("#comment tr").removeAttr('style');
      
      //선택한 취소 버튼을 제거합니다.
      $('.cancel').remove();
      
      //$("#write")의 "수정완료" 라벨을 "등록"으로 변경합니다.
      $('#write').text("등록");
      
      //$("#content")의 값을 초기화 합니다.
      $("#content").val(""); 
       
      //삭제 할 수 있도록 합니다.
      $('.remove').prop('disabled',false); 
      
   });//pencil2.png 클릭하는 경우(수정) end
   
   
   
   
   //delete.png 클릭하는 경우(댓글 삭제)
   $("#comment").on('click','.remove',function(){
   
    // "정말 삭제하시겠습니까?" 확인을 클릭하면 ajax진행하고 취소를 클릭하면 클릭이벤트 종료합니다.
    if(!confirm("정말 삭제하시겠습니까?")){ // 취소를 클릭한 경우
      return;
    }
    
    //댓글 번호 구해옵니다.
    const deleteNum = $(this).next().val(); 
    
     $.ajax({
			type:"post",
			url: "../comment/delete",
			data : {
			 "num" : deleteNum
			 },
			dataType:"json",
			//추가
			beforeSend : function(xhr)
        {   //데이터를 전송하기 전에 헤더에 csrf값을 설정합니다.
        	xhr.setRequestHeader(header, token);			
        },
			success:function(rdata){
          if(rdata == 1){
            getList(page); //삭제 후 해당 체이지의 내용을 보여줍니다.
           }
         } // success end
     }) // ajax end
  
   }); //delete.png 클릭하는 경우(댓글 삭제)end
   
    
}) // $(function(){