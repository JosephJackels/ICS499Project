package edu.ics499.model.widgets;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.ics499.model.payloads.CurrentWeatherPayload;
import edu.ics499.model.payloads.ForecastWeatherPayload;

@Entity
@Table(name="weatherWidgets")
public class WeatherWidget extends Widget {
	//already has widgetId and dashboard fields
	
	/*
	 * replace {requestType} with 
			weather - for current weather 
			forecast  - for forecasted weather
		replace {query} with 
			zip={zip code}, {country code} - to search by zip code, country code optional, will presume US if omitted
			q={city name}, {state code}, {country code} - to search by city name. state/country codes are optional
			 	(uses ISO 3166 codes for state/country) https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes
		replace {key} with the api key
		replace {units} with
			imperial - to use Fahrenheit
			metric - to use Celsius
		 * 
	*/
	//Transient annotation = not saved in the db, these are hardcoded for all
	//weatherwidgets so it does not need to be saved
	/*
	@Transient
	private String baseUrl = "https:api.openweathermap.org/data/2.5{requestType}?{query}&appid={key}&units={units}";
	
	@Transient
	private String apiKey = "b4808d3be62ce204189dcf0c196809f0";
	*/
	//keeping these separate so it's easy to update a single widget's settings
	
	//no longer needed?
	//private String requestType;
	private String query;
	private String units;
	
	//response from api
	@ManyToOne
	private CurrentWeatherPayload currentPayload;
	
	@ManyToOne
	private ForecastWeatherPayload forecastPayload;
	
	//maybe we should do a makeshift cache to reduce the amount of external
	//api calls and track when it was last updated, then only update?
	//once a day or something?
	
	public WeatherWidget() {
		super();
	}

	public WeatherWidget(String query, String units) {
		super();
		this.query = query;
		this.units = units;
	}

	/*
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
*/
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	public CurrentWeatherPayload getCurrentPayload() {
		return currentPayload;
	}
	
	public void setCurrentPayload(CurrentWeatherPayload currentPayload) {
		this.currentPayload = currentPayload;
	}
	
	public ForecastWeatherPayload getForecastPayload() {
		return forecastPayload;
	}
	
	public void setForecastPayload(ForecastWeatherPayload forecastPayload) {
		this.forecastPayload = forecastPayload;
	}
}
