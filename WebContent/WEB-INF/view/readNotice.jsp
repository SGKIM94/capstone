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
<div class="center_box">
    <div class="title_box">
        <span class="_title">Management System</span>
    </div>
    <div class="sign_box">
        <div class="list_box flex-center-row">
            
        </div>
    </div>
    <div class="join_box_notice">
    	<span class="desc"><strong>번호</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${noticeData.notice.postNo}</span><br><br>
    	<span class="desc"><strong>작성자</strong>&nbsp;&nbsp;&nbsp;${noticeData.notice.writer.name}</span><br><br>
    	<span class="desc"><strong>제목</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${noticeData.notice.title}' /></span><br><br>
    	<span class="desc"><strong>내용</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${noticeData.content}'/></span><br><br><br><br><br>
    	
    	<form action="downloadFile.do" method="post" name="downFile">
    	<span class="desc"><strong>파일</strong>	  	
    	<a>
    	<input type="submit" class="submitLink" name="filename" value='${noticeData.file}'>
    	</a> 	
    	</span>
    	</form>
    	<br><br>
    	
    	<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="noticelist.do?pageNo=${pageNo}"><button class="prof_btn_small">목록</button></a>
		<a href="noticemodify.do?no=${noticeData.notice.postNo}"><button class="prof_btn_small">게시글수정</button></a>
		<a href="noticemodify.do?no=${noticeData.notice.postNo}"><button class="prof_btn_small">게시글삭제</button></a>
		<c:if test="${errors.NotExistNoticeFile}">파일이 존재하지 않습니다.</c:if>
		<c:if test="${authUser.id == noticeData.notice.writer.id}">
		
		</c:if>
    </div>
</div>
</body>
</html>