<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인 페이지</title>
</head>
<body>
	<div align=center>
	<H2>로그인</H2>
 	
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		로그인 에러입니다.<br>
		예외 타입 : ${SPRING_SECURITY_LAST_EXCEPTION.getClass().name} <br>
		메시지 : ${SPRING_SECURITY_LAST_EXCEPTION.message} <br>
		<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
	</c:if>
	
	<form action="processLogin" method="post">
		계정 : <input type="text" name="paramLoginId" /><br>
		암호 : <input type="password" name="paramPassword" /><br><br>
		<input type="submit"value="로그인" />
	</form>
	
	</div>
</body>
</html>