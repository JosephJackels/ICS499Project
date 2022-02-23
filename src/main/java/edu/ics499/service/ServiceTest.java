package edu.ics499.service;

import java.io.*;

import edu.ics499.model.payloads.*;

public class ServiceTest {
    public static void main(String[] args) throws IOException {
        CurrentWeatherPayload payload = WeatherWidgetService.requestCurrentWeather("london");
        payload.showPayload();
    }
}
