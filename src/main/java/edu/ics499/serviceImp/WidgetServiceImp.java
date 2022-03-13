package edu.ics499.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ics499.model.widgets.Widget;
import edu.ics499.repositories.WidgetRepository;
import edu.ics499.service.WidgetService;

@Service
public class WidgetServiceImp implements WidgetService{
	
	@Autowired
	private WidgetRepository widgetRepo;
	
	@Override
	public List<Widget> getAll() {
		return widgetRepo.findAll();
	}

	@Override
	public Widget getWidgetById(Long widgetId) {
		return widgetRepo.findById(widgetId).orElseThrow(() -> new RuntimeException());
	}

}
