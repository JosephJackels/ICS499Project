package edu.ics499.model.widgets;

import java.util.HashMap;
import java.util.Map;

/*
 * when creating a new widget type, add a new name for each list. 
 * 		First list is the base url up to any customized query paramaters.
 * 		Second list is any other static text between query paramaters and declaring the key. if no key or following params insert empty string ""
 * 		third list is name of environment variable containing the private api key. if no key insert empty string ""
 * 		YOU MUST CREATE ENVIRONMENT VARIABLE FOR ANY KEY IN FILE WidgetServicesImp.java with corresponding name and value.
 * 			To do so: right click on WidgetServiceImp.java => hover over RunAs, click Run Configurations. scroll over to ENvironment tab and create key.
 */
public class WidgetTypes {
	public static final Map<String, String> mapWidgetTypeToUrl = new HashMap<String, String>(Map.of(
			"currentWeather", "http://api.openweathermap.org/data/2.5/weather?q=",
			"forecastWeather", "http://api.openweathermap.org/data/2.5/forecast?q=",
			"calendar", "",
			"stock", "INSERT STOCK URL HERE"));
	public static final Map<String, String> mapWidgetTypeToMidUrl = new HashMap<String, String>(Map.of(
			"currentWeather", "&appid=",
			"forecastWeather", "&appid=",
			"calendar", "",
			"stock", ""));
	public static final Map<String, String> mapWidgetTypeToApiKeyName = new HashMap<String, String>(Map.of(
			"currentWeather", "weather_key",
			"forecastWeather", "weather_key",
			"calendar", "",
			"stock", ""));
}
