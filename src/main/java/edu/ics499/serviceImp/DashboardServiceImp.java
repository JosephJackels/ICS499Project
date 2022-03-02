package edu.ics499.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ics499.model.Dashboard;
import edu.ics499.model.widgets.Widget;
import edu.ics499.repositories.DashboardRepository;
import edu.ics499.service.DashboardService;

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
		Dashboard dash = dashboardRepo.findById(dashboardId).orElseThrow(() -> new RuntimeException());
		List<Widget> widgets = dash.getWidgetList();
		int index = 0;
		Widget removedWidget = new Widget();
		for(Widget widget : widgets) {
			if(widget.getWidgetID() == widgetId) {
				removedWidget = widgets.remove(index);
				break;
			}
			index++;
		}
		dashboardRepo.saveAndFlush(dash);
		return removedWidget;
	}

	@Override
	public Widget addWidgetToDashboard(Long dashboardId, Widget widget) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dashboard getDashboardById(Long dashboardId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dashboard> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
