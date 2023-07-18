<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
	<h3>Create a Procedure</h3>
	<form action="createProcedure.htm" method="post">
		Name: <input name="name"/> <form:errors path="name" /><br><br>
		Duration(min): <select name="duration">
			<option value="30">30 min</option>
			<option value="60">1 hour</option>
			<option value="90">1.5 hours</option>
			<option value="120">2 hours</option>
			<option value="150">2.5 hours</option>
			<option value="180">3 hours</option>
			<option value="210">3.5 hours</option>
			<option value="240">4 hours</option>
		</select><br><br>
		Cost: <input name="cost" type="number"/><form:errors path="cost" /><br><br>
		
		Choose Doctors: 
		<div id="div1">
		<br>
		<select name="doc">
		<c:forEach var="j" items="${doctors}">  
		   <option value="${j.getId()}">${j.getFname()} ${j.getLname()}</option> 
		</c:forEach>  
		</select>
		<br><br>
		
		<button id="bt1" type="button">Add doctor</button>
		
		</div>
		
		<br>
		<br>
		<input type="submit" value="Create Procedure">
	</form>
	
	<script>
		$("#bt1").click(function(){
			
		$('<label>Doctor : <label><select name="doc"><c:forEach var="j" items="${doctors}"><option value="${j.getId()}">${j.getFname()} ${j.getLname()}</option></c:forEach></select><br><br>').appendTo("#div1");
			
		});
		 
		 
		 
	
	</script>
		
		<form method="post" action="login.htm">
			<input type="submit" value="Go to Admin Home">
		</form>
		
		<a href="/HospitalMIS" target="_self">Go to Hospital Home</a>

	
</body>
</html>