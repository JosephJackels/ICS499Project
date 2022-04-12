package edu.ics499.model.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.ics499.model.widgets.Widget;

//abstract?
@Entity
@Table(name="payloads")
@Inheritance(strategy = InheritanceType.JOINED)
public class Payload {
	@Id
	@GeneratedValue
	private Long payloadID;
	
	@Lob
	@Column(length = 16383)
	private String jsonResponse;
	
	private String lastUpdatedTime;
	private String updateFrequency;
	
	@OneToMany
	@JoinTable(
			name="widgets_payloads",
			joinColumns = @JoinColumn(name="payloadID"),
			inverseJoinColumns = @JoinColumn(name="widgetID"))
	private List<Widget> listeners = new ArrayList<>();
	
	public String getJsonResponse() {
		return jsonResponse;
	}

	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	
	public Long getPayloadID() {
		return payloadID;
	}

	public void setPayloadID(Long payloadID) {
		this.payloadID = payloadID;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(String updateFrequency) {
		this.updateFrequency = updateFrequency;
	}
}
