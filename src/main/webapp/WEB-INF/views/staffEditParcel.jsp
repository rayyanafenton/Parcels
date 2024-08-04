<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Staff Edit Parcel</title>
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
<%@page import="com.app.model.Parcel, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	String email = (String) request.getAttribute("email");
	Parcel parcel = (Parcel) request.getAttribute("parcel");
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
			<a class="option-btn-1">Edit Parcel</a>
		</div>
	</div>
		
	<div class="wrapper">
		<div class="error-message"></div>
		<div class="successful-message"></div>
		

		<form action="staff-edit-parcel?email=${email}" method="post">			
			<input type="hidden" id="parcelID" name="parcelID" value="<%= parcel.getParcelID()%>" >
			
			<div class="form-row">
				<div class="label-column">
					<label for="parcelName">Parcel Name</label>
				</div>
				<div class="input-column">
					<input type="text" id="parcelName" name="parcelName" value="<%= parcel.getParcelName() %>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="cID">Owner ID</label>
				</div>
				<div class="input-column">
					<input type="text" id="ownerID" name="ownerID" value="<%=parcel.getOwnerID()%>">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="dateReceived">Date Received</label>
				</div>
				<div class="input-column">
					<input type="date" id="dateReceived" name="dateReceived" value="<%=parcel.getDateReceived()%>">
				</div>
			</div>

			<div class="form-row">
			    <div class="label-column">
			        <label for="status">Status</label>
			    </div>
			    <div class="input-column">
			        <select name="status">
					    <option value="Collected" <%= "Collected".equals(parcel.getStatus()) ? "selected" : "" %>>Collected</option>
					    <option value="Not Collected" <%= "Not Collected".equals(parcel.getStatus()) ? "selected" : "" %>>Not Collected</option>
					    <option value="Scheduled" <%= "Scheduled".equals(parcel.getStatus()) ? "selected" : "" %>>Scheduled</option>
					</select>
			    </div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="dateCollected">Date Collected</label>
				</div>
				<div class="input-column">
					<input type="date" id="dateCollected" name="dateCollected" value="<%=parcel.getDateCollected()%>">
				</div>
			</div>
			
			<input type="submit" class="submit-btn"> 
			
		</form>
	</div>

	<footer>@2022 All Rights Reserved</footer>

</body>
</html>