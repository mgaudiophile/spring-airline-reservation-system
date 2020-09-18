<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User</title>
</head>
<body>

	<div align="center">
		<frm:form action="saveUser" method="POST" modelAttribute="user">
			<table>
				<tr>
					<td>User Id:</td>
					<td><frm:input name="userId" path="userId" /></td>
					<td><frm:errors path="userId" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Name:</td>
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
			</table>
		</frm:form>
	</div>

</body>
</html>