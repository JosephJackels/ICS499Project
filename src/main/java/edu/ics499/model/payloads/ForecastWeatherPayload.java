package edu.ics499.model.payloads;

import java.util.*;

import javax.persistence.*;

import edu.ics499.model.widgets.*;

@Entity
@Table(name="forecastPayloads")
public class ForecastWeatherPayload extends Payload {


	private String cod;
	private String message;
	private String cnt;

	@OneToMany
	@JoinTable(
			name="forecastPayloads_forecastItems",
			joinColumns = @JoinColumn(name="payloadID"),
			inverseJoinColumns = @JoinColumn(name="forecastPayloadItemID"))
	private List<ForecastWeatherPayloadItem> list = new ArrayList<>();

	private String city_id;
	private String city_name;
	private String city_coord_lat;
	private String city_coord_lon;
	private String city_country;
	private String city_timezone;
	private String city_sunrise;
	private String city_sunset;

	@OneToMany
	@JoinTable(
			name="widgets_forecastWeatherPayloads",
			joinColumns = @JoinColumn(name="PayloadID"),
			inverseJoinColumns = @JoinColumn(name="widgetID"))
	private List<WeatherWidget> listeners = new ArrayList<>();

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

	public void addPayloadItem(ForecastWeatherPayloadItem item) {
	    list.add(item);
	}
	//shows payload contents to make sure it is populating correctly
	public void showPayload() {
	    System.out.println("cod - " + cod);
	    System.out.println("message - " + message);
	    System.out.println("cnt - " + cnt);
	    System.out.println("city id - " + city_id);
	    System.out.println("city name - " + city_name);
	    System.out.println("coord lon - " + city_coord_lon);
	    System.out.println("coord lat - " + city_coord_lat);
	    System.out.println("country - " + city_country);
	    System.out.println("timezone - " + city_timezone);
	    System.out.println("sunrise - " + city_sunrise);
	    System.out.println("sunset - " + city_sunset);
	    System.out.println("list size - " + list.size());
	}

	//shows first payload item, if the first populates correctly, the rest should
	public void showPayloadItem() {
	    ForecastWeatherPayloadItem item = list.get(0);
	    System.out.println("dt - " + item.getDt());
	    System.out.println("feels like - " + item.getMain_feelsLike());
	    System.out.println("grand level - " + item.getMain_grndLevel());
	    System.out.println("humidity - " + item.getMain_humidity());
	    System.out.println("main temp - " + item.getMain_temp());
	    System.out.println("tmp min - " + item.getMain_tempMin());
	    System.out.println("tmp kf - " + item.getMain_tempKf());
	    System.out.println("temp max - " + item.getMain_tempMax());
	    System.out.println("pressure - " + item.getMain_pressure());
	    System.out.println("sea level - " + item.getMain_seaLevel());
	    System.out.println("clouds - " + item.getClouds_all());
	    System.out.println("dt txt - " + item.getDt_txt());
	    System.out.println("pop - " + item.getPop());
	    System.out.println("description - " + item.getWeather_description());
	    System.out.println("icon - " + item.getWeather_icon());
	    System.out.println("weather id - " + item.getWeather_id());
	    System.out.println("weather main - " + item.getWeather_main());
	    System.out.println("deg - " + item.getWind_deg());
	    System.out.println("gust - " + item.getWind_gust());
	    System.out.println("speed - " + item.getWind_speed());
	    System.out.println("Visibility - " + item.getVisibility());
	    System.out.println("pod - " + item.getSys_pod());
	}
}
