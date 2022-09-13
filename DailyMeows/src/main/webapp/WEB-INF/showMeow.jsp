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
				<a href="/dailymeows/account" class="nav-link">Account</a>
				<a href="/dailymeows/home" class="nav-link">Home</a>
				<a href="/logout" class="nav-link">Log Out</a>		
			</div>
		</div>	
			
		<div class="container">
			<div class="row">
				<div class="col d-flex justify-content-center">
					<div class="card">
						<div class="card-header">
							<h4 class="header"><c:out value="${meow.headline}"/></h4>
						</div>
						<div class="card-body">
							<div class="card-title">
								<h5><c:out value="${meow.location}"/></h5>
							</div>
							<div class="card-text">
								<p><c:out value="${meow.details}"/></p>
							</div>
						</div>
						
						<img src="${meow.image}" alt="${meow.details}" class="card-img-bottom"/>

						<div class="card-footer">
							<c:if test="${userId == meow.user.id}">
								<a href="/dailymeows/update/${meow.id}" class="footer">Update Meow</a>
								<form:form action="/dailymeows/delete/${meow.id}" method="POST">
									<input type="hidden" name="_method" value="delete" />
									<input class="btn btn-link" type="submit" value="Delete" />
								</form:form>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>