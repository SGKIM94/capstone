<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>암호 변경</title>
<link href="css/background_sub.css?ver=2" rel="stylesheet" type="text/css" >
<link href="css/ChangePwdForm.css?ver=2" rel="stylesheet" type="text/css" >
</head>
<body>
<header class="title">
        <h1>Capstone Design Management System</h1>
</header>

<div id="wrapper">

<form action="changeTeam.do" method="post">
<p id="Change_Team">Change Team</p>
<p id="PWD">
	<label for="curPwd">Current Password</label><br>
	<br/><input type="password" name="curPwd">
	<c:if test="${errors.curPwd}">현재 암호를 입력하세요.</c:if>
	<c:if test="${errors.badCurPwd}">현재 암호가 일치하지 않습니다.</c:if>
</p>
<p id="TEAMNO">	
	<label for="newTeam">조번호</label><br>
   	<select name="newTeam">
      <option value=1>1조</option>
      <option value=2>2조</option>
      <option value=3>3조</option>
      <option value=4>4조</option>
      <option value=5>5조</option>
      <option value=6>6조</option>
      <option value=7>7조</option>
      <option value=8>8조</option>
      <option value=9>9조</option>
      <option value=10>10조</option>
      <option value=11>11조</option>
      <option value=12>12조</option>
   </select><c:if test="${errors.teamNo}">팀번호를 입력하세요.</c:if>
   <c:if test="${errors.duplicateId}">이미 사용중인 팀번호입니다.</c:if>
	<c:if test="${errors.newTeam}">새 팀명을 입력하세요.</c:if>
</p>
<input type="submit" value="Change Team">
</form>
</div>

</body>
</html>