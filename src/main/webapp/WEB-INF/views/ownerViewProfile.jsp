<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Owner View Profile</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
.nav-option {
	display: grid;
	grid-template-columns: auto auto;
	gap: 0px;
}
.center-nav {
	max-width: 500px;
}
#nav-profile{
	color: var(--clr-dark);
}
</style>
</head>

<body>
<%@page import="com.app.model.Owner, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<%
	String email = (String)request.getAttribute("email"); 
	Owner owner = (Owner)request.getAttribute("owner");
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
			<a href="owner-view-profile?email=${email}" class="option-btn-1">View Profile</a> 
			<a href="owner-edit-profile?email=${email}" class="option-btn-2">Edit Profile</a>
		</div>
	</div>

	<div class="wrapper">

		<form>
			<div class="form-row">
				<div class="label-column">
					<label for="firstName">First Name</label>
				</div>
				<div class="input-column">
					<input type="text" id="firstName" name="firstName" value="<%= owner.getFirstName()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="lastName">Last Name</label>
				</div>
				<div class="input-column">
					<input type="text" id="lastName" name="lastName" value="<%= owner.getLastName()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="email">Email</label>
				</div>
				<div class="input-column">
					<input type="email" id="email" name="email" value="<%= owner.getEmail()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="phoneNumber">Phone Number</label><br>
				</div>
				<div class="input-column">
					<input type="text" id="phoneNumber" name="phoneNumber" value="<%= owner.getPhoneNumber()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="password">Password</label>
				</div>
				<div class="input-column">
					<input type="password" id="password" name="password" value="<%= owner.getPassword()%>">
				</div>
			</div>
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

</body>
</html>