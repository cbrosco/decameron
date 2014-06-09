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
<div class="container">
<h1>Search</h1>
<form action="SearchServlet" method="post">
<input type="text" name="searchTerm">
 <input type="radio" name="searchType" value="giorno"> Day
 <input type="radio" name="searchType" value="teller"> Storyteller
 <input type="radio" name="searchType" value="location" checked> Location
 <input type="submit" value="Search" class="btn btn-default">
</form>
<%ArrayList<Integer> storyIds= (ArrayList<Integer>)session.getAttribute("storyIds"); 
 int numResults= storyIds.size();
 String searchType= request.getParameter("searchType");
 if(searchType.equals("teller")) searchType= "Story teller";
 if(searchType.equals("giorno")) searchType= "Day";
 if(searchType.equals("location")) searchType= "Location";
%>
Search for <%=searchType%> "<%=request.getParameter("searchTerm")%>" produced <%=numResults%> results <br>

<%for(int i=0; i< storyIds.size(); i++) {
	Story s= new Story(storyIds.get(i));
	String link= "mapAndInfo.jsp?storyID=" +storyIds.get(i);
	if(searchType.equals("Location")){
		String location= s.getSimilarLocation(request.getParameter("searchTerm"));
%>
<a class="results" href=<%=link %>>Day <%=s.getGiorno()%> Story <%=s.getNumber()%> Location:  <%=location%> </a> <br>

<% }else{%>
<a class="results" href=<%=link %>>Day <%=s.getGiorno()%> Story <%=s.getNumber()%> StoryTeller <%=s.getStoryteller() %> </a>
<br>
<%}
}%>

</div>
</body>
</html>