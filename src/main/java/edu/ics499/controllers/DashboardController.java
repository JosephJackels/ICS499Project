package edu.ics499.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import edu.ics499.model.*;
import edu.ics499.model.widgets.*;
import edu.ics499.repositories.*;
import edu.ics499.serviceImp.*;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {
	@Autowired
	private DashboardRepository dashboardRepo;
	@Autowired
	private DashboardServiceImp dashboardService;

	//get request to localhost:port/dashboards/all
	@GetMapping("/all")
	public List<Dashboard> list(){
		return dashboardService.getAll();
	}

	//get request to localhost:port/dashboards/one?id=#
	@GetMapping("/one")
	public Dashboard get(@RequestParam Long id) {
		return dashboardService.getDashboardById(id);
	}

	@PostMapping("/remove")
	public Widget removeWidget(@RequestParam Long dashboardId, @RequestParam Long widgetId) {
	    return dashboardService.removeWidgetFromDashboard(dashboardId, widgetId);
	}

	@PostMapping("/add")
	public Dashboard addWidget(Long dashboardId, Widget widget) {
	    return dashboardService.addWidgetToDashboard(dashboardId, widget);
	}

	//post request to localhost:port/dashboards/add with a Dashboard object
	//in the request body? not sure how this works yet/how to use it
	@PostMapping("/create")
	public Dashboard create(@RequestBody final Dashboard dashboard) {
		return dashboardRepo.saveAndFlush(dashboard);

	}
}
