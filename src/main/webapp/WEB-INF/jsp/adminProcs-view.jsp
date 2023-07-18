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
<h3>Procedures</h3>
	<table id="tab">
		<tr>
			<th>Procedure Name</th>
			<th>Duration</th>
			<th>Cost</th>
			<th>Doctors</th>
			<th>Remove</th>
		</tr>
	<c:forEach var="j" items="${procs}">
		<tr id="${j.getId()}">
			<td>${j.getName()}</td>
			<td>${j.getDuration()} min</td>
			<td>USD ${j.getCost()}</td>
			<td>
				<c:forEach var="i" items="${j.getDoctors()}">
					Dr. ${i.getFname()} ${i.getLname()}
				</c:forEach>
			
			</td>
			<td><button class="remove">Remove</button></td>
		</tr>
	</c:forEach>
	</table>
	
	<a href="login.htm" target="_self">Go to Home Page</a>
	
	<script>
	$(".remove").click(function(){
		
		var $item = $(this).closest("tr");
		console.log($item.attr('id'));	
		
		$.post( "deleteProc.htm", {procId:$item.attr('id')},function(data){
			
			$item.remove();
			
		});
			
	});
	</script>

</body>
</html>