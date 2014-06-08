<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import= "decameron.*" %>
    <%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select</title>
<jsp:include page="mapDisplay.jsp"></jsp:include>

</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<h1> Select</h1>
<form action="SelectServlet" method="post">
Day:
<select name="giorno">
<option value="all" selected>all</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3" >3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
</select>
Story teller:
<select name="teller">
<option value="all" selected>all</option>
<option value="Pampinea">Pampinea</option>
<option value="Filomena">Filomena</option>
<option value="Neifile" >Neifile</option>
<option value="Filostrato">Filostrato </option>
<option value="Fiammetta">Fiammetta</option>
<option value="Elissa">Elissa</option>
<option value="Dioneo">Dioneo</option>
<option value="Lauretta">Lauretta</option>
<option value="Emilia">Emilia</option>
<option value="Panfilo">Panfilo</option>
<option value="N/A">N/A</option>
</select>
Story Number:
<select name="number">
<option value="N/A" selected>N/A</option>
<option value="all">all</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3" >3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10" selected>10</option>
</select>
<input type="submit" value="Select">
</form>
<%Story st= (Story)session.getAttribute("story"); 
%>

<% if(st != null){ %>
Day: <%=st.getGiorno()%> Storyteller: <%=st.getStoryteller() %> Number<%=st.getNumber()%> <br>
Ruler: <%=st.getRegina() %><br>
Extra Info: <%=st.getExtraInfo() %>
<%} %>
<div id="map-canvas">
</div>



</body>
</html>