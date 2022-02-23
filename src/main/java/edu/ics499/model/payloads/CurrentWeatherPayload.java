package edu.ics499.model.payloads;

import java.util.*;

import javax.persistence.*;

import org.springframework.boot.json.*;

import edu.ics499.model.widgets.*;

@Entity
@Table(name="currentWeatherPayloads")
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
	private String wind_speed;
    private String wind_deg;
    private String wind_gust;

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

	@OneToMany
	@JoinTable(
			name="widgets_currentWeatherPayloads",
			joinColumns = @JoinColumn(name="payloadID"),
			inverseJoinColumns = @JoinColumn(name="widgetID"))
	private List<WeatherWidget> listeners = new ArrayList<>();

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

	public void showPayload() {
	    System.out.println("base - " + base);
	    System.out.println("visibility - " + visibility);
	    System.out.println("dt - " + dt);
	    System.out.println("timezone - " + timezone);
	    System.out.println("id - " + id);
	    System.out.println("name - " + name);
	    System.out.println("cod - " + cod);
	    System.out.println("weather main - " + weather_main);
	    System.out.println("weather id - " + weather_id);
	    System.out.println("weather descritption - " + weather_description);
	    System.out.println("weather icon - " + weather_icon);
	    System.out.println("lat - " + coord_lat);
	    System.out.println("lon - " + coord_lon);
	    System.out.println("temp - " + main_temp);
	    System.out.println("min - " + main_tempmin);
	    System.out.println("max - " + main_tempmax);
	    System.out.println("feels like - " + main_feelslike);
	    System.out.println("pressure - " + main_pressure);
	    System.out.println("humidity - " + main_humidity);
	    System.out.println("speed - " + wind_speed);
	    System.out.println("deg - " + wind_deg);
	    System.out.println("gust - " + wind_gust);
	    System.out.println("clouds - " + clouds_all);
	    System.out.println("sunrise - " + sys_sunrise);
	    System.out.println("sunset - " + sys_sunset);
	    System.out.println("country - " + sys_country);
	    System.out.println("sys id - " + sys_id);
	    System.out.println("type - " + sys_type);
	    System.out.println("coord lat - " + coord_lat);
	    System.out.println("coord lon - " + coord_lon);
	}

    public String getWind_gust() {
        return wind_gust;
    }

    public void setWind_gust(String wind_gust) {
        this.wind_gust = wind_gust;
    }

    public void populate(String response) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(response);
        this.setBase((String)map.get("base"));
        this.setVisibility(String.valueOf(map.get("visibility")));
        this.setDt(String.valueOf(map.get("dt")));
        this.setTimezone(String.valueOf(map.get("timezone")));
        this.setId(String.valueOf(map.get("id")));
        this.setName((String)map.get("name"));
        this.setCod(String.valueOf(map.get("cod")));
        List weather_list = (List) map.get("weather");
        LinkedHashMap weather = (LinkedHashMap) weather_list.get(0);
        this.setWeather_description((String) weather.get("description"));
        this.setWeather_id(String.valueOf(weather.get("id")));
        this.setWeather_main((String) weather.get("main"));
        this.setWeather_icon((String) weather.get("icon"));
        LinkedHashMap coord = (LinkedHashMap) map.get("coord");
        this.setCoord_lat(String.valueOf(coord.get("lat")));
        this.setCoord_lon(String.valueOf(coord.get("lon")));
        LinkedHashMap main = (LinkedHashMap) map.get("main");
        this.setMain_feelslike(String.valueOf(main.get("feels_like")));
        this.setMain_temp(String.valueOf(main.get("temp")));
        this.setMain_tempmin(String.valueOf(main.get("temp_min")));
        this.setMain_tempmax(String.valueOf(main.get("temp_max")));
        this.setMain_pressure(String.valueOf(main.get("pressure")));
        this.setMain_humidity(String.valueOf(main.get("humidity")));
        LinkedHashMap wind = (LinkedHashMap) map.get("wind");
        this.setWind_deg(String.valueOf(wind.get("deg")));
        this.setWind_gust(String.valueOf(wind.get("gust")));
        this.setWind_speed(String.valueOf(wind.get("speed")));
        LinkedHashMap clouds = (LinkedHashMap) map.get("clouds");
        this.setClouds_all(String.valueOf(clouds.get("all")));
        LinkedHashMap sys= (LinkedHashMap) map.get("sys");
        this.setSys_country((String) sys.get("country"));
        this.setSys_type(String.valueOf(sys.get("type")));
        this.setSys_id(String.valueOf(sys.get("id")));
        this.setSys_sunrise(String.valueOf(sys.get("sunrise")));
        this.setSys_sunset(String.valueOf(sys.get("sunset")));
    }
}
