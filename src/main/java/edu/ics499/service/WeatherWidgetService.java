package edu.ics499.service;
import java.io.*;
import java.net.*;
import java.util.*;

import edu.ics499.model.payloads.*;

public class WeatherWidgetService {
    private final static String forecastString = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private final static String currentWeatherString = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final static String midURL = "&appid=";
    private static URL forecastURL;
    private static URL currentWeatherURL;

    public static String getConnection(String requestCity, URL url, String weatherType) throws IOException {
        //contruct api request url with city name and api key
        HttpURLConnection conn;
        url = new URL(weatherType + requestCity + midURL + System.getenv("weather_key"));
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String lines = "";
        int code = conn.getResponseCode();
        if (code != 200) {
            throw new RuntimeException("HttpResponseCode: " + code);
        } else {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                lines += scanner.nextLine();
            }
            scanner.close();
        }
        return lines;
    }

    public static ForecastWeatherPayload requestWeatherForecast(String requestCity) throws IOException  {
        ForecastWeatherPayload payload = new ForecastWeatherPayload();
        String response = getConnection(requestCity, forecastURL, forecastString);
        payload.populate(response);
        return payload;
    }

    public static CurrentWeatherPayload requestCurrentWeather(String requestCity) throws IOException {
        CurrentWeatherPayload payload= new CurrentWeatherPayload();
        String response = getConnection(requestCity, currentWeatherURL, currentWeatherString);
        payload.populate(response);
        return payload;
    }
}
