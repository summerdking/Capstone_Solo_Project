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
				<div class="col">
					<h4>Update Meow</h4>
					<form action="/dailymeows/update/${meow.id}" method="POST" enctype="multipart/form-data" class="form">
						<input type="hidden" name="_method" value="put"/>
						<!--<form:errors path="user" class="error"/>-->
						<input type="hidden" name="user" value="${user.id}"/>
							
						<!--<form:errors path="headline"/>-->
						<input class="form-control" type="text" name="headline" value="${meow.headline}"/>
						
						<!--<form:errors path="location"/>-->
						<input class="form-control" type="text" name="location" value="${meow.location}"/>
						
						<!--<form:errors path="image"/>-->
						<input class="form-control-file" type="hidden" name="image" value="${meow.image}"/>
						
						<!--<form:errors path="details"/>-->
						<textarea rows="5" cols="25" class="form-control" name="details">${meow.details}</textarea>
						
						<input class="btn btn-outline-dark btn-light" type="submit" value="Update Meow"/>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>