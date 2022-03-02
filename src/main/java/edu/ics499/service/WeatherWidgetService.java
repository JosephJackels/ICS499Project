package edu.ics499.service;

import java.io.IOException;
import java.util.List;

import edu.ics499.model.payloads.Payload;
import edu.ics499.model.widgets.WeatherWidget;

public interface WeatherWidgetService {
	
	boolean checkPayloadStatus(Payload payload);
	
	void updateCurrentPayload(Long widgetId) throws IOException;
	void updateForecastPayload(Long widgetId) throws IOException;
	Payload getCurrentPayload(Long widgetId) throws IOException;
	Payload getForecastPayload(Long widgetId) throws IOException;
	
	void setWeatherQuery(String query, Long widgetId);
	
	List<WeatherWidget> getAll();
	WeatherWidget getWeatherWidgetById(Long widgetId);
	
}
