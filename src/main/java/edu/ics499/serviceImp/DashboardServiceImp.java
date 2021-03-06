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
    private DashboardRepository dashboardRepo;

    @Autowired
    private WidgetServiceImp widgetService;

    @Override
    public Dashboard getDashboardByUserId(Long userId) {
        return dashboardRepo.findByUserId(userId);
    }

    @Override
    public Widget removeWidgetFromDashboard(Long dashboardId, Long widgetId) {
        Dashboard dash = dashboardRepo.findById(dashboardId).orElseThrow(() -> new RuntimeException());
        List<Widget> widgets = dash.getWidgetList();
        int index = 0;
        Widget removedWidget = new Widget();
        for (Widget widget : widgets) {
            if (widget.getWidgetID() == widgetId) {
                removedWidget = widgets.remove(index);
                break;
            }
            index++ ;
        }
        dashboardRepo.saveAndFlush(dash);
        return removedWidget;
    }

    @Override
    public Dashboard addWidgetToDashboard(Long dashboardId, Long widgetId) {
        Dashboard db = dashboardRepo.findById(dashboardId).orElseThrow(() -> new RuntimeException());
        Widget widget = widgetService.getWidgetById(widgetId);
        widget.setDashboardId(db.getDashboardID());
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
