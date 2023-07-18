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
	<h3>Please enter patient details:</h3>
	<form:form  modelAttribute="patient" method="post">
		First Name: <form:input path="fname"/> <form:errors path="fname" /><br><br>
		Last Name: <form:input path="lname" /> <form:errors path="lname" /><br><br>
		Date of Birth: <input type="date" name="dob"/><%-- <form:errors path="dob" /> --%><br><br>
		Gender at Birth: <select  name="gender" >
		  <option value="M">Male</option>
		  <option value="F">Female</option>
		  <option value="O">Other</option>
		</select><br><br>
		Email: <input name="email" type="email"/> <form:errors path="email" /><br><br>
		User: <form:input path="uname"/> <form:errors path="uname" /><br><br>
		Password: <input type="password" name="password" /> <form:errors path="password" /><br>
		<br>
		<input type="submit" value="Register">
		
	</form:form>
</body>
</html>