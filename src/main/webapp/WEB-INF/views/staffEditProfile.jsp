<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Staff Edit Profile</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
.nav-option{
	display: grid;
	grid-template-columns: auto auto;
	gap: 0px;
}
.center-nav{
	max-width: 500px;
}
#nav-profile{
	color: var(--clr-dark);
}
</style>
</head>

<body>
<%@page import="com.app.model.Admin, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
    String email = (String) request.getAttribute("email");
    Admin staff = (Admin) request.getAttribute("staff");
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
			<a href="staff-view-profile?email=${email}" class="option-btn-2">View Profile</a>  
			<a href="staff-edit-profile?email=${email}" class="option-btn-1">Edit Profile</a>
		</div>
	</div>

	<div class="wrapper">
		<div class="error-message"></div>
		<div class="successful-message"></div>

		<form action="staff-edit-profile?email=${email}" method="post">
 
 			<input type="hidden" id="staffID" name="staffID" value="<%=staff.getAdminID()%>">
		
			<div class="form-row">
				<div class="label-column">
					<label for="firstName">First Name</label>
				</div>
				<div class="input-column">
					<input type="text" id="firstName" name="firstName" value="<%=staff.getFirstName()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="lastName">Last Name</label>
				</div>
				<div class="input-column">
					<input type="text" id="lastName" name="lastName" value="<%=staff.getLastName()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="email">Email</label>
				</div>
				<div class="input-column">
					<input type="email" id="email" name="email" value="<%=staff.getEmail()%>">
				</div>
			</div>
			
			<div class="form-row">
				<div class="label-column">
					<label for="password">Password</label>
				</div>
				<div class="input-column">
					<input type="password" id="password" name="password" value=<%=staff.getPassword()%>>
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="phoneNumber">Phone Number</label>
				</div>
				<div class="input-column">
					<input type="text" id="phoneNumber" name="phoneNumber" value="<%=staff.getPhoneNumber()%>">
				</div>
			</div>
			
			<input type="submit" class="submit-btn">
			
		</form>
	</div>

	<footer>@2022 All Rights Reserved</footer>

</body>
</html>