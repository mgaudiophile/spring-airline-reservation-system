<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
				<tr>
					<td>Roles:</td>
					<td><c:forEach items="${roles}" var="role">
							<c:choose>
								<c:when test="${fn:contains(selectedRoles, role)}">
									<strong>${role.roleName}</strong>
									<frm:checkbox path="roles" value="${role.roleId}" checked="true" />&nbsp;&nbsp;
								</c:when>
								<c:otherwise>
									${role.roleName}<frm:checkbox path="roles" value="${role.roleId}" />&nbsp;&nbsp;
								</c:otherwise>
							</c:choose>
						</c:forEach></td>

					<td><frm:errors path="roles" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td colspan="3" align="center"><input type="submit" value="submit" /></td>
				</tr>
			</table>
		</frm:form>
		
		<c:if test="${not empty users}">
		<hr/>
			<table border="1">
				<tr>
					<th>User Id</th>
					<th>Name</th>
					<th>Password</th>
					<th>Email</th>
					<th>Roles</th>
					<th colspan="2">Action</th>
				</tr>
		
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.userId}&nbsp;</td>
						<td>${user.username}&nbsp;</td>
						<td>${user.password}</td>
						<td>${user.email}&nbsp;</td>
						<td>
							<c:forEach items="${user.roles}" var="role">
   								${role.roleName}&nbsp;
							</c:forEach>
						</td>
						<td><a href="deleteUser?userId=${user.userId}">Delete</a> &nbsp;&nbsp; <a href="updateUser?userId=${user.userId}">Update</a></td>
					</tr>
				</c:forEach>

			</table>
		</c:if>
		
	</div>

</body>
</html>