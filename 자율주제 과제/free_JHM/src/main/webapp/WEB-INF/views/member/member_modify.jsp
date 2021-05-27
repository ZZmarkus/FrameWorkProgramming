<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
	<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
	<title>회원가입</title>
</head>
<body>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbtron" style="padding-top: 20px;">
				<form name=form1 action="http://localhost:8080/myweb/member/modify/" method="post">
					<h3 style="text-align: center;">예약자 정보</h3>
					<div class="form-group">
						<input type="text" class="form-control" value="${student.resid}"
							name="resid" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" value="${student.resname}"
							name="resname" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" value="${student.resdate}"
							name="resdate" maxlength="20">
							<a class="btn btn-primary" role="button" href="/myweb/member/calendar">날짜선택</a> 
					</div>
					<div class="form-group">
						<input type="text" class="form-control" value="${student.resusername}"
							name="resusername" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" value="${student.resusermobile}"
							name="resusermobile" maxlength="20">
					</div>
					<div class="form-group">
						<input type="email" class="form-control" value="${student.resuseremail}"
							name="resuseremail" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control"
						value="수정하기">
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
</body>
</html>