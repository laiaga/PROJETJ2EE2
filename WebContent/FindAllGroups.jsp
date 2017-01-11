<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Groups</title>
</head>
<body>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_GROUPS">Find All Groups</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_ALL_PERSONS">Find All Persons</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=FIND_PERSON">Find Person</a>

<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=CONNECTION&message=">Connection</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=OFFLINE">Offline</a>
<a href="/PROJETJ2EE2/GestionAnnuaireServlet?ACTION=Password_Recovery">Password Recovery</a>

<br><br><br>

	<table border="1" cellpadding="5">
			<h1>List of groups</h1>
            <tr>
                <th>Group name</th>
                <th>ID</th>
            </tr>
            <c:forEach items="${groups}" var="group">
			   <tr>
			       <td>${group.name}</td>
			       <td>${group.groupId}</td>
			    </tr>
			</c:forEach>
	</table>
</body>
</html>