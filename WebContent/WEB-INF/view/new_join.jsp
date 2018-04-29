<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./../css/index/main.css">
    <title>Main</title>
</head>s
<body class="flex-center-row">
	<form action="join.do" method="post"> 
    <div class="center_box flex-center-column">
        <div class="title_box">
            <span class="_title">Management System</span>
        </div>
        <div class="sign_box">
            <div class="list_box flex-center-row">
            <label for="groupnumber">Job</label><br>
                <select class="custom-select" name="groupnumber" >
                    <option value="2">학생</option>
                    <option value="1">교수</option>
                </select>
            </div>
            <div class="list_box flex-center-row">
                <!--<span class="list_head">아이디</span>-->
                <label for="id">ID</label><br>
                <input type="text" name="id" value="${param.id}" maxlength="8" placeholder="아이디 입력">
                <c:if test="${errors.id}">ID를 입력하세요.</c:if>
				<c:if test="${errors.duplicateId}">이미 사용중인 아이디입니다.</c:if>
            </div>
            <div class="list_box flex-center-row">
            	<!-- <span class="list_head">이름</span -->
            	<label for="confirmPassword">ConfirmPassword</label><br>
            	<label for="name">Name</label><br>
            	<input type="text" name="name" value="${param.name}" maxlength="8" placeholder="이름 입력">
            	<c:if test="${errors.name}">이름을 입력하세요.</c:if>
            </div>
            <div class="list_box flex-center-row">
               <!--<span class="list_head">패스워드</span>-->
               
               <label for="password">Password</label><br>
                <input type="password" name="password" placeholder="비밀번호 입력" >
                <c:if test="${errors.password}">암호르 입력하세요.</c:if>
            </div>
            <div class="list_box flex-center-row">
            <label for="confirmPassword">ConfirmPassword</label><br>
            	<input type="password" name="confirmPassword"  placeholder="비밀번호 확인 입력">
				<c:if test="${errors.confirmPassword}">확인을 입력하세요.</c:if>
				<c:if test="${errors.notMatch}">암호와 확인이 일치하지 않습니다.</c:if>
        	</div>
        	<div class="list_box flex-center-row">
        	<label for="phonenumber">Phone Number</label><br>
				<input type="text" name="phonenumber">
				<c:if test="${errors.phonenumber}">핸드폰 번호를 입력하세요.</c:if>
			</div>
			<div class="list_box flex-center-row">
			<label for="email">Email</label><br>
				<input type="text" name="email">
				<c:if test="${errors.email}">이메일을 입력하세요.</c:if>
			</div>
		</div>	
        <div class="button_box flex-center-column">
            <br>
            <button type="submit">Join</button>
        </div>       
    </div>
   </form>
</body>
</html>