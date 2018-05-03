<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/join/join.css">
    <title>Join</title>
</head>
<body class="flex-center-row">
<form action="join.do" method="post">
<div class="center_box">
    <div class="title_box">
        <span class="_title">Management System</span>
    </div>
    <div class="sign_box">
        <div class="list_box flex-center-row">
            
        </div>
    </div>
    <div class="join_box">
    	<span class="desc">번호 <br> ${noticeData.article.fileNo}</span>
    	<span class="desc">작성자 <br> ${noticeData.article.writer.writerId}</span>
    	<span class="desc">제목 <br> <c:out value='${noticeData.article.title}' /></span>
    	<span class="desc">내용 <br> <c:out value='${noticeData.content}'/></span>
    	<br><br>
    	
    	<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="teamlist.do?pageNo=${pageNo}"><button class="prof_btn">목록</button></a>
		<c:if test="${authUser.id == noticeData.article.writer.writerId}">
		<a href="teamarticlemodify.do?fileNo=${noticeData.article.fileNo}"><button class="prof_btn">게시글수정</button></a>
		<a href="teamarticledelete.do?fileNo=${noticeData.article.fileNo}"><button class="prof_btn">게시글삭제</button></a>
		</c:if>
    </div>
</div>
</form>
</body>
</html>