<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parcels - Owner Schedule Parcel</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
.nav-option{
	display: grid;
	grid-template-columns: auto;
	gap: 0px;
}
.center-nav{
	max-width: 400px;
}
.wrapper {
	background: var(- -clr-light);
	width: 90%;
	max-width: 1200px;
	min-width: 700px;
	padding: 50px 5%;
	border-radius: 10px;
	display: blocked;
	margin: 0 auto;
	margin-top: 40px;
	margin-bottom: 80px;
}
#nav-parcel{
	color: var(--clr-dark);
}
</style>
</head>
<body>

<%@page import="com.app.model.Schedule, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<%
	String email = (String)request.getAttribute("email"); 
	Schedule schedule = (Schedule)request.getAttribute("schedule"); 

%>
	<header>
		<nav>
			<img src="resources/images/parcel-logo.jpg" class="logo">
			<ul class="nav-list">
				<li><a href="owner-track-parcel?email=${email}" id="nav-parcel">Parcel</a></li>
				<li><a href="owner-view-profile?email=${email}" id="nav-profile">Profile</a></li>
				<li><a href="index.html" id="nav-logout">Log out</a></li>
			</ul>
		</nav>
	</header>

	<div class="page-title">
		<h2>Owner</h2>
	</div>

	<div class="center-nav">
		<div class="nav-option">
			<a  class="option-btn-1">Edit Schedule</a>
		</div>
	</div>

	<div class="wrapper">
		<div class="error-message"></div>
		<div class="successful-message"></div>
			
		<form action="owner-edit-schedule" method="post">
        	<input type="hidden" name="scheduleID" value="<%=schedule.getScheduleID()%>">
        	<input type="hidden" name="email" value="<%= email %>">
			
			<div class="form-row">
				<div class="label-column">
					<label for="parcels">Parcels</label>
				</div>
				<div class="input-column">
					<input type="text" id="parcels" name="parcels" value="<%=schedule.getParcels()%>" disabled>
				</div>
			</div>
			
			<div class="form-row">
				<div class="label-column">
					<label for="scheduledDate">Schedule Date</label>
				</div>
				<div class="input-column">
					<input type="date" id="scheduledDate" name="scheduledDate" value="<%=schedule.getScheduledDate()%>" onchange="fetchAvailableSlots()">
				</div>
			</div>
			
			<div class="form-row">
				<div class="label-column">
					<label for="pScheduleTime">Schedule Time</label>
				</div>
				<div class="input-column">
					<select id="scheduledTime" name="scheduledTime">
						<option><%=schedule.getScheduledTime()%></option>
					</select>
					<p style="font-size: 14px;">Note: Office hours are 14:00 to 17:00</p>
				</div>
			</div>
			
			<input type="submit" class="submit-btn">
		</form>
	</div>


	<div class="contact-us">
		<div style="margin-top: 30px;">
			<h2>contact us</h2>
		</div>
		<div>
			<p>Phone: +60-123123123</p>
			<p>E-mail: parcel@xmu.edu.my</p>
			<p>Visit us on B1#117</p>
		</div>
	</div>

	<footer>@2022 All Rights Reserved</footer>
<script>
	document.addEventListener('DOMContentLoaded', (event) => {
	    fetchAvailableSlots();
	});
	function fetchAvailableSlots() {
	    const scheduledDate = document.getElementById('scheduledDate').value;
	    const existingScheduledTime = '<%=schedule.getScheduledTime()%>';

	    fetch('owner-available-slot', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify({ scheduledDate: scheduledDate })
	    })
	    .then(response => response.json())
	    .then(data => {
	        const select = document.getElementById('scheduledTime');
	        select.innerHTML = ''; 
	        
	        const existingOption = document.createElement('option');
	        existingOption.value = existingScheduledTime;
	        existingOption.textContent = existingScheduledTime;
	        select.appendChild(existingOption);
	        
	        const startHour = 14;
	        const endHour = 17;
	        const interval = 3;
	
	        function isExcluded(time) {
	            return data.includes(time);
	        }
	
	        for (let hour = startHour; hour < endHour; hour++) {
	            for (let minute = 0; minute < 60; minute += interval) {
	                const hourString = String(hour).padStart(2, '0');
	                const minuteString = String(minute).padStart(2, '0');
	                const time = hourString + ":" + minuteString;
	
	                if (!isExcluded(time)) {
	                    const option = document.createElement('option');
	                    option.value = time;
	                    option.textContent = time;
	                    select.appendChild(option);
	                }
	            }
	        }
	        
	        select.value = existingScheduledTime;

	    })
	    .catch(error => console.error('Error:', error));
	}

</script>

</body>
</html>