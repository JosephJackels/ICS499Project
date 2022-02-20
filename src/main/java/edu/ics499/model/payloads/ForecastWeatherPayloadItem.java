package edu.ics499.model.payloads;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="forecastPayloadItems")
public class ForecastWeatherPayloadItem {
	
	@Id
	@GeneratedValue
	private Long forecastPayloadItemID;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private ForecastWeatherPayload parent;
	
	private String dt;
	
	private String main_temp;
	private String main_feelsLike;
	private String main_tempMin;
	private String main_tempMax;
	private String main_pressure;
	private String main_seaLevel;
	private String main_grndLevel;
	private String main_humidity;
	private String main_tempKf;
	
	private String weather_id;
	private String weather_main;
	private String weather_description;
	private String weather_icon;
	
	private String clouds_all;
	
	private String wind_speed;
	private String wind_deg;
	private String wind_gust;
	
	private String visibility;
	
	private String pop;
	
	private String rain_3h;
	
	private String sys_pod;
	
	private String dt_txt;

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getMain_temp() {
		return main_temp;
	}

	public void setMain_temp(String main_temp) {
		this.main_temp = main_temp;
	}

	public String getMain_feelsLike() {
		return main_feelsLike;
	}

	public void setMain_feelsLike(String main_feelsLike) {
		this.main_feelsLike = main_feelsLike;
	}

	public String getMain_tempMin() {
		return main_tempMin;
	}

	public void setMain_tempMin(String main_tempMin) {
		this.main_tempMin = main_tempMin;
	}

	public String getMain_tempMax() {
		return main_tempMax;
	}

	public void setMain_tempMax(String main_tempMax) {
		this.main_tempMax = main_tempMax;
	}

	public String getMain_pressure() {
		return main_pressure;
	}

	public void setMain_pressure(String main_pressure) {
		this.main_pressure = main_pressure;
	}

	public String getMain_seaLevel() {
		return main_seaLevel;
	}

	public void setMain_seaLevel(String main_seaLevel) {
		this.main_seaLevel = main_seaLevel;
	}

	public String getMain_grndLevel() {
		return main_grndLevel;
	}

	public void setMain_grndLevel(String main_grndLevel) {
		this.main_grndLevel = main_grndLevel;
	}

	public String getMain_humidity() {
		return main_humidity;
	}

	public void setMain_humidity(String main_humidity) {
		this.main_humidity = main_humidity;
	}

	public String getMain_tempKf() {
		return main_tempKf;
	}

	public void setMain_tempKf(String main_tempKf) {
		this.main_tempKf = main_tempKf;
	}

	public String getWeather_id() {
		return weather_id;
	}

	public void setWeather_id(String weather_id) {
		this.weather_id = weather_id;
	}

	public String getWeather_main() {
		return weather_main;
	}

	public void setWeather_main(String weather_main) {
		this.weather_main = weather_main;
	}

	public String getWeather_description() {
		return weather_description;
	}

	public void setWeather_description(String weather_description) {
		this.weather_description = weather_description;
	}

	public String getWeather_icon() {
		return weather_icon;
	}

	public void setWeather_icon(String weather_icon) {
		this.weather_icon = weather_icon;
	}

	public String getClouds_all() {
		return clouds_all;
	}

	public void setClouds_all(String clouds_all) {
		this.clouds_all = clouds_all;
	}

	public String getWind_speed() {
		return wind_speed;
	}

	public void setWind_speed(String wind_speed) {
		this.wind_speed = wind_speed;
	}

	public String getWind_deg() {
		return wind_deg;
	}

	public void setWind_deg(String wind_deg) {
		this.wind_deg = wind_deg;
	}

	public String getWind_gust() {
		return wind_gust;
	}

	public void setWind_gust(String wind_gust) {
		this.wind_gust = wind_gust;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getPop() {
		return pop;
	}

	public void setPop(String pop) {
		this.pop = pop;
	}

	public String getRain_3h() {
		return rain_3h;
	}

	public void setRain_3h(String rain_3h) {
		this.rain_3h = rain_3h;
	}

	public String getSys_pod() {
		return sys_pod;
	}

	public void setSys_pod(String sys_pod) {
		this.sys_pod = sys_pod;
	}

	public String getDt_txt() {
		return dt_txt;
	}

	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}
}
