package edu.ics499.model.payloads;

import java.util.ArrayList;
import java.util.List;

public class ForecastWeatherPayload extends Payload {
	
	
	private String cod;
	private String message;
	private String cnt;
	
	private List<ForecastWeatherPayloadItem> list = new ArrayList<ForecastWeatherPayloadItem>();
	
	private String city_id;
	private String city_name;
	private String city_coord_lat;
	private String city_coord_lon;
	private String city_country;
	private String city_timezone;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public List<ForecastWeatherPayloadItem> getList() {
		return list;
	}
	public void setList(List<ForecastWeatherPayloadItem> list) {
		this.list = list;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCity_coord_lat() {
		return city_coord_lat;
	}
	public void setCity_coord_lat(String city_coord_lat) {
		this.city_coord_lat = city_coord_lat;
	}
	public String getCity_coord_lon() {
		return city_coord_lon;
	}
	public void setCity_coord_lon(String city_coord_lon) {
		this.city_coord_lon = city_coord_lon;
	}
	public String getCity_country() {
		return city_country;
	}
	public void setCity_country(String city_country) {
		this.city_country = city_country;
	}
	public String getCity_timezone() {
		return city_timezone;
	}
	public void setCity_timezone(String city_timezone) {
		this.city_timezone = city_timezone;
	}
	public String getCity_sunrise() {
		return city_sunrise;
	}
	public void setCity_sunrise(String city_sunrise) {
		this.city_sunrise = city_sunrise;
	}
	public String getCity_sunset() {
		return city_sunset;
	}
	public void setCity_sunset(String city_sunset) {
		this.city_sunset = city_sunset;
	}
	private String city_sunrise;
	private String city_sunset;
}
