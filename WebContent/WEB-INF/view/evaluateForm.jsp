<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./css/evalForm/evaluationForm.css">
    <title>평가지</title>
</head>
<body class="flex-center-row">
<div class="center_box">
    <div class="title_box">
        <span class="_title">졸업작품 심사서</span>
    </div>
    <div class="evalForm_box">
        <div class="classInfo_box flex-space-row">
            <ul class="left_box left_txt">
                <li class="left_li">조 이름</li>
                <li class="left_li">팀원</li>
            </ul>
            <ul class="right_box">
                <li class="right_li">
                    <input type="text" name="teamName" value="${teamName}">
                </li>
                <c:forEach var="stu" items="${stulist}">
                	<li class="right_li">
                		<input type="text" name="memberName" value="${stu.name}">
                	</li>
	            </c:forEach>
            </ul>
        </div>
        <div class="check_box">
            <div class="question_box">
                <span class="question_txt">1.작품의 내용과 제목이 적절하게 부합되었는가?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val1" value="10">아주우수</li>
                    <li class="radio_txt"><input type="radio" name="val1" value="8">우수</li>
                    <li class="radio_txt"><input type="radio" name="val1" value="6">보통</li>
                    <li class="radio_txt"><input type="radio" name="val1" value="4">불량</li>
                    <li class="radio_txt"><input type="radio" name="val1" value="2">매우불량</li>
                </ul>
                <span class="opinion">의견작성</span>
                <textarea cols="30" rows="3" name="comment1"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">2.작품의 내용이 독창적인가?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val2" value="10">아주우수</li>
                    <li class="radio_txt"><input type="radio" name="val2" value="8">우수</li>
                    <li class="radio_txt"><input type="radio" name="val2" value="6">보통</li>
                    <li class="radio_txt"><input type="radio" name="val2" value="4">불량</li>
                    <li class="radio_txt"><input type="radio" name="val2" value="2">매우불량</li>
                </ul>
                <span class="opinion">의견작성</span>
                <textarea cols="30" rows="3" name="comment2"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">3.작품의 구성은 적절한가(목표설정, 요구분석, 설계, 구현 등)?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val3" value="10">아주우수</li>
                    <li class="radio_txt"><input type="radio" name="val3" value="8">우수</li>
                    <li class="radio_txt"><input type="radio" name="val3" value="6">보통</li>
                    <li class="radio_txt"><input type="radio" name="val3" value="4">불량</li>
                    <li class="radio_txt"><input type="radio" name="val3" value="2">매우불량</li>
                </ul>
                <span class="opinion">의견작성</span>
                <textarea cols="30" rows="3" name="comment3"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">4.작품의 완성도는?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val4" value="10">아주우수</li>
                    <li class="radio_txt"><input type="radio" name="val4" value="8">우수</li>
                    <li class="radio_txt"><input type="radio" name="val4" value="6">보통</li>
                    <li class="radio_txt"><input type="radio" name="val4" value="4">불량</li>
                    <li class="radio_txt"><input type="radio" name="val4" value="2">매우불량</li>
                </ul>
                <span class="opinion">의견작성</span>
                <textarea cols="30" rows="3" name="comment4"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">5.작품에 대한 문서유지와 논문 구성은 적절한가?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val5" value="10">아주우수</li>
                    <li class="radio_txt"><input type="radio" name="val5" value="8">우수</li>
                    <li class="radio_txt"><input type="radio" name="val5" value="6">보통</li>
                    <li class="radio_txt"><input type="radio" name="val5" value="4">불량</li>
                    <li class="radio_txt"><input type="radio" name="val5" value="2">매우불량</li>
                </ul>
                <span class="opinion">의견작성</span>
                <textarea cols="30" rows="3" name="comment5"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">6.작품에 대한 소개문서는 적절한가?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val6" value="10">아주우수</li>
                    <li class="radio_txt"><input type="radio" name="val6" value="8">우수</li>
                    <li class="radio_txt"><input type="radio" name="val6" value="6">보통</li>
                    <li class="radio_txt"><input type="radio" name="val6" value="4">불량</li>
                    <li class="radio_txt"><input type="radio" name="val6" value="2">매우불량</li>
                </ul>
                <span class="opinion">의견작성</span>
                <textarea cols="30" rows="3" name="comment6"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">7.팀원간의 협력이 잘 이루어졌는가?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val7" value="10">아주우수</li>
                    <li class="radio_txt"><input type="radio" name="val7" value="8">우수</li>
                    <li class="radio_txt"><input type="radio" name="val7" value="6">보통</li>
                    <li class="radio_txt"><input type="radio" name="val7" value="4">불량</li>
                    <li class="radio_txt"><input type="radio" name="val7" value="2">매우불량</li>
                </ul>
                <span class="opinion">의견작성</span>
                <textarea cols="30" rows="3" name="comment7"></textarea>
            </div>
        </div>
    </div>
    <div class="result_box">
        <div class="lastEval_box">
            <span class="result_txt">최종판정</span>
            <ul class="btn_box flex-space-row">
                <li class="radio_txt"><input type="radio" name="result"> 합격(45점 이상)</li>
                <li class="radio_txt"><input type="radio" name="result"> 재심사(35 ~ 44점)</li>
                <li class="radio_txt"><input type="radio" name="result"> 불합격(34 이하)</li>
            </ul>
        </div>
        <div class="date_box">
            <span class="date_txt">2018.00.00</span>
        </div>
        <div class="sign_here">
            심사위원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(서명)
        </div>
    </div>
</div>
</body>
</html>