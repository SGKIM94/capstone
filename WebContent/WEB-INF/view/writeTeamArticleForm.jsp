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
<form action="join.do" method="post">
<div class="center_box">
    <div class="title_box">
        <span class="_title">Management System</span>
    </div>   
    <form action="teamwrite.do" method="post" enctype="multipart/form-data">
	    <div class="join_box">
			<div class="list_box flex-center-row">
				<input type="text" name="title" value="${param.title}" maxlength="20" placeholder="제목 입력">
				<c:if test="${errors.title}">제목을 입력하세요.</c:if>
			</div>
			파일:<br/>
				<%--!!이부분을 파일업로드로 수정해야함. 자바스크립스 이용!! --%>
				<label for="filetype">문서타입</label><br>
				<select name="filetype">
			    	<option value="0">종류</option>
			    	<option value="a">회의록</option>
			       	<option value="b">제안서</option>
			       	<option value="c">요구분석서</option>
			       	<option value="d">설계서</option>
			       	<option value="e">구현서</option>
			       	<option value="f">형상관리서</option>
			       	<option value="g">메뉴얼</option>
			       	<option value="h">최종보고서</option>  
				</select>
				<input name="file" type = "file"/>${param.file}<br/>
			<div class="button_box flex-center-column">
				<input type="submit" value="새 글 등록">
			</div>
		</div>
	</form>
</div>
</form>
</body>
</html>