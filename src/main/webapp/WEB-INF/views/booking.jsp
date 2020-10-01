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

<title>Booking</title>

<style>
.error {
	color: red;
	font-style: italic;
	font-weight: bold;
	font-size: small;
}
</style>

<script>

</script>

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
			<h1 class="display-4">${ passengerSaved }</h1>
		</div>
	</div>

	<div class="container my-5">
		<div class="accordion" id="myaccordion">
		
			<!-- passenger details -->
			<c:forEach var="i" begin="1" end="${ tickets }">
				<div class="card">
					<div class="card-header" id="heading-${ i }">
						<h2 class="mb-0">
							<button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapse-${ i }" aria-expanded="false" aria-controls="collapse-${ i }">
								Passenger Details
							</button>
						</h2>
					</div>
					
					<div id="collapse-${ i }" class="collapse" aria-labelledby="heading-${ i }" data-parent="#myaccordion">
						<div class="card-body">
							
							<frm:form action="savePassenger" method="POST" modelAttribute="passenger">
								<div class="form-row">
    								<div class="form-group col-4">
								    <label class="my-1" for="name">Name</label>
								    	<frm:input class="form-control form-control-lg" path="name" id="name" /> <frm:errors path="name" cssClass="error" />
								    </div>
								    <div class="form-group col-4">
								    	<label class="my-1" for="gender">Gender</label>
								    	<frm:input class="form-control form-control-lg" path="gender" id="gender" /> <frm:errors path="gender" cssClass="error" />
								    </div>
								    <div class="form-group col-4">
										<label class="my-1" for="dob">Date of Birth</label>
								      	<frm:input type="date" class="form-control form-control-lg" path="dob" id="dob" /> <frm:errors path="dob" cssClass="error" />
								    </div>
								</div>
						
								<div class="form-row">
									<div class="form-group col-4">
									<label class="my-1" for="mobile">Mobile</label>
								      	<frm:input class="form-control form-control-lg" path="mobile" id="mobile" /> <frm:errors path="mobile" cssClass="error" />
								    </div>
								    <div class="form-group col-4">
								    	<label class="my-1" for="email">Email</label>
								      	<frm:input type="email" class="form-control form-control-lg mb-3" path="email" id="email" /> <frm:errors path="email" cssClass="error" />
								      	<frm:hidden path="ticketNumber" value="${ tickets }" />
								    </div>
								</div>
								
								<div class="form-row">
									<div class="form-group col-4">
										<div class="text-center">
											<button type="submit" class="btn btn-primary btn-lg">Save</button>
										</div>
									</div>
								</div>
							</frm:form>
							
						</div>
					</div>
				</div>
			</c:forEach>
			<!-- end passenger details -->
			
			<!-- payment -->
			<div class="card">
				<div class="card-header" id="heading-${ tickets+1 }">
					<h2 class="mb-0">
						<button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapse-${ tickets+1 }" aria-expanded="false" aria-controls="collapse-${ tickets+1 }">
							Review and book
						</button>
					</h2>
				</div>
					
				<div id="collapse-${ tickets+1 }" class="collapse" aria-labelledby="heading-${ tickets+1 }" data-parent="#myaccordion">
					<div class="card-body">
						
						<frm:form action="purchase" method="POST" modelAttribute="payment">
							<div class="form-row">
								<div class="form-group col-4">
							    <label class="my-1" for="name">Credit Card Holder Name</label>
							    	<frm:input class="form-control form-control-lg" path="name" id="name" /> <frm:errors path="name" cssClass="error" />
							    </div>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-12">
								<label class="mt-3 mb-1" for="billingaddress">Billing Address</label>
							      	<frm:input class="form-control form-control-lg" path="billingAddress.addressLine1" id="billingaddress" placeholder="123 Main Street"/>
							      	<frm:errors path="billingAddress.addressLine1" cssClass="error" />
							    </div>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-12">
							      	<frm:input class="form-control form-control-lg" path="billingAddress.addressLine2" placeholder="Apartment, studio, or floor"/>
							      	<frm:errors path="billingAddress.addressLine2" cssClass="error" />
							    </div>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-4">
							      	<frm:input class="form-control form-control-lg" path="billingAddress.city" id="city" placeholder="City" />
							      	<frm:errors path="billingAddress.city" cssClass="error" />
							    </div>
							    <div class="form-group col-md-4">
							      	<frm:input class="form-control form-control-lg" path="billingAddress.state" id="state" placeholder="State" />
							      	<frm:errors path="billingAddress.state" cssClass="error" />
							    </div>
							    <div class="form-group col-md-4">
							      	<frm:input class="form-control form-control-lg" path="billingAddress.zip" id="zip" placeholder="Zip" />
							      	<frm:errors path="billingAddress.zip" cssClass="error" />
							    </div>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-4">
									<label class="mt-5 mb-1" for="credit">Credit Card</label>
							      	<frm:input class="form-control form-control-lg" path="creditCardNumber" id="credit" />
							      	<frm:errors path="creditCardNumber" cssClass="error" />
							    </div>
							    <div class="form-group col-md-4">
							    	<label class="mt-5 mb-1" for="expiration">Expiration</label>
							      	<frm:input type="date" class="form-control form-control-lg" path="expiration" id="expiration" />
							      	<frm:errors path="expiration" cssClass="error" />
							    </div>
							    <div class="form-group col-md-4">
							    	<label class="mt-5 mb-1" for="ccv">CCV</label>
							      	<frm:input class="form-control form-control-lg" path="ccv" id="ccv" />
							      	<frm:errors path="ccv" cssClass="error" />
							      	<frm:hidden path="flightId" value="${ flightId }" />
							      	<frm:hidden path="total" value="${ total }" />
							    </div>
							</div>
							
							<div class="form-row">
								<div class="form-group col-md-4">
							    	<div class="text-center">
										<button type="submit" class="btn btn-primary btn-lg my-4">Submit Payment</button>
									</div>
							    </div>
							</div>
							
						</frm:form>
						
					</div>
				</div>
			</div>
			<!-- end payment -->
			
  		</div>
	</div>

	
	<div class="container">
		<div class="jumbotron" style="background-color:white;">

		</div>
	</div>
	

</body>
</html>