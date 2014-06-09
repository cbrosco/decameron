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
<% if(session.getAttribute("error") != null) {
		if(session.getAttribute("error").equals(ErrorTypes.NOT_A_COORDINATE)){
			session.setAttribute("error", null);
			%>
			<p class= "errorMessage"> Error- Please enter decimal numbers for coordinates </p>
			<% 
		}
	}
%>
<%Story st= (Story)session.getAttribute("story"); %>
Select the next Location for the story from <%=st.getStoryteller() %> on Giorno <%=st.getGiorno() %>

<form action="LocationServlet" method="post">
<%
String num= (String)session.getAttribute("numLocations");
int locs= Integer.parseInt(num);
for(int i=0; i < locs; i++){
	String variableName= "name" + i;
	String variableLat= "lat" + i;
	String variableLon= "lon" + i;
	String previousValueName= request.getParameter(variableName);
	String previousValueLat= request.getParameter(variableLat);
	String previousValueLon= request.getParameter(variableLon);
	if(previousValueName != null){
	%>
	Location Name:<input type="text" name=<%=variableName%> value=<%=previousValueName%>> Latitude:<input type="text" name=<%=variableLat%> value=<%=previousValueLat%>> Longitude: <input type="text" name=<%=variableLon%> value=<%=previousValueLon%>> <br>  
<% } else{ %>	
Location Name:<input type="text" name=<%=variableName%>> Latitude:<input type="text" name=<%=variableLat%>> Longitude: <input type="text" name=<%=variableLon%>> <br>   

<% }
}	
%>
<input type="submit" value="Preview Map">
</form>
</body>
</html>