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
    <title>Join Team</title>
</head>
<body class="flex-center-row">
<div class="center_box">
<form action="selectTeam.do" method="post">
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
        </div>
    </div>
    	팀 이름 : <%= session.getAttribute("tName") %><br>
    	지도 교수님 : <%= session.getAttribute("advisor") %>
    <div class="button_box flex-center-column">
        <button type="submit">Search</button>    
    </div>
    </form>
    <form action="joinTeam.do" method="post">
    	<div class="button_box flex-center-column">
        	<button type="submit">Join Team</button>    
    	</div>
    </form>
</div>



</body>
</html>