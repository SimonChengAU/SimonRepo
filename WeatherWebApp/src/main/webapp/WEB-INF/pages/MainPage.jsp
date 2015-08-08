<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.10.4/dojo/dojo.js" data-dojo-config="'parseOnLoad':true,isDebug: true"></script>


<html>
<head>
<style>
.error {
	color: #ff0000;
}
</style>
</head>
<body>
<h2>Code Test - Get Current Weather</h2>
<h3>Please choose a city to see the weather details.</h3>
<form:form method="POST" commandName="WeatherForm">
<form:errors path="*" cssClass="errorblock" element="div"/>

<table>
<tr>
	<td>City : </td>
	<td>
		<form:select path="city" onchange="javascript:lookupWeatherDetails()">
			<form:option value="NONE" label="--- Select ---"/>
			<form:options items="${cityList}" />
		</form:select>
	</td>
	<td><form:errors path="city" cssClass="error" /></td>
</tr>
<tr>
	<td>Update Time :</td>
	<td><form:input path="updateTime" /></td>
	<td><form:errors path="updateTime" cssClass="error" /></td>
</tr>
<tr>
	<td>Weather :</td>
	<td><form:input path="weather" /></td>
	<td><form:errors path="weather" cssClass="error" /></td>
</tr>
<tr>
	<td>Temperature :</td>
	<td><form:input path="temperature" /></td>
	<td><form:errors path="temperature" cssClass="error" /></td>
</tr>
<tr>
	<td>Wind :</td>
	<td><form:input path="wind" /></td>
	<td><form:errors path="wind" cssClass="error" /></td>
</tr>
</form:form>


<script type="text/javascript">

    function lookupWeatherDetails()
    {
        var selectBox = document.getElementById("city");
        var chosenCity = selectBox.options[selectBox.selectedIndex].value;
    	if (chosenCity == "NONE") {
    		console.log("No need to lookup since ---Select--- option chosen.")
    	}
   		else {
    		var xhrArgs = {
					url : "http://localhost:8080/WeatherWebApp/lookupWeatherDetails",
					handleAs : 'json',
					content : {
						city : chosenCity
					},
					load : function(dataFromServer) {
						cityChanged(dataFromServer);
					},
					error: function(error) {
						console.log("error:" + error);
					}
				};
				//make the ajax call
				dojo.xhrGet(xhrArgs);
    	}
    };

	function cityChanged(dataFromServer) 
	{
		  console.log(dataFromServer.updateTime, dataFromServer.temperature, dataFromServer.weather, dataFromServer.wind);
		  var updateTime = document.getElementById("updateTime");
		  updateTime.value = dataFromServer.updateTime; 
		  var updateTime = document.getElementById("weather");
		  updateTime.value = dataFromServer.weather; 
		  var updateTime = document.getElementById("temperature");
		  updateTime.value = dataFromServer.temperature; 
		  var updateTime = document.getElementById("wind");
		  updateTime.value = dataFromServer.wind; 
	};
    
</script>

</body>
</html>