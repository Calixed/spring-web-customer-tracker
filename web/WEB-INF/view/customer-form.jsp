<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- add support for JSTL Core Tags -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save Customer</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
	
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
	
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Customer</h3>

		<!-- modelAttribute="customer" is in the controller in the line code
		theModel.addAttribute("customer",theCustomer);-->
		<!-- action = -> is where spring sends to Spring MVC mapping -->
		<form:form action="saveCustomer" modelAttribute="customer"
			method="POST">
			<!-- associate data with customer id -->
			<form:hidden path="id"/>
			
			<table>
				<tbody>
					<tr>
						<td><label>First Name: </label></td>
						<td><form:input path="firstName" /></td>
					</tr>
					<tr>
						<td><label>Last Name: </label></td>
						<td><form:input path="lastName" /></td>
					</tr>
					<tr>
						<td><label>Email: </label></td>
						<td><form:input path="email" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"/></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		
		<div style="clear; both;"></div>
		<p> 
			<a href="${pageContext.request.contextPath}/customer/list">back to list	</a>
		</p>
	</div>

</body>
</html>