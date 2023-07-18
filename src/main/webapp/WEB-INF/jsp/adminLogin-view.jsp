<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Please enter your user name and password</h1>
	<form action="login.htm" method="post">
	Admin ID: <input type="number" name="adminID"/>
	Password: <input type="password" name="password"/>
	<input type="submit" value="Login"/>
	</form>
	
	<br><br>${requestScope.loginStatus}
	<br><br>
	<a href="/HospitalMIS">Hospital Home!</a>

</body>
</html>