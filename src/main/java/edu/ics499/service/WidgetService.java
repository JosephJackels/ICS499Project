package edu.ics499.service;

import java.util.List;

import edu.ics499.model.widgets.Widget;

public interface WidgetService {
	List<Widget> getAll();
	Widget getWidgetById(Long widgetId);
}
