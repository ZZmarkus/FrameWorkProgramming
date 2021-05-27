<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
		<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
	</head>
	
	<body>
	<c:if test="${member != null }">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		  <a class="navbar-brand" href="/myweb/">
		  <i class="fas fa-home"></i>
		  펜션 예약하기</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>

		  <div class="collapse navbar-collapse" id="navbarsExample05">
		  
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="/main-1.0.0-BUILD-SNAPSHOT/board/list">게시판</a>
		      </li>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${member}님 로그인중</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown05">
		        	<a class="dropdown-item" href="/myweb/member/myres">내 예약 보기</a>
		          	<a class="dropdown-item" href="/myweb/member/logout">로그아웃</a>
		        </div>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-md-0">
		    	<div class="form-group">
		    		<input type="text" class="form-control mr-2" placeholder="Search">
		    	</div>
		    	<button type="submit" class="btn btn-primary">검색</button>
		    </form>
		  </div>
		</nav>
	</c:if>
	
	
	<c:if test="${member == null }">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		  <a class="navbar-brand" href="/main-1.0.0-BUILD-SNAPSHOT/main/home">
		  <i class="fas fa-home"></i>
		  펜션 예약하기</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>

		  <div class="collapse navbar-collapse" id="navbarsExample05">
		  
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="/main-1.0.0-BUILD-SNAPSHOT/board/list">게시판</a>
		      </li>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">로그인/회원가입</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown05">
		          <a class="dropdown-item" href="/myweb/member/login">로그인</a>
		          <a class="dropdown-item" href="/myweb/member/register">회원가입</a>
		        </div>
		      </li>
		    </ul>
		    <form class="form-inline my-2 my-md-0">
		    	<div class="form-group">
		    		<input type="text" class="form-control mr-2" placeholder="Search">
		    	</div>
		    	<button type="submit" class="btn btn-primary">검색</button>
		    </form>
		  </div>
		</nav>
	</c:if>
	
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</html>
