<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Owner Parcel History</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
.nav-option{
	display: grid;
	grid-template-columns: auto auto auto;
	gap: 0px;
}
.center-nav{
	max-width: 600px;
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
<%@page import="com.app.model.Parcel, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	String email = (String) request.getAttribute("email");
	List<Parcel> list = (List<Parcel>) request.getAttribute("list");

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
			<a href="owner-track-parcel?email=${email}" class="option-btn-2">Track Parcel</a> 
			<a href="owner-parcel-schedule?email=${email}" class="option-btn-2">Schedule</a>
			<a href="owner-parcel-history?email=${email}" class="option-btn-1">History</a>
		</div>
	</div>

	<div class="wrapper">
		<table>
			<tr>
				<th>Parcel ID</th>
				<th>Parcel Name</th>
				<th>Date Collected</th>
				<th>Status</th>
			</tr>
			<c:forEach items="${list}" var="u">
			<tr>
				<td>${u.getParcelID()}</td>
				<td>${u.getParcelName()}</td>
				<td>${u.getDateReceived()}</td>
				<td>${u.getStatus()}</td>
			</tr>
			</c:forEach>
		</table>

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