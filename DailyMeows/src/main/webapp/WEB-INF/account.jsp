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
				<a href="/dailymeows/home" class="nav-link">Home</a>
				<a href="/logout" class="nav-link">Log Out</a>		
			</div>
		</div>	
		
		<div class="container-fluid">
			<div class="row">
				<div class="col">
					<h4>Account</h4>
					<form:form class="form" action="/dailymeows/account/update/${user.id}" method="POST" modelAttribute="user">				
						<input type="hidden" name="_method" value="put"/>
						<!--<form:errors path="user" class="error"/>-->
						<input type="hidden" name="user" value="${user.id}"/>
						
						<form:errors path="username"/>
						<form:input class="form-control" type="text" path="username" placeholder="Username"/>
						
						<form:errors path="email"/>
						<form:input class="form-control" type="email" path="email" placeholder="Email"/>
						
						<form:errors path="password"/>
						<form:input class="form-control" type="password" path="password" placeholder="Password"/>
						
						<form:errors path="confirmPassword"/>
						<form:input class="form-control" type="password" path="confirmPassword" placeholder="Confirm Password"/>
						
						<form:checkbox class="form-check-input" path="meowsletter" value="meowsletter"/>
						<form:label class="form-check-label" path="meowsletter">Meowsletter</form:label>
						
						<input class="btn btn-outline-dark" type="submit" value="Log In"/>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>