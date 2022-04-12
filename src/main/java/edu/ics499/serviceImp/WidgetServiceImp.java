package edu.ics499.serviceImp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ics499.model.payloads.Payload;
import edu.ics499.model.widgets.Widget;
import edu.ics499.repositories.PayloadRepository;
import edu.ics499.repositories.WidgetRepository;
import edu.ics499.service.WidgetService;
import edu.ics499.model.widgets.WidgetTypes;

@Service
public class WidgetServiceImp implements WidgetService{
	
	@Autowired
	private WidgetRepository widgetRepo;
	@Autowired PayloadRepository payloadRepo;
	
	@Override
	public List<Widget> getAll() {
		return widgetRepo.findAll();
	}

	@Override
	public Widget getWidgetById(Long widgetId) {
		return widgetRepo.findById(widgetId).orElseThrow(() -> new RuntimeException());
	}

	@Override
	public boolean checkPayloadStatus(Payload payload) {
		return (System.currentTimeMillis() - Long.parseLong(payload.getLastUpdatedTime())) > Long.parseLong(payload.getUpdateFrequency());
	}

	@Override
	public void updatePayload(Long widgetId) throws IOException {
		Widget widget = getWidgetById(widgetId);
		Payload payload = widget.getPayload();
		payload.setJsonResponse(getResponse(widget, payload));
		payloadRepo.saveAndFlush(payload);
		widgetRepo.saveAndFlush(widget);
	}
	
	public String getResponse(Widget widget, Payload payload) throws IOException{
		HttpURLConnection conn;
	    URL url = buildUrl(widget.getType(), widget.getQueryParameters());
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
        System.out.println(lines.length());
        return lines;
	}
	
	public URL buildUrl(String type, String queryParameters) throws MalformedURLException{
		String baseUrl = WidgetTypes.mapWidgetTypeToUrl.get(type);
		String midUrl = WidgetTypes.mapWidgetTypeToMidUrl.get(type);
		String apiKeyName = WidgetTypes.mapWidgetTypeToApiKeyName.get(type);
		String finalUrl = baseUrl + queryParameters + midUrl;
		if(apiKeyName != "") {
			finalUrl += System.getenv(apiKeyName);
		}
		return new URL(finalUrl);
	}

	@Override
	public Payload getPayload(Long widgetId) throws IOException {
		Widget widget = getWidgetById(widgetId);
		Payload payload = widget.getPayload();
		if(checkPayloadStatus(payload))
		{
			updatePayload(widgetId);
			widget = getWidgetById(widgetId);
			payload = widget.getPayload();
		}
		return payload;
	}

	@Override
	public Widget updateQuery(String query, Long widgetId) {
		Widget widget = getWidgetById(widgetId);
		widget.setQueryParameters(query);
		return widgetRepo.saveAndFlush(widget);
	}

	@Override
	public Widget createWidget(String type, String query) {
		Widget widget = new Widget();
		widget.setQueryParameters(query);
		widget.setType(type);
		
		Payload payload = new Payload();
		
		payload.setLastUpdatedTime("0");
		payload.setUpdateFrequency("0");
		
		payloadRepo.save(payload);
		
		widget.setPayload(payload);
		
		return widgetRepo.saveAndFlush(widget);
	}

}
