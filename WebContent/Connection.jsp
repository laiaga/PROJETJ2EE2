<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connection</title>
</head>
<body>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_GROUPS">Find All Groups</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_PERSONS">Find All Persons</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_PERSON">Find Person</a>

<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=CONNECTION">Connection</a>

<br><br><br>

<form action="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=CONNECTION" method="GET">
	<input type=hidden id="ACTION" name="ACTION" value="CONNECTION"/>
	<label for="mail">Mail : </label>
	<input type=text id="mail" name="mail" value="${mail}" required/>
	<br><br>
	<label for="password">Password : </label>
	<input type=text id="password" name="password" value="${password}" required/>
	<br><br>
	<label id='message' name='message' for="message" value="${message}"/>
	<br><br>
	<input value="Connect" type="submit" />
</form>

</body>
</html>