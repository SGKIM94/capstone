<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/evalForm/evaluationForm.css">
    <title>최종 심사서</title>
</head>
<body class="flex-center-row">
<div class="center_box">
	<form action="EvaluateFinal.do" method="post" name="evalfinal">
    <div class="title_box">
        <span class="_title">졸업작품 최종 심사서</span>
    </div>
    <div class="evalForm_box">
        <div class="classInfo_box flex-space-row">
            <ul class="left_box left_txt">
                <li class="left_li">조 이름</li>
                <li class="left_li">팀원</li>
            </ul>
            <ul class="right_box">
                <li class="right_li">
                    <span>${teamName}</span>
                </li>
                <c:forEach var="stu" items="${memberList}">
                	<li class="right_li">
                		<span>${stu.id}&nbsp;&nbsp;&nbsp;&nbsp;${stu.name}</span>
                	</li>
	            </c:forEach>
            </ul>
        </div>
        <div class="check_box">
            <div class="question_box">
                <span class="question_txt">최종 평가</span>
                <span class="opinion">의견작성</span>
                <textarea cols="30" rows="3" name="comment1">${c1}</textarea>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val_1" value="1"<c:if test = "${ '1' eq v1}">checked</c:if>>합격</li>
                    <li class="radio_txt"><input type="radio" name="val_1" value="2"<c:if test = "${ '2' eq v1}">checked</c:if>>재심사</li>
                    <li class="radio_txt"><input type="radio" name="val_1" value="3"<c:if test = "${ '3' eq v1}">checked</c:if>>불합격</li>
                </ul>
            </div>
    	</div>
    </div>
    <div class="result_box">
        <div class="lastEval_box">
            <span class="result_txt">평가</span><span id="result">0점</span>
            <ul class="btn_box flex-space-row">
                <li class="radio_txt">
             	<button class="option-button" name="select" value="save">저장</button>
             	<input type="hidden" name="team_no" value="${team_no}">
             	</li>
                <li class="radio_txt"><button class="option-button" name="select" value="complete" >최종 평가</button></li>
            </ul>
        </div>
    </div>
    </form>
</div>
</body>
</html>