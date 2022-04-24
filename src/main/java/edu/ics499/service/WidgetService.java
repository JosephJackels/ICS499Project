package edu.ics499.service;

import java.io.IOException;
import java.util.List;

import edu.ics499.model.payloads.Payload;
import edu.ics499.model.widgets.Widget;
/**
 * Service class for Widget manipulation
 *
 */
public interface WidgetService {
	List<Widget> getAll();
	Widget getWidgetById(Long widgetId);
	
	boolean checkPayloadStatus(Payload payload);	
	void updatePayload(Long widgetId) throws IOException;
	Payload getPayload(Long widgetId) throws IOException;
	Widget updateQuery(String query, Long widgetId);
	Widget createWidget(String type, String query);
	Widget deleteWidget(Long widgetId);
}
