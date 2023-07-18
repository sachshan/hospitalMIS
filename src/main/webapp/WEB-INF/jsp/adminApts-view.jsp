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
<h3>Upcoming Procedures</h3>
	<table id="tab">
		<tr>
			<th>Procedure</th>
			<th>Patient</th>
			<th>Doctor</th>
			<th>Date</th>
			<th>Time</th>
			<th>Remove</th>
		</tr>
	<c:forEach var="j" items="${aptU}">
		<tr id="${j.getId()}">
			<td>${j.getProc().getName()}</td>
			<td>${j.getPatient().getFname()} ${j.getPatient().getLname() }</td>
			<td>Dr. ${j.getDoc().getFname()} ${j.getDoc().getLname() }</td>
			<td>${j.getDate()}</td>
			<td>${j.getBeginTime()}</td>
			<td><button class="cancel">Cancel</button></td>
		</tr>
	</c:forEach>
	</table>
	
	
	<h3>Completed Procedures</h3>
	<table id="tab">
		<tr>
			<th>Procedure</th>
			<th>Patient</th>
			<th>Doctor</th>
			<th>Date</th>
			<th>Time</th>
		</tr>
	<c:forEach var="j" items="${aptC}">
		<tr id="${j.getId()}">
			<td>${j.getProc().getName()}</td>
			<td>${j.getPatient().getFname()} ${j.getPatient().getLname() }</td>
			<td>Dr. ${j.getDoc().getFname()} ${j.getDoc().getLname() }</td>
			<td>${j.getDate()}</td>
			<td>${j.getBeginTime()}</td>
			<td>
			
				<c:choose>
				    <c:when test="${j.getResult()==null}">
				        Report not uploaded yet.
				    </c:when>    
				    <c:otherwise>
				        Report has been uploaded.
				    </c:otherwise>
				</c:choose>
      		</td>
		</tr>
	</c:forEach>
	</table>
	
	<br>
	<br>
	<a href="login.htm">Home</a>
	
	<script>
		
	$(".cancel").click(function(){
		
		var $item = $(this).closest("tr");
		console.log($item.attr('id'));	
		
		$.post( "deleteApt.htm", {aptId:$item.attr('id')},function(data){
			
			$item.remove();
			
		});
			
	});
	
		
	</script>

</body>
</html>