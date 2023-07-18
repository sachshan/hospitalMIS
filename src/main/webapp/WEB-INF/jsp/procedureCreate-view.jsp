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
	<form:form  modelAttribute="proc" method="post">
		Procedure Name: <form:input path="name"/> <form:errors path="name" /><br><br>
		
		Doctor: <input name="email" type="email"/> <form:errors path="email" /><br><br>
		User: <form:input path="uname"/> <form:errors path="uname" /><br><br>
		Password: <input type="password" name="password" /> <form:errors path="password" /><br>
		<br>
		<input type="submit" value="Register">
		
	</form:form>
</body>
</html>