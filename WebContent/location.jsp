<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "decameron.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Locations</title>
</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<%Story st= (Story)session.getAttribute("story"); %>
Select the next Location for the story from <%=st.getStoryteller() %> on Giorno <%=st.getGiorno() %>

<form action="LocationServlet" method="post">
<%
String num= (String)session.getAttribute("numLocations");
int locs= Integer.parseInt(num);
for(int i=0; i < locs; i++){
	%>
Location Name:<input type="text" name="name<%=i%>"> Latitude:<input type="text" name="lat<%=i%>"> Longitude: <input type="text" name="lon<%=i%>"> <br>  

<% }
%>
<input type="submit" value="Preview Map">
</form>
</body>
</html>