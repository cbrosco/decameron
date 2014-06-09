<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="decameron.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map Saved</title>
<jsp:include page="mapDisplay.jsp"></jsp:include>
</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<div class="container">
Your Map was saved successfully
<br>
<%Story st= (Story)session.getAttribute("story"); %>
Day <%=st.getGiorno() %> Story <%=st.getNumber()%> <br> 
Storyteller: <%=st.getStoryteller()%><br>
Ruler: <%=st.getRegina() %><br>
Extra Info: <%=st.getExtraInfo() %>
</div>
<div id="map-canvas">
</div>
<%session.setAttribute("story", null); %>


</body>
</html>