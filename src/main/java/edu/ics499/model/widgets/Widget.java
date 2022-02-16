package edu.ics499.model.widgets;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.ics499.model.Dashboard;

@Entity
@Table(name="widgets")
@Inheritance(strategy = InheritanceType.JOINED)
public class Widget {
	
	@Id
	@GeneratedValue
	private Long widgetID;
	
	//not sure if or why we need this but he always has a corresponding class
	//when there is a relationship between models
	@ManyToOne
	private Dashboard dashboard;
	//not sure what other info to put here?
	//maybe like a base URL string?
	
	public Widget() {
		super();
	}

	public Long getWidgetID() {
		return widgetID;
	}

	public void setWidgetID(Long widgetID) {
		this.widgetID = widgetID;
	}
	
	//NEEDS TO BE UPDATED IF WE ADD OTHER PROPERTIES
	@Override
	public int hashCode() {
		return Objects.hash(widgetID);
	}
	
	//NEEDS TO BE UPDATED IF WE ADD OTHER PROPERTIES
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Widget other = (Widget) obj;
		return Objects.equals(widgetID, other.widgetID);
	}
	
	//NEEDS TO BE UPDATED IF WE ADD OTHER PROPERTIES
	@Override
	public String toString() {
		return "Widget [widgetID=" + widgetID + "]";
	}
	
	
	
	
}
