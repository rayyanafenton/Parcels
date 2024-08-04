<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parcels - Owner Schedule Time</title>
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
#nav-parcel{
	color: var(--clr-dark);
}
</style>
</head>
<body>
<%@ page import="com.app.model.Owner, com.app.dao.ScheduleDao, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String email = (String) request.getAttribute("email");
	String parcels = (String) request.getAttribute("parcels");
    Owner owner = (Owner) request.getAttribute("owner");
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
		<a class="option-btn-1">Schedule Time</a>
	</div>
</div>

<div class="wrapper">
	<div class="error-message"></div>

	<form id="dateForm" action="owner-schedule-time?email=${email}" method="post">
        <input type="hidden" name="email" value="<%= email %>">
		<input id="ownerID" type="hidden" name="ownerID" value="<%=owner.getOwnerID()%>">
		
		<div class="form-row">
			<div class="label-column">
				<label for="parcels">Parcels</label>
			</div>
			<div class="input-column">
				<input type="text" id="parcels" name="parcels" value="<%=parcels%>">
			</div>
		</div>
		
		<div class="form-row">
			<div class="label-column">
				<label for="scheduledDate">Schedule Date</label>
			</div>
			<div class="input-column">
				<input type="date" id="scheduledDate" name="scheduledDate" onchange="fetchAvailableSlots()">
			</div>
		</div>
		
		<div class="form-row">
			<div class="label-column">
				<label for="scheduledTime">Schedule Time</label>
			</div>
			<div class="input-column">
				<select id="scheduledTime" name="scheduledTime"></select>
				<p style="font-size: 14px;">Note: Office hours are 14:00 to 17:00</p>
			</div>
		</div>
		
		<input type="submit" class="submit-btn">
	</form>
</div>

<div class="contact-us">
	<div style="margin-top: 30px;">
		<h2>Contact Us</h2>
	</div>
	<div>
		<p>Phone: +60-123123123</p>
		<p>E-mail: parcel@xmu.edu.my</p>
		<p>Visit us on B1#117</p>
	</div>
</div>

<footer>@2022 All Rights Reserved</footer>

<script>
	function fetchAvailableSlots() {
	    const scheduledDate = document.getElementById('scheduledDate').value;

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
	    })
	    .catch(error => console.error('Error:', error));
	}
</script>

</body>
</html>
