package simon.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import flexjson.JSONSerializer;
import simon.demo.model.WeatherDetails;
import simon.demo.service.OnlineWeatherService;

@Controller
public class WeatherLookupController {
	
	@Autowired
	private OnlineWeatherService onlineWeatherService;
	
    @RequestMapping(value = "/lookupWeatherDetails", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String lookupWeatherDetails(@RequestParam(value = "city", required = true) String city)
    {
        JSONSerializer serializer = new JSONSerializer();

        WeatherDetails weatherDetails = onlineWeatherService.queryWeatherDetails(city);

        String flexJsonString = serializer.serialize(weatherDetails);

        return flexJsonString;
    }
}
