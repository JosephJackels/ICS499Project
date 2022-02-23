package edu.ics499.service;

import java.util.List;

import edu.ics499.model.Dashboard;
import edu.ics499.model.widgets.Widget;

public interface DashboardService {
	Dashboard getDashboardById(Long dashboardId);
	List<Dashboard> getAll();
	Dashboard getDashboardByUserId(Long userId);
	Widget removeWidgetFromDashboard(Long dashboardId, Long widgetId);
	Widget addWidgetToDashboard(Long dashboardId, Widget widget);
}
