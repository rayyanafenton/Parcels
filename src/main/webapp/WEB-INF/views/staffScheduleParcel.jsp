<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Staff Schedule Parcel</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
.wrapper{
	background: var(--clr-light);
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
<%@page import="com.app.model.Parcel, com.app.dao.ParcelDao, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String email = (String) request.getAttribute("email");
	List<Collection> list = (List<Collection>) request.getAttribute("list");
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
			<a href="staff-add-parcel?email=${email}" class="option-btn-2">Add Parcel</a>  
			<a href="staff-parcel-list?email=${email}" class="option-btn-2">Parcel List</a>  
			<a href="staff-search-parcel?email=${email}" class="option-btn-2">Search Parcel</a>
			<a href="staff-schedule-parcel?email=${email}" class="option-btn-1">Schedule</a>
		</div>
	</div>
 
 	<div class="wrapper">
		<table>
			<tr>
				<th>Parcel ID</th>
				<th>Parcel Name</th>
				<th>Schedule Date</th>
				<th>Schedule Time</th>
				<th>Customer ID</th>
				<th>Status</th>
				<th>Edit</th>
			</tr>
			
			<c:forEach items="${list}" var="u">
				<tr>
					<td>${u.getParcelID()}</td>
					<td>${u.getParcelName()}</td>
					<td>${u.getScheduledDate()}</td>
					<td>${u.getScheduledTime()}</td>
					<td>${u.getOwnerID()}</td>
					<td>${u.getStatus()}</td>
					<td>
	                    <a href="staff-edit-parcel?parcelID=${u.getParcelID()}&email=${email}" class="edit-btn">Edit</a>
	                </td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<footer>@2022 All Rights Reserved</footer>
</body>
</html>