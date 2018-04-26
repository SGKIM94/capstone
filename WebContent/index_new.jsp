<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/index/main.css">
    <title>Main</title>
</head>
<body class="flex-center-row">
    <form action="login.do" method="post">
    <div class="center_box flex-center-column">
        <div class="title_box">
            <span class="_title">Management System</span>
        </div>
        <div class="sign_box">            
            <div class="list_box flex-center-row">
                <select class="custom-select" name="groupnumber">
                    <option value="2">학생</option>
                    <option value="1">교수</option>
                </select>
            </div>
            <div class="list_box flex-center-row">
                <!--<span class="list_head">아이디</span>-->
                <input type="text" maxlength="8" placeholder="아이디 입력" name="id" value="${param.id}">
                <c:if test="${errors.id}">ID를 입력하세요.</c:if>
            </div>
            <div class="list_box flex-center-row">
               <!--<span class="list_head">패스워드</span>-->
                <input type="password" placeholder="비밀번호 입력" name="password" >
                <c:if test="${errors.password}">암호를 입력하세요.</c:if>
            </div>
            <div class="guide_box">
            	<c:if test="${errors.idOrPwNotMatch}">
					아이디와 암호가 일치하지 않습니다.
				</c:if>
            </div>
        </div>
        <div class="button_box flex-center-column">
            <button>Login</button>
            <br>
            <button>Join</button>
        </div>
    </div>
 </form>   
</body>
</html>