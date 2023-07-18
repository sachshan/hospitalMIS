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
	<h3>Please enter Doctor details:</h3>
	<form:form  modelAttribute="doctor" method="post">
		First Name: <form:input path="fname"/> <form:errors path="fname" /><br><br>
		
		Last Name: <form:input path="lname" /> <form:errors path="lname" /><br><br>
		

		Specialty: <select  name="dept">
		  <option value="Cardiology">Cardiology</option>
		  <option value="Neurology">Neurology</option>
		  <option value="Ophthalmology">Ophthalmology</option>
		  <option value="Orthopedics">Orthopedics</option>
		</select><br><br>
		
		User: <form:input path="uname"/> <form:errors path="uname" /><br><br>
		Password: <input type="password" name="password" /> <form:errors path="password" /><br>
		<br>
		<input type="submit" value="Register">
		
	</form:form>
</body>
</html>