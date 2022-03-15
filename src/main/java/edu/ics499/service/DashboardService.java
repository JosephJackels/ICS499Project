package edu.ics499.service;

import java.util.*;

import edu.ics499.model.*;
import edu.ics499.model.widgets.*;

public interface DashboardService {
	Dashboard getDashboardById(Long dashboardId);
	List<Dashboard> getAll();
	Dashboard getDashboardByUserId(Long userId);
	Widget removeWidgetFromDashboard(Long dashboardId, Long widgetId);
	Dashboard addWidgetToDashboard(Long dashboardId, Long widgetId);
}

