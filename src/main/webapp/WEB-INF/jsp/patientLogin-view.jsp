<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>Please enter your user name and password</h3>
	<form action="login.htm" method="post">
	User Name: <input type="text" name="uname"/>
	Password: <input type="password" name="password"/>
	<input type="submit" value="Login"/>
	
	</form>
	${requestScope.loginStatus}<br><br>
	Don't have an account with our hospital
	<a href="create.htm">Create Patient Account!</a>
	<br>
	<br>
	<a href="/HospitalMIS">Hospital Home!</a>

</body>
</html>