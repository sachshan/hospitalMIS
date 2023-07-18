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

	 <h2>Hello Dr. ${doctor.getFname()} ${doctor.getLname()}</h2>
	 
	 <h3> Today's Appointments</h3>
	 <table id="ttab">
		<tr>
			<th>Procedure</th>
			<th>Patient</th>
			<th>Date</th>
			<th>Time</th>
			<th>Cancel</th>
			<th>Appointment Completed</th>
		</tr>
	<c:forEach var="j" items="${aptT}">
		<tr id="${j.getId()}">
			<td>${j.getProc().getName()}</td>
			<td>${j.getPatient().getFname()} ${j.getPatient().getLname() }</td>
			<td>${j.getDate()}</td>
			<td>${j.getBeginTime()}</td>
			<td><button class="cancel">Cancel</button></td>
			<td><button class="completed">Completed</button></td>
		</tr>
	</c:forEach>
	</table>
	 
	 
	 <h3>Completed(Report Pending)</h3>
	 <table id="ctab">
		<tr>
			<th>Procedure</th>
			<th>Patient</th>
			<th>Date</th>
			<th>Time</th>
			<th>Enter Report</th>
			<th>Submit</th>
		</tr>
	<c:forEach var="j" items="${aptC}">
		<tr id="${j.getId()}">
			<td>${j.getProc().getName()}</td>
			<td>${j.getPatient().getFname()} ${j.getPatient().getLname() }</td>
			<td>${j.getDate()}</td>
			<td>${j.getBeginTime()}</td>
			<td><input type="text" class="report"/></td>
			<td><button class="finish_report">Submit Report</button>
		</tr>
	</c:forEach>
	</table>
	 
	 
	 <h3>Upcoming Appointments</h3>
	<table id="utab">
		<tr>
			<th>Procedure</th>
			<th>Patient</th>
			<th>Date</th>
			<th>Time</th>
			<th>Remove</th>
		</tr>
	<c:forEach var="j" items="${aptU}">
		<tr id="${j.getId()}">
			<td>${j.getProc().getName()}</td>
			<td>${j.getPatient().getFname()} ${j.getPatient().getLname() }</td>
			<td>${j.getDate()}</td>
			<td>${j.getBeginTime()}</td>
			<td><button class="cancel">Cancel</button>
		</tr>
	</c:forEach>
	</table>
	
	<h3>Past Appointments</h3>
	<table id="ptab">
		<tr>
			<th>Procedure</th>
			<th>Patient</th>
			<th>Date</th>
			<th>Time</th>
			<th>Report</th>
		</tr>
	<c:forEach var="j" items="${aptP}">
		<tr id="${j.getId()}">
			<td>${j.getProc().getName()}</td>
			<td>${j.getPatient().getFname()} ${j.getPatient().getLname() }</td>
			<td>${j.getDate()}</td>
			<td>${j.getBeginTime()}</td>
			<td>${j.getResult()}</td>
		</tr>
	</c:forEach>
	</table>
	
	<a href="logout.htm" target="_self">Logout</a>
	 
	 <script>
		
	$(".cancel").click(function(){
		
		var $item = $(this).closest("tr");
		console.log($item.attr('id'));	
		
		$.post( "deleteApt.htm", {aptId:$item.attr('id')},function(data){
			
			$item.remove();
			
		});
			
	});
	
	$(".completed").click(function(){
		
		var $item = $(this).closest("tr");
		console.log($item.attr('id'));	
		
		$.post( "completeApt.htm", {aptId:$item.attr('id')},function(data){
				
			
			$('#ctab tr:first').after('<tr id="'+$item.attr('id')+'"><td>'+$item.find("td").eq(0).html()+'</td><td>'+$item.find("td").eq(1).html()+'</td><td>'+$item.find("td").eq(2).html()+'</td><td>'+$item.find("td").eq(3).html()+'</td><td><input type="text" class="report"/></td><td><button class="finish_report">Submit Report</button></td></tr>');
			$item.remove();
		});
			
	});
	
	$(document.body).on('click', '.finish_report' ,function(){
	
		
		var $item = $(this).closest("tr");
		console.log($item.attr('id'));	
		
		$.post( "reportApt.htm", {aptId:$item.attr('id'), report:$item.find("td").eq(4).find("input").val()},function(data){
				
			
			$('#ptab tr:first').after('<tr id="'+$item.attr('id')+'"><td>'+$item.find("td").eq(0).html()+'</td><td>'+$item.find("td").eq(1).html()+'</td><td>'+$item.find("td").eq(2).html()+'</td><td>'+$item.find("td").eq(3).html()+'</td><td>'+$item.find("td").eq(4).find("input").val()+'</td></tr>');
			$item.remove();
		});
			
	});
	
	
	</script>
	 

</body>
</html>