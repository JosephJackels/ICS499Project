package edu.ics499.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.ics499.model.Widget;
import edu.ics499.repositories.WidgetRepository;

@RestController
@RequestMapping("/widgets")
public class WidgetController {
	@Autowired
	private WidgetRepository widgetRepo;
	
	//get request to localhost:port/widgets/all
	@GetMapping("/all")
	public List<Widget> list(){
		return widgetRepo.findAll();
	}
	
	//get request to localhost:port/widgets?id=#
	@GetMapping("{id}")
	public Widget get(@PathVariable Long id) {
		return widgetRepo.getById(id);
	}
	
	//post request to localhost:port/widgets/add with a Widget object
	//in the request body? not sure how this works yet/how to use it
	@PostMapping("/add")
	public Widget create(@RequestBody final Widget widget) {

		return widgetRepo.saveAndFlush(widget);
		
	}
}
