package edu.ics499.service;

import java.net.*;

import edu.ics499.model.payloads.*;

public class ServiceTest {
    public static void main(String[] args) throws MalformedURLException, ProtocolException {
        CurrentWeatherPayload payload = WeatherWidgetService.requestCurrentWeather("london");
        payload.showPayload();
    }
}
