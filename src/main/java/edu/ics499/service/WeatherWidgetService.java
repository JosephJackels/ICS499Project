package edu.ics499.service;
import java.io.*;
import java.net.*;
import java.util.*;

import org.springframework.boot.json.*;

import edu.ics499.model.payloads.*;

public class WeatherWidgetService {

    public static ForecastWeatherPayload requestWeatherForecast(String requestCity) throws MalformedURLException, ProtocolException {
        //contruct api request url with city name and api key
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + requestCity + "&appid=" + System.getenv("weather_key"));
        HttpURLConnection conn;
        ForecastWeatherPayload payload = new ForecastWeatherPayload();
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int code = conn.getResponseCode();

            if (code != 200) {
                throw new RuntimeException("HttpResponseCode: " + code);
            } else {
                String lines = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    lines += scanner.nextLine();
                }

                scanner.close();
                System.out.println(lines);

                JsonParser springParser = JsonParserFactory.getJsonParser();
                Map<String, Object> map = springParser.parseMap(lines);
                payload.setCod((String)map.get("cod"));
                payload.setMessage(String.valueOf(map.get("message")));
                payload.setCnt(String.valueOf(map.get("cnt")));
                LinkedHashMap city = (LinkedHashMap) map.get("city");
                payload.setCity_id(String.valueOf(city.get("id")));
                payload.setCity_name((String)city.get("name"));
                payload.setCity_country((String)city.get("country"));
                payload.setCity_timezone(String.valueOf(city.get("timezone")));
                payload.setCity_sunrise(String.valueOf(city.get("sunrise")));
                payload.setCity_sunset(String.valueOf(city.get("sunset")));
                LinkedHashMap coord = (LinkedHashMap) city.get("coord");
                payload.setCity_coord_lat(String.valueOf(coord.get("lat")));
                payload.setCity_coord_lon(String.valueOf(coord.get("lon")));


                List items = (List) map.get("list");
                //loop over each item day and make a new payload item
                for(int i = 0; i < items.size(); i++) {
                    LinkedHashMap day = (LinkedHashMap) items.get(i);
                    ForecastWeatherPayloadItem item = new ForecastWeatherPayloadItem();
                    item.setDt(String.valueOf(day.get("dt")));
                    item.setDt_txt((String) day.get("dt_txt"));
                    item.setVisibility(String.valueOf(day.get("visibility")));
                    item.setPop(String.valueOf(day.get("pop")));
                    LinkedHashMap main = (LinkedHashMap) day.get("main");
                    item.setMain_temp(String.valueOf(main.get("temp")));
                    item.setMain_feelsLike(String.valueOf(main.get("feels_like")));
                    item.setMain_tempMin(String.valueOf(main.get("temp_min")));
                    item.setMain_tempMax(String.valueOf(main.get("temp_max")));
                    item.setMain_pressure(String.valueOf(main.get("pressure")));
                    item.setMain_seaLevel(String.valueOf(main.get("sea_level")));
                    item.setMain_grndLevel(String.valueOf(main.get("grnd_level")));
                    item.setMain_humidity(String.valueOf(main.get("humidity")));
                    item.setMain_tempKf(String.valueOf(main.get("temp_kf")));
                    List weather_list = (List) day.get("weather");
                    LinkedHashMap weather = (LinkedHashMap) weather_list.get(0);
                    item.setWeather_id(String.valueOf(weather.get("id")));
                    item.setWeather_main((String) weather.get("main"));
                    item.setWeather_description((String)weather.get("description"));
                    item.setWeather_icon((String) weather.get("icon"));
                    LinkedHashMap clouds = (LinkedHashMap) day.get("clouds");
                    item.setClouds_all(String.valueOf(clouds.get("all")));
                    LinkedHashMap wind = (LinkedHashMap) day.get("wind");
                    item.setWind_speed(String.valueOf(wind.get("speed")));
                    item.setWind_deg(String.valueOf(wind.get("deg")));
                    item.setWind_gust(String.valueOf(wind.get("gust")));
                    LinkedHashMap sys = (LinkedHashMap) day.get("sys");
                    item.setSys_pod((String) sys.get("pod"));
                    payload.addPayloadItem(item);
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return payload;
    }

    public static CurrentWeatherPayload requestCurrentWeather(String requestCity) throws MalformedURLException {
        CurrentWeatherPayload payload= new CurrentWeatherPayload();

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + requestCity + "&appid=" + System.getenv("weather_key"));
        HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int code = conn.getResponseCode();

            if (code != 200) {
                throw new RuntimeException("HttpResponseCode: " + code);
            } else {
                String lines = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    lines += scanner.nextLine();
                }
                scanner.close();
                System.out.println(lines);
                JsonParser springParser = JsonParserFactory.getJsonParser();
                Map<String, Object> map = springParser.parseMap(lines);
                System.out.println(map);
                payload.setBase((String)map.get("base"));
                payload.setVisibility(String.valueOf(map.get("visibility")));
                payload.setDt(String.valueOf(map.get("dt")));
                payload.setTimezone(String.valueOf(map.get("timezone")));
                payload.setId(String.valueOf(map.get("id")));
                payload.setName((String)map.get("name"));
                payload.setCod(String.valueOf(map.get("cod")));
                List weather_list = (List) map.get("weather");
                LinkedHashMap weather = (LinkedHashMap) weather_list.get(0);
                payload.setWeather_description((String) weather.get("description"));
                payload.setWeather_id(String.valueOf(weather.get("id")));
                payload.setWeather_main((String) weather.get("main"));
                payload.setWeather_icon((String) weather.get("icon"));
                LinkedHashMap coord = (LinkedHashMap) map.get("coord");
                payload.setCoord_lat(String.valueOf(coord.get("lat")));
                payload.setCoord_lon(String.valueOf(coord.get("lon")));
                LinkedHashMap main = (LinkedHashMap) map.get("main");
                payload.setMain_feelslike(String.valueOf(main.get("feels_like")));
                payload.setMain_temp(String.valueOf(main.get("temp")));
                payload.setMain_tempmin(String.valueOf(main.get("temp_min")));
                payload.setMain_tempmax(String.valueOf(main.get("temp_max")));
                payload.setMain_pressure(String.valueOf(main.get("pressure")));
                payload.setMain_humidity(String.valueOf(main.get("humidity")));
                LinkedHashMap wind = (LinkedHashMap) map.get("wind");
                payload.setWind_deg(String.valueOf(wind.get("deg")));
                payload.setWind_gust(String.valueOf(wind.get("gust")));
                payload.setWind_speed(String.valueOf(wind.get("speed")));
                LinkedHashMap clouds = (LinkedHashMap) map.get("clouds");
                payload.setClouds_all(String.valueOf(clouds.get("all")));
                LinkedHashMap sys= (LinkedHashMap) map.get("sys");
                payload.setSys_country((String) sys.get("country"));
                payload.setSys_type(String.valueOf(sys.get("type")));
                payload.setSys_id(String.valueOf(sys.get("id")));
                payload.setSys_sunrise(String.valueOf(sys.get("sunrise")));
                payload.setSys_sunset(String.valueOf(sys.get("sunset")));
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return payload;
    }


}
