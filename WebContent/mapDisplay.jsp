<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<%@ page import="decameron.*" %>
<%@ page import="java.util.*" %>
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
		<% if(st != null && !st.isMulipleStoriesCombined()) {%>
          flightPath.setMap(map);
          <% } %>
      }
      google.maps.event.addDomListener(window, 'load', initialize);
  
    </script>


</body>
</html>