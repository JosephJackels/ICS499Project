package edu.ics499.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import edu.ics499.model.*;
import edu.ics499.model.widgets.*;
import edu.ics499.repositories.*;
import edu.ics499.serviceImp.*;
/**
 * Dashboard Controller
 * 
 * Controller that creates endpoints for operations to be performed involving dashboard including:
 * 
 * /dashboards/all - GET, returns all dashboard
 * /dashboards/one/{id} - GET, returns single dashboard by dashboardId
 * /dashboards/remove/{id}?widgetId={widgetId} - POST, removes widget (by id) from dashboard
 * /dashboards/add/{id}?widgetId={widgetId} - POST, adds a widget (by id) to a dasboard
 * /dashboards/create/ - POST, creates a dashboard via informaiton sent in the body of the request
 *
 */
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

	//get request to localhost:port/dashboards/one/{id}
	@GetMapping("/one/{id}")
	public Dashboard get(@PathVariable Long id) {
		return dashboardService.getDashboardById(id);
	}
	
	//post request to localhost:port/dashboards/remove/{dashId}?widgetId=#
	@PostMapping("/remove/{dashboardId}")
	public Widget removeWidget(@PathVariable Long dashboardId, @RequestParam Long widgetId) {
	    return dashboardService.removeWidgetFromDashboard(dashboardId, widgetId);
	}
	
	//first create widget with create widget end point then
	//post request to localhost:port/dashboards/add/{dashId}?widgetId=#
	@PostMapping("/add/{dashboardId}")
	public Dashboard addWidget(@PathVariable Long dashboardId, @RequestParam Long widgetId) {
	    return dashboardService.addWidgetToDashboard(dashboardId, widgetId);
	}

	//post request to localhost:port/dashboards/create with a Dashboard object
	//in the request body
	@PostMapping("/create")
	public Dashboard create(@RequestBody final Dashboard dashboard) {
		return dashboardRepo.saveAndFlush(dashboard);

	}
}
