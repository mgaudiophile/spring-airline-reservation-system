<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>	

<title>Profile</title>

<style>
.error {
	color: red;
	font-style: italic;
	font-weight: bold;
	font-size: small;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">

		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link active"
					href="${pageContext.request.contextPath}/customer">Home <span
					class="sr-only">(current)</span></a> <a class="nav-item nav-link"
					href="${pageContext.request.contextPath}/itinerary">Itinerary</a> <a
					class="nav-item nav-link"
					href="${pageContext.request.contextPath}/profile">Profile</a> <a
					class="nav-item nav-link"
					href="${pageContext.request.contextPath}/login?logout">Logout</a>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="jumbotron" style="background-color: white;">
			<h1 class="display-4">My Profile</h1><br>
			<p class="lead">Address:</p>
			<p class="lead">
				${ customer.customerAddress.addressLine1 }, ${ customer.customerAddress.addressLine2 } <br>
				${ customer.customerAddress.city }, ${ customer.customerAddress.state } ${ customer.customerAddress.zip } <br>
			</p>
			<p class="lead">Contact:</p>
			<p class="lead">
				${ customer.phone } <br>
				${ customer.email }
			</p>
		</div>
	</div>
	
	
	
	<div class="container">
		<hr>
		<br><br>
		<frm:form action="updateProfile" method="POST" modelAttribute="profile">
			<div class="form-row">
				<div class="form-group col-md-6">
					<frm:input class="form-control form-control-lg" path="addressLine1" placeholder="123 main street" /> <frm:errors path="addressLine1" cssClass="error" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<frm:input class="form-control form-control-lg" path="addressLine2" placeholder="Apartment, studio, or floor" /> <frm:errors path="addressLine2" cssClass="error" />
				</div>
			</div>
			
			<div class="form-row">
				<div class="form-group col-md-6">
					<frm:input class="form-control form-control-lg" path="city" placeholder="city" /> <frm:errors path="city" cssClass="error" />
				</div>
				<div class="form-group col-md-4">
					<frm:input class="form-control form-control-lg" path="state" placeholder="state" /> <frm:errors path="state" cssClass="error" />
				</div>
				<div class="form-group col-md-2">
					<frm:input class="form-control form-control-lg" path="zip" placeholder="zip" /> <frm:errors path="zip" cssClass="error" />
				</div>
			</div>
			
			<div class="form-row mt-5">
				<div class="form-group col-md-6">
					<label for="mobile">Mobile</label>
					<frm:input class="form-control" path="mobile" id="mobile" /> <frm:errors path="mobile" cssClass="error" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="email">Email</label>
					<frm:input class="form-control" path="email" id="email" /> <frm:errors path="email" cssClass="error" />
				</div>
			</div>
			
			<div class="form-row mb-5 mt-4">
				<div class="form-group col-md-6">
					<div class="text-center">
						<button type="submit" class="btn btn-lg btn-primary">Update</button>
					</div>
				</div>
			</div>
		</frm:form>

	</div>
	
	
	<div class="container">
		<div class="jumbotron" style="background-color: white;">
		
		</div>
	</div>
	<div class="container">
		<div class="jumbotron" style="background-color: white;">
		
		</div>
	</div>



</body>
</html>