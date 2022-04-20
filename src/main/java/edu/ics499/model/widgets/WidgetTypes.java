package edu.ics499.model.widgets;

import java.util.*;

/*
 * when creating a new widget type, add a new name for each list.
 * 		First list is the base url up to any customized query paramaters.
 * 		Second list is any other static text between query paramaters and declaring the key. if no key or following params insert empty string ""
 * 		third list is name of environment variable containing the private api key. if no key insert empty string ""
 * 		YOU MUST CREATE ENVIRONMENT VARIABLE FOR ANY KEY IN FILE WidgetServicesImp.java with corresponding name and value.
 * 			To do so: right click on WidgetServiceImp.java => hover over RunAs, click Run Configurations. scroll over to ENvironment tab and create key.
 */
public class WidgetTypes {

	public static final List<String> types = new ArrayList<>(List.of(
        "calendar",
		"currentWeather",
		"forecastWeather",
		"stock"));

	public static final Map<String, String> mapWidgetTypeToUrl = new HashMap<>(Map.of(
	    "calendar", "",	
	    "currentWeather", "http://api.openweathermap.org/data/2.5/weather?q=",
		"forecastWeather", "http://api.openweathermap.org/data/2.5/forecast?q=",
		"stock", "https://yfapi.net/v6/finance/quote?region=US&lang=en&symbols="));

	public static final Map<String, String> mapWidgetTypeToMidUrl = new HashMap<>(Map.of(
	    "calendar", "",
	    "currentWeather", "&appid=",
		"forecastWeather", "&units=imperial&appid=",
		//the stock thing is technically the end
		"stock", "%2CUSD%3DX"));

	public static final Map<String, String> mapWidgetTypeToApiKeyName = new HashMap<>(Map.of(
	    "calendar", "",
	    "currentWeather", "weather_key",
		"forecastWeather", "weather_key",
		"stock", "stock_key"));

	public static final Map<String, String> mapWidgetTypeToUpdateFrequency = new HashMap<>(Map.of(
	    "calendar", "0",
	    "currentWeather", "0",
		"forecastWeather", "0",
		"stock", "0"));
	//is apiKeyHeader
	//calendar, false
	//....
	//stock, true
	
	//apiKeyHeaderName
	//calendar, ""
	//stock, api-x-
}
