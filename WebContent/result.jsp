<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>
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
	<div class="container">
		<c:if test="${'Y' == resultType}">
			<div id="msg" class="alert alert-success">
				<strong>${message}</strong>
			</div>
		</c:if>

		<c:if test="${'N' == resultType}">
			<div id="msg" class="alert alert-danger">
				<strong>${message}</strong>
			</div>
		</c:if>

		<c:if test="${'I' == resultType}">
			<div id="msg" class="alert alert-warning">
				<strong>${message}</strong>
			</div>
			<a href="./" class="btn btn-info" role="button">Enter Again</a>
		</c:if>
	</div>
</body>
</html>
