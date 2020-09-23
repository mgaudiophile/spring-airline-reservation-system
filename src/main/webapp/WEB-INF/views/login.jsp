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

<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>Login</title>

<script>
	$(document).ready(function() {
		$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
			localStorage.setItem('activeTab', $(e.target).attr('href'));
		});
		var activeTab = localStorage.getItem('activeTab');
		if (activeTab) {
			$('#myTab a[href="' + activeTab + '"]').tab('show');
		}
	});
	
</script>

<style>
.jumbotron {
	background-color: white;
}
.error {
	color: red;
	font-style: italic;
	font-weight: bold;
	font-size: small;
}
</style>

</head>
<body>

<div class="container">
	<div class="jumbotron">
		<h1 class="display-4">${ registerMessage }</h1>
	</div>
</div>

	<div class="container">
		<ul class="nav nav-tabs" role="tablist" id="myTab">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#login">Login</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#register">Register</a></li>
		</ul>

		<!-- tab panes -->
		<div class="tab-content">
			<div id="login" class="container tab-pane active">
				<br> <br>
				<form action="login" method="POST">
					<div class="form-row">
						<div class="col-4 mb-4">
							<input class="form-control form-control-lg" name="username" placeholder="username" /> 
						</div>
					</div>
					<div class="form-row">
						<div class="col-4 mb-4">
							<input class="form-control form-control-lg mb-3" name="password" type="password" placeholder="password" />
							<p class="error"> ${ message } </p>
						</div>
					</div>
					<div class="form-row">
						<div class="col-4">
							<div class="text-right">
								<button type="submit" class="btn btn-primary">Login</button>
							</div>
						</div>
					</div>
				</form>
			</div>
			<!-- end login panel -->

			<div id="register" class="container tab-pane fade">
				<br> <br>
				<frm:form action="register" method="POST" modelAttribute="register">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="name">Name</label>
							<frm:input class="form-control" path="name" id="name" placeholder="Name" /> <frm:errors path="name" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label for="address1">Address 1</label>
						<frm:input class="form-control" path="addressLine1" id="address1" placeholder="1234 Main St" /> <frm:errors path="addressLine1" cssClass="error" />
					</div>
					<div class="form-group">
						<label for="address2">Address 2</label>
						<frm:input class="form-control" path="addressLine2" id="address2" placeholder="Apartment, studio, or floor" /> <frm:errors path="addressLine2" cssClass="error" />
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="city">City</label> 
							<frm:input class="form-control" path="city" id="city" /> <frm:errors path="city" cssClass="error" />
						</div>
						<div class="form-group col-md-4">
							<label for="state">State</label> 
							<frm:input class="form-control" path="state" id="state" /> <frm:errors path="state" cssClass="error" />
						</div>
						<div class="form-group col-md-2">
							<label for="zip">Zip</label> 
							<frm:input class="form-control" path="zip" id="zip" /> <frm:errors path="zip" cssClass="error" />
						</div>
					</div>
					
					<div class="form-row mt-4">
						<div class="form-group col-md-6">
							<label for="dob">Dob</label>
							<frm:input type="date" class="form-control" path="dob" id="dob" /> <frm:errors path="dob" cssClass="error" />
						</div>
						<div class="form-group col-md-4">
							<label for="ssn">SSN</label>
							<frm:input class="form-control" path="ssn" id="ssn" /> <frm:errors path="ssn" cssClass="error" />
						</div>
						<div class="form-group col-md-2">
							<label for="gender">Gender</label>
							<frm:input class="form-control" path="gender" id="gender" /> <frm:errors path="gender" cssClass="error" />
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
					
					<div class="form-row mt-5">
						<div class="form-group col-md-6">
							<label for="username">Username</label>
							<frm:input class="form-control" path="username" id="username" /> <frm:errors path="username" cssClass="error" />
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="password">Password</label>
							<frm:input type="password" class="form-control" path="password" id="password" /> <frm:errors path="password" cssClass="error" />
						</div>
					</div>
					
					<div class="form-row mb-5 mt-4">
						<div class="form-group col-md-6">
							<div class="text-center">
								<button type="submit" class="btn btn-lg btn-primary">Register</button>
							</div>
						</div>
					</div>
				</frm:form>
			</div><!-- end register panel -->

		</div><!-- end tab panes -->
	</div>


<div class="container">
	<div class="jumbotron">
		
	</div>
</div>

</body>
</html>