<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import= "decameron.*" %>
    <%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map Preview</title>

<style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 20; padding: 0 }
      #map-canvas {height: 70%;}
</style>
    <%Story st= (Story)session.getAttribute("story"); 
    	ArrayList<Location> locs= st.getLocations();
    	int numLocs= locs.size();
    	Location first= new Location("Florence", 11, 43);
    	if(numLocs >0){
    		first= locs.get(0);
    	}
    %>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBJX6pI9eeV-On7_Eosdkeex44wlfIeTqY&sensor=false">
    </script>
        <script type="text/javascript">
      function initialize() {
    	  var firstLat= parseFloat("<%=first.getLat()%>");
          var firstLong= parseFloat("<%=first.getLong()%>");
          var firstLocation = new google.maps.LatLng(firstLat,firstLong);
        var mapOptions = {
          center: firstLocation,
          zoom: 5,
          mapTypeId: google.maps.MapTypeId.TERRAIN
          
        };
        
        var styleArray = [
                          {
                        	    "elementType": "labels",
                        	    "stylers": [
                        	      { "visibility": "off" }
                        	    ]
                        	  },{
                        	    "featureType": "road",
                        	    "elementType": "geometry",
                        	    "stylers": [
                        	      { "visibility": "off" }
                        	    ]
                        	  },{
                        	    "featureType": "administrative",
                        	    "elementType": "geometry.stroke",
                        	    "stylers": [
                        	      { "visibility": "on" },
                        	      { "weight": 0.6 }
                        	    ]
                        	  },{
                        	  }
                        	];
        var map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);
        map.setOptions({styles: styleArray});
        var flightPlanCoordinates = [];
        <%
        	for(int j= 0; j< locs.size(); j++){
        		Location loc= locs.get(j);
        	
        %>
  
        var locationName= "<%=loc.getName()%>";
        var latitude= parseFloat("<%=loc.getLat()%>");
        var longitude= parseFloat("<%=loc.getLong()%>");
        var myLatlng1 = new google.maps.LatLng(latitude,longitude);
        var marker = new google.maps.Marker({
      	    position: myLatlng1,
      	    map: map,
      	    title:locationName
      	});
        flightPlanCoordinates.push(myLatlng1);
		
        <% } %>
        var flightPath = new google.maps.Polyline({
            path: flightPlanCoordinates,
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 2
          });

          flightPath.setMap(map);
      }
      google.maps.event.addDomListener(window, 'load', initialize);
  
    </script>

</head>
<body>
<jsp:include page="toolbar.jsp"></jsp:include>
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
<input type="submit" value="Update Locations">
</form>
</div>
<div id="map-canvas">
</div>


<form action="SaveMapServlet" id= "saving" method="post">
<input type="submit" value="Save Map">
</form>
</body>
</html>