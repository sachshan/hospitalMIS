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
 <h2>Hello ${patient.getFname()} ${patient.getLname()}</h2>

	<h3><a href="bookapt.htm">Book Appointment</a></h3>
	
	<h3>Upcoming Appointments</h3>
	<table id="tab">
		<tr>
			<th>Procedure</th>
			<th>Doctor</th>
			<th>Date</th>
			<th>Time</th>
			<th>Duration</th>
			<th>Remove</th>
		</tr>
	<c:forEach var="j" items="${aptU}">
		<tr id="${j.getId()}">
			<td>${j.getProc().getName()}</td>
			<td>Dr. ${j.getDoc().getFname()} ${j.getDoc().getLname() }</td>
			<td>${j.getDate()}</td>
			<td>${j.getBeginTime()}</td>
			<td>${j.getProc().getDuration()} min</td>
			<td><button class="cancel">Cancel</button></td>
		</tr>
	</c:forEach>
	</table>
	
	
	<h3>Completed Appointments</h3>
	<table id="tab">
		<tr>
			<th>Procedure</th>
			<th>Doctor</th>
			<th>Date</th>
			<th>Time</th>
			<th>Report</th>
			<th>PDF</th>
		</tr>
	<c:forEach var="j" items="${aptC}">
		<tr id="${j.getId()}">
			<td>${j.getProc().getName()}</td>
			<td>Dr. ${j.getDoc().getFname()} ${j.getDoc().getLname() }</td>
			<td>${j.getDate()}</td>
			<td>${j.getBeginTime()}</td>
			<td>
			
				<c:choose>
				    <c:when test="${j.getResult()==null}">
				        Report not made yet.
				    </c:when>    
				    <c:otherwise>
				        ${j.getResult()}
				    </c:otherwise>
				</c:choose>
      		</td>
			<td>
			
			<c:choose>
				 <c:when test="${j.getResult()==null}">
				       	PDF not available.
				    </c:when>    
				    <c:otherwise>
				        <a href="pdfreport.pdf?id=${j.getId()}" target="_blank">Get PDF</a>
				    </c:otherwise>
				</c:choose>
			
			</td>
		</tr>
	</c:forEach>
	</table>
	
	<br>
	<br>
	<a href="logout.htm">Logout</a>
	
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