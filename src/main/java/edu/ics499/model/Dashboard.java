package edu.ics499.model;

import java.util.*;

import javax.persistence.*;

import edu.ics499.model.widgets.*;

@Entity
@Table(name="dashboards")
public class Dashboard {

	@Id
	@GeneratedValue
	private Long dashboardID;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "dashboards_widgets",
			joinColumns = @JoinColumn(name = "dashboardID"),
			inverseJoinColumns = @JoinColumn(name = "widgetID"))
	private List<Widget> widgetList = new ArrayList<>();
	//not sure why we are including this but in his examples he
	//has an object in both classes whenever theres a relationship even if the object isnt used
	@OneToOne
	private User user;

	public Dashboard() {
		super();
		this.widgetList = new ArrayList<>();
	}

	public Long getDashboardID() {
		return dashboardID;
	}

	public void setDashboardID(Long dashboardID) {
		this.dashboardID = dashboardID;
	}

	public List<Widget> getWidgetList() {
		return widgetList;
	}

	public void setWidgetList(List<Widget> widgetList) {
		this.widgetList = widgetList;
	}

	public void addWidget(Widget widget) {
	    widgetList.add(widget);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dashboardID, widgetList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dashboard other = (Dashboard) obj;
		return Objects.equals(dashboardID, other.dashboardID) && Objects.equals(widgetList, other.widgetList);
	}

	@Override
	public String toString() {
		String dashboardString = "Dashboard [dashboardID=" + dashboardID + ", widgetList=";

		for(Widget widget : widgetList) {
			dashboardString += widget.toString() + "\n";
		}
		dashboardString += "]";

		return dashboardString;
	}

}
