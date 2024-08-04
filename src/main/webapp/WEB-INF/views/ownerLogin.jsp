<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Parcels - Owner Login</title>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<style>
.nav-option{
	display: grid;
	grid-template-columns: auto auto;
	gap: 0px;
}
#nav-customer{
	color: var(--clr-dark);
}
</style>
</head>

<body>
	<header>
		<nav>
			<img src="resources/images/parcel-logo.jpg" class="logo">
			<ul class="nav-list">
				<li><a href="owner-login" id="nav-customer">Owner</a></li>
				<li><a href="staff-login" id="nav-staff">Staff</a></li>
				<li><a href="admin-login" id="nav-admin">Admin</a></li>
			</ul>
		</nav>
	</header>

	<div class="page-title">
		<h2>Owner</h2>
	</div>

	<div class="center-nav">
		<div class="nav-option">
			<a href="owner-signup" class="option-btn-2">Sign up</a> 
			<a href="owner-login" class="option-btn-1">Login</a>
		</div>
	</div>

	<div class="wrapper">
		<div class="error-message"></div>
		<div class="successful-message"></div>
		
		<form action="<%= request.getContextPath() %>/owner-login" method="post">
			<div class="form-row">
				<div class="label-column">
					<label for="email">Email</label>
				</div>
				<div class="input-column">
					<input type="email" id="email" name="email" placeholder="studentID@xmu.edu.my">
				</div>
			</div>

			<div class="form-row">
				<div class="label-column">
					<label for="password">Password</label>
				</div>
				<div class="input-column">
					<input type="password" id="password" name="password">
				</div>
			</div>

			<input type="submit" class="submit-btn">
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