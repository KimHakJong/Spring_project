<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>    
<head>
 <meta charset="utf-8">

    <link href="${pageContext.request.contextPath}/resources/css/home/bootstrap.min.css" rel="stylesheet"> 
    <link href="${pageContext.request.contextPath}/resources/css/home/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
    <style>
    .d-flex.justify-content-end.mb-3{
    width:400px;
    float:right
   }
   
  .rounded.h-100.p-4{
	    width:150% !important;
	    margin-left:20%;
	    margin-top:4%;
        text-align:center;
	}
	

.table{
    margin-top:5%;   
}

.dropdown{
    float:left
}	
    </style>
<script>
      $(document).ready(function () {   
    	 console.log('${only}');
    	 if('${only}' == "" ){
    		 $('#filterDropdown').text('전체');
    	 }else if('${only}' == '휴가신청서'){
    		 $('#filterDropdown').text('휴가신청서');
    	 }else if('${only}' == '초과근무신청서'){
    		 $('#filterDropdown').text('초과근무신청서');
    	 }
    	 
    	 
    	  $('.condition').each(function() {
    		    
    		  // 결재상태에따라 결재상태 글자색을 변경한다.
    		  var condition = $(this).text();

    		    if (condition == '대기') {
    		      $(this).css('color', 'blue');
    		    } else if (condition == '거절') {
    		      $(this).css('color', 'red');
    		    } else if (condition == '승인') {
    		      $(this).css('color', 'green');
    		    }
    		  });
    	  
      });
  </script>    
</head>

<body>
<jsp:include page="../home/side.jsp" />

<div class="content">
<jsp:include page="../home/header2.jsp" />
<div class="container-fluid pt-4 px-4">



			             <div class="col-sm-12 col-xl-6">
                        <div class="rounded h-100 p-4">
                            <nav>
                                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                    <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab"
                                        data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home"
                                        aria-selected="true" onclick="location.href='getMian'">받은결재함</button>
                                        
                                    <button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
                                        data-bs-target="#nav-profile" type="button" role="tab"
                                        aria-controls="nav-profile" aria-selected="false" onclick="location.href='sendMain'">보낸결재함</button>
                                        
                                    <button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab"
                                        data-bs-target="#nav-contact" type="button" role="tab"
                                        aria-controls="nav-contact" aria-selected="false"  onclick="location.href='writeOvertime'">작성하기</button>
                                
                                    <c:if test="${department == '인사부' || admin == 'true' }">
                                     <button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab"
                                        data-bs-target="#nav-contact" type="button" role="tab"
                                        aria-controls="nav-contact" aria-selected="false"  onclick="location.href='getAdmin'">관리자</button>
                                    </c:if> 
                                
                                </div>
                            </nav>
                            <div class="tab-content pt-3" id="nav-tabContent">
                             
                             	<div class="dropdown">
						         <button class="btn btn-primary dropdown-toggle" type="button" id="filterDropdown" data-bs-toggle="dropdown"
						            aria-expanded="false"></button>
						         <ul class="dropdown-menu" aria-labelledby="filterDropdown">
						            <li><a class="dropdown-item" href="getMian">전체</a></li>
						            <li><a class="dropdown-item" href="getMian?only=휴가신청서">휴가신청서</a></li>
						            <li><a class="dropdown-item" href="getMian?only=초과근무신청서">초과근무신청서</a></li>
						         </ul>
						      </div>
                             
                                <!--테이블  시작 -->
						   <table class="table">
						      <thead>
						         <tr>
						            <th scope="col">번호</th>
						            <th scope="col">작성자</th>
						            <th scope="col">분류</th>
						            <th scope="col">작성일</th>
						            <th scope="col">결재상태</th>
						            
						         </tr>
						      </thead>
						      <tbody>
						       <%-- 받은 결제서류가 있을 때 --%>
						      <c:if test="${not empty paper_list}">						         
								    <c:forEach var="b" items="${paper_list}" >
								    <tr>
								     <td><c:out value="${b.RNUM}" /></td>
								     								     
								    <td><c:out value="${b.NAME}" /></td>
								    								    
									   <c:url var="detail" value="getDetail">
								        <c:param name="paper_num" value="${b.PAPER_NUM}"/>
								        <c:param name="classification" value="${b.CLASSIFICATION}"/>
								       </c:url>							       
								     <td>
								     <a href="${detail}" ><c:out value="${b.CLASSIFICATION}"/></a>
								     </td>
								     
								     <td><c:out value="${b.WRITE_DATE}"/></td>
								     <td class="condition"><c:out value="${b.CONDITION}"/></td>
								    </tr>    
								    </c:forEach>
							  </c:if>
						      
						      <%-- 받은 결제서류가 없을 때 --%>
						      <c:if test="${empty paper_list}">
							         <tr>
							            <td colspan="5">받은 결제서류가 없습니다.</td>
							         </tr>
                              </c:if>
						      </tbody>
						   </table>
<!--  페이징 처리  시작-->
<style>
.pagination{font-family: 'Lato', sans-serif; margin-top:40px}

