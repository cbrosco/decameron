<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "decameron.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
int userId= Integer.parseInt(request.getParameter("userID"));
Users usr= new Users(userId); %>

<title>User Page</title>
</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<div class="container">
<h1><%=usr.getName() %>'s page</h1>
Hello <%=usr.getName()%>, welcome to GeoDecameron.

<%
if(usr.isAdmin()){
%>
<p>As an Administrator you can add other users to this site.</p>
<p> Members have the ability to add new maps</p>
<p>If you choose to make these new users also administrators they will be able to
delete maps and add other users.</p>



<% if(session.getAttribute("error") != null) {
		if(session.getAttribute("error").equals(ErrorTypes.NEW_USER_INFORMATION_MISSING)){
			session.setAttribute("error", null);
			%>
			<p class= "errorMessage"> Error- You must enter all new user information. Please try again. </p>
			<% 
		}
	}
%>

<h2>Adding a member:</h2>
<form action="CreateUserServlet" method="post">
			New Username: <input type="text" name="username"><br>
			New Password: <input type="password" name="password"><br>
			<input type="checkbox" name="makeAdmin" value="makeAdmin">This user has Admin privileges<br>
			<input type="submit" value="Create User" class="btn">
</form>			

<%} else {%>
<p>As a non-Administrative member, you cannot add new members.</p>

<%} %>
</div>
</body>
</html>