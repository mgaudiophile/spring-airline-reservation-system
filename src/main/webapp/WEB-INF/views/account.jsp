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

<title>Account</title>
</head>
<body>

	<div class="container">
		<div class="jumbotron" style="background-color: white;">
			<h1 class="display-4">My Profile</h1>
		</div>
	</div>
	
	
	
	<div class="container">
		<hr>
		<p>My Contact</p>
		<form>
			<div class="form-row">
				<input class="form-control form-control-lg" name="username" placeholder="username" /> 
			</div>
		</form>
	</div>
	
	<div class="container">
		<hr>
		<p>Password</p>
	</div>

	<div class="container">
		<div class="jumbotron" style="background-color: white;">
		
		</div>
	</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>