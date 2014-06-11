<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "decameron.*" %>
    <%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map Preview</title>

<jsp:include page="mapDisplay.jsp"></jsp:include>

</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<%Story st= (Story)session.getAttribute("story"); 
ArrayList<Location> locs= st.getLocations();
int numLocs= locs.size();
%>
<div class="container">
Day <%=st.getGiorno() %> Story <%=st.getNumber()%> <br> 
Storyteller: <%=st.getStoryteller()%><br>
Ruler: <%=st.getRegina() %><br>

Extra Info :
<textarea rows="2" cols="50" name="extra" form ="saving">
 <%=st.getExtraInfo() %>
</textarea>
<form action="ChangeLocationsServlet" method="post">
<div id="locationNames">
Locations: <br>
<%
for(int i=0; i < numLocs; i++ ){ %>
<%=i+1%>: <input type="text" name="name<%=i%>" value=<%=locs.get(i).getName()%>> Latitude:<input type="text" name="lat<%=i%>" value=<%=locs.get(i).getLat()%>> Longitude: <input type="text" name="lon<%=i%>" value=<%=locs.get(i).getLong()%>> <br>
<% } %>
<input type="submit" value="Update Locations" class="btn btn-default">
</form>
</div>
<form action="SaveMapServlet" id= "saving" method="post">
<input type="submit" value="Save Map" class="btn btn-primary">
</form>

</div>
<div id="map-canvas">
</div>



</body>
</html>