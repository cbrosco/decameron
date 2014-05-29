<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import= "decameron.*" %>
    <%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select</title>
<style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 20; padding: 0 }
      #map-canvas {height: 70%;}
</style>
    <%Story st= (Story)session.getAttribute("story"); 
    Location first= new Location("Florence", 11, 43);
    ArrayList<Location> locs= new ArrayList<Location>();
    if(st != null){
    		locs= st.getLocations();
    		int numLocs= locs.size();
    		if(numLocs >0){
    			first= locs.get(0);
    		}
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
		<% if(!st.isMulipleStoriesCombined()) {%>
          flightPath.setMap(map);
          <% } %>
      }
      google.maps.event.addDomListener(window, 'load', initialize);
  
    </script>

</head>
<body>
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
</select>
<input type="submit" value="Select">
</form>


<% if(st != null){ %>

<%} %>
<div id="map-canvas">
</div>



</body>
</html>