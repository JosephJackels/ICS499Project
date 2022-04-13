package edu.ics499.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ics499.model.payloads.Payload;
import edu.ics499.model.widgets.Widget;
import edu.ics499.model.widgets.WidgetTypes;
import edu.ics499.serviceImp.WidgetServiceImp;

@RestController
@RequestMapping("/widgets")
public class WidgetController {
	@Autowired
	WidgetServiceImp widgetService;
	
	//localhost:port/widgets/all
	@GetMapping("/get/all")
	public List<Widget> list(){
		return widgetService.getAll();
	}
	
	//localhost:port/widgets/one/{id}
	@GetMapping("/get/{id}")
	public Widget get(@PathVariable Long id) {
		return widgetService.getWidgetById(id);
	}
	
	@GetMapping("get/{id}/payload")
	public Payload getPayload(@PathVariable long id) throws IOException{
		return widgetService.getPayload(id);
	}
	
	@PostMapping("/add/{widgetType}")
	public Widget createWidget(@PathVariable String widgetType, @RequestParam String query) {
		System.out.println("WidgetController - adding new widget of " + widgetType);
		if(WidgetTypes.types.contains(widgetType)) {	
			return widgetService.createWidget(widgetType, query);
		} else {
			//throw an error?
			return null;
		}
	}
	
	@PostMapping("update/{id}")
	public Widget updateWidget(@PathVariable long id, @RequestParam String query) {
		return widgetService.updateQuery(query, id);
	}
}
