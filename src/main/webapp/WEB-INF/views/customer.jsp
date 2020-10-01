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

<title>Flights</title>

<style>
.error {
	color: red;
	font-style: italic;
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

	<div class="container my-5">
		<img src="${pageContext.request.contextPath}/img/travel.jpeg"
			class="rounded mx-auto d-block shadow-lg bg-white" width="50%"
			alt="travel picture" />
	</div>

	<div class="container bg-light shadow bg-light rounded">
		<frm:form action="searchFlights" method="POST" modelAttribute="search">
			<div class="row mb-4">
				<div class="col col-4">
					<frm:select class="form-control form-control-lg mt-4" path="fromInput" placeholder="From">
						<frm:option value="From" label="From" />
						<c:forEach items="${ airportCodes }" var="code">
							<frm:option value="${ code.key }" label="${ code.value }" />
						</c:forEach>
					</frm:select>
				</div>

				<div class="col col-4">
					<frm:select class="form-control form-control-lg mt-4" path="toInput" placeholder="To">
						<frm:option value="To" label="To" />
						<c:forEach items="${ airportCodes }" var="code">
							<frm:option value="${ code.key }" label="${ code.value }" />
						</c:forEach>
					</frm:select>
					<frm:errors path="toInput" cssClass="error" />
				</div>

				<div class="col col-4">
					<frm:input class="form-control form-control-lg mt-4" type="date"
						path="depart" />
				</div>
			</div>

			<div class="row">
				<div class="col col-sm-2 text-right">
					<label>Tickets: </label>
				</div>
				<div class="col col-sm-2">
					<frm:input class="form-control form-control-lg mb-3" type="number"
						min="1" max="100" step="1" path="tickets" /> <frm:errors path="tickets" cssClass="error" />
				</div>

				<div class="col col-4">
					<button type="submit" class="btn btn-primary mb-3 shadow rounded"
						id="searchButton">Search</button>
				</div>
			</div>
		</frm:form>
	</div>

	<div class="container mt-5">

		<c:forEach items="${ searchResults }" var="res">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col">Flight #: ${ res.flightNumber }</div>
						<div class="col">Departure: ${ res.prettyFlight.date }</div>
						<div class="col">${ res.airline.airlineName }</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col col-8">
							<div class="pb-1">${ res.prettyFlight.depart } &emsp;&middot;&emsp; ${ res.departFrom.airportName } (${ res.departFrom.airportCode }) &emsp;&middot;&emsp; ${ res.departFrom.airportCity }, ${ res.departFrom.airportState }</div>
							<div class="py-2 pl-3" style="font-size:small; color:DodgerBlue;">Travel time: ${ res.prettyFlight.eta } hrs.</div>
							<div>${ res.prettyFlight.arrive } &emsp;&middot;&emsp; ${ res.arriveAt.airportName } (${ res.arriveAt.airportCode }) &emsp;&middot;&emsp; ${ res.arriveAt.airportCity }, ${ res.arriveAt.airportState }</div>
						</div>
						<div class="col">
							<div class="text-right">
							<div class="pb-4 pr-3">$${ res.price*tickets }</div>
							<a href="${pageContext.request.contextPath}/booking?flightId=${ res.flightId }&tickets=${ tickets }&total=${ res.price*tickets }"
								class="btn btn-info selectButton" style="border-radius:25px;">Book</a>
						</div>
						</div>
					</div>

				</div>
			</div>

		</c:forEach>

	</div>



	<div class="container">
		<div class="jumbotron" style="background-color:white;">
			
		</div>
	</div>
	


</body>
</html>