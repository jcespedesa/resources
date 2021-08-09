
var map;
		  	
var latRaw=document.getElementById('lat');
var lonRaw=document.getElementById('lon');
		  	
var lat=latRaw.value;
var lon=lonRaw.value;
	  			  	 	
function initMap() 
{
	//alert("Lat is "+ lat +" and Lon is "+ lon);
		  		
   	map=new google.maps.Map(document.getElementById('map'), 
   	{
   		center: new google.maps.LatLng(lat,lon),
   		zoom: 10
   	});
}