<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parcels - Admin Edit Staff</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
.nav-option {
	display: grid;
	grid-template-columns: auto;
	gap: 0px;
}
.center-nav {
	max-width: 400px;
}
#nav-staff{
	color: var(--clr-dark);
}
</style>
</head>
<body>
<%@page import="com.app.model.Admin, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	Admin admin = (Admin) request.getAttribute("admin");
%>

	<header>
		<nav>
			<img src="resources/images/parcel-logo.jpg" class="logo">
			<ul class="nav-list">
				<li><a href="adminStaffList.jsp" id="nav-staff">Staff</a></li>
				<li><a href="adminParcelSummary.jsp" id="nav-parcel">Parcel</a></li>
				<li><a href="index.html" id="nav-logout">Log out</a></li>
			</ul>
		</nav>
	</header>

	<div class="page-title">
		<h2>Admin</h2>
	</div>

	<div class="center-nav">
		<div class="nav-option">
			<a class="option-btn-1">Edit Staff</a>
		</div>
	</div>
	
	<div class="wrapper">
		<div class="error-message"></div>
		<div class="successful-message"></div>
		
		<form action="admin-edit-staff" method="post">
			
			<input type="hidden" id="adminID" name="adminID" value="<%=admin.getAdminID()%>">
			
			<div class="form-row">
				<div class="label-column">
					<label for="firstName">First Name</label>
				</div>
				<div class="input-column">
					<input type="text" id="firstName" name="firstName" value="<%=admin.getFirstName()%>">					
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="lastName">Last Name</label>
				</div>
				<div class="input-column">
					<input type="text" id="lastName" name="lastName" value="<%=admin.getLastName()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="email">Email</label>
				</div>
				<div class="input-column">
					<input type="email" id="email" name="email" value="<%=admin.getEmail()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="password">Password</label>
				</div>
				<div class="input-column">
					<input type="password" id="password" name="password" value=<%=admin.getPassword() %>>
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="phoneNumber">Phone Number</label><br>
				</div>
				<div class="input-column">
					<input type="text" id="phoneNumber" name="phoneNumber" value="<%=admin.getPhoneNumber()%>">
				</div>
			</div>

			<input class="submit-btn" type="submit">
		</form>

	</div>

	<footer>@2022 All Rights Reserved</footer>
</body>
</html>