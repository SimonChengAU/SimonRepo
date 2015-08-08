package simon.demo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import simon.demo.model.WeatherDetails;

public class OnlineWeatherService {
	
	public String urlTemplate;
	public String cityNamePlaceHolder;

	public String getUrlTemplate() {
		return urlTemplate;
	}

	public void setUrlTemplate(String urlTemplate) {
		this.urlTemplate = urlTemplate;
	}

	public String getCityNamePlaceHolder() {
		return cityNamePlaceHolder;
	}

	public void setCityNamePlaceHolder(String cityNamePlaceHolder) {
		this.cityNamePlaceHolder = cityNamePlaceHolder;
	}
	
	public WeatherDetails queryWeatherDetails(String cityName) 
	{
		WeatherDetails weatherDetails = new WeatherDetails();
		
		DateFormat dateFormat = new SimpleDateFormat("E hh:mm a");
		Date date = new Date();
		weatherDetails.setUpdateTime(dateFormat.format(date));
		
		String fullUrl = urlTemplate.replaceAll(cityNamePlaceHolder, cityName);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			//Read the XML returned by the URL
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(fullUrl).openStream());
			
			//Extract temperature from the XML Document
			Element rootElement = doc.getDocumentElement();
			Node temperatureNode = rootElement.getChildNodes().item(1);
			float temperatureInKelvin = Float.parseFloat(temperatureNode.getAttributes().getNamedItem("value").getNodeValue());
			float temperatureInCelsius = temperatureInKelvin - 272.15f;
			weatherDetails.setTemperature(String.format("%.1f °„C", temperatureInCelsius));
			
			//Extract the wind from the XML document
			Node windNode = rootElement.getChildNodes().item(4);
			Node windSpeedNode = windNode.getChildNodes().item(0);
			weatherDetails.setWind(windSpeedNode.getAttributes().getNamedItem("value").getNodeValue());
			
			Node weatherNode = rootElement.getChildNodes().item(8);
			weatherDetails.setWeather(weatherNode.getAttributes().getNamedItem("value").getNodeValue());
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weatherDetails;
	}
}
