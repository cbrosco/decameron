<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "decameron.*" %>
    <%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
<h1>Search</h1>
<form action="SearchServlet" method="post">
<input type="text" name="searchTerm">
 <input type="radio" name="searchType" value="giorno"> Day
 <input type="radio" name="searchType" value="teller"> Storyteller
 <input type="radio" name="searchType" value="location"> Location
 <input type="submit" value="Search">
</form>
<%ArrayList<Integer> storyIds= (ArrayList<Integer>)session.getAttribute("storyIds"); 
 String searchType= request.getParameter("searchType");
 if(searchType.equals("teller")) searchType= "Story teller";
 if(searchType.equals("giorno")) searchType= "Day";
 if(searchType.equals("location")) searchType= "Location";
%>
Search for <%=request.getParameter("searchTerm")%> as a <%=searchType%>

<%for(int i=0; i<storyIds.size(); i++) {
	Story s= new Story(storyIds.get(i));
	if(request.getParameter("searchType").equals("giorno")){
%>
<a href="mapAndInfo">Day <%=s.getGiorno()%> Story <%=s.getNumber()%> </a>

<% }else if(request.getParameter("searchType").equals("teller")){%>
<a href="mapAndInfo">Day <%=s.getGiorno()%> Story <%=s.getNumber()%> StoryTeller <%=s.getStoryteller() %> </a>

<%} else { 
		String location= s.getSimilarLocation(request.getParameter("searchTerm"));%>

<a href="mapAndInfo">Day <%=s.getGiorno()%> Story <%=s.getNumber()%> Location <%=location%> </a>
<%}
}%>


</body>
</html>