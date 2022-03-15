package edu.ics499.serviceImp;
import java.io.*;
import java.net.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ics499.model.payloads.*;
import edu.ics499.model.widgets.WeatherWidget;
import edu.ics499.model.widgets.Widget;
import edu.ics499.repositories.ForecastWeatherPayloadItemRepository;
import edu.ics499.repositories.PayloadRepository;
import edu.ics499.repositories.WidgetRepository;
import edu.ics499.service.WeatherWidgetService;

@Service
public class WeatherWidgetServiceImp implements WeatherWidgetService {
    private final static String forecastString = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private final static String currentWeatherString = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final static String midURL = "&appid=";
    private static URL forecastURL;
    private static URL currentWeatherURL;
    
    @Autowired
    private WidgetRepository widgetRepo;
    
    @Autowired 
    private PayloadRepository payloadRepo;
    @Autowired
    private ForecastWeatherPayloadItemRepository fwpiRepo;
    
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

    public static void requestWeatherForecast(String requestCity, ForecastWeatherPayload payload) throws IOException  {
        //ForecastWeatherPayload payload = new ForecastWeatherPayload();
        String response = getConnection(requestCity, forecastURL, forecastString);
        payload.populate(response);
        //return payload;
    }

    public static void requestCurrentWeather(String requestCity, CurrentWeatherPayload payload) throws IOException {
        //CurrentWeatherPayload payload= new CurrentWeatherPayload();
        String response = getConnection(requestCity, currentWeatherURL, currentWeatherString);
        payload.populate(response);
    }

	@Override
	public boolean checkPayloadStatus(Payload payload) {
		return (System.currentTimeMillis() - Long.parseLong(payload.getLastUpdatedTime())) > Long.parseLong(payload.getUpdateFrequency());
	}

	@Override
	public WeatherWidget setWeatherQuery(String query, Long widgetId) {
		// TODO Auto-generated method stub
		WeatherWidget widget = getWeatherWidgetById(widgetId);
		widget.setQuery(query);
		return widgetRepo.saveAndFlush(widget);
	}

	@Override
	public List<WeatherWidget> getAll() {
		// TODO Auto-generated method stub
		
		List<WeatherWidget> widgetList = new ArrayList<WeatherWidget>();
		for(Widget widget : widgetRepo.findAll())
		{
			if(widget instanceof WeatherWidget)
			{
				widgetList.add((WeatherWidget) widget);
			}
		}
		return widgetList;
	}

	@Override
	public WeatherWidget getWeatherWidgetById(Long widgetId) {
		return (WeatherWidget) widgetRepo.findById(widgetId).orElseThrow(() -> new RuntimeException());
	}
	 //internal
	@Override
	public void updateCurrentPayload(Long widgetId) throws IOException{
		WeatherWidget widget = getWeatherWidgetById(widgetId);
		CurrentWeatherPayload current = widget.getCurrentPayload();
		//Long payloadId = current.getPayloadID();
		//payloadRepo.deleteById(current.getPayloadID());
		requestCurrentWeather(widget.getQuery(), current);
		//updated.setPayloadID(payloadId);
		//updated.setLastUpdatedTime("0");
		//updated.setUpdateFrequency("0");
		payloadRepo.saveAndFlush(current);
		//widget.setCurrentPayload();
		widgetRepo.saveAndFlush(widget);
	}
	
	//internal
	@Override
	public void updateForecastPayload(Long widgetId) throws IOException{
		WeatherWidget widget = getWeatherWidgetById(widgetId);
		ForecastWeatherPayload forecast = widget.getForecastPayload();
		requestWeatherForecast(widget.getQuery(), forecast);
		fwpiRepo.saveAllAndFlush(forecast.getList());
		payloadRepo.saveAndFlush(forecast);
		widgetRepo.saveAndFlush(widget);
	}

	@Override
	public CurrentWeatherPayload getCurrentPayload(Long widgetId) throws IOException{
		WeatherWidget widget = getWeatherWidgetById(widgetId);
		CurrentWeatherPayload payload = widget.getCurrentPayload();
		if(checkPayloadStatus(payload))
		{
			updateCurrentPayload(widgetId);
			widget = getWeatherWidgetById(widgetId);
			payload = widget.getCurrentPayload();
		}
		return payload;
	}

	@Override
	public ForecastWeatherPayload getForecastPayload(Long widgetId) throws IOException{
		WeatherWidget widget = getWeatherWidgetById(widgetId);
		ForecastWeatherPayload payload = widget.getForecastPayload();
		if(checkPayloadStatus(payload))
		{
			updateForecastPayload(widgetId);
			widget = getWeatherWidgetById(widgetId);
			payload = widget.getForecastPayload();
		}
		return payload;
	}
	
	@Override
	public WeatherWidget addWidget(String query) {
		WeatherWidget widget = new WeatherWidget();
		widget.setQuery(query);
		widget.setUnits("F");
		
		CurrentWeatherPayload current = new CurrentWeatherPayload();
		ForecastWeatherPayload forecast = new ForecastWeatherPayload();
		
		current.setLastUpdatedTime("0");
		current.setUpdateFrequency("0");
		forecast.setLastUpdatedTime("0");
		forecast.setUpdateFrequency("0");
		
		payloadRepo.save(current);
		payloadRepo.save(forecast);
		
		widget.setCurrentPayload(current);
		widget.setForecastPayload(forecast);
		
		return widgetRepo.saveAndFlush(widget);
	}
}
