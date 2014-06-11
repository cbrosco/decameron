<!DOCTYPE html>
<html>
<meta charset="UTF-8">
   <%@ page import= "decameron.*" %>
<link rel="stylesheet" href="bootstrap.css" type="text/css">
<script src="bootstrap.js" type="text/javascript"></script>
<!--  Code currently from w3schools  -->
<style>

h1{
	font-size:25px;
}

h2{
	font-size:20px;
}
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
}

li {
    float: left;
}

p.errorMessage {
    color: red;
}

a.toolbar{
    
    width: 120px;
    font-weight: bold;
    color: #FFFFFF;
    background-color: #98bf21;
    text-align: center;
    text-decoration: none;
}




a.results{
	margin: 5px;
}

</style>
<ul class="nav navbar-nav">
        <li><a class = "toolbar" href="index.jsp">Home</a></li>
        <li><a class = "toolbar" href="admin.jsp">Create</a></li>
        <li><a class = "toolbar" href="search.jsp">Search</a></li>
       	<li><a class = "toolbar" href="select.jsp">Select</a></li>
</ul>
<%	Users usr = (Users)session.getAttribute("user"); 
	String name= "Sign In";
	String link= "signIn.jsp";
	if(usr != null){ 
		name= usr.getName();
		link= "userpage.jsp?userID=" + usr.getId();
	}
	%>
<br>&nbsp;&nbsp;&nbsp;<a class="btn-link btn-lg" href=<%=link%>><%=name%></a>
	

<br><br>
</html>

