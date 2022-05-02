package edu.ics499.serviceImp;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import edu.ics499.model.payloads.*;
import edu.ics499.model.widgets.*;
import edu.ics499.repositories.*;
import edu.ics499.service.*;

@Service
public class WidgetServiceImp implements WidgetService {
    @Autowired
    private WidgetRepository widgetRepo;
    @Autowired
    private PayloadRepository payloadRepo;

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
        return (System.currentTimeMillis() - Long.parseLong(payload.getLastUpdatedTime())) > Long
            .parseLong(payload.getUpdateFrequency());
    }

    @Override
    public void updatePayload(Long widgetId) throws IOException {
        Widget widget = getWidgetById(widgetId);
        Payload payload = widget.getPayload();
        try {
            payload.setJsonResponse(getResponse(widget, payload));
        } catch (IOException x) {
            x.printStackTrace();
        } catch (InterruptedException x) {
            x.printStackTrace();
        }
        payloadRepo.saveAndFlush(payload);
        widgetRepo.saveAndFlush(widget);
    }

    public String getResponse(Widget widget, Payload payload) throws IOException, InterruptedException {
        HttpURLConnection conn;
        if (widget.getType().equals("stock")) {
            return this.connectToStockApi(widget, widget.getQueryParameters());
        }
        URL url = buildUrl(widget.getType(), widget.getQueryParameters());
        // check for widgets that have no api to connect to e.x. calender
        if (url.getPath() == "") {
            return "";
        }

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

    // this works
    public String connectToStockApi(Widget widget, String params) throws IOException, InterruptedException {
        System.out.println("Stock API is being called for Widget: " + widget.getWidgetID());
        String baseUrl = WidgetTypes.mapWidgetTypeToUrl.get(widget.getType());
        String midUrl = WidgetTypes.mapWidgetTypeToMidUrl.get(widget.getType());
        String finalUrl = baseUrl + params + midUrl;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalUrl))
            .header("x-api-key", System.getenv(WidgetTypes.mapWidgetTypeToApiKeyName.get("stock")))
            .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String lines = response.body();
        return lines;
    }

    public URL buildUrl(String type, String queryParameters) throws MalformedURLException {
        String baseUrl = WidgetTypes.mapWidgetTypeToUrl.get(type);
        String midUrl = WidgetTypes.mapWidgetTypeToMidUrl.get(type);
        String apiKeyName = WidgetTypes.mapWidgetTypeToApiKeyName.get(type);
        String finalUrl = baseUrl + queryParameters + midUrl;
        if (apiKeyName != "") {
            finalUrl += System.getenv(apiKeyName);
        }
        return new URL(finalUrl);
    }

    @Override
    public Payload getPayload(Long widgetId) throws IOException {
        Widget widget = getWidgetById(widgetId);
        Payload payload = widget.getPayload();
        if (checkPayloadStatus(payload)) {
            updatePayload(widgetId);
            widget = getWidgetById(widgetId);
            payload = widget.getPayload();
            payload.setLastUpdatedTime(Long.toString(System.currentTimeMillis()));
            // reset payloadLoadLastUpdateTime
        }
        payloadRepo.saveAndFlush(payload);
        widgetRepo.saveAndFlush(widget);
        return payload;
    }

    @Override
    public Widget updateQuery(String query, Long widgetId) {
        Widget widget = getWidgetById(widgetId);
        widget.setQueryParameters(query);
        Payload payload = checkForPayload(widget.getType(), query);
        widget.setPayload(payload);
        return widgetRepo.saveAndFlush(widget);
    }

    public Payload checkForPayload(String type, String query) {
        Payload payload = new Payload();
        List<Widget> list = getAll();
        Iterator<Widget> it = list.iterator();
        boolean found = false;
        while (it.hasNext() && !found) {
            Widget next = it.next();
            String nextType = next.getType();
            String nextQuery = next.getQueryParameters();
            if (nextType.equals(type) && nextQuery.equals(query)) {
                payload = next.getPayload();
                found = true;
            }
        }
        if (payload.getPayloadID() == null) {
            payload.setLastUpdatedTime("0");
            // set to 0 so that it is automatically update the first time it is fetched
            payload.setUpdateFrequency(WidgetTypes.mapWidgetTypeToUpdateFrequency.get(type));
            // set to whatever we want to update
            payloadRepo.save(payload);
        }
        return payload;
    }

    @Override
    public Widget createWidget(String type, String query) {
        Widget widget = new Widget();
        widget.setQueryParameters(query);
        widget.setType(type);
        Payload payload = checkForPayload(type, query);
        widget.setPayload(payload);
        return widgetRepo.saveAndFlush(widget);
    }

    @Override
    public Widget deleteWidget(Long widgetId) {
        Widget widget = widgetRepo.getById(widgetId);
        widgetRepo.deleteById(widgetId);
        // widgetRepo.saveAndFlush(widget);
        return widget;
    }

}
