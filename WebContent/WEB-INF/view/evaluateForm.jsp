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
    <title>����</title>
</head>
<body class="flex-center-row">
<div class="center_box">
    <div class="title_box">
        <span class="_title">������ǰ �ɻ缭</span>
    </div>
    <div class="evalForm_box">
        <div class="classInfo_box flex-space-row">
            <ul class="left_box left_txt">
                <li class="left_li">�� �̸�</li>
                <li class="left_li">����</li>
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
                <span class="question_txt">1.��ǰ�� ����� ������ �����ϰ� ���յǾ��°�?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val1" value="10">���ֿ��</li>
                    <li class="radio_txt"><input type="radio" name="val1" value="8">���</li>
                    <li class="radio_txt"><input type="radio" name="val1" value="6">����</li>
                    <li class="radio_txt"><input type="radio" name="val1" value="4">�ҷ�</li>
                    <li class="radio_txt"><input type="radio" name="val1" value="2">�ſ�ҷ�</li>
                </ul>
                <span class="opinion">�ǰ��ۼ�</span>
                <textarea cols="30" rows="3" name="comment1"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">2.��ǰ�� ������ ��â���ΰ�?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val2" value="10">���ֿ��</li>
                    <li class="radio_txt"><input type="radio" name="val2" value="8">���</li>
                    <li class="radio_txt"><input type="radio" name="val2" value="6">����</li>
                    <li class="radio_txt"><input type="radio" name="val2" value="4">�ҷ�</li>
                    <li class="radio_txt"><input type="radio" name="val2" value="2">�ſ�ҷ�</li>
                </ul>
                <span class="opinion">�ǰ��ۼ�</span>
                <textarea cols="30" rows="3" name="comment2"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">3.��ǰ�� ������ �����Ѱ�(��ǥ����, �䱸�м�, ����, ���� ��)?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val3" value="10">���ֿ��</li>
                    <li class="radio_txt"><input type="radio" name="val3" value="8">���</li>
                    <li class="radio_txt"><input type="radio" name="val3" value="6">����</li>
                    <li class="radio_txt"><input type="radio" name="val3" value="4">�ҷ�</li>
                    <li class="radio_txt"><input type="radio" name="val3" value="2">�ſ�ҷ�</li>
                </ul>
                <span class="opinion">�ǰ��ۼ�</span>
                <textarea cols="30" rows="3" name="comment3"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">4.��ǰ�� �ϼ�����?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val4" value="10">���ֿ��</li>
                    <li class="radio_txt"><input type="radio" name="val4" value="8">���</li>
                    <li class="radio_txt"><input type="radio" name="val4" value="6">����</li>
                    <li class="radio_txt"><input type="radio" name="val4" value="4">�ҷ�</li>
                    <li class="radio_txt"><input type="radio" name="val4" value="2">�ſ�ҷ�</li>
                </ul>
                <span class="opinion">�ǰ��ۼ�</span>
                <textarea cols="30" rows="3" name="comment4"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">5.��ǰ�� ���� ���������� �� ������ �����Ѱ�?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val5" value="10">���ֿ��</li>
                    <li class="radio_txt"><input type="radio" name="val5" value="8">���</li>
                    <li class="radio_txt"><input type="radio" name="val5" value="6">����</li>
                    <li class="radio_txt"><input type="radio" name="val5" value="4">�ҷ�</li>
                    <li class="radio_txt"><input type="radio" name="val5" value="2">�ſ�ҷ�</li>
                </ul>
                <span class="opinion">�ǰ��ۼ�</span>
                <textarea cols="30" rows="3" name="comment5"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">6.��ǰ�� ���� �Ұ������� �����Ѱ�?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val6" value="10">���ֿ��</li>
                    <li class="radio_txt"><input type="radio" name="val6" value="8">���</li>
                    <li class="radio_txt"><input type="radio" name="val6" value="6">����</li>
                    <li class="radio_txt"><input type="radio" name="val6" value="4">�ҷ�</li>
                    <li class="radio_txt"><input type="radio" name="val6" value="2">�ſ�ҷ�</li>
                </ul>
                <span class="opinion">�ǰ��ۼ�</span>
                <textarea cols="30" rows="3" name="comment6"></textarea>
            </div>
            <div class="question_box">
                <span class="question_txt">7.�������� ������ �� �̷�����°�?</span>
                <ul class="btn_box flex-space-row">
                    <li class="radio_txt"><input type="radio" name="val7" value="10">���ֿ��</li>
                    <li class="radio_txt"><input type="radio" name="val7" value="8">���</li>
                    <li class="radio_txt"><input type="radio" name="val7" value="6">����</li>
                    <li class="radio_txt"><input type="radio" name="val7" value="4">�ҷ�</li>
                    <li class="radio_txt"><input type="radio" name="val7" value="2">�ſ�ҷ�</li>
                </ul>
                <span class="opinion">�ǰ��ۼ�</span>
                <textarea cols="30" rows="3" name="comment7"></textarea>
            </div>
        </div>
    </div>
    <div class="result_box">
        <div class="lastEval_box">
            <span class="result_txt">��������</span>
            <ul class="btn_box flex-space-row">
                <li class="radio_txt"><input type="radio" name="result"> �հ�(45�� �̻�)</li>
                <li class="radio_txt"><input type="radio" name="result"> ��ɻ�(35 ~ 44��)</li>
                <li class="radio_txt"><input type="radio" name="result"> ���հ�(34 ����)</li>
            </ul>
        </div>
        <div class="date_box">
            <span class="date_txt">2018.00.00</span>
        </div>
        <div class="sign_here">
            �ɻ�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(����)
        </div>
    </div>
</div>
</body>
</html>