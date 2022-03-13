package edu.ics499.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ics499.model.payloads.CurrentWeatherPayload;
import edu.ics499.model.payloads.ForecastWeatherPayload;
import edu.ics499.model.widgets.WeatherWidget;
import edu.ics499.model.widgets.Widget;
import edu.ics499.serviceImp.WeatherWidgetServiceImp;
import edu.ics499.serviceImp.WidgetServiceImp;

@RestController
@RequestMapping("/widgets")
public class WidgetController {
	@Autowired
	WidgetServiceImp widgetService;
	
	@Autowired
	WeatherWidgetServiceImp weatherWidgetService;
	
	//localhost:port/widgets/all
	@GetMapping("/all")
	public List<Widget> list(){
		return widgetService.getAll();
	}
	
	//localhost:port/widgets/one/{id}
	@GetMapping("/one/{id}")
	public Widget get(@PathVariable Long id) {
		return widgetService.getWidgetById(id);
	}
	
	//localhost:port/widgets/weather/all
	@GetMapping("/weather/all")
	public List<WeatherWidget> weatherList(){
		return weatherWidgetService.getAll();
	}
	
	//localhost:port/widgets/weather/one/{id}
	@GetMapping("/weather/one/{id}")
	public WeatherWidget getWeatherWidget(@PathVariable Long id) {
		return weatherWidgetService.getWeatherWidgetById(id);
	}
	
	//localhost:port/widgets/weather/one/{id}/current
	@GetMapping("/weather/one/{id}/current")
	public CurrentWeatherPayload getCurrentWeather(@PathVariable Long id) throws IOException {
		return weatherWidgetService.getCurrentPayload(id);
	}
	
	//localhost:port/widgets/weather/one/{id}/forecast
	@GetMapping("/weather/one/{id}/forecast")
	public ForecastWeatherPayload getForecastWeather(@PathVariable Long id) throws IOException {
		return weatherWidgetService.getForecastPayload(id);
	}
	
	//localhost:port/widgets/weather/one/{id}/update?query=newCity
	@PostMapping("/weather/one/{id}/update")
	public WeatherWidget updateWidget(@RequestParam String query, @PathVariable Long id) {
		return weatherWidgetService.setWeatherQuery(query, id);
	}
}
