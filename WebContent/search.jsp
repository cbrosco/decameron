<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>

<form action="SearchServlet" method="post">
Search: <input type="text" name="searchTerm">
 <input type="radio" name="searchType" value="giorno"> Day
 <input type="radio" name="searchType" value="teller"> Storyteller
 <input type="radio" name="searchType" value="location"> Location
</form>

</body>
</html>