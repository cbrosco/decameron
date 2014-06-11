<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import= "decameron.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign in</title>
</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<div class="container">
<h1>Sign In</h1>
<% String mapId= request.getParameter("mapToDelete");
if(mapId != null){ %>
<p class= "errorMessage"> Please sign in. Only Admin have deleting privileges </p>
<%} %>
<% if(session.getAttribute("error") != null) {
		if(session.getAttribute("error").equals(ErrorTypes.INVALID_USER)){
			session.setAttribute("error", null);
			%>
			<p class= "errorMessage"> Error- Invalid credentials. Please try again. </p>
			<% 
		}
	}
%>

<form action="LoginServlet" method="post">
			Username: <input type="text" name="username"><br>
			Password: <input type="password" name="password"><br>
			<input type="submit" value="Login" class="btn btn-primary">
			<%if(mapId != null){ %>
			<input type=hidden name="mapToDelete" value=<%=mapId%>>
			<% } %>
			</form>
</div>
</body>
</html>