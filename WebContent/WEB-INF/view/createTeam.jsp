<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>팀생성</title>
<link href="css/background_sub.css?ver=2" rel="stylesheet" type="text/css" >
<link href="css/joinForm.css?ver=2" rel="stylesheet" type="text/css">
</head>
<body>
<header class="title">
        <h1>Capstone Design Management System</h1>
</header>

<div id="wrapper">

<form action="CreateTeam.do" method="post">
<p id="sign_title">Create Team</p>
<p id="ClASS">
	<label for="groupNo">수업반</label><br>
	<select name="groupNo">
		<option value='월요일오전'>월요일오전</option>
		<option value='월요일오후'>월요일오후</option>
		<option value='화요일오전'>화요일오전</option>
		<option value='화요일오후'>화요일오후</option>
		<option value='수요일오전'>수요일오전</option>
		<option value='수요일오후'>수요일오후</option>
		<option value='목요일오전'>목요일오전</option>
		<option value='목요일오후'>목요일오후</option>
		<option value='금요일오전'>금요일오전</option>
		<option value='금요일오후'>금요일오후</option>
	</select>
<p id="TEAMNO">
   <label for="teamNo">조번호</label><br>
   <select name="teamNo">
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
   </select><c:if test="${errors.teamNo}">팀번호를 입력하세요.</c:if>
   <c:if test="${errors.duplicateId}">이미 사용중인 팀번호입니다.</c:if>
</p>
<p id="NAME">
   <label for="teamName">TeamName</label><br>
   <input type="text" name="teamName" value="${param.teamName}">
   <c:if test="${errors.teamName}">팀이름을 입력하세요.</c:if>
   <c:if test="${errors.duplicateId}">이미 사용중인 팀이름입니다.</c:if>
</p>
<p id="TEAMSUBJECT">
   <label for="teamSubject">Subject</label><br>
   <select name="teamSubject">
       <option value='웹사이트' selected>웹사이트</option>
       <option value='임베디트'>임베디드</option>
       <option value='소프트웨어'>소프트웨어</option>
       <option value='하드웨어'>하드웨어</option>
   </select>
</p>
<p id="ADVISOR">
   <label for="advisor">지도교수님</label><br>
   <select name="advisor">
      <option value='김점구' selected>김점구</option>
      <option value='정지문'>정지문</option>
      <option value='송은지'>송은지</option>
      <option value='나상엽'>나상엽</option>
      <option value='황정희'>황정희</option>
      <option value='김현철'>김현철</option>
      <option value='김정길'>김정길</option>
      <option value='문송철'>문송철</option>
      <option value='Oakley'>Matthew Oakley</option>
      <option value='기창진'>기창진</option>
   </select>
</p>
<input type="submit" value="Create">
</form>
</div>
</body>
</html>