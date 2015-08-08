package simon.demo.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import simon.demo.model.WeatherDetails;
import simon.demo.service.CityListService;
import simon.demo.service.OnlineWeatherService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestOnlineWeatherService {
	
	@Autowired
	private OnlineWeatherService onlineWeatherService;

	@Autowired
	private CityListService cityListService;
	
	@Test
	public void testOnlineLookup() {
		for (String cityName : cityListService.getCityList()) {
			WeatherDetails weatherDetails = onlineWeatherService.queryWeatherDetails(cityName);
			assertNotNull(weatherDetails.getUpdateTime());
			assertNotNull(weatherDetails.getTemperature());
			assertNotNull(weatherDetails.getWeather());
			assertNotNull(weatherDetails.getWind());
		}
	}
}
