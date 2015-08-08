package simon.demo.service;

import java.util.List;

public class CityListService {
	//This class is used to avoid hard-coding city names in the Java code. The city names are externalized into Spring XML.
	
	List<String> cityList;

	public List<String> getCityList() {
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
	
}
