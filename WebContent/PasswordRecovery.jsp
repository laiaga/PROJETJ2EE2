<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Recovery</title>
</head>
<body>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_GROUPS">Find All Groups</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_PERSONS">Find All Persons</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_PERSON">Find Person</a>

<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=CONNECTION&message=">Connection</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=OFFLINE">Offline</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=Password_Recovery">Password Recovery</a>
<form action="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=Password_Recovery" method="GET">
	<input type=hidden id="ACTION" name="ACTION" value="Password_Recovery"/>
	<label for="mail">Mail : </label>
	<input type=text id="mail" name="mail" value="" required/>
	<input value="Forget" type="submit" />
</form>
</body>
</html>