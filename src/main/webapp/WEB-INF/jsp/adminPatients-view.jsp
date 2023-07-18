<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, th, td {
  border: 1px solid black;
  padding: 5px;
}
table {
  border-spacing: 15px;
}
</style>
</head>
<body>
	
	<h3>Patients</h3>
	<table id="tab">
		<tr>
			<th>Patient Name</th>
			<th>Date of Birth</th>
			<th>Email</th>
			<th>Remove</th>
		</tr>
	<c:forEach var="j" items="${patients}">
		<tr id="${j.getId()}">
			<td>${j.getFname()} ${j.getLname()}</td>
			<td>${j.getDob()}</td>
			<td>${j.getEmail()}</td>
			<td><button class="remove">Remove</button></td>
		</tr>
	</c:forEach>
	</table>
	
	<a href="login.htm" target="_self">Go to Home Page</a>
	
	<script>
	$(".remove").click(function(){
		
		var $item = $(this).closest("tr");
		console.log($item.attr('id'));	
		
		$.post( "deletePatient.htm", {patientID:$item.attr('id')},function(data){
			
			$item.remove();
			
		});
			
	});
	</script>

</body>
</html>