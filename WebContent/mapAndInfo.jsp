<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "decameron.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<% int storyID= Integer.parseInt(request.getParameter("storyID"));
Story st;
if (storyID != Story.MULTI_STORY){
	st= new Story(storyID); 
	session.setAttribute("story", st);
} else {
	st= (Story)session.getAttribute("story");
}
%>

<title>Day <%=st.getGiorno()%> Story <%=st.getNumber()%> </title>
<jsp:include page="mapDisplay.jsp"></jsp:include>
</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<%if(st.getGiorno() != Story.ALL) {%>
	Day <%=st.getGiorno() %>  
<%} else{ %>
	All Days 
<%} %>
<%if(st.getNumber() != Story.ALL) {%>
	 Story <%=st.getNumber()%> <br>
<%} else{ %>
	 All Numbers  <br>
<%} %>
Storyteller: <%=st.getStoryteller()%><br>
<%if(st.getNumber() != Story.ALL) {%>
Ruler: <%=st.getRegina() %><br>
Extra Info: <%=st.getExtraInfo() %>
<%} %>
<div id="map-canvas">
</div>
<%session.setAttribute("story", null); %>

</body>
</html>