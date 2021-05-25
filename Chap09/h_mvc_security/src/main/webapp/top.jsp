<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top JSP</title>
</head>
<body>
	<h1>Top 페이지입니다.</h1>
	<ul>
		<li><a href="user/user.jsp">일반 사용자용 페이지</a></li>
		<li><a href="admin/admin.jsp">관리자 전용 페이지</a></li>
	</ul>
	<form action="logout" method="post">
		<button> 로그아웃 </button>
	</form>
</body>
</html>