<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>    
<head>
 <meta charset="utf-8">

    <link href="${pageContext.request.contextPath}/resources/css/home/bootstrap.min.css" rel="stylesheet"> 
    <link href="${pageContext.request.contextPath}/resources/css/home/style.css" rel="stylesheet">
    
    <style>
    .d-flex.justify-content-end.mb-3{
    width:400px;
    float:right
   }
    #filterDropdown{
    border-radius: 5px 0 0 5px;
    }
    #Search{
    border-radius: 0px
    }
  .rounded.h-100.p-4{
	    width:150% !important;
	    margin-left:20%;
	    margin-top:4%;
        text-align:center;
	}
	
	

.table{
    margin-top:4%;   
}
	
    </style>
    
    
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
                                </div>
                            </nav>
                            <div class="tab-content pt-3" id="nav-tabContent">

                                <!--  시작 -->
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
						         <tr>
						            <td>1</td>
						            <td>로준</td>
						            <td>휴가신청서</td>
						            <td>2023/06/27</td>
						            <td>대기</td>			            
						         </tr>

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
				  
				  <%-- 1페이지보다 크면: 이전버튼 누르면 page값 ,board/commute_record으로 보냄 --%>
				  <c:if test="${page>1}">
				    <c:url var="first" value="commute_record">
				    	<c:param name="page" value="${1}"/>
				    </c:url>
				    
				   	<c:url var="back" value="commute_record">
				        <c:param name="page" value="${page-1}"/>
				    </c:url>
				    <li class="paga-item">
				    	 <a href="${first}" class="page-link first">&lt;&lt;</a>&nbsp;
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
			   
				    <%--다른 페이지는 누르면 검색필드,검색어,페이지들고 commute_record.bo갔다온다 --%>
				    <c:if test="${i != page}">
				      <c:url var="move" value="commute_record">
				        <c:param name="page" value="${i}"/>
				      </c:url>
				      <li class="paga-item">
				        <a href="${move}" class="page-link">${i}</a>
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
				    
					    <%--최대페이지미만: 다음 버튼누르면 page+1값, 검색필드, 검색어 들고 commute_record.bo 갔다옴 --%>
					    <c:if test="${page < maxpage}">
					      <c:url var="last" value="commute_record">
					      	<c:param name="page" value="${maxpage}"/>
					      </c:url>
					      
					      <c:url var="next" value="commute_record">
					        <c:param name="page" value="${page+1}"/>
					      </c:url>
					      <li class="page-item">
					        <a href="${next}" class="page-link next">&gt;</a>
					      </li>
					      <li class="page-item">
					        <a href="${last}" class="page-link last">&gt;&gt;</a>
					      </li>
			         </c:if>
			        </ul>
			     </div>


<!-- 페이징처리끝 -->                                   
                                   

                                    <!--  끝  -->
                          <div class="d-flex justify-content-end mb-3">
						      <div class="dropdown">
						         <button class="btn btn-primary dropdown-toggle" type="button" id="filterDropdown" data-bs-toggle="dropdown"
						            aria-expanded="false">전체</button>
						         <ul class="dropdown-menu" aria-labelledby="filterDropdown">
						            <li><a class="dropdown-item" href="#">전체</a></li>
						            <li><a class="dropdown-item" href="#">휴가신청서</a></li>
						            <li><a class="dropdown-item" href="#">초과근무 신청서</a></li>
						         </ul>
						      </div>
						      <div class="input-group">
						         <input type="text" class="form-control" placeholder="작성자를 입력하세요" aria-label="Search" aria-describedby="button-addon2" id="Search">
						         <button class="btn btn-primary" type="button" id="button-addon2">검색</button>
						      </div>
						   </div>
    
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