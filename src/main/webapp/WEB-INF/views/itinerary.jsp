<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<%@ page isELIgnored="false"%>
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

<title>Itinerary</title>

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
		<div class="jumbotron" style="background-color:white;">
			<h1 class="display-4">Upcoming trips</h1>
		</div>
	</div>
	
	<div class="container">
		
		<c:forEach items="${ listOfTickets }" var="ticket">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col">Flight #: ${ ticket.flight.flightNumber }</div>
						<div class="col">Departure: ${ ticket.flight.prettyFlight.date }</div>
						<div class="col">${ ticket.flight.airline.airlineName }</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col col-8">
							<div class="pb-1">${ ticket.flight.prettyFlight.depart } &emsp;&middot;&emsp; ${ ticket.flight.departFrom.airportName } (${ ticket.flight.departFrom.airportCode }) &emsp;&middot;&emsp; ${ ticket.flight.departFrom.airportCity }, ${ ticket.flight.departFrom.airportState }</div>
							<div class="py-2 pl-3" style="font-size:small; color:DodgerBlue;">Travel time: ${ ticket.flight.prettyFlight.eta } hrs.</div>
							<div>${ ticket.flight.prettyFlight.arrive } &emsp;&middot;&emsp; ${ ticket.flight.arriveAt.airportName } (${ ticket.flight.arriveAt.airportCode }) &emsp;&middot;&emsp; ${ ticket.flight.arriveAt.airportCity }, ${ ticket.flight.arriveAt.airportState }</div>
						</div>
						
					</div>
					
					<br>
					<hr>
					<br>
					<div class="mx-auto" style="width: 900px;">
					<table class="table table-sm">
						<thead class="thead-light">
							<tr>
								<th>Passenger</th>
								<th>Mobile#</th>
								<th>Email</th>
							</tr>
						</thead>

						<c:forEach items="${ ticket.passengers }" var="p">
							<tr>
								<td>${ p.name }</td>
								<td>${ p.mobile }</td>
								<td>${ p.email }</td>
							</tr>
						</c:forEach>
					</table>
					</div>
				
				</div><!-- end card body -->
			</div><!-- end card -->

			<br>
		</c:forEach>
		
	</div>


	<div class="container">
		<div class="jumbotron" style="background-color:white;">
			
		</div>
	</div>


</body>
</html>