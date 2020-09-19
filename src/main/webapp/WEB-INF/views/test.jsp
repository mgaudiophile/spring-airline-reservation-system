<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">

<title>Test</title>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="#">Features</a>
      <a class="nav-item nav-link" href="#">Pricing</a>
      <a class="nav-item nav-link disabled" href="#">Disabled</a>
    </div>
  </div>
</nav>

<div class="container my-5">
	<img src="img/travel.jpeg" class="rounded mx-auto d-block shadow-lg bg-white" width="50%" alt="travel picture" />
</div>
	
<div class="container bg-light shadow bg-light rounded">
	<form>
		<div class="row mb-4">
			<div class="col">
				<input class="form-control form-control-lg mt-4" type="text" placeholder="From">
			</div>
			
			<div class="col">
				<input class="form-control form-control-lg mt-4" type="text" placeholder="To">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="depart">Depart:</label>
  				<input class="form-control form-control-lg" type="date" id="depart" name="depart">
			</div>
			
			<div class="col">
				<label for="arrive">Arrive:</label>
  				<input class="form-control form-control-lg" type="date" id="arrive" name="depart">
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="text-right">
					<button type="button" class="btn btn-primary btn-lg mt-5 mb-3 shadow rounded">Search</button>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="container mt-5">
flights
</div>

</body>
</html>