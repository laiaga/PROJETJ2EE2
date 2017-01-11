<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Person</title>
</head>
<body>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_GROUPS">Find All Groups</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_PERSONS">Find All Persons</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_PERSON">Find Person</a>

<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=CONNECTION&message=">Connection</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=OFFLINE">Offline</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=Password_Recovery">Password Recovery</a>

<form action="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=UPDATE_PERSON" method="GET">

<h1>Person infos</h1>
 <form action="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=DISPLAY_PERSON" method="GET">
    <th>Person Id : </th>
    <input id='personId' name='personId'  value="${person.personId}"/><br>
	<th>First Name : </th>
	<input id='firstName' name='firstName'  value="${person.firstName}"/><br>
	<th>Last Name : </th>
	<input id='lastName' name='lastName'  value="${person.lastName}"/><br>
	<th>Email : </th>
	<input id='email' name='email'  value="${person.email}"/><br>
	<th>WebSite : </th>
	<input id='webSite' name='webSite'  value="${person.webSite}"/><br>
	<th>BirthDate : </th>
	<input id='birthDate' name='birthDate' value="${person.birthDate}"/><br>
	<th>Password : </th>
	<input id='password'  type="password" name='password' value=""/><br>
	<th>Group Id : </th>
	<input id='groupId' name='groupId' value="${person.groupId}"/><br>
	<input type=hidden id="ACTION" name="ACTION" value="UPDATE_PERSON"/>
	<input value="Update" type="submit" />
 </form>
</body>
</html>