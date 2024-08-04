<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Admin Parcel Summary</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
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
.nav-option {
	display: grid;
	grid-template-columns: auto;
	gap: 0px;
}
.center-nav {
	max-width: 400px;
}
#nav-parcel{
	color: var(--clr-dark);
}
</style>
</head>

<body>
<%@page import="com.app.model.Collection,com.app.model.Parcel, java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
    String email = (String) request.getAttribute("email");
    Collection summary = (Collection) request.getAttribute("summary");
    List<Parcel> list = (List<Parcel>) request.getAttribute("list");
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
			<a  class="option-btn-1">Parcel Summary</a>
		</div>
	</div>

	<div class="wrapper">
		
				<form>
			<div class="form-row">
				<div class="label-column">
					<label for="totalparcel">Total Parcel</label>
				</div>
				<div class="input-column">
					<input type="text" id="addStatus" name="addStatus" value=<%=summary.getStatusAdd()%>>
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="collected">Collected</label>
				</div>
				<div class="input-column">
					<input type="text" id="collected" name="collected" value=<%=summary.getStatus() %>>
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="notcollected">Not Collected</label>
				</div>
				<div class="input-column">
					<input type="text" id="notcollected" name="notcollected" value=<%=summary.getStatusNo() %>>
				</div>
			</div>
		</form>
		<br>
		<h3>PARCEL LIST</h3>

		<table>
			<tr>
				<th>Parcel ID</th>
				<th>Parcel Name</th>
				<th>Customer ID</th>
				<th>Status</th>
				<th>Date Received</th>
				<th>Date Collected</th>
			</tr>
			
			<c:forEach items="${list}" var="u">
			<tr>
				<td>${u.getParcelID()}</td>
				<td>${u.getParcelName()}</td>
				<td>${u.getOwnerID()}</td>
				<td>${u.getStatus()}</td>
				<td>${u.getDateReceived()}</td>
				<td>${u.getDateCollected()}</td>
			</tr>
			</c:forEach>
			
		</table>
	</div>

	<footer>@2022 All Rights Reserved</footer>

</body>
</html>