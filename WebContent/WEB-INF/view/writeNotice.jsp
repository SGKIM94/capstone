<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 <form action="noticewrite.do" method="post" enctype="multipart/form-data">
<div class="center_box">
    <div class="title_box" margin:3px >
        <span class="_title" >Management System</span>
    </div>
    <div class="button_box flex-center-column">
	<div padding:1px>
	<div class="list_box flex-center-row">
	<p>
	<input type="text" name="noticetitle" value="${param.title}" maxlength="20" placeholder="제목 입력">
	<c:if test="${errors.title}">제목을 입력하세요.</c:if>
	</p>
	</div>
	</div>
	<div padding:3px>
	<div class="list_box flex-center-row">
	<p>
	<%--!!이부분을 파일업로드로 수정해야함. 자바스크립스 이용!! --%>
	<textarea name="content" rows="5" cols="30" maxlength="255" placeholder="공지사항 입력">${param.content}</textarea><br/>
	</p>
	</div>
	</div>
	<div padding:3px>
	<div class="list_box flex-center-row">
	<p>
	<input name="file" type = "file" size="15" placeholder="파일 선택"/>${param.file}<br/>
	</p>
	</div>
	<div padding:3px>
	<input type="submit" value="새 글 등록">
	</div>
	</div>
	</div>
</div>
</form>
</body>
</html>