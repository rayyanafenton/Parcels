<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Staff Add Parcel</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>

.submit-btn {
	margin-top: 30px;
}
#nav-parcel{
	color: var(--clr-dark);
}
</style>
</head>

<body>

<%
	String email = (String) request.getAttribute("email");
%>
	<header>
		<nav>
			<img src="resources/images/parcel-logo.jpg" class="logo">
			<ul class="nav-list">
				<li><a href="staff-parcel-list?email=${email}" id="nav-parcel">Parcel</a></li>
				<li><a href="staff-view-profile?email=${email}" id="nav-profile">Profile</a></li>
				<li><a href="index.html" id="nav-logout">Log out</a></li>
			</ul>
		</nav>
	</header>
		
	<div class="page-title">
		<h2>Staff</h2>
	</div>

	<div class="center-nav">
		<div class="nav-option">
			<a href="staff-add-parcel?email=${email}" class="option-btn-1">Add Parcel</a>  
			<a href="staff-parcel-list?email=${email}" class="option-btn-2">Parcel List</a>  
			<a href="staff-search-parcel?email=${email}" class="option-btn-2">Search Parcel</a>
			<a href="staff-schedule-parcel?email=${email}" class="option-btn-2">Schedule</a>
		</div>
	</div>

	<div class="wrapper">

		<form action="staff-add-parcel?email=${email}" method="post">
			<div class="form-row">
				<div class="label-column">
					<label for="parcelID">Parcel ID</label>
				</div>
				<div class="input-column">
					<input type="text" id="parcelID" name="parcelID">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="ownerID">Owner ID</label>
				</div>
				<div class="input-column">
					<input type="text" id="ownerID" name="ownerID">
				</div>
			</div>
			
			<div class="form-row">
				<div class="label-column">
					<label for="parcelName">Parcel Name</label>
				</div>
				<div class="input-column">
					<input type="text" id="parcelName" name="parcelName">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="dateReceived">Date Received</label>
				</div>
				<div class="input-column">
					<input type="date" id="dateReceived" name="dateReceived">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="status">Status</label>
				</div>
				<div class="input-column">
					<select name="status" id="status">
						<option>Not Collected</option>
						<option>Collected</option>
					</select> 
				</div>
			</div>
			
			<input type="submit" class="submit-btn"> 
	
		</form>
	 	
	</div>

	<footer>@2022 All Rights Reserved</footer>

</body>
</html>