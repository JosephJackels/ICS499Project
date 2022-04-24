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
import edu.ics499.model.payloads.Payload;

/**
 * 
 * Widget Entity
 * Has a type used to get relevent information for creation and use that is not stored in the DB - api url, environement variable name, etc. See WidgetTypes for more info
 * Belongs to a dashboard, holds a payload object
 * Stores the queryParameters that make this Widget unique among others of its type and are sent to the api when updating its payload
 *
 */

@Entity
@Table(name="widgets")
@Inheritance(strategy = InheritanceType.JOINED)
public class Widget {
	
	@Id
	@GeneratedValue
	private Long widgetID;
	
	@ManyToOne
	private Dashboard dashboard;
	private Long dashboardId;
	
	@ManyToOne
	private Payload payload;
	
	private String queryParameters;
	private String type;
	
	public Widget() {
		super();
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQueryParameters() {
		return queryParameters;
	}

	public void setQueryParameters(String queryParameters) {
		this.queryParameters = queryParameters;
	}

	public Long getWidgetID() {
		return widgetID;
	}

	public void setWidgetID(Long widgetID) {
		this.widgetID = widgetID;
	}
	
	public Long getDashboardId() {
		return this.dashboardId;
	}
	
	public void setDashboardId(Long dashboardId) {
		this.dashboardId = dashboardId;
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
