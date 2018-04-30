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
    <title>Create Team</title>
</head>
<body class="flex-center-row">
<form action="createTeam.do" method="post">
<div class="center_box">
    <div class="title_box">
        <span class="_title">Management System</span>
    </div>
    <div class="sign_box">
        <div class="list_box flex-center-row">
            <select class="custom-select" name="groupNo">
                <option value=01>월요일오전</option>
				<option value=02>월요일오후</option>
				<option value=03>화요일오전</option>
				<option value=04>화요일오후</option>
				<option value=05>수요일오전</option>
				<option value=06>수요일오후</option>
				<option value=07>목요일오전</option>
				<option value=08>목요일오후</option>
				<option value=09>금요일오전</option>
				<option value=10>금요일오후</option>
            </select>
        </div>
    </div>
    <div class="sign_box">
        <div class="list_box flex-center-row">
            <select class="custom-select" name="teamNo">
             	<option value=01>1조</option>
      	 	 	<option value=02>2조</option>
     		 	<option value=03>3조</option>
     		 	<option value=04>4조</option>
      		 	<option value=05>5조</option>
      		 	<option value=06>6조</option>
      	 	 	<option value=07>7조</option>
      		 	<option value=08>8조</option>
      		 	<option value=09>9조</option>
      		 	<option value=10>10조</option>
      		 	<option value=11>11조</option>
      		 	<option value=12>12조</option>
            </select>
            <c:if test="${errors.teamNo}">팀번호를 입력하세요.</c:if>
  		    <c:if test="${errors.duplicateId}">이미 사용중인 팀번호입니다.</c:if>
        </div>
    </div>
    <div class="join_box">
    <div class="list_box flex-center-row">
            <input type="text" name="teamName" value="${param.teamName}" maxlength="8" placeholder="팀이름 입력">
            <c:if test="${errors.teamName}">팀이름를 입력하세요.</c:if>
			<c:if test="${errors.ExistTeam}">이미 사용중인 팀이름입니다.</c:if>
        </div>
    </div>
    <div class="sign_box">
    <div class="list_box flex-center-row">
       <select class="custom-select" name="teamSubject">
       		<option value='웹사이트' selected>웹사이트</option>
       		<option value='임베디드'>임베디드</option>
       		<option value='소프트웨어'>소프트웨어</option>
       		<option value='하드웨어'>하드웨어</option>      
       </select>    
    </div>
    <div class="list_box flex-center-row">
       <select class="custom-select" name="advisor">
       		<option value=0 selected>김점구</option>
      		<option value=1>정지문</option>
      		<option value=2>송은지</option>
      		<option value=3>나상엽</option>
     		<option value=4>황정희</option>
    		<option value=5>김현철</option>
   		    <option value=6>김정길</option>
    		<option value=7>문송철</option>
    		<option value=8>Matthew Oakley</option>
   		    <option value=9>기창진</option>  
       </select>    
    </div>
    <div class="button_box flex-center-column">
        <button type="submit">Create Team</button>
    </div>
</div>
</div>
</form>
</body>
</html>