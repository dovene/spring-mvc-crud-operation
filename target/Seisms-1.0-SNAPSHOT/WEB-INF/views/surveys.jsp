<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Seismic Survey</h2>
	 <h3><a href="surveys/create">Add a survey</a></h3>
		<table border="2" width="70%" cellpadding="2">
        <tr><th>Name</th><th>Country</th><th>Date</th><th>Level</th><th colspan="2">Actions</th></tr>
           <c:forEach var="survey" items="${surveys}">
           <tr>
           <td>${survey.name}</td>
           <td>${survey.country}</td>
           <td>${survey.date}</td>
           <td>${survey.level}</td>
           <td><a href="surveys/edit/${survey.name}">Edit</a></td>
           <td><a href="surveys/delete?name=${survey.name}">Delete</a></td>
           </tr>
           </c:forEach>
           </table>
</body>
</html>