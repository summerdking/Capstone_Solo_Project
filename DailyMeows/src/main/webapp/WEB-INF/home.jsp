<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Daily Meows</title>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="/css/style.css">
	</head>
	<body>
		<div class="navbar sticky-top">
			<h1>Daily Meows</h1>
			<div class="nav">
				<a href="/dailymeows/account/${user.id}" class="nav-link">Account</a>
				<a href="/dailymeows/add" class="nav-link">Add Meow</a>
				<a href="/logout" class="nav-link">Log Out</a>
			</div>
		</div>
		
		<div class="container">	
			<div class="row">
				<h2 class="text-center">Meows Feed</h2>
				<div class="col d-inline-flex flex-wrap">
					<c:forEach var="meow" items="${meows}">
						<div class="card m-2">
							<div class="card-header">
								<h4><a href="/dailymeows/${meow.id}" class="header"><c:out value="${meow.headline}"/></a></h4>
							</div>
							<img src="${meow.image}" alt="${meow.details}" class="card-img-bottom"/>

							<div class="card-footer">
								<c:if test="${userId == meow.user.id}">
									<a href="/dailymeows/update/${meow.id}" class="footer">Update Meow</a>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</body>
</html>