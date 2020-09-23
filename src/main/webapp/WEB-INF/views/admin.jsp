<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Admin</title>

<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
.error {
	color: red;
	font-style: italic;
	font-weight: bold
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
					<table class="table table-striped">
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
							<td>Airline:</td>
							<td><frm:input path="airline" /></td>
							<td><frm:errors path="airline" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Depart From:</td>
							<td><frm:input path="departFrom" /></td>
							<td><frm:errors path="departFrom" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Arrive At:</td>
							<td><frm:input path="arriveAt" /></td>
							<td><frm:errors path="arriveAt" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Departure Time:</td>
							<td><frm:input path="departureDateTime" /></td>
							<td><frm:errors path="departureDateTime" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Arrival Time:</td>
							<td><frm:input path="arrivalDateTime" /></td>
							<td><frm:errors path="arrivalDateTime" cssClass="error" /></td>
						</tr>
						<tr>
							<td align="right" colspan="2"><input
								class="btn btn-primary btn-lg" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>
				<br><br>
				<div class="mx-auto" style="width: 1100px;">
					<table class="table table-striped">
						<thead class="thead-dark">
							<tr>
								<th>Flight Id</th>
								<th>Flight Number</th>
								<th>Airline</th>
								<th>Depart</th>
								<th>Arrive</th>
								<th>Depart Time</th>
								<th>Arrive Time</th>
								<th>Price</th>
								<th colspan="2">Action</th>
							</tr>
						</thead>

						<c:forEach items="${ flights }" var="flight">
							<tr>
								<td>${ flight.flightId }</td>
								<td>${ flight.flightNumber }</td>
								<td>${ flight.airline }</td>
								<td>${ flight.departFrom }</td>
								<td>${ flight.arriveAt }</td>
								<td>${ flight.departureDateTime }</td>
								<td>${ flight.arrivalDateTime }</td>
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
<!-- 						<tr> -->
<!-- 							<td>Address Line 1:</td> -->
<%-- 							<td><frm:input path="airportAddress.addressLine1" /></td> --%>
<%-- 							<td><frm:errors path="airportAddress.addressLine1" cssClass="error" /></td> --%>
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>Address Line 2:</td> -->
<%-- 							<td><frm:input path="airportAddress.addressLine2" /></td> --%>
<%-- 							<td><frm:errors path="airportAddress.addressLine2" cssClass="error" /></td> --%>
<!-- 						</tr> -->
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
<!-- 						<tr> -->
<!-- 							<td>Phone:</td> -->
<%-- 							<td><frm:input path="airportPhone" /></td> --%>
<%-- 							<td><frm:errors path="airportPhone" cssClass="error" /></td> --%>
<!-- 						</tr> -->
						<tr>
							<td align="right" colspan="2"><input
								class="btn btn-primary btn-lg" type="submit" value="submit" /></td>
						</tr>
					</table>
				</frm:form>
				<br><br>
				<div class="mx-auto" style="width: 1100px;">
					<table class="table table-striped">
						<thead class="thead-dark">
							<tr>
								<th>Airport Id</th>
								<th>Name</th>
								<th>Code</th>
<!-- 								<th>Address Line 1</th> -->
<!-- 								<th>Address Line 2</th> -->
								<th>City</th>
								<th>State</th>
<!-- 								<th>Phone</th> -->
								<th colspan="2">Action</th>
							</tr>
						</thead>

						<c:forEach items="${ airports }" var="airport">
							<tr>
								<td>${ airport.airportId }</td>
								<td>${ airport.airportName }</td>
								<td>${ airport.airportCode }</td>
<%-- 								<td>${ airport.airportAddress.addressLine1 }</td> --%>
<%-- 								<td>${ airport.airportAddress.addressLine2 }</td> --%>
								<td>${ airportCity }</td>
								<td>${ airportState }</td>
<%-- 								<td>${ airport.airportPhone }</td> --%>
								<td><a href="adminDeleteAirport?airportId=${ airport.airportId }">delete</a></td>
								<td><a href="adminUpdateAirport?airportId=${ airport.airportId }">update</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div><!-- end airports panel -->

			<div id="customers" class="container tab-pane fade">
				<br> <br>
				<h3>Customers</h3>
				<br>
			</div>
			
			<div id="transactions" class="container tab-pane fade">
				<br> <br>
				<h3>Transactions</h3>
				<br>
			</div>
			
		</div><!-- end tab content -->
	</div><!-- end container -->

</body>
</html>