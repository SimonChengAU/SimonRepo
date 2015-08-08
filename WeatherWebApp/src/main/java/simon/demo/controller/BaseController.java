package simon.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import simon.demo.model.WeatherDetails;
import simon.demo.service.CityListService;

@Controller
public class BaseController {
	
	private static final String MAIN_PAGE = "MainPage";
	
	@Autowired
	CityListService cityListService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView initForm(ModelMap model) {
        ModelAndView mv = new ModelAndView(MAIN_PAGE);
        mv.addObject("WeatherForm", new WeatherDetails());
        return mv;
	}
	
	@ModelAttribute("cityList")
	public Map<String, String> loadCountryList(){
		Map<String, String> cityList = new LinkedHashMap<String, String>();
		for (String city : cityListService.getCityList()) {
			cityList.put(city, city);
		}
		return cityList;
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String processSubmit(
//		@ModelAttribute("WeatherForm") WeatherDetails weatherDetails,
//		BindingResult result, SessionStatus status) {
//		
//		//clear the command object from the session
//		status.setComplete(); 
//		
//		//This OnPost method will never be called
//		return "Dummy View";
//	}
}
