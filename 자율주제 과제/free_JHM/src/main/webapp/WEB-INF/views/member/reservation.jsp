<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
				<form name=form1 action="http://localhost:8080/myweb/member/reservation/" method="post">
					<h3 style="text-align: center;">예약자 정보</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="예약자 아이디"
							name="resid" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="숙소이름"
							name="resname" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="예약날짜"
							name="resdate" maxlength="20">
							<a class="btn btn-primary" role="button" href="/myweb/member/calendar">날짜선택</a> 
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름"
							name="resusername" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="전화번호"
							name="resusermobile" maxlength="20">
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일"
							name="resuseremail" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control"
						value="예약하기">
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>