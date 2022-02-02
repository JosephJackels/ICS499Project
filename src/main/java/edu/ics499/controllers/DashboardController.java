package edu.ics499.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ics499.model.Dashboard;
import edu.ics499.repositories.DashboardRepository;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {
	@Autowired
	private DashboardRepository dashboardRepo;
	
	//get request to localhost:port/dashboards/all
	@GetMapping("/all")
	public List<Dashboard> list(){
		return dashboardRepo.findAll();
	}
	
	//get request to localhost:port/dashboards?id=#
	@GetMapping("{id}")
	public Dashboard get(@PathVariable Long id) {
		return dashboardRepo.getById(id);
	}
	
	//post request to localhost:port/dashboards/add with a Dashboard object
	//in the request body? not sure how this works yet/how to use it
	@PostMapping("/add")
	public Dashboard create(@RequestBody final Dashboard dashboard) {

		return dashboardRepo.saveAndFlush(dashboard);
		
	}
}
