package edu.ics499.serviceImp;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import edu.ics499.model.*;
import edu.ics499.model.widgets.*;
import edu.ics499.repositories.*;
import edu.ics499.service.*;

@Service
public class DashboardServiceImp implements DashboardService {

	@Autowired
	DashboardRepository dashboardRepo;

	@Override
	public Dashboard getDashboardByUserId(Long userId) {
		return dashboardRepo.findByUserUserID(userId);
	}

	@Override
	public Widget removeWidgetFromDashboard(Long dashboardId, Long widgetId) {
	    Dashboard db = dashboardRepo.findById(dashboardId).orElseThrow(() -> new RuntimeException());
	    List<Widget> widgets = db.getWidgetList();
	    int index = 0;
	    Widget removed = null;
	    for(Widget widget : widgets) {
	        if(widget.getWidgetID().equals(widgetId)) {
	            removed = widgets.remove(index);
	            break;
	        }
	        index++;
	    }
	    dashboardRepo.saveAndFlush(db);
		return removed;
	}

	@Override
	public Dashboard addWidgetToDashboard(Long dashboardId, Widget widget) {
	    Dashboard db = dashboardRepo.findById(dashboardId).orElseThrow(() -> new RuntimeException());
	    db.addWidget(widget);
	    return dashboardRepo.saveAndFlush(db);
	}

	@Override
	public Dashboard getDashboardById(Long dashboardId) {
        return dashboardRepo.findById(dashboardId).orElseThrow(() -> new RuntimeException());
	}

	@Override
	public List<Dashboard> getAll() {
		return dashboardRepo.findAll();
	}

}
