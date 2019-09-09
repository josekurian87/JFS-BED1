<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CreditCard-Home Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/custom.css">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"><b>Check for Credit Card Eligibility</b></a>
			</div>
		</div>
	</nav>

	<p>&nbsp;</p>
	<form:form class="form-horizontal" action="checkEligibility" method="post" commandName="panData">
		<form:errors path = "*" cssClass = "errorblock" element = "div" />
		<div class="form-group">
			<label class="control-label col-sm-2" for="panNo">Enter PAN
				Number:</label>
			<div class="col-sm-3">
				<input type="text" name="panNo" id="panNo" class="form-control"
					required="required" pattern="^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}$"
					title="10 character alpha numeric"></input>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
				<input type="submit" id="submit" value="Check"
					class="btn btn-primary">
			</div>
		</div>
	</form:form>
</body>
</html>
