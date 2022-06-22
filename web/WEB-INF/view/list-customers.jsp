<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- add support for JSTL Core Tags -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer List</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<h1>Test List Customer</h1>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<!-- button: Add Customer -->
			<input type="button" value="Add Customer"
				onClick="window.location.href= 'addCustomerForm'; return False"
				class="add-button" />
			<!-- Call spring controller mapping -->

			<!-- add html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>

				<!-- looping through the passed data -->
				<c:forEach var="tempCustomer" items="${customers}">

					<!-- update link with customer id -->
					<c:url var="updateLink" value="/customer/updateCustomerForm">
						<c:param name="customerId" value="${tempCustomer.id}" />
						<!-- PARAMETER customerId when looping -->
					</c:url>

					<!-- delete link with customer id -->
					<c:url var="deleteLink" value="/customer/deleteCustomer">
						<c:param name="customerId" value="${tempCustomer.id}" />
						<!-- PARAMETER customerId when looping -->
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>

						<td>
							<!-- update customer data -->
							<a href="${updateLink}">Update</a> 
							<!-- delete customer data -->
							<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure want to delete this customer?')))">Delete</a>
						</td>
					</tr>

				</c:forEach>

			</table>

		</div>
	</div>


</body>
</html>