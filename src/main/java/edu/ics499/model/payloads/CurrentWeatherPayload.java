package edu.ics499.model.payloads;

public class CurrentWeatherPayload extends Payload {
	
	private String coord_lon;
	private String coord_lat;
	
	private String weather_id;
	private String weather_main;
	private String weather_description;
	private String weather_icon;
	
	private String base;
	
	private String main_temp;
	private String main_feelslike;
	private String main_tempmin;
	private String main_tempmax;
	private String main_pressure;
	private String main_humidity;
	
	private String visibility;
	
	public String getPayloadID() {
		return PayloadID;
	}

	public void setPayloadID(String payloadID) {
		PayloadID = payloadID;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(String updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	public String getCoord_lon() {
		return coord_lon;
	}

	public void setCoord_lon(String coord_lon) {
		this.coord_lon = coord_lon;
	}

	public String getCoord_lat() {
		return coord_lat;
	}

	public void setCoord_lat(String coord_lat) {
		this.coord_lat = coord_lat;
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

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getMain_temp() {
		return main_temp;
	}

	public void setMain_temp(String main_temp) {
		this.main_temp = main_temp;
	}

	public String getMain_feelslike() {
		return main_feelslike;
	}

	public void setMain_feelslike(String main_feelslike) {
		this.main_feelslike = main_feelslike;
	}

	public String getMain_tempmin() {
		return main_tempmin;
	}

	public void setMain_tempmin(String main_tempmin) {
		this.main_tempmin = main_tempmin;
	}

	public String getMain_tempmax() {
		return main_tempmax;
	}

	public void setMain_tempmax(String main_tempmax) {
		this.main_tempmax = main_tempmax;
	}

	public String getMain_pressure() {
		return main_pressure;
	}

	public void setMain_pressure(String main_pressure) {
		this.main_pressure = main_pressure;
	}

	public String getMain_humidity() {
		return main_humidity;
	}

	public void setMain_humidity(String main_humidity) {
		this.main_humidity = main_humidity;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
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

	public String getClouds_all() {
		return clouds_all;
	}

	public void setClouds_all(String clouds_all) {
		this.clouds_all = clouds_all;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getSys_type() {
		return sys_type;
	}

	public void setSys_type(String sys_type) {
		this.sys_type = sys_type;
	}

	public String getSys_id() {
		return sys_id;
	}

	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	public String getSys_message() {
		return sys_message;
	}

	public void setSys_message(String sys_message) {
		this.sys_message = sys_message;
	}

	public String getSys_country() {
		return sys_country;
	}

	public void setSys_country(String sys_country) {
		this.sys_country = sys_country;
	}

	public String getSys_sunrise() {
		return sys_sunrise;
	}

	public void setSys_sunrise(String sys_sunrise) {
		this.sys_sunrise = sys_sunrise;
	}

	public String getSys_sunset() {
		return sys_sunset;
	}

	public void setSys_sunset(String sys_sunset) {
		this.sys_sunset = sys_sunset;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	private String wind_speed;
	private String wind_deg;
	
	private String clouds_all;
	
	private String dt;
	
	private String sys_type;
	private String sys_id;
	private String sys_message;
	private String sys_country;
	private String sys_sunrise;
	private String sys_sunset;
	
	private String timezone;
	
	private String id;
	
	private String name;
	
	private String cod;
}
