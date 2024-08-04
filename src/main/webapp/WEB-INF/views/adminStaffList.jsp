<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Admin Staff List</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
.wrapper {
	background: var(- -clr-light);
	width: 90%;
	min-width: 700px;
	padding: 50px 5%;
	border-radius: 10px;
	display: blocked;
	margin: 0 auto;
	margin-top: 40px;
	margin-bottom: 80px;
}

#nav-staff{
	color: var(--clr-dark);
}

.nav-option {
	display: grid;
	grid-template-columns: auto auto;
	gap: 0px;
}
.center-nav {
	max-width: 500px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

</style>
</head>

<body>

<%@page import="com.app.model.Admin, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
    List<Admin> list = (List<Admin>) request.getAttribute("list");
%>

	<header>
		<nav>
			<img src="resources/images/parcel-logo.jpg" class="logo">
			<ul class="nav-list">
				<li><a href="admin-staff-list" id="nav-staff">Staff</a></li>
				<li><a href="admin-parcel-summary" id="nav-parcel">Parcel</a></li>
				<li><a href="index.html" id="nav-logout">Log out</a></li>
			</ul>
		</nav>
	</header>
		
	<div class="page-title">
		<h2>Admin</h2>
	</div>

	<div class="center-nav">
		<div class="nav-option">
			<a href="admin-add-staff" class="option-btn-2">Add Staff</a> 
			<a href="admin-staff-list" class="option-btn-1">Staff List</a>
		</div>
	</div>

	<div class="wrapper">

			<table>
			<tr>
				<th>Staff ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Role</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			
			<c:forEach items="${list}" var="u">
			<tr>
				<td>${u.getAdminID()}</td>
				<td>${u.getFirstName()}</td>
				<td>${u.getLastName()}</td>
				<td>${u.getEmail()}</td>
				<td>${u.getPhoneNumber()}</td>
				<td>${u.getRole()}</td>
				<td>
					<a href="admin-edit-staff?adminID=${u.getAdminID()}" class="edit-btn">Edit</a>
				</td>
				<td>
					<a href="admin-delete-staff?adminID=${u.getAdminID()}" class="delete-btn">Delete</a>
				</td>
			</tr>
			</c:forEach>
			
		</table>
	 	
	</div>

	<footer>@2022 All Rights Reserved</footer>

</body>
</html>