<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약 정보</title>
	<link rel="stylesheet" href="../resources/member.css" type="text/css"></link>
</head>
<body>
	<div align=center>
		<header>예약 정보</header><br>
		<table>
			<tr>
				<th>숙소이름</th><th>예약날짜</th><th>예약자명</th><th>전화번호</th><th>이메일</th><th></th>
			</tr>
			<tr>
			    <td><c:out value="${myres.resname}"/></td>
			    <td><c:out value="${myres.resdate}"/></td>
			    <td><c:out value="${myres.resusername}"/></td>
			    <td><c:out value="${myres.resusermobile}"/></td>
			    <td><c:out value="${myres.resuseremail}"/></td>
			    <td>
			      <c:url value="/member/modify?id=${myres.resid}" var="url"/><a href="${url}">예약정보수정</a>
			      <c:url value="/member/delete?id=${myres.resid}" var="url"/><a href="${url}">예약취소</a>
			 	</td>
			 </tr>
		</table>
	</div>
</body>
</html>