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
		
		<div class="container-fluid">
			<div class="row">
				<div class="col">
					<h4>Add Meow</h4>
					<form action="/dailymeows/add" method="POST" enctype="multipart/form-data" class="form">
						
						<input type="hidden" name="user" value="${user.id}"/>
							
						
						<input class="form-control" type="text" name="headline" placeholder="Headline"/>
						
						
						<input class="form-control" type="text" name="location" placeholder="Location"/>
						
						<div role="alert">${message}</div>
						<input class="form-control-file" type="file" accept="image/*" name="image" placeholder="Image"/>
						
						
						<textarea rows="5" cols="25" class="form-control" name="details" placeholder="Details"></textarea>
						
						<input class="btn btn-outline-dark btn-light" type="submit" value="Add Meow"/>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>