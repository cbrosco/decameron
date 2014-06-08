<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<h1>Search</h1>
<form action="SearchServlet" method="post">
<input type="text" name="searchTerm">
 <input type="radio" name="searchType" value="giorno"> Day
 <input type="radio" name="searchType" value="teller"> Storyteller
 <input type="radio" name="searchType" value="location"> Location
 <input type="submit" value="Search">
</form>



</body>
</html>