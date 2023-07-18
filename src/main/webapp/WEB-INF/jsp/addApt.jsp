<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
	<h3>Book Appointment</h3>
	<form action="bookapt.htm" method="post">
	Choose Procedure: 
	<select name="proc" id="proc">
		<c:forEach var="j" items="${procs}">
			<option value="${j.getId()}">${j.getName()}</option>
		</c:forEach>
	</select>
	<br>
	<br>
	<div id="div1"></div>
	<div id="div2"></div>
	<br><br>
	</form>

		
	
	
	<script type="text/javascript">
	
	$("#proc").change(function(){
		
		$('#div1').empty();
		
		$.post( "getProcDoc.htm", { procId:$("#proc").val()},function(data){
			
			$('<label>Select Doctor: </label><select name="docList" id="docList"></select>').appendTo("#div1");
			
			
			$.each(data, function(index, value) {
			    $('#docList').append('<option value='+value.id+'>' + value.lname + '</option>');
			});
			
			var currentDate = new Date();
			currentDate.setDate(currentDate.getDate() + 1);
			
			var maxDate = new Date();
			maxDate.setDate(maxDate.getDate()+31);
			
			$('<br><br><label>Input Date: </label><input name="date1" id="date1" type="date"/>').appendTo("#div1").change(function(){
				
				
				$('#div2').empty();
				
				
				$.post( "getSlots.htm", {procId:$("#proc").val(), date:$("#date1").val(), doc:$('#docList').val() },function(data){
					
					if(data.length>0)
					{
						
						
						$('<br><br><label>Choose Slot: </label><select name="slotList" id="slotList"></select>').appendTo("#div2");
						
										
						$.each(data, function(index, value) {
						    $('#slotList').append('<option value='+value+'>' + value + '</option>');
						});
						
						
						$('<br><br><input id="input_submit" type="submit" value="Book Appointment" />').appendTo("#div2");
					}
					
					else
					{
						$('<label>No slots available.</label>').appendTo("#div2");
					}
					
				});
			
		});
			
		});
				
	});
	
		
	
	
		
	</script>
	
	
	

</body>
</html>