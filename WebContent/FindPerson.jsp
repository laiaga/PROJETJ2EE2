<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Person</title>
</head>
<body>

<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_GROUPS">Find All Groups</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_PERSONS">Find All Persons</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_PERSON">Find Person</a>

<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=CONNECTION&message=">Connection</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=OFFLINE">Offline</a>

<br><br><br>

<form action="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=DISPLAY_PERSON" method="GET">
	<input type=hidden id="ACTION" name="ACTION" value="DISPLAY_PERSON"/>
	<label for="mail">Mail : </label>
	<input type=text id="mail" name="mail"/>
	<br><br>
	<label for="mail">ID : </label>
	<input type=text id="id" name="id"/>
	<br><br>
	<input value="Search" type="submit" />
</form>

</body>
</html>