.page-link {border:none; color:#777777; margin:0px 12px 0px 12px; padding:0px; 
height:25px; font-size:16px}
.page-link:focus{box-shadow: none;}
.page-link:hover{background-color:white;color:#777777;}
.page-item.active .page-link {
    color: #000;
    background-color: white;
     border-bottom:2px solid #000;
}
.first {margin:0px 12px 0px 0px;}
.back {margin:0px 22px 0px 0px;}
.next {margin:0px 0px 0px 22px;}
.last {margin:0px 0px 0px 12px;}
</style>
 
			 <div>
				<ul class="pagination justify-content-center">
				  <%-- 1페이지이전: 작동X, 안보임 --%>
				  <c:if test="${page<=1}">
				    <li class="paga-item" style="display:none">
				      <a class="page-link">&lt;&lt;</a>
				    </li>
				    <li class="paga-item" style="display:none">
				      <a class="page-link">&lt;</a>
				    </li>
				  </c:if>
				  
				  <%-- 1페이지보다 크면: 이전버튼 누르면 page값 ,board/getMian으로 보냄 --%>
				  <c:if test="${page>1}">
				    <c:url var="first" value="getMian">
				    	<c:param name="page" value="${1}"/>
				    	<c:if test="${search_name != ''}">
					      	<c:param name="search_name" value="${search_name}"/>
					    </c:if>
					    <c:if test="${only != ''}">
					      	<c:param name="only" value="${only}"/>
					    </c:if>
				    </c:url>
				    
				   	<c:url var="back" value="getMian">
				        <c:param name="page" value="${page-1}"/>
				        <c:if test="${search_name != ''}">
					      	<c:param name="search_name" value="${search_name}"/>
					    </c:if>
					     <c:if test="${only != ''}">
					      	<c:param name="only" value="${only}"/>
					    </c:if>
				    </c:url>
				    <li class="paga-item">
				    	 <a href="${first}" class="page-link first">&lt;&lt;</a>
				    </li>
				    
				    <li class="paga-item">
				         <a href="${back}" class="page-link back">&lt;</a>&nbsp;
				    </li>
				  </c:if>
				  
				  <%-- 1번부터 끝번호까지 페이지번호 매김--%>
				  <c:forEach var="i" begin="${startpage}" end="${endpage}" step="1">
				    <%--현재 페이지는 색깔다르고, 이동없음 --%>
				    <c:if test="${i == page}">
				      <li class="page-item active">
				        <a class="page-link">${i}</a>
				      </li>
				    </c:if>
			       
				    <%--다른 페이지는 누르면 검색필드,검색어,페이지들고 getMian.bo갔다온다 --%>
				    <c:if test="${i != page}">
				      <c:url var="move" value="getMian">
				        <c:param name="page" value="${i}"/>
				        <c:if test="${search_name != ''}">
					      	<c:param name="search_name" value="${search_name}"/>
					    </c:if>
					    <c:if test="${only != ''}">
					      	<c:param name="only" value="${only}"/>
					    </c:if>
				      </c:url>
				      <li class="paga-item">
				        <a href="${move}" class="page-link">${i}&nbsp;&nbsp;</a>
				      </li> 
				    </c:if>
				  </c:forEach>
				    
				    <%--현재 페이지가 최대페이지거나 이상 : 작동X, 안보임 --%>  
				    <c:if test="${page >= maxpage}">
				      <li class="page-item" style="display:none">
				      	<a class="page-link">&gt;</a>
				      </li>
				      <li class="page-item" style="display:none">
				      	<a class="page-link">&gt;</a>
				      </li>
				    </c:if>
				    
					    <%--최대페이지미만: 다음 버튼누르면 page+1값, 검색필드, 검색어 들고 getMian.bo 갔다옴 --%>
					    <c:if test="${page < maxpage}">
					      <c:url var="last" value="getMian">
					      	<c:param name="page" value="${maxpage}"/>
					      	<c:if test="${search_name != ''}">
					      	<c:param name="search_name" value="${search_name}"/>
					      	</c:if>
	                        <c:if test="${only != ''}">
					      	<c:param name="only" value="${only}"/>
					        </c:if>
					     </c:url>
					      
					      <c:url var="next" value="getMian">
					        <c:param name="page" value="${page+1}"/>
					        <c:if test="${search_name != ''}">
					      	<c:param name="search_name" value="${search_name}"/>
					      	</c:if>
					      	<c:if test="${only != ''}">
					      	<c:param name="only" value="${only}"/>
					        </c:if>
					      </c:url>
					      <li class="page-item">
					        <a href="${next}" class="page-link next">&gt;</a>
					      </li>
					      <li class="page-item">
					        <a href="${last}" class="page-link last">&nbsp;&nbsp;&gt;&gt;</a>
					      </li>
			         </c:if>
			        </ul>
			     </div>


<!-- 페이징처리끝 -->                                   
 <!-- 테이블  끝  -->                                   

                    
                          <form action="getMian" method="get">       
                          <div class="d-flex justify-content-end mb-3">
						      <div class="input-group">
						         <input type="text" class="form-control" placeholder="작성자를 입력하세요" name="search_name" id="Search">
						         <button class="btn btn-primary" type="submit">검색</button>
						      </div>
						   </div>
                          </form>
                            
                            </div>
                        </div>
                    </div>
		
		
		</div> 
	</div>

   
	<footer>
		<jsp:include page="../home/bottom.jsp" />
	</footer>

</body>
</html>