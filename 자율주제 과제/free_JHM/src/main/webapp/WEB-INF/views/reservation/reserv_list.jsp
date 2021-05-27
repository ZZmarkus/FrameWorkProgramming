<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Reservation List</title>
	<link rel="stylesheet" href="../resources/member.css" type="text/css"></link>
</head>
<body>
	<div align=center>
		<header>예약 목록</header><br>
		<c:url value="/member/register" var="url"/><a href="${url}">학생 등록</a><br><br>
		<table>
		  <tr>
		    <th>번호</th><th>객실이름</th><th>결제금액</th><th>예약날짜</th><th>예약자ID</th><th>예약자이름</th>
		  </tr>
		  <c:forEach var="reservation" items="${reserv}" >
			  <tr>
			    <td><c:out value="${reservation.resnum}"/></td>
			    <td><c:out value="${reservation.resname}"/></td>
			    <td><c:out value="${reservation.resprice}"/></td>
			    <td><c:out value="${reservation.resdate}"/></td>
			    <td><c:out value="${reservation.resuserid}"/></td>
			    <td><c:out value="${reservation.resusername}"/></td>
			  </tr>
		  </c:forEach>
		</table>
	</div>
</body>
</html>