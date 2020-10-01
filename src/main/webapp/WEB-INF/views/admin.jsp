<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>	

<title>Admin</title>


<style>
.error {
	color: red;
	font-style: italic;
	font-weight: bold;
	font-size: small;
}
</style>
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
</head>
<body>

	<div class="container">
		<br>
		<h3>Welcome ${pageContext.request.userPrincipal.name}</h3>
		<h5>
			<a href="${pageContext.request.contextPath}/home" class="btn btn btn-primary">Home</a>&nbsp;&nbsp;<a
				href="login?logout" class="btn btn-primary">Logout</a>
		</h5>
		<br>
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist" id="myTab">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#users">Users</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab" 
				href="#flights">Flights</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#airports">Airports</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#airlines">Airlines</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#customers">Customers</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#transactions">Transactions</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div id="users" class="container tab-pane active">
				<br> <br>
				<h3>Users</h3>
				<br>
				<frm:form action="adminSaveUser" method="POST" modelAttribute="user">
					<table>
						<tr>
							<td>User ID:</td>
							<td><frm:input path="userId" /></td>
							<td><frm:errors path="userId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Username:</td>
							<td><frm:input path="username" /></td>
							<td><frm:errors path="username" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><frm:input type="password" path="password" /></td>
							<td><frm:errors path="password" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><frm:input path="email" /></td>
							<td><frm:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Roles:</td>
							<td><c:forEach items="${ roles }" var="role">
									<c:choose>
										<c:when test="${ fn:contains(selectedRoles, role)}">
											<strong>${ role.roleName }</strong>
											<frm:checkbox path="roles" value="${ role.roleId }"
												checked="true" />&nbsp;&nbsp;
								</c:when>
										<c:otherwise>
									${ role.roleName }<frm:checkbox path="roles" value="${ role.roleId }" />&nbsp;&nbsp;
								</c:otherwise>
									</c:choose>
								</c:forEach></td>

							<td><frm:errors path="roles" cssClass="error" /></td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input
								class="btn btn-primary btn-lg mt-3" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>

				<br> <br>

				<div class="mx-auto" style="width: 1000px;">
					<table class="table table-striped table-sm">
						<thead class="thead-dark">
							<tr>
								<th>User Id</th>
								<th>Username</th>
								<th>Password</th>
								<th>Email</th>
								<th>Roles</th>
								<th colspan="2">Action</th>
							</tr>
						</thead>

						<c:forEach items="${ users }" var="user">
							<tr>
								<td>${ user.userId }</td>
								<td>${ user.username }</td>
								<td>***********</td>
								<%-- 								<td>${ user.password }</td> --%>
								<td>${ user.email }</td>
								<td><c:forEach items="${user.roles}" var="role">
   							${role.roleName}&nbsp;
						</c:forEach></td>
								<td><a href="adminDeleteUser?userId=${ user.userId }">delete</a></td>
								<td><a href="adminUpdateUser?userId=${ user.userId }">update</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div><!-- end users panel -->
			
			<div id="flights" class="container tab-pane fade">
				<br> <br>
				<h3>Flights</h3>
				<br>
				<frm:form action="adminSaveFlight" method="POST"
					modelAttribute="flight">
					<table>
						<tr>
							<td>Flight Id:</td>
							<td><frm:input path="flightId" /></td>
							<td><frm:errors path="flightId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Flight Number:</td>
							<td><frm:input path="flightNumber" /></td>
							<td><frm:errors path="flightNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Airline Id:</td>
							<td><frm:input path="airline.airlineId" /></td>
							<td><frm:errors path="airline.airlineId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Departure Airport Id:</td>
							<td><frm:input path="departFrom.airportId" /></td>
							<td><frm:errors path="departFrom.airportId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Arrival Airport Id:</td>
							<td><frm:input path="arriveAt.airportId" /></td>
							<td><frm:errors path="arriveAt.airportId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Departure Date Time:</td>
							<td><frm:input path="departureDateTime" placeHolder="yyyy-MM-dd HH:mm:ss" /></td>
							<td><frm:errors path="departureDateTime" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Arrival Date Time:</td>
							<td><frm:input path="arrivalDateTime" placeHolder="yyyy-MM-dd HH:mm:ss" /></td>
							<td><frm:errors path="arrivalDateTime" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Price:</td>
							<td><frm:input path="price" /></td>
							<td><frm:errors path="price" cssClass="error" /></td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input
								class="btn btn-primary btn-lg" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>
				<br><br>
				<div class="mx-auto" style="width: 1100px;">
					<table class="table table-striped table-sm">
						<thead class="thead-dark">
							<tr>
								<th>Id</th>
								<th>Flight#</th>
								<th>Airline Id</th>
								<th>Airline</th>
								<th>Depart Id</th>
								<th>IATA</th>
								<th>Arrive Id</th>
								<th>IATA</th>
								<th>Date</th>
								<th>Depart</th>
								<th>Arrive</th>
								<th>Price</th>
								<th colspan="2">Action</th>
							</tr>
						</thead>

						<c:forEach items="${ flights }" var="flight">
							<tr>
								<td>${ flight.flightId }</td>
								<td>${ flight.flightNumber }</td>
								<td>${ flight.airline.airlineId }</td>
								<td>${ flight.airline.airlineName }</td>
								<td>${ flight.departFrom.airportId }</td>
								<td>${ flight.departFrom.airportCode }</td>
								<td>${ flight.arriveAt.airportId }</td>
								<td>${ flight.arriveAt.airportCode }</td>
								<td>${ flight.prettyFlight.date }</td>
								<td>${ flight.prettyFlight.depart }</td>
								<td>${ flight.prettyFlight.arrive }</td>
								<td>${ flight.price }</td>
								<td><a
									href="adminDeleteFlight?flightId=${ flight.flightId }">delete</a></td>
								<td><a
									href="adminUpdateFlight?flightId=${ flight.flightId }">update</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div><!-- end flights panel -->
			
			<div id="airports" class="container tab-pane fade">
				<br> <br>
				<h3>Airports</h3>
				<br>
				<frm:form action="adminSaveAirport" method="POST"
					modelAttribute="airport">
					<table>
						<tr>
							<td>Airport Id:</td>
							<td><frm:input path="airportId" /></td>
							<td><frm:errors path="airportId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Airport Name:</td>
							<td><frm:input path="airportName" /></td>
							<td><frm:errors path="airportName" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Airport Code:</td>
							<td><frm:input path="airportCode" /></td>
							<td><frm:errors path="airportCode" cssClass="error" /></td>
						</tr>
						<tr>
							<td>City:</td>
							<td><frm:input path="airportCity" /></td>
							<td><frm:errors path="airportCity" cssClass="error" /></td>
						</tr>
						<tr>
							<td>State:</td>
							<td><frm:input path="airportState" /></td>
							<td><frm:errors path="airportState" cssClass="error" /></td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input
								class="btn btn-primary btn-lg" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>
				<br><br>
				<div class="mx-auto" style="width: 1100px;">
					<table class="table table-striped table-sm">
						<thead class="thead-dark">
							<tr>
								<th>Airport Id</th>
								<th>Name</th>
								<th>Code</th>
								<th>City</th>
								<th>State</th>
								<th colspan="2">Action</th>
							</tr>
						</thead>

						<c:forEach items="${ airports }" var="airport">
							<tr>
								<td>${ airport.airportId }</td>
								<td>${ airport.airportName }</td>
								<td>${ airport.airportCode }</td>
								<td>${ airport.airportCity }</td>
								<td>${ airport.airportState }</td>
								<td><a href="adminDeleteAirport?airportId=${ airport.airportId }">delete</a></td>
								<td><a href="adminUpdateAirport?airportId=${ airport.airportId }">update</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div><!-- end airports panel -->
			
			<div id="airlines" class="container tab-pane fade">
				<br> <br>
				<h3>Airlines</h3>
				<br>
				<frm:form action="adminSaveAirline" method="POST" modelAttribute="airline">
					<table>
						<tr>
							<td>Id:</td>
							<td><frm:input path="airlineId" /></td>
							<td><frm:errors path="airlineId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Airline:</td>
							<td><frm:input path="airlineName" /></td>
							<td><frm:errors path="airlineName" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Code:</td>
							<td><frm:input path="airlineCode" /></td>
							<td><frm:errors path="airlineCode" cssClass="error" /></td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input
								class="btn btn-primary btn-lg" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>

				<br> <br>

				<div class="mx-auto" style="width: 1000px;">
					<table class="table table-striped table-sm">
						<thead class="thead-dark">
							<tr>
								<th>Id</th>
								<th>Airline</th>
								<th>Code</th>
								<th colspan="2">Action</th>
							</tr>
						</thead>

						<c:forEach items="${ airlines }" var="airline">
							<tr>
								<td>${ airline.airlineId }</td>
								<td>${ airline.airlineName }</td>
								<td>${ airline.airlineCode }</td>
								<td><a
									href="${pageContext.request.contextPath}/adminDeleteAirline?airlineId=${ airline.airlineId }">delete</a></td>
								<td><a
									href="${pageContext.request.contextPath}/adminUpdateAirline?airlineId=${ airline.airlineId }">update</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div><!-- end airlines panel -->

			<div id="customers" class="container tab-pane fade">
				<br> <br>
				<h3>Customers</h3>
				<br>
				<frm:form action="adminSaveCustomer" method="POST"
					modelAttribute="customer">
					<table>
						<tr>
							<td>Customer Id:</td>
							<td><frm:input path="customerId" /></td>
							<td><frm:errors path="customerId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Name:</td>
							<td><frm:input path="name" /></td>
							<td><frm:errors path="name" cssClass="error" /></td>
						</tr>
						<tr>
							<td>User Id:</td>
							<td><frm:input path="user.userId" /></td>
							<td><frm:errors path="user.userId" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Address Line 1:</td>
							<td><frm:input path="customerAddress.addressLine1" /></td>
							<td><frm:errors path="customerAddress.addressLine1"
									cssClass="error" /></td>
						</tr>
						<tr>
							<td>Address Line 2:</td>
							<td><frm:input path="customerAddress.addressLine2" /></td>
							<td><frm:errors path="customerAddress.addressLine2"
									cssClass="error" /></td>
						</tr>
						<tr>
							<td>City:</td>
							<td><frm:input path="customerAddress.city" /></td>
							<td><frm:errors path="customerAddress.city" cssClass="error" /></td>
						</tr>
						<tr>
							<td>State:</td>
							<td><frm:input path="customerAddress.state" /></td>
							<td><frm:errors path="customerAddress.state"
									cssClass="error" /></td>
						</tr>
						<tr>
							<td>Phone:</td>
							<td><frm:input path="phone" /></td>
							<td><frm:errors path="phone" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><frm:input path="email" /></td>
							<td><frm:errors path="email" cssClass="error" /></td>
						</tr>

						<tr>
							<td>SSN:</td>
							<td><frm:input path="ssn" /></td>
							<td><frm:errors path="ssn" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Gender:</td>
							<td><frm:input path="gender" /></td>
							<td><frm:errors path="gender" cssClass="error" /></td>
						</tr>
						<tr>
							<td>DOB:</td>
							<td><frm:input type="date" path="dob" /></td>
							<td><frm:errors path="dob" cssClass="error" /></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><input
								class="btn btn-primary btn-lg" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>
				<br> <br>

				<table class="table table-sm table-striped">
					<thead class="thead-dark">
						<tr>
							<th>Customer Id</th>
							<th>Name</th>
							<th>User Id</th>
							<th>Address Line 1</th>
							<th>Address Line 2</th>
							<th>City</th>
							<th>State</th>
							<th>Mobile</th>
							<th>Email</th>
							<th>SSN</th>
							<th>Gender</th>
							<th>DOB</th>
							<th colspan="2">Action</th>
						</tr>
					</thead>

					<c:forEach items="${ customers }" var="customer">
						<tr>
							<td>${ customer.customerId }</td>
							<td>${ customer.name }</td>
							<td>${ customer.user.userId }</td>
							<td>${ customer.customerAddress.addressLine1 }</td>
							<td>${ customer.customerAddress.addressLine2 }</td>
							<td>${ customer.customerAddress.city }</td>
							<td>${ customer.customerAddress.state }</td>
							<td>${ customer.phone }</td>
							<td>${ customer.email }</td>
							<td>${ customer.ssn }</td>
							<td>${ customer.gender }</td>
							<td>${ customer.dob }</td>
							<td><a
								href="${pageContext.request.contextPath}/adminDeleteCustomer?customerId=${ customer.customerId }">delete</a></td>
							<td><a
								href="${pageContext.request.contextPath}/adminUpdateCustomer?customerId=${ customer.customerId }">update</a></td>
						</tr>
					</c:forEach>
				</table>
			</div><!-- end customers panel -->
			
			<div id="transactions" class="container tab-pane fade">
				<br> <br>
				<h3>Transactions</h3>
				<br>
				<table class="table table-sm table-striped">
					<thead class="thead-dark">
						<tr>
							<th>Id</th>
							<th>Customer Id</th>
							<th>Name</th>
							<th>Flight Id</th>
							<th>No. Of Passengers</th>
							<th>Total Price</th>
						</tr>
					</thead>

					<c:forEach items="${ listOfTickets }" var="ticket">
						<tr>
							<td>${ ticket.ticketId }</td>
							<td>${ ticket.customer.customerId }</td>
							<td>${ ticket.customer.name }</td>
							<td>${ ticket.flight.flightId }</td>
							<td>${ ticket.passengers.size() }</td>
							<td>${ ticket.total }</td>
						
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div><!-- end tab content -->
	</div><!-- end container -->



</body>
</html>