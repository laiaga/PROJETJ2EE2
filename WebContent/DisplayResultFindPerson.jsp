<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Result Find Person</title>
</head>
<body>

	<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_GROUPS">Find
		All Groups</a>
	<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_PERSONS">Find
		All Persons</a>
	<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_PERSON">Find
		Person</a>

	<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=CONNECTION">Connection</a>
	<br>
	<br>
	<br>

	<h1>Person infos</h1>
	<table border="1" cellpadding="5">
		<tr>
			<th>Person Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>WebSite</th>
			<th>BirthDate</th>
			<th>Group Id</th>
		</tr>
		<c:forEach items="${persons}" var="person">
			<tr>
				<td>${person.personId}</td>
				<td>${person.firstName}</td>
				<td>${person.lastName}</td>
				<td>${person.email}</td>
				<td>${person.webSite}</td>
				<td>${person.birthDate}</td>
				<td>${person.groupId}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>