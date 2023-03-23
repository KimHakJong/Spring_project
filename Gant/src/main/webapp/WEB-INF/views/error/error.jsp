<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<title>에러페이지</title>
<style>
b{color:orange}
body{text-align:center}
</style>
</head>
<body>
에러페이지<br>
<img src="${pageContext.request.contextPath }/resources/image/tear3.png" width="100px"><br>
요청하신 <b>${url }</b>처리에 오류가 발생했습니다
<hr>
${message }
</body>
</html